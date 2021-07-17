package com.ubalube.scifiaddon.enchantment;

import com.ubalube.scifiaddon.items.GunBase;
import com.ubalube.scifiaddon.util.Reference;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

public class EnchantmentJohnWickReload extends Enchantment
{

	public EnchantmentJohnWickReload() {
		super(Rarity.RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[] { EntityEquipmentSlot.MAINHAND });
		setRegistryName(Reference.MOD_ID, "john_wick_reload");
		setName("john_wick_reloading");
	}
	
	@Override
	public int getMinEnchantability(int enchantmentLevel) {
		return enchantmentLevel * 10;
	}
	
	@Override
	public int getMaxEnchantability(int enchantmentLevel) {
		return this.getMinEnchantability(enchantmentLevel) + 15;
	}
	
	@Override
	public int getMaxLevel() {
		return 1;
	}
	
	@Override
	protected boolean canApplyTogether(Enchantment ench) {
		return false;
	}
	
	@Override
	public boolean canApply(ItemStack stack) {
		return stack.getItem() instanceof GunBase;
	}
	
	@Override
	public boolean isTreasureEnchantment() {
		return true;
	}
	
}
