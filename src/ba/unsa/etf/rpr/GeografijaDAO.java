package ba.unsa.etf.rpr;

import java.sql.*;

public class GeografijaDAO
{
    private static GeografijaDAO instanca = null;
    private Connection conn;
    private Statement st;

    private static void initialize()
    {
        instanca = new GeografijaDAO();
    }

    private GeografijaDAO()
    {
        String url = "jdbc:sqlite:proba.db";

        try
        {
            //Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url);
            st = conn.createStatement();

            /*PreparedStatement ps1 = conn.prepareStatement("INSERT INTO grad VALUES (1, Pariz, 1000000, 1");
            PreparedStatement ps2 = conn.prepareStatement("INSERT INTO grad VALUES (2, London, 2000000, 2");
            PreparedStatement ps3 = conn.prepareStatement("INSERT INTO grad VALUES (3, Beč, 3000000, 3");
            PreparedStatement ps4 = conn.prepareStatement("INSERT INTO grad VALUES (4, Manchester, 5000000, 2");
            PreparedStatement ps5 = conn.prepareStatement("INSERT INTO grad VALUES (5, Graz, 4000000, 3");

            PreparedStatement ps6 = conn.prepareStatement("INSERT INTO drzava VALUES (1, Francuska, Pariz");
            PreparedStatement ps7 = conn.prepareStatement("INSERT INTO drzava VALUES (1, UK, London");
            PreparedStatement ps8 = conn.prepareStatement("INSERT INTO drzava VALUES (1, Austrija, Beč");*/


            /*Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(upit);

            float ukupno = 0;
            while(result.next())
            {
                String naziv = result.getString(1);
                float cijena = result.getFloat(2);
                System.out.println (naziv + " " + cijena);
                ukupno += cijena;
            }
            System.out.println("UKUPNO: "+ukupno);
            conn.close();*/

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    public static GeografijaDAO getInstance()
    {
        if(instanca == null)
            initialize();

        return instanca;
    }

    Grad glavniGrad(String drzava)
    {
        Grad g = new Grad();

        String upit = "SELECT g.id, g.naziv, g.broj_stanovnika, g.drzava FROM drzava d," +
                      " grad g WHERE g.id=d.glavni_grad AND d.naziv='"+drzava+"'";

        try
        {
            ResultSet rs = st.executeQuery(upit);

            g.setId_grada(rs.getInt(1));
            g.setNaziv_grada( rs.getString(2) );
            g.setBroj_stanovnika( rs.getInt(3));

            int id_d = rs.getInt(4);

            String upit2 = "SELECT d.id, d.naziv, d.glavni_grad FROM drzava d WHERE d.id='"+id_d+"'";
            Drzava drz = new Drzava();
            rs = st.executeQuery(upit2);

            drz.setId_drzave(rs.getInt(1));
            drz.setNaziv_drzave( rs.getString(1));

            rs = st.executeQuery("SELECT g.id, g.naziv, g.broj_stanovnika, g.drzava FROM grad g, drzava d WHERE g.drzva='"+id_d+"'");

            Grad gg = new Grad();

            gg.setId_grada(rs.getInt(1));
            gg.setNaziv_grada(rs.getString(2));
            gg.setBroj_stanovnika(rs.getInt(3));
            gg.setD(drz);

            drz.setGlavni_grad(gg);

            g.setD(drz);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
