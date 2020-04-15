public class Aapning extends HvitRute {

    Aapning(int kolonne, int rad){
        super(kolonne, rad);
    }

    @Override
    char tilTegn() {
        return 'o';
    }
    @Override
    void gaa(String v){
        System.out.println(v+"Jeg er en åpning! ("+kolonne+","+ rad+")");
    }
}
