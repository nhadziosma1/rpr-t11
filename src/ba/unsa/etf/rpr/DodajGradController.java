package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DodajGradController implements Initializable
{
    public TextField tfNazivGrada;
    public TextField tfBrojStanovnika;
    public TextField tfGradDrzava;

    GeografijaDAO gdo;

    public DodajGradController(GeografijaDAO gdooo)
    {
        gdo = gdooo;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }
    
    public void DodajGrad(ActionEvent actionEvent) 
    {
        if(tfBrojStanovnika.getText().isEmpty() || tfGradDrzava.getText().isEmpty() || tfNazivGrada.getText().isEmpty())
        {
            Alert upozorenje = new Alert( Alert.AlertType.WARNING );

            upozorenje.setTitle("UPOZORENJE!");
            upozorenje.setHeaderText("Pazite sljedeći put!");
            upozorenje.setContentText("Niste unijeli sve potrebne informacije");
            upozorenje.showAndWait();
        }
        else
        {

            /*Stage stejdz = (Stage)tfBrojStanovnikaGlavnogGrada.getScene().getWindow();
            stejdz.close();*/
        }
    }
}

