package ba.unsa.etf.rpr;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.TreeSet;

//ZAKOMENTARISANO PISE VERZIJA U KOJOJ SE SLUZE OBICNI Statementi, DOK NEZAKOMENTARISANA VERZIJA JE SA PreparedStatementima
public class GeografijaDAO
{
    private static GeografijaDAO instanca = null;
    private Connection conn;
    private Statement st;

    private PreparedStatement ps1, ps2, ps3, ps4, ps5, ps6, sp7;

    private static void initialize()
    {
        instanca = new GeografijaDAO();
    }

    private GeografijaDAO()
    {
        String url = "jdbc:sqlite:baza.db";

        File db = new File("baza.db");
        boolean postojiLi = db.exists();

        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*try
        {
            ps1 = conn.prepareStatement("INSERT INTO grad VALUES (?, ?, ?, ?)" );
            ps2 = conn.prepareStatement("INSERT INTO drzava VALUES (?, ?, ?)");
            ps3 = conn.prepareStatement("SELECT g.id, g.naziv, g.broj_stanovnika, g.drzava FROM drzava d," +
                    " grad g WHERE g.id=d.glavni_grad AND d.naziv=?");

            ps4 = conn.prepareStatement("SELECT d.id, d.naziv, d.glavni_grad FROM drzava d WHERE d.id=?");
            ps5 = conn.prepareStatement()
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }*/

        try
        {
            //Class.forName("com.mysql.jdbc.Driver");

            /*if(postojiLi == true) { //tj ako postoji ovaj fajl
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
                String unos1 = "INSERT INTO drzava VALUES(3, 'Francuska', 1)";
                Statement s_un1 = conn.createStatement();
                s_un1.executeUpdate(unos1);

                String unos2 = "INSERT INTO drzava VALUES(4, 'Velika Britanija', 2)";
                Statement s_un2 = conn.createStatement();
                s_un2.executeUpdate(unos2);

                String unos3 = "INSERT INTO drzava VALUES(5, 'Austrija', 3)";
                Statement s_un3 = conn.createStatement();
                s_un3.executeUpdate(unos3);

                String unos4 = "INSERT INTO grad VALUES(1, 'Pariz', 2206488, 3)";
                Statement s_un4 = conn.createStatement();
                s_un4.executeUpdate(unos4);

                String unos5 = "INSERT INTO grad VALUES(2, 'London', 8825000, 4)";
                Statement s_un5 = conn.createStatement();
                s_un5.executeUpdate(unos5);

                String unos6 = "INSERT INTO grad VALUES(3, 'Beč', 1899055, 5)";
                Statement s_un6 = conn.createStatement();
                s_un6.executeUpdate(unos6);

                String unos7 = "INSERT INTO grad VALUES(4, 'Manchester', 545500, 4)";
                Statement s_un7 = conn.createStatement();
                s_un7.executeUpdate(unos7);

                String unos8 = "INSERT INTO grad VALUES(5, 'Graz', 280200, 5)";
                Statement s_un8 = conn.createStatement();
                s_un8.executeUpdate(unos8);
            }*/


            if(postojiLi==true)
            {
                //brise sve iz tabela
                String brisi_sve_grad = "DELETE FROM grad";
                Statement st0 = conn.createStatement();
                st0.executeUpdate(brisi_sve_grad);

                String brisi_sve_drzava = "DELETE FROM drzava";
                Statement st01 = conn.createStatement();
                st01.executeUpdate(brisi_sve_drzava);

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
                ps.setInt(4, 2);
                ps.executeUpdate();

                ps.setInt(1, 5);
                ps.setString(2,"Graz");
                ps.setInt(3, 10000);
                ps.setInt(4, 3);
                ps.executeUpdate();

                /*ps.setInt(1, 6);
                ps.setString(2,"RajvoSa");
                ps.setInt(3, 30000);
                ps.setInt(4, 4);
                ps.executeUpdate();*/

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

                /*ps2.setInt(1, 4);
                ps2.setString(2, "Bosna i Hercegovina");
                ps2.setInt(3, 6);
                ps2.executeUpdate();*/
            }

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
            /*st = conn.createStatement();
            String upit = "SELECT g.id, g.naziv, g.broj_stanovnika, g.drzava FROM drzava d," +
                          " grad g WHERE g.id=d.glavni_grad AND d.naziv='"+drzava+"'";*/

            PreparedStatement traziGradPoNazivu = conn.prepareStatement
                    ("SELECT g.id, g.naziv, g.broj_stanovnika, g.drzava FROM drzava d," +
                                                " grad g WHERE g.id=d.glavni_grad AND d.naziv=?");
            traziGradPoNazivu.setString(1, drzava);

            ResultSet rs = traziGradPoNazivu.executeQuery();

            g.setId_grada( rs.getInt(1));
            g.setNaziv( rs.getString(2) );
            g.setBrojStanovnika( rs.getInt(3));

            int id_drz_fk = rs.getInt(4);


            /*st = conn.createStatement();
            String upit2 = "SELECT d.id, d.naziv, d.glavni_grad FROM drzava d WHERE d.id='"+id_drz_fk+"'";*/

            PreparedStatement traziDrzavaPoId = conn.prepareStatement
                    ("SELECT d.id, d.naziv, d.glavni_grad FROM drzava d WHERE d.id=?");
            traziDrzavaPoId.setInt(1, id_drz_fk);

            Drzava drz = new Drzava();
            rs = traziDrzavaPoId.executeQuery();

            drz.setId_drzave( rs.getInt(1));
            drz.setNaziv( rs.getString(2));

            /*st = conn.createStatement();
            rs = st.executeQuery("SELECT g.id, g.naziv, g.broj_stanovnika, g.drzava FROM grad g, drzava d WHERE g.drzava='"+id_drz_fk+"'");*/

            PreparedStatement traziGradPoId = conn.prepareStatement
                    ("SELECT g.id, g.naziv, g.broj_stanovnika, g.drzava FROM grad g, drzava d WHERE g.drzava=?");

            traziGradPoId.setInt(1, id_drz_fk);

            Grad gg = new Grad();

            gg.setId_grada( rs.getInt(1));
            gg.setNaziv( rs.getString(2));
            gg.setBrojStanovnika( rs.getInt(3));
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
            /* Statement st2 = conn.createStatement();
            String upit1 = "SELECT id FROM drzava WHERE naziv='"+drzava+"'";

            ResultSet rs2 = st2.executeQuery(upit1);
            int id_drzave = rs2.getInt(1);*/

            PreparedStatement traziDrzavuPoNazivu = conn.prepareStatement("SELECT id FROM drzava WHERE naziv=?");
            traziDrzavuPoNazivu.setString(1,drzava);

            ResultSet rs2 = traziDrzavuPoNazivu.executeQuery();
            int id_drzave = rs2.getInt(1);

            /*st = conn.createStatement();
            String komanda1 = "DELETE FROM drzava " +
                              "WHERE naziv='"+drzava+"'";
            st.executeUpdate(komanda1);*/

            PreparedStatement brisiDrzavePoNazivu = conn.prepareStatement
                    ("DELETE FROM drzava " +
                    "WHERE naziv=?");
            brisiDrzavePoNazivu.setString(1, drzava);
            brisiDrzavePoNazivu.executeQuery();

            /*Statement st3 = conn.createStatement();
            String upit2 = "SELECT id FROM grad WHERE drzava='"+id_drzave+"'";
            ResultSet rs3 = st3.executeQuery(upit2);*/

            PreparedStatement traziDrzavaPoId = conn.prepareStatement
                    ("SELECT d.id, d.naziv, d.glavni_grad FROM drzava d WHERE d.id=?");
            traziDrzavaPoId.setInt(1, id_drzave);
            ResultSet rs3 = traziDrzavaPoId.executeQuery();

            PreparedStatement brisiGradPoId = conn.prepareStatement
                    ("DELETE FROM grad " +
                            "WHERE id=?");

            while(rs3.next())
            {
                brisiGradPoId.setInt(1, rs3.getInt("id"));
                /*Statement st4 = conn.createStatement();
                String komanda2 = "DELETE FROM grad " +
                                  "WHERE id='"+rs3.getInt(1)+"'";

                st4.executeUpdate(komanda2);*/
            }
        }
        catch (SQLException e)
        {
            e.getMessage();
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

                g.setId_grada(rs.getInt("id"));
                g.setNaziv(rs.getString("naziv"));
                g.setBrojStanovnika(rs.getInt("broj_stanovnika"));

                int id_drz_fk = rs.getInt("drzava");

                /*Statement st2 = conn.createStatement();
                String upit2 = "SELECT d.id, d.naziv, d.glavni_grad FROM drzava d WHERE d.id='"+id_drz_fk+"'";
                ResultSet rs2 = st2.executeQuery(upit2);*/

                PreparedStatement traziDrzavaPoId = conn.prepareStatement
                        ("SELECT d.id, d.naziv, d.glavni_grad FROM drzava d WHERE d.id=?");
                traziDrzavaPoId.setInt(1, id_drz_fk);
                ResultSet rs2 = traziDrzavaPoId.executeQuery();

                Drzava drz = new Drzava();

                drz.setId_drzave(rs2.getInt("id"));
                drz.setNaziv( rs2.getString("naziv"));

                /*Statement st3 = conn.createStatement();
                ResultSet rs3 = st3.executeQuery("SELECT g.id, g.naziv, g.broj_stanovnika, g.drzava " +
                                                     "FROM grad g WHERE g.drzava='"+id_drz_fk+"'");*/

                PreparedStatement traziGradPoDrzavi = conn.prepareStatement(
                        "SELECT g.id, g.naziv, g.broj_stanovnika, g.drzava " +
                             "FROM grad g WHERE g.drzava=?");
                traziGradPoDrzavi.setInt(1, id_drz_fk);

                ResultSet rs3 = traziGradPoDrzavi.executeQuery();

                Grad gg = new Grad();

                gg.setId_grada(rs3.getInt("id"));
                gg.setNaziv(rs3.getString("naziv"));
                gg.setBrojStanovnika(rs3.getInt("broj_stanovnika"));
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
            PreparedStatement dodajGrad = conn.prepareStatement("INSERT INTO grad VALUES (?, ?, ?, ?)");
            dodajGrad.setInt(1, g.getId_grada());
            dodajGrad.setString(2, g.getNaziv());
            dodajGrad.setInt(3, g.getBrojStanovnika());
            dodajGrad.setInt(4, g.getDrzava().getId_drzave());

            dodajGrad.executeUpdate();

            /*st = conn.createStatement();
            String komanda= "INSERT INTO grad VALUES ('"+g.getId_grada()+"','"+g.getNaziv()+"','"+g.getBrojStanovnika()+"', '"+g.getDrzava().getId_drzave()+"')";

            st.executeUpdate(komanda);*/
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
            /*st = conn.createStatement();
            String komanda= "INSERT INTO drzava VALUES ('"+d.getId_drzave()+"' ,'"+d.getNaziv()+"' ,'"+d.getGlavniGrad().getId_grada()+"')";
            st.executeUpdate(komanda);*/

            PreparedStatement dodajDarzavu = conn.prepareStatement("INSERT INTO drzava VALUES (?, ?, ?)");
            dodajDarzavu.setInt(1, d.getId_drzave());
            dodajDarzavu.setString(2, d.getNaziv());
            dodajDarzavu.setInt( 3, d.getGlavniGrad().getId_grada() );

            dodajDarzavu.executeUpdate();
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
            PreparedStatement promijeniGrad = conn.prepareStatement(
                    "UPDATE grad SET naziv=?, broj_stanovnika=?, drzava=?" +
                         "WHERE id=?");
            promijeniGrad.setString(1, g.getNaziv());
            promijeniGrad.setInt(2, g.getBrojStanovnika());
            promijeniGrad.setInt(3, g.getDrzava().getId_drzave());
            promijeniGrad.setInt(4, g.getId_grada());

            promijeniGrad.executeQuery();
            /*st = conn.createStatement();
            String komanda = "UPDATE grad SET naziv='"+g.getNaziv()+"', broj_stanovnika='"+g.getBrojStanovnika()+"', drzava='"+g.getDrzava().getId_drzave()+"'" +
                    " WHERE id='"+g.getId_grada()+"'";

            st.executeUpdate(komanda);*/
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
