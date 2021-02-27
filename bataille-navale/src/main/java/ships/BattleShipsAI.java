package ships;

import ensta.*;
import java.io.Serializable;
import java.util.*;

public class BattleShipsAI implements Serializable {

    /* **
     * Attributs
     */

    /**
     * grid size.
     */
    private final int size;

    /**
     * My board. My ships have to be put on this one.
     */
    private final IBoard board;

    /**
     * My opponent's board. My hits go on this one to strike his ships.
     */
    private final IBoard opponent;

    /**
     * Coords of last known strike. Would be a good idea to target next hits around this point.
     */
    private int lastStrike[];

    /**
     * If last known strike lead me to think the underlying ship has vertical placement.
     */
    private Boolean lastVertical;

    /* **
     * Constructeur
     */

    /**
     *
     * @param myBoard board where ships will be put.
     * @param opponentBoard Opponent's board, where hits will be sent.
     */
    public BattleShipsAI(IBoard myBoard, IBoard opponentBoard) {
        this.board = myBoard;
        this.opponent = opponentBoard;
        size = board.getSize();
    }

    /* **
     * Méthodes publiques
     */

    /**
     * Put the ships on owned board.
     * @param ships the ships to put
     */
    public void putShips(AbstractShip ships[]) {
        int x, y;
        int ori;
        //AbstractShip.Orientation o;
        Random rnd = new Random();
        //AbstractShip.Orientation[] orientations = AbstractShip.Orientation.values();

        for (AbstractShip s : ships) {
            do {
                // TODO use Random to pick a random x, y & orientation
                //aux = 
                x = rnd.nextInt(size);
                y = rnd.nextInt(size);
                ori = rnd.nextInt(4);
                switch (ori) {
                    case 0:
                        s.changeOrientation(Orientation.NORD);
                        break;
                    case 1:
                        s.changeOrientation(Orientation.SUD);
                        break;
                    case 2:
                        s.changeOrientation(Orientation.EST);
                        break;
                    case 3:
                        s.changeOrientation(Orientation.OUEST);
                        break;
                    default:
                        break;
                }
                System.out.println(s.getLabel() + " Orientantion: " + ori + " x: " + x + " y: " + y);
                System.out.println(canPutShip(s, x, y));
                
            } while(!canPutShip(s, x, y));
            
            board.putShip(s, x, y);
        }
    }

    /**
     *
     * @param coords array must be of size 2. Will hold the coord of the send hit.
     * @return the status of the hit.
     */
    public Hit sendHit(int[] coords) {
        int res[] = null;
        if (coords == null || coords.length < 2) {
            throw new IllegalArgumentException("must provide an initialized array of size 2");
        }

        // already found strike & orientation?
        if (lastVertical != null) {
            if (lastVertical) {
                res = pickVCoord();
            } else {
                res = pickHCoord();
            }

            if (res == null) {
                // no suitable coord found... forget last strike.
                lastStrike = null;
                lastVertical = null;
            }
        } else if (lastStrike != null) {
            // if already found a strike, without orientation
            // try to guess orientation
            res = pickVCoord();
            if (res == null) {
                res = pickHCoord();
            }
            if (res == null) {
                // no suitable coord found... forget last strike.
                lastStrike = null;
            }
        }

        if (lastStrike == null) {
            res = pickRandomCoord();
        }

        Hit hit = opponent.sendHit(res[0], res[1]);
        board.setHit(hit != Hit.MISS, res[0], res[1]);

        if (hit != Hit.MISS) {
            if (lastStrike != null) {
                lastVertical = guessOrientation(lastStrike, res);
            }
            lastStrike = res;
        }

        coords[0] = res[0];
        coords[1] = res[1];
        return hit;
    }

    /* ***
     * Méthodes privées
     */

    private boolean canPutShip(AbstractShip ship, int x, int y) {
        boolean setpos = true;
        try {
            switch (ship.getOrientation()) {
                case EST:
                    for (int i = y; i < y + ship.getTailleNavire(); i++){
                        System.out.println("x: " + x + " y: " + i + " has ship: " + board.hasShip(x, i));
                        if(board.hasShip(x, i)){
                            setpos = false;
                        }
                    }
                    break;
                case NORD:
                    for (int i = x; i > x - ship.getTailleNavire(); i--){
                        System.out.println("x: " + i + " y: " + y + " has ship: " + board.hasShip(i, y));
                        if(board.hasShip(i, y)){
                            setpos = false;
                        }
                    }     
                    break;
                case SUD:
                    for (int i = x; i < x + ship.getTailleNavire(); i++){
                        System.out.println("x: " + i + " y: " + y + " has ship: " + board.hasShip(i, y));
                        if(board.hasShip(i, y)){
                        setpos = false;
                        }
                    }
                    break;
        
                case OUEST:
                    for (int i = y; i > y - ship.getTailleNavire(); i--){
                        System.out.println("x: " + x + " y: " + i + " has ship: " + board.hasShip(x, i));
                        if(board.hasShip(x, i)){
                            setpos = false;
                        }
                    }
                    break;
                default:
                    break;
                }
        } catch (Exception e) {
            return false;
        }
        return setpos;
    }

    private boolean guessOrientation(int[] c1, int[] c2) {
        return c1[0] == c2[0];
    }

    private boolean isUndiscovered(int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size && board.getHit(x, y) == null;
    }

    private int[] pickRandomCoord() {
        Random rnd = new Random();
        int x;
        int y;

        do {
            x = rnd.nextInt(size);
            y = rnd.nextInt(size);
        } while (!isUndiscovered(x, y));

        return new int[] {x, y};
    }

    /**
     * pick a coord verically around last known strike
     * @return suitable coord, or null if none is suitable
     */
    private int[] pickVCoord() {
        int x = lastStrike[0];
        int y = lastStrike[1];

        for (int iy : new int[]{y - 1, y + 1}) {
            if (isUndiscovered(x, iy)) {
                return new int[]{x, iy};
            }
        }
        return null;
    }

    /**
     * pick a coord horizontally around last known strike
     * @return suitable coord, or null if none is suitable
     */
    private int[] pickHCoord() {
        int x = lastStrike[0];
        int y = lastStrike[1];

        for (int ix : new int[]{x - 1, x + 1}) {
            if (isUndiscovered(ix, y)) {
                return new int[]{ix, y};
            }
        }
        return null;
    }
}