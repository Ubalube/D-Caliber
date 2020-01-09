package com.ubalube.scifiaddon.client.mainmenu;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.lwjgl.opengl.GL11;

import com.mojang.authlib.GameProfile;
import com.ubalube.scifiaddon.client.mainmenu.utilities.GuiRenderHelper;
import com.ubalube.scifiaddon.init.ModItems;
import com.ubalube.scifiaddon.util.OperationManager;
import com.ubalube.scifiaddon.util.Reference;
import com.ubalube.scifiaddon.util.handlers.ConfigHandler;

import akka.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiScreenWorking;
import net.minecraft.client.gui.GuiWorldSelection;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.GameType;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldType;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.WorldInfo;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraft.world.storage.WorldSummary;
import net.minecraftforge.common.DimensionManager;
import scala.Array;
import scala.reflect.io.Path;

public class GuiOperations extends GuiScreen
{
	
	public GuiCaliberMenu mainMenu;
	private GuiButton contBut;
	
	private Map<GuiButton, File> buttonBinds = new HashMap<GuiButton, File>();
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		
		String text1 = String.valueOf(TextFormatting.YELLOW) + TextFormatting.BOLD + "Operations";
		
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
		
		GuiRenderHelper.renderPlayer(width / 2 - 150,height / 2 + 155,150, headRot, new ItemStack(ModItems.NVGOGGLES_t3), new ItemStack(ModItems.SEAL_CHEST), ItemStack.EMPTY, new ItemStack(ModItems.SEAL_PANTS), new ItemStack(ModItems.FAL));
		
		this.drawString(this.fontRenderer, text1, (sr.getScaledWidth() / 2) - (this.fontRenderer.getStringWidth(text1) / 2), 20, 0xFFFFFF);
		
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
    	
    	int lastButton = 1;
    	int lastY = height - 140;
    	
    	/*File dir = new File(mc.mcDataDir + "/operations");
    	
    	int fileCount = dir.listFiles().length;
    	
    	List<File> operations = new ArrayList<File>();
    	
    	for(int i = 0; i < fileCount; i++)
    	{
    		int yPos = lastY * i;
    		
    	}*/

		GuiButton b = new GuiButton(1, (sr.getScaledWidth() / 2) - 60, height - 140, 120, 20, "Operation Plane");
    	this.buttonList.add(b);
    	
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
    	
    	if(button.id == 1)
    	{
    		
    		String source = mc.mcDataDir.getAbsolutePath() + "/operations/Operation Plane/";
    		File sourceDir = new File(source);
    		
    		String saves = mc.mcDataDir.getAbsolutePath() + "/saves/Operation_Plane";
    		File saveDir = new File(saves);
    		
    		try
    		{
    			FileUtils.copyDirectory(sourceDir, saveDir);
    		}
    		catch(IOException e)
    		{
    			e.printStackTrace();
    		}
    		
    		WorldSettings settings = new WorldSettings(0L, GameType.ADVENTURE, false, true, WorldType.FLAT);
            this.mc.launchIntegratedServer("Operation_Plane", "Operation_Plane", settings);
    	}
    	
    	super.actionPerformed(button);
    }
    
    public static void renderCenteredText(final String text, final int posX, final int posY, final int color) {
        final Minecraft mc = Minecraft.getMinecraft();
        renderText(text, posX - mc.fontRenderer.getStringWidth(text) / 2, posY, color);
    }
}
