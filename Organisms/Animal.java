package Organisms;

import World.Action;
import World.Position;
import World.World;
import World.ActionEnum;
import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.Random;

public class Animal extends Organism{
    private Position lastPosition;

    public Animal (){
        super();
    }
    public Animal(Animal grass){
        super(grass);
    }
    public Animal (Position position){
        super(position);
        this.lastPosition = position;
    }
    public Animal (World world){
        super(world);
    }
    public Animal (Animal animal, Position position){
        super(animal, position);
        this.lastPosition = position;
    }
    public Animal (Animal animal, World world){
        super(animal, world);
    }
    public Animal (Position position, World world){
        super(position, world);
        this.lastPosition = position;
    }
    public Animal (Animal animal, Position position, World world){
        super(animal,position,world);
        this.lastPosition = position;
    }

    public Position getLastPosition() {
        return lastPosition;
    }

    public void setLastPosition(Position lastPosition) {
        this.lastPosition = lastPosition;
    }

    public ArrayList<Position> getNeighboringPositions(){
        return getWorld().getNeighboringPositions(getPosition());
    }

    public ArrayList<Position> getNeighboringBirthPositions(){
        return getWorld().filterFreePositions(getWorld().getNeighboringPositions(getPosition()));
    }

    @Override
    public ArrayList<Action> move() {
        ArrayList<Action> result = new ArrayList<>();
        ArrayList<Position> pomPositions = getNeighboringPositions();
        Position newPosition;

        if(!pomPositions.isEmpty()){
            Random rand = new Random();
            int randNumber = rand.nextInt(pomPositions.size());
            newPosition = pomPositions.get(randNumber);
            result.add(new Action(ActionEnum.A_MOVE, newPosition, 0, this));
            setLastPosition(getPosition());
            Organism metOrganism = getWorld().getOrganismFromPosition(newPosition);
            if(metOrganism != null){
                result.addAll(metOrganism.consequences(this));
            }
        }
        return result;
    }


    @Override
    public ArrayList<Action> action() {
        ArrayList<Action> result = new ArrayList<>();
        Organism newAnimal;
        ArrayList<Position> birthPositions = getNeighboringBirthPositions();

        if(ifReproduce() && birthPositions != null){
            Random rand = new Random();
            int randNumber = rand.nextInt(birthPositions.size());
            Position newAnimalPosition = birthPositions.get(randNumber);
            newAnimal = this.clone();
            newAnimal.initParams();
            newAnimal.setPosition(newAnimalPosition);
            setPower(getPower()/2);
            result.add(new Action(ActionEnum.A_ADD, newAnimalPosition, 0, newAnimal));
        }
        return result;
    }

    @Override
    public void initParams(){};

    @Override
    public Organism clone() {
        return new Animal(this.getWorld());}
}
