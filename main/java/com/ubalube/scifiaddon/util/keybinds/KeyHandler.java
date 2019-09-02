package com.ubalube.scifiaddon.util.keybinds;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import com.ubalube.scifiaddon.main;
import com.ubalube.scifiaddon.util.packets.LeanDirection;
import com.ubalube.scifiaddon.util.packets.MessageLean;
import com.ubalube.scifiaddon.util.packets.MessageReloadGun;

import ca.weblite.objc.Message;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

public class KeyHandler 
{
	public static final int CUSTOM_INV = 0;
	private static final String[] desc = {"Reload"};
	/** Default key values */
	private static final int[] keyValues = {Keyboard.KEY_R};
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
			main.NETWORK.sendToServer(new MessageReloadGun(Minecraft.getMinecraft().player, Minecraft.getMinecraft().player.getHeldItemMainhand().getItem()));
		}
		
		
		
	}
	
}
