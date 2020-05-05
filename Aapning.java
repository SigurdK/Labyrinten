public class Aapning extends HvitRute {

    Aapning(int kolonne, int rad){
        super(kolonne, rad);
        setStyle("-fx-background-color: #FFFFFF");
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
        this.settForrige(forrige);
        String apning = v+"("+kolonne+", "+ rad+")";
        Labyrint.losninger.leggTil(apning);
        this.blittGaatt = false;
        this.hentForrige().settForrige(this);

    }
    @Override
    void settMerke(){
        setStyle("-fx-background-color: #FFFFFF");
    }
}
