package ohtu;

import javax.swing.JButton;
import javax.swing.JTextField;

public abstract class Komento {
    
    protected JTextField tuloskentta;
    protected JTextField syotekentta;
    protected JButton nollaa;
    protected JButton undo;
    protected Sovelluslogiikka sovellus;
    private int edellinen;
    
    public Komento(JTextField tuloskentta, JTextField syotekentta, JButton nollaa, JButton undo, Sovelluslogiikka sovellus) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = sovellus;
        this.edellinen = 0;
    }

    public void suorita() {
        int syote = 0;

        try {
            syote = Integer.parseInt(syotekentta.getText());
        } catch (Exception e) {
        }

        this.edellinen = Integer.parseInt(tuloskentta.getText());
        laske(syote);
        int laskunTulos = sovellus.tulos();
         
        syotekentta.setText("");
        tuloskentta.setText("" + laskunTulos);
        if ( laskunTulos==0) {
            this.nollaa.setEnabled(false);
        } else {
            this.nollaa.setEnabled(true);
        }
        undo.setEnabled(true);
    }

    public void peru() {
        tuloskentta.setText("" + this.edellinen);
        sovellus.setTulos(this.edellinen);
        undo.setEnabled(false);
    }

    protected abstract void laske(int syote);
    
}