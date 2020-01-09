package com.ubalube.scifiaddon.util.keybinds;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import com.ubalube.scifiaddon.main;
import com.ubalube.scifiaddon.util.packets.MessageReloadGun;
import com.ubalube.scifiaddon.util.packets.MessageThrowGrenade;

import ca.weblite.objc.Message;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.settings.KeyBindingMap;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class KeyHandler 
{
	public static final int CUSTOM_INV = 0;
	private static final String[] desc = {"Reload", "Throw Grenade"};
	/** Default key values */
	private static final int[] keyValues = {Keyboard.KEY_R, Keyboard.KEY_G};
	private final KeyBinding[] keys;
	
	
	public KeyHandler() {
		keys = new KeyBinding[desc.length];
		for (int i = 0; i < desc.length; ++i) {
		keys[i] = new KeyBinding(desc[i], keyValues[i], "Diamond Caliber");
		ClientRegistry.registerKeyBinding(keys[i]);
		
		
		
		}
	}
	
	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event) {
	// FMLClientHandler.instance().getClient().inGameHasFocus
		if (keys[0].isPressed()) 
		{
			
			EntityPlayer p = (EntityPlayer) Minecraft.getMinecraft().player;
			
			main.NETWORK.sendToServer(new MessageReloadGun(p, p.getHeldItemMainhand().getItem()));
		}
		if (keys[1].isPressed()) 
		{
			
			EntityPlayer p = (EntityPlayer) Minecraft.getMinecraft().player;

			main.NETWORK.sendToServer(new MessageThrowGrenade(p, p.getHeldItemMainhand().getItem()));
		}
		
		
		
	}
	
}
