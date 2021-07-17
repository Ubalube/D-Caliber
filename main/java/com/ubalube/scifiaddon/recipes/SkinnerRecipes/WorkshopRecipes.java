package com.ubalube.scifiaddon.recipes.SkinnerRecipes;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import com.ubalube.scifiaddon.init.ModItems;
import com.ubalube.scifiaddon.items.GunAttachments;
import com.ubalube.scifiaddon.items.GunBase;
import com.ubalube.scifiaddon.items.ItemModification;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.RecipesBanners.RecipeAddPattern;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class WorkshopRecipes 
{
	private static final WorkshopRecipes INSTANCE = new WorkshopRecipes();
	private final Table<ItemStack, ItemStack, ItemStack> smeltingList = HashBasedTable.<ItemStack, ItemStack, ItemStack>create();
	private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();
	
	public static WorkshopRecipes getInstance()
	{
		return INSTANCE;
	}
	
	private WorkshopRecipes() 
	{
		addHarvestorRecipe(new ItemStack(Items.IRON_AXE), new ItemStack(Items.REPEATER), new ItemStack(ModItems.STATTRACK), 5.0F);
		addHarvestorRecipe(new ItemStack(Items.IRON_AXE), new ItemStack(Blocks.WOOL), new ItemStack(ModItems.LOWRECOIL), 5.0F);
		addHarvestorRecipe(new ItemStack(Items.DIAMOND_AXE), new ItemStack(Items.IRON_SWORD), new ItemStack(ModItems.INCREASEDAMAGE), 5.0F);
		addHarvestorRecipe(new ItemStack(Items.GLASS_BOTTLE), new ItemStack(Items.BLAZE_ROD), new ItemStack(ModItems.BULLETEFFECT), 5.0F);
		
		//Reset
		for(Item i : ModItems.ITEMS)
		{
			if(i instanceof GunBase)
			{
				addModificationRecipe(new ItemStack(i), new ItemStack(ModItems.INCREASEDAMAGE), 2.0f);
				addModificationRecipe(new ItemStack(i), new ItemStack(ModItems.LOWRECOIL), 2.0f);
				addModificationRecipe(new ItemStack(i), new ItemStack(ModItems.STATTRACK), 2.0f);
				addModificationRecipe(new ItemStack(i), new ItemStack(ModItems.BULLETEFFECT), 2.0f);
			}
		}
		
	}

	
	public void addHarvestorRecipe(ItemStack input1, ItemStack input2, ItemStack result, float experience) 
	{
		if(getHarvestorResult(input1, input2) != ItemStack.EMPTY) return;
		this.smeltingList.put(input1, input2, result);
		this.experienceList.put(result, Float.valueOf(experience));

		
	}
	
	public void addModificationRecipe(ItemStack weapon, ItemStack modification,  float experience)
	{

		ItemStack result = weapon.copy();
		ItemModification mod = (ItemModification) modification.getItem();
		GunBase gun = (GunBase) result.getItem();
		if(getHarvestorResult(weapon, modification) != ItemStack.EMPTY) return;
		this.smeltingList.put(weapon, modification, result);
		this.experienceList.put(result, Float.valueOf(experience));
		if(mod.getModification() == GunAttachments.POTIONEFFECT)
		{
			if(mod.getPotionEffect() != null)
			{
				if(modification.getItem() instanceof ItemModification)
				{
					
					if(mod.getModification() == GunAttachments.POTIONEFFECT)
					{
						gun.addPotionEffect(mod.getPotionEffect().getPotion(), result);
					}
					
					gun.addModification(mod.getModification(), result);
				}
			}
		}
		else
		{
			if(modification.getItem() instanceof ItemModification)
			{

				if(mod.getModification() == GunAttachments.STATTRACK)
				{
					gun.addStatTrack(result);
				}
				gun.addModification(mod.getModification(), result);
				
			}
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
