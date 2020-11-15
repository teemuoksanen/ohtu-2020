package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class VarastoTest {

    Kirjanpito kirjanpito;
    Varasto v;

    @Before
    public void setUp() {
        kirjanpito = mock(Kirjanpito.class);
        v = new Varasto(kirjanpito);
    }

    @Test
    public void saldoPalauttaaOikeatSaldot() {
        assertEquals(v.saldo(1), 100);
        assertEquals(v.saldo(5), 15);
    }

    @Test
    public void olematonTuotePalauttaaNull() {
        assertNull(v.haeTuote(100));
    }

    @Test
    public void varastostaOttaminenMuuttaaSaldoaJaLisaaTapahtumanKirjanpitoon() {
        Tuote t = v.haeTuote(1);
        int alkuSaldo = v.saldo(1);
        v.otaVarastosta(t);
        int loppuSaldo = v.saldo(1);

        assertTrue(alkuSaldo > loppuSaldo);
        verify(kirjanpito).lisaaTapahtuma("otettiin varastosta " + t);
    }

    @Test
    public void varastoonPalauttaminenMuuttaaSaldoaJaLisaaTapahtumanKirjanpitoon() {
        Tuote t = v.haeTuote(1);
        int alkuSaldo = v.saldo(1);
        v.palautaVarastoon(t);
        int loppuSaldo = v.saldo(1);

        assertTrue(alkuSaldo < loppuSaldo);
        verify(kirjanpito).lisaaTapahtuma("palautettiin varastoon " + t);
    }
}