package World;

import Organisms.Organism;

import java.util.HashMap;

public class Action {
    private ActionEnum action;
    private Position position;
    private int value;
    private Organism organism;

    public Action(ActionEnum action, Position position, int value, Organism organism) {
        this.action = action;
        this.position = position;
        this.value = value;
        this.organism = organism;
    }

    public ActionEnum getAction() {
        return action;
    }

    public Position getPosition() {
        return position;
    }

    public int getValue() {
        return value;
    }

    public Organism getOrganism() {
        return organism;
    }

    @Override
    public String toString() {
        String className = getOrganism().getClass().getSimpleName();
        HashMap<ActionEnum, String> choice = new HashMap<>();
        choice.put(ActionEnum.A_ADD, className + " add at " + getPosition());
        choice.put(ActionEnum.A_INCREASEPOWER, className + " increase power " + getValue());
        choice.put(ActionEnum.A_MOVE, className + " move from " + getOrganism().getPosition() + " to " + getPosition());
        choice.put(ActionEnum.A_REMOVE, className + " remove from " + getOrganism().getPosition());

        return choice.get(getAction());
    }
}
