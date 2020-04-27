import java.io.File;
import java.util.Scanner;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Labyrint {

    //private Lock laas = new ReentrantLock();

    static Lenkeliste<String> losninger = new Lenkeliste<>();
    static int antallRader;
    static int antallKolonner;
    Rute[][] array;
    Monitor monitor;

    private Labyrint (int rader, int kolonner, Rute[][] a){ //Tar inn array av ruteobjekter?
        antallRader = rader;
        antallKolonner = kolonner;
        array = a;
        this.monitor = new Monitor(losninger);
    }

    static Labyrint leseFil(File filen) throws Exception{
        Scanner fil = new Scanner(filen);
        //Leser inn antall rader og kolonner fra første linje i filene (rad,kolonne)
        antallRader =  fil.nextInt();
        antallKolonner = fil.nextInt();

        Rute[][] array = new Rute[antallRader][antallKolonner];
        fil.nextLine();
        //oppretter en 2D array som skal sendes til Labyrint konstruktøren
        int rad = 0;
        int kolonne = 0;
        while (rad < antallRader){
            //Vet at denne string linjen er antallKolonner lang:
            String[] linje = fil.nextLine().split("");
            //leser inn om objektene er sorte eller hvite ruter basert på . (hvit) og # (sort)
            for (int i = 0 ; i < antallKolonner; i++){
                if (linje[i].equals(".")){
                    //Sjekker om objektet er på kanten av labyrintetn (aapning)
                    HvitRute nyRute = new HvitRute(kolonne, rad);
                    if (sjekkOmAapning(rad,kolonne)){
                        Aapning aapning = new Aapning(kolonne,rad);
                        array[rad][kolonne] = aapning;
                    }else{array[rad][kolonne] = nyRute;}

                }else if (linje[i].equals("#")){
                    SortRute nyRute = new SortRute(kolonne, rad);
                    array[rad][kolonne] = nyRute;
                }
                kolonne++;
            }
            rad ++;
            kolonne = 0;
        }
        //Oppretter Labyrint objektet
        Labyrint labyrinten = new Labyrint(antallRader,antallKolonner,array);

        //Her skal jeg sette alle ruters naboer og labyrint referanse:(Nord sør øst vest)
        for(int r = 0; r < antallRader ; r ++){
            for (int k = 0 ; k < antallKolonner ; k ++){

                labyrinten.hentRute(r,k).settLabyrint(labyrinten);

                if (r > 0){
                    labyrinten.hentRute(r,k).settNord(labyrinten.hentRute(r-1,k));
                }
                if (r < antallRader-1){
                    labyrinten.hentRute(r,k).settSor(labyrinten.hentRute(r+1,k));
                }
                if (k > 0){
                    labyrinten.hentRute(r,k).settVest(labyrinten.hentRute(r,k-1));
                }
                if (k < antallKolonner -1){
                    labyrinten.hentRute(r,k).settOst(labyrinten.hentRute(r,k+1));
                }
            }
        }
        return labyrinten;
    }

    //For testingens del:
    public int hentAntallRader(){
        return antallRader;
    }
    public int hentAntallKolonner(){
        return antallKolonner;
    }

    static boolean sjekkOmAapning(int rad, int kolonne){
        if (rad == 0 || kolonne == 0 || rad == (antallRader -1) || kolonne == (antallKolonner-1)){
            return true;
        }
        return false;
    }

    //funksjon for å hente en Rute på en gitt plassen
    public Rute hentRute(int rad,int kolonne){
        return array[rad][kolonne];
    }

    public Lenkeliste<String> finnUtveiFra(int k, int r){
        //Kjører finnUtvei(som starter gaa()) fra den angitte posisjonen(da starter rekursive løsningen)
        //setter her forrige ruten, som start ruten og kaller gaa

        this.hentRute(r,k).finnUtvei(this.hentRute(r,k),"START ");

        //Lager en tempListe som returneres, mens static listen resettes.
        Lenkeliste<String> temp = new Lenkeliste<>();
        for (String losning : losninger){
            temp.leggTil(losninger.fjern());
        }
        //resetter alle rutene, og rutenes instanser.
        for(int ra = 0; ra < antallRader ; ra ++){
            for (int ko = 0 ; ko < antallKolonner ; ko ++){
                this.array[ra][ko].settBlittGatt();
                this.array[ra][ko].settVeien();
                this.array[ra][ko].traader = 1;
            }
        }
        monitor.reset();
        return temp;
    }
    //ønsker å itterer gjennom 2d arrayen og printe ut alle elementer
    public String toString(){
        String utskrift = "";
        for (int i = 0 ; i < antallRader ; i++){
            for (int j = 0 ; j <antallKolonner ; j++){
                utskrift += array[i][j].tilTegn();
            }
            utskrift += "\n";
        }
        return utskrift;
    }
}

class Monitor {

    private Lock laas = new ReentrantLock();
    Lenkeliste<String> losningene;

    private Condition alleFerdige = laas.newCondition();
    int antallFerdigeSubtrader = 0;

    Monitor(Lenkeliste<String> l){
        losningene = l;
    }

    public void leggTilLosning(String l, int r){
        laas.lock();
        try{
            antallFerdigeSubtrader ++;
            losningene.leggTil(l);

            //For sener efinne sykliske med tråder.
            /*if(losningene.stoerrelse() == 2){
                alleFerdige.signal();
            }*/

        }finally {
            laas.unlock();
        }
    }

    //For senere finne sykliske med flere tråder:
    public void vent() {
       laas.lock();
       try {
            while (losningene.stoerrelse() != 2) {
	            alleFerdige.await();
                System.out.println(antallFerdigeSubtrader + " ferdige subtrader ");
            }
        }
        catch (InterruptedException e)
		        { System.out.println(" Uventet avbrudd ");  System.exit(0); }
        finally { laas.unlock();  }
    }
    public void reset(){
        for (String s : losningene){
            losningene.fjern();
        }
    }
}
