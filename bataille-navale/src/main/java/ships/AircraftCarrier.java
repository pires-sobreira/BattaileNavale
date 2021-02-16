package ships;

//import ensta.*;

import ensta.Orientation;

public class AircraftCarrier extends AbstractShip{
    public AircraftCarrier(Orientation orientation){
        super("AircraftCarrier", 'C', 5, orientation);
    }

    public AircraftCarrier(){
        super("AircraftCarrier", 'C', 5, Orientation.EST);
    }
}