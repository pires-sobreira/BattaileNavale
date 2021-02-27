package ensta;

import ensta.InputHelper.CoordInput;
import ensta.InputHelper.ShipInput;
import ships.*;
import java.util.*;

public class TestGame 
{
    public static void main( String[] args )
    {      

        // Board board = new Board("nom", 10);
        // //Board opponentBoard = new Board("op", 10);

        // int[] coords = new int[2];
        // int shipsdestroyed = 0;

        // Hit hit;

        // char c;
        // int aux;

        // String msg;

        // Destroyer destroyer = new Destroyer();
        // Submarine submarine1 = new Submarine();
        // Submarine submarine2 = new Submarine();
        // BattleShip battleShip = new BattleShip();
        // AircraftCarrier aircraftCarrier = new AircraftCarrier();
        
        // List<AbstractShip> ships = new ArrayList<AbstractShip>();
        // ships.add(destroyer);
        // ships.add(submarine1);
        // ships.add(submarine2);
        // ships.add(battleShip);
        // ships.add(aircraftCarrier);

        // board.print();

        // BattleShipsAI battleAi = new BattleShipsAI(board, board);
        // //battleAi.putShips(ships);
        // battleAi.putShips(ships.toArray(new AbstractShip[ships.size()]));

        // do{
        //     hit = battleAi.sendHit(coords);

        //     msg = hit.toString();

        //     switch (hit.toString()) {
        //         case "Frégate":
        //             shipsdestroyed++;
        //             break;
        //         case "Sous-marin":
        //             shipsdestroyed++;
        //             break;
        //         case "Croiseur":
        //             shipsdestroyed++;
        //             break;  
        //         case "Porte-avion":
        //             shipsdestroyed++;
        //             break;   
        //         default:
        //             break;
        //     }

        //     aux = coords[1]+65;

        //     System.out.println("Frappe: " + hit + " / Coords: " + coords[0] + "," + (char)aux);
        //     board.print();

        //     sleep(1000);
        // }while(shipsdestroyed < 5);

        // aux = coords[1]+65;
        
        //ystem.out.println("Frappe: " + hit + " / Coords: " + coords[0] + "," + coords[1]);
        
        //Player player = new Player(board, board, ships);
        //player.putShips();
        //player.sendHit(coords);

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

    private static void sleep(int ms) {
        try {
        Thread.sleep(ms);
        } catch (InterruptedException e) {
        e.printStackTrace();
        }
    }
}
