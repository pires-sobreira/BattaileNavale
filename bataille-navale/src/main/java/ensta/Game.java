package ensta;

import ships.*;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Game {

    /* ***
     * Constante
     */
    public static final File SAVE_FILE = new File("savegame.dat");

    /* ***
     * Attributs
     */
    private Player player1;
    private AIPlayer player2;
    private Scanner sin;
    private Scanner taille_board;
    private int taille_b;

    /* ***
     * Constructeurs
     */
    public Game() {}

    public Game init() {
        if (!loadSave()) {
            // init attributes
            System.out.println("Taille du board:");

            taille_board = new Scanner(System.in);

            taille_b = taille_board.nextInt();

            System.out.println("entre ton nom:");

            // TODO use a scanner to read player name
            this.sin = new Scanner(System.in);

            // TODO init boards
            Board b1 = new Board(sin.nextLine(), taille_b);
            Board b2 = new Board("AI", taille_b);;

            // TODO init this.player1 & this.player2
            List<AbstractShip> ships1 = Game.createDefaultShips();
            List<AbstractShip> ships2 = Game.createDefaultShips();

            this.player1 = new Player(b1, b2, ships1);
            this.player2 = new AIPlayer(b2, b1, ships2);

            b1.print(b2);
            // place player ships
            System.out.println("player 1 put ship inicio");
            player1.putShips();
            System.out.println("player 1 put ship concluido");
            System.out.println("player 2 put ship inicio");
            player2.putShips();
            System.out.println("player 2 put ship concluido");
        }
        return this;
    }

    /* ***
     * Méthodes
     */
    public void run() {
        int[] coords = new int[2];
        Board b1 = player1.board;
        Board b2 = player2.board;
        Hit hit;

        // main loop
        b1.print(b2);
        boolean done;
        do {
            hit = player1.sendHit(coords); // TODO player1 send a hit


            boolean strike = hit != Hit.MISS; //&& hit != null; // TODO set this hit on his board (b1)

            done = updateScore();
            b1.print(b2);
            System.out.println(makeHitMessage(false /* outgoing hit */, coords, hit));

            save();

            if (!done && !strike) {
                do {
                    hit = player2.sendHit(coords);; // TODO player2 send a hit.

                    strike = hit != Hit.MISS;
                    if (strike) {
                        b1.print(b2);
                    }
                    else{
                        b1.print(b2);
                    }
                    System.out.println(makeHitMessage(true /* incoming hit */, coords, hit));
                    done = updateScore();

                    if (!done) {
                        save();
                    }
                } while(strike && !done);
            }

        } while (!done);

        SAVE_FILE.delete();
        System.out.println(String.format("joueur %d gagne", player1.lose ? 2 : 1));
        sin.close();
    }


    private void save() {
        // try {
        //     // TODO bonus 2 : uncomment
        //     //  if (!SAVE_FILE.exists()) {
        //     //      SAVE_FILE.getAbsoluteFile().getParentFile().mkdirs();
        //     //  }

        //     // TODO bonus 2 : serialize players

        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
    }

    private boolean loadSave() {
        // if (SAVE_FILE.exists()) {
        //     try {
        //         // TODO bonus 2 : deserialize players

        //         return true;
        //     } catch (IOException | ClassNotFoundException e) {
        //         e.printStackTrace();
        //     }
        // }
        return false;
    }

    private boolean updateScore() {
        for (Player player : new Player[]{player1, player2}) {
            int destroyed = 0;
            for (AbstractShip ship : player.getShips()) {
                if (ship.isSunk()) {
                    destroyed++;
                }
            }

            player.destroyedCount = destroyed;
            player.lose = destroyed == player.getShips().length;
            if (player.lose) {
                return true;
            }
        }
        return false;
    }

    private String makeHitMessage(boolean incoming, int[] coords, Hit hit) {
        String msg;
        ColorUtil.Color color = ColorUtil.Color.RESET;
        switch (hit) {
            case MISS:
                msg = hit.toString();
                break;
            case STIKE:
                msg = hit.toString();
                color = ColorUtil.Color.RED;
                break;
            default:
                msg = hit.toString() + " coulé";
                color = ColorUtil.Color.RED;
        }
        msg = String.format("%s Frappe en %c%d : %s", incoming ? "<=" : "=>",
                ((char) ('A' + coords[1])),
                (coords[0]), msg);
        return ColorUtil.colorize(msg, color);
    }

    private static List<AbstractShip> createDefaultShips() {
        return Arrays.asList(new AbstractShip[]{new Destroyer(), new Submarine(), new Submarine(), new BattleShip(), new AircraftCarrier()});
    }

    // public static void main(String args[]) {
    //     new Game().init().run();
    // }
}
