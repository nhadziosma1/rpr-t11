package ba.unsa.etf.rpr;

public class Grad implements Comparable<Grad>
{
    private int id_grada;
    private String naziv;
    private int brojStanovnika;
    private Drzava drzava;

    public int getId_grada() {
        return id_grada;
    }

    public void setId_grada(int id_grada) {
        this.id_grada = id_grada;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv_grada) {
        this.naziv = naziv_grada;
    }

    public int getBrojStanovnika() {
        return brojStanovnika;
    }

    public void setBrojStanovnika(int broj_stanovnika) {
        this.brojStanovnika = broj_stanovnika;
    }

    public Drzava getDrzava() {
        return drzava;
    }

    public void setDrzava(Drzava d) {
        this.drzava = d;
    }


    @Override
    public int compareTo(Grad o)
    {
        if(this.getBrojStanovnika() < o.getBrojStanovnika())
            return 1;
        else if(this.getBrojStanovnika() > o.getBrojStanovnika())
            return -1;
        else
            return 0;
    }
}
