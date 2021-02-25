package ships;

import ensta.*;
import java.util.*;

public class ShipState {
    protected AbstractShip ship;
    protected boolean struck;

    public ShipState(AbstractShip ship){
        this.ship = ship;
        this.struck = false;
    }

    public void addStrike(){
        if(!this.struck){
            this.ship.addStrike();
        }
        else{
            System.out.println("You have already hit this position");
        }
        this.struck = true;
    }

    public boolean isStruck(){
        return this.struck;
    }

    public boolean isSunk(){
        return this.ship.isSunk();
    }

    public AbstractShip getShip(){
        return this.ship;
    }

    public String toString(){
        return ColorUtil.colorize(this.ship.getLabel().toString(), ColorUtil.Color.RED);
    }
}
