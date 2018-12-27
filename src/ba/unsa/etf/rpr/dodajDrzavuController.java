package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class dodajDrzavuController implements Initializable
{
    public TextField tfNazivDrzave;
    public TextField tfGlavniGrad;
    public TextField tfBrojStanovnikaGlavnogGrada;

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
        if(tfNazivDrzave.getText().isEmpty() || tfGlavniGrad.getText().isEmpty() || tfBrojStanovnikaGlavnogGrada.getText().isEmpty())
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
            d.setNaziv(tfNazivDrzave.getText());

            Grad g = new Grad();
            g.setNaziv(tfGlavniGrad.getText());
            g.setBrojStanovnika(Integer.parseInt(tfBrojStanovnikaGlavnogGrada.getText()));

            g.setDrzava(d);
            gdo.dodajGrad(g);

            d.setGlavniGrad(g);

            gdo.dodajDrzavu(d);

            System.out.println("Nakon dodavanja noge drzave ima "+gdo.gradovi().size()+" gradova!");

            Stage stejdz = (Stage)tfBrojStanovnikaGlavnogGrada.getScene().getWindow();
            stejdz.close();
        }
    }
}
