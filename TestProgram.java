import java.io.File;
import java.util.Scanner;

class TestProgram {

    public static void main(String[] args) throws Exception{
        File f = new File("3.in");
        Labyrint labyrint = Labyrint.leseFil(f); //Oppretter ett labyrintObjekt.
        //System.out.println("Antall Rader: "+labyrint.hentAntallRader());
        //System.out.println("Antall Kolonner: "+labyrint.hentAntallKolonner());
        //System.out.println(labyrint);
        //Kolonne,rad
        //labyrint.hentRute(4,1).finnUtvei("START ");
        System.out.println(labyrint.finnUtveiFra(1,1));
        System.out.println("TEST 2");
        //vil ikke skrive ut riktig losninger her da labyrintetn er gatt! må refreshe
        labyrint.finnUtveiFra(5,1);
    }
}
