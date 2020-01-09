package com.ubalube.scifiaddon.client.mainmenu;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import com.mojang.authlib.GameProfile;
import com.ubalube.scifiaddon.client.mainmenu.api.GuiButtonCaliber;
import com.ubalube.scifiaddon.client.mainmenu.utilities.GuiRenderHelper;
import com.ubalube.scifiaddon.client.mainmenu.utilities.ScissorState;
import com.ubalube.scifiaddon.util.Reference;
import com.ubalube.scifiaddon.util.handlers.ConfigHandler;
import com.ubalube.scifiaddon.util.handlers.GuiHandler;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiWorldSelection;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.GuiModList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuiCaliberMenu extends GuiCaliberMain {

	
	public GuiButtonCaliber updates;
	
	private int currentMenu;
	private int menuIndex = 1;
	
	public boolean displayUpdates;
	public String url;
	
	public float headRot;
	
    public GuiCaliberMenu(){
        super();
        setUiTitle(I18n.format("gui.title.mainmenu"));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX,mouseY,partialTicks);

        //DeadRenderHelper.renderRectWithOutline(width / 2 - 75,3,150,34,0x22FFFFFF,0x22FFFFFF,1);

    	
    	headRot = mouseX;

        if(headRot <= -45)
        {
        	headRot = -45;
        }
        if(headRot >= 45)
        {
        	headRot = 45;
        }
        
        GuiRenderHelper.renderCenteredTextWithShadow(I18n.format("gui.subtitle.status"),width / 2,0,0xFFFFFF);
        
        GuiRenderHelper.renderImageCentered(width / 2, 15, new ResourceLocation(Reference.MOD_ID,"textures/gui/logo.png"), 110,35);

        GlStateManager.pushMatrix();

        ScissorState.scissor(30, 0, width, height, true);

        if(menuIndex == 1)
    	{
        	this.updates.displayString = I18n.format("gui.button.updates");
        	this.updates.setImage(new ResourceLocation(Reference.MOD_ID,"textures/gui/menu/update.png"));
        	this.buttonList.get(8).enabled = false;
        	this.buttonList.get(9).enabled = true;
        	this.buttonList.get(10).enabled = true;
        	this.buttonList.get(11).enabled = true;
        	displayUpdates = true;
        	url = "";
    	}
    	else
    	{
    		if(menuIndex == 2)
    		{
            	this.updates.displayString = I18n.format("gui.button.announcement1");
            	this.updates.setImage(new ResourceLocation(Reference.MOD_ID,"textures/gui/menu/announcement1.png"));
            	this.buttonList.get(8).enabled = true;
            	this.buttonList.get(9).enabled = false;
            	this.buttonList.get(10).enabled = true;
            	this.buttonList.get(11).enabled = true;
            	displayUpdates = false;
            	url = Reference.LINK_ANNOUNCEMENT1;
    		}
    		else
    		{
    			if(menuIndex == 3)
    			{
                	this.updates.displayString = I18n.format("gui.button.announcement2");
                	this.updates.setImage(new ResourceLocation(Reference.MOD_ID,"textures/gui/menu/announcement2.png"));
                	this.buttonList.get(8).enabled = true;
                	this.buttonList.get(9).enabled = true;
                	this.buttonList.get(10).enabled = false;
                	this.buttonList.get(11).enabled = true;
                	displayUpdates = false;
                	url = Reference.LINK_ANNOUNCEMENT2;
    			}
    			else
    			{
        			if(menuIndex == 4)
        			{
                    	this.updates.displayString = I18n.format("gui.button.announcement3");
                    	this.updates.setImage(new ResourceLocation(Reference.MOD_ID,"textures/gui/menu/announcement3.png"));
                    	this.buttonList.get(8).enabled = true;
                    	this.buttonList.get(9).enabled = true;
                    	this.buttonList.get(10).enabled = true;
                    	this.buttonList.get(11).enabled = false;
                    	displayUpdates = false;
                    	url = Reference.LINK_ANNOUNCEMENT3;
        			}
    			}
    		}
    	}
        
        ItemStack head = ConfigHandler.ClientSide.HELMET;
        ItemStack chest = ConfigHandler.ClientSide.CHESTPLATE;
        ItemStack legs = ConfigHandler.ClientSide.LEGGINGS;
        ItemStack boots = ConfigHandler.ClientSide.BOOTS;
        ItemStack hand = ConfigHandler.ClientSide.lastMainItem;
        
        if(hand == null)
        {
        	hand = ItemStack.EMPTY;
        }
        else
        {
        	if(head == null)
        	{
        		head = ItemStack.EMPTY;
        	}
        	else
        	{
        		if(chest == null)
        		{
        			chest = ItemStack.EMPTY;
        		}
        		else
        		{
        			if(legs == null)
        			{
        				legs = ItemStack.EMPTY;
        			}
        			else
        			{
        				if(boots == null)
        				{
        					boots = ItemStack.EMPTY;
        				}
        			}
        		}
        	}
        }
        GuiRenderHelper.renderPlayer(width / 2 - 50,height / 2 + 155,150, headRot, head, chest, legs, boots, hand);
        

        ScissorState.endScissor();

        GlStateManager.popMatrix();
    }
    
    @Override
    public void updateScreen() {
    	
    	currentMenu = currentMenu + 1;
    	
    	if(currentMenu >= 100)
    	{
    		if(menuIndex >= 4)
    		{
    			menuIndex = 1;
    		}
    		else
    		{
        		menuIndex++;
    		}
    		currentMenu = 0;
    	}
    	
    	super.updateScreen();
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException
    {
        super.actionPerformed(button);

        switch(button.id){
            case BUTTON_LINK_DISCORD:
                this.openURL(Reference.LINK_DISCORD);
                break;
            case BUTTON_LINK_WEBSITE:
                this.openURL(Reference.LINK_WEBSITE);
                break;
            case BUTTON_SINGLEPLAYER:
                mc.displayGuiScreen(new GuiModList(this));
                break;
            case BUTTON_MULTIPLAYER:
                mc.displayGuiScreen(new GuiMultiplayer(this));
                break;
            case BUTTON_PLAY:
                mc.displayGuiScreen(new GuiWorldSelection(this));
                break;
            case BUTTON_SETTINGS:
                mc.displayGuiScreen(new GuiOptions(this,mc.gameSettings));
                break;
            case BUTTON_QUIT:
                //mc.shutdown();
                mc.displayGuiScreen(new GuiOperations());
                break;
            case BUTTON_UPDATES:
            	if(displayUpdates)
            	{
                    mc.displayGuiScreen(new GuiChangelog());
            	}
            	else
            	{
            		openURL(this.url);
            	}
            	break;
            case 211:
            	menuIndex = 1;
            	currentMenu = 0;
            	this.updates.displayString = I18n.format("gui.button.updates");
            	this.updates.setImage(new ResourceLocation(Reference.MOD_ID,"textures/gui/menu/update.png"));
            	this.buttonList.get(8).enabled = false;
            	this.buttonList.get(9).enabled = true;
            	this.buttonList.get(10).enabled = true;
            	this.buttonList.get(11).enabled = true;
            	displayUpdates = true;
            	url = "";
            	break;
            case 212:
            	menuIndex = 2;
            	currentMenu = 0;
            	this.updates.displayString = I18n.format("gui.button.announcement1");
            	this.updates.setImage(new ResourceLocation(Reference.MOD_ID,"textures/gui/menu/announcement1.png"));
            	this.buttonList.get(8).enabled = true;
            	this.buttonList.get(9).enabled = false;
            	this.buttonList.get(10).enabled = true;
            	this.buttonList.get(11).enabled = true;
            	displayUpdates = false;
            	url = Reference.LINK_ANNOUNCEMENT1;
            	break;
            case 213:
            	menuIndex = 3;
            	currentMenu = 0;
            	this.updates.displayString = I18n.format("gui.button.announcement2");
            	this.updates.setImage(new ResourceLocation(Reference.MOD_ID,"textures/gui/menu/announcement2.png"));
            	this.buttonList.get(8).enabled = true;
            	this.buttonList.get(9).enabled = true;
            	this.buttonList.get(10).enabled = false;
            	this.buttonList.get(11).enabled = true;
            	displayUpdates = false;
            	url = Reference.LINK_ANNOUNCEMENT2;
            	break;
            case 214:
            	menuIndex = 4;
            	currentMenu = 0;
            	this.updates.displayString = I18n.format("gui.button.announcement3");
            	this.updates.setImage(new ResourceLocation(Reference.MOD_ID,"textures/gui/menu/announcement3.png"));
            	this.buttonList.get(8).enabled = true;
            	this.buttonList.get(9).enabled = true;
            	this.buttonList.get(10).enabled = true;
            	this.buttonList.get(11).enabled = false;
            	displayUpdates = false;
            	url = Reference.LINK_ANNOUNCEMENT3;
            	break;
        }

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

    /**
     * Initialize GUI - Initialize the GUI
     */
    public void initGui() {

        this.buttonList.add(new GuiButtonCaliber(BUTTON_LINK_DISCORD,this.width - 83,3,80,15,I18n.format("gui.button.discord")));
        this.buttonList.add(new GuiButtonCaliber(BUTTON_LINK_WEBSITE,this.width - 83,22,80,15,I18n.format("gui.button.website"))
				.setDisabled(false));

        //this.buttonList.add(new GuiButtonCaliber(BUTTON_SETTINGS,this.width - 83,height - 37,80,15,I18n.format("gui.button.settings")));
        this.buttonList.add(new GuiButtonCaliber(BUTTON_PLAY,this.width - 140,this.height - 35,120,30,I18n.format("gui.button.play"))
                .setScale(2)
                .setYOffset(-3)
                .setImage(new ResourceLocation(Reference.MOD_ID,"textures/gui/menu/play.png")));

        this.buttonList.add(new GuiButtonCaliber(BUTTON_MULTIPLAYER,this.width - 140,height - 80,120,15,I18n.format("gui.button.multiplayer")).setImage(new ResourceLocation(Reference.MOD_ID,"textures/gui/menu/play.png")));
            this.buttonList.add(new GuiButtonCaliber(BUTTON_SINGLEPLAYER, this.width - 140, height - 100,120,15,I18n.format("gui.button.mods")).setImage(new ResourceLocation(Reference.MOD_ID,"textures/gui/menu/play.png")));
            this.buttonList.add(new GuiButtonCaliber(BUTTON_SETTINGS, this.width - 140, height - 120,120,15,I18n.format("gui.button.settings")).setImage(new ResourceLocation(Reference.MOD_ID,"textures/gui/menu/play.png")));
            this.buttonList.add(new GuiButtonCaliber(BUTTON_QUIT, this.width - 140, height - 140,120,15,I18n.format("gui.button.quit")).setImage(new ResourceLocation(Reference.MOD_ID,"textures/gui/menu/play.png")));
            
            
            this.updates = new GuiButtonCaliber(BUTTON_UPDATES, 7, height - 80,70,70,I18n.format("gui.button.updates")).setImage(new ResourceLocation(Reference.MOD_ID,"textures/gui/menu/update.png"));
            this.buttonList.add(this.updates);
            
            //The buttons that switch between the announcements
            this.buttonList.add(new GuiButtonCaliber(211, 10, height - 90,5,5,""));
            this.buttonList.add(new GuiButtonCaliber(212, 30, height - 90,5,5,""));
            this.buttonList.add(new GuiButtonCaliber(213, 50, height - 90,5,5,""));
            this.buttonList.add(new GuiButtonCaliber(214, 70, height - 90,5,5,""));
    }
}