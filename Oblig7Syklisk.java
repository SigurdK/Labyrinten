import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.event.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;

/*
* Skal være mulig å trykke på alle hvite ruter:
* Når en rute trykkes på så skal finnUtveiFra() begynne og løsningene lagres i losningne.
* første løsningen skal vises visuelt i labyrinten
* Resterende løsninger skrives ut som antall.
*
* Labyrinten er lagd som en grid pane, utifra rutenettet som er opprettet etter fil er lest.
* fil skal velges, så leses og initialiserer labyrinten.
* løsningen konverteres til en booelan ruteneett. Dette rutetnettet loopes gjennom sammen med labyrinten.
*/

public class Oblig7Syklisk extends Application{
    //Initialisere brett variable og statusInfo Tesxt osv
    Text statusinfo;
    Labyrint l = null;
    boolean[][] losningen = null;
    File filen = null;
    static Lenkeliste<String> utveier = new Lenkeliste<>();

    public static void main(String[] args) {
        // launch Kalles fra 'main' for å starte FX.
        launch(args);
    }

    class Klikkbehandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            statusinfo.setText("Velg en ny rute");
            //henter ruten som ble trykket
            Rute ruten = (Rute)e.getSource();
            //kaller løsningne fra tidligere oblig og finner losningene
            utveier = l.finnUtveiFra(ruten.kolonne,ruten.rad);

            if (utveier.stoerrelse() == 0){
                statusinfo.setText("Ingen utvei derfra, velg ny rute.");
            }else{
                //Alle Løsningene ligger her i utveier, men jeg skriverbare ut den første
                statusinfo.setText("her er losningen fra valgt rute\nDet er totalt "+ utveier.stoerrelse()+" utveier herfra");
                losningen = l.losningStringTilTabell(utveier.hent(0),l.antallKolonner,l.antallRader);
                for (int k = 0 ; k < l.antallKolonner ; k++){
                    for (int r = 0 ; r < l.antallRader ; r++){
                        if (losningen[r][k]){//Sjekker om rutene er en del av løsningen, og skifter farge basert på det.
                            l.hentRute(r,k).settLosningMerke();
                        }else{
                            l.hentRute(r,k).settMerke();
                        }
                    }
                }
            }
        }
    }
    @Override
    public void start(Stage teater) throws Exception{
        //her kommer GUI initialiseringen
        //Brukeren velger fil (Labyrint) i det programmet starter:

        statusinfo = new Text("Trykk på en rute for å finne veien ut");
        statusinfo.setFont(new Font(13));
        statusinfo.setX(10); statusinfo.setY(15);

        FileChooser fil = new FileChooser();
        filen = fil.showOpenDialog(teater); // Åpner et dialogvindu for å velge en fil.

        //Labyrinten leser filen og setter opp rutenettet sitt:
        try {
            l = Labyrint.leseFil(filen);
        } catch (FileNotFoundException e) {
            System.out.printf("FEIL: Kunne ikke lese filen!");
            System.exit(1);
        }

        Klikkbehandler klikk = new Klikkbehandler();

        //Her opprettes rutenettet og alle rutene tilegnes brettets verdier.
        GridPane rutenett = new GridPane();
        rutenett.setGridLinesVisible(true);

        for (int k = 0 ; k < l.antallKolonner ; k++){
            for (int r = 0 ; r < l.antallRader ; r++){
                Rute ruten = l.hentRute(r,k);
                ruten.setOnAction(klikk); //Kobler hver rute opp mot et klikk.
                rutenett.add(ruten,k,r);
            }
        }
        rutenett.setLayoutY(35);
        rutenett.setLayoutX(10);

        Pane kulisser = new Pane();
        kulisser.getChildren().add(statusinfo);
        kulisser.getChildren().add(rutenett);

        Scene scene = new Scene(kulisser);
        teater.setTitle("Labyrint Oblig 7");
        teater.setScene(scene);
        teater.show();
    }
}
