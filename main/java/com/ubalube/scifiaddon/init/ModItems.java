package com.ubalube.scifiaddon.init;

import java.util.ArrayList;
import java.util.List;

import com.ubalube.scifiaddon.items.VSS;

import com.ubalube.scifiaddon.main;
import com.ubalube.scifiaddon.armor.ArmorBaseSkin;
import com.ubalube.scifiaddon.items.FamasF1;
import com.ubalube.scifiaddon.items.ItemBase;
import com.ubalube.scifiaddon.items.KrissVector;
import com.ubalube.scifiaddon.items.Packages;
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
	
	public static final ArmorMaterial ARMOR_RANGER = EnumHelper.addArmorMaterial("armor_ranger", Reference.MOD_ID + ":ranger_model", 20, new int[] { 7, 8, 8, 9 }, 0, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0f);
	
	//Vector .45 ACP
	public static final Item VECTOR = new KrissVector("vector", main.gunTab);
	public static final Item VECTORAMMO = new ItemBase("vectormag", 64, main.gunTab);
	
	//Famas F1
	public static final Item FAMAS = new FamasF1("famas", main.gunTab);
	public static final Item FAMASAMMO = new ItemBase("famasmag", 64, main.gunTab);
	
	//VSS
	public static final Item VSS = new VSS("vssvintorez", main.gunTab);
	public static final Item VSSMAG = new ItemBase("vssmag", 64, main.gunTab);
	
	//Minetratec Tec-9
	public static final Item TEC9 = new Tec("tec9", main.gunTab);
	public static final Item TECAMMO = new ItemBase("tecmag", 64, main.gunTab);
	
	//Packages
	public static final Item BETA = new Packages("beta", 1, CreativeTabs.COMBAT);
	public static final Item BRAVO = new Packages("bravo", 1, CreativeTabs.COMBAT);
	public static final Item ALPHA = new Packages("alpha", 1, CreativeTabs.COMBAT);
	public static final Item ELITE = new Packages("elite", 1, CreativeTabs.COMBAT);
	
	//Parts
	public static final Item RIFLECALIBER = new ItemBase("riflecaliber", 64, main.partTab);
	public static final Item RIFLECALIBERCARBON = new ItemBase("riflecalibercarbon", 64, main.partTab);
	
	public static final Item PISTOLCALIBER = new ItemBase("pistolcaliber", 64, main.partTab);
	public static final Item PISTOLCALIBERCARBON = new ItemBase("pistolcalibercarbon", 64, main.partTab);
	
	public static final Item PSO = new ItemBase("pso", 64, main.partTab);
	public static final Item IRONSIGHTS = new ItemBase("ironsights", 64, main.partTab);
	
	public static final Item VECTORSTOCK = new ItemBase("vectorstock", 64, main.partTab);
	public static final Item VSSSTOCK = new ItemBase("vssstock", 64, main.partTab);
	
	public static final Item VSSBARREL = new ItemBase("vssbarrel", 64, main.partTab);
	public static final Item SHOTBARREL = new ItemBase("shortbarrel", 64, main.partTab);
	public static final Item LONGBARREL = new ItemBase("longbarrel", 64, main.partTab);
	
	//Materials
	public static final Item CARBONFIBRE = new ItemBase("carbonfibre", 64, main.partTab);
	public static final Item STEEL = new ItemBase("steel", 64, main.partTab);
	public static final Item LENSE = new ItemBase("lense", 64, main.partTab);
	
	//Armor
	public static final Item RANGER_HELMET = new ArmorBaseSkin("rangerhelmet", ARMOR_RANGER, 1, EntityEquipmentSlot.HEAD);
	public static final Item RANGER_CHEST = new ArmorBaseSkin("rangerchest", ARMOR_RANGER, 1, EntityEquipmentSlot.CHEST);
	public static final Item RANGER_PANTS = new ArmorBaseSkin("rangerpants", ARMOR_RANGER, 1, EntityEquipmentSlot.LEGS);
	public static final Item RANGER_BOOTS = new ArmorBaseSkin("rangerboots", ARMOR_RANGER, 1, EntityEquipmentSlot.FEET);
}
