package com.ubalube.scifiaddon.items;

import java.util.List;

import org.apache.http.util.TextUtils;

import com.ubalube.scifiaddon.main;
import com.ubalube.scifiaddon.entity.EntityBullet;
import com.ubalube.scifiaddon.init.ModItems;
import com.ubalube.scifiaddon.util.IHasModel;
import com.ubalube.scifiaddon.util.handlers.SoundHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSubtitleOverlay.Subtitle;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class KrissVector extends Item implements IHasModel
{
	
	int Firerate = 8;
	int clipsize = 15;
	int kick = 5;
	int ReloadTime = 20;
	
	
	public KrissVector(String name, CreativeTabs tab) 
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);
		setMaxStackSize(1);
		setMaxDamage(clipsize + 1);
		
		ModItems.ITEMS.add(this);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) 
	{
		tooltip.add(TextFormatting.YELLOW + "Impact: " + TextFormatting.GREEN + ":::" + TextFormatting.RED + ":::::::");
		tooltip.add(TextFormatting.YELLOW + "Range:  " + TextFormatting.GREEN + ":::::" + TextFormatting.RED + ":::::");
		tooltip.add(TextFormatting.YELLOW + "Clipsize: " + TextFormatting.GREEN + "15");
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public boolean isEnchantable(ItemStack stack) {
		return false;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) 
	{
		
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		
		if (!playerIn.capabilities.isCreativeMode)
		{
			if(itemstack.isItemDamaged())
			{
				if(itemstack.getItemDamage() >= clipsize)
				{
					if(playerIn.inventory.hasItemStack(new ItemStack(ModItems.VECTORAMMO)))
					{
						EntityBullet entity = new EntityBullet(worldIn, playerIn, 2.0F, 50);
						itemstack.setItemDamage(-clipsize - 1);
						playerIn.inventory.clearMatchingItems(ModItems.VECTORAMMO, 0, 1, null);
						playerIn.getCooldownTracker().setCooldown(this, ReloadTime);
						worldIn.playSound(playerIn,	playerIn.posX, playerIn.posY, playerIn.posZ, SoundHandler.RELOAD_RIFLERELOAD, SoundCategory.MASTER, 1, 1);
					}
				}
				else
				{
					playerIn.getCooldownTracker().setCooldown(this, Firerate);
					if (!worldIn.isRemote)
					{
						EntityBullet entity = new EntityBullet(worldIn, playerIn, 3.0F, 100);
						entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 0.0F);
						worldIn.spawnEntity(entity);
						itemstack.damageItem(1, playerIn);
						
					}
					worldIn.playSound(playerIn,	playerIn.posX, playerIn.posY, playerIn.posZ, SoundHandler.GUN_PISTOL_SHOOT, SoundCategory.MASTER, 1, 1);
				}
				
			}
			else
			{
				//First Bullet
				playerIn.getCooldownTracker().setCooldown(this, Firerate);
				if(!worldIn.isRemote)
				{
					EntityBullet entity = new EntityBullet(worldIn, playerIn, 3.0F, 100);
					entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 0.0F);
					worldIn.spawnEntity(entity);
					itemstack.damageItem(1, playerIn);
					
				}
				worldIn.playSound(playerIn,	playerIn.posX, playerIn.posY, playerIn.posZ, SoundHandler.GUN_PISTOL_SHOOT, SoundCategory.MASTER, 1, 1);
				
			}
		}
		else
		{
			//Creative Move
			playerIn.getCooldownTracker().setCooldown(this, Firerate);
			if(!worldIn.isRemote)
			{
				EntityBullet entity = new EntityBullet(worldIn, playerIn, 3.0F, 100);
				entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 0.0F);
				worldIn.spawnEntity(entity);
				itemstack.damageItem(1, playerIn);
				
			}
			worldIn.playSound(playerIn,	playerIn.posX, playerIn.posY, playerIn.posZ, SoundHandler.GUN_PISTOL_SHOOT, SoundCategory.MASTER, 1, 1);
			
		}
		
		
		
		return new ActionResult(EnumActionResult.PASS, itemstack);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
		
		int SingleFire = 8;
		int Automatic = 2;
		
		World worldIn = ((EntityPlayer)entityLiving).getEntityWorld();
		
		worldIn.playSound(((EntityPlayer)entityLiving),	((EntityPlayer)entityLiving).posX, ((EntityPlayer)entityLiving).posY, ((EntityPlayer)entityLiving).posZ, SoundHandler.RELOAD_FIREMODE, SoundCategory.MASTER, 1, 1);
		
		if(entityLiving instanceof EntityPlayer)
		{
			if(!entityLiving.world.isRemote)
			{
				
				if(Firerate == SingleFire)
				{
					((EntityPlayer)entityLiving).sendMessage(new TextComponentString("Firemode Set to " + TextFormatting.YELLOW + "AUTOMATIC"));
					Firerate = Automatic;
				}
				else
				{
					((EntityPlayer)entityLiving).sendMessage(new TextComponentString("Firemode Set to " + TextFormatting.YELLOW + "Semi-Automatic"));
					Firerate = SingleFire;
				}
			}
		}
		return super.onEntitySwing(entityLiving, stack);
	}
	
	@Override
	public void registerModels() 
	{
		main.proxy.registerItemRender(this, 0, "inventory");
		
	}

}
