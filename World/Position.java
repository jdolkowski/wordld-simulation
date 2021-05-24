package World;

import java.lang.Math;

import static java.lang.Math.pow;

public class Position {
    private int x = 0;
    private int y = 0;

    public Position(){}
    public Position(Position position){
        this(position.getX(), position.getY());
    }
    public Position (int x, int y){
        this.x = x;
        this.y = y;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    public Boolean equals(Position other){
        return (getX() == other.getX()) && (getY() == other.getY());
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

}
