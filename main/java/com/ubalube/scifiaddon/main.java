package com.ubalube.scifiaddon;

import java.util.List;

import javax.swing.plaf.metal.OceanTheme;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;

import com.ubalube.scifiaddon.data.LoadoutData;
import com.ubalube.scifiaddon.entity.EntityBullet;
import com.ubalube.scifiaddon.entity.Player;
import com.ubalube.scifiaddon.init.EntityInit;
import com.ubalube.scifiaddon.init.ModItems;
import com.ubalube.scifiaddon.init.ModRecipes;
import com.ubalube.scifiaddon.proxy.CommonProxy;
import com.ubalube.scifiaddon.tabs.Armor;
import com.ubalube.scifiaddon.tabs.Decor;
import com.ubalube.scifiaddon.tabs.Guns;
import com.ubalube.scifiaddon.tabs.Objects;
import com.ubalube.scifiaddon.tabs.Parts;
import com.ubalube.scifiaddon.util.CamoDropEvent;
import com.ubalube.scifiaddon.util.FovUpdater;
import com.ubalube.scifiaddon.util.GunNBTEvent;
import com.ubalube.scifiaddon.util.MainEvents;
import com.ubalube.scifiaddon.util.Overlay;
import com.ubalube.scifiaddon.util.Reference;
import com.ubalube.scifiaddon.util.handlers.GuiHandler;
import com.ubalube.scifiaddon.util.handlers.RegistryHandler;
import com.ubalube.scifiaddon.util.handlers.RenderHandler;
import com.ubalube.scifiaddon.util.keybinds.KeyHandler;
import com.ubalube.scifiaddon.util.keybinds.ReloadKeyBind;
import com.ubalube.scifiaddon.util.packets.MessageGiveItems;
import com.ubalube.scifiaddon.util.packets.MessageReloadGun;
import com.ubalube.scifiaddon.util.packets.MessageTakeItems;
import com.ubalube.scifiaddon.world.WorldGen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.GameRules.ValueType;
import net.minecraft.world.gen.structure.StructureOceanMonument;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces;
import net.minecraftforge.client.settings.KeyBindingMap;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerCareer;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class main 
{
	
	public static final Guns gunTab = new Guns();
	public static final Parts partTab = new Parts();
	public static final Armor armorTab = new Armor();
	public static final Decor decorTab = new Decor();
	public static final Objects objectTab = new Objects();
	
	@Instance
	public static main instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;
	public static LoadoutData loadoutData;
	
	public static final SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);
	
	@EventHandler
	public static void preinit(FMLPreInitializationEvent event)
	{
		RegistryHandler.preInitRegistries();
		GameRegistry.registerWorldGenerator(new WorldGen(), 3);
		NetworkRegistry.INSTANCE.registerGuiHandler(main.instance, new GuiHandler());
		NETWORK.registerMessage(MessageGiveItems.HandleGiveItems.class, MessageGiveItems.class, 2, Side.SERVER);
		NETWORK.registerMessage(MessageTakeItems.HandleTakeItems.class, MessageTakeItems.class, 3, Side.SERVER);
		NETWORK.registerMessage(MessageReloadGun.HandleReloadGun.class, MessageReloadGun.class, 4, Side.SERVER);
	}
	
	@SideOnly(Side.CLIENT)
	@EventHandler
	public static void preinitOne(FMLPreInitializationEvent event)
	{
		RegistryHandler.preInitRegistriesOne();
	}
	
	//GAMERULES
	@EventHandler
	public void serverStart(FMLServerStartingEvent event) 
	{
		World world = event.getServer().getEntityWorld();
		RegistryHandler.serverRegistries(event);
		//world.getGameRules().addGameRule("lethalguns", "false", ValueType.BOOLEAN_VALUE);
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent e) 
	{
		RegistryHandler.initRegistries();
		
		MinecraftForge.EVENT_BUS.register(new CamoDropEvent());
		MinecraftForge.EVENT_BUS.register(new GunNBTEvent());
		MinecraftForge.EVENT_BUS.register(new FovUpdater());
	    FMLCommonHandler.instance().bus().register(new KeyHandler());
	     
		ModRecipes.init();
		
	}
	
	
	@EventHandler
	public static void PostInit(FMLPostInitializationEvent e) 
	{
		RegistryHandler.postInitRegistries();
	}

}
