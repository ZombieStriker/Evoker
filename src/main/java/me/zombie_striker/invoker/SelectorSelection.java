package me.zombie_striker.invoker;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

public class SelectorSelection {

    private int x;
    private int y;
    private int width;
    private int height;
    private List<MenuButton> buttons = new LinkedList<>();
    private ScreenImageManager screenImageManager;
    private CodeContent content;
    private DropDownSelector selector;

    public SelectorSelection(int x, int y, ScreenImageManager screenImageManager, DropDownSelector selector, CodeContent codeContent) {
        this.x = x;
        this.y = y;
        this.width = 150;
        this.height = (buttons.size()+1) * 20;
        this.screenImageManager = screenImageManager;
        this.content = codeContent;
        this.selector = selector;
    }

    public void draw(Graphics2D g) {
        g.setFont(new Font("TimesRoman", Font.PLAIN, 11));

        g.setColor(new Color(100, 100, 100));
        g.fillRect(x+screenImageManager.getGui().getScreenPosX(), y+screenImageManager.getGui().getScreenPosY(), width, height);

        for (int i = 0; i <buttons.size();i++) {
            if(screenImageManager.gui.getMousePosX()>=x+screenImageManager.getGui().getScreenPosX()&&screenImageManager.getGui().getMousePosX()<=x+width+screenImageManager.getGui().getScreenHeight()&&
                    screenImageManager.gui.getMousePosY()>=y+screenImageManager.getGui().getScreenPosY()+(i*20)&&screenImageManager.getGui().getMousePosY()<=y+screenImageManager.getGui().getScreenPosY()+((i+1)*20)
            ){
                g.setColor(new Color(133, 145, 155));
                g.fillRect(x+screenImageManager.getGui().getScreenPosX(),y+screenImageManager.getGui().getScreenPosY()+(i*20),width,20);
            }
            g.setColor(new Color(180, 180, 180));
            MenuButton b = buttons.get(i);
            g.drawString(b.getText(), x+screenImageManager.getGui().getScreenPosX(), y+screenImageManager.getGui().getScreenPosY() + ((i+1)*20));
        }

    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public boolean handleClick(MouseEvent e) {
        if(e.getX()>=x+screenImageManager.getGui().getScreenPosX() && e.getX()<=x+width+screenImageManager.getGui().getScreenPosX() && e.getY() >= y+screenImageManager.getGui().getScreenPosY() && e.getY() <= y+height+screenImageManager.getGui().getScreenPosY()){
            int i = ((e.getY()-(y+screenImageManager.getGui().getScreenPosY()))/20);
            if(i < getButtons().size()) {
                selector.setSelection(getButtons().get(i).getText());
            }
            return true;
        }
        return false;
    }

    public List<MenuButton> getButtons() {
        return buttons;
    }
    public void addButton(MenuButton button){
        this.buttons.add(button);
        this.height = (buttons.size()+1) * 20;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public CodeContent getContent() {
        return content;
    }

    public DropDownSelector getSelector() {
        return selector;
    }
}
