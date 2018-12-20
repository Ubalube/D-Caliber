package com.ubalube.scifiaddon.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes 
{
	public static void init() 
	{
		GameRegistry.addSmelting(ModItems.CARBONMIXTURE, new ItemStack(ModItems.STEEL, 1), 5.0F);
	}
}
