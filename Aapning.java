

public class Aapning extends HvitRute {



    //private Condition ikkeTomtBord = bordlas.newCondition();
    //private Condition ikkeFulltBord = bordlas.newCondition();

    Aapning(int kolonne, int rad){
        super(kolonne, rad);
        setStyle("-fx-background-color: #999999");
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


        this.blittGaatt = true;

        this.settForrige(forrige);
        String apning = v+"("+kolonne+", "+ rad+")";
        //legger til losningen via monitorens LeggTilLosning metode:

        labyrint.monitor.leggTilLosning(apning, this.hentTraader());
        this.blittGaatt = false;
        this.hentForrige().settForrige(this);

    }
    @Override
    void settMerke() {
        setStyle("-fx-background-color: #999999");

    }
}
