package Organisms;

import World.*;

import java.util.ArrayList;
import java.util.Random;

public class Alien extends Animal{
    public boolean wasAlive = false;

    public Alien(World world){
        super(world);
    }

    public boolean getWasAlive() {
        return wasAlive;
    }

    public void setWasAlive(boolean alive) {
        wasAlive = alive;
    }

    public boolean willBePresent(){ //szanse 1/9
        Random rand = new Random();
        int randNumber = rand.nextInt(9);

        return randNumber < 1;
    }

    public void alienAction(Boolean willBePresent){
        if(willBePresent){
            if(!getWasAlive()){
                Random rand = new Random();
                int randX = rand.nextInt(getWorld().getWorldX());
                int randY = rand.nextInt(getWorld().getWorldY());
                Position pomPosition = new Position(randX, randY);
                setPosition(pomPosition);
            } else {
                moveByOneAlien();
            }
        }
    }

    public void moveByOneAlien(){
        ArrayList<Position> pomPositions = getNeighboringPositions();
        Position newPosition;

        Random rand = new Random();
        int randNumber = rand.nextInt(pomPositions.size());
        setPosition(pomPositions.get(randNumber));
    }
}
