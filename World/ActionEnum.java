package World;

public enum ActionEnum {
    A_MOVE(0),
    A_REMOVE(1),
    A_ADD(2),
    A_INCREASEPOWER(3);
    final int numVal;
    ActionEnum(int numVal){
        this.numVal = numVal;
    }
}
