package com.ubalube.scifiaddon.util;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

import org.lwjgl.input.Mouse;

import com.ubalube.scifiaddon.main;
import com.ubalube.scifiaddon.client.mainmenu.GuiCaliberMenu;
import com.ubalube.scifiaddon.client.mainmenu.GuiNew;
import com.ubalube.scifiaddon.items.GunAimable;
import com.ubalube.scifiaddon.items.GunBase;
import com.ubalube.scifiaddon.util.handlers.ConfigHandler;
import com.ubalube.scifiaddon.util.packets.MessageReloadGun;
import com.ubalube.scifiaddon.util.packets.MessageSaveData;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiDownloadTerrain;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreenWorking;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.util.EnumHand;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.client.GuiWrongMinecraft;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import scala.reflect.io.Directory;

@EventBusSubscriber
public class MainEvents 
{
	@SubscribeEvent
	public static void doRespawnEvent(PlayerRespawnEvent e)
	{
		EntityPlayer p = (EntityPlayer) e.player;
		main.proxy.OpenLoadoutGUI(p);
	}

	@SubscribeEvent
    public static void playerQuit(PlayerEvent.PlayerLoggedOutEvent event) {
        if(!event.player.getEntityWorld().isRemote) {
            main.NETWORK.sendToAll(new MessageSaveData(WorldData.teams));
        }
        
        if(!event.player.getEntityWorld().isRemote)
        {
    		ConfigHandler.ClientSide.HELMET = event.player.inventory.armorInventory.get(3);
            ConfigHandler.ClientSide.CHESTPLATE = event.player.inventory.armorInventory.get(2);
            ConfigHandler.ClientSide.LEGGINGS = event.player.inventory.armorInventory.get(1);
            ConfigHandler.ClientSide.BOOTS = event.player.inventory.armorInventory.get(0);
            ConfigHandler.ClientSide.lastMainItem = event.player.getHeldItemMainhand();
        }
    }
	
	@SubscribeEvent
	public void onGuiLaunch(GuiOpenEvent event) {
		
		if(ConfigHandler.ClientSide.ShowNewGUI == true)
		{
			if (event.getGui() instanceof GuiMainMenu) 
			{
				event.setGui(new GuiNew());
			}
		}
		else
		{
			if(ConfigHandler.ClientSide.DiamondCaliberMainMenu)
			{
				if (event.getGui() instanceof GuiMainMenu) 
				{
					event.setGui(new GuiCaliberMenu());
				}
			}
		}
		
	}

    @SubscribeEvent
    public static void playerJoin(EntityJoinWorldEvent event) {
        if(!event.getWorld().isRemote && event.getEntity() instanceof EntityPlayer) {
            try {
                WorldData.get(event.getWorld());
            } catch (NoClassDefFoundError ex) {}
            main.NETWORK.sendToAll(new MessageSaveData(WorldData.teams));
        }
    }
    
    @SubscribeEvent
    public static void configUpdate(ConfigChangedEvent.OnConfigChangedEvent event) {
        if(event.getModID().equals(Reference.MOD_ID)) {
            ConfigManager.sync(Reference.MOD_ID, Config.Type.INSTANCE);
        }
    }
    
    @SubscribeEvent
    public static void logIn(PlayerEvent.PlayerLoggedInEvent event) {
        if(!event.player.getEntityWorld().isRemote) {
        	main.NETWORK.sendToAll(new MessageSaveData(WorldData.teams));
            if(ConfigHandler.ServerSide.showLoadoutGUI == true)
            {
            	main.proxy.OpenLoadoutGUI(event.player);
            }
        }
    }
	
}
