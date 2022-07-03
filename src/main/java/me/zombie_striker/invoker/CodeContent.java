package me.zombie_striker.invoker;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

public class CodeContent {

    private int x;
    private int y;
    private int width;
    private int height;
    private CodeBlockType type;
    private ScreenImageManager screenImageManager;

    private List<DropDownSelector> selectors = new LinkedList<>();
    private List<CodeNode> nodes = new LinkedList<>();

    public CodeContent(ScreenImageManager screenImageManager, CodeBlockType type, int x, int y) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.width = type.getWidth();
        this.screenImageManager = screenImageManager;

        if (type == CodeBlockType.EVENT) {
            selectors.add(new DropDownSelector(DropDownSelectorType.EVENTS, this, 0));
        }


        this.height = type.getHeight() + (selectors.size() * 20) + (nodes.size() * 20);
    }

    public int getX() {
        return x;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getY() {
        return y;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void draw(Graphics2D g, int screenXOff, int screenYoff) {
        g.setColor(new Color(200, 200, 200));
        g.fillRect(x + screenXOff, y + screenYoff, width, height);
        g.setColor(new Color(150, 150, 150));
        g.fillRect(x + 3 + screenXOff, y + 3 + screenYoff, width - 6, height - 6);
        g.setColor(getType().getColor());
        g.fillRect(x + screenXOff, y + screenYoff, width, 20);
        g.setColor(new Color((int) (getType().getColor().getRed() * 0.5), (int) (getType().getColor().getGreen() * 0.5), (int) (getType().getColor().getBlue() * 0.5)));
        g.fillRect(x + 3 + screenXOff, y + 3 + screenYoff, width - 6, 14);

        g.setColor(new Color(200, 200, 200));
        g.drawString(type.name(), x + screenXOff + 5, y + screenYoff + 16);


        for (DropDownSelector selectors1 : selectors) {
            g.setColor(new Color(120, 120, 120));
            g.fillRect(x + screenXOff + 3, y + ((selectors1.getIndex() + 1) * 23) + 5 + screenYoff, width - 6, 18);
            if (selectors1.getSelection() == null) {
                selectors1.setSelection("NULL");
            }
            g.setColor(new Color(200, 200, 200));
            g.drawString(selectors1.getSelection().length() > 20 ? selectors1.getSelection().substring(0, 20) + "..." : selectors1.getSelection(), x + 5 + screenXOff, y + ((selectors1.getIndex() + 1) * 23) + 5 + screenYoff + 16);
        }
    }

    public CodeBlockType getType() {
        return type;
    }

    public boolean handleClick(MouseEvent e) {
        System.out.println(e.getX() + "   " + screenImageManager.getGui().getScreenPosX() + "  " + x);
        if (e.getX() - screenImageManager.getGui().getScreenPosX() >= x &&
                e.getX() - screenImageManager.getGui().getScreenPosX() <= x + width &&
                e.getY() - screenImageManager.getGui().getScreenPosY() >= y &&
                e.getY() - screenImageManager.getGui().getScreenPosY() <= y + height
        ) {
            if (e.getY() - screenImageManager.getGui().getScreenPosY() <= y + 30) {
                screenImageManager.getGui().setCurrentSelection(null);
                if (!screenImageManager.getGui().isPressedMouse1()) {
                    screenImageManager.getGui().setDragged(null);
                    screenImageManager.getGui().setPressedMouse1(false);
                } else {
                    screenImageManager.getGui().setDragged(this);
                    screenImageManager.getGui().setDraggedOffsetX(screenImageManager.getGui().getMousePosX() - getX());
                    screenImageManager.getGui().setDraggedOffsetY(screenImageManager.getGui().getMousePosY() - getY());
                }
            }else{
                int i = (e.getY()-screenImageManager.getGui().getScreenPosY()-y)/30;
                if(i > 0 && i < selectors.size()*30){
                    if(i-1 <= selectors.size()) {
                        DropDownSelector selector = selectors.get(i - 1);
                        screenImageManager.getGui().openSelector(x + width, y, selector, this);
                    }
                }
                screenImageManager.getGui().setDragged(null);
                screenImageManager.getGui().setPressedMouse1(false);
            }
            return true;
        }
        return false;
    }
}
