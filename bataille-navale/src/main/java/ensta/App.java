package ensta;

import ships.*;

public class App 
{
    public static void main( String[] args )
    {
        Board board = new Board("nom", 10);
        Destroyer destroyer = new Destroyer();
        Submarine submarine = new Submarine(Orientation.EST);
        AircraftCarrier aircraftCarrier = new AircraftCarrier();
        BattleShip battleShip = new BattleShip();

        board.putShip(submarine, 1, 1);
        board.putShip(destroyer, 7, 0);
        board.putShip(aircraftCarrier, 2, 0);
        board.putShip(battleShip, 3, 5);

        board.putShip(submarine, 1, 2);
        board.putShip(aircraftCarrier, 9, 8);

        board.setHit(board.hasShip(7, 0), 7, 0);
        board.print();
    }
}
