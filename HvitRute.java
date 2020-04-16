public class HvitRute extends Rute {

    HvitRute(int kolonne, int rad){
        super(kolonne,rad);

    }

    @Override
    char tilTegn() {
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
        //System.out.println("("+kolonne + ","+rad+")");

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

        //System.out.println(veien);
        /*if(nord.blittGatt() && sor.blittGatt() && vest.blittGatt() && ost.blittGatt()){
            System.out.println("Jeg er en blindvei!"+"("+kolonne + ","+rad+")");
            return;
        }*/

    }
}
