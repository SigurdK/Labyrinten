public class HvitRute extends Rute {

    HvitRute(int kolonne, int rad){
        super(kolonne,rad);

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
    void gaa(String v){

        this.blittGaatt = true;
        String kordinat = "("+kolonne + ","+rad+")";
        veien += v + " "+kordinat +" --> ";

        if (!nord.blittGatt()){
            nord.gaa(veien);
        }
        if (!sor.blittGatt()){
            sor.gaa(veien);
        }
        if (!ost.blittGatt()){
            ost.gaa(veien);
        }
        if (!vest.blittGatt()){
            vest.gaa(veien);
        }
        //this.blittGaatt = false;

    }
}
