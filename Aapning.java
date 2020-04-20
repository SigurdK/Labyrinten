public class Aapning extends HvitRute {

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
    void gaa(Rute forrige,String v){
        this.blittGaatt = true;
        try {
            System.out.println(labyrint);
            System.out.println("LØSNING!");
            Thread.sleep(800);
        }catch(InterruptedException e){
            System.out.println("feil");
        }

        this.settForrige(forrige);
        String apning = v+"Jeg er en apning! ("+kolonne+","+ rad+")";
        Labyrint.losninger.leggTil(apning);
        this.blittGaatt = false;
        this.hentForrige().settForrige(this);

    }
}
