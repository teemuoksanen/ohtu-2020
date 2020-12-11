package ohtu.kivipaperisakset;

public class KPSTekoaly extends KiviPaperiSakset {

    private Kone kone;

    public KPSTekoaly(IO io, Kone kone) {
        super(io);
        this.kone = kone;
    }
    
    @Override
    protected String toisenSiirto() {
        String tokanSiirto;

        tokanSiirto = kone.annaSiirto();
        io.tulosta("Tietokone valitsi: " + tokanSiirto);
        return tokanSiirto;
    }

}