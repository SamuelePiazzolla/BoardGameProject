package boardgame;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.IllegalFormatConversionException;

public class GameBoardController {
    @FXML private ArrayList<Pane> pezziBianchi = new ArrayList<>();
    @FXML private ArrayList<Pane> pezziNeri = new ArrayList<>();

    @FXML private GridPane scacchiera;

    //Variabile che identifica di chi è il turno
    boolean turnoB = true;

    //Variabile in cui salvo se ho selezionato oppure meno un pezzo da muovere
    boolean selected = false;

    //Variabile in cui salvo il pezzo selezionato da muovere
    Pane pezzo;


    @FXML
    public void initialize()
    {
        //Scorro tutte le posizioni della griglia per inserire e salvare i pezzi
        for (Node node: scacchiera.getChildren())
        {
            //Mi salvo in due variabili la riga e la colonna in cui ci troviamo attualmente all'interno della tabella
            Integer colonna = GridPane.getColumnIndex(node);
            Integer riga = GridPane.getRowIndex(node);

            //Stampo la posizione attuale
            //System.out.println("Colonna: " + colonna + " Riga: " + riga);

            //Controllo che non ci troviamo in posizioni al di fuori dell'interno della scacchiera
            if (riga != null && colonna != null && riga < 9 && colonna < 9) {
                //Se siamo nelle prime tre righe dobbiamo inserire i pezzi neri
                if (riga == 1 && colonna != 9 || riga == 2 && colonna != 9 || riga == 3 && colonna != 9) {
                    //Nella dama i pezzi vanno inseriti alternati
                    if (node.getStyle().contains("#9f9f9f")) {
                        node.getStyleClass().clear();
                        node.getStyleClass().add("damaNera");

                        //Mi salvo il pane in cui ho inserito i pezzi neri
                        pezziNeri.add((Pane) node);
                    }
                }

                //Se siamo nelle prime tre righe dobbiamo inserire i pezzi bianchi
                if (riga == 6 && colonna != 9 || riga == 7 && colonna != 9 || riga == 8 && colonna != 9) {
                    if (node.getStyle().contains("#9f9f9f")) {
                        node.getStyleClass().clear();
                        node.getStyleClass().add("damaBianca");

                        //Mi salvo il pane in cui ho inserito i pezzi bianchi
                        pezziBianchi.add((Pane) node);
                    }
                }

                //Imposto tutte le posizioni della scacchiera come premibili
                node.setOnMouseClicked(mouseEvent -> {
                    try {
                        handlePieceSelection(mouseEvent);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });


            }
        }
        //Stampo il numero di pezzi inseriti
        //System.out.println("Pezzi bianchi: " + pezziBianchi.size() + " Pezzi neri: " + pezziNeri.size());
    }

    @FXML
    public void handlePieceSelection(MouseEvent e) throws Exception
    {
        //Mi salvo il punto cliccato
        Pane spazio = null;
        try {
            spazio = (Pane)e.getSource();
        }
        catch (Exception exc)
        {
            throw new RuntimeException(exc);
        }

        if (selected)
        {
            if(turnoB)
            {
                //Se posso sposto il pezzo
                if(spazio.getStyleClass().toString().contains("blank"))
                {
                    //Sposto il pezzo
                    spazio.getStyleClass().clear();
                    spazio.getStyleClass().add("damaBianca");

                    //Rimuovo il pezzo dalla posizione precedente
                    pezzo.getStyleClass().clear();
                    pezzo.getStyleClass().add("blank");

                    //Rendo di nuovo selezionabili altri pezzi
                    selected = false;

                    //Passo il turno
                    turnoB = false;
                }
                else
                {
                    if(spazio.getStyleClass().toString().contains("damaBianca"))
                    {
                        pezzo.getStyleClass().clear();
                        pezzo.getStyleClass().add("damaBianca");
                        pezzo = spazio;
                        pezzo.getStyleClass().clear();
                        pezzo.getStyleClass().add("selezionatoB");
                    }
                }
            }
            else
            {
                //Se posso sposto il pezzo
                if(spazio.getStyleClass().toString().contains("blank"))
                {
                    //Sposto il pezzo
                    spazio.getStyleClass().clear();
                    spazio.getStyleClass().add("damaNera");

                    //Rimuovo il pezzo dalla posizione precedente
                    pezzo.getStyleClass().clear();
                    pezzo.getStyleClass().add("blank");

                    //Rendo di nuovo selezionabili altri pezzi
                    selected = false;

                    //Passo il turno
                    turnoB = true;
                }
                else
                {
                    if(spazio.getStyleClass().toString().contains("damaNera"))
                    {
                        pezzo.getStyleClass().clear();
                        pezzo.getStyleClass().add("damaNera");
                        pezzo = spazio;
                        pezzo.getStyleClass().clear();
                        pezzo.getStyleClass().add("selezionatoN");
                    }
                }
            }

        }
        else
        {
            if (turnoB)
            {
                //Se è um pezzo me lo salvo come selezionato
                if (spazio.getStyleClass().toString().contains( "damaBianca"))
                {
                    selected = true;
                    pezzo = spazio;
                    pezzo.getStyleClass().clear();
                    pezzo.getStyleClass().add("selezionatoB");
                }
            }
            else
            {
                //Se è um pezzo me lo salvo come selezionato
                if (spazio.getStyleClass().toString().contains("damaNera"))
                {
                    selected = true;
                    pezzo = spazio;
                    pezzo.getStyleClass().clear();
                    pezzo.getStyleClass().add("selezionatoN");
                }
            }
        }
    }
}
