package models.enums;

public enum Position {
    DEVELOPER(0),
    MANAGER(1),
    CEO(2),
    SELLER(3);

    private final int val;

    Position(int val) {
        this.val = val;
    }

    public int getVal(){
        return val;
    }
}
