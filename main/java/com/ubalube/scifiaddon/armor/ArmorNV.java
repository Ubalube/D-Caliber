package com.ubalube.scifiaddon.armor;

import org.lwjgl.opengl.GL11;

import com.ubalube.scifiaddon.main;
import com.ubalube.scifiaddon.entity.model.ModelChromaticHelm;
import com.ubalube.scifiaddon.entity.model.ModelNvGoggles;
import com.ubalube.scifiaddon.init.ModItems;
import com.ubalube.scifiaddon.util.IHasModel;
import com.ubalube.scifiaddon.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ArmorNV extends ItemArmor implements IHasModel{

	public static final ResourceLocation LOCATION = new ResourceLocation(Reference.MOD_ID + ":textures/overlay/nightvision.png");

	public ArmorNV(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super(materialIn, renderIndexIn, equipmentSlotIn);		
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(main.armorTab);
		ModItems.ITEMS.add(this);
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 200, 1));
		super.onArmorTick(world, player, itemStack);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
		if(!itemStack.isEmpty()) {
			if(itemStack.getItem() instanceof ItemArmor) {
				
				ModelNvGoggles armorModel = new ModelNvGoggles(1.0f);
				
				
				
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
