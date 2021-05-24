package World;

import Organisms.*;

public class Main {
    public static void main(String[] args){
        World javaWorld = new World(20,10);

        Organism newOrg = new Grass(new Position(9,9), javaWorld);
        javaWorld.addOrganism(newOrg);

        newOrg = new Dandelion (new Position(1,3), javaWorld);
        javaWorld.addOrganism(newOrg);

        newOrg = new Dandelion (new Position(1,2), javaWorld);
        javaWorld.addOrganism(newOrg);

        newOrg = new Sheep(new Position(2,2), javaWorld);
        javaWorld.addOrganism(newOrg);

        newOrg = new Sheep(new Position(2,3), javaWorld);
        javaWorld.addOrganism(newOrg);

        newOrg = new Sheep(new Position(3,3), javaWorld);
        javaWorld.addOrganism(newOrg);

        newOrg = new Sheep(new Position(3,5), javaWorld);
        javaWorld.addOrganism(newOrg);

        newOrg = new Wolf(new Position(2,3), javaWorld);
        javaWorld.addOrganism(newOrg);

        newOrg = new Wolf(new Position(2,7), javaWorld);
        javaWorld.addOrganism(newOrg);

        newOrg = new Toadstool(new Position(4,4), javaWorld);
        javaWorld.addOrganism(newOrg);


        for(int i = 0; i < 20; i++){
            System.out.print(javaWorld);
            javaWorld.makeTurn();
            System.out.println();
        }
    }
}
