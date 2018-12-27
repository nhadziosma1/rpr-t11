package ba.unsa.etf.rpr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

//import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class Main extends Application
{
    private static GeografijaDAO gdao;

    public static void main(String[] args)
    {

        /*GeografijaDAO dao = GeografijaDAO.getInstance();
        ArrayList<Grad> gradovi = dao.gradovi();
        System.out.println(gradovi.get(0).getNaziv()+" "+gradovi.get(2).getNaziv());*/

        launch(args);
    }

    static String ispisiGradove()
    {
        gdao = GeografijaDAO.getInstance();

        ArrayList<Grad> arl = gdao.gradovi();

        String povratni = new String();

        for(int i=0; i<arl.size(); i++)
        {
            povratni = povratni + arl.get(i).getNaziv()+" ("+arl.get(i).getDrzava().getNaziv()+") - "+arl.get(i).getBrojStanovnika()+"\n";
        }

        return  povratni;
    }

    static void glavniGrad()
    {
        System.out.println("Unesite ime drzave: ");
        Scanner ulaz = new Scanner(System.in);

        String drz = ulaz.nextLine();

    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("grafika.fxml"), bundle);

        GeografijaDAO gdo = GeografijaDAO.getInstance();

        loader.setController(new GrafikaController(gdo));
        Parent root = loader.load();
        primaryStage.setTitle("zadatak 2");
        primaryStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        primaryStage.show();
    }
}
