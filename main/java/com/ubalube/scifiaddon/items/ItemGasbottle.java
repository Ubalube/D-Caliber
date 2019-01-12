package com.ubalube.scifiaddon.items;

import java.util.List;

import javax.annotation.Nullable;

import org.lwjgl.input.Keyboard;

import com.mojang.realmsclient.gui.ChatFormatting;
import com.ubalube.scifiaddon.main;
import com.ubalube.scifiaddon.init.ModItems;
import com.ubalube.scifiaddon.items.CGrenade.type;
import com.ubalube.scifiaddon.util.IHasModel;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemGasbottle extends Item implements IHasModel
{
	public ItemGasbottle(String name, int StackSize, CreativeTabs tab) 
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		
		setMaxStackSize(StackSize);
		
		setCreativeTab(tab);
		
		ModItems.ITEMS.add(this);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) 
	{
		
		String gasname = null;
		/*
		 * types:
		 * 1 - Creeper Gas
		 */
		if(stack.getItem() == ModItems.GASBOTTLE)
		{
			gasname = "<???>";
		}
		
		if(stack.getItem() == ModItems.GASBOTTLE_C)
		{
			gasname = "Creeper Gas";
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
		{
			tooltip.add("Gas Type:");
			tooltip.add(TextFormatting.GREEN + "[" + TextFormatting.YELLOW + gasname + TextFormatting.GREEN + "]");
		}
		else
		{
			tooltip.add(TextFormatting.YELLOW + "Gas Properties <LSHIFT>");
		}
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public void registerModels() 
	{
		main.proxy.registerItemRender(this, 0, "inventory");
		
	}
	
}
