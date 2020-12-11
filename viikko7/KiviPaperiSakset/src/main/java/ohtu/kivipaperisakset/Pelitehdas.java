package ohtu.kivipaperisakset;

import java.util.HashMap;

public class Pelitehdas {
  
    private HashMap<String, KiviPaperiSakset> pelit;

    public Pelitehdas(IO io) {
        pelit = new HashMap<>();
        pelit.put("a", KiviPaperiSakset.aloitaPelaajaaVastaan(io));
        pelit.put("b", KiviPaperiSakset.aloitaKonettaVastaan(io, new Tekoaly()));
        pelit.put("c", KiviPaperiSakset.aloitaKonettaVastaan(io, new TekoalyParannettu(20)));
    }

    public KiviPaperiSakset valitse(String komento) {
        return pelit.getOrDefault(komento, null);
    }
  
}
