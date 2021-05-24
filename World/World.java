package World;

import Organisms.*;

import java.util.ArrayList;
import java.util.Random;

public class World {
    private final int worldX;
    private final int worldY;
    private int turn = 1;
    private ArrayList<Organism> organisms = new ArrayList<>();
    private ArrayList<Organism> newOrganisms = new ArrayList<>();
    private final char separator = '.';
    private final Alien alien = new Alien(this);


    public World(int worldX, int worldY) {
        this.worldX = worldX;
        this.worldY = worldY;

    }

    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public ArrayList<Organism> getOrganisms() {
        return organisms;
    }

    public ArrayList<Organism> getNewOrganisms() {
        return newOrganisms;
    }

    public void setOrganisms(ArrayList<Organism> organisms) {
        this.organisms = organisms;
    }

    public void setNewOrganisms(ArrayList<Organism> newOrganisms) {
        this.newOrganisms = newOrganisms;
    }

    public void appendNewOrganism(Organism newOrganism){
        this.newOrganisms.add(newOrganism);
    }

    public void appendOrganism(Organism organism){
        this.organisms.add(organism);
    }

    public char getSeparator() {
        return separator;
    }

    public boolean positionOnBoard(Position position){
        return position.getX() >= 0 && position.getY() >= 0 && position.getX() < this.getWorldX() && position.getY() < this.getWorldY();
    }

    public Alien getAlien() {
        return alien;
    }

    public void makeTurn(){
        ArrayList<Action> actions;

        boolean willBePresent = getAlien().willBePresent();

        getAlien().alienAction(willBePresent);

        for(Organism org : getOrganisms()){
            if(positionOnBoard(org.getPosition()) && !alienNearby(org.getPosition())){
                actions = org.move();
                for(Action a : actions){
                    makeMove(a);
                }
                actions.clear();
                if(positionOnBoard(org.getPosition()) && !alienNearby(org.getPosition())){
                    actions = org.action();
                    for(Action a : actions){
                        makeMove(a);
                    }
                    actions.clear();
                }
            }
        }

        //sprawdzanie czy organizmy sa na mapie
        getOrganisms().removeIf(o -> !positionOnBoard(o.getPosition()));


        for(Organism o : getOrganisms()){
            if(!alienNearby(o.getPosition())) {                   //dodawanie powera i odejmowanie zycia
                o.setLiveLength(o.getLiveLength() - 1);
                o.setPower(o.getPower() + 1);
                if (o.getLiveLength() < 1) {
                    System.out.println(o.getClass().getSimpleName() + ": died of old age at: " + o.getPosition().toString());
                }
            }
        }

        getOrganisms().removeIf(o -> o.getLiveLength() < 1);

        getNewOrganisms().removeIf(o -> !positionOnBoard(o.getPosition()));
        getOrganisms().addAll(getNewOrganisms());

        getOrganisms().sort(new InitiativeComparator());
        setTurn(getTurn()+1);

        getAlien().setWasAlive(willBePresent);
    }

    public void makeMove(Action action){
        System.out.println(action.toString());

        if(action.getAction() == ActionEnum.A_ADD){
            appendNewOrganism(action.getOrganism());
        } else if(action.getAction() == ActionEnum.A_INCREASEPOWER){
            action.getOrganism().setPower(action.getValue());
        } else if(action.getAction() == ActionEnum.A_MOVE){
            action.getOrganism().setPosition(action.getPosition());
        } else if(action.getAction() == ActionEnum.A_REMOVE){
            action.getOrganism().setPosition(new Position(-1,-1));
        }
    }

    public void addOrganism(Organism newOrganism){
        Position newOrgPosition = newOrganism.getPosition();

        if(positionOnBoard(newOrgPosition)){
            appendOrganism(newOrganism);
            getOrganisms().sort(new InitiativeComparator());
        }
    }

    public Organism getOrganismFromPosition(Position position){

        for(Organism org : this.getOrganisms()){
            if(org.getPosition().equals(position)){
                return org;
            }
        }

        for(Organism org : this.getNewOrganisms()){
            if(org.getPosition().equals(position)){
                return org;
            }
        }

        return null;
    }

    public ArrayList<Position> getNeighboringPositions(Position position){
        ArrayList<Position> result = new ArrayList<>();
        Position pomPosition;

        for(int y = -1; y < 2; y++){
            for(int x = -1; x < 2; x++){
                pomPosition = new Position(position.getX()+x,position.getY()+y);
                if(positionOnBoard(pomPosition) && !(y == 0 && x == 0)){
                    result.add(pomPosition);
                }
            }
        }
        return result;
    }

    public ArrayList<Position> filterFreePositions(ArrayList<Position> fields){
        ArrayList<Position> result = new ArrayList<>();

        for(Position field : fields){
            if(getOrganismFromPosition(field) == null){
                result.add(field);
            }
        }
        return result;
    }

    public ArrayList<Position> filterPositionsWithoutAnimals(ArrayList<Position> fields){
        ArrayList<Position> result = new ArrayList<>();
        Organism pomOrg;

        for(Position field : fields){
            pomOrg = getOrganismFromPosition(field);
            if(pomOrg == null || pomOrg.getClass().isInstance(new Plant())){
                result.add(field);
            }
        }
        return result;
    }

    public ArrayList<Position> filterPositionsWithOtherSpecies(ArrayList<Position> fields, Animal species){
        ArrayList<Position> result = new ArrayList<>();
        for(Position field : fields){
            Organism pomOrganism = getOrganismFromPosition(field);
            if(pomOrganism != null) {
                if (!pomOrganism.getClass().isInstance(species)) {
                    result.add(field);
                }
            }
        }
        return result;
    }
    public boolean alienNearby(Position position){
        for(int y = -2; y < 3; y++){
            for(int x = -2; x < 3; x++){
                Position pomPosition = new Position(position.getX()+x,position.getY()+y);
                if(getAlien().getPosition() != null){
                    if(positionOnBoard(pomPosition) && getAlien().getPosition().equals(pomPosition)){
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public String toString(){
        String result = "\nturn: " + getTurn() + "\n";
        for(int wY = 0; wY < getWorldY(); wY++){
            for(int wX = 0; wX < getWorldX(); wX++){
                Organism org = getOrganismFromPosition(new Position(wX,wY));
                if(org != null){
                    result += org.getSign();
                } else{
                    result += getSeparator();
                }
            }
            result += "\n";
        }
        return result;
    }
}
