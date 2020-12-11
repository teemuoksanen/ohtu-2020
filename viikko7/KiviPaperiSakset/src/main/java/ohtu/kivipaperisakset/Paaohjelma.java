package ohtu.kivipaperisakset;

public class Paaohjelma {

    public static void main(String[] args) {

        IO io = new KonsoliIO();
        Pelitehdas pelit = new Pelitehdas(io);

        while (true) {
            io.tulosta("\nValitse pelataanko"
                    + "\n (a) ihmistä vastaan "
                    + "\n (b) tekoälyä vastaan"
                    + "\n (c) parannettua tekoälyä vastaan"
                    + "\nmuilla valinnoilla lopetataan");

            String vastaus = io.seuraavaKomento();
            KiviPaperiSakset peli = pelit.valitse(vastaus);
            if (peli == null) {
                break;
            }
            peli.pelaa();
        }

    }
}
