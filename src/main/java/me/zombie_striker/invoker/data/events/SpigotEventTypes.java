package me.zombie_striker.invoker.data.events;

public enum SpigotEventTypes {

    MOVE_EVENT("PlayerMoveEvent"),
    JUMP_EVENT("PlayerJumpEvent"),
    SNEAK_EVENT("ToggleSneakEvent"),
    PLACE_BLOCK_EVENT("BlockPlaceEvent"),
    BREAK_BLOCK_EVENT("BlockBreakEvent"),
    ;

    private String s;

    SpigotEventTypes(String methodname){
        this.s = methodname;
    }
    public String getMethodName(){
        return s;
    }
}
