package com.ubalube.scifiaddon.items.medical;

import com.ubalube.scifiaddon.main;
import com.ubalube.scifiaddon.init.ModItems;
import com.ubalube.scifiaddon.util.IHasModel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class Boosters extends Item implements IHasModel
{
	
	int HealthFix;
	int Cooldown;
	int Multiplier;
	
	public Boosters(String name, int StackSize, CreativeTabs tab, int health, int cooldown, int mult) 
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);
		setMaxStackSize(StackSize);
		
		Cooldown = cooldown;
		HealthFix = health;
		Multiplier = mult;
		
		ModItems.ITEMS.add(this);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) 
	{
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		
		playerIn.getCooldownTracker().setCooldown(this, Cooldown);
		if(!worldIn.isRemote)
		{
			if(playerIn.getHealth() != playerIn.getMaxHealth())
			{
				playerIn.addPotionEffect(new PotionEffect(MobEffects.SPEED, Multiplier, 1));
				playerIn.setHealth(playerIn.getHealth() + HealthFix);
			}
			
		}
		return new ActionResult(EnumActionResult.SUCCESS, itemstack);
	}
	
	@Override
	public void registerModels() 
	{
		main.proxy.registerItemRender(this, 0, "inventory");
		
	}
	
}
