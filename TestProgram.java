import java.io.File;
import java.util.Scanner;

class TestProgram {

    public static void main(String[] args) throws Exception{
        File f = new File("3.in");
        Labyrint labyrint = Labyrint.LeseFil(f); //Oppretter ett labyrintObjekt.
        System.out.println("Antall Rader: "+labyrint.hentAntallRader());
        System.out.println("Antall Kolonner: "+labyrint.hentAntallKolonner());
        System.out.println(labyrint);
        //Kolonne,rad
        labyrint.hentRute(4,1).finnUtvei("START ");
    }
}
