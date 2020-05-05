import java.util.*;

public class HvitRute extends Rute {//implements Runnable

    public class VeiViseren implements Runnable {
        Rute rute;
        String vei;
        Rute forrige;

        VeiViseren(Rute r,Rute f, String v){
            this.rute = r;
            this.vei = v;
            this.forrige = f;
        }

        public void run(){
            try{
                rute.gaa(forrige, this.vei);
            }catch (Exception e) {}
        }
    }

    HvitRute(int kolonne, int rad){
        super(kolonne,rad);
        //setter rutens farge som hvit når den opprettes
        setStyle("-fx-background-color: #999999");
    }
    public int hentTraader(){
        return traader;
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
    int antallUtveier(){
        int teller = 0;
        if (nord.blittGaatt == false){
            teller+= 1;
        }
        if (sor.blittGaatt == false){
            teller+= 1;
        }
        if (ost.blittGaatt == false){
            teller+= 1;
        }
        if (vest.blittGaatt == false){
            teller+= 1;
        }
        return teller;
    }

/*
OPPGAVE:
Hva skjer om den gamle tråden først går videre til neste rute og så etterpå starter opp nye tråder?

Da det benyttes rekursjon vil det ikke være ideelt å starte en ny tråd som skal gå en annen retning etter at den gamle tråden har gått videre til neste rute.
dersom en ny tråd startes etter at den første tråden har gått videre, vil den nye tråden vente på hovedtråden. hovedtråden vil vel fullføre hele sin vei, før den nye tråden i det hele tatt får startet på sin vei.

*/
    @Override
    void gaa(Rute forrige, String v){
        //koden under for å se hvordan den går i labyrinten.
/*
        System.out.println(labyrint);
        try {
            Thread.sleep(170);
        }catch(InterruptedException e){
            System.out.println("feil");
        }
*/
        //benytter en liste for å holde styr på trådene som er opprettet (og ha dem i riktig scoop)
        LinkedList<Thread> traadene = new LinkedList<Thread>();
        this.blittGaatt = true;
        this.settForrige(forrige);
        String kordinat = "("+kolonne + ", "+rad+") --> ";

        //en variable for å avgjøre om det skal opprettes flere tråder fra den nåværende ruten
        int antall = this.antallUtveier();

        if (!nord.blittGatt() && this.hentForrige() != this.nord){
            //hvis er flere veier? (antall) da starte ny tråd, else gaa() )
            if (antall>1){
                traader +=1;
                antall-=1;
                //Opretter den nye tråden, starter den og legger den i traad-listen.
                Runnable run = new VeiViseren(this.nord,this, v + kordinat);
                Thread nyVeiviser = new Thread(run);
                nyVeiviser.start();
                traadene.add(nyVeiviser);
            }else{
                nord.gaa(this,v + kordinat);
            }
        }
        if (!sor.blittGatt() && this.hentForrige() != this.sor){
            if (antall>1){
                traader +=1;
                antall-=1;

                Runnable run = new VeiViseren(this.sor,this, v + kordinat);
                Thread nyVeiviser = new Thread(run);
                nyVeiviser.start();
                traadene.add(nyVeiviser);

            }else{
                sor.gaa(this,v + kordinat);
            }
        }
        if (!ost.blittGatt() && this.hentForrige() != this.ost){
            if (antall>1){
                traader +=1;
                antall-=1;

                Runnable run = new VeiViseren(this.ost,this, v + kordinat);
                Thread nyVeiviser = new Thread(run);
                nyVeiviser.start();
                traadene.add(nyVeiviser);

            }else{
                ost.gaa(this,v + kordinat);
            }
        }

        if (!vest.blittGatt() && this.hentForrige() != this.vest){
            if (antall>1){
                traader +=1;
                antall-=1;

                Runnable run = new VeiViseren(this.vest,this, v + kordinat);
                Thread nyVeiviser = new Thread(run);
                nyVeiviser.start();
                traadene.add(nyVeiviser);

            }else{
                vest.gaa(this,v + kordinat);
            }
        }
        //Looper gjennom listen med tråder og joiner den aktuelle
        for(int i = 0; i<traadene.size(); i++){
            try{
                traadene.get(i).join();
            }catch(Exception ex){

            }
        }
        //Dette er her for å eventuelt klare løse sykliske labyrinter.
        //this.blittGaatt = false;
        //this.hentForrige().settForrige(this);
    }

    @Override
    void settMerke() {
        if (!blittGaatt){
            setStyle("-fx-background-color: #999999");
        }else {
            setStyle("-fx-background-color: #FF6347");
        }

    }
}
