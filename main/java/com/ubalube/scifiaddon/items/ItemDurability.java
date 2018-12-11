package com.ubalube.scifiaddon.items;

import com.ubalube.scifiaddon.main;
import com.ubalube.scifiaddon.init.ModItems;
import com.ubalube.scifiaddon.util.IHasModel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;

public class ItemDurability extends Item implements IHasModel
{
	public ItemDurability(String name, int StackSize, CreativeTabs tab) 
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);
		setMaxStackSize(StackSize);
		
		setMaxDamage(100);
		
		ModItems.ITEMS.add(this);
	}
	
    public boolean doesContainerItemLeaveCraftingGrid(ItemStack stack) {
        return false;
    }
    
    //Tells the game your item has a container item
    public boolean hasContainerItem() {
    	return true;
    }
    
    //Sets teh container item
    public ItemStack getContainerItem(ItemStack itemStack) {
    	itemStack.setItemDamage(itemStack.getItemDamage() + 1);
    	
    	return itemStack;
    }
	
	@Override
	public void registerModels() 
	{
		main.proxy.registerItemRender(this, 0, "inventory");
		
	}
	
}
