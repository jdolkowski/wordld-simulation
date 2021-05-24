package Organisms;

import World.Position;
import World.World;

import java.util.ArrayList;

public class Wolf extends Animal{
    public Wolf (){
        super();
    }
    public Wolf(Wolf wolf){
        super(wolf);
    }
    public Wolf (Position position){
        super(position);
    }
    public Wolf (World world){
        super(world);
    }
    public Wolf (Wolf wolf, Position position){
        super(wolf, position);
    }
    public Wolf (Wolf wolf, World world){
        super(wolf, world);
    }
    public Wolf (Position position, World world){
        super(position, world);
    }
    public Wolf (Wolf wolf, Position position, World world){
        super(wolf,position,world);
    }

    @Override
    public Organism clone(){
        return new Wolf(this);
    }
    @Override
    public void initParams(){
        setPower(8);
        setInitiative(5);
        setLiveLength(20);
        setPowerToReproduce(16);
        setSign('W');
    }
    public ArrayList<Position> getNeighboringPositions(){
        return getWorld().filterPositionsWithOtherSpecies(getWorld().getNeighboringPositions(getPosition()),new Wolf());
    }
}
