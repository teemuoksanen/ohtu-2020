
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5,   // aloitustalukon koko
                            OLETUSKASVATUS = 5; // luotava uusi taulukko on 
                                                // näin paljon isompi kuin vanha
    private int kasvatuskoko;   // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] taulukko;        // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;   // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        this.taulukko = alustaTaulukko(KAPASITEETTI);
        this.alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 1) {
            throw new IndexOutOfBoundsException("Kapasiteetin on oltava vähintään 1.");
        }
        this.taulukko = alustaTaulukko(kapasiteetti);
        this.alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }    
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 1 || kasvatuskoko < 1) {
            throw new IndexOutOfBoundsException("Kapasiteetin ja kasvatuskoon on oltava vähintään 1.");
        }
        this.taulukko = alustaTaulukko(kapasiteetti);
        this.alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    private int[] alustaTaulukko(int kapasiteetti) {
        int[] t = new int[kapasiteetti];
        for (int i = 0; i < kapasiteetti; i++) {
            t[i] = 0;
        }
        return t;
    }

    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {
            taulukko[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm == taulukko.length) {
                this.taulukko = laajennaTaulukko(alkioidenLkm + kasvatuskoko);
            }
            return true;
        }
        return false;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == taulukko[i]) {
                return true;
            }
        }
        return false;
    }

    private int[] laajennaTaulukko(int kapasiteetti) {
        int[] uusiTaulukko = new int[kapasiteetti];
        for (int i = 0; i < this.taulukko.length; i++) {
            uusiTaulukko[i] = this.taulukko[i];
        }
        for (int i = this.taulukko.length; i < kapasiteetti; i++) {
            uusiTaulukko[i] = 0;
        }
        return uusiTaulukko;
    }

    public boolean poista(int luku) {
        boolean lukuSisaltyy = false;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (lukuSisaltyy) {
                taulukko[i] = taulukko[i + 1];
                taulukko[i + 1] = 0;
            }
            if (luku == taulukko[i]) {
                lukuSisaltyy = true;
                taulukko[i] = taulukko[i + 1];
                alkioidenLkm--;
            }
        }
        return lukuSisaltyy;
    }
    
    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } else {
            String lukujoukko = "{" + taulukko[0];
            for (int i = 1; i < alkioidenLkm; i++) {
                lukujoukko += ", " + taulukko[i];
            }
            lukujoukko += "}";
            return lukujoukko;
        }
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = taulukko[i];
        }
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdiste = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            yhdiste.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            yhdiste.lisaa(bTaulu[i]);
        }
        return yhdiste;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko leikkaus = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    leikkaus.lisaa(bTaulu[j]);
                }
            }
        }
        return leikkaus;
    }

    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko erotus = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            erotus.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            erotus.poista(bTaulu[i]);
        }
        return erotus;
    }
        
}
