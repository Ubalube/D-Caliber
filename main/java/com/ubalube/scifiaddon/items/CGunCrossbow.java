package com.ubalube.scifiaddon.items;

import java.util.List;

import javax.annotation.Nullable;

import org.lwjgl.input.Keyboard;

import com.ubalube.scifiaddon.main;
import com.ubalube.scifiaddon.entity.EntityBullet;
import com.ubalube.scifiaddon.init.ModItems;
import com.ubalube.scifiaddon.util.IHasModel;
import com.ubalube.scifiaddon.util.handlers.SoundHandler;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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

public class CGunCrossbow extends Item implements IHasModel
{
	
	int Firerate;
	int clipsize;
	int ReloadTime;
	int AutoFiremode;
	int SingleFiremode;
	float damage;
	int range;
	Item ammo;
	
	public CGunCrossbow(String name, CreativeTabs tab, int fireRate, int ammocap, int reloadtm, int AFiremode, int SFiremode, float bulletDamage, int bulletDuration, Item ammunition) 
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
		
		this.addPropertyOverride(new ResourceLocation("reload"), new IItemPropertyGetter()
        {
			@SideOnly(Side.CLIENT)
	        public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
	        {
	            NBTTagCompound nbt = checkNBTTags(stack);
	            float j = nbt.getInteger("STATE");
	            return j; 
	        }
        });
		
		setMaxDamage(clipsize);
		
		ModItems.ITEMS.add(this);
	}
	
	public static NBTTagCompound checkNBTTags(ItemStack stack) {
        NBTTagCompound nbt = stack.getTagCompound();
        if (nbt == null) {
            nbt = new NBTTagCompound();
            stack.setTagCompound(nbt);
        }
        if (!nbt.hasKey("STATE")) {
            nbt.setInteger("STATE", 0);
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
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return false;
    }
	
	@Override
	public void onUsingTick(ItemStack stack, EntityLivingBase player, int count) 
	{
		NBTTagCompound nbt = stack.getTagCompound();
		if(nbt == null)
        {
            nbt = new NBTTagCompound();
            stack.setTagCompound(nbt);
        }
        
		if(stack.getItemDamage() == 1)
		{
			nbt.setBoolean("STATE", true);
		}
		else
		{
			nbt.setBoolean("STATE", false);
		}
		
        
		super.onUsingTick(stack, player, count);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) 
	{
		int firemode = AutoFiremode;
		
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
		
		/*if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
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
				
				itemstack.setTagInfo("firerate", nbt);
				
			}
		}
		else
		{*/
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
						playerIn.getCooldownTracker().setCooldown(this, firemode);
						if (!worldIn.isRemote)
						{
							EntityBullet entity = new EntityBullet(worldIn, playerIn);
							entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 1.0F, 2.0F, 0.0F);
							entity.setDamage((double)this.damage);
							entity.setRange(this.range);
							worldIn.spawnEntity(entity);
							itemstack.damageItem(1, playerIn);
							
						}
						worldIn.playSound(playerIn,	playerIn.posX, playerIn.posY, playerIn.posZ, SoundHandler.GUN_RIFLE_SHOOT, SoundCategory.MASTER, 1, 1);
					}
					
				}
				else
				{
					//First Bullet
					playerIn.getCooldownTracker().setCooldown(this, firemode);
					if(!worldIn.isRemote)
					{
						EntityBullet entity = new EntityBullet(worldIn, playerIn);
						entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 1.0F, 2.0F, 0.0F);
						entity.setDamage((double)this.damage);
						entity.setRange(this.range);
						worldIn.spawnEntity(entity);
						itemstack.damageItem(1, playerIn);
						
					}
					worldIn.playSound(playerIn,	playerIn.posX, playerIn.posY, playerIn.posZ, SoundHandler.GUN_RIFLE_SHOOT, SoundCategory.MASTER, 1, 1);
					
				}
			}
			
			else
			{
				
				//Creative Move
				playerIn.getCooldownTracker().setCooldown(this, firemode);
				if(!worldIn.isRemote)
				{
					EntityBullet entity = new EntityBullet(worldIn, playerIn);
					entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 1.0F, 2.0F, 0.0F);
					entity.setDamage((double)this.damage);
					entity.setRange(this.range);
					worldIn.spawnEntity(entity);
					itemstack.damageItem(1, playerIn);
				}
				worldIn.playSound(playerIn,	playerIn.posX, playerIn.posY, playerIn.posZ, SoundHandler.GUN_RIFLE_SHOOT, SoundCategory.MASTER, 1, 1);
				
			}
		//}
		playerIn.addStat(StatList.getObjectUseStats(this));
		return new ActionResult(EnumActionResult.PASS, itemstack);
	}
	
	@Override
	public void registerModels() 
	{
		main.proxy.registerItemRender(this, 0, "inventory");
		
	}

}
