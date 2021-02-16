package ships;

import ensta.Orientation;

public class Destroyer extends AbstractShip{
    public Destroyer(Orientation orientation){
        super("Destroyer", 'D', 2, orientation);
    }

    public Destroyer(){
        super("Destroyer", 'D', 2, Orientation.EST);
    }
}
