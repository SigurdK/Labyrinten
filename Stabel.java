public class Stabel<T> extends Lenkeliste<T>{
    public Stabel(){//Oppfører seg nå likt som en Lenkeliste:
        super();
    }
    public void leggPaa(T x){ //Vil oppføre seg likt som leggTil().
        leggTil(x);
    }
    public T taAv(){ //benytter fjern() med siste index i lsten slik at vi får en stabel
        T temp = fjern(stoerrelse()-1);
        return temp;
    }
    public void print(){
        for (int i = 0 ; i<this.stoerrelse() ; i++) {
            System.out.println(this.hent(i));

        }
    }
}
