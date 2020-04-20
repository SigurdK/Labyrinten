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
    void gaa(Rute forrige, String v){

        //koden under for å se hvordan den går i labyrinten.
        try {
            System.out.println(labyrint);
            //System.out.println("STRINGEN ER: "+v);
            Thread.sleep(500);
        }catch(InterruptedException e){
            System.out.println("feil");
        }

        this.blittGaatt = true;
        this.settForrige(forrige);
        String kordinat = "("+kolonne + ","+rad+")";
        veien += v + " "+kordinat +" --> ";
        //Lage en else if. og ha til slutt dersom alle rundt er gått , så backtrack eventuelt om det er en åpning.
        //når koden kommer til en åpning lagres ruten og koden backtracker.

        if (!nord.blittGatt() && this.hentForrige() != this.nord){
            //Sette nord sin forrige til denne
            nord.gaa(this,veien);
        }
        if (!sor.blittGatt() && this.hentForrige() != this.sor){
            sor.gaa(this,veien);
        }
        if (!ost.blittGatt() && this.hentForrige() != this.ost){
            ost.gaa(this,veien);
        }
        if (!vest.blittGatt() && this.hentForrige() != this.vest){
            vest.gaa(this,veien);
        }

        this.blittGaatt = false;
        this.hentForrige().settForrige(this);

    }
}
