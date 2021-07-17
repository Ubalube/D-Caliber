package com.ubalube.scifiaddon.util.handlers;


import com.mojang.authlib.GameProfile;
import com.ubalube.scifiaddon.util.Reference;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Config;

@Config(modid=Reference.MOD_ID)
public class ConfigHandler {

	public static Client ClientSide = new Client();
    public static Server ServerSide = new Server();
	
    public static class Client {
        @Config.Comment({"Use Diamond Caliber Crosshair"})
        public boolean dcCrosshair = true;
        @Config.Comment({"Show Weapon HUD"})
        public boolean weaponHud = true;
        
        @Config.Ignore
        public ItemStack lastMainItem = ItemStack.EMPTY;
        @Config.Ignore
        public ItemStack HELMET = ItemStack.EMPTY;
        @Config.Ignore
        public ItemStack CHESTPLATE = ItemStack.EMPTY;
        @Config.Ignore
        public ItemStack LEGGINGS = ItemStack.EMPTY;
        @Config.Ignore
        public ItemStack BOOTS = ItemStack.EMPTY;
    }

    public static class Server {
        @Config.Comment({"Enable Structure Spawns"})
        public boolean spawnStructures = true;
        @Config.Comment({"Goliath Spawn Chance"})
        public int spawnChance = 10;
        @Config.Comment({"Do hitmarker sound"})
        public boolean hitmark;
    }
    
}
