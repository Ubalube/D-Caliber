package com.ubalube.scifiaddon.items;

import com.google.common.collect.Multimap;
import com.ubalube.scifiaddon.main;
import com.ubalube.scifiaddon.init.ModItems;
import com.ubalube.scifiaddon.util.IHasModel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class ItemMelee extends Item implements IHasModel
{
	
	private float attackDamage;
	
	public ItemMelee(String name, int StackSize, CreativeTabs tab, float attackDamage) 
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);
		setMaxStackSize(StackSize);
		this.attackDamage = attackDamage;
		this.setMaxDamage(155);
		ModItems.ITEMS.add(this);
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        stack.damageItem(1, attacker);
        return true;
	}
	
	@Override
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot) {
		Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
        {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double)this.attackDamage, 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -1D, 0));
        }

        return multimap;
	}
	
	
	
	@Override
	public boolean isEnchantable(ItemStack stack) {
		return true;
	}
	
	@Override
	public int getItemEnchantability() {
		return 32;
	}
	
	@Override
	public void registerModels() 
	{
		main.proxy.registerItemRender(this, 0, "inventory");
		
	}
	
}
