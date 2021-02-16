package ensta;

import ships.*;

public class Board 
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
                if(tableau_bool_frappes[i][j]){
                    line_tableau[i] += ". ";
                }
                line_tableau[i] += tableau_char_navires[i][j] + " ";
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
}
