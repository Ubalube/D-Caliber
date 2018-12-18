package com.ubalube.scifiaddon.init;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.ubalube.scifiaddon.items.medical.Boosters;
import com.ubalube.scifiaddon.items.medical.MedicalKit;
import com.ubalube.scifiaddon.items.music.RecordItem;
import com.ubalube.scifiaddon.main;
import com.ubalube.scifiaddon.armor.ArmorBaseSkin;
import com.ubalube.scifiaddon.armor.ArmorBaseSkinNV;
import com.ubalube.scifiaddon.items.CGunAimingBase;
import com.ubalube.scifiaddon.items.CGunBase;
import com.ubalube.scifiaddon.items.CGunHelper;
import com.ubalube.scifiaddon.items.CGunPDW;
import com.ubalube.scifiaddon.items.CGunPistol;
import com.ubalube.scifiaddon.items.CGunSkinnable;
import com.ubalube.scifiaddon.items.CGunSniper;
import com.ubalube.scifiaddon.items.CNode;
import com.ubalube.scifiaddon.items.CRangefinder;
import com.ubalube.scifiaddon.items.ItemBase;
import com.ubalube.scifiaddon.items.ItemDurability;
import com.ubalube.scifiaddon.items.ItemFactions;
import com.ubalube.scifiaddon.items.ItemLore;
import com.ubalube.scifiaddon.items.ItemPaint;
import com.ubalube.scifiaddon.util.Reference;
import com.ubalube.scifiaddon.util.handlers.SoundHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockPumpkin;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.util.text.TextFormatting;
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
	
	
	
	//Ammmo
	public static final Item VECTORAMMO = new ItemBase("vectormag", 64, main.gunTab);
	public static final Item M16AMMO = new ItemBase("m16mag", 64, main.gunTab);
	public static final Item VSSMAG = new ItemBase("vssmag", 64, main.gunTab);
	public static final Item TECAMMO = new ItemBase("tecmag", 64, main.gunTab);
	public static final Item DRUMMAG = new ItemBase("drummag", 64, main.gunTab);
	public static final Item C96MAG = new ItemBase("c96magazine", 64, main.gunTab);
	public static final Item LMGMAG = new ItemBase("lmgmag", 64, main.gunTab);
	
	//Paints
	public static final Item REDSTONE_PAINT = new ItemPaint("redstonepaint", 10, main.objectTab, "Redstone Dust", TextFormatting.RED);
	public static final Item LIGHTNING_PAINT = new ItemPaint("lightningpaint", 10, main.objectTab, "Redstone Dust", TextFormatting.RED);
	
	//Medals
	public static final Item TANKMEDAL = new ItemLore("tankmedal", 1, main.objectTab, "You defeated the Goliath", TextFormatting.YELLOW);
	
	//Parts
	public static final Item RIFLECALIBER = new ItemBase("riflecaliber", 64, main.partTab);
	public static final Item RIFLECALIBERCARBON = new ItemBase("riflecalibercarbon", 64, main.partTab);
	
	public static final Item PISTOLCALIBER = new ItemBase("pistolcaliber", 64, main.partTab);
	public static final Item PISTOLCALIBERCARBON = new ItemBase("pistolcalibercarbon", 64, main.partTab);
	
	public static final Item PSO = new ItemBase("pso", 64, main.partTab);
	public static final Item IRONSIGHTS = new ItemBase("ironsights", 64, main.partTab);
	public static final Item M16IRONSIGHTS = new ItemBase("m16ironsights", 64, main.partTab);
	public static final Item ACOG = new ItemBase("acog", 64, main.partTab);
	
	public static final Item VECTORSTOCK = new ItemBase("vectorstock", 64, main.partTab);
	public static final Item VSSSTOCK = new ItemBase("vssstock", 64, main.partTab);
	public static final Item M16STOCK = new ItemBase("m16a4stock", 64, main.partTab);
	
	public static final Item VSSBARREL = new ItemBase("vssbarrel", 64, main.partTab);
	public static final Item SHOTBARREL = new ItemBase("shortbarrel", 64, main.partTab);
	public static final Item LONGBARREL = new ItemBase("longbarrel", 64, main.partTab);
	
	public static final Item GRIP = new ItemFactions("grip", 64, main.partTab);
	
	//Materials
	public static final Item CARBONFIBRE = new ItemBase("carbonfibre", 64, main.partTab);
	public static final Item STEEL = new ItemBase("steel", 64, main.partTab);
	public static final Item LENSE = new ItemBase("lense", 64, main.partTab);
	
	//Music
	//Music Discs
	public static RecordItem HOSTILE_LANDS = new RecordItem("hostile_lands", ModMusic.HOSTILE_LANDS);
	
	//Medical
	public static final Item PILLS = new Boosters("pills", 3, main.objectTab, 2, 50, 2400);
	public static final Item MEDKIT = new MedicalKit("medkit", 3, main.objectTab, 10, 100);
	
	//Objects
	public static final Item RANGEFINDER = new CRangefinder("rangefinder", main.gunTab);
	public static final Item NODE = new CNode("node", main.objectTab);
	
	//Armor
	public static final Item RANGER_HELMET = new ArmorBaseSkin("helmet_ranger", "ranger_model_", ARMOR_MATERIAL_RANGER, 1, EntityEquipmentSlot.HEAD);
	public static final Item RANGER_CHEST = new ArmorBaseSkin("chest_ranger", "ranger_model_", ARMOR_MATERIAL_RANGER, 1, EntityEquipmentSlot.CHEST);
	public static final Item RANGER_PANTS = new ArmorBaseSkin("pants_ranger", "ranger_model_", ARMOR_MATERIAL_RANGER, 1, EntityEquipmentSlot.FEET);
	
	public static final Item SPEC_HELMET = new ArmorBaseSkinNV("helmet_spec", "specop_", ARMOR_MATERIAL_SPECOPS, 1, EntityEquipmentSlot.HEAD);
	public static final Item SPEC = new ArmorBaseSkin("chest_spec", "specop_", ARMOR_MATERIAL_SPECOPS, 1, EntityEquipmentSlot.CHEST);
	public static final Item SPEC_PANTS = new ArmorBaseSkin("pants_spec", "specop_", ARMOR_MATERIAL_SPECOPS, 1, EntityEquipmentSlot.FEET);
	
	//GUNS
	//Types: 
	//1 = Rifle | 2 = Pistol | 3 = Sniper
	//Format: Name, Creative Tab, Firerate, Clipsize, reload time, Automatic firerate, Single firerate, Bullet Damage, Bullet Range (Ticks), Ammo, Shootsound, reload sound
	public static final Item VECTOR = new CGunSkinnable("vector", main.gunTab, 8, 25, 8, 4, 8, 4.5F, 600, ModItems.VECTORAMMO, 2);
	public static final Item M4 = new CGunBase("m4", main.gunTab, 8, 35, 12, 4, 8, 6.5F, 600, ModItems.M16AMMO);
	public static final Item COMBATRIFLE = new CGunAimingBase("tactm4", main.gunTab, 8, 35, 12, 4, 8, 7.5F, 600, ModItems.M16AMMO, 1);
	public static final Item COMBATSMG = new CGunAimingBase("combatsmg", main.gunTab, 8, 35, 12, 4, 8, 7.5F, 600, ModItems.VECTOR, 1);
	public static final Item TACTSCAR = new CGunAimingBase("tactscar", main.gunTab, 8, 55, 15, 8, 4, 7.0F, 600, ModItems.DRUMMAG, 1);
	public static final Item FAMAS = new CGunSkinnable("famas", main.gunTab, 8, 35, 12, 4, 8, 5.5F, 500, ModItems.M16AMMO, 1);
	public static final Item SCAR = new CGunBase("scar", main.gunTab, 8, 35, 12, 4, 8, 5.0F, 500, ModItems.M16AMMO);
	public static final Item TEC9 = new CGunBase("tec9", main.gunTab, 8, 20, 8, 4, 8, 2.0F, 300, ModItems.TECAMMO);
	public static final Item AK12 = new CGunSkinnable("ak12", main.gunTab, 8, 35, 8, 4, 8, 10.0F, 300, ModItems.TECAMMO, 1);
	
	public static final Item PDW = new CGunPDW("PDW", main.gunTab, 8, 35, 12, 4, 8, 100.0F, 600, ModItems.M16AMMO, 1);
	
	//LMG
	public static final Item LMG = new CGunAimingBase("lmg", main.gunTab, 8, 55, 15, 8, 4, 6.5F, 600, ModItems.DRUMMAG, 1);
	public static final Item LMG_NOSHIELD = new CGunAimingBase("lmg_noshield", main.gunTab, 8, 55, 15, 8, 4, 6.5F, 600, ModItems.DRUMMAG, 1);
	
	//Snipers
	public static final Item VSS = new CGunSniper("vssvintorez", main.gunTab, 12, 12, 12, 15.0F, 800, ModItems.VSSMAG);
	public static final Item M24 = new CGunSniper("m24", main.gunTab, 20, 12, 30, 50.0F, 1000, ModItems.VSSMAG);
	public static final Item BFG = new CGunSniper("bfg", main.gunTab, 0, 1, 80, 100.0F, 1000, ModItems.VSSMAG);
	
	//Pistols
	public static final Item G17 = new CGunPistol("g18", main.gunTab, 8, 17, 12, 3, 300, ModItems.TECAMMO);
	public static final Item C96 = new CGunPistol("c96", main.gunTab, 6, 12, 15, 8, 300, ModItems.TECAMMO);
	
	//Helpers
	public static final Item ELCOVERT = new CGunHelper("elcovert", main.gunTab, 4, 34, 12, 5.0F, 600, ModItems.TECAMMO, "They won't know what hit em!", "XboxSignOut", TextFormatting.AQUA);
	public static final Item THELOSTONE = new CGunHelper("thelostone", main.gunTab, 10, 100, 12, 5.0F, 600, ModItems.TECAMMO, "Found from another realm, slipped through a portal", "SmellyModder", TextFormatting.AQUA);
	
}

