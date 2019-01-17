package com.ubalube.scifiaddon.client.gui;

import java.io.IOException;

import com.ubalube.scifiaddon.init.ModItems;
import com.ubalube.scifiaddon.items.GunAimableSkin;
import com.ubalube.scifiaddon.items.GunBase;
import com.ubalube.scifiaddon.items.ItemBase;
import com.ubalube.scifiaddon.items.ItemPaint;
import com.ubalube.scifiaddon.squads.SquadProvider;
import com.ubalube.scifiaddon.util.Reference;
import com.ubalube.scifiaddon.util.packets.ISquad;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.network.play.server.SPacketTitle;
import net.minecraft.util.CooldownTracker;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiLoadout extends GuiScreen
{
	private EntityPlayer p;
	Minecraft mc = Minecraft.getMinecraft();
	
	
	private GuiButton assault;
	private GuiButton recon;
	private GuiButton scout;
	private GuiButton engineer;
	
	String AssaultName = I18n.format("class.assault.name");
	String ScoutName = I18n.format("class.scout.name");
	String ReconName = I18n.format("class.recon.name");
	String EngineerName = I18n.format("class.engineer.name");
	
	int g_width = 256;
	int g_height = 256;
	
	public GuiLoadout(EntityPlayer p)
	{
		this.p = p;
	}
	
	@Override
	public void initGui() {
		
		//- goes up | + goes down
		
		int offsetFromScreenLeft = (width - g_width) / 2;	
		this.buttonList.add(assault = new GuiButton(0, offsetFromScreenLeft + (int)30F, this.height - (this.height / 4) - 80, AssaultName));
		this.buttonList.add(scout = new GuiButton(1, offsetFromScreenLeft + (int)30F, this.height - (this.height / 4) - 20, ScoutName));
		this.buttonList.add(recon = new GuiButton(2, offsetFromScreenLeft + (int)30F, this.height - (this.height / 4) - 40, ReconName));
		this.buttonList.add(engineer = new GuiButton(3, offsetFromScreenLeft + (int)30F, this.height - (this.height / 4) - 60, EngineerName));
		super.initGui();
	}
	
	@Override
	public boolean doesGuiPauseGame() 
	{
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
		
		this.fontRenderer.drawString("Select A Class", offsetFromScreenLeft + (int)90.5F, y + 2, 16777215, true);
		
		
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	public void updateScreen() {
		
		super.updateScreen();
	}
	
	public void setNBT(ItemStack item)
	{
		NBTTagCompound nbt = item.getTagCompound();
		
		CooldownTracker cd = p.getCooldownTracker();
		
		if(item.getItem() instanceof GunBase)
		{
			if (nbt == null) {
	            nbt = new NBTTagCompound();
	            item.setTagCompound(nbt);
	        }
			nbt.setBoolean("reload", false);
			nbt.setBoolean("ADS", false);
			
			cd.removeCooldown(item.getItem());
		}
	}
	
	public void displayClassMessage(String classname)
	{
		Minecraft mc = Minecraft.getMinecraft();
		//mc.ingameGUI.setOverlayMessage(new TextComponentString(TextFormatting.GREEN + classname + " selected"), false);
	}
	
	@Override
    protected void actionPerformed(GuiButton button) throws IOException {
        
		ISquad squad = mc.player.getCapability(SquadProvider.SQUAD, null);
		EntityPlayer player = this.p;
        
		ItemStack SMG = new ItemStack(ModItems.UZI);
		ItemStack PISTOL = new ItemStack(ModItems.GLOCK);
		ItemStack SNIPER = new ItemStack(ModItems.AWP);
		ItemStack CR4 = new ItemStack(ModItems.CR4);
		
        if(button == assault)
        {
        	
        	ItemStack Primary = new ItemStack(ModItems.SCAR);
        	ItemStack Secondary = new ItemStack(ModItems.GLOCK);
        	this.setNBT(Primary);
        	this.setNBT(Secondary);
        	
        	player.inventory.clear();
        	player.inventory.addItemStackToInventory(Primary);
        	player.inventory.addItemStackToInventory(Secondary);
        	player.inventory.addItemStackToInventory(new ItemStack(ModItems.MEDKIT, 3));
        	player.inventory.addItemStackToInventory(new ItemStack(ModItems.PILLS, 2));
        	
        	this.displayClassMessage(AssaultName);
        	
        	this.p.closeScreen();
        }
        
        if(button == scout)
        {
        	ItemStack Primary = new ItemStack(ModItems.UZI);
        	ItemStack Secondary = new ItemStack(ModItems.GLOCK);
        	this.setNBT(Primary);
        	this.setNBT(Secondary);
        	
        	player.inventory.clear();
        	player.inventory.addItemStackToInventory(Primary);
        	player.inventory.addItemStackToInventory(Secondary);
        	player.inventory.addItemStackToInventory(new ItemStack(ModItems.MEDKIT, 3));
        	player.inventory.addItemStackToInventory(new ItemStack(ModItems.PILLS, 2));
        	
        	this.displayClassMessage(ScoutName);
        	
        	this.p.closeScreen();
        }
        
        if(button == recon)
        {
        	ItemStack Primary = new ItemStack(ModItems.AWP);
        	ItemStack Secondary = new ItemStack(ModItems.GLOCK);
        	this.setNBT(Primary);
        	this.setNBT(Secondary);
        	
        	player.inventory.clear();
        	player.inventory.addItemStackToInventory(Primary);
        	player.inventory.addItemStackToInventory(Secondary);
        	player.inventory.addItemStackToInventory(new ItemStack(ModItems.MEDKIT, 3));
        	player.inventory.addItemStackToInventory(new ItemStack(ModItems.PILLS, 2));
        	
        	this.displayClassMessage(ReconName);
        	
        	this.p.closeScreen();
        }
        
        if(button == engineer)
        {
        	ItemStack Primary = new ItemStack(ModItems.CR4);
        	ItemStack Secondary = new ItemStack(ModItems.GLOCK);
        	this.setNBT(Primary);
        	this.setNBT(Secondary);
        	
        	player.inventory.clear();
        	player.inventory.addItemStackToInventory(Primary);
        	player.inventory.addItemStackToInventory(Secondary);
        	player.inventory.addItemStackToInventory(new ItemStack(ModItems.MEDKIT, 3));
        	player.inventory.addItemStackToInventory(new ItemStack(ModItems.BLITZSHIELD, 1));
        	
        	this.displayClassMessage(EngineerName);
        	
        	this.p.closeScreen();
        }
        
    }
	
	
}
