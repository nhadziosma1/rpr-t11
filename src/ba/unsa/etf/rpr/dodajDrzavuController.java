package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class dodajDrzavuController implements Initializable
{
    public TextField tfNazivDrzave;
    public TextField tfGlavniGrad;

    GeografijaDAO gdo;

    public dodajDrzavuController(GeografijaDAO gdooo)
    {
        gdo = gdooo;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }


    public void DodajDrzavu(ActionEvent actionEvent)
    {
        if(tfNazivDrzave.getText().isEmpty() || tfGlavniGrad.getText().isEmpty())
        {
            Alert upozorenje = new Alert( Alert.AlertType.WARNING );

            upozorenje.setTitle("UPOZORENJE!");
            upozorenje.setHeaderText("Pazite sljedeći put!");
            upozorenje.setContentText("Niste unijeli sve potrebne informacije");
            upozorenje.showAndWait();
        }
        else
        {
            Drzava d = new Drzava();
            d.setGlavniGrad(gdo.nadjiGrad(tfGlavniGrad.getText()));
            d.setNaziv(tfNazivDrzave.getText());

            gdo.dodajDrzavu(d);

        }
    }
}
