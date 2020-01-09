package com.ubalube.scifiaddon.client.mainmenu;

import com.ubalube.scifiaddon.client.mainmenu.api.GuiButtonCaliber;
import com.ubalube.scifiaddon.client.mainmenu.utilities.GuiRenderHelper;
import com.ubalube.scifiaddon.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiShareToLan;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.gui.advancements.GuiScreenAdvancements;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.realms.RealmsBridge;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.GuiModList;

public class GuiCaliberOperations extends GuiScreen
{
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        this.drawDefaultBackground();
        GlStateManager.disableDepth();
        GuiRenderHelper.renderRectWithOutline(39, this.height - 178, 126, 150, 1426063360, 1140850688, 1);
        GuiRenderHelper.renderImage(20.0, this.height - 235, new ResourceLocation("deadrising", "textures/gui/logo.png"), 165.0, 43.5);
        GuiRenderHelper.renderCenteredTextScaledWithShadow("v1.0", 102, this.height - 200, 16777215, 1.0);
        EntityPlayer p = mc.player;
        
        ItemStack HELMET = p.inventory.armorInventory.get(3);
        ItemStack CHESTPLATE = p.inventory.armorInventory.get(2);
        ItemStack LEGGINGS = p.inventory.armorInventory.get(1);
        ItemStack BOOTS = p.inventory.armorInventory.get(0);
        ItemStack lastMainItem = p.getHeldItemMainhand();
        
        GuiRenderHelper.renderPlayer(width / 2 +150,height / 2 + 155,150, -45, HELMET, CHESTPLATE, LEGGINGS, BOOTS, lastMainItem);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    public void initGui() {
        this.buttonList.clear();
        this.buttonList.add(new GuiButtonCaliber(1, 42, this.height - 187, 120, 30, "Resume").setImage(new ResourceLocation(Reference.MOD_ID + "/textures/gui/menu/play.png")));
        this.buttonList.add(new GuiButtonCaliber(2, 52, this.height - 101, 98, 20, "Quit"));
        final GuiButton guibutton = this.addButton((GuiButton)new GuiButtonCaliber(7, 52, this.height - 126, 98, 20, "Co-Op"));
        guibutton.enabled = (this.mc.isSingleplayer() && !this.mc.getIntegratedServer().getPublic());
        this.buttonList.add(new GuiButtonCaliber(3, 52, this.height - 151, 98, 20, "Settings"));
        

        
    }
    
    protected void actionPerformed(final GuiButton button) {
        switch (button.id) {
	        case 1: {
	            this.mc.displayGuiScreen((GuiScreen)null);
	            this.mc.setIngameFocus();
	            break;
	        }
	        case 2: {
                final boolean flag = this.mc.isIntegratedServerRunning();
                final boolean flag2 = this.mc.isConnectedToRealms();
                button.enabled = false;
                this.mc.world.sendQuittingDisconnectingPacket();
                this.mc.loadWorld((WorldClient)null);
                if (flag) {
                    this.mc.displayGuiScreen((GuiScreen)new GuiCaliberMain());
                    break;
                }
                if (flag2) {
                    final RealmsBridge realmsbridge = new RealmsBridge();
                    realmsbridge.switchToRealms((GuiScreen)new GuiCaliberMain());
                    break;
                }
                this.mc.displayGuiScreen((GuiScreen)new GuiMultiplayer((GuiScreen)new GuiCaliberMain()));
                break;
	        }
            case 3: {
                this.mc.displayGuiScreen((GuiScreen)new GuiOptions((GuiScreen)this, this.mc.gameSettings));
                break;
            }
        }
    }
    
}
