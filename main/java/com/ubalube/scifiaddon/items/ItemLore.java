package com.ubalube.scifiaddon.items;

import java.util.List;

import org.lwjgl.input.Keyboard;

import com.ubalube.scifiaddon.main;
import com.ubalube.scifiaddon.init.ModItems;
import com.ubalube.scifiaddon.util.IHasModel;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemLore extends Item implements IHasModel
{
	
	TextFormatting Lorecolor;
	String lore;
	
	public ItemLore(String name, int StackSize, CreativeTabs tab, String Lore, TextFormatting color) 
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);
		setMaxStackSize(StackSize);
		
		this.lore = Lore;
		this.Lorecolor = color;
		
		ModItems.ITEMS.add(this);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) 
	{
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
		{
			tooltip.add(Lorecolor + this.lore);
		}
		else
		{
			tooltip.add(TextFormatting.YELLOW + "Press LSHIFT for stats!");
		}
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public void registerModels() 
	{
		main.proxy.registerItemRender(this, 0, "inventory");
		
	}
	
}
