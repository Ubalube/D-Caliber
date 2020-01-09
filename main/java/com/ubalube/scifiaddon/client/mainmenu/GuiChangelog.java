package com.ubalube.scifiaddon.client.mainmenu;

import java.io.IOException;
import java.util.UUID;

import org.lwjgl.opengl.GL11;

import com.mojang.authlib.GameProfile;
import com.ubalube.scifiaddon.client.mainmenu.utilities.GuiRenderHelper;
import com.ubalube.scifiaddon.init.ModItems;
import com.ubalube.scifiaddon.util.handlers.ConfigHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

public class GuiChangelog extends GuiScreen
{
	
	public GuiCaliberMenu mainMenu;
	private GuiButton contBut;
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		
		String text1 = String.valueOf(TextFormatting.YELLOW) + TextFormatting.BOLD + "Changelog";

		String[] desc = ("[/] Optomized Guns\n"
				+ TextFormatting.YELLOW + "[/] Changed the Main Menu\n"
				+ TextFormatting.RED + "[-] Removed New Welcome UI!\n"
				+ TextFormatting.GREEN + "[+] Added Changelog\n\n"
				+ TextFormatting.BLUE + "Diamond Caliber 2.1").split("\n");
		
		ScaledResolution sr = new ScaledResolution(this.mc);
		
		float headRot = mouseX;

        if(headRot <= -45)
        {
        	headRot = -45;
        }
        if(headRot >= 45)
        {
        	headRot = 45;
        }
		
		GuiRenderHelper.renderPlayer(width / 2 - 150,height / 2 + 155,150, headRot, new ItemStack(ModItems.MARINE_HELMET), new ItemStack(ModItems.MARINE_CHEST), ItemStack.EMPTY, new ItemStack(ModItems.MARINE_PANTS), new ItemStack(ModItems.FAL));
		
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
    	this.contBut = new GuiButton(0, (sr.getScaledWidth() / 2) - 60, 204, 120, 20, "Back");
    	this.buttonList.add(this.contBut);
    	super.initGui();
    }
    
    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	if(button == this.contBut)
    	{
    		this.mc.displayGuiScreen(new GuiCaliberMenu());
    	}
    	super.actionPerformed(button);
    }
    
    public static void renderCenteredText(final String text, final int posX, final int posY, final int color) {
        final Minecraft mc = Minecraft.getMinecraft();
        renderText(text, posX - mc.fontRenderer.getStringWidth(text) / 2, posY, color);
    }
}
