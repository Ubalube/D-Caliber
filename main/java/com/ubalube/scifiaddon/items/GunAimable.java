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
import net.minecraft.util.CooldownTracker;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import scala.reflect.internal.Trees.Modifiers;

public class GunAimable extends GunBase implements IHasModel
{
	
	/**
	 * 
	 * @param name
	 * @param tab
	 * @param fireRate
	 * @param ammocap
	 * @param reloadtm
	 * @param recoil
	 * @param bulletDamage
	 * @param bulletDuration
	 * @param ammunition
	 * @param guntype
	 * @param desc
	 * @param ammoN
	 * @param strength
	 */
	public GunAimable(String name, CreativeTabs tab, int fireRate, int ammocap, int reloadtm, int recoil, float bulletDamage, int bulletDuration, Item ammunition, int guntype, String desc, String ammoN, int strength) 
	{
		//String name, int fireRate, int ammocap, int reloadtm, int recoil, float bulletDamage, int bulletDuration, Item ammunition, int guntype
		super(name, fireRate, ammocap, reloadtm, recoil, bulletDamage, bulletDuration, ammunition, guntype, desc, ammoN, strength);
		setCreativeTab(tab);
		setMaxStackSize(1);
		
		setMaxDamage(clipsize);
		
		
		
		ModItems.ITEMS.add(this);
	}
	
	@Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return false;
    }
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.BOW;
    }
	
	/*@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) 
	{
		
		ItemStack itemstack = playerIn.getHeldItemMainhand();
		if(!this.isRunning(itemstack))
		{
			this.shootGun(worldIn, playerIn, itemstack);
			if(itemstack.getItemDamage() != this.clipsize)
			{
				this.doRecoil(playerIn);
				this.playShootSound(playerIn);
			}
			//}
			playerIn.addStat(StatList.getObjectUseStats(this));
			this.checkStates(itemstack, worldIn, playerIn);
		}
		
		return new ActionResult(EnumActionResult.PASS, itemstack);
	}*/
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) 
	{
		EntityPlayer p = (EntityPlayer) entityIn;
		NBTTagCompound nbt = stack.getTagCompound();
		this.checkStates(stack, worldIn, entityIn);
		/*if(p.isSprinting())
		{
			this.checkStates(stack, worldIn, entityIn);
		}
		else
		{
			if(!p.isSprinting() && nbt.getBoolean("running") == true)
			{
				nbt.setBoolean("running", false);
			}
		}*/
		
		
		super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
	}
	
	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player) {
		if(this.isAiming(itemstack))
		{
			return false;
		}
		return super.onBlockStartBreak(itemstack, pos, player);
	}
	
	/*@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) 
	{
		EntityPlayer playerIn = (EntityPlayer) entityLiving;

		this.doAim(stack);
		
		//this.checkStates(stack, worldIn, entityLiving);
		return true;
	}*/
	
	@Override
	public void registerModels() 
	{
		main.proxy.registerItemRender(this, 0, "inventory");
		
	}

}
