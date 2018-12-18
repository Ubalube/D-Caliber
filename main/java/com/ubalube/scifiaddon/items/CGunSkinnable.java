package com.ubalube.scifiaddon.items;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.annotation.Nullable;

import org.lwjgl.input.Keyboard;

import com.google.common.collect.Multimap;
import com.ubalube.scifiaddon.main;
import com.ubalube.scifiaddon.entity.EntityBullet;
import com.ubalube.scifiaddon.init.ModItems;
import com.ubalube.scifiaddon.util.IHasModel;
import com.ubalube.scifiaddon.util.handlers.SoundHandler;

import akka.event.Logging.Debug;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemElytra;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import scala.reflect.internal.Trees.Modifiers;

public class CGunSkinnable extends Item implements IHasModel
{
	
	int Firerate;
	int clipsize;
	int ReloadTime;
	int AutoFiremode;
	int SingleFiremode;
	float damage;
	int range;
	Item ammo;
	int skinamt;
	
	public CGunSkinnable(String name, CreativeTabs tab, int fireRate, int ammocap, int reloadtm, int AFiremode, int SFiremode, float bulletDamage, int bulletDuration, Item ammunition, int skins) 
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);
		setMaxStackSize(1);
		
		this.Firerate = fireRate;
		this.clipsize = ammocap;
		this.ReloadTime = reloadtm;
		this.AutoFiremode = AFiremode;
		this.SingleFiremode = SFiremode;
		this.damage = bulletDamage;
		this.range = bulletDuration;
		this.ammo = ammunition;
		
		setMaxDamage(clipsize);
		
		this.skinamt = skins;
		
		this.addPropertyOverride(new ResourceLocation("skin"), new IItemPropertyGetter()
        {
			@SideOnly(Side.CLIENT)
	        public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
	        {
	            if (entityIn == null) {
	                return 0.0F;
	            }
	            NBTTagCompound nbt = checkNBTTags(stack);
	            float j = nbt.getInteger("SKIN");
	            return j; 
	        }
        });
		
		ModItems.ITEMS.add(this);
	}
	
	public static NBTTagCompound checkNBTTags(ItemStack stack) {
        NBTTagCompound nbt = stack.getTagCompound();
        if (nbt == null) {
            nbt = new NBTTagCompound();
            stack.setTagCompound(nbt);
        }
        if (!nbt.hasKey("SKIN")) {
            nbt.setInteger("SKIN", 0);
        }
        
        return nbt;
    }
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) 
	{
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
		{
			tooltip.add(TextFormatting.YELLOW + "Impact: " + TextFormatting.GREEN + damage);
			tooltip.add(TextFormatting.YELLOW + "Range:  " + TextFormatting.GREEN + range);
			tooltip.add(TextFormatting.YELLOW + "Clipsize: " + TextFormatting.GREEN + clipsize);
		}
		else
		{
			tooltip.add(TextFormatting.YELLOW + "Press LSHIFT for stats!");
		}
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.BOW;
    }
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) 
	{
		int firemode = Firerate;
		
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		
		NBTTagCompound nbt = itemstack.getTagCompound();
		if(nbt == null)
		{
			nbt = new NBTTagCompound();
		}
		
		if(!nbt.hasKey("firerate"))
		{
			nbt.setInteger("firerate", SingleFiremode);
		}
		
		nbt = nbt.getCompoundTag("firerate");
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
		{
			if(!worldIn.isRemote)
			{
					if(nbt.getInteger("firerate") == SingleFiremode)
					{
						nbt.setInteger("firerate", AutoFiremode);
					}
					else
					{
						nbt.setInteger("firerate", SingleFiremode);
					}
					System.out.println("GUN FIREMODE");
				
				itemstack.setTagInfo("firerate", nbt);
				
			}
		}
		else
		{
			if (!playerIn.capabilities.isCreativeMode)
			{
				if(itemstack.isItemDamaged())
				{
					if(itemstack.getItemDamage() == clipsize)
					{
						if(playerIn.inventory.hasItemStack(new ItemStack(ammo)))
						{
							itemstack.setItemDamage(-clipsize);
							playerIn.inventory.clearMatchingItems(ammo, 0, 1, null);
							playerIn.getCooldownTracker().setCooldown(this, ReloadTime);
						}
					}
					else
					{
						playerIn.getCooldownTracker().setCooldown(this, nbt.getInteger("firerate"));
						if (!worldIn.isRemote)
						{
							EntityBullet entity = new EntityBullet(worldIn, playerIn, range);
							entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 1.0F, 1.5F, 0.0F);
							entity.setBulletDamage(this.damage);
							worldIn.spawnEntity(entity);
							itemstack.damageItem(1, playerIn);
							System.out.println("GUN SHOOTING");
							
						}
						worldIn.playSound(playerIn,	playerIn.posX, playerIn.posY, playerIn.posZ, SoundHandler.GUN_RIFLE_SHOOT, SoundCategory.MASTER, 1, 1);
					}
					
				}
				else
				{
					//First Bullet
					playerIn.getCooldownTracker().setCooldown(this, nbt.getInteger("firerate"));
					if(!worldIn.isRemote)
					{
						EntityBullet entity = new EntityBullet(worldIn, playerIn, range);
						entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 1.0F, 1.5F, 0.0F);
						entity.setBulletDamage(this.damage);
						worldIn.spawnEntity(entity);
						itemstack.damageItem(1, playerIn);
						System.out.println("GUN SHOOTING");
						
					}
					worldIn.playSound(playerIn,	playerIn.posX, playerIn.posY, playerIn.posZ, SoundHandler.GUN_RIFLE_SHOOT, SoundCategory.MASTER, 1, 1);
					
				}
			}
			
			else
			{
				
				//Creative Move
				playerIn.getCooldownTracker().setCooldown(this, nbt.getInteger("firerate"));
				if(!worldIn.isRemote)
				{
					EntityBullet entity = new EntityBullet(worldIn, playerIn, range);
					entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 1.0F, 1.5F, 0.0F);
					entity.setBulletDamage(this.damage);
					worldIn.spawnEntity(entity);
					itemstack.damageItem(1, playerIn);
					System.out.println("GUN SHOOTING");
				}
				worldIn.playSound(playerIn,	playerIn.posX, playerIn.posY, playerIn.posZ, SoundHandler.GUN_RIFLE_SHOOT, SoundCategory.MASTER, 1, 1);
				
			}
		}
		playerIn.addStat(StatList.getObjectUseStats(this));
		return new ActionResult(EnumActionResult.PASS, itemstack);
	}
	
	@Override
	public void registerModels() 
	{
		main.proxy.registerItemRender(this, 0, "inventory");
		
	}

}
