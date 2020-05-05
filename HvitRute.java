public class HvitRute extends Rute {

    HvitRute(int kolonne, int rad){
        super(kolonne,rad);
        setStyle("-fx-background-color: #FFFFFF");
    }

    @Override
    char tilTegn() {
        if (blittGaatt == true){
            return '0';
        }
        return '.';
    }
    @Override
    void settBlittGatt(){
        this.blittGaatt = false;
    }

    @Override
    void gaa(Rute forrige, String v){

        
        this.blittGaatt = true;
        this.settForrige(forrige);
        String kordinat = "("+kolonne + ", "+rad+") --> ";

        if (!nord.blittGatt() && this.hentForrige() != this.nord){
            //Sette nord sin forrige til denne
            nord.gaa(this,v + kordinat);
        }
        if (!sor.blittGatt() && this.hentForrige() != this.sor){
            sor.gaa(this,v + kordinat);
        }
        if (!ost.blittGatt() && this.hentForrige() != this.ost){
            ost.gaa(this,v + kordinat);
        }
        if (!vest.blittGatt() && this.hentForrige() != this.vest){
            vest.gaa(this,v + kordinat);
        }

        this.blittGaatt = false;
        this.hentForrige().settForrige(this);
    }
    @Override
    void settMerke(){
        setStyle("-fx-background-color: #FFFFFF");
    }
}
