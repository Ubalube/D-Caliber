package com.ubalube.scifiaddon.client.mainmenu.utilities;

import com.mojang.authlib.GameProfile;
import com.ubalube.scifiaddon.init.ModItems;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;

import static net.minecraft.client.Minecraft.getMinecraft;
import static net.minecraft.client.renderer.GlStateManager.*;


public class FakePlayerRendering {

    private final Renderer normalArms;
    private final Renderer smallArms;
    private final AbstractClientPlayer player;

    public FakePlayerRendering(GameProfile profile) {
        RenderManager renderManager = getMinecraft().getRenderManager();
        normalArms = new Renderer(renderManager, false);
        smallArms = new Renderer(renderManager, true);

        boolean isSessionProfile = getMinecraft().getSession().getProfile() == profile;
        if (!isSessionProfile || !getMinecraft().getSession().hasCachedProperties()) {
            profile = getMinecraft().getSessionService().fillProfileProperties(profile, true);
            if (isSessionProfile) {
                getMinecraft().getSession().setProperties(profile.getProperties());
            }
        }
        player = new FakePlayer(profile);
    }

    public void renderOtherPlayerModel(GameProfile profile, int posX, int posY, float scale, float rotation, ItemStack helm, ItemStack chest, ItemStack legs, ItemStack boots, ItemStack held) {
        Minecraft mc = getMinecraft();
        FakePlayer fplayer = new FakePlayer(profile);

        mc.getRenderManager().pointedEntity = fplayer;//TODO ?
        mc.getRenderManager().renderEngine = mc.getTextureManager();

        pushMatrix();
        translate(posX, posY, 50.0F);
        scale(-scale, scale, scale);
        rotate(180.0F, 0.0F, 0.0F, 1.0F);

        color(1, 1, 1, 1);
        enableCull();
        enableAlpha();
        enableDepth();

        RenderHelper.enableStandardItemLighting();

        rotate(rotation, 0.0F, 1.0F, 0.0F);



        player.rotationYawHead = player.rotationYaw + rotation;
        player.inventory.armorInventory.set(3, helm);
        player.inventory.armorInventory.set(2, chest);
        player.inventory.armorInventory.set(1, legs);
        player.inventory.armorInventory.set(0, boots);
        player.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, held);
        
        fplayer.rotationYawHead = fplayer.rotationYaw + rotation;

        translate(0.0F, fplayer.getYOffset(), 0.0F);

        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
        Renderer renderer = fplayer.getSkinType().equals("slim") ? smallArms : normalArms;
        renderer.doRender(fplayer, 0.0D, 0.0D, 0.0F, 0.0F, 0.625F);

        disableDepth();
        disableAlpha();
        RenderHelper.disableStandardItemLighting();
        popMatrix();

    }

    public void renderPlayerModel(int posX, int posY, float scale, float rotation, ItemStack helm, ItemStack chest, ItemStack legs, ItemStack boots, ItemStack held) {
        Minecraft mc = getMinecraft();

        mc.getRenderManager().pointedEntity = player;//0TODO ?
        mc.getRenderManager().renderEngine = mc.getTextureManager();

        pushMatrix();
        translate(posX, posY, 50.0F);
        scale(-scale, scale, scale);
        rotate(180.0F, 0.0F, 0.0F, 1.0F);
        color(1, 1, 1, 1);
        enableCull();
        enableAlpha();
        enableDepth();

        RenderHelper.enableStandardItemLighting();

        rotate(rotation, 0.0F, 1.0F, 0.0F);

        player.rotationYawHead = player.rotationYaw + rotation;
        player.inventory.armorInventory.set(3, helm);
        player.inventory.armorInventory.set(2, chest);
        player.inventory.armorInventory.set(1, legs);
        player.inventory.armorInventory.set(0, boots);
        player.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, held);

        translate(0.0F, player.getYOffset(), 0.0F);

        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);

        Renderer renderer = player.getSkinType().equals("slim") ? smallArms : normalArms;
        renderer.doRender(player, 0.0D, 0.0D, 0.0F, 0.0F, 0.625F);
        
        //player.inventory.armorInventory.add(new ItemStack(ModItems.NVGOGGLES_t3));

        disableDepth();
        disableAlpha();
        RenderHelper.disableStandardItemLighting();
        popMatrix();

    }

    private static final class Renderer extends RenderPlayer {

        public Renderer(RenderManager renderManager, boolean useSmallArms) {
            super(renderManager, useSmallArms);
        }

        @Override
        protected boolean canRenderName(AbstractClientPlayer targetEntity) {
            return false;
        }
    }

    private static final class FakePlayer extends AbstractClientPlayer {

        private final NetworkPlayerInfo playerInfo;

        FakePlayer(GameProfile profile) {
            super(new FakeWorld(), profile);
            playerInfo = new NetworkPlayerInfo(profile);
            playerInfo.getLocationSkin(); // initialize it
        }

        @Override
        protected NetworkPlayerInfo getPlayerInfo() {
            return playerInfo;
        }

        @Override
        public boolean isSpectator() {
            return false;
        }

    }

    private static final class FakeWorld extends World {

        FakeWorld() {
            super(null, null, new FakeWorldProvider(), null, true);
        }

        @Override
        protected IChunkProvider createChunkProvider() {
            return null;
        }

        @Override
        protected boolean isChunkLoaded(int x, int z, boolean allowEmpty) {
            return false;
        }

        @Override
        public BlockPos getSpawnPoint() {
            return new BlockPos(0, 0, 0);
        }
    }

    private static final class FakeWorldProvider extends WorldProvider {

        FakeWorldProvider() {
        }

        @Override
        public DimensionType getDimensionType() {
            return DimensionType.OVERWORLD;
        }

    }

}
