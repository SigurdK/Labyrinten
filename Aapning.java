public class Aapning extends HvitRute {

    Aapning(int kolonne, int rad){
        super(kolonne, rad);
    }

    @Override
    char tilTegn() {
        return 'o';
    }
    @Override
    void settBlittGatt(){
        this.blittGaatt = false;
    }
    @Override
    void gaa(String v){
        String apning = v+"Jeg er en apning! ("+kolonne+","+ rad+")";
        //Labyrint.leggTilUtvei(apning);
        Labyrint.losninger.leggTil(apning);
        //System.out.println(v+"Jeg er en åpning! ("+kolonne+","+ rad+")");
    }
}
