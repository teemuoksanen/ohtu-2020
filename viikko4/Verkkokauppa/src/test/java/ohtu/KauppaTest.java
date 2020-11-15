package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {

    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;
    Kauppa k;

    @Before
    public void setUp() {
        pankki = mock(Pankki.class);

        viite = mock(Viitegeneraattori.class);
        when(viite.uusi()).thenReturn(42);

        varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.saldo(2)).thenReturn(5); 
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "kalja", 8));
        when(varasto.saldo(3)).thenReturn(0); 
        when(varasto.haeTuote(3)).thenReturn(new Tuote(3, "tyhjä", 11));

        k = new Kauppa(varasto, pankki, viite);
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaArvoilla() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(5));   
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaArvoillaKunKaksiTuotetta() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(13));   
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaArvoillaKunKaksiSamaaTuotetta() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.tilimaksu("timo", "54321");

        verify(pankki).tilisiirto(eq("timo"), eq(42), eq("54321"), eq("33333-44455"), eq(10));   
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaArvoillaKunYksiTuotePuuttuu() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(3);
        k.tilimaksu("timo", "54321");

        verify(pankki).tilisiirto(eq("timo"), eq(42), eq("54321"), eq("33333-44455"), eq(5));   
    }

    @Test
    public void aloitaAsiointiNollaaOstostenHinnan() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("timo", "54321");

        k.aloitaAsiointi();
        k.tilimaksu("teemu", "123123");

        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), eq(0));   
    }

    @Test
    public void uusiViitenumeroJokaiselleMaksulle() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("timo", "54321");

        verify(viite, times(1)).uusi();

        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.tilimaksu("teemu", "123123");

        verify(viite, times(2)).uusi();

        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("jorma", "5122333");

        verify(viite, times(3)).uusi();
    }

    @Test
    public void koristaPoistaminenVahentaaOstostenHintaa() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.poistaKorista(1);
        k.tilimaksu("timo", "54321");

        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), eq(5));   
    }

    @Test
    public void viitegeneraattoriPalauttaaOikeatViitteet() {
        Viitegeneraattori viiteAito = new Viitegeneraattori();
        assertEquals(viiteAito.uusi(), 1);
        assertEquals(viiteAito.uusi(), 2);
    }
}
