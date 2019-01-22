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
import net.minecraft.client.Minecraft;
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
import scala.util.Random;

public class GunAimableSkin extends GunBase implements IHasModel
{
	
	public GunAimableSkin(String name, CreativeTabs tab, int fireRate, int ammocap, int reloadtm, int recoil, float bulletDamage, int bulletDuration, Item ammunition, int guntype, String desc) 
	{
		//String name, int fireRate, int ammocap, int reloadtm, int recoil, float bulletDamage, int bulletDuration, Item ammunition, int guntype
		super(name, fireRate, ammocap, reloadtm, recoil, bulletDamage, bulletDuration, ammunition, guntype, desc);
		setCreativeTab(tab);
		setMaxStackSize(1);
		setMaxDamage(clipsize);
		
		this.addPropertyOverride(new ResourceLocation("skin"), new IItemPropertyGetter()
        {
			@SideOnly(Side.CLIENT)
	        public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
	        {
	            NBTTagCompound nbt = checkNBTTags(stack);
	            float j = nbt.getInteger("SKIN");
	            return j; 
	        }
        });
		
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
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) 
	{
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		this.shootGun(worldIn, playerIn, handIn, itemstack);
		//}
		playerIn.addStat(StatList.getObjectUseStats(this));
		this.checkStates(itemstack, worldIn, playerIn);
		return new ActionResult(EnumActionResult.PASS, itemstack);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) 
	{
		EntityPlayer p = (EntityPlayer) entityIn;
		NBTTagCompound nbt = stack.getTagCompound();
		if(p.isSprinting())
		{
			this.checkStates(stack, worldIn, entityIn);
		}
		else
		{
			if(!p.isSprinting() && nbt.getBoolean("running") == true)
			{
				nbt.setBoolean("running", false);
			}
		}
		
		
		super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
	}
	
	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) 
	{
		this.doAim(stack);
		World worldIn = entityLiving.world;
		this.checkStates(stack, worldIn, entityLiving);
		return true;
	}
	
	@Override
	public void registerModels() 
	{
		main.proxy.registerItemRender(this, 0, "inventory");
		
	}

}
