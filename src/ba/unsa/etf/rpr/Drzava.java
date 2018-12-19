package ba.unsa.etf.rpr;

public class Drzava
{
    private int id_drzave;
    private String naziv_drzave;
    private Grad glavni_grad;

    public int getId_drzave() {
        return id_drzave;
    }

    public void setId_drzave(int id_drzave) {
        this.id_drzave = id_drzave;
    }

    public String getNaziv_drzave() {
        return naziv_drzave;
    }

    public void setNaziv_drzave(String naziv_drzave) {
        this.naziv_drzave = naziv_drzave;
    }

    public Grad getGlavni_grad() {
        return glavni_grad;
    }

    public void setGlavni_grad(Grad glavni_grad) {
        this.glavni_grad = glavni_grad;
    }
}
