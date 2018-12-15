package com.ubalube.scifiaddon.client.gui;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.ubalube.scifiaddon.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FactionViewer extends GuiScreen
{
	private final int bookImageHeight = 192;
    private final int bookImageWidth = 192;
	private int currentScreen = 0;
	
	//Pages
	private static final int bookTotalPages = 4;
    private static ResourceLocation[] bookPageTextures = 
          new ResourceLocation[bookTotalPages];
    private static String[] stringPageText = new String[bookTotalPages];
	
	//Buttons
	private GuiButton buttonDone;
	private NextPageButton buttonNextPage;
    private NextPageButton buttonNextPageF;
    private NextPageButton buttonPreviousPage;
    
    public FactionViewer()
    {
        bookPageTextures[0] = new ResourceLocation(
              Reference.MOD_ID+":textures/gui/faction_milita.png");
        bookPageTextures[1] = new ResourceLocation(
        		Reference.MOD_ID +":textures/gui/faction_ghosts.png");
        bookPageTextures[2] = new ResourceLocation(
        		Reference.MOD_ID +":textures/gui/faction_scorpions.png");
        bookPageTextures[3] = new ResourceLocation(
        		Reference.MOD_ID +":textures/gui/faction_bandits.png");
        
        //Militia
        stringPageText[0] = "The Militia are the the lowest-armed hostiles. Equipped with only AK12's and low armor value. \nTheir only goal is causing trouble.";
        //Ghosts
        stringPageText[1] = "The Ghosts are the deadliest troops. \nOnly spawning during the night, equipped with silenced Scars with night-vision scopes, makes them un-noticable!";
        //Scorpions
        stringPageText[2] = "The most common hostiles, the scorpions are commonly found in deserts. They are said to have a lethal tank known as the Goliath.\nThey are equipped with Vectors and Combat Rifles.";
        //Bandits
        stringPageText[3] = "The bandits are barely considered an enemy. They're equipped with Machetes and have almost no armor at all.";
    }
    
    @Override
    public void initGui() 
    {
     // DEBUG
     System.out.println("GuiMysteriousStranger initGUI()");
        buttonList.clear();
        Keyboard.enableRepeatEvents(true);

        buttonDone = new GuiButton(0, width / 2 + 2, 4 + bookImageHeight, 
              98, 20, I18n.format("gui.done", new Object[0]));
  
        buttonList.add(buttonDone);
        int offsetFromScreenLeft = (width - bookImageWidth) / 2;
        buttonList.add(buttonNextPage = new NextPageButton(1, 
              offsetFromScreenLeft + 120, 156, true));
        buttonList.add(buttonPreviousPage = new NextPageButton(2, 
              offsetFromScreenLeft + 38, 156, false));
    }

    /**
     * Called from the main game loop to update the screen.
     */
    @Override
    public void updateScreen() 
    {
        buttonDone.visible = (currentScreen == bookTotalPages - 1);
        buttonNextPage.visible = (currentScreen < bookTotalPages - 1);
        buttonPreviousPage.visible = currentScreen > 0;
    }
    
    @Override
    public void drawScreen(int parWidth, int parHeight, float p_73863_3_)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        if (currentScreen == 0)
     {
         mc.getTextureManager().bindTexture(bookPageTextures[0]);
     }
        else
        {
         mc.getTextureManager().bindTexture(bookPageTextures[1]);
        }
        int offsetFromScreenLeft = (width - bookImageWidth ) / 2;
        drawTexturedModalRect(offsetFromScreenLeft, 2, 0, 0, bookImageWidth, 
        		bookImageHeight);
        int widthOfString;
        String stringPageIndicator = I18n.format("book.pageIndicator", 
              new Object[] {Integer.valueOf(currentScreen + 1), bookTotalPages});
        widthOfString = fontRenderer.getStringWidth(stringPageIndicator);
        fontRenderer.drawString(stringPageIndicator, 
              offsetFromScreenLeft - widthOfString + bookImageWidth - 44, 
              18, 0);
        fontRenderer.drawSplitString(stringPageText[currentScreen], 
              offsetFromScreenLeft + 36, 34, 116, 0);
        super.drawScreen(parWidth, parHeight, p_73863_3_);

    }
    
    @Override
    protected void mouseClickMove(int parMouseX, int parMouseY, 
          int parLastButtonClicked, long parTimeSinceMouseClick) 
    {
     
    }

    @Override
    protected void actionPerformed(GuiButton parButton) 
    {
     if (parButton == buttonDone)
     {
         // You can send a packet to server here if you need server to do 
         // something
         mc.displayGuiScreen((GuiScreen)null);
     }
        else if (parButton == buttonNextPage)
        {
            if (currentScreen < bookTotalPages - 1)
            {
                ++currentScreen;
            }
        }
        else if (parButton == buttonPreviousPage)
        {
            if (currentScreen > 0)
            {
                --currentScreen;
            }
        }
   }
    
    @Override
    public void onGuiClosed() 
    {
    	
    }

    /**
     * Returns true if this GUI should pause the game when it is displayed in 
     * single-player
     */
    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }
    
    @SideOnly(Side.CLIENT)
    static class NextPageButton extends GuiButton
    {
        private final boolean isNextButton;

        public NextPageButton(int parButtonId, int parPosX, int parPosY, 
              boolean parIsNextButton)
        {
            super(parButtonId, parPosX, parPosY, 23, 13, "");
            isNextButton = parIsNextButton;
        }

        /**
         * Draws this button to the screen.
         */
        public void drawButton(Minecraft mc, int parX, int parY)
        {
            if (visible)
            {
                boolean isButtonPressed = (parX >= x 
                      && parY >= y 
                      && parX < x + width 
                      && parY < y + height);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                mc.getTextureManager().bindTexture(bookPageTextures[1]);
                int textureX = 0;
                int textureY = 192;

                if (isButtonPressed)
                {
                    textureX += 23;
                }

                if (!isNextButton)
                {
                    textureY += 13;
                }

                drawTexturedModalRect(x, y, 
                      textureX, textureY, 
                      23, 13);
            }
        }
    }
    
}
