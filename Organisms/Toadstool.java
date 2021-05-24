package Organisms;

import World.*;

import java.util.ArrayList;

public class Toadstool extends Plant{
    public Toadstool (){
        super();
    }
    public Toadstool(Toadstool toadstool){
        super(toadstool);
    }
    public Toadstool (Position position){
        super(position);
    }
    public Toadstool (World world){
        super(world);
    }
    public Toadstool (Toadstool toadstool, Position position){
        super(toadstool, position);
    }
    public Toadstool (Toadstool toadstool, World world){
        super(toadstool, world);
    }
    public Toadstool (Position position, World world){
        super(position, world);
    }
    public Toadstool (Toadstool toadstool, Position position, World world){
        super(toadstool,position,world);
    }

    @Override
    public Organism clone(){
        return new Toadstool(this);
    }

    public ArrayList<Action> consequences(Organism attackingOrganism){
        ArrayList<Action> result = new ArrayList<>();

        if(getPower() > attackingOrganism.getPower()){
            result.add(new Action(ActionEnum.A_REMOVE, new Position(-1,-1),0, attackingOrganism));
        } else{
            result.add(new Action(ActionEnum.A_REMOVE, new Position(-1,-1),0 , this));
            result.add(new Action(ActionEnum.A_REMOVE, new Position(-1,-1),0 , attackingOrganism));
        }
        return result;
    }
    @Override
    public void initParams(){
        setPower(0);
        setInitiative(0);
        setLiveLength(10);
        setPowerToReproduce(5);
        setSign('T');
    }
}
