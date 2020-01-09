package com.ubalube.scifiaddon.client.mainmenu.utilities;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import com.mojang.authlib.GameProfile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiRenderHelper
{
    public static float swing;
    public static final FakePlayerRendering PLAYER_RENDERER;
    
    public static void renderColor(final int par1, final double alpha) {
        final Color color = Color.decode("" + par1);
        final double red = color.getRed() / 255.0;
        final double green = color.getGreen() / 255.0;
        final double blue = color.getBlue() / 255.0;
        GL11.glColor4d(red, green, blue, alpha);
    }
    
    public static void renderText(final String text, final int posX, final int posY, final int color) {
        final Minecraft mc = Minecraft.getMinecraft();
        mc.fontRenderer.drawString(text, posX, posY, color);
    }
    
    public static void renderTextWithShadow(final String text, final int posX, final int posY, final int color) {
        final Minecraft mc = Minecraft.getMinecraft();
        mc.fontRenderer.drawStringWithShadow(text, (float)posX, (float)posY, color);
    }
    
    public static void renderCenteredText(final String text, final int posX, final int posY, final int color) {
        final Minecraft mc = Minecraft.getMinecraft();
        renderText(text, posX - mc.fontRenderer.getStringWidth(text) / 2, posY, color);
    }
    
    public static void renderCenteredTextWithShadow(final String text, final int posX, final int posY, final int color) {
        final Minecraft mc = Minecraft.getMinecraft();
        renderTextWithShadow(text, posX - mc.fontRenderer.getStringWidth(text) / 2, posY, color);
    }
    
    public static void renderTextScaled(final String text, final int posX, final int posY, final int color, final double givenScale) {
        GL11.glPushMatrix();
        GL11.glTranslated((double)posX, (double)posY, 0.0);
        GL11.glScaled(givenScale, givenScale, givenScale);
        renderText(text, 0, 0, color);
        GL11.glPopMatrix();
    }
    
    public static void renderCenteredTextScaled(final String text, final int posX, final int posY, final int color, final double givenScale) {
        GL11.glPushMatrix();
        GL11.glTranslated((double)posX, (double)posY, 0.0);
        GL11.glScaled(givenScale, givenScale, givenScale);
        renderCenteredText(text, 0, 0, color);
        GL11.glPopMatrix();
    }
    
    public static void renderCenteredTextScaledWithShadow(final String text, final int posX, final int posY, final int color, final double givenScale) {
        GL11.glPushMatrix();
        GL11.glTranslated((double)posX, (double)posY, 0.0);
        GL11.glScaled(givenScale, givenScale, givenScale);
        renderCenteredTextWithShadow(text, 0, 0, color);
        GL11.glPopMatrix();
    }
    
    public static void renderTextWithOutline(final String text, final int x, final int y, final int color, final int outlineColor) {
        renderText(text, x - 1, y + 1, outlineColor);
        renderText(text, x, y + 1, outlineColor);
        renderText(text, x + 1, y + 1, outlineColor);
        renderText(text, x - 1, y, outlineColor);
        renderText(text, x + 1, y, outlineColor);
        renderText(text, x - 1, y - 1, outlineColor);
        renderText(text, x, y - 1, outlineColor);
        renderText(text, x + 1, y - 1, outlineColor);
        renderText(text, x, y, color);
    }
    
    public static void renderTextScaledWithOutline(final String text, final int x, final int y, final int color, final int outlineColor, final double givenScale) {
        renderTextScaled(text, x - 1, y + 1, outlineColor, givenScale);
        renderTextScaled(text, x, y + 1, outlineColor, givenScale);
        renderTextScaled(text, x + 1, y + 1, outlineColor, givenScale);
        renderTextScaled(text, x - 1, y, outlineColor, givenScale);
        renderTextScaled(text, x + 1, y, outlineColor, givenScale);
        renderTextScaled(text, x - 1, y - 1, outlineColor, givenScale);
        renderTextScaled(text, x, y - 1, outlineColor, givenScale);
        renderTextScaled(text, x + 1, y - 1, outlineColor, givenScale);
        renderTextScaled(text, x, y, color, givenScale);
    }
    
    public static void renderCenteredTextWithOutline(final String text, final int x, final int y, final int color, final int outlineColor) {
        final Minecraft mc = Minecraft.getMinecraft();
        final FontRenderer fr = mc.fontRenderer;
        renderText(text, x - 1 - fr.getStringWidth(text) / 2, y + 1, outlineColor);
        renderText(text, x - fr.getStringWidth(text) / 2, y + 1, outlineColor);
        renderText(text, x + 1 - fr.getStringWidth(text) / 2, y + 1, outlineColor);
        renderText(text, x - 1 - fr.getStringWidth(text) / 2, y, outlineColor);
        renderText(text, x + 1 - fr.getStringWidth(text) / 2, y, outlineColor);
        renderText(text, x - 1 - fr.getStringWidth(text) / 2, y - 1, outlineColor);
        renderText(text, x - fr.getStringWidth(text) / 2, y - 1, outlineColor);
        renderText(text, x + 1 - fr.getStringWidth(text) / 2, y - 1, outlineColor);
        renderText(text, x - fr.getStringWidth(text) / 2, y, color);
    }
    
    public static void renderRect(int givenPosX, int givenPosY, int givenWidth, int givenHeight, final int givenColor) {
        GL11.glPushMatrix();
        givenWidth += givenPosX;
        givenHeight += givenPosY;
        if (givenPosX < givenWidth) {
            final int i = givenPosX;
            givenPosX = givenWidth;
            givenWidth = i;
        }
        if (givenPosY < givenHeight) {
            final int j = givenPosY;
            givenPosY = givenHeight;
            givenHeight = j;
        }
        final float f3 = (givenColor >> 24 & 0xFF) / 255.0f;
        final float f4 = (givenColor >> 16 & 0xFF) / 255.0f;
        final float f5 = (givenColor >> 8 & 0xFF) / 255.0f;
        final float f6 = (givenColor & 0xFF) / 255.0f;
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder bufferbuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.color(f4, f5, f6, f3);
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION);
        bufferbuilder.pos((double)givenPosX, (double)givenHeight, 0.0).endVertex();
        bufferbuilder.pos((double)givenWidth, (double)givenHeight, 0.0).endVertex();
        bufferbuilder.pos((double)givenWidth, (double)givenPosY, 0.0).endVertex();
        bufferbuilder.pos((double)givenPosX, (double)givenPosY, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GL11.glPopMatrix();
    }
    
    public static void renderRect(int givenPosX, int givenPosY, int givenWidth, int givenHeight, final int givenColor, final float givenAlpha) {
        givenWidth += givenPosX;
        givenHeight += givenPosY;
        if (givenPosX < givenWidth) {
            final int i = givenPosX;
            givenPosX = givenWidth;
            givenWidth = i;
        }
        if (givenPosY < givenHeight) {
            final int j = givenPosY;
            givenPosY = givenHeight;
            givenHeight = j;
        }
        final float f = (givenColor >> 16 & 0xFF) / 255.0f;
        final float f2 = (givenColor >> 8 & 0xFF) / 255.0f;
        final float f3 = (givenColor & 0xFF) / 255.0f;
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder bufferbuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(f, f2, f3, givenAlpha);
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION);
        bufferbuilder.pos((double)givenPosX, (double)givenHeight, 0.0).endVertex();
        bufferbuilder.pos((double)givenWidth, (double)givenHeight, 0.0).endVertex();
        bufferbuilder.pos((double)givenWidth, (double)givenPosY, 0.0).endVertex();
        bufferbuilder.pos((double)givenPosX, (double)givenPosY, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    public static void renderRectWithOutline(final int givenPosX, final int givenPosY, final int givenWidth, final int givenHeight, final int givenColor, final int givenOutlineColor, final int outlineThickness) {
        GL11.glPushMatrix();
        renderRect(givenPosX - outlineThickness, givenPosY - outlineThickness, givenWidth + outlineThickness * 2, givenHeight + outlineThickness * 2, givenOutlineColor);
        renderRect(givenPosX, givenPosY, givenWidth, givenHeight, givenColor);
        GL11.glPopMatrix();
    }
    
    public static void renderRectWithFade(int givenX, int givenY, int givenWidth, int givenHeight, final int givenColor, final float givenFade) {
        givenWidth += givenX;
        givenHeight += givenY;
        if (givenX < givenWidth) {
            final int i = givenX;
            givenX = givenWidth;
            givenWidth = i;
        }
        if (givenY < givenHeight) {
            final int j = givenY;
            givenY = givenHeight;
            givenHeight = j;
        }
        final float f3 = (givenColor >> 24 & 0xFF) / 255.0f;
        final float f4 = (givenColor >> 16 & 0xFF) / 255.0f;
        final float f5 = (givenColor >> 8 & 0xFF) / 255.0f;
        final float f6 = (givenColor & 0xFF) / 255.0f;
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder bufferbuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(f4, f5, f6, givenFade);
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION);
        bufferbuilder.pos((double)givenX, (double)givenHeight, 0.0).endVertex();
        bufferbuilder.pos((double)givenWidth, (double)givenHeight, 0.0).endVertex();
        bufferbuilder.pos((double)givenWidth, (double)givenY, 0.0).endVertex();
        bufferbuilder.pos((double)givenX, (double)givenY, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    public static void renderRectWithGradient(final int givenPosX, final int givenPosY, int givenWidth, int givenHeight, final int startColor, final int endColor, final double givenZLevel) {
        GL11.glPushMatrix();
        givenWidth += givenPosX;
        givenHeight += givenPosY;
        final float f = (startColor >> 24 & 0xFF) / 255.0f;
        final float f2 = (startColor >> 16 & 0xFF) / 255.0f;
        final float f3 = (startColor >> 8 & 0xFF) / 255.0f;
        final float f4 = (startColor & 0xFF) / 255.0f;
        final float f5 = (endColor >> 24 & 0xFF) / 255.0f;
        final float f6 = (endColor >> 16 & 0xFF) / 255.0f;
        final float f7 = (endColor >> 8 & 0xFF) / 255.0f;
        final float f8 = (endColor & 0xFF) / 255.0f;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos((double)givenWidth, (double)givenPosY, givenZLevel).color(f2, f3, f4, f).endVertex();
        bufferbuilder.pos((double)givenPosX, (double)givenPosY, givenZLevel).color(f2, f3, f4, f).endVertex();
        bufferbuilder.pos((double)givenPosX, (double)givenHeight, givenZLevel).color(f6, f7, f8, f5).endVertex();
        bufferbuilder.pos((double)givenWidth, (double)givenHeight, givenZLevel).color(f6, f7, f8, f5).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GL11.glPopMatrix();
    }
    
    public static void renderPositionedImageNoDepth(final ResourceLocation par1, final double par2, final double par3, final double par4, final float par5, final float width, final float height) {
        GL11.glPushMatrix();
        GL11.glDepthMask(false);
        GL11.glDisable(2929);
        renderPositionedImage(par1, par2, par3, par4, par5, width, height);
        GL11.glDepthMask(true);
        GL11.glEnable(2929);
        GL11.glPopMatrix();
    }
    
    public static void renderPositionedImage(final ResourceLocation par1, final double par2, final double par3, final double par4, final float par5, final float width, final float height) {
        final Minecraft mc = Minecraft.getMinecraft();
        final EntityPlayer player = (EntityPlayer)mc.player;
        GL11.glPushMatrix();
        GL11.glTranslated(par2, par3, par4);
        GL11.glTranslated(-mc.getRenderManager().viewerPosX, -mc.getRenderManager().viewerPosY, -mc.getRenderManager().viewerPosZ);
        GL11.glNormal3f(0.0f, 1.0f, 0.0f);
        GL11.glRotatef(-player.rotationYaw, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(player.rotationPitch, 1.0f, 0.0f, 0.0f);
        GL11.glScalef(-0.03f, -0.03f, 0.03f);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        renderImage(-width / 2.0f, -height / 2.0f, par1, width, height);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }
    
    public static void renderPositionedTextScaled(final String givenText, final double par2, final double par3, final double par4, final float par5, final int givenColor) {
        final Minecraft mc = Minecraft.getMinecraft();
        final EntityPlayer player = (EntityPlayer)mc.player;
        GL11.glPushMatrix();
        GL11.glTranslated(par2, par3, par4);
        GL11.glTranslated(-mc.getRenderManager().viewerPosX, -mc.getRenderManager().viewerPosY, -mc.getRenderManager().viewerPosZ);
        GL11.glNormal3f(0.0f, 1.0f, 0.0f);
        GL11.glRotatef(-player.rotationYaw, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(player.rotationPitch, 1.0f, 0.0f, 0.0f);
        GL11.glScalef(-0.03f, -0.03f, 0.03f);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        renderCenteredTextScaled(givenText, 0, 0, givenColor, par5);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }
    
    public static void renderImageCenteredScaled(final double givenX, final double givenY, final ResourceLocation givenTexture, final double givenWidth, final double givenHeight, final double givenScale) {
        GL11.glPushMatrix();
        GL11.glTranslated(givenX, givenY, 0.0);
        GL11.glScaled(givenScale, givenScale, givenScale);
        renderImageCentered(givenX - givenWidth / 2.0, givenY, givenTexture, givenWidth, givenHeight);
        GL11.glPopMatrix();
    }
    
    public static void renderImageCentered(final double givenX, final double givenY, final ResourceLocation givenTexture, final double givenWidth, final double givenHeight) {
        GL11.glPushMatrix();
        renderImage(givenX - givenWidth / 2.0, givenY, givenTexture, givenWidth, givenHeight);
        GL11.glPopMatrix();
    }
    
    public static void renderImageTransparent(final double x, final double y, final ResourceLocation image, final double width, final double height, final double alpha) {
        renderColor(16777215, alpha);
        Minecraft.getMinecraft().renderEngine.bindTexture(image);
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder bufferbuilder = tessellator.getBuffer();
        GL11.glEnable(3042);
        GL11.glEnable(2832);
        GL11.glHint(3153, 4353);
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos(x, y + height, 0.0).tex(0.0, 1.0).endVertex();
        bufferbuilder.pos(x + width, y + height, 0.0).tex(1.0, 1.0).endVertex();
        bufferbuilder.pos(x + width, y, 0.0).tex(1.0, 0.0).endVertex();
        bufferbuilder.pos(x, y, 0.0).tex(0.0, 0.0).endVertex();
        tessellator.draw();
        GL11.glDisable(3042);
        GL11.glDisable(2832);
    }
    
    public static void renderImageCenteredTransparent(final double givenX, final double givenY, final ResourceLocation givenTexture, final double givenWidth, final double givenHeight, final double alpha) {
        GlStateManager.pushMatrix();
        renderImageTransparent(givenX - givenWidth / 2.0, givenY - givenHeight / 2.0, givenTexture, givenWidth, givenHeight, alpha);
        GlStateManager.popMatrix();
    }
    
    public static void renderImage(final double x, final double y, final ResourceLocation image, final double width, final double height) {
        GL11.glColor3f(1.0f, 1.0f, 1.0f);
        Minecraft.getMinecraft().renderEngine.bindTexture(image);
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder bufferbuilder = tessellator.getBuffer();
        GL11.glEnable(3042);
        GL11.glEnable(2832);
        GL11.glHint(3153, 4353);
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos(x, y + height, 0.0).tex(0.0, 1.0).endVertex();
        bufferbuilder.pos(x + width, y + height, 0.0).tex(1.0, 1.0).endVertex();
        bufferbuilder.pos(x + width, y, 0.0).tex(1.0, 0.0).endVertex();
        bufferbuilder.pos(x, y, 0.0).tex(0.0, 0.0).endVertex();
        tessellator.draw();
        GL11.glDisable(3042);
        GL11.glDisable(2832);
    }
    
    public static void renderOtherPlayer(final GameProfile profile, final int x, final int y, final float givenScale, final float givenRotation, ItemStack helm, ItemStack chest, ItemStack legs, ItemStack boots, ItemStack held) {
        GL11.glPushMatrix();
        GuiRenderHelper.PLAYER_RENDERER.renderOtherPlayerModel(profile, x, y, givenScale, givenRotation, helm, chest, legs, boots, held);
        GL11.glPopMatrix();
    }
    
    public static void renderPlayer(final int x, final int y,final float givenScale, final float givenRotation, ItemStack helm, ItemStack chest, ItemStack legs, ItemStack boots, ItemStack held) {
        GL11.glPushMatrix();
        GuiRenderHelper.PLAYER_RENDERER.renderPlayerModel(x, y, givenScale, givenRotation, helm, chest, legs, boots, held);
        GL11.glPopMatrix();
    }
    
    static {
        GuiRenderHelper.swing = 0.0f;
        PLAYER_RENDERER = new FakePlayerRendering(Minecraft.getMinecraft().getSession().getProfile());
    }
}
