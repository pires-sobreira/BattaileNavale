package ships;

import ensta.Orientation;
import java.util.*;


public abstract class AbstractShip {
    protected Character label;
    protected String nom_navire;
    protected int taille_navire;
    protected Orientation orientation;
    protected int strikeCount;
    

    public AbstractShip(String nom_navire, Character label, int taille_navire, Orientation orientation){
        this.label = label;
        this.nom_navire = nom_navire;
        this.taille_navire = taille_navire;
        this.orientation = orientation;
        this.strikeCount = 0;
    }

    public void setLabel(Character c){
        this.label = c;
    }

    public Character getLabel(){
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

    public void addStrike(){
        if(this.strikeCount < this.taille_navire){
            this.strikeCount++;
        }
    }

    public boolean isSunk(){
        if(strikeCount == this.taille_navire){
            return true;
        }
        else
            return false;
    }
}
