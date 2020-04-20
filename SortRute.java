public class SortRute extends Rute {

    SortRute(int kolonne, int rad){
        super(kolonne,rad);
        this.blittGaatt = true;
    }

    @Override
    char tilTegn() {
        return '#';
    }
    @Override
    void settBlittGatt(){
        this.blittGaatt = true;
    }

    @Override
    void gaa(Rute r,String v){
        System.out.println("Du startet på en Sort Rute.");
    }
}
