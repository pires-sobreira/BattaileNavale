package ensta;

import ensta.InputHelper.CoordInput;
import ensta.InputHelper.ShipInput;
import ships.*;
import java.util.*;

public class App 
{
    public static void main( String[] args )
    {      

        Board board = new Board("nom", 10);
        Board opponentBoard = new Board("op", 10);

        int[] coords = new int[2];

        Destroyer destroyer = new Destroyer();
        Submarine submarine1 = new Submarine();
        Submarine submarine2 = new Submarine();
        BattleShip battleShip = new BattleShip();
        AircraftCarrier aircraftCarrier = new AircraftCarrier();
        
        List<AbstractShip> ships = new ArrayList<AbstractShip>();
        ships.add(destroyer);
        ships.add(submarine1);
        ships.add(submarine2);
        ships.add(battleShip);
        ships.add(aircraftCarrier);

        board.print();

        Player player = new Player(board, board, ships);
        player.putShips();
        player.sendHit(coords);

        // System.out.println(board.sendHit(0,0));
        // System.out.println(board.sendHit(0,1));
        // System.out.println(board.sendHit(0,2));
        // System.out.println(board.sendHit(0,0));
        // board.putShip(submarine, 1, 1);
        // board.putShip(destroyer, 7, 0);
        // board.putShip(aircraftCarrier, 2, 0);
        // board.putShip(battleShip, 3, 5);

        // board.putShip(submarine, 1, 2);
        // board.putShip(aircraftCarrier, 9, 8);

        // board.setHit(board.hasShip(7, 0), 7, 0);
        // board.print();

        
    }
}
