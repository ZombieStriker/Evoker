package me.zombie_striker.invoker;

import java.awt.*;

public class ScreenImageManager {

    public InvokerGUI gui;

    public ScreenImageManager(InvokerGUI gui) {
        this.gui = gui;
    }

    public void render(Graphics2D g) {
        renderGrids(g);
        renderCodeBlocks(g);

        if(gui.getRightClickDropdown()!=null){
            gui.getRightClickDropdown().draw(g);
        }
        if(gui.getSelectorSelection()!=null){
            gui.getSelectorSelection().draw(g);
        }
    }

    private void renderCodeBlocks(Graphics2D g) {
        for (CodeContent cc : gui.getCodeContentList()) {
            cc.draw(g,gui.getScreenPosX(),gui.getScreenPosY());
        }
    }

    private void renderGrids(Graphics2D g) {
        for (int x = 0; x < (gui.getScreenWidth() / 100) + 1; x++) {
            g.drawLine((x * 100) + (gui.getScreenPosX()%100), 0, (x * 100) + (gui.getScreenPosX()%100), gui.getScreenHeight());
        }
        for (int y = 0; y < (gui.getScreenHeight() / 100) + 1; y++) {
            g.drawLine(0, (y * 100) + (gui.getScreenPosY()%100), gui.getScreenWidth(), (y * 100) + (gui.getScreenPosY()%100));
        }
    }

    public InvokerGUI getGui() {
        return gui;
    }
}
