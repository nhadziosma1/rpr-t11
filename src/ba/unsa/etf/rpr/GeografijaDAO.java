package ba.unsa.etf.rpr;

import java.sql.*;
import java.util.ArrayList;

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
            PreparedStatement ps8 = conn.prepareStatement("INSERT INTO drzava VALUES (1, Austrija, Beč");

            ResultSet rs = st.executeUpdate()*/


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

    public static void removeInstance()
    {
        instanca=null;
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
            g.setNaziv( rs.getString(2) );
            g.setBrojStanovnika( rs.getInt(3));

            int id_drz_fk = rs.getInt(4);

            String upit2 = "SELECT d.id, d.naziv, d.glavni_grad FROM drzava d WHERE d.id='"+id_drz_fk+"'";
            Drzava drz = new Drzava();
            rs = st.executeQuery(upit2);

            drz.setId_drzave(rs.getInt(1));
            drz.setNaziv( rs.getString(1));

            rs = st.executeQuery("SELECT g.id, g.naziv, g.broj_stanovnika, g.drzava FROM grad g, drzava d WHERE g.drzva='"+id_drz_fk+"'");

            Grad gg = new Grad();

            gg.setId_grada(rs.getInt(1));
            gg.setNaziv(rs.getString(2));
            gg.setBrojStanovnika(rs.getInt(3));
            gg.setDrzava(drz);

            drz.setGlavniGrad(gg);

            g.setDrzava(drz);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return g;
    }

    void obrisiDrzavu(String drzava)
    {
        String komanda1 = "DELETE FROM drzava " +
                          "WHERE naziv='"+drzava+"'";

        String upit1 = "SELECT id FROM drzava WHERE naziv='"+drzava+"'";

        try
        {
            st.executeUpdate(komanda1);

            ResultSet rs = st.executeQuery(upit1);
            int id_drzave = rs.getInt(1);

            String upit2 = "SELECT id FROM grad WHERE drzava='"+id_drzave+"'";
            rs = st.executeQuery(upit2);

            while(rs.next())
            {
                String komanda2 = "DELETE FROM grad " +
                                  "WHERE id='"+rs.getInt(1)+"'";

                st.executeUpdate(komanda2);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    ArrayList<Grad> gradovi()
    {
        ArrayList<Grad> alg = new ArrayList<>();

        String upit1 = "SELECT * FROM grad";

        try
        {
            ResultSet rs = st.executeQuery(upit1);

            while(rs.next())
            {
                Grad g = new Grad();

                g.setId_grada(rs.getInt(1));
                g.setNaziv(rs.getString(2));
                g.setBrojStanovnika(rs.getInt(3));

                int id_drz_fk = rs.getInt(4);

                String upit2 = "SELECT d.id, d.naziv, d.glavni_grad FROM drzava d WHERE d.id='"+id_drz_fk+"'";
                Drzava drz = new Drzava();
                rs = st.executeQuery(upit2);

                drz.setId_drzave(rs.getInt(1));
                drz.setNaziv( rs.getString(1));

                rs = st.executeQuery("SELECT g.id, g.naziv, g.broj_stanovnika, g.drzava FROM grad g, drzava d WHERE g.drzva='"+id_drz_fk+"'");

                Grad gg = new Grad();

                gg.setId_grada(rs.getInt(1));
                gg.setNaziv(rs.getString(2));
                gg.setBrojStanovnika(rs.getInt(3));
                gg.setDrzava(drz);

                drz.setGlavniGrad(gg);

                g.setDrzava(drz);

                alg.add(g);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return alg;
    }

    void dodajGrad(Grad g)
    {
        String komanda= "INSERT INTO grad VALUES ("+g.getId_grada()+","+g.getNaziv()+","+g.getBrojStanovnika()+","+g.getDrzava().getId_drzave()+")";

        try
        {
            st.executeUpdate(komanda);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    void dodajDrzavu(Drzava d)
    {
        String komanda= "INSERT INTO drzava VALUES ("+d.getId_drzave()+","+d.getNaziv()+","+d.getGlavniGrad().getId_grada()+")";

        try
        {
            st.executeUpdate(komanda);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    void izmijeniGrad(Grad g)
    {
        String komanda = "UPDATE grad SET id=111, naziv=Breza, broj_stanovnika=10, drzava=3 " +
                         "WHERE id='"+g.getId_grada()+"'";

        try
        {
            st.executeUpdate(komanda);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    Drzava nadjiDrzavu(String d)
    {
        String upit1 = "SELECT * FROM drzava WHERE naziv='"+d+"'";

        Drzava drz= new Drzava();

        try
        {
            ResultSet rs = st.executeQuery(upit1);

            drz.setId_drzave(rs.getInt(1));
            drz.setNaziv(rs.getString(2));

            int id_gr_fk = rs.getInt(3);

            String upit2 = "SELECT * FROM grad WHERE id='"+id_gr_fk+"'";

            rs = st.executeQuery(upit2);

            Grad g = new Grad();

            g.setId_grada( rs.getInt(1));
            g.setNaziv( rs.getString(2));
            g.setBrojStanovnika( rs.getInt(3));
            g.setDrzava(drz);

            drz.setGlavniGrad(g);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return drz;
    }

}
