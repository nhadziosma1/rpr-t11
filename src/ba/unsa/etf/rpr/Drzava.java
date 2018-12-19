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

    public String getNaziv() {
        return naziv_drzave;
    }

    public void setNaziv(String naziv_drzave) {
        this.naziv_drzave = naziv_drzave;
    }

    public Grad getGlavniGrad() {
        return glavni_grad;
    }

    public void setGlavniGrad(Grad glavni_grad) {
        this.glavni_grad = glavni_grad;
    }
}
