package com.ubalube.scifiaddon.client.gui;

import java.io.IOException;

import com.ubalube.scifiaddon.init.ModItems;
import com.ubalube.scifiaddon.items.ItemBase;
import com.ubalube.scifiaddon.items.ItemPaint;
import com.ubalube.scifiaddon.squads.SquadProvider;
import com.ubalube.scifiaddon.util.Reference;
import com.ubalube.scifiaddon.util.packets.ISquad;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TraderShop extends GuiScreen
{
	private EntityPlayer p;
	Minecraft mc = Minecraft.getMinecraft();
	
	private boolean error;
	private boolean bought;
	private boolean showInfo;
	private String boughtItem;
	private GuiButton exit;
	private GuiButton buy;
	private GuiButton buyI;
	private boolean buyInfo;
	private int messagefade;
	
	int g_width = 256;
	int g_height = 256;
	
	public TraderShop(EntityPlayer p)
	{
		this.p = p;
	}
	
	@Override
	public void initGui() {
		
		//- goes up | + goes down
		
		int y_offset = (height - this.g_height) / 2;
		this.buttonList.add(exit = new GuiButton(0, y_offset, this.height - (this.height / 4) - 0, "Close"));
		this.buttonList.add(buy = new GuiButton(0, y_offset, this.height - (this.height / 4) - 40, "Buy Crate"));
		this.buttonList.add(buyI = new GuiButton(0, y_offset + 200, this.height - (this.height / 4) - 40, 16, 16, "?"));
		super.initGui();
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	@Override
	public void onGuiClosed() {
		// TODO Auto-generated method stub
		super.onGuiClosed();
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		
		int offsetFromScreenLeft = (width - g_width) / 2;	
		int y = (this.height - g_height) / 2;
		
		
		
		this.drawDefaultBackground();
		
		if(this.error) {
			this.fontRenderer.drawString("You need money to buy this!", offsetFromScreenLeft + 202, y + 180, 16711680, true);
    	}
		
		if(this.bought)
		{
			this.fontRenderer.drawString("Successfully bought " + this.boughtItem + "!", offsetFromScreenLeft + 202, y + 180, 65280, true);
		}
		
		if(this.buyInfo)
		{
			//mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID + ":textures/gui/trader/guns/tavorinfo.png"));
			//this.drawModalRectWithCustomSizedTexture(offsetFromScreenLeft + (int)90.5F, y + 11, 0, 0, 80, 32, 80, 32);
			this.fontRenderer.drawString("Costs 5 Supplies", offsetFromScreenLeft + (int)90.5F, y + 2, 16777215, true);
			this.fontRenderer.drawString("This crate can drop:", offsetFromScreenLeft + (int)90.5F, y + 50, 16777215, true);
			this.fontRenderer.drawString("FAL", offsetFromScreenLeft + (int)90.5F, y + 60, 16777215, true);
			this.fontRenderer.drawString("UZI", offsetFromScreenLeft + (int)90.5F, y + 70, 16777215, true);
			this.fontRenderer.drawString("SCAR", offsetFromScreenLeft + (int)90.5F, y + 80, 16777215, true);
			this.fontRenderer.drawString("UZI", offsetFromScreenLeft + (int)90.5F, y + 90, 16777215, true);
			this.fontRenderer.drawString("Blue Skin", offsetFromScreenLeft + (int)90.5F, y + 100, 16777215, true);
		}
		
		
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	public void updateScreen() {
		//Loop
		if(error || bought) {
			messagefade++;
		}
		else
		{
			if(error && bought)
			{
				messagefade++;
			}
		}
		if(messagefade >= 100) {
			
			if(error)
			{
				error = false;
			}
			if(bought)
			{
				bought = false;
			}
			
			messagefade = 0;
		}
		super.updateScreen();
	}
	
	@Override
    protected void actionPerformed(GuiButton button) throws IOException {
        
		ISquad squad = mc.player.getCapability(SquadProvider.SQUAD, null);
		
		
		if (button == exit) {
            mc.player.closeScreen();
        }
		
		if(button == buyI)
		{
			buyInfo = !buyInfo;
		}
        
        if(button == buy)
        {
        	
        	ItemStack i = this.findCurrency(p, ModItems.SUPPLIES1);
        	if(!i.isEmpty())
        	{
        		if(i.getItem() == ModItems.SUPPLIES1)
        		{
        			if(this.getTargetStackSize(i) == 5)
        			{
        				i.shrink(5);
            			this.bought = true;
            			this.error = false;
            			this.boughtItem = "Supply Crate";
        			}
        			else
                	{
                		this.error = true;
                	}
        		}
        	}
        	else
        	{
        		this.error = true;
        	}
        }
        
    }
	
	protected boolean isCurrency(ItemStack stack, Item itemS)
	{
	   return stack.getItem() == itemS;
	}
	
	private ItemStack findCurrency(EntityPlayer p, Item itemS)
	{
		for(int i = 0; i < p.inventory.getSizeInventory(); ++i)
    	{
    		ItemStack item = p.inventory.getStackInSlot(i);
    		
    		if(this.isCurrency(item, itemS))
    		{
    			return item;
    		}
    		
    	}
		return ItemStack.EMPTY;
	}
	
	private int getTargetStackSize(ItemStack stack)
	{
		int i = stack.getCount();
		return i;
	}
	
	
}
