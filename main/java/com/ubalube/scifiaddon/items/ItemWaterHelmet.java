package com.ubalube.scifiaddon.items;

import com.ubalube.scifiaddon.main;
import com.ubalube.scifiaddon.armor.ArmorBaseSkin;
import com.ubalube.scifiaddon.entity.model.ModelNvGoggles;
import com.ubalube.scifiaddon.init.ModItems;
import com.ubalube.scifiaddon.util.IHasModel;
import com.ubalube.scifiaddon.util.Reference;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemWaterHelmet extends ArmorBaseSkin
{
	
	public ItemWaterHelmet(String name, String pathName, ArmorMaterial material, int renderIndex, EntityEquipmentSlot equipmentSlot) {
		super(name, pathName, material, renderIndex, equipmentSlot);
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		player.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 1, 1));
		super.onArmorTick(world, player, itemStack);
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, net.minecraft.enchantment.Enchantment enchantment)
    {
        return true;
    }
}
