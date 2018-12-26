package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Statement;
import java.util.ResourceBundle;

public class GrafikaController implements Initializable
{
    public TextField tfGGrad;
    public TextField tfObrisi;
    public Label izvrseno;

    public TableView<Grad> tvGradova;
    //kolone tabele
    public TableColumn tcIdGrada;
    public TableColumn tcNazivGrada;
    public TableColumn tcBrojStanovnika;
    public TableColumn tcIdOdgovoarajuceDrzave;

    public TableView<Drzava> tvDrzava;
    //kolone tabele2
    public TableColumn tcIdDrzave;
    public TableColumn tcNazivDrzave;
    public TableColumn tcIdGlavnogGrada;

    private ObservableList<Grad> listaGradova;
    private ObservableList<Drzava> listaDrzava;

    //ovo ga cini Modelom
    private GeografijaDAO gdo;

    GrafikaController(GeografijaDAO gdooo)
    {
        listaDrzava = FXCollections.observableArrayList();
        listaGradova = FXCollections.observableArrayList();

        gdo = gdooo;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        tcIdGrada.setCellValueFactory(new PropertyValueFactory<>("id_grada"));
        tcNazivGrada.setCellValueFactory(new PropertyValueFactory<>("naziv_grada"));
        tcBrojStanovnika.setCellValueFactory(new PropertyValueFactory<>("broj_stanovnika"));

        tcNazivDrzave.setCellValueFactory(new PropertyValueFactory<>("naziv_drzave"));
        tcIdDrzave.setCellValueFactory(new PropertyValueFactory<>("id_drzave"));

        tvDrzava.setItems(listaDrzava);
        tvGradova.setItems(listaGradova);

        listaGradova.addAll(FXCollections.observableArrayList(gdo.gradovi()));
        listaDrzava.addAll(FXCollections.observableArrayList(gdo.drzave()));
    }


    public void NadjiGlavniGrad(ActionEvent actionEvent)
    {
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
            upozori.setContentText("Unijeta drzava ne postoji u bazi!");
            upozori.showAndWait();
        }
        else
        {
            izvrseno.setText("Glavni grad "+tfGGrad.getText()+" je "+gg.getNaziv()+"!");
        }
    }

    public void ObrisiDrzvuINjeneGradove(ActionEvent actionEvent)
    {
        Drzava d = gdo.nadjiDrzavu(tfObrisi.getText());

        if(tfObrisi.getText().isEmpty() )
        {
            Alert upozori = new Alert(Alert.AlertType.WARNING);
            upozori.setTitle("UPOZORENJE");
            upozori.setContentText("Niste unijeli drzavu u namjenjeno polje!");
            upozori.showAndWait();
        }
        else if (d == null)
        {
            Alert upozori = new Alert(Alert.AlertType.WARNING);
            upozori.setTitle("UPOZORENJE");
            upozori.setContentText("Unijeta drzava ne postoji u bazi!");
            upozori.showAndWait();
        }
        else
        {
            gdo.obrisiDrzavu(d.getNaziv());

            izvrseno.setText("Dzrzava "+d.getNaziv()+" i njeni gradovi su izbrisani iz baze!");
        }



    }
}
