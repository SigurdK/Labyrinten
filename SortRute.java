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
    void gaa(String v){
        System.out.println("Sort Rute");
    }
}
