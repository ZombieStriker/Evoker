package me.zombie_striker.invoker;

public class DropDownSelector {

    private CodeContent parent;
    private int index;
    private DropDownSelectorType type;
    private String selection=null;

    public DropDownSelector(DropDownSelectorType type, CodeContent parent, int index){
        this.type = type;
        this.index = index;
        this.parent = parent;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    public String getSelection() {
        return selection;
    }

    public int getIndex() {
        return index;
    }

    public CodeContent getParent() {
        return parent;
    }

    public DropDownSelectorType getType() {
        return type;
    }

}
