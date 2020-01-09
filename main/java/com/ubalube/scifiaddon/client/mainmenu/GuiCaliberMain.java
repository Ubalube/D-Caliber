package com.ubalube.scifiaddon.client.mainmenu;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Color;

import com.ubalube.scifiaddon.client.mainmenu.api.GuiButtonCaliber;
import com.ubalube.scifiaddon.client.mainmenu.api.GuiContainer;
import com.ubalube.scifiaddon.client.mainmenu.utilities.GuiRenderHelper;
import com.ubalube.scifiaddon.util.Reference;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class GuiCaliberMain extends GuiScreen {

    protected ArrayList<GuiContainer> guiContainers = new ArrayList();

    public String uiTitle = "Unknown";

    public static final ResourceLocation menuBackground = new ResourceLocation(Reference.MOD_ID,"textures/gui/menu/background" + (int) (Math.random() * ((5 - 1) + 1)) + ".png");

    public final int BUTTON_LINK_DISCORD = 200;
    public final int BUTTON_LINK_WEBSITE = 201;

    public final int BUTTON_PLAY = 204;

    public final int BUTTON_NEWS = 205;

    public final int BUTTON_SETTINGS = 206;

    public final int BUTTON_SINGLEPLAYER = 207;
    public final int BUTTON_MULTIPLAYER = 208;

    public final int BUTTON_QUIT = 209;
    public final int BUTTON_UPDATES = 210;

    @Override
    public void updateScreen()
    {
        super.updateScreen();
        for(GuiButton button : this.buttonList){
            if(button instanceof GuiButtonCaliber){
                ((GuiButtonCaliber)button).updateButton();
            }
        }
    }


    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks){

        GL11.glPushMatrix();

        GuiRenderHelper.renderImageCentered(width / 2,0,menuBackground,width,height);

        GuiRenderHelper.renderRectWithOutline(0,0,width,40,0x55000000,0x44000000,1);
        GuiRenderHelper.renderRectWithOutline(0,height - 40,width,40,0x55000000,0x44000000,1);
        //DeadRenderHelper.renderImage(4,3,new ResourceLocation(Reference.MOD_ID,"textures/gui/logo.png"),110,29);

        GuiRenderHelper.renderCenteredTextScaled(I18n.format("gui.button.version") ,58,34,0xFFFFFF,0.5);

        GL11.glPopMatrix();

        super.drawScreen(mouseX,mouseY,partialTicks);
    }


    public void setUiTitle(String givenTitle){
        this.uiTitle = givenTitle;
    }


    public String getUiTitle(){
        return this.uiTitle;
    }


    public void addContainer(GuiContainer container) {
        container.initGui();
        guiContainers.add(container);
    }


    public void updateContainers() {
        for (GuiContainer gui : guiContainers) {
            gui.updateScreen();
        }
    }


    public GuiContainer getContainer(int containerID) {
        for (GuiContainer cont : guiContainers) {
            if (cont.containerID == containerID) {
                return cont;
            }
        }
        return null;
    }

    private static final String pad(String s) {
        return (s.length() == 1) ? "0" + s : s;
    }

    public int toHex(Color color) {
        String alpha = pad(Integer.toHexString(color.getAlpha()));
        String red = pad(Integer.toHexString(color.getRed()));
        String green = pad(Integer.toHexString(color.getGreen()));
        String blue = pad(Integer.toHexString(color.getBlue()));
        String hex = "0x" + alpha + red + green + blue;
        return Integer.parseInt(hex, 16);
    }


    public void openURL(String givenURL){
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(new URI(givenURL));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }
}
