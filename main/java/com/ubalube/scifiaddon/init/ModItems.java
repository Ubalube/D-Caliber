package com.ubalube.scifiaddon.init;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.ubalube.scifiaddon.items.medical.Boosters;
import com.ubalube.scifiaddon.items.medical.MedicalKit;
import com.ubalube.scifiaddon.items.music.RecordItem;
import com.google.common.graph.ElementOrder.Type;
import com.ubalube.scifiaddon.main;
import com.ubalube.scifiaddon.armor.Armor3D;
import com.ubalube.scifiaddon.armor.ArmorBaseSkin;
import com.ubalube.scifiaddon.armor.ArmorBaseSkinNV;
import com.ubalube.scifiaddon.armor.ArmorHazmat;
import com.ubalube.scifiaddon.armor.ArmorJuggernaut;
import com.ubalube.scifiaddon.armor.ArmorNV;
import com.ubalube.scifiaddon.armor.ArmorNV2;
import com.ubalube.scifiaddon.items.CGrenade;
import com.ubalube.scifiaddon.items.CGrenade.type;
import com.ubalube.scifiaddon.items.CNode;
import com.ubalube.scifiaddon.items.GuiItem;
import com.ubalube.scifiaddon.items.GunAimable;
import com.ubalube.scifiaddon.items.GunAimableSkin;
import com.ubalube.scifiaddon.items.GunAttachments;
import com.ubalube.scifiaddon.items.ItemBase;
import com.ubalube.scifiaddon.items.ItemBlitzShield;
import com.ubalube.scifiaddon.items.ItemDurability;
import com.ubalube.scifiaddon.items.ItemLore;
import com.ubalube.scifiaddon.items.ItemModification;
import com.ubalube.scifiaddon.items.ItemPaint;
import com.ubalube.scifiaddon.items.ItemWaterHelmet;
import com.ubalube.scifiaddon.items.TraderCase;
import com.ubalube.scifiaddon.util.Reference;
import com.ubalube.scifiaddon.util.handlers.SoundHandler;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockPumpkin;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiMainMenu;
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
import net.minecraftforge.event.entity.player.AdvancementEvent;
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
	public static final ArmorMaterial SEAL_SUIT = EnumHelper.addArmorMaterial("seal", "caliber:seal", 40, new int[]{2, 7, 10, 5}, 34, SoundEvents.ITEM_ARMOR_EQIIP_ELYTRA, 3.0f);
	public static final ArmorMaterial GIGN_SUIT = EnumHelper.addArmorMaterial("gign", "caliber:gign", 40, new int[]{2, 7, 8, 6}, 34, SoundEvents.ITEM_ARMOR_EQIIP_ELYTRA, 3.0f);
	public static final ArmorMaterial GHILLIE_SUIT = EnumHelper.addArmorMaterial("ghillie", "caliber:ghillie", 40, new int[]{2, 5, 4, 3}, 34, SoundEvents.ITEM_ARMOR_EQIIP_ELYTRA, 3.0f);
	public static final ArmorMaterial COMBAT_SUIT = EnumHelper.addArmorMaterial("combat", "caliber:combat", 40, new int[]{2, 5, 10, 6}, 34, SoundEvents.ITEM_ARMOR_EQIIP_ELYTRA, 3.0f);
	public static final ArmorMaterial VECTOR_SUIT = EnumHelper.addArmorMaterial("vector", "caliber:vector", 40, new int[]{2, 6, 7, 4}, 34, SoundEvents.ITEM_ARMOR_EQIIP_ELYTRA, 3.0f);
	public static final ArmorMaterial HAZMAT_SUIT = EnumHelper.addArmorMaterial("hazmat", "caliber:hazmat", 40, new int[]{1, 2, 3, 1}, 34, SoundEvents.ITEM_ARMOR_EQIIP_ELYTRA, 3.0f);
	public static final ArmorMaterial CAPTAIN_SUIT = EnumHelper.addArmorMaterial("captain", "caliber:captain", 40, new int[]{1, 2, 3, 1}, 34, SoundEvents.ITEM_ARMOR_EQIIP_ELYTRA, 3.0f);
	public static final ArmorMaterial MARINE_SUIT = EnumHelper.addArmorMaterial("marine", "caliber:marine", 40, new int[]{2, 8, 7, 4}, 34, SoundEvents.ITEM_ARMOR_EQIIP_ELYTRA, 3.0f);
	public static final ArmorMaterial JUGGERNAUT_SUIT = EnumHelper.addArmorMaterial("juggernaut", "caliber:juggernaut", 40, new int[]{5, 5, 5, 5}, 34, SoundEvents.ITEM_ARMOR_EQIIP_ELYTRA, 3.0f);
	
	public static final ArmorMaterial CHROM_SUIT = EnumHelper.addArmorMaterial("chrom", "caliber:chrom", 40, new int[]{2, 10, 12, 6}, 34, SoundEvents.ITEM_ARMOR_EQIIP_ELYTRA, 3.0f);
	public static final ArmorMaterial NV = EnumHelper.addArmorMaterial("nightvision", "caliber:nightvision", 40, new int[]{2, 10, 12, 6}, 34, SoundEvents.ITEM_ARMOR_EQIIP_ELYTRA, 3.0f);
	public static final ArmorMaterial NV1 = EnumHelper.addArmorMaterial("nightvision1", "caliber:nightvision1", 40, new int[]{2, 10, 12, 6}, 34, SoundEvents.ITEM_ARMOR_EQIIP_ELYTRA, 3.0f);
	public static final ArmorMaterial CHROMB_SUIT = EnumHelper.addArmorMaterial("chrombody", "caliber:chrombody", 40, new int[]{2, 10, 12, 6}, 34, SoundEvents.ITEM_ARMOR_EQIIP_ELYTRA, 3.0f);
	
	
	//Objects
	//public static final Item SUPPLIES1 = new ItemBase("supplies1", 64, main.objectTab);
	//public static final Item SUPPLIES2 = new ItemBase("supplies2", 64, main.objectTab);
	
	public static final Item RIFLE56 = new ItemBase("56mclip", 64, main.gunTab);
	public static final Item RIFLE762 = new ItemBase("762mclip", 64, main.gunTab);
	public static final Item SMG45 = new ItemBase("45acpclip", 64, main.gunTab);
	public static final Item PISTOL9mm = new ItemBase("9mmclip", 64, main.gunTab);
	public static final Item DMRCLIP = new ItemBase("dmrclip", 64, main.gunTab);
	public static final Item SNIPERCLIP = new ItemBase("sniperclip", 64, main.gunTab);
	
	//Paints
	public static final Item BLUE_PAINT = new ItemPaint("bluepaint", 10, main.objectTab, "Bright Blue", TextFormatting.BLUE);
	public static final Item RED_PAINT = new ItemPaint("redpaint", 10, main.objectTab, "Bright Red", TextFormatting.RED);
	public static final Item GREEN_PAINT = new ItemPaint("greenpaint", 10, main.objectTab, "Bright Green", TextFormatting.RED);
	public static final Item ORANGE_PAINT = new ItemPaint("orangepaint", 10, main.objectTab, "Orange", TextFormatting.RED);
	public static final Item DEFAULT_PAINT = new ItemPaint("defaultpaint", 10, main.objectTab, "Default Skin", TextFormatting.WHITE); 
	
	//Medals
	public static final Item TANKMEDAL = new ItemLore("tankmedal", 1, main.objectTab, "You defeated the Goliath", TextFormatting.YELLOW);
	public static final Item LEGIONMEDAL = new ItemLore("bossmedal", 1, main.objectTab, "You defeated the Legion General", TextFormatting.YELLOW);
	public static final Item ICON = new ItemBase("icon", 1, main.objectTab);
	
	public static final Item LoadoutEditor = new GuiItem("loadout", 1, main.objectTab);
	
	//Materials
	public static final Item CARBONFIBRE = new ItemBase("carbonfibre", 64, main.partTab);
	public static final Item STEEL = new ItemBase("steel", 64, main.partTab);
	public static final Item COPPER = new ItemBase("copper", 64, main.partTab);
	public static final Item LENSE = new ItemBase("lense", 64, main.partTab);
	public static final Item CARBONMIXTURE = new ItemBase("carbonmixture", 64, main.partTab);
	public static final Item HOLOGRAPHIC = new ItemBase("holo", 1, main.partTab);
	public static final Item ACOG = new ItemBase("acog", 1, main.partTab);
	public static final Item SNIPERSCOPE = new ItemBase("sniperscope", 1, main.partTab);
	public static final Item REDDOT = new ItemBase("reddot", 1, main.partTab);
	public static final Item AIMPOINT = new ItemBase("aimpoint", 1, main.partTab);
	public static final Item CALIBERCARBON = new ItemBase("caliber_carbon", 64, main.partTab);
	public static final Item CALIBERIRON = new ItemBase("caliber_iron", 64, main.partTab);
	public static final Item PISTOLCALIBERCARBON = new ItemBase("pistol_caliber_carbon", 64, main.partTab);
	public static final Item PISTOLCALIBERIRON = new ItemBase("pistol_caliber_iron", 64, main.partTab);
	public static final Item TACTICALSTOCK = new ItemBase("stock_tactical", 64, main.partTab);
	public static final Item UZISTOCK = new ItemBase("stock_uzi", 64, main.partTab);
	public static final Item COMPACTSTOCK = new ItemBase("stock_compact", 64, main.partTab);
	public static final Item WOODCOMPACTSTOCK = new ItemBase("stock_compact_wood", 64, main.partTab);
	
	//Music
	//Music Discs
	public static RecordItem HOSTILE_LANDS = new RecordItem("hostile_lands", ModMusic.HOSTILE_LANDS);
	public static RecordItem CRYSIS = new RecordItem("crysis", ModMusic.CRYSIS);
	
	//Medical
	public static final Item PILLS = new Boosters("pills", 3, main.objectTab, 2, 50, 2400);
	public static final Item MEDKIT = new MedicalKit("medkit", 3, main.objectTab, 10, 100);
	
	//Objects
	public static final Item NODE = new CNode("node", main.objectTab);
	
	public static final Item PADDING = new ItemBase("padding", 64, main.objectTab);
	public static final Item CLOTH = new ItemBase("cloth", 64,main.objectTab);
	
	//Modifications
	public static final Item STATTRACK = new ItemModification("stattrack", 16, main.partTab, GunAttachments.STATTRACK);
	public static final Item INCREASEDAMAGE = new ItemModification("increasedamage", 16, main.partTab, GunAttachments.INCREASEDAMAGE);
	public static final Item LOWRECOIL = new ItemModification("lowrecoil", 16, main.partTab, GunAttachments.LOWRECOIL);
	public static final Item BULLETEFFECT = new ItemModification("potioneffect", 16, main.partTab, GunAttachments.POTIONEFFECT);
	
	//Armor
	public static final Item RANGER_HELMET = new ArmorBaseSkin("helmet_ranger", "ranger_model_", ARMOR_MATERIAL_RANGER, 1, EntityEquipmentSlot.HEAD);
	public static final Item RANGER_CHEST = new ArmorBaseSkin("chest_ranger", "ranger_model_", ARMOR_MATERIAL_RANGER, 1, EntityEquipmentSlot.CHEST);
	public static final Item RANGER_PANTS = new ArmorBaseSkin("pants_ranger", "ranger_model_", ARMOR_MATERIAL_RANGER, 1, EntityEquipmentSlot.FEET);
	
	public static final Item SPEC_HELMET = new ArmorBaseSkinNV("helmet_spec", "specop_", ARMOR_MATERIAL_SPECOPS, 1, EntityEquipmentSlot.HEAD);
	public static final Item SPEC = new ArmorBaseSkin("chest_spec", "specop_", ARMOR_MATERIAL_SPECOPS, 1, EntityEquipmentSlot.CHEST);
	public static final Item SPEC_PANTS = new ArmorBaseSkin("pants_spec", "specop_", ARMOR_MATERIAL_SPECOPS, 1, EntityEquipmentSlot.FEET);
	
	public static final Item SEAL_HELMET = new ItemWaterHelmet("helmet_seal", "seal_", SEAL_SUIT, 1, EntityEquipmentSlot.HEAD);
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
	
	public static final Item HAZMAT_HELMET = new ArmorHazmat("helmet_hazmat", "hazmat_", HAZMAT_SUIT, 1, EntityEquipmentSlot.HEAD);
	public static final Item HAZMAT_CHEST = new ArmorHazmat("chest_hazmat", "hazmat_", HAZMAT_SUIT, 1, EntityEquipmentSlot.CHEST);
	public static final Item HAZMAT_PANTS = new ArmorHazmat("pants_hazmat", "hazmat_", HAZMAT_SUIT, 1, EntityEquipmentSlot.FEET);
	
	public static final Item CAPTAIN_HELMET = new ArmorBaseSkin("helmet_captain", "captain_", CAPTAIN_SUIT, 1, EntityEquipmentSlot.HEAD);
	public static final Item CAPTAIN_CHEST = new ArmorBaseSkin("chest_captain", "captain_", CAPTAIN_SUIT, 1, EntityEquipmentSlot.CHEST);
	public static final Item CAPTAIN_PANTS = new ArmorBaseSkin("pants_captain", "captain_", CAPTAIN_SUIT, 1, EntityEquipmentSlot.FEET);
	
	public static final Item JUGGERNAUT_HELMET = new ArmorJuggernaut("helmet_juggernaut", "juggernaut_", JUGGERNAUT_SUIT, 1, EntityEquipmentSlot.HEAD);
	public static final Item JUGGERNAUT_CHEST = new ArmorJuggernaut("chest_juggernaut", "juggernaut_", JUGGERNAUT_SUIT, 1, EntityEquipmentSlot.CHEST);
	public static final Item JUGGERNAUT_PANTS = new ArmorJuggernaut("pants_juggernaut", "juggernaut_", JUGGERNAUT_SUIT, 1, EntityEquipmentSlot.FEET);
	
	public static final Item MARINE_HELMET = new ArmorBaseSkin("helmet_marine", "marine_", MARINE_SUIT, 1, EntityEquipmentSlot.HEAD);
	public static final Item MARINE_CHEST = new ArmorBaseSkin("chest_marine", "marine_", MARINE_SUIT, 1, EntityEquipmentSlot.CHEST);
	public static final Item MARINE_PANTS = new ArmorBaseSkin("pants_marine", "marine_", MARINE_SUIT, 1, EntityEquipmentSlot.FEET);
	
	public static final Item NVGOGGLES_t2 = new ArmorNV("nightvision", NV, 1, EntityEquipmentSlot.HEAD);
	
	//GUNS
	//Types: 
	//1 = Rifle | 2 = Pistol | 3 = Sniper
	//Format: Name, Creative Tab, Firerate, Clipsize, reload time, Automatic firerate, Single firerate, Bullet Damage, Bullet Range (Ticks), Ammo, Shootsound, reload sound
	public static final Item SCARACOG = new GunAimableSkin("scar_acog", main.gunTab, 4, 35, 50, 2, 5, 500, ModItems.RIFLE56, 1, "gun.scar.desc", "5.56x45mm Clip", 2);
	public static final Item SCAR = new GunAimableSkin("scar", main.gunTab, 4, 35, 50, 2, 5, 500, ModItems.RIFLE56, 1, "gun.scar.desc", "5.56x45mm Clip", 2);
	public static final Item GLOCK = new GunAimable("glock", main.gunTab, 12, 12, 50, 1, 3, 200, ModItems.PISTOL9mm, 1, "gun.glock.desc", "9mm Clip", 1);
	public static final Item AWP = new GunAimableSkin("l69a1", main.gunTab, 10, 6, 100, 3, 20, 500, ModItems.SNIPERCLIP, 1, "gun.l69a1.desc", ".338 Sniper Clip", 2);
	public static final Item SMG = new GunAimable("sting", main.gunTab, 2, 35, 50, 1, 7, 500, ModItems.SMG45, 1, "gun.sting.desc", ".45 ACP Clip", 2);
	public static final Item TOMMYGUN = new GunAimable("thompson", main.gunTab, 8, 35, 50, 1, 4F, 500, ModItems.SMG45, 1, "gun.thompson.desc", ".45 ACP Clip", 1);
	public static final Item HK416 = new GunAimableSkin("hk", main.gunTab, 4, 35, 50, 1, 6F, 500, ModItems.RIFLE56, 1, "gun.hk.desc", "5.56x45mm Clip", 2);
	public static final Item AKM = new GunAimableSkin("akm", main.gunTab, 3, 35, 50, 5, 6.5F, 500, ModItems.RIFLE762, 1, "gun.akm.desc", "7.62x39mm Clip", 2);
	public static final Item RPK = new GunAimableSkin("rpk", main.gunTab, 3, 50, 50, 5, 8.5F, 500, ModItems.RIFLE762, 1, "gun.rpk.desc", "7.62x39mm Clip", 2);
	public static final Item CR4 = new GunAimable("cr4", main.gunTab, 8, 35, 50, 5, 6F, 500, ModItems.RIFLE762, 1, "gun.cr4.desc", "7.62x39mm Clip", 2);
	public static final Item FAL = new GunAimable("fal", main.gunTab, 8, 35, 50, 5, 5.5F, 500, ModItems.RIFLE762, 1, "gun.fal.desc", "7.62x39mm Clip", 2);
	public static final Item UZI = new GunAimable("uzi", main.gunTab, 2, 35, 50, 5, 6F, 500, ModItems.PISTOL9mm, 1, "gun.uzi.desc", "9mm Clip", 1);
	public static final Item G36 = new GunAimable("g36", main.gunTab, 4, 35, 50, 2, 7.5F, 500, ModItems.RIFLE56, 1, "gun.g36.desc", "5.56x45mm Clip", 2);
	public static final Item G36C = new GunAimable("g36c", main.gunTab, 8, 35, 50, 2, 9F, 500, ModItems.DMRCLIP, 1, "gun.g36.desc", "5.56x45mm DMR Clip", 2);
	public static final Item GLOCK_SCOPED = new GunAimable("g18_scoped", main.gunTab, 12, 12, 50, 1, 3, 200, ModItems.PISTOL9mm, 1, "gun.glock.desc", "9mm Clip", 1);
	public static final Item AK74U = new GunAimable("ak74u", main.gunTab, 2, 35, 50, 1, 5.5F, 200, ModItems.RIFLE762, 1, "gun.ak74u.desc", "7.62x39mm Clip", 1);
	public static final Item MP18 = new GunAimableSkin("mp18", main.gunTab, 3, 32, 50, 1, 5, 200, ModItems.SMG45, 1, "gun.mp18.desc", ".45ACP Clip", 2);
	public static final Item P250 = new GunAimable("p250", main.gunTab, 8, 12, 50, 1, 4, 200, ModItems.PISTOL9mm, 1, "gun.p250.desc", "9mm Clip", 1);
	public static final Item AK12 = new GunAimable("ak12", main.gunTab, 2, 35, 50, 2, 5.5F, 200, ModItems.RIFLE762, 1, "gun.akm.desc", "7.62x39mm Clip", 2);
	public static final Item VECTOR = new GunAimableSkin("vector", main.gunTab, 2, 25, 50, 2, 4.5F, 200, ModItems.SMG45, 1, "gun.vector.desc", ".45ACP Clip", 1);
	public static final Item M4A1 = new GunAimableSkin("m4", main.gunTab, 2, 40, 50, 2, 4.0F, 200, ModItems.RIFLE56, 1, "gun.m4.desc", "5.56x45mm Clip", 2);
	public static final Item HK416C = new GunAimableSkin("hk416c", main.gunTab, 2, 45, 50, 2, 3.5F, 200, ModItems.RIFLE56, 1, "gun.hk416c.desc", "5.56x45mm Clip", 2);
	public static final Item MK14 = new GunAimable("mk14", main.gunTab, 8, 10, 50, 5, 9F, 500, ModItems.RIFLE762, 1, "gun.mk14.desc", "7.62x39mm Clip", 2);
	//public static final Item Grenade = new CGrenade("frag", 16, main.gunTab, type.FRAG);
	public static final Item ImpactGrenade = new CGrenade("impact", 16, main.gunTab, type.IMPACT);
	public static final Item BLITZSHIELD = new ItemBlitzShield("blitzshield", 1000, main.gunTab);
}

