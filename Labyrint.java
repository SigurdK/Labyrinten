import java.io.File;
import java.util.Scanner;

class Labyrint {
    static Lenkeliste<String> losninger = new Lenkeliste<>();
    static int antallRader;
    static int antallKolonner;
    static Rute[][] array;
    // blir sikkert element av type rute etterhvert
     //(Kolonne ,rad)

    private Labyrint (int rader, int kolonner, Rute[][] a){ //Tar inn array av ruteobjekter?
        antallRader = rader;
        antallKolonner = kolonner;
        array = a;

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
    public void leggTilUtvei(String utvei) {
        losninger.leggTil(utvei);
    }
    public Lenkeliste<String> finnUtveiFra(int k, int r){
        this.hentRute(k,r).gaa("START ");

        Lenkeliste<String> temp = new Lenkeliste<>();
        for (String losning : losninger){
            String a = losninger.fjern();
            temp.leggTil(a);
        }

        //maa resette alle rutene:
        for(int ra = 0; ra < antallRader ; ra ++){
            for (int ko = 0 ; ko < antallKolonner ; ko ++){
                System.out.println(this.array[ko][ra]);
                this.array[ra][ko].settBlittGatt();
            }
        }


        /*
        for (String e : losninger){
            e.fjern();
        }*/
        return temp;
        /*int s = losninger.stoerrelse();
        if (losninger.stoerrelse() == 0){
            System.out.println("Kan ikke finne noen åninger fra det gitte startpunkt.");
        }else{
            for (int i = 0 ; i < s ; i++){
                System.out.println("Losning nummer "+(i+1)+":");
                System.out.println(losninger.fjern());
            }
        }*/
    }

    //ønsker å itterer gjennom 2d arrayen og printe ut alle elementer
    public String toString(){
        String utskrift = "";
        for (int i = 0 ; i < antallRader ; i++){

            for (int j = 0 ; j <antallKolonner ; j++){
                utskrift += array[i][j].tilTegn(); //må hente symbolene til ruteobjektene det gjelder
            }
            utskrift += "\n";
        }
        return utskrift;
    }
}
