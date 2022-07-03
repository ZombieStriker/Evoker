package me.zombie_striker.invoker;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

public class RightClickDropDown {

    private int x;
    private int y;
    private int width;
    private int height;
    private List<MenuButton> buttons = new LinkedList<>();
    private ScreenImageManager screenImageManager;

    public RightClickDropDown(int x, int y, ScreenImageManager screenImageManager) {
        this.x = x;
        this.y = y;
        this.width = 150;
        buttons.add(new MenuButton("Create EventListener"));
        buttons.add(new MenuButton("Create Variable"));
        buttons.add(new MenuButton("Create If-Statement"));
        buttons.add(new MenuButton("Create Method"));
        buttons.add(new MenuButton("Create Relay"));
        this.height = (buttons.size()+1) * 20;
        this.screenImageManager = screenImageManager;
    }

    public void draw(Graphics2D g) {
        g.setFont(new Font("TimesRoman", Font.PLAIN, 11));

        g.setColor(new Color(100, 100, 100));
        g.fillRect(x, y, width, height);

        for (int i = 0; i <buttons.size();i++) {
            if(screenImageManager.gui.getMousePosX()>=x&&screenImageManager.getGui().getMousePosX()<=x+width&&
                    screenImageManager.gui.getMousePosY()>=y+(i*20)&&screenImageManager.getGui().getMousePosY()<=y+((i+1)*20)
            ){
                g.setColor(new Color(133, 145, 155));
                g.fillRect(x,y+(i*20),width,20);
            }
            g.setColor(new Color(180, 180, 180));
            MenuButton b = buttons.get(i);
            g.drawString(b.getText(), x, y-3 + ((i+1)*20));
        }

    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public boolean handleClick(MouseEvent e) {
        if(e.getX()>x && e.getX()<x+width && e.getY() > y && e.getY() <= y+height){
            int i = ((e.getY()-y)/20);
            if(i==0){
                CodeContent content = new CodeContent(screenImageManager,CodeBlockType.EVENT,x-screenImageManager.getGui().getScreenPosX(),y-screenImageManager.getGui().getScreenPosY());
                screenImageManager.getGui().getCodeContentList().add(content);
            }else if(i==1){
                CodeContent content = new CodeContent(screenImageManager,CodeBlockType.VARIABLE,x-screenImageManager.getGui().getScreenPosX(),y-screenImageManager.getGui().getScreenPosY());
                screenImageManager.getGui().getCodeContentList().add(content);
            }else if(i==2){
                CodeContent content = new CodeContent(screenImageManager,CodeBlockType.IF,x-screenImageManager.getGui().getScreenPosX(),y-screenImageManager.getGui().getScreenPosY());
                screenImageManager.getGui().getCodeContentList().add(content);
            }else if(i==3){
                CodeContent content = new CodeContent(screenImageManager,CodeBlockType.METHOD,x-screenImageManager.getGui().getScreenPosX(),y-screenImageManager.getGui().getScreenPosY());
                screenImageManager.getGui().getCodeContentList().add(content);
            }else if(i==4){
                CodeContent content = new CodeContent(screenImageManager,CodeBlockType.RELAY,x-screenImageManager.getGui().getScreenPosX(),y-screenImageManager.getGui().getScreenPosY());
                screenImageManager.getGui().getCodeContentList().add(content);
            }
            return true;
        }
        return false;
    }
}
