package ships;

import ensta.Orientation;


abstract class AbstractShip {
    protected char label;
    protected String nom_navire;
    protected int taille_navire;
    protected Orientation orientation;

    public AbstractShip(String nom_navire, char label, int taille_navire, Orientation orientation){
        this.label = label;
        this.nom_navire = nom_navire;
        this.taille_navire = taille_navire;
        this.orientation = orientation;
    }

    public char getLabel(){
        return this.label;
    }

    public Orientation getOrientation(){
        return this.orientation;
    }

    public String getNom(){
        return this.nom_navire;
    }

    public int getTailleNavire(){
        return this.taille_navire;
    }

    public void changeOrientation(Orientation new_orientation)
    {
        this.orientation = new_orientation;
    }
}
