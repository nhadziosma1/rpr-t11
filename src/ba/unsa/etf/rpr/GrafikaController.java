package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;

public class GrafikaController
{
    public TextField tfGGrad;
    public TextField tfObrisi;
    public Label izvrseno;

    public void NadjiGlavniGrad(ActionEvent actionEvent)
    {
        GeografijaDAO gdo = GeografijaDAO.getInstance();

        Grad gg = gdo.glavniGrad( tfGGrad.getText() );

        if( tfGGrad.getText().isEmpty())
        {
            Alert upozori = new Alert(Alert.AlertType.WARNING);
            upozori.setTitle("UPOZORENJE");
            upozori.setContentText("Niste unijeli nista u namjeneno polje!");
            upozori.showAndWait();
        }
        else if(gg == null)
        {
            Alert upozori = new Alert(Alert.AlertType.WARNING);
            upozori.setTitle("UPOZORENJE");
            upozori.setContentText("Unijeti grad ne postoji u bazi!");
            upozori.showAndWait();
        }
        else
        {
            izvrseno.setText("Glavni grad "+tfGGrad.getText()+" je "+gg.getNaziv()+"!");
        }
    }

    public void ObrisiDrzvuINjeneGradove(ActionEvent actionEvent)
    {
        GeografijaDAO gdo = GeografijaDAO.getInstance();

        if(tfObrisi.getText().isEmpty() )
        {
            Alert upozori = new Alert(Alert.AlertType.WARNING);
            upozori.setTitle("UPOZORENJE");
            upozori.setContentText("Niste unijeli drzavu u namjenjeno polje!");
            upozori.showAndWait();
        }
        else if(true)
        {

        }
        else
        {
            izvrseno.setText("Dzrazava i njeni gradovi su izbrisani iz baze");
        }

    }
}
