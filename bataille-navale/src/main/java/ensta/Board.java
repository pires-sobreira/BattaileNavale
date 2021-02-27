package ensta;

import ships.*;

public class Board implements IBoard
{
    protected int taille = 10;
    protected String nom_tableau;
    protected String[] line_tableau;
    protected String line_header, line_tableau_indicator;
    protected ShipState[][] tableau_navires;
    protected char[] columns;
    protected Boolean[][] tableau_bool_frappes;

    

    public Board( String nom, int taille )
    {
        this.nom_tableau = nom;
        this.taille = taille;
        this.tableau_navires = new ShipState[taille][taille];
        this.tableau_bool_frappes = new Boolean[taille][taille];
        this.line_tableau = new String[taille];
        this.columns = new char[taille];
    }

    public Board( String nom )
    {
        this.nom_tableau = nom;
        this.taille = 10;
        this.tableau_navires = new ShipState[taille][taille];
        this.tableau_bool_frappes = new Boolean[taille][taille];
        this.line_tableau = new String[taille];
        this.columns = new char[taille];
    }

    protected void columnsName()
    {
        for(int i = 0; i < taille; i++){
            int ascii = 65 + i;
            columns[i] = (char)ascii;
        }
    }

    protected void setTableauIndicator()
    {
        line_tableau_indicator = "Navire:";
        for(int i = 0; i < 2*taille; i++){
            line_tableau_indicator += " ";
        }
        line_tableau_indicator += "Frappes:";

        System.out.println(line_tableau_indicator);
    }

    protected void setTableauHeader()
    {
        columnsName();
        line_header = " ";
        for (int i = 0; i < taille; i++){
            line_header += " " + columns[i];
        }
        line_header += "       ";

        for (int i = 0; i < taille; i++){
            line_header += " " + columns[i];
        }

        System.out.println(line_header);
    }

    protected Boolean[][] getFrappes(){
        return this.tableau_bool_frappes;
    }

    protected void PrintTableau()
    {
        for (int i = 0; i < taille; i++){
            System.out.print(i + " ");
            for (int j = 0; j < taille; j++){
                //System.out.print(tableau_char_navires[i][j] + " ");
                if(this.tableau_navires[i][j] == null) 
                        System.out.print(". ");
                else {
                    if (this.getFrappes()[i][j] == null)
                        System.out.print(this.tableau_navires[i][j].getShip().getLabel() + " ");
                    else
                        System.out.print(ColorUtil.colorize(this.tableau_navires[i][j].getShip().getLabel() + " ", ColorUtil.Color.RED));
                }
            }
            System.out.print("     ");
            System.out.print(i + " ");
            for (int k = 0; k < taille; k++){
                if(tableau_bool_frappes[i][k] == null)
                    System.out.print(". ");
                
                else if(tableau_bool_frappes[i][k] == true)
                    System.out.print(ColorUtil.colorize("X ", ColorUtil.Color.RED));
                
                else 
                    System.out.print(ColorUtil.colorize("X ", ColorUtil.Color.WHITE));
            }
            System.out.println("");
        }
    }

    public void print()
    {
        setTableauIndicator();
        setTableauHeader();
        
        
        PrintTableau(); 
        System.out.println("\n");
    }


    public int getSize(){
        return this.taille;
    }
    
    public void putShip(AbstractShip ship, int x, int y){
        boolean setpos = true;
        try {
            switch (ship.getOrientation()) {
                case EST:
                for (int i = y; i < y + ship.getTailleNavire(); i++){
                    if(hasShip(x, i)){
                        setpos = false;
                    }
                }
                if(setpos){
                    for (int i = y; i < y + ship.getTailleNavire(); i++){
                        tableau_navires[x][i] = new ShipState(ship);
                        tableau_navires[x][i].getShip().setLabel(ship.getLabel());
                    }
                }
                else{
                    System.out.println("Invalide position! Coliding Ship");
                }
                    break;
                case NORD:
                for (int i = x; i > x - ship.getTailleNavire(); i--){
                    if(hasShip(i, y)){
                        setpos = false;
                    }
                }
                if(setpos){
                    for (int i = x; i > x - ship.getTailleNavire(); i--){
                        tableau_navires[i][y] = new ShipState(ship);
                        tableau_navires[i][y].getShip().setLabel(ship.getLabel());
                    }
                }
                else{
                    System.out.println("Invalide position! Coliding Ship");
                }       
                    break;
                case SUD:
                for (int i = x; i < x + ship.getTailleNavire(); i++){
                    if(hasShip(i, y)){
                        setpos = false;
                    }
                }
                if(setpos){
                    for (int i = x; i < x + ship.getTailleNavire(); i++){
                        //tableau_char_navires[i][y] = ship.getLabel();
                        tableau_navires[i][y] = new ShipState(ship);
                        tableau_navires[i][y].getShip().setLabel(ship.getLabel());
                    }
                }
                else{
                    System.out.println("Invalide position! Coliding Ship");
                }
                    break;
        
                case OUEST:
                    for (int i = y; i > y - ship.getTailleNavire(); i--){
                        if(hasShip(x, i)){
                            setpos = false;
                        }
                    }
                    if(setpos){
                        for (int i = y; i > y - ship.getTailleNavire(); i--){
                            //tableau_char_navires[x][i] = ship.getLabel();
                            tableau_navires[x][i] = new ShipState(ship);
                            tableau_navires[x][i].getShip().setLabel(ship.getLabel());
                        }
                    }
                    else{
                        System.out.println("Invalide position! Coliding Ship");
                    }
                    break;
                default:
                    break;
                }
        } catch (Exception e) {
            System.out.println("invalid position! out of the board");
        }
        
    }
    

    public boolean hasShip(int x, int y){
        if(tableau_navires[x][y] != null){
            return true;
        }
        else return false;
    }

    public void setHit(boolean hit, int x, int y){
        if(hit){
            tableau_bool_frappes[x][y] = true;
        }
    }

    public Boolean getHit(int x, int y){
        return tableau_bool_frappes[x][y];
    }

    public Hit sendHit(int x, int y){
        if(hasShip(x, y)){
            if(!tableau_navires[x][y].isStruck()){
                tableau_bool_frappes[x][y] = true;
                tableau_navires[x][y].addStrike();
                int aux = -1;
                if(tableau_navires[x][y].isSunk()){
                    switch (tableau_navires[x][y].getShip().getLabel().toString()) {
                        case "C":
                            //return Hit.CARRIER;
                            aux = 0;
                            break;
                        
                        case "B":
                            //return Hit.BATTLESHIP;
                            aux = 1;
                            break;

                        case "D":
                            //return Hit.DESTROYER;
                            aux = 2;
                            break;

                        case "S":
                            //return Hit.SUBMARINE;
                            aux = 3;
                            break;
                        default:
                            break;
                    }
                    if(aux == 0){
                        return Hit.CARRIER;
                    }
                    if(aux == 1){
                        return Hit.BATTLESHIP;
                    } 
                    if(aux == 2){
                        return Hit.DESTROYER;
                    }
                    if(aux == 3){
                        return Hit.SUBMARINE;
                    }
                }
                else{
                    return Hit.STIKE;
                }
            }
            else{
                System.out.println("You have already hit this position");
                return Hit.INVALID;
            }
        }
        tableau_bool_frappes[x][y] = false;
        return Hit.MISS;
    }
}
