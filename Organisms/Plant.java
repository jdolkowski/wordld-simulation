package Organisms;

import World.Action;
import World.Position;
import World.World;
import World.ActionEnum;

import java.util.ArrayList;
import java.util.Random;

public class Plant extends Organism{

    public Plant (){
        super();
    }
    public Plant(Plant plant){
        super(plant);
    }
    public Plant (Position position){
        super(position);
    }
    public Plant (World world){
        super(world);
    }
    public Plant (Plant plant, Position position){
        super(plant, position);
    }
    public Plant (Plant plant, World world){
        super(plant, world);
    }
    public Plant (Position position, World world){
        super(position, world);
    }
    public Plant (Plant plant, Position position, World world){
        super(plant,position,world);
    }

    @Override
    public ArrayList<Action> action(){
        ArrayList<Action> result = new ArrayList<>();
        Organism newPlant;
        Position newPosition;

        if(ifReproduce()){
            ArrayList<Position> pomPositions = getFreeNeighboringPosition(getPosition());

            if(!pomPositions.isEmpty()){
                Random rand = new Random();
                int randNumber = rand.nextInt(pomPositions.size());
                newPosition = pomPositions.get(randNumber);
                newPlant = this.clone();
                newPlant.initParams();
                newPlant.setPosition(newPosition);
                setPower(getPower()/2);
                result.add(new Action(ActionEnum.A_ADD, newPosition, 0, newPlant));
            }
        }
        return result;
    }

    @Override
    public ArrayList<Action> move() {
        ArrayList<Action> result = new ArrayList<>();
        return result;
    }

    @Override
    public void initParams() {

    }

    @Override
    public Organism clone() {
        return new Plant(this.getWorld());
    }

    public ArrayList<Position> getFreeNeighboringPosition(Position position){
        return getWorld().filterFreePositions(getWorld().getNeighboringPositions(getPosition()));
    }
}
