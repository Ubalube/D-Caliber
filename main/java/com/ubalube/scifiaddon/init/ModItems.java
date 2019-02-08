package com.ubalube.scifiaddon.init;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.ubalube.scifiaddon.items.medical.Boosters;
import com.ubalube.scifiaddon.items.medical.MedicalKit;
import com.ubalube.scifiaddon.items.music.RecordItem;
import com.ubalube.scifiaddon.main;
import com.ubalube.scifiaddon.armor.Armor3D;
import com.ubalube.scifiaddon.armor.ArmorBaseSkin;
import com.ubalube.scifiaddon.armor.ArmorBaseSkinNV;
import com.ubalube.scifiaddon.armor.ArmorNV;
import com.ubalube.scifiaddon.items.CGrenade;
import com.ubalube.scifiaddon.items.CGrenade.type;
import com.ubalube.scifiaddon.items.CGunAimingBase;
import com.ubalube.scifiaddon.items.CGunBase;
import com.ubalube.scifiaddon.items.CGunCrossbow;
import com.ubalube.scifiaddon.items.CGunGrenadeLauncher;
import com.ubalube.scifiaddon.items.CGunHelper;
import com.ubalube.scifiaddon.items.CGunPDW;
import com.ubalube.scifiaddon.items.CGunPistol;
import com.ubalube.scifiaddon.items.CGunSkinnable;
import com.ubalube.scifiaddon.items.CGunSniper;
import com.ubalube.scifiaddon.items.CNode;
import com.ubalube.scifiaddon.items.CRangefinder;
import com.ubalube.scifiaddon.items.GunAimable;
import com.ubalube.scifiaddon.items.GunAimableSkin;
import com.ubalube.scifiaddon.items.ItemBase;
import com.ubalube.scifiaddon.items.ItemBlitzShield;
import com.ubalube.scifiaddon.items.ItemBounty;
import com.ubalube.scifiaddon.items.ItemDurability;
import com.ubalube.scifiaddon.items.ItemLore;
import com.ubalube.scifiaddon.items.ItemPaint;
import com.ubalube.scifiaddon.items.TraderCase;
import com.ubalube.scifiaddon.util.Reference;
import com.ubalube.scifiaddon.util.handlers.SoundHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockPumpkin;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.gen.structure.StructureVillagePieces.Church;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.RenderBlockOverlayEvent.OverlayType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.common.util.EnumHelper;

@Mod.EventBusSubscriber(modid = "uc")
public class ModItems 
{
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	public static final ArmorMaterial ARMOR_MATERIAL_RANGER = EnumHelper.addArmorMaterial("armor_material_ranger", Reference.MOD_ID + ":ranger_model", 20, new int[] { 2, 5, 8, 4 }, 0, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0f);
	public static final ArmorMaterial ARMOR_MATERIAL_SPECOPS = EnumHelper.addArmorMaterial("armor_material_specops", Reference.MOD_ID + ":specop", 20, new int[] { 1, 4, 5, 8 }, 0, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0f);
	public static final ArmorMaterial SEAL_SUIT = EnumHelper.addArmorMaterial("seal", "caliber:seal", 40, new int[]{2, 10, 12, 6}, 34, SoundEvents.ITEM_ARMOR_EQIIP_ELYTRA, 3.0f);
	public static final ArmorMaterial GIGN_SUIT = EnumHelper.addArmorMaterial("gign", "caliber:gign", 40, new int[]{2, 10, 12, 6}, 34, SoundEvents.ITEM_ARMOR_EQIIP_ELYTRA, 3.0f);
	public static final ArmorMaterial GHILLIE_SUIT = EnumHelper.addArmorMaterial("ghillie", "caliber:ghillie", 40, new int[]{2, 10, 12, 6}, 34, SoundEvents.ITEM_ARMOR_EQIIP_ELYTRA, 3.0f);
	public static final ArmorMaterial COMBAT_SUIT = EnumHelper.addArmorMaterial("combat", "caliber:combat", 40, new int[]{2, 10, 12, 6}, 34, SoundEvents.ITEM_ARMOR_EQIIP_ELYTRA, 3.0f);
	public static final ArmorMaterial VECTOR_SUIT = EnumHelper.addArmorMaterial("vector", "caliber:vector", 40, new int[]{2, 10, 12, 6}, 34, SoundEvents.ITEM_ARMOR_EQIIP_ELYTRA, 3.0f);
	
	public static final ArmorMaterial CHROM_SUIT = EnumHelper.addArmorMaterial("chrom", "caliber:chrom", 40, new int[]{2, 10, 12, 6}, 34, SoundEvents.ITEM_ARMOR_EQIIP_ELYTRA, 3.0f);
	public static final ArmorMaterial NV = EnumHelper.addArmorMaterial("nightvision", "caliber:nightvision", 40, new int[]{2, 10, 12, 6}, 34, SoundEvents.ITEM_ARMOR_EQIIP_ELYTRA, 3.0f);
	public static final ArmorMaterial CHROMB_SUIT = EnumHelper.addArmorMaterial("chrombody", "caliber:chrombody", 40, new int[]{2, 10, 12, 6}, 34, SoundEvents.ITEM_ARMOR_EQIIP_ELYTRA, 3.0f);
	
	public static final Item BLITZSHIELD = new ItemBlitzShield("blitzshield", 1000, main.gunTab);
	
	//Objects
	public static final Item SUPPLIES1 = new ItemBase("supplies1", 64, main.objectTab);
	public static final Item SUPPLIES2 = new ItemBase("supplies2", 64, main.objectTab);
	
	public static final Item M16AMMO = new ItemBase("m16mag", 64, main.gunTab);
	
	//Paints
	public static final Item BLUE_PAINT = new ItemPaint("bluepaint", 10, main.objectTab, "Bright Blue", TextFormatting.BLUE);
	public static final Item RED_PAINT = new ItemPaint("redpaint", 10, main.objectTab, "Bright Red", TextFormatting.RED);
	public static final Item GREEN_PAINT = new ItemPaint("greenpaint", 10, main.objectTab, "Bright Green", TextFormatting.RED);
	public static final Item ORANGE_PAINT = new ItemPaint("orangepaint", 10, main.objectTab, "Orange", TextFormatting.RED);
	public static final Item DEFAULT_PAINT = new ItemPaint("defaultpaint", 10, main.objectTab, "Default Skin", TextFormatting.WHITE); 
	
	//public static final Item CASE = new TraderCase("case", 1, main.objectTab);
	
	//Medals
	public static final Item TANKMEDAL = new ItemLore("tankmedal", 1, main.objectTab, "You defeated the Goliath", TextFormatting.YELLOW);
	public static final Item ICON = new ItemBase("icon", 1, main.objectTab);
	
	//Materials
	public static final Item CARBONFIBRE = new ItemBase("carbonfibre", 64, main.partTab);
	public static final Item STEEL = new ItemBase("steel", 64, main.partTab);
	public static final Item COPPER = new ItemBase("copper", 64, main.partTab);
	public static final Item LENSE = new ItemBase("lense", 64, main.partTab);
	public static final Item CARBONMIXTURE = new ItemBase("carbonmixture", 64, main.partTab);
	
	//Music
	//Music Discs
	public static RecordItem HOSTILE_LANDS = new RecordItem("hostile_lands", ModMusic.HOSTILE_LANDS);
	public static RecordItem CRYSIS = new RecordItem("crysis", ModMusic.CRYSIS);
	
	//Medical
	public static final Item PILLS = new Boosters("pills", 3, main.objectTab, 2, 50, 2400);
	public static final Item MEDKIT = new MedicalKit("medkit", 3, main.objectTab, 10, 100);
	
	//Objects
	public static final Item NODE = new CNode("node", main.objectTab);
	
	//Armor
	public static final Item RANGER_HELMET = new ArmorBaseSkin("helmet_ranger", "ranger_model_", ARMOR_MATERIAL_RANGER, 1, EntityEquipmentSlot.HEAD);
	public static final Item RANGER_CHEST = new ArmorBaseSkin("chest_ranger", "ranger_model_", ARMOR_MATERIAL_RANGER, 1, EntityEquipmentSlot.CHEST);
	public static final Item RANGER_PANTS = new ArmorBaseSkin("pants_ranger", "ranger_model_", ARMOR_MATERIAL_RANGER, 1, EntityEquipmentSlot.FEET);
	
	public static final Item SPEC_HELMET = new ArmorBaseSkinNV("helmet_spec", "specop_", ARMOR_MATERIAL_SPECOPS, 1, EntityEquipmentSlot.HEAD);
	public static final Item SPEC = new ArmorBaseSkin("chest_spec", "specop_", ARMOR_MATERIAL_SPECOPS, 1, EntityEquipmentSlot.CHEST);
	public static final Item SPEC_PANTS = new ArmorBaseSkin("pants_spec", "specop_", ARMOR_MATERIAL_SPECOPS, 1, EntityEquipmentSlot.FEET);
	
	public static final Item SEAL_HELMET = new ArmorBaseSkin("helmet_seal", "seal_", SEAL_SUIT, 1, EntityEquipmentSlot.HEAD);
	public static final Item SEAL_CHEST = new ArmorBaseSkin("chest_seal", "seal_", SEAL_SUIT, 1, EntityEquipmentSlot.CHEST);
	public static final Item SEAL_PANTS = new ArmorBaseSkin("pants_seal", "seal_", SEAL_SUIT, 1, EntityEquipmentSlot.FEET);
	
	public static final Item GIGN_HELMET = new ArmorBaseSkin("helmet_gign", "gign_", GIGN_SUIT, 1, EntityEquipmentSlot.HEAD);
	public static final Item GIGN_CHEST = new ArmorBaseSkin("chest_gign", "gign_", GIGN_SUIT, 1, EntityEquipmentSlot.CHEST);
	public static final Item GIGN_PANTS = new ArmorBaseSkin("pants_gign", "gign_", GIGN_SUIT, 1, EntityEquipmentSlot.FEET);
	
	public static final Item VECTOR_HELMET = new ArmorBaseSkin("helmet_vector", "vector_", VECTOR_SUIT, 1, EntityEquipmentSlot.HEAD);
	public static final Item VECTOR_CHEST = new ArmorBaseSkin("chest_vector", "vector_", VECTOR_SUIT, 1, EntityEquipmentSlot.CHEST);
	public static final Item VECTOR_PANTS = new ArmorBaseSkin("pants_vector", "vector_", VECTOR_SUIT, 1, EntityEquipmentSlot.FEET);
	
	public static final Item COMBAT_HELMET = new ArmorBaseSkin("helmet_combat", "combat_", COMBAT_SUIT, 1, EntityEquipmentSlot.HEAD);
	public static final Item COMBAT_CHEST = new ArmorBaseSkin("chest_combat", "combat_", COMBAT_SUIT, 1, EntityEquipmentSlot.CHEST);
	public static final Item COMBAT_PANTS = new ArmorBaseSkin("pants_combat", "combat_", COMBAT_SUIT, 1, EntityEquipmentSlot.FEET);
	
	public static final Item GHILLIE_HELMET = new ArmorBaseSkin("helmet_ghillie", "ghillie_", GHILLIE_SUIT, 1, EntityEquipmentSlot.HEAD);
	public static final Item GHILLIE_CHEST = new ArmorBaseSkin("chest_ghillie", "ghillie_", GHILLIE_SUIT, 1, EntityEquipmentSlot.CHEST);
	public static final Item GHILLIE_PANTS = new ArmorBaseSkin("pants_ghillie", "ghillie_", GHILLIE_SUIT, 1, EntityEquipmentSlot.FEET);
	
	public static final Item CHROM_HELMET = new Armor3D("chromhelm", CHROM_SUIT, 1, EntityEquipmentSlot.HEAD);
	public static final Item CHROM_CHEST = new ArmorBaseSkin("chest_chrom", "chrombody_", GIGN_SUIT, 1, EntityEquipmentSlot.CHEST);
	public static final Item CHROM_PANTS = new ArmorBaseSkin("pants_chrom", "chrombody_", GIGN_SUIT, 1, EntityEquipmentSlot.FEET);
	
	public static final Item NVGOGGLES = new ArmorNV("nightvision", NV, 1, EntityEquipmentSlot.HEAD);
	
	//GUNS
	//Types: 
	//1 = Rifle | 2 = Pistol | 3 = Sniper
	//Format: Name, Creative Tab, Firerate, Clipsize, reload time, Automatic firerate, Single firerate, Bullet Damage, Bullet Range (Ticks), Ammo, Shootsound, reload sound
	public static final Item SCARACOG = new GunAimableSkin("scar_acog", main.gunTab, 8, 35, 50, 2, 2, 500, ModItems.M16AMMO, 1, "gun.cr4.desc");
	public static final Item SCAR = new GunAimableSkin("scar", main.gunTab, 8, 35, 50, 2, 2, 500, ModItems.M16AMMO, 1, "gun.cr4.desc");
	public static final Item GLOCK = new GunAimable("glock", main.gunTab, 12, 12, 50, 1, 1, 200, ModItems.M16AMMO, 1, "gun.cr4.desc");
	public static final Item AWP = new GunAimable("l69a1", main.gunTab, 10, 20, 100, 3, 4, 500, ModItems.M16AMMO, 1, "gun.cr4.desc");
	public static final Item SMG = new GunAimable("sting", main.gunTab, 8, 35, 50, 1, 2, 500, ModItems.M16AMMO, 1, "gun.cr4.desc");
	public static final Item TOMMYGUN = new GunAimable("thompson", main.gunTab, 8, 35, 50, 1, 1.5F, 500, ModItems.M16AMMO, 1, "gun.cr4.desc");
	public static final Item HK416 = new GunAimableSkin("hk", main.gunTab, 8, 35, 50, 1, 2.1F, 500, ModItems.M16AMMO, 1, "gun.cr4.desc");
	public static final Item AKM = new GunAimableSkin("akm", main.gunTab, 8, 35, 50, 5, 2.2F, 500, ModItems.M16AMMO, 1, "gun.cr4.desc");
	public static final Item CR4 = new GunAimable("cr4", main.gunTab, 8, 35, 50, 5, 2.4F, 500, ModItems.M16AMMO, 1, "gun.cr4.desc");
	public static final Item FAL = new GunAimableSkin("fal", main.gunTab, 8, 35, 50, 5, 2.3F, 500, ModItems.M16AMMO, 1, "gun.cr4.desc");
	public static final Item UZI = new GunAimableSkin("uzi", main.gunTab, 8, 35, 50, 5, 1.4F, 500, ModItems.M16AMMO, 1, "gun.cr4.desc");
	public static final Item G36 = new GunAimable("g36", main.gunTab, 8, 35, 50, 2, 2.4F, 500, ModItems.M16AMMO, 1, "gun.cr4.desc");
	public static final Item G36C = new GunAimable("g36c", main.gunTab, 16, 35, 50, 2, 2.4F, 500, ModItems.M16AMMO, 1, "gun.cr4.desc");
	
	public static final Item BOUNTY = new ItemBounty("bounty", 1, main.objectTab);
}

