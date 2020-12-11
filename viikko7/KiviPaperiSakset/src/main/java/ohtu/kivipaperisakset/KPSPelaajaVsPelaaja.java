package ohtu.kivipaperisakset;

public class KPSPelaajaVsPelaaja extends KiviPaperiSakset {

    public KPSPelaajaVsPelaaja(IO io) {
        super(io);
    }
    
    @Override
    protected String toisenSiirto() {
        io.tulosta("Toisen pelaajan siirto: ");
        return io.seuraavaKomento();  
    }
    
}