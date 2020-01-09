package com.ubalube.scifiaddon.client.mainmenu.api;

import net.minecraft.client.gui.GuiButton;

import java.util.ArrayList;
import java.util.List;

import com.ubalube.scifiaddon.client.mainmenu.GuiCaliberMain;

public class GuiContainer {

    private final List<GuiButton> buttons = new ArrayList<>();
    public int containerID;
    public int posX;
    public int posY;
    public int width;
    public GuiCaliberMain parentGUI;
    public int height;
    protected String color = "0x000000";

    public GuiContainer(int containerID, int posX, int posY, int width, int height, GuiCaliberMain parentGUI) {
        this.containerID = containerID;
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.parentGUI = parentGUI;
    }

    public GuiContainer setColor(String color) {
        this.color = color;
        return this;
    }

    public void initGui() {
        buttons.clear();
    }

    public void parentActionPerformed(GuiButton guiButton) {
    }

    public void actionPerformed(GuiButton button) {
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (mouseButton == 0) {
            Buttons.click(buttons, mouseX, mouseY, this::actionPerformed);
        }
    }

    public void mouseReleased(int mouseX, int mouseY) {}

    public void handleScroll(int mouseX, int mouseY, int dWheel) {}

    public void updateScreen() {}

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawBackground();

        Buttons.draw(buttons, mouseX, mouseY, partialTicks);
    }

    public void drawBackground() { }

    public void onClose() {}
}
