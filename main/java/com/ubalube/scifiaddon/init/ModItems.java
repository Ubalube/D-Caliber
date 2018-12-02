package com.ubalube.scifiaddon.init;

import java.util.ArrayList;
import java.util.List;

import com.ubalube.scifiaddon.items.VSS;
import com.ubalube.scifiaddon.items.medical.Boosters;
import com.ubalube.scifiaddon.items.medical.MedicalKit;
import com.ubalube.scifiaddon.items.TactM4;
import com.ubalube.scifiaddon.main;
import com.ubalube.scifiaddon.armor.ArmorBaseSkin;
import com.ubalube.scifiaddon.armor.ArmorBaseSkinNV;
import com.ubalube.scifiaddon.items.FamasF1;
import com.ubalube.scifiaddon.items.ItemBase;
import com.ubalube.scifiaddon.items.KrissVector;
import com.ubalube.scifiaddon.items.M16A4;
import com.ubalube.scifiaddon.items.Tec;
import com.ubalube.scifiaddon.util.Reference;

import net.minecraft.block.BlockPumpkin;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
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
	
	//Vector .45 ACP
	public static final Item VECTOR = new KrissVector("vector", main.gunTab);
	public static final Item VECTORAMMO = new ItemBase("vectormag", 64, main.gunTab);
	
	//M16A4
	public static final Item M16 = new M16A4("m4", main.gunTab);
	public static final Item M16AMMO = new ItemBase("m16mag", 64, main.gunTab);
	public static final Item TactM4 = new TactM4("tactm4", main.gunTab);
	
	//Famas F1
	public static final Item FAMAS = new FamasF1("famas", main.gunTab);
	
	//VSS
	public static final Item VSS = new VSS("vssvintorez", main.gunTab);
	public static final Item VSSMAG = new ItemBase("vssmag", 64, main.gunTab);
	
	//Minetratec Tec-9
	public static final Item TEC9 = new Tec("tec9", main.gunTab);
	public static final Item TECAMMO = new ItemBase("tecmag", 64, main.gunTab);
	
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
	
	public static final Item GRIP = new ItemBase("grip", 64, main.partTab);
	
	//Materials
	public static final Item CARBONFIBRE = new ItemBase("carbonfibre", 64, main.partTab);
	public static final Item STEEL = new ItemBase("steel", 64, main.partTab);
	public static final Item LENSE = new ItemBase("lense", 64, main.partTab);
	
	//Medical
	public static final Item PILLS = new Boosters("pills", 3, CreativeTabs.COMBAT, 2, 50, 2400);
	public static final Item MEDKIT = new MedicalKit("medkit", 3, CreativeTabs.COMBAT, 10, 100);
	
	//Armor
	public static final Item RANGER_HELMET = new ArmorBaseSkin("helmet_ranger", ARMOR_MATERIAL_RANGER, 1, EntityEquipmentSlot.HEAD);
	public static final Item RANGER_CHEST = new ArmorBaseSkin("chest_ranger", ARMOR_MATERIAL_RANGER, 1, EntityEquipmentSlot.CHEST);
	public static final Item RANGER_PANTS = new ArmorBaseSkin("pants_ranger", ARMOR_MATERIAL_RANGER, 1, EntityEquipmentSlot.FEET);
	
	public static final Item SPEC_HELMET = new ArmorBaseSkinNV("helmet_spec", ARMOR_MATERIAL_SPECOPS, 1, EntityEquipmentSlot.HEAD);
	public static final Item SPEC = new ArmorBaseSkin("chest_spec", ARMOR_MATERIAL_SPECOPS, 1, EntityEquipmentSlot.CHEST);
	public static final Item SPEC_PANTS = new ArmorBaseSkin("pants_spec", ARMOR_MATERIAL_SPECOPS, 1, EntityEquipmentSlot.FEET);
	
}
