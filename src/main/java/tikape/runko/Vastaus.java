
package tikape.runko;

public class Vastaus {
    private int id;
    private String vastausteksti;
    private Boolean oikein;

    public Vastaus(String teksti){
        this.vastausteksti = teksti;
    }
    public Vastaus(String teksti, Boolean totta){
        this.vastausteksti = teksti;
        this.oikein = totta;
    }
    public Vastaus(int id, String teksti, Boolean oikein) {
        this.id = id;
        this.vastausteksti = teksti;
        this.oikein=oikein;
    }

    public int getId() {
        return id;
    }

    public Boolean getOikein() {
        return oikein;
    }

    public String getVastausteksti() {
        return vastausteksti;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVastausteksti(String vastausteksti) {
        this.vastausteksti = vastausteksti;
    }

    public void setOikein(Boolean oikein) {
        this.oikein = oikein;
    }

    
    
    
}
