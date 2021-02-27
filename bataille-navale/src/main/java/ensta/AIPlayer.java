package ensta;

import ships.*;
import java.io.Serializable;
import java.util.List;

public class AIPlayer extends Player {
    /* **
     * Attribut
     */
    private BattleShipsAI ai;

    /* **
     * Constructeur
     */
    public AIPlayer(Board ownBoard, Board opponentBoard, List<AbstractShip> ships) {
        super(ownBoard, opponentBoard, ships);
        ai = new BattleShipsAI(ownBoard, opponentBoard);
        System.out.println("AI player created");
    }

    protected void setai(){
        ai = new BattleShipsAI(this.board, this.opponentBoard);
        System.out.println("AI player created");
    }

    // TODO AIPlayer must not inherit "keyboard behavior" from player. Call ai instead.
    public void putShips(){
        //setai();
        System.out.println("entrou player 2 putships");
        ai.putShips(this.ships);
        System.out.println("terminou player 2 putships");
    }

    public Hit sendHit(int[] coords){
        Hit hit = ai.sendHit(coords);
        return hit;
    }
}
