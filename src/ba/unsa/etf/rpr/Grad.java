package ba.unsa.etf.rpr;

public class Grad
{
    private int id_grada;
    private String naziv_grada;
    private int broj_stanovnika;
    private Drzava d;

    public int getId_grada() {
        return id_grada;
    }

    public void setId_grada(int id_grada) {
        this.id_grada = id_grada;
    }

    public String getNaziv() {
        return naziv_grada;
    }

    public void setNaziv(String naziv_grada) {
        this.naziv_grada = naziv_grada;
    }

    public int getBrojStanovnika() {
        return broj_stanovnika;
    }

    public void setBrojStanovnika(int broj_stanovnika) {
        this.broj_stanovnika = broj_stanovnika;
    }

    public Drzava getDrzava() {
        return d;
    }

    public void setDrzava(Drzava d) {
        this.d = d;
    }
}
