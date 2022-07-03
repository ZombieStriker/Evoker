package me.zombie_striker.invoker;

public class MenuButton {

    private String text;
    private MenuButton parent;

    public MenuButton(String string){
        this.text = string;
    }

    public void setParent(MenuButton parent) {
        this.parent = parent;
    }

    public MenuButton getParent() {
        return parent;
    }

    public String getText() {
        return text;
    }
}
