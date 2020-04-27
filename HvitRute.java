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
    @Override

    void gaa(Rute forrige, String v){
        //koden under for å se hvordan den går i labyrinten.
        try {
            System.out.println("traader opprettet: "+ traader);
            System.out.println(labyrint);
            Thread.sleep(200);
        }catch(InterruptedException e){
            System.out.println("feil");
        }

        LinkedList<Thread> traadene = new LinkedList<Thread>();
        this.blittGaatt = true;
        this.settForrige(forrige);
        String kordinat = "("+kolonne + ","+rad+") -->";
        int antall = this.antallUtveier();



        if (!nord.blittGatt() && this.hentForrige() != this.nord){//og er flere veier? da starte ny tråd?else gaa)
            //Sette nord sin forrige til denne
            if (antall>1){
                traader +=1;
                antall-=1;

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

        //Dette er her for å eventuelt klare løse sykliske labyrinter også med flere tråder..
        //this.blittGaatt = false;
        //this.hentForrige().settForrige(this);

        for(int i = 0; i<traadene.size(); i++){
            try{
                traadene.get(i).join();
            }catch(Exception ex){

            }
        }
    }
}
