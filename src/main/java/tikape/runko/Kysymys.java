package tikape.runko;

import java.util.ArrayList;
import java.util.List;

public class Kysymys {
        private int id;
        private String kurssi;
        private String aihe;
        private String kysymysteksti;
        private List<Vastaus> vastaukset;

    public Kysymys(String kurssi, String aihe, String teksti) {
        this.kurssi = kurssi;
        this.aihe = aihe;
        this.kysymysteksti = teksti;
        this.vastaukset = new ArrayList<>();
    }

    public String getAihe() {
        return aihe;
    }

    public String getKurssi() {
        return kurssi;
    }

    public int getId() {
        return id;
    }

    public String getKysymysteksti() {
        return kysymysteksti;
    }

    public void setAihe(String aihe) {
        this.aihe = aihe;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setKurssi(String kurssi) {
        this.kurssi = kurssi;
    }

    public void setKysymysteksti(String teksti) {
        this.kysymysteksti = teksti;
    }

    public List<Vastaus> getVastaukset() {
        return vastaukset;
    }

    public void setVastaukset(List<Vastaus> vastaukset) {
        this.vastaukset = vastaukset;
    }
    
    
    
}
