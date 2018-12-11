package com.ubalube.scifiaddon.items;

import java.util.List;

import org.lwjgl.input.Keyboard;

import com.ubalube.scifiaddon.main;
import com.ubalube.scifiaddon.entity.EntityBullet;
import com.ubalube.scifiaddon.init.ModItems;
import com.ubalube.scifiaddon.util.IHasModel;
import com.ubalube.scifiaddon.util.handlers.SoundHandler;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class CGunPistol extends Item implements IHasModel
{
	
	int Firerate;
	int clipsize;
	int ReloadTime;
	int damage;
	int range;
	Item ammo;
	
	public CGunPistol(String name, CreativeTabs tab, int fireRate, int ammocap, int reloadtm, int bulletDamage, int bulletDuration, Item ammunition) 
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);
		setMaxStackSize(1);
		
		
		this.Firerate = fireRate;
		this.clipsize = ammocap;
		this.ReloadTime = reloadtm;
		this.damage = bulletDamage;
		this.range = bulletDuration;
		this.ammo = ammunition;
		
		setMaxDamage(clipsize);
		
		ModItems.ITEMS.add(this);
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
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) 
	{
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		{
			if (!playerIn.capabilities.isCreativeMode)
			{
				if(itemstack.isItemDamaged())
				{
					if(itemstack.getItemDamage() == clipsize)
					{
						if(playerIn.inventory.hasItemStack(new ItemStack(ammo)))
						{
							EntityBullet entity = new EntityBullet(worldIn, playerIn, damage, range);
							itemstack.setItemDamage(-clipsize);
							playerIn.inventory.clearMatchingItems(ammo, 0, 1, null);
							playerIn.getCooldownTracker().setCooldown(this, ReloadTime);
						}
					}
					else
					{
						playerIn.getCooldownTracker().setCooldown(this, this.Firerate);
						if (!worldIn.isRemote)
						{
							EntityBullet entity = new EntityBullet(worldIn, playerIn, damage, range);
							entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 1.0F, 1.5F, 0.0F);
							worldIn.spawnEntity(entity);
							itemstack.damageItem(1, playerIn);
							
						}
						worldIn.playSound(playerIn,	playerIn.posX, playerIn.posY, playerIn.posZ, SoundHandler.GUN_RIFLE_SHOOT, SoundCategory.MASTER, 1, 1);
					}
					
				}
				else
				{
					//First Bullet
					playerIn.getCooldownTracker().setCooldown(this, this.Firerate);
					if(!worldIn.isRemote)
					{
						EntityBullet entity = new EntityBullet(worldIn, playerIn, damage, range);
						entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 1.0F, 1.5F, 0.0F);
						worldIn.spawnEntity(entity);
						itemstack.damageItem(1, playerIn);
						
					}
					worldIn.playSound(playerIn,	playerIn.posX, playerIn.posY, playerIn.posZ, SoundHandler.GUN_RIFLE_SHOOT, SoundCategory.MASTER, 1, 1);
					
				}
			}
			
			else
			{
				
				//Creative Move
				playerIn.getCooldownTracker().setCooldown(this, this.Firerate);
				if(!worldIn.isRemote)
				{
					EntityBullet entity = new EntityBullet(worldIn, playerIn, damage, range);
					entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 1.0F, 1.5F, 0.0F);
					worldIn.spawnEntity(entity);
					itemstack.damageItem(1, playerIn);
				}
				worldIn.playSound(playerIn,	playerIn.posX, playerIn.posY, playerIn.posZ, SoundHandler.GUN_RIFLE_SHOOT, SoundCategory.MASTER, 1, 1);
				
			}
		}
		return new ActionResult(EnumActionResult.PASS, itemstack);
	}
	
	@Override
	public void registerModels() 
	{
		main.proxy.registerItemRender(this, 0, "inventory");
		
	}

}
