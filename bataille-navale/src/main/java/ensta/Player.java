package ensta;
import java.io.Serializable;
import java.util.List;
import ships.*;
import ensta.InputHelper.CoordInput;
import ensta.InputHelper.ShipInput;

public class Player {
    /* **
     * Attributs
     */
    protected Board board;
    protected Board opponentBoard;
    protected int destroyedCount;
    protected AbstractShip[] ships;
    protected boolean lose;

    /* **
     * Constructeur
     */
    public Player(Board board, Board opponentBoard, List<AbstractShip> ships) {
        this.board = board;
        this.ships = ships.toArray(new AbstractShip[0]);
        this.opponentBoard = opponentBoard;
        System.out.println("player created");
    }

    /* **
     * Méthodes
     */

    /**
     * Read keyboard input to get ships coordinates. Place ships on given coodrinates.
     */
    public void putShips() {
        boolean done = false;
        int i = 0;

        do {
            AbstractShip s = ships[i];
            String msg = String.format("placer %d : %s(%d)", i + 1, s.getNom(), s.getTailleNavire());
            System.out.println(msg);
            InputHelper.ShipInput res = InputHelper.readShipInput();
            if(!board.hasShip(res.x, res.y)){
                // TODO set ship orientation
                setOrientation(res.orientation, s);
                // TODO put ship at given position
                this.board.putShip(s, res.x, res.y);
                // TODO when ship placement successful
                if (board.hasShip(res.x, res.y)){
                    ++i;
                }
            }
            done = i == 5;

            board.print(this.opponentBoard);
        } while (!done);
    }

    protected void setOrientation(String ori, AbstractShip ship){
        switch (ori) {
            case "n":
                ship.changeOrientation(Orientation.NORD);
                break;
            case "s":
                ship.changeOrientation(Orientation.SUD);
                break;
            case "e":
                ship.changeOrientation(Orientation.EST);
                break;
            case "w":
                ship.changeOrientation(Orientation.OUEST);
                break;    
            default:
                break;
        }
    }

    public Hit sendHit(int[] coords) {
        boolean done = false;
        Hit hit = null;
        int ship_destroy = 0;

        do {
            System.out.println("où frapper?");
            InputHelper.CoordInput hitInput = InputHelper.readCoordInput();
            coords[0] = hitInput.x;
            coords[1] = hitInput.y;
            // TODO call sendHit on this.opponentBoard
            hit = this.opponentBoard.sendHit(coords[0], coords[1]);
            // TODO : Game expects sendHit to return BOTH hit result & hit coords.
            System.out.println("Frappe: " + hit + " / Coords: " + coords[0] + "," + coords[1]);
            switch (hit) {
                case DESTROYER:
                    board.setHit(true, coords[0], coords[1]);
                    ship_destroy++;
                    done = true;
                    break;
                case SUBMARINE:
                    board.setHit(true, coords[0], coords[1]);
                    ship_destroy++;
                    done = true;
                    break;
                case BATTLESHIP:
                    board.setHit(true, coords[0], coords[1]);
                    ship_destroy++;
                    done = true;
                    break;
                case CARRIER:
                    board.setHit(true, coords[0], coords[1]);
                    ship_destroy++;
                    done = true;
                    break;
                case STIKE:
                    board.setHit(true, coords[0], coords[1]);
                    done = true;
                    break;
                case MISS:
                    board.setHit(false, coords[0], coords[1]);
                    done = true;
                    break;
                default:
                    break;
            }
            // if(ship_destroy == 5){
            //     done = true;
            // }
            // return hit is obvious. But how to return coords at the same time ?
            this.board.print(this.opponentBoard);

        } while (!done);

        return hit;
    }

    public AbstractShip[] getShips() {
        return ships;
    }

    public void setShips(AbstractShip[] ships) {
        this.ships = ships;
    }
}
