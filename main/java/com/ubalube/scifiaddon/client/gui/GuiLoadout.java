package com.ubalube.scifiaddon.client.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ubalube.scifiaddon.commands.util.Loadout;
import com.ubalube.scifiaddon.init.ModItems;
import com.ubalube.scifiaddon.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.WorldServer;
import net.minecraftforge.client.event.sound.SoundSetupEvent;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiLoadout extends GuiScreen
{
	EntityPlayer player;
	Minecraft mine = Minecraft.getMinecraft();
	ScaledResolution resolution = new ScaledResolution(mine);
    double screenHeight = resolution.getScaledHeight_double();
	
	public GuiLoadout(EntityPlayer p)
	{
		this.player = p;
	}
	
	Minecraft mc = Minecraft.getMinecraft();
	
	private GuiButton loadout1;
	private GuiButton loadout2;
	private GuiButton loadout3;
	private GuiButton loadout4;
	private Loadout loadout1_contents = new Loadout();
	private Loadout loadout2_contents = new Loadout();
	private Loadout loadout3_contents = new Loadout();
	private Loadout loadout4_contents = new Loadout();
	private GuiButton deploy;
	
	private boolean countdown;
	private int currentCountdown;
	
	private boolean countdown1, countdown2, countdown3, countdown4, countdown5;

	int PlayerPosX = 160;
	int PlayerPosY = 60;
	int PlayerScale = 30;
	
	int second;

	private boolean hovering1, hovering2, hovering3, hovering4;
	
	private Loadout selectedLoadout = new Loadout();
	
	int g_width = 256;
	int g_height = 256;
	
	@Override
	public void initGui() 
	{
		
		this.countdown5 = true;
		
		int y_offset = (this.height - height) / 2;
		loadout1_contents.Primary = new ItemStack(ModItems.M4A1, 1);
		loadout2_contents.Primary = new ItemStack(ModItems.AWP, 1);
		loadout3_contents.Primary = new ItemStack(ModItems.VECTOR, 1);
		loadout4_contents.Primary = new ItemStack(ModItems.FAL, 1);
		loadout1_contents.Secondary = new ItemStack(ModItems.GLOCK, 1);
		loadout2_contents.Secondary = new ItemStack(ModItems.UZI, 1);
		loadout3_contents.Secondary = new ItemStack(ModItems.GLOCK_SCOPED, 1);
		loadout4_contents.Secondary = new ItemStack(ModItems.GLOCK, 1);
		
		//Rifle man
		loadout1_contents.items.add(new ItemStack(ModItems.RIFLE56, 32));
		loadout1_contents.items.add(new ItemStack(ModItems.PISTOL9mm, 32));
		loadout1_contents.items.add(new ItemStack(ModItems.COMBAT_HELMET, 1));
		loadout1_contents.items.add(new ItemStack(ModItems.COMBAT_CHEST, 1));
		loadout1_contents.items.add(new ItemStack(ModItems.COMBAT_PANTS, 1));
		
		//Marksman
		loadout1_contents.items.add(new ItemStack(ModItems.SNIPERCLIP, 32));
		loadout1_contents.items.add(new ItemStack(ModItems.SMG45, 32));
		loadout1_contents.items.add(new ItemStack(ModItems.GHILLIE_HELMET, 1));
		loadout1_contents.items.add(new ItemStack(ModItems.GHILLIE_CHEST, 1));
		loadout1_contents.items.add(new ItemStack(ModItems.GHILLIE_PANTS, 1));
		
		//Scout
		loadout1_contents.items.add(new ItemStack(ModItems.SMG45, 32));
		loadout1_contents.items.add(new ItemStack(ModItems.PISTOL9mm, 32));
		loadout1_contents.items.add(new ItemStack(ModItems.RANGER_HELMET, 1));
		loadout1_contents.items.add(new ItemStack(ModItems.RANGER_CHEST, 1));
		loadout1_contents.items.add(new ItemStack(ModItems.RANGER_PANTS, 1));
		loadout1_contents.items.add(new ItemStack(ModItems.PILLS, 3));
		
		//Support
		loadout1_contents.items.add(new ItemStack(ModItems.RIFLE762, 32));
		loadout1_contents.items.add(new ItemStack(ModItems.PISTOL9mm, 32));
		loadout1_contents.items.add(new ItemStack(ModItems.GIGN_HELMET, 1));
		loadout1_contents.items.add(new ItemStack(ModItems.GIGN_CHEST, 1));
		loadout1_contents.items.add(new ItemStack(ModItems.GIGN_PANTS, 1));
		loadout1_contents.items.add(new ItemStack(ModItems.MEDKIT, 3));
		
		int offsetFromScreenLeft = (width - g_width) / 2;	
		this.buttonList.add(loadout1 = new GuiButton(0, offsetFromScreenLeft - (int) 90.0f, this.height - (this.height / 4) - 80, "Assault"));
		this.buttonList.add(loadout2 = new GuiButton(1, offsetFromScreenLeft - (int) 90.0f, this.height - (this.height / 4) - 20, "Marksman"));
		this.buttonList.add(loadout3 = new GuiButton(2, offsetFromScreenLeft - (int) 90.0f, this.height - (this.height / 4) - 40, "Scout"));
		this.buttonList.add(loadout4 = new GuiButton(3, offsetFromScreenLeft - (int) 90.0f, this.height - (this.height / 4) - 60, "Support"));
		this.buttonList.add(deploy = new GuiButton(4, offsetFromScreenLeft + (int)30F, this.height - (this.height / 4) + 20, "Deploy"));
		
		super.initGui();
	}
	
	public void drawMenuBackground(int tint)
	{
		GlStateManager.disableLighting();
        GlStateManager.disableFog();
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID + ":textures/gui/loadout/loadout_bg.png"));
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        float f = 32.0F;
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        bufferbuilder.pos(0.0D, (double)this.height, 0.0D).tex(0.0D, (double)((float)this.height / 32.0F + (float)tint)).color(64, 64, 64, 255).endVertex();
        bufferbuilder.pos((double)this.width, (double)this.height, 0.0D).tex((double)((float)this.width / 32.0F), (double)((float)this.height / 32.0F + (float)tint)).color(64, 64, 64, 255).endVertex();
        bufferbuilder.pos((double)this.width, 0.0D, 0.0D).tex((double)((float)this.width / 32.0F), (double)tint).color(64, 64, 64, 255).endVertex();
        bufferbuilder.pos(0.0D, 0.0D, 0.0D).tex(0.0D, (double)tint).color(64, 64, 64, 255).endVertex();
        tessellator.draw();
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		
		int offsetFromScreenLeft = (this.width - g_width) / 2;	
		int y = (this.height - g_height) / 2;
		int downAmount = 20;
		this.fontRenderer.drawString("Select A Class", offsetFromScreenLeft + (int)90.5F, y - 160, 16777215, true);
		this.drawMenuBackground(0);
		

		if(countdown)
		{
			loadout1.visible = false;
			loadout2.visible = false;
			loadout3.visible = false;
			loadout4.visible = false;
			deploy.visible = false;
			hovering1 = false;
			hovering2 = false;
			hovering3 = false;
			hovering4 = false;
			
			if(this.countdown5)
			{

				mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID + ":textures/gui/loadout/countdown_5.png"));
				this.drawModalRectWithCustomSizedTexture(offsetFromScreenLeft + (int)100F, y + 100, 0, 0, 64, 64, 64, 64);
				player.getEntityWorld().playSound(player, player.getPosition(), SoundEvents.BLOCK_NOTE_HAT, SoundCategory.MASTER, 1, 1);
			}
			else
			{
				if(this.countdown4)
				{

					mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID + ":textures/gui/loadout/countdown_4.png"));
					this.drawModalRectWithCustomSizedTexture(offsetFromScreenLeft + (int)100F, y + 100, 0, 0, 64, 64, 64, 64);
					player.getEntityWorld().playSound(player, player.getPosition(), SoundEvents.BLOCK_NOTE_HAT, SoundCategory.MASTER, 1, 1);
				}
				else
				{
					if(this.countdown3)
					{

						mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID + ":textures/gui/loadout/countdown_3.png"));
						this.drawModalRectWithCustomSizedTexture(offsetFromScreenLeft + (int)100F, y + 100, 0, 0, 64, 64, 64, 64);
						player.getEntityWorld().playSound(player, player.getPosition(), SoundEvents.BLOCK_NOTE_PLING, SoundCategory.MASTER, 1, 1);
					}
					else
					{
						if(this.countdown2)
						{

							mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID + ":textures/gui/loadout/countdown_2.png"));
							this.drawModalRectWithCustomSizedTexture(offsetFromScreenLeft + (int)100F, y + 100, 0, 0, 64, 64, 64, 64);
							player.getEntityWorld().playSound(player, player.getPosition(), SoundEvents.BLOCK_NOTE_PLING, SoundCategory.MASTER, 1, 1);
						}
						else
						{
							if(this.countdown1)
							{

								mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID + ":textures/gui/loadout/countdown_1.png"));
								this.drawModalRectWithCustomSizedTexture(offsetFromScreenLeft + (int)100F, y + 100, 0, 0, 64, 64, 64, 64);
								player.getEntityWorld().playSound(player, player.getPosition(), SoundEvents.BLOCK_NOTE_PLING, SoundCategory.MASTER, 1, 3);
							}
							else
							{
								if(!this.countdown1 && !this.countdown2 && !this.countdown3 && !this.countdown4 && !this.countdown5)
								{
									countdown = false;
									this.player.closeScreen();
									this.player.setInvisible(false);
								}
							}
						}
					}
				}
			}
			
			
			
			
			
			
		}
		
		if(hovering1)
		{
			
			this.fontRenderer.drawString("Primary", offsetFromScreenLeft + (int)160.5F, y + 110, 16777215, true);
			mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID + ":textures/gui/loadout/loadout_weapon.png"));
			this.drawModalRectWithCustomSizedTexture(offsetFromScreenLeft + (int)160.5F, y + 120, 0, 0, 64, 64, 64, 64);
			itemRender.renderItemAndEffectIntoGUI(loadout1_contents.Primary, offsetFromScreenLeft + (int)184F, y + 134);
			this.fontRenderer.drawString(loadout1_contents.Primary.getDisplayName(), offsetFromScreenLeft + (int)162.5F, y + 120 + 52, 16777215, true);
			

			this.fontRenderer.drawString("Secondary", offsetFromScreenLeft + (int)160.5F + (int)80F, y + 110, 16777215, true);
			mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID + ":textures/gui/loadout/loadout_weapon.png"));
			this.drawModalRectWithCustomSizedTexture(offsetFromScreenLeft + (int)160.5F + (int)80F, y + 120, 0, 0, 64, 64, 64, 64);
			itemRender.renderItemAndEffectIntoGUI(loadout1_contents.Secondary, offsetFromScreenLeft + (int)184F + (int)80F, y + 134);
			this.fontRenderer.drawString(loadout1_contents.Secondary.getDisplayName(), offsetFromScreenLeft + (int)160.5F + 82, y + 120 + 52, 16777215, true);
		}
		if(hovering2)
		{
			this.fontRenderer.drawString("Primary", offsetFromScreenLeft + (int)160.5F, y + 110, 16777215, true);
			mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID + ":textures/gui/loadout/loadout_weapon.png"));
			this.drawModalRectWithCustomSizedTexture(offsetFromScreenLeft + (int)160.5F, y + 120, 0, 0, 64, 64, 64, 64);
			itemRender.renderItemAndEffectIntoGUI(loadout2_contents.Primary, offsetFromScreenLeft + (int)184F, y + 134);
			this.fontRenderer.drawString(loadout2_contents.Primary.getDisplayName(), offsetFromScreenLeft + (int)162.5F, y + 120 + 52, 16777215, true);
			

			this.fontRenderer.drawString("Secondary", offsetFromScreenLeft + (int)160.5F + (int)80F, y + 110, 16777215, true);
			mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID + ":textures/gui/loadout/loadout_weapon.png"));
			this.drawModalRectWithCustomSizedTexture(offsetFromScreenLeft + (int)160.5F + (int)80F, y + 120, 0, 0, 64, 64, 64, 64);
			itemRender.renderItemAndEffectIntoGUI(loadout2_contents.Secondary, offsetFromScreenLeft + (int)184F + (int)80F, y + 134);
			this.fontRenderer.drawString(loadout2_contents.Secondary.getDisplayName(), offsetFromScreenLeft + (int)160.5F + 82, y + 120 + 52, 16777215, true);
		}
		if(hovering3)
		{
			
			this.fontRenderer.drawString("Primary", offsetFromScreenLeft + (int)160.5F, y + 110, 16777215, true);
			mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID + ":textures/gui/loadout/loadout_weapon.png"));
			this.drawModalRectWithCustomSizedTexture(offsetFromScreenLeft + (int)160.5F, y + 120, 0, 0, 64, 64, 64, 64);
			itemRender.renderItemAndEffectIntoGUI(loadout3_contents.Primary, offsetFromScreenLeft + (int)184F, y + 134);
			this.fontRenderer.drawString(loadout3_contents.Primary.getDisplayName(), offsetFromScreenLeft + (int)162.5F, y + 120 + 52, 16777215, true);
			

			this.fontRenderer.drawString("Secondary", offsetFromScreenLeft + (int)160.5F + (int)80F, y + 110, 16777215, true);
			mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID + ":textures/gui/loadout/loadout_weapon.png"));
			this.drawModalRectWithCustomSizedTexture(offsetFromScreenLeft + (int)160.5F + (int)80F, y + 120, 0, 0, 64, 64, 64, 64);
			itemRender.renderItemAndEffectIntoGUI(loadout3_contents.Secondary, offsetFromScreenLeft + (int)184F + (int)80F, y + 134);
			this.fontRenderer.drawString(loadout3_contents.Secondary.getDisplayName(), offsetFromScreenLeft + (int)160.5F + 82, y + 120 + 52, 16777215, true);
		}
		if(hovering4)
		{
			
			this.fontRenderer.drawString("Primary", offsetFromScreenLeft + (int)160.5F, y + 110, 16777215, true);
			mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID + ":textures/gui/loadout/loadout_weapon.png"));
			this.drawModalRectWithCustomSizedTexture(offsetFromScreenLeft + (int)160.5F, y + 120, 0, 0, 64, 64, 64, 64);
			itemRender.renderItemAndEffectIntoGUI(loadout4_contents.Primary, offsetFromScreenLeft + (int)184F, y + 134);
			this.fontRenderer.drawString(loadout4_contents.Primary.getDisplayName(), offsetFromScreenLeft + (int)162.5F, y + 120 + 52, 16777215, true);
			

			this.fontRenderer.drawString("Secondary", offsetFromScreenLeft + (int)160.5F + (int)80F, y + 110, 16777215, true);
			mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID + ":textures/gui/loadout/loadout_weapon.png"));
			this.drawModalRectWithCustomSizedTexture(offsetFromScreenLeft + (int)160.5F + (int)80F, y + 120, 0, 0, 64, 64, 64, 64);
			itemRender.renderItemAndEffectIntoGUI(loadout4_contents.Secondary, offsetFromScreenLeft + (int)184F + (int)80F, y + 134);
			this.fontRenderer.drawString(loadout4_contents.Secondary.getDisplayName(), offsetFromScreenLeft + (int)160.5F + 82, y + 120 + 52, 16777215, true);
		}
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	@Override
	public void updateScreen() {
		

		if(countdown)
		{
			
			if(countdown5)
			{
				second++;
				if(second >= 20)
				{
					countdown5 = false;
					countdown4 = true;
					second = 0;
				}
			}
			
			if(countdown4)
			{
				second++;
				if(second >= 20)
				{
					countdown4 = false;
					countdown3 = true;
					second = 0;
				}
			}
			
			if(countdown3)
			{
				second++;
				if(second >= 20)
				{
					countdown3 = false;
					countdown2 = true;
					second = 0;
				}
			}
			
			if(countdown2)
			{
				second++;
				if(second >= 20)
				{
					countdown2 = false;
					countdown1 = true;
					second = 0;
				}
			}
			
			if(countdown1)
			{
				second++;
				if(second >= 20)
				{
					countdown1 = false;
					second = 0;
				}
			}
			
		}
		
		if(loadout1.enabled || loadout2.enabled || loadout3.enabled || loadout4.enabled)
		{
			deploy.enabled = true;
		}
		else
		{
			deploy.enabled = false;
		}
		
		if(loadout1.isMouseOver())
		{
			hovering1 = true;
			hovering2 = false;
			hovering3 = false;
			hovering4 = false;
		}
		if(loadout2.isMouseOver())
		{
			hovering1 = false;
			hovering2 = true;
			hovering3 = false;
			hovering4 = false;
		}
		if(loadout3.isMouseOver())
		{
			hovering1 = false;
			hovering2 = false;
			hovering3 = true;
			hovering4 = false;
		}
		if(loadout4.isMouseOver())
		{
			hovering1 = false;
			hovering2 = false;
			hovering3 = false;
			hovering4 = true;
		}
		super.updateScreen();
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException 
	{
		
		if(button == deploy)
		{
			if(selectedLoadout != null)
			{
				player.inventory.clear();
				player.inventory.addItemStackToInventory(selectedLoadout.Primary);
				player.inventory.addItemStackToInventory(selectedLoadout.Secondary);
				for(ItemStack stack : selectedLoadout.items)
				{
					player.inventory.addItemStackToInventory(stack);
				}
				
				this.countdown = true;
			}
		}
		
		if(button == loadout1)
		{
			loadout4.enabled = true;
			loadout3.enabled = true;
			loadout2.enabled = true;
			loadout1.enabled = false;
			this.selectedLoadout = loadout1_contents;
		}
		
		if(button == loadout2)
		{
			loadout4.enabled = true;
			loadout3.enabled = true;
			loadout2.enabled = false;
			loadout1.enabled = true;
			this.selectedLoadout = loadout2_contents;
		}
		
		if(button == loadout3)
		{
			loadout4.enabled = true;
			loadout3.enabled = false;
			loadout2.enabled = true;
			loadout1.enabled = true;
			this.selectedLoadout = loadout3_contents;
		}
		
		if(button == loadout4)
		{
			loadout4.enabled = false;
			loadout3.enabled = true;
			loadout2.enabled = true;
			loadout1.enabled = true;
			this.selectedLoadout = loadout4_contents;
		}
		
		super.actionPerformed(button);
	}
	
}
