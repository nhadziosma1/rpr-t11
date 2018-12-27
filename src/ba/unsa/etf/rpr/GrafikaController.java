package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Statement;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

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

    public TableView<Drzava> tvDrzava;
    //kolone tabele2
    public TableColumn tcIdDrzave;
    public TableColumn tcNazivDrzave;

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
        tcNazivGrada.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        tcBrojStanovnika.setCellValueFactory(new PropertyValueFactory<>("brojStanovnika"));

        tcNazivDrzave.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        tcIdDrzave.setCellValueFactory(new PropertyValueFactory<>("id_drzave"));

        tvDrzava.setItems(listaDrzava);
        tvGradova.setItems(listaGradova);

        listaGradova.addAll(FXCollections.observableArrayList(gdo.gradovi()));
        listaDrzava.addAll( FXCollections.observableArrayList(gdo.drzave()));
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

            //ZASTO SE NE POZOVE INITIALIZE, NEGO OVO MORAM RUCNO PISAT?????????
            listaDrzava.clear();
            listaGradova.clear();
            listaGradova.addAll(FXCollections.observableArrayList(gdo.gradovi()));
            listaDrzava.addAll( FXCollections.observableArrayList(gdo.drzave()));

            izvrseno.setText("Dzrzava "+d.getNaziv()+" i njeni gradovi su izbrisani iz baze!");
            System.out.println("Broj gradova nakon izbacivanja drzave "+d.getNaziv()+" "+gdo.gradovi().size());
        }
    }

    public void DodajDrzavu(ActionEvent actionEvent)
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("dodajDrzavu.fxml"));
        loader.setController(new dodajDrzavuController(gdo));

        try
        {
            Parent root = loader.load();

            Stage aboutStage = new Stage();
            aboutStage.setTitle("Informacije o drzavi koja se dodaje");
            aboutStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            aboutStage.show();

            listaDrzava.clear();
            listaGradova.clear();
            listaGradova.addAll(FXCollections.observableArrayList(gdo.gradovi()));
            listaDrzava.addAll( FXCollections.observableArrayList(gdo.drzave()));
        }
        catch(Exception e)
        {
            System.out.println("ne ucitava se za dodavanje drzave");
        }
    }

    public void DodajGrad(ActionEvent actionEvent)
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("dodajGrad.fxml"));
        loader.setController(new DodajGradController(gdo));

        try
        {
            Parent root = loader.load();

            Stage aboutStage = new Stage();
            aboutStage.setTitle("Informacije o gradu koja se dodaje");
            aboutStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            aboutStage.show();

            listaDrzava.clear();
            listaGradova.clear();
            listaGradova.addAll(FXCollections.observableArrayList(gdo.gradovi()));
            listaDrzava.addAll( FXCollections.observableArrayList(gdo.drzave()));
        }
        catch(Exception e)
        {
            System.out.println("nesto ne valja pri otvaranju prozora za dodavanje grada");
        }
    }
}
