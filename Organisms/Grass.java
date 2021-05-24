package Organisms;

import World.Position;
import World.World;

public class Grass extends Plant{

    public Grass (){
        super();
    }
    public Grass(Grass grass){
        super(grass);
    }
    public Grass (Position position){
        super(position);
    }
    public Grass (World world){
        super(world);
    }
    public Grass (Grass grass, Position position){
        super(grass, position);
    }
    public Grass (Grass grass, World world){
        super(grass, world);
    }
    public Grass (Position position, World world){
        super(position, world);
    }
    public Grass (Grass grass, Position position, World world){
        super(grass,position,world);
    }

    @Override
    public Organism clone(){
        return new Grass(this);
    }

    @Override
    public void initParams(){
        setPower(0);
        setInitiative(0);
        setLiveLength(6);
        setPowerToReproduce(3);
        setSign('G');
    }
}
