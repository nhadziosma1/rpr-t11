package ba.unsa.etf.rpr;

public class Drzava implements Comparable
{

    private int id_drzave;
    private String naziv;
    private Grad glavniGrad;

    public int getId_drzave() {
        return id_drzave;
    }

    public void setId_drzave(int id_drzave) {
        this.id_drzave = id_drzave;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv_drzave) {
        this.naziv = naziv_drzave;
    }

    public Grad getGlavniGrad() {
        return glavniGrad;
    }

    public void setGlavniGrad(Grad glavni_grad) {
        this.glavniGrad = glavni_grad;
    }


    @Override
    public int compareTo(Object o)
    {
        if(this.getId_drzave() < ((Drzava)o).getId_drzave() )
            return 1;
        else if(this.getId_drzave() > ((Drzava)o).getId_drzave() )
            return -1;
        else
        return 0;
    }
}
