import java.util.*;

import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.event.*;

abstract class Rute extends Button{
    //referasne til labyrint objektet blir satt i les fra fil
    Labyrint labyrint;
    //sette sin rutens posisjon i labyrinten.
    int kolonne;
    int rad;
    //referanser til naboruter:
    Rute nord = null;
    Rute sor = null;
    Rute ost = null;
    Rute vest = null;
    Rute forrige = null;

    boolean blittGaatt = false;
    String veien = "";

    Rute(int k, int r){
        kolonne = k;
        rad = r;
    }
    //Metoder for å finne naboer. Hvis ingen objekter så null.
    abstract char tilTegn(); //Returnerer 'char' for hver rute.

    //Metoder for å sett naboer og referasne labyrint
    public void settLabyrint(Labyrint l){
        labyrint = l;
    }
    public void settNord(Rute r){
        nord = r;
    }
    public void settSor(Rute r){
        sor = r;
    }
    public void settVest(Rute r){
        vest = r;
    }
    public void settOst(Rute r){
        ost = r;
    }
    public void settForrige(Rute r){
        forrige = r;
    }
    public Rute hentForrige(){
        return this.forrige;
    }


    abstract void gaa(Rute forrige, String v);
    abstract void settBlittGatt();
    abstract void settMerke();
    public void settVeien(){
        veien = "";
    }

    public boolean blittGatt(){
        return blittGaatt;
    }
    //En testMetode for å sjekke om sider er tileget riktig.
    public void printSider(){
        System.out.println("nord: "+nord.rad + ","+nord.kolonne);
        System.out.println("sor: "+sor.rad + ","+sor.kolonne);
        System.out.println("ost: "+ost.rad + ","+ost.kolonne);
        System.out.println("vest: "+vest.rad + ","+vest.kolonne);
    }
    public void finnUtvei(Rute forrige, String v){
        gaa(forrige, v);
    }
    void settLosningMerke(){
        setStyle("-fx-background-color: #FF6347");

    }
}
