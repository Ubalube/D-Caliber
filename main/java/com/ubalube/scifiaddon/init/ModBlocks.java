package com.ubalube.scifiaddon.init;

import java.util.ArrayList;
import java.util.List;

import com.ubalube.scifiaddon.blocks.Airdrop;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(modid = "sfa")
public class ModBlocks 
{
	
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block AIRDROP = new Airdrop("airdrop", Material.ANVIL);
	
}
