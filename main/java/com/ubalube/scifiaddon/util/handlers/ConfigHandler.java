package com.ubalube.scifiaddon.util.handlers;


import com.ubalube.scifiaddon.util.Reference;

import net.minecraftforge.common.config.Config;

@Config(modid=Reference.MOD_ID)
public class ConfigHandler {

	public static Client ClientSide = new Client();
    public static Server ServerSide = new Server();
	
    public static class Client {
        @Config.Comment({"Use Diamond Caliber Crosshair"})
        public boolean dcCrosshair;
        @Config.Comment({"Show Weapon HUD"})
        public boolean weaponHud;
        @Config.Comment({"Left Click to Shoot"})
        public boolean leftClickToShoot = false;
    }

    public static class Server {
        @Config.Comment({"Enable Structure Spawns"})
        public boolean spawnStructures = true;
        @Config.Comment({"Goliath Spawn Chance"})
        public int spawnChance = 10;
        @Config.Comment({"Show Loadout GUI on Join"})
        public boolean showLoadoutGUI = false;
        @Config.Comment({"Do hitmarker sound"})
        public boolean hitmark;
    }
    
}
