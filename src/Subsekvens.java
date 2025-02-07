public class Subsekvens {
    public int antall;
    public String sekvens;

    public Subsekvens(String sekvensen, int antalle){
        sekvens = sekvensen;
        antall = antalle;
    } 

    public void PlussForekomster(int tall){
        antall =tall;
    }

    public Integer hentAntForekomster(){
        return antall;
    }

    public String hentSekvens(){
        return sekvens;
    }

    public String toString(){
        return "("+ sekvens + "," + antall + ")";
    }
}
