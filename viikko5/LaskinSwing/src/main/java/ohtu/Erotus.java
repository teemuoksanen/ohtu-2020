package ohtu;

import javax.swing.JButton;
import javax.swing.JTextField;

public class Erotus extends Komento {

  public Erotus(JTextField tuloskentta, JTextField syotekentta, JButton nollaa, JButton undo, Sovelluslogiikka sovellus) {
      super(tuloskentta, syotekentta, nollaa, undo, sovellus);
  }

  @Override
  public void laske(int arvo) {
      sovellus.miinus(arvo);
  }
  
}