package ensta;

import ships.*;

public class Board implements IBoard
{
    protected int taille = 10;
    protected String nom_tableau;
    protected String[] line_tableau;
    protected String line_header, line_tableau_indicator;
    protected char[][] tableau_char_navires;
    protected char[] columns;
    protected boolean[][] tableau_bool_frappes;

    

    public Board( String nom, int taille )
    {
        this.nom_tableau = nom;
        this.taille = taille;
        this.tableau_char_navires = new char[taille][taille];
        this.tableau_bool_frappes = new boolean[taille][taille];
        this.line_tableau = new String[taille];
        this.columns = new char[taille];

        initTableauChar(tableau_char_navires);
        initTableauBool(tableau_bool_frappes);
    }

    public Board( String nom )
    {
        this.nom_tableau = nom;
        this.taille = 10;
        this.tableau_char_navires = new char[taille][taille];
        this.tableau_bool_frappes = new boolean[taille][taille];
        this.line_tableau = new String[taille];
        this.columns = new char[taille];

        initTableauChar(tableau_char_navires);
        initTableauBool(tableau_bool_frappes);
    }

    protected void columnsName()
    {
        for(int i = 0; i < taille; i++){
            int ascii = 65 + i;
            columns[i] = (char)ascii;
        }
    }

    protected void initTableauChar(char[][] tableau)
    {
        for (int i = 0; i < taille; i++){
            for (int j = 0; j < taille; j++){
                tableau[i][j] = '.';
                tableau[i][j] = '.';
            }
        }
    }

    protected void initTableauBool(boolean[][] tableau)
    {
        for (int i = 0; i < taille; i++){
            for (int j = 0; j < taille; j++){
                tableau[i][j] = false;
            }
        }
    }

    protected void setTableauIndicator()
    {
        line_tableau_indicator = "Navire:";
        for(int i = 0; i < 2*taille; i++){
            line_tableau_indicator += " ";
        }
        line_tableau_indicator += "Frappes:";
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
    }

    protected void setTableauNavire()
    {
        for (int i = 0; i < taille; i++){
            line_tableau[i] = i + " ";
            for (int j = 0; j < taille; j++){
                line_tableau[i] += tableau_char_navires[i][j] + " ";
            }
            line_tableau[i] += "     ";
        }
    }

    protected void setTableauFrappes()
    {
        for (int i = 0; i < taille; i++){
            line_tableau[i] += i + " ";
            for (int j = 0; j < taille; j++){
                if(!tableau_bool_frappes[i][j]){
                    line_tableau[i] += ". ";
                }
                else{
                    line_tableau[i] += "X ";
                }
            }
            line_tableau[i] += "     ";
        }
    }

    protected void tableauToPrint()
    {
        setTableauIndicator();
        setTableauHeader();
        setTableauNavire();  
        setTableauFrappes();
    }

    public void print()
    {
        tableauToPrint();
        System.out.println("\n");
        System.out.println(line_tableau_indicator);
        System.out.println(line_header);
        for (int i = 0; i < taille; i++){
            System.out.println(line_tableau[i]);               
        }
        System.out.println("\n");
    }


    public int getSize(){
        return this.taille;
    }
    
    public void putShip(Submarine ship, int x, int y){
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
                        
                        tableau_char_navires[x][i] = ship.getLabel();
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
                        
                        tableau_char_navires[i][y] = ship.getLabel();
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
                        
                        tableau_char_navires[i][y] = ship.getLabel();
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
                            
                            tableau_char_navires[x][i] = ship.getLabel();
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
            //tableau_char_navires = tableau_char_navires_aux2;
            System.out.println("invalid position! out of the board");
        }
        
    }

    public void putShip(Destroyer ship, int x, int y){
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
                        
                        tableau_char_navires[x][i] = ship.getLabel();
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
                        
                        tableau_char_navires[i][y] = ship.getLabel();
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
                        
                        tableau_char_navires[i][y] = ship.getLabel();
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
                            
                            tableau_char_navires[x][i] = ship.getLabel();
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
            //tableau_char_navires = tableau_char_navires_aux2;
            System.out.println("invalid position! out of the board");
        }
    }

    public void putShip(BattleShip ship, int x, int y){
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
                        
                        tableau_char_navires[x][i] = ship.getLabel();
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
                        
                        tableau_char_navires[i][y] = ship.getLabel();
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
                        
                        tableau_char_navires[i][y] = ship.getLabel();
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
                            
                            tableau_char_navires[x][i] = ship.getLabel();
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
            //tableau_char_navires = tableau_char_navires_aux2;
            System.out.println("invalid position! out of the board");
        }
    }

    public void putShip(AircraftCarrier ship, int x, int y){
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
                        
                        tableau_char_navires[x][i] = ship.getLabel();
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
                        
                        tableau_char_navires[i][y] = ship.getLabel();
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
                        
                        tableau_char_navires[i][y] = ship.getLabel();
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
                            
                            tableau_char_navires[x][i] = ship.getLabel();
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
            //tableau_char_navires = tableau_char_navires_aux2;
            System.out.println("invalid position! out of the board");
        }
    }
    

    public boolean hasShip(int x, int y){
        if(tableau_char_navires[x][y] != '.'){
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
}
