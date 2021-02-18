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
            // TODO set ship orientation
            setOrientation(res.orientation, s);
            // TODO put ship at given position
            this.board.putShip(s, res.x, res.y);
            // TODO when ship placement successful
            if (board.hasShip(res.x, res.y)){
                ++i;
            }
            done = i == 5;

            board.print();
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
        boolean done = true;
        Hit hit = null;

        do {
            System.out.println("où frapper?");
            InputHelper.CoordInput hitInput = InputHelper.readCoordInput();
            // TODO call sendHit on this.opponentBoard

            // TODO : Game expects sendHit to return BOTH hit result & hit coords.
            // return hit is obvious. But how to return coords at the same time ?
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