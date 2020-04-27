

public class Aapning extends HvitRute {



    //private Condition ikkeTomtBord = bordlas.newCondition();
    //private Condition ikkeFulltBord = bordlas.newCondition();

    Aapning(int kolonne, int rad){
        super(kolonne, rad);
    }

    @Override
    char tilTegn() {
        if (blittGaatt == true){
            return '0';
        }
        return 'o';
    }

    @Override
    void settBlittGatt(){
        this.blittGaatt = false;
    }
    @Override
    void gaa(Rute forrige,String v){ //throws InterruptedException

        //har og Conditon brukes = new laas.newCondition();
        //brukes.signal();
        //brukes.await();

        this.blittGaatt = true;
        /*try {
            System.out.println(labyrint);
            //System.out.println("LØSNING!");
            Thread.sleep(800);
        }catch(InterruptedException e){
            System.out.println("feil");
        }*/

        //Her har vi felles data, så denne må låses av en rentrantLock. kritisk region
        //<Lås>

        this.settForrige(forrige);
        String apning = v+"Jeg er en apning! ("+kolonne+","+ rad+")";
        //legger til losningen via monitorens LeggTilLosning metode:
        
        labyrint.monitor.leggTilLosning(apning, this.hentTraader());
        this.blittGaatt = false;
        this.hentForrige().settForrige(this);




        //<Lås opp>
        //

    }
}
