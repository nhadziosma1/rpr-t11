package ba.unsa.etf.rpr;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.TreeSet;

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
        String url = "jdbc:sqlite:baza.db";

        File db = new File("/baza.db");
        boolean postojiLi = db.exists();

        try
        {
            //Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url);
            st = conn.createStatement();

            //brise sve iz tabela
            String brisi_sve_grad = "DELETE FROM grad";
            Statement st0 = conn.createStatement();
            st0.executeUpdate(brisi_sve_grad);

            String brisi_sve_drzava = "DELETE FROM drzava";
            Statement st01 = conn.createStatement();
            st01.executeUpdate(brisi_sve_drzava);

            //ubacuje ponovo u tabele
            String unos1="INSERT INTO drzava VALUES(3, 'Francuska', 1)";
            Statement s_un1 = conn.createStatement();
            s_un1.executeUpdate(unos1);

            String unos2="INSERT INTO drzava VALUES(4, 'Velika Britanija', 2)";
            Statement s_un2 = conn.createStatement();
            s_un2.executeUpdate(unos2);

            String unos3="INSERT INTO drzava VALUES(5, 'Austrija', 3)";
            Statement s_un3 = conn.createStatement();
            s_un3.executeUpdate(unos3);

            String unos4="INSERT INTO grad VALUES(1, 'Pariz', 2206488, 3)";
            Statement s_un4 = conn.createStatement();
            s_un4.executeUpdate(unos4);

            String unos5="INSERT INTO grad VALUES(2, 'London', 8825000, 4)";
            Statement s_un5 = conn.createStatement();
            s_un5.executeUpdate(unos5);

            String unos6="INSERT INTO grad VALUES(3, 'Beč', 1899055, 5)";
            Statement s_un6 = conn.createStatement();
            s_un6.executeUpdate(unos6);

            String unos7="INSERT INTO grad VALUES(4, 'Manchester', 545500, 4)";
            Statement s_un7 = conn.createStatement();
            s_un7.executeUpdate(unos7);

            String unos8="INSERT INTO grad VALUES(5, 'Graz', 280200, 5)";
            Statement s_un8 = conn.createStatement();
            s_un8.executeUpdate(unos8);

            /*if(postojiLi==false)
            {
                PreparedStatement ps = conn.prepareStatement("INSERT INTO grad VALUES (?, ?, ?, ?)" ) ;

                ps.setInt(1, 1);
                ps.setString(2,"Pariz");
                ps.setInt(3, 100000);
                ps.setInt(4, 1);
                ps.executeUpdate();

                ps.setInt(1, 2);
                ps.setString(2,"London");
                ps.setInt(3, 200000);
                ps.setInt(4, 2);
                ps.executeUpdate();

                ps.setInt(1, 3);
                ps.setString(2,"Beč");
                ps.setInt(3, 300000);
                ps.setInt(4, 3);
                ps.executeUpdate();

                ps.setInt(1, 4);
                ps.setString(2,"Manchester");
                ps.setInt(3, 500000);
                ps.setInt(4, 4);
                ps.executeUpdate();

                ps.setInt(1, 5);
                ps.setString(2,"Graz");
                ps.setInt(3, 10000);
                ps.setInt(4, 5);
                ps.executeUpdate();

                PreparedStatement ps2 = conn.prepareStatement("INSERT INTO drzava VALUES (?, ?, ?)");

                ps2.setInt(1, 1);
                ps2.setString(2, "Francuska");
                ps2.setInt(3, 1);
                ps2.executeUpdate();

                ps2.setInt(1, 2);
                ps2.setString(2, "UK");
                ps2.setInt(3, 2);
                ps2.executeUpdate();

                ps2.setInt(1, 3);
                ps2.setString(2, "Austrija");
                ps2.setInt(3, 3);
                ps2.executeUpdate();
            }*/
            /*conn.close();     KADA ZATVARAM KONEKCIJU????????????*/
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

        try
        {
            st = conn.createStatement();

            String upit = "SELECT g.id, g.naziv, g.broj_stanovnika, g.drzava FROM drzava d," +
                          " grad g WHERE g.id=d.glavni_grad AND d.naziv='"+drzava+"'";


            ResultSet rs = st.executeQuery(upit);

            g.setId_grada(rs.getInt(1));
            g.setNaziv( rs.getString(2) );
            g.setBrojStanovnika( rs.getInt(3));

            int id_drz_fk = rs.getInt(4);


            st = conn.createStatement();
            String upit2 = "SELECT d.id, d.naziv, d.glavni_grad FROM drzava d WHERE d.id='"+id_drz_fk+"'";
            Drzava drz = new Drzava();
            rs = st.executeQuery(upit2);

            drz.setId_drzave(rs.getInt(1));
            drz.setNaziv( rs.getString(2));

            st = conn.createStatement();
            rs = st.executeQuery("SELECT g.id, g.naziv, g.broj_stanovnika, g.drzava FROM grad g, drzava d WHERE g.drzava='"+id_drz_fk+"'");

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
            //e.printStackTrace();
            return null;
        }

        return g;
    }

    void obrisiDrzavu(String drzava)
    {
        try
        {
            /*String str_pomocni = "INSERT INTO drzava (id, naziv, glavni_grad) VALUES (5, 'Austrija', 3)";
            Statement st_pomocni = conn.createStatement();
            st_pomocni.executeUpdate(str_pomocni);*/

            Statement st2 = conn.createStatement();
            String upit1 = "SELECT id FROM drzava WHERE naziv='"+drzava+"'";

            ResultSet rs2 = st2.executeQuery(upit1);
            int id_drzave = rs2.getInt(1);

            st = conn.createStatement();
            String komanda1 = "DELETE FROM drzava " +
                              "WHERE naziv='"+drzava+"'";
            st.executeUpdate(komanda1);

            Statement st3 = conn.createStatement();
            String upit2 = "SELECT id FROM grad WHERE drzava='"+id_drzave+"'";
            ResultSet rs3 = st3.executeQuery(upit2);

            while(rs3.next())
            {
                Statement st4 = conn.createStatement();
                String komanda2 = "DELETE FROM grad " +
                                  "WHERE id='"+rs3.getInt(1)+"'";

                st4.executeUpdate(komanda2);
            }

            /*String str_pomocni2 = "INSERT INTO drzava (id, naziv, glavni_grad) VALUES (5, 'Austrija', 3)";
            Statement st_pomocni2 = conn.createStatement();
            st_pomocni2.executeUpdate(str_pomocni2);

            String str_pomocni3 = "INSERT INTO drzava (id, naziv, glavni_grad) VALUES (5, 'Austrija', 3)";
            Statement st_pomocni3 = conn.createStatement();
            st_pomocni3.executeUpdate(str_pomocni3);
            */
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<Grad> gradovi()
    {
        ArrayList<Grad> alg = new ArrayList<>();

        try
        {
            st = conn.createStatement();
            String upit1 = "SELECT * FROM grad";

            ResultSet rs = st.executeQuery(upit1);

            while(rs.next())
            {
                Grad g = new Grad();

                g.setId_grada(rs.getInt(1));
                g.setNaziv(rs.getString(2));
                g.setBrojStanovnika(rs.getInt(3));

                int id_drz_fk = rs.getInt(4);

                Statement st2 = conn.createStatement();
                String upit2 = "SELECT d.id, d.naziv, d.glavni_grad FROM drzava d WHERE d.id='"+id_drz_fk+"'";
                Drzava drz = new Drzava();
                ResultSet rs2 = st2.executeQuery(upit2);

                drz.setId_drzave(rs2.getInt(1));
                drz.setNaziv( rs2.getString(2));

                Statement st3 = conn.createStatement();
                ResultSet rs3 = st3.executeQuery("SELECT g.id, g.naziv, g.broj_stanovnika, g.drzava FROM grad g, drzava d WHERE g.drzava='"+id_drz_fk+"'");

                Grad gg = new Grad();

                gg.setId_grada(rs3.getInt(1));
                gg.setNaziv(rs3.getString(2));
                gg.setBrojStanovnika(rs3.getInt(3));
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

        TreeSet<Grad> tsg =new TreeSet<>();
        tsg.addAll(alg);

        alg.clear();
        alg.addAll(tsg);

        return alg;
    }

    void dodajGrad(Grad g)
    {
        try
        {
            st = conn.createStatement();
            String komanda= "INSERT INTO grad VALUES ('"+g.getId_grada()+"','"+g.getNaziv()+"','"+g.getBrojStanovnika()+"', '"+g.getDrzava().getId_drzave()+"')";

            st.executeUpdate(komanda);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    void dodajDrzavu(Drzava d)
    {
        try
        {
            st = conn.createStatement();
            String komanda= "INSERT INTO drzava VALUES ('"+d.getId_drzave()+"' ,'"+d.getNaziv()+"' ,'"+d.getGlavniGrad().getId_grada()+"')";

            st.executeUpdate(komanda);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    void izmijeniGrad(Grad g)
    {
        try
        {
            st = conn.createStatement();
            String komanda = "UPDATE grad SET naziv='"+g.getNaziv()+"', broj_stanovnika='"+g.getBrojStanovnika()+"', drzava='"+g.getDrzava().getId_drzave()+"'" +
                    " WHERE id='"+g.getId_grada()+"'";

            st.executeUpdate(komanda);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    Drzava nadjiDrzavu(String d)
    {

        Drzava drz= new Drzava();

        try
        {
            st = conn.createStatement();
            String upit1 = "SELECT * FROM drzava WHERE naziv='"+d+"'";

            ResultSet rs = st.executeQuery(upit1);

            drz.setId_drzave(rs.getInt(1));
            drz.setNaziv(rs.getString(2));

            int id_gr_fk = rs.getInt(3);

            st = conn.createStatement();
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
