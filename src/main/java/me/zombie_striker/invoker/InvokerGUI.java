package me.zombie_striker.invoker;

import me.zombie_striker.invoker.data.events.SpigotEventTypes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.List;

public class InvokerGUI extends Canvas implements MouseMotionListener, KeyListener, MouseListener {
    private boolean needsRepaint = true;
    private JFrame window;

    private int mousePosX = 0;
    private int mousePosY = 0;

    private int screenPosX = 0;
    private int screenPosY = 0;

    private int screenWidth;
    private int screenHeight;

    private boolean pressedMouse1 = false;
    private CodeContent dragged = null;
    private int draggedOffsetX = 0;
    private int draggedOffsetY = 0;

    private final ScreenImageManager screenImageManager;

    private final List<CodeContent> codeContentList = new LinkedList<>();

    private RightClickDropDown rightClickDropdown = null;
    private SelectorSelection currentSelection = null;

    public InvokerGUI() {
        screenImageManager = new ScreenImageManager(this);
        window = new JFrame("Invoker V1.0");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(1000, 1000);
        window.setVisible(true);
        window.add(this);

        window.addMouseListener(this);
        window.addKeyListener(this);
        window.addMouseMotionListener(this);

        this.addMouseListener(this);
        this.addKeyListener(this);
        this.addMouseMotionListener(this);
        window.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Component c = (java.awt.Component) e.getSource();
                screenWidth = c.getWidth();
                screenHeight = c.getHeight();
                needsRepaint();
            }
        });

        setBackground(new Color(80, 90, 140));


        Timer myTimer = new Timer(20, e -> {
            if (pressedMouse1) {
                needsRepaint();
                if (dragged != null) {
                    dragged.setX(mousePosX - draggedOffsetX);
                    dragged.setY(mousePosY - draggedOffsetY);
                } else {
                    int difx = mousePosX - draggedOffsetX;
                    int dify = mousePosY - draggedOffsetY;

                    draggedOffsetX = mousePosX;
                    draggedOffsetY = mousePosY;

                    screenPosX = screenPosX + difx;
                    screenPosY = screenPosY + dify;
                }
            }

            if(needsRepaint) {
                repaint();
                needsRepaint=false;
            }
        });
        myTimer.setRepeats(true);
        myTimer.start();
    }

    @Override
    public void paint(Graphics g) {
        screenImageManager.render((Graphics2D) g);
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.mousePosX = e.getX();
        this.mousePosY = e.getY();
        if(currentSelection!=null || rightClickDropdown!=null){
            needsRepaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    private void onClick(MouseEvent e) {
        needsRepaint();
        if (e.getButton() == MouseEvent.BUTTON1) {
            if (rightClickDropdown != null) {
                if (rightClickDropdown.handleClick(e)) {
                    pressedMouse1 = false;
                    dragged = null;
                    currentSelection=null;
                    rightClickDropdown = null;
                    return;
                }
            }

            if (currentSelection != null) {
                if (currentSelection.handleClick(e)) {
                    pressedMouse1 = false;
                    dragged = null;
                    currentSelection = null;
                    return;
                }
            }

            rightClickDropdown = null;
            if (!pressedMouse1) {
                pressedMouse1 = true;
                for (CodeContent codeContent : codeContentList) {
                    if (codeContent.getX() <= mousePosX - screenPosX && codeContent.getY() < mousePosY - screenPosY && codeContent.getY() + codeContent.getHeight() >= mousePosY - screenPosY && codeContent.getX() + codeContent.getWidth() >= mousePosX - screenPosX) {
                        if (codeContent.handleClick(e)) {
                            break;
                        }
                    }
                }
                if (dragged == null) {
                    draggedOffsetX = mousePosX;
                    draggedOffsetY = mousePosY;
                }
            } else {
                dragged = null;
                pressedMouse1 = false;
            }
        } else if (e.getButton()==MouseEvent.BUTTON3){
            rightClickDropdown = new RightClickDropDown(mousePosX, mousePosY, screenImageManager);
            needsRepaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        onClick(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public int getMousePosX() {
        return mousePosX;
    }

    public int getMousePosY() {
        return mousePosY;
    }

    public int getScreenPosX() {
        return screenPosX;
    }

    public int getScreenPosY() {
        return screenPosY;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public java.util.List<CodeContent> getCodeContentList() {
        return codeContentList;
    }

    public RightClickDropDown getRightClickDropdown() {
        return rightClickDropdown;
    }

    public void setDragged(CodeContent dragged) {
        this.dragged = dragged;
    }

    public void setDraggedOffsetX(int draggedOffsetX) {
        this.draggedOffsetX = draggedOffsetX;
    }

    public void setDraggedOffsetY(int draggedOffsetY) {
        this.draggedOffsetY = draggedOffsetY;
    }

    public CodeContent getDragged() {
        return dragged;
    }

    public int getDraggedOffsetX() {
        return draggedOffsetX;
    }

    public int getDraggedOffsetY() {
        return draggedOffsetY;
    }

    public void setPressedMouse1(boolean pressedMouse1) {
        this.pressedMouse1 = pressedMouse1;
    }

    public boolean isPressedMouse1() {
        return pressedMouse1;
    }

    public void openSelector(int xx, int yy, DropDownSelector dropDownSelectors, CodeContent codeContent) {
         currentSelection = new SelectorSelection(xx,yy,screenImageManager,dropDownSelectors,codeContent);
         if(dropDownSelectors.getType()==DropDownSelectorType.EVENTS) {
             for(SpigotEventTypes type : SpigotEventTypes.values()) {
                 currentSelection.addButton(new MenuButton(type.name()));
             }
         }
    }

    public SelectorSelection getSelectorSelection() {
        return currentSelection;
    }

    public void needsRepaint(){
        this.needsRepaint = true;
    }

    public void setCurrentSelection(SelectorSelection currentSelection) {
        this.currentSelection = currentSelection;
    }
}
