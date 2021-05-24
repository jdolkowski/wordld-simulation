package Organisms;

import World.Position;
import World.World;

import java.util.ArrayList;

public class Sheep extends Animal{
    public Sheep (){
        super();
    }
    public Sheep(Sheep sheep){
        super(sheep);
    }
    public Sheep (Position position){
        super(position);
    }
    public Sheep (World world){
        super(world);
    }
    public Sheep (Sheep sheep, Position position){
        super(sheep, position);
    }
    public Sheep (Sheep sheep, World world){
        super(sheep, world);
    }
    public Sheep (Position position, World world){
        super(position, world);
    }
    public Sheep (Sheep sheep, Position position, World world){
        super(sheep,position,world);
    }

    @Override
    public Organism clone(){
        return new Sheep(this);
    }

    @Override
    public void initParams(){
        setPower(3);
        setInitiative(3);
        setLiveLength(10);
        setPowerToReproduce(6);
        setSign('S');
    }

    public ArrayList<Position> getNeighboringPosition(){
        return getWorld().filterPositionsWithoutAnimals(getWorld().getNeighboringPositions(getPosition()));
    }
}
