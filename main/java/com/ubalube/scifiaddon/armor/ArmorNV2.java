package com.ubalube.scifiaddon.armor;

import org.lwjgl.opengl.GL11;

import com.ubalube.scifiaddon.main;
import com.ubalube.scifiaddon.entity.model.ModelChromaticHelm;
import com.ubalube.scifiaddon.entity.model.ModelNVG;
import com.ubalube.scifiaddon.entity.model.ModelNvGoggles;
import com.ubalube.scifiaddon.init.ModItems;
import com.ubalube.scifiaddon.util.IHasModel;
import com.ubalube.scifiaddon.util.Overlay;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ArmorNV2 extends ItemArmor implements IHasModel{


	public ArmorNV2(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super(materialIn, renderIndexIn, equipmentSlotIn);		
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(main.armorTab);
		ModItems.ITEMS.add(this);
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 1, 1));
		super.onArmorTick(world, player, itemStack);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
		if(!itemStack.isEmpty()) {
			if(itemStack.getItem() instanceof ItemArmor) {
				
				ModelNVG armorModel = new ModelNVG(1.0f);
				
				
				
				armorModel.bipedHead.showModel = armorSlot == EntityEquipmentSlot.HEAD;
				armorModel.bipedHeadwear.showModel = armorSlot == EntityEquipmentSlot.HEAD;

				armorModel.isSneak = _default.isSneak;
				armorModel.isRiding = _default.isRiding;
				armorModel.isChild = _default.isChild;
				armorModel.rightArmPose = _default.rightArmPose;
				armorModel.leftArmPose = _default.leftArmPose;
				return armorModel;
			}
		}
		
		return null;
	}
	
	@Override
	public void registerModels() {
		
		main.proxy.registerItemRender(this, 0, "inventory");
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, net.minecraft.enchantment.Enchantment enchantment)
    {
        return true;
    }
}
