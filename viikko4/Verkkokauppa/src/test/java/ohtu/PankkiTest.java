package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PankkiTest {

    Kirjanpito kirjanpito;
    Pankki p;

    @Before
    public void setUp() {
        kirjanpito = mock(Kirjanpito.class);
        p = new Pankki(kirjanpito);
    }

    @Test
    public void tilisiirtoTekeeKirjanpitotapahtumanJaPalauttaaTrue() {
        String tililta = "54321";
        String tilille = "12345";
        int viite = 42;
        int summa = 50;
        boolean tilisiirto = p.tilisiirto("teemu", viite, tililta, tilille, summa);

        assertTrue(tilisiirto);
        verify(kirjanpito).lisaaTapahtuma("tilisiirto: tililtä " + tililta + " tilille " + tilille + " viite " + viite + " summa " + summa + "e");
    }

    @Test
    public void tilisiirtoMuuttaaOikeaaKirjanpitoa() {
        Kirjanpito kirjanpitoAito = new Kirjanpito();
        p = new Pankki(kirjanpitoAito);

        int tapahtumatAlku = kirjanpitoAito.getTapahtumat().size();

        String tililta = "54321";
        String tilille = "12345";
        int viite = 42;
        int summa = 50;
        p.tilisiirto("teemu", viite, tililta, tilille, summa);

        int tapahtumatLoppu = kirjanpitoAito.getTapahtumat().size();

        assertTrue(tapahtumatLoppu > tapahtumatAlku);
    }
}