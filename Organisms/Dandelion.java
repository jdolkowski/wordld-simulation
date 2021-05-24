package Organisms;

import World.Position;
import World.World;

public class Dandelion extends Plant{
    public Dandelion (){
        super();
    }
    public Dandelion(Dandelion dandelion){
        super(dandelion);
    }
    public Dandelion (Position position){
        super(position);
    }
    public Dandelion (World world){
        super(world);
    }
    public Dandelion (Dandelion dandelion, Position position){
        super(dandelion, position);
    }
    public Dandelion (Dandelion dandelion, World world){
        super(dandelion, world);
    }
    public Dandelion (Position position, World world){
        super(position, world);
    }
    public Dandelion (Dandelion dandelion, Position position, World world){
        super(dandelion,position,world);
    }
    @Override
    public Organism clone(){
        return new Dandelion(this);
    }
    @Override
    public void initParams(){
        setPower(0);
        setInitiative(0);
        setLiveLength(6);
        setPowerToReproduce(2);
        setSign('D');
    }
}
