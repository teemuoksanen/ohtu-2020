package ohtu.kivipaperisakset;

public abstract class KiviPaperiSakset {

    protected IO io;
    private Tuomari tuomari;

    public static KiviPaperiSakset aloitaPelaajaaVastaan(IO io) {
        return new KPSPelaajaVsPelaaja(io);
    }

    public static KiviPaperiSakset aloitaKonettaVastaan(IO io, Kone kone) {
        return new KPSTekoaly(io, kone);
    }

    protected KiviPaperiSakset(IO io) {
        this.io = io;
        this.tuomari = new Tuomari();
    }
    
    public void pelaa() {
        System.out.println("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
        
        String ekanSiirto = ensimmaisenSiirto();
        String tokanSiirto = toisenSiirto();
        
        while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            io.tulosta(tuomari);
            io.tulosta("");

            ekanSiirto = ensimmaisenSiirto();        
            tokanSiirto = toisenSiirto();
        }

        io.tulosta("");
        io.tulosta("Kiitos!");
        io.tulosta(tuomari);
    }
    
    protected String ensimmaisenSiirto() {
        io.tulosta("Ensimmäisen pelaajan siirto: ");
        return io.seuraavaKomento();
    }

    abstract protected String toisenSiirto();
    
    protected static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }

}