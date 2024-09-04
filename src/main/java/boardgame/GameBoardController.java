package boardgame;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class GameBoardController {
    @FXML private ArrayList<Pane> pezziBianchi = new ArrayList<>();
    @FXML private ArrayList<Pane> pezziNeri = new ArrayList<>();

    @FXML private GridPane schacchiera;


    @FXML
    public void initialize()
    {
        //Scorro tutte le posizioni della griglia per inserire e salvare i pezzi
        for (Node node: schacchiera.getChildren())
        {
            //Mi salvo in due variabili la riga e la colonna in cui ci troviamo attualmente all'interno della tabella
            Integer colonna = GridPane.getColumnIndex(node);
            Integer riga = GridPane.getRowIndex(node);

            //Stampo la posizione attuale
            //System.out.println("Colonna: " + colonna + " Riga: " + riga);

            //Controllo che non ci troviamo in posizioni inesistenti
            if(riga != null && colonna != null)
            {
                //Se siamo nelle prime tre righe dobbiamo inserire i pezzi neri
                if(riga == 1 && colonna != 9 || riga == 2 && colonna != 9 || riga == 3 && colonna != 9)
                {
                    //Nella dama i pezzi vanno inseriti alternati
                    if(node.getStyle().contains("#9f9f9f"))
                    {
                        node.getStyleClass().clear();
                        node.getStyleClass().add("damaNera");

                        //Mi salvo il pane in cui ho inserito i pezzi neri
                        pezziNeri.add((Pane) node);
                    }
                }

                //Se siamo nelle prime tre righe dobbiamo inserire i pezzi bianchi
                if(riga == 6 && colonna != 9 || riga == 7 && colonna != 9 || riga == 8 && colonna != 9)
                {
                    if(node.getStyle().contains("#9f9f9f"))
                    {
                        node.getStyleClass().clear();
                        node.getStyleClass().add("damaBianca");

                        //Mi salvo il pane in cui ho inserito i pezzi bianchi
                        pezziBianchi.add((Pane) node);
                    }
                }
            }
        }
        //Stampo il numero di pezzi inseriti
        //System.out.println("Pezzi bianchi: " + pezziBianchi.size() + " Pezzi neri: " + pezziNeri.size());

    }

}
