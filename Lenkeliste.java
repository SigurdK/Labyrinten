import java.util.Iterator;

public class Lenkeliste<T> implements Liste<T>{


    Node hode = new Node(null); //start (eldste element O*** =>)
    Node hale = new Node(null); // nyeste element (***O)
    int antallNoder;//Ikke private nå da jeg kan sjekke antall noder i listen med .antallNoder

    public Lenkeliste(){ //Konstruktøren til Lenkeliste
        hode.neste = hale; //setter hodets neste-peker til å peke på halen
        hale.forrige = hode;//halens forrige peler til å peke på hodet
        antallNoder = 0; //antall elementer lik 0
    }

    public int stoerrelse(){ //Sjekker gjennom lista og teller så lenge hode av listen ikke er lik halen.
        int count = 0;
        Node temp = hode.neste;

        while (temp != hale){
            count++;
            temp = temp.neste;
        }
        return count;
    }

    public void leggTil(T x){//med denne leggTil legges elementer til bak.
        Node nyNode = new Node(x); //Oppretter ny node med data x, som skal legges på slutten av lenken

        nyNode.neste = hale;//omdirigerer alle pekere slik at halen flyttes bakover
        hale.forrige.neste = nyNode;
        nyNode.forrige = hale.forrige;
        hale.forrige = nyNode;
        antallNoder ++; //Øker tellerene på antall noder
    }

    public T fjern(){//fjerene som en kø, første element i lenken
        T x = hode.neste.data;
        if(hode.neste != hale){
            hode.neste = hode.neste.neste; //hodets neste-peker peker på nestes neste
            hode.neste.forrige = hode; // det andre elementet blir det første
        }else {throw new UgyldigListeIndex(-1);}
        antallNoder--; //reduserer antall noder
        return x;
    }

    public void sett(int pos,T x){ //Sette inn i lenken og erstatte på posisjon int pos.
        Node nyNode = new Node(x);
        Node temp = hode.neste;
        if (pos >= 0 && pos < stoerrelse()){ //looper gjennom lenken til vi når riktig posisjon.
            for (int i = 0 ; i<pos ; i++){
                temp = temp.neste;
            }
            temp.data = nyNode.data;
        }else { throw new UgyldigListeIndex(pos);}
    }

    public void leggTil(int pos, T x){
        Node nyNode = new Node(x);
        Node temp = hode.neste;
        if (pos >= 0 && pos <= stoerrelse()){ //looper gjennom lenken til vi når riktig posisjon. her med <= ettersom pos kan være slutten av listen pluss 1.
            for (int i = 0 ; i<pos ; i++){
                temp = temp.neste;
            }
            nyNode.forrige =temp.forrige; //får endret de 2 forrige og de 2 neste pekerne i elementene rundt den nyinsatte noden.
            temp.forrige = nyNode;
            nyNode.neste=temp;
            nyNode.forrige.neste = nyNode;

        }else{ throw new UgyldigListeIndex(pos);}
        antallNoder ++;
    }

    public T fjern(int pos){
        Node temp = hode.neste;
        if (antallNoder > 0 && pos >= 0 && pos < stoerrelse()){

            for (int i = 0 ; i<pos ; i++){
                temp = temp.neste;
            }
            temp.neste.forrige = temp.forrige;
            temp.forrige.neste = temp.neste;

        }else{ throw new UgyldigListeIndex(pos);}

        antallNoder --;
        return temp.data;
    }

    public T hent(int pos){ //scroller gjennom listen til riktig posisjon, så returenrer dataen til den noden.
        Node temp = hode.neste;
        if (antallNoder > 0 && pos >= 0 && pos < stoerrelse()){
            for (int i = 0 ; i<pos ; i++){
                temp = temp.neste;
            }
        }else{ throw new UgyldigListeIndex(pos);}
        return temp.data;
    }
    protected class Node{
        public Node neste; //Oppretter en neste node peker
        public Node forrige; //Oppretter en forrige Node peker
        public T data;

        public Node(T x){ //konstruktør til klassen Node.
            data = x;
        }
    }

    public Iterator<T> iterator(){
        return new LenkelisteIterator();
    }

    private class LenkelisteIterator implements Iterator<T> {

        private Node temp;
        LenkelisteIterator(){
            temp = hode;
        }

        public boolean hasNext(){
            return temp.neste != hale;
        }

        public T next(){
            temp = temp.neste;
            T data = temp.data;
            return data;
        }

        public void remove(){
        }
    }
}
