package com.ubalube.scifiaddon.util.handlers;

import org.lwjgl.input.Keyboard;

import com.google.common.graph.Network;
import com.ubalube.scifiaddon.main;
import com.ubalube.scifiaddon.commands.CommandModify;
import com.ubalube.scifiaddon.entity.EntityBandit;
import com.ubalube.scifiaddon.entity.EntityGhost;
import com.ubalube.scifiaddon.entity.EntityGoliath;
import com.ubalube.scifiaddon.entity.model.shields.Blitzshield;
import com.ubalube.scifiaddon.entity.model.shields.ModelShield;
import com.ubalube.scifiaddon.init.BiomeInit;
import com.ubalube.scifiaddon.init.EntityInit;
import com.ubalube.scifiaddon.init.ModBlocks;
import com.ubalube.scifiaddon.init.ModItems;
import com.ubalube.scifiaddon.init.ModMusic;
import com.ubalube.scifiaddon.util.IHasModel;
import com.ubalube.scifiaddon.util.IShield;
import com.ubalube.scifiaddon.util.Reference;
import com.ubalube.scifiaddon.util.keybinds.KeyHandler;
import com.ubalube.scifiaddon.world.gen.WorldGenCustomStructures;
import com.ubalube.scifiaddon.world.gen.WorldGenOceanStructures;
import com.ubalube.scifiaddon.world.types.WorldTypeBadlands;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelWolf;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber
public class RegistryHandler 
{
	
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
		TileEntityHandler.registerTileEntities();
	}
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) 
	{
		for(Item item : ModItems.ITEMS) 
		{
			
			if(item instanceof IShield)
			{
				ModelShield shield = ((IShield) item).shieldModel();
				
				if(item == ModItems.BLITZSHIELD)
				{
					RegistryHandler.createRender(item, shield, ":textures/models/shield/blitzshield.png");
				}
				
			}
			
			if(item instanceof IHasModel) 
			{
				((IHasModel)item).registerModels();
			}
		}
		

		
		for(Block block : ModBlocks.BLOCKS) 
		{
			if(block instanceof IHasModel) 
			{
				((IHasModel)block).registerModels();
			}
		}
	}
	
	
	public static void preInitRegistries() {
		
		EntityInit.registerProjectile();
		EntityInit.registerEntities();

		GameRegistry.registerWorldGenerator(new WorldGenOceanStructures(), 100);
		GameRegistry.registerWorldGenerator(new WorldGenCustomStructures(), 100);
		//GameRegistry.registerWorldGenerator(new WorldGenCustomStructures_Dune(), 100);
		//GameRegistry.registerWorldGenerator(new WorldGenCustomStructures_Lab(), 100);
		//GameRegistry.registerWorldGenerator(new WorldGenCustomStructures_Armory(), 50);
		//GameRegistry.registerWorldGenerator(new WorldGenCustomStructures_Legion_SmallComp(), 50);
		//GameRegistry.registerWorldGenerator(new WorldGenCustomStructures_Legion_BossRoom(), 10);
	}
	
	@SideOnly(Side.CLIENT)
	public static void preInitRegistriesOne() 
	{
		RenderHandler.registerEntityRenders();
	}
	
	public static void initRegistries()
	{
		SoundHandler.registerSounds();
		//KeyHandler.registerKeyBinds();
		//BiomeInit.registerBiomes();
		
		//NetworkRegistry.INSTANCE.registerGuiHandler(main.instance, new GuiHandler());
		
		//For the Goliath Spawn
		Biome[] spawnBiomes = {Biome.REGISTRY.getObject(new ResourceLocation("desert")),};
		EntityRegistry.addSpawn(EntityBandit.class, 5, 4, 5, EnumCreatureType.CREATURE, spawnBiomes);
		EntityRegistry.addSpawn(EntityGoliath.class, 10, 0, 1, EnumCreatureType.CREATURE, spawnBiomes);
		Biome[] spawnBiomes2 = {Biome.REGISTRY.getObject(new ResourceLocation("ice_flats")), };
		EntityRegistry.addSpawn(EntityGhost.class, 30, 2, 4, EnumCreatureType.MONSTER, spawnBiomes2);
	}
	
	public static  void postInitRegistries()
	{
		WorldType BADLANDS = new WorldTypeBadlands();
		
	}
	
	public static void serverRegistries(FMLServerStartingEvent e)
	{
		e.registerServerCommand(new CommandModify());
	}
	
	@SideOnly(Side.CLIENT)
	private static void createRender(Item item, ModelShield s, String resource) {
		item.setTileEntityItemStackRenderer(new TileEntityItemStackRenderer() {    
		@Override
            public void renderByItem(ItemStack stack) {
                Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID + resource));
                GlStateManager.pushMatrix();
                GlStateManager.scale(1.0F, -1.0F, -1.0F);
                s.render();
                GlStateManager.popMatrix();
            }
	    });
	}
	
}
