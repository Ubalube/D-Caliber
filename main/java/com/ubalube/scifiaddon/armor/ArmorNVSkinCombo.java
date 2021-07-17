package com.ubalube.scifiaddon.armor;

import org.lwjgl.opengl.GL11;

import com.ubalube.scifiaddon.main;
import com.ubalube.scifiaddon.entity.model.ModelChromaticHelm;
import com.ubalube.scifiaddon.entity.model.ModelNVG;
import com.ubalube.scifiaddon.entity.model.ModelNvGoggles;
import com.ubalube.scifiaddon.init.ModItems;
import com.ubalube.scifiaddon.util.IHasModel;
import com.ubalube.scifiaddon.util.Overlay;
import com.ubalube.scifiaddon.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.layers.LayerElytra;
import net.minecraft.entity.Entity;
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

public class ArmorNVSkinCombo extends ItemArmor implements IHasModel{


	private String armorTexturePath;
	private String skinType;

	public ArmorNVSkinCombo(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn, String pathName) {
		super(materialIn, renderIndexIn, equipmentSlotIn);		
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(main.armorTab);
		ModItems.ITEMS.add(this);
		
		this.armorTexturePath = Reference.MOD_ID + ":textures/models/armor/" + pathName;
        this.skinType = "default";
		
	}
	
	private void setPaths(final String input)
    {
        String[] str = input.split(":", 2);
        this.armorTexturePath = str[0] + ":" + "textures/models/armor/";

        str = str[1].split("");

        for (int i = 0; i < str.length; i++)
            this.armorTexturePath += str[i] + "_";

        this.skinType = "default";
    }
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {		 
		return this.armorTexturePath + this.skinType + "_layer_" + this.renderIndex + ".png";
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 200, 1));
		super.onArmorTick(world, player, itemStack);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
		if(itemStack != ItemStack.EMPTY && itemStack.getItem() instanceof ArmorNVSkinCombo) {
			ModelNVG armorModel = new ModelNVG(0.0f);
			
			armorModel.bipedHead.showModel = armorSlot == EntityEquipmentSlot.HEAD;
			armorModel.bipedBody.showModel = armorSlot == EntityEquipmentSlot.CHEST;
			armorModel.bipedLeftArm.showModel = armorSlot == EntityEquipmentSlot.CHEST;
			armorModel.bipedRightArm.showModel = armorSlot == EntityEquipmentSlot.CHEST;
			armorModel.bipedLeftLeg.showModel = armorSlot == EntityEquipmentSlot.LEGS;
			armorModel.bipedRightLeg.showModel = armorSlot == EntityEquipmentSlot.LEGS;
			
			armorModel.bipedHead.showModel = armorSlot == EntityEquipmentSlot.HEAD;
			armorModel.bipedHeadwear.showModel = armorSlot == EntityEquipmentSlot.HEAD;

			armorModel.isSneak = _default.isSneak;
			armorModel.isRiding = _default.isRiding;
			armorModel.isChild = _default.isChild;
			armorModel.rightArmPose = _default.rightArmPose;
			armorModel.leftArmPose = _default.leftArmPose;
			
		 	return armorModel;
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
