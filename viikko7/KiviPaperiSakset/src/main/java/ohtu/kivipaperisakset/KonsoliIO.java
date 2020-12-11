package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KonsoliIO implements IO {

    private Scanner lukija;

    public KonsoliIO() {
        lukija = new Scanner(System.in);
    }

    public String seuraavaKomento() {
        return lukija.nextLine();
    }

    public void tulosta(Object o) {
        System.out.println(o);
    }

}
