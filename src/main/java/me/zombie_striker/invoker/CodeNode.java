package me.zombie_striker.invoker;

public class CodeNode {

    private CodeContent parent;
    private int index;

    private CodeNode connectedTo;

    public CodeNode(CodeContent parent, int index){
        this.parent = parent;
        this.index = index;
    }

    public CodeContent getParent() {
        return parent;
    }

    public int getIndex() {
        return index;
    }

    public CodeNode getConnectedTo() {
        return connectedTo;
    }

    public void setConnectedTo(CodeNode connectedTo) {
        this.connectedTo = connectedTo;
    }
}
