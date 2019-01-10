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
	private boolean tavorinfo;
	private boolean bought;
	private String boughtItem;
	private GuiButton exit;
	private GuiButton buy;
	private GuiButton sell;
	private GuiButton tavorInfo;
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
		this.buttonList.add(buy = new GuiButton(0, y_offset, this.height - (this.height / 4) - 20, "Buy Tavor"));
		this.buttonList.add(tavorInfo = new GuiButton(0, y_offset + 200, this.height - (this.height / 4) - 20, 16, 16, "?"));
		this.buttonList.add(sell = new GuiButton(0, y_offset, this.height - (this.height / 4) - 40, "Sell Materials"));
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
		
		if(this.tavorinfo)
		{
			mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID + ":textures/gui/trader/guns/tavorinfo.png"));
			this.drawModalRectWithCustomSizedTexture(offsetFromScreenLeft + (int)90.5F, y + 11, 0, 0, 80, 32, 80, 32);
			this.fontRenderer.drawString("Costs 1 Supply", offsetFromScreenLeft + (int)90.5F, y + 2, 16777215, true);
			this.fontRenderer.drawString("TAVOR - Rifle", offsetFromScreenLeft + (int)90.5F, y + 50, 16777215, true);
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
		
		if(button == tavorInfo)
		{
			tavorinfo = !tavorinfo;
			
		}
		
		if(button == sell)
		{
			ItemStack i = this.findCurrency(p);
        	if(!i.isEmpty())
        	{
        		if(i.getItem() == ModItems.SUPPLIES1)
        		{
        			squad.setSquadRep(squad.getSquadRep() + 5);
        			i.shrink(1);
        			this.error = false;
        		}
        		
        		if(i.getItem() == ModItems.SUPPLIES2)
        		{
        			squad.setSquadRep(squad.getSquadRep() + 10);
        			i.shrink(1);
        			this.error = false;
        		}
        		
        	}
		}
        
        if(button == buy)
        {
        	
        	ItemStack i = this.findCurrency(p);
        	if(!i.isEmpty())
        	{
        		if(i.getItem() == ModItems.SUPPLIES1)
        		{
        			i.shrink(1);
        			p.inventory.addItemStackToInventory(new ItemStack(ModItems.TAVOR));
        			this.bought = true;
        			this.error = false;
        			this.boughtItem = "Tavor";
        		}
        	}
        	else
        	{
        		this.error = true;
        	}
        }
        
    }
	
	protected boolean isCurrency(ItemStack stack)
	{
	   return stack.getItem() instanceof ItemBase;
	}
	
	private ItemStack findCurrency(EntityPlayer p)
	{
		for(int i = 0; i < p.inventory.getSizeInventory(); ++i)
    	{
    		ItemStack item = p.inventory.getStackInSlot(i);
    		
    		
    		if(this.isCurrency(item))
    		{
    			return item;
    		}
    		
    	}
		return ItemStack.EMPTY;
	}
	
	
}
