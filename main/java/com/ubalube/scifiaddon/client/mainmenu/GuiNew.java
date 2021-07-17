package com.ubalube.scifiaddon.client.mainmenu;

import java.io.IOException;

import org.lwjgl.opengl.GL11;

import com.ubalube.scifiaddon.util.handlers.ConfigHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.text.TextFormatting;

public class GuiNew extends GuiScreen
{
	
	public GuiCaliberMenu mainMenu;
	private GuiButton contBut;
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		
		String text1 = String.valueOf(TextFormatting.GREEN) + TextFormatting.BOLD + "Hello User!";

		String[] desc = ("Here are some things you should know before playing the mod!\n"
				+ "This is Diamond Caliber 2.0, a lot of things have been changed!\n"
				+ "There is now a config for the mod (Yay)!\n"
				+ "If the main menu does not look good to you, you are able to disable it in the config!\n"
				+ "Other than that,\n"
				+ "you may click the continue button below, this menu will not popup anymore!\n\n\n\n"
				+ "\nI hope you enjoy the new things I added!\n\n"
				+ TextFormatting.GREEN + "- xUbalubex (Developer)").split("\n");
		
		ScaledResolution sr = new ScaledResolution(this.mc);
		
		this.drawString(this.fontRenderer, text1, (sr.getScaledWidth() / 2) - (this.fontRenderer.getStringWidth(text1) / 2), 20, 0xFFFFFF);

		for (int i = 0; i < desc.length; i++)
		{
			String str = desc[i];

			this.drawString(this.fontRenderer, str, (sr.getScaledWidth() / 2) - (this.fontRenderer.getStringWidth(str) / 2), 40 + (i * 10), 0xFFFFFF);
		}
		
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
    public static void renderCenteredTextScaled(final String text, final int posX, final int posY, final int color, final double givenScale) {
        GL11.glPushMatrix();
        GL11.glTranslated((double)posX, (double)posY, 0.0);
        GL11.glScaled(givenScale, givenScale, givenScale);
        renderCenteredText(text, 0, 0, color);
        GL11.glPopMatrix();
    }
    
    public static void renderText(final String text, final int posX, final int posY, final int color) {
        final Minecraft mc = Minecraft.getMinecraft();
        mc.fontRenderer.drawString(text, posX, posY, color);
    }
    
    @Override
    public void initGui() {
    	ScaledResolution sr = new ScaledResolution(this.mc);
    	this.contBut = new GuiButton(0, (sr.getScaledWidth() / 2) - 60, 204, 120, 20, "Continue");
    	this.buttonList.add(this.contBut);
    	super.initGui();
    }
    
    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	if(button == this.contBut)
    	{
    		//ConfigHandler.ClientSide.ShowNewGUI = false;
    		this.mc.displayGuiScreen(new GuiCaliberMenu());
    	}
    	super.actionPerformed(button);
    }
    
    public static void renderCenteredText(final String text, final int posX, final int posY, final int color) {
        final Minecraft mc = Minecraft.getMinecraft();
        renderText(text, posX - mc.fontRenderer.getStringWidth(text) / 2, posY, color);
    }
}
