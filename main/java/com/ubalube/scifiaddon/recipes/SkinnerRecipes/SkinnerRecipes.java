package com.ubalube.scifiaddon.recipes.SkinnerRecipes;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import com.ubalube.scifiaddon.init.ModItems;
import com.ubalube.scifiaddon.items.CGunSkinnable;
import com.ubalube.scifiaddon.items.GunAimableSkin;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class SkinnerRecipes 
{
	private static final SkinnerRecipes INSTANCE = new SkinnerRecipes();
	private final Table<ItemStack, ItemStack, ItemStack> smeltingList = HashBasedTable.<ItemStack, ItemStack, ItemStack>create();
	private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();
	
	public static SkinnerRecipes getInstance()
	{
		return INSTANCE;
	}
	
	private SkinnerRecipes() 
	{
		//Input 1 = Gun 
		//Input 2 = Paint
		
		/*
		 Skins
		 1 = Redstone
		 2 = Lightning
		 3 = Volcanic
		 4 = Fade
		 5 = Desert
		 6 = Forest
		 7 = Blue
		 8 = Red
		 */
		
		//AK12
		addHarvestorRecipe(new ItemStack(ModItems.SCAR), new ItemStack(ModItems.RED_PAINT), new ItemStack(ModItems.SCAR), 5.0F, 1);
		addHarvestorRecipe(new ItemStack(ModItems.HK416), new ItemStack(ModItems.ORANGE_PAINT), new ItemStack(ModItems.HK416), 5.0F, 1);
		addHarvestorRecipe(new ItemStack(ModItems.SCARACOG), new ItemStack(ModItems.BLUE_PAINT), new ItemStack(ModItems.SCARACOG), 5.0F, 1);
		addHarvestorRecipe(new ItemStack(ModItems.AKM), new ItemStack(ModItems.GREEN_PAINT), new ItemStack(ModItems.AKM), 5.0F, 1);
	}

	
	public void addHarvestorRecipe(ItemStack input1, ItemStack input2, ItemStack result, float experience, int skinId) 
	{
		if(getHarvestorResult(input1, input2) != ItemStack.EMPTY) return;
		this.smeltingList.put(input1, input2, result);
		this.experienceList.put(result, Float.valueOf(experience));
		
		NBTTagCompound nbt = result.getTagCompound();
		
		if(result.getItem() instanceof GunAimableSkin)
		{
			if (nbt == null) {
	            nbt = new NBTTagCompound();
	            result.setTagCompound(nbt);
	        }
			nbt.setInteger("SKIN", skinId);
			nbt.setBoolean("reload", false);
		}
		
	}
	
	public ItemStack getHarvestorResult(ItemStack input1, ItemStack input2) 
	{
		for(Entry<ItemStack, Map<ItemStack, ItemStack>> entry : this.smeltingList.columnMap().entrySet()) 
		{
			if(this.compareItemStacks(input1, (ItemStack)entry.getKey())) 
			{
				for(Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet()) 
				{
					if(this.compareItemStacks(input2, (ItemStack)ent.getKey())) 
					{
						return (ItemStack)ent.getValue();
					}
				}
			}
		}
		return ItemStack.EMPTY;
	}
	
	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
	{
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
	}
	
	public Table<ItemStack, ItemStack, ItemStack> getDualSmeltingList() 
	{
		return this.smeltingList;
	}
	
	public float getHarvestorExperience(ItemStack stack)
	{
		for (Entry<ItemStack, Float> entry : this.experienceList.entrySet()) 
		{
			if(this.compareItemStacks(stack, (ItemStack)entry.getKey())) 
			{
				return ((Float)entry.getValue()).floatValue();
			}
		}
		return 0.0F;
	}
}
