package Organisms;
import World.*;

import java.util.ArrayList;


public abstract class Organism{
    private int power;
    private int initiative;
    private Position position;
    private int liveLength;
    private int powerToReproduce;
    private char sign;
    private World world;

    public Organism (){
        this(null, null, null);
    }
    public Organism (Organism organism){
        this(organism, null, null);
    }
    public Organism (Position position){
        this(null, position, null);
    }
    public Organism (World world){
        this(null, null, world);
    }
    public Organism (Organism organism, Position position){
        this(organism, position, null);
    }
    public Organism (Organism organism, World world){
        this(organism, null, world);
    }
    public Organism (Position position, World world){
        this(null, position, world);
    }

    public Organism(Organism organism, Position position, World world) {
        if(organism != null){
            this.power = organism.getPower();
            this.initiative = organism.getInitiative();
            this.position = organism.getPosition();
            this.liveLength = organism.getLiveLength();
            this.powerToReproduce = organism.getPowerToReproduce();
            this.sign = organism.getSign();
            this.world = organism.getWorld();
        } else {
            if(position != null){
                this.position = position;
            }
            if(world != null){
                this.world = world;
            }
            this.initParams();
        }
    }


    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getLiveLength() {
        return liveLength;
    }

    public void setLiveLength(int liveLength) {
        this.liveLength = liveLength;
    }

    public int getPowerToReproduce() {
        return powerToReproduce;
    }

    public void setPowerToReproduce(int powerToReproduce) {
        this.powerToReproduce = powerToReproduce;
    }

    public char getSign() {
        return sign;
    }

    public void setSign(char sign) {
        this.sign = sign;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }


    public ArrayList<Action> consequences(Organism attackingOrganism){
        ArrayList<Action> result = new ArrayList<>();
        if(getPower() > attackingOrganism.getPower()){
            result.add(new Action(ActionEnum.A_REMOVE, new Position(-1, -1), 0, attackingOrganism));
        } else{
            result.add(new Action(ActionEnum.A_REMOVE,new Position(-1,-1), 0, this));
        }
        return result;
    }

    public Boolean ifReproduce(){
        return getPower() >= getPowerToReproduce();
    }

    @Override
    public String toString() {
        return "Organism{" +
                "power=" + power +
                ", initiative=" + initiative +
                ", position=" + position +
                ", liveLength=" + liveLength +
                ", powerToReproduce=" + powerToReproduce +
                ", sign=" + sign +
                ", world=" + world +
                '}';
    }


    public abstract ArrayList<Action> move();
    public abstract ArrayList<Action> action();
    public abstract void initParams();
    public abstract Organism clone();
}
