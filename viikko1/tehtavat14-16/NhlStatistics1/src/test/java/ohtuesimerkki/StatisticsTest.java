package ohtuesimerkki;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import ohtuesimerkki.Reader;
import ohtuesimerkki.Player;

import java.util.ArrayList;
import java.util.List;

public class StatisticsTest {
 
    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }  

    @Test
    public void tuntematonPelaajaPalauttaaNull() {
        Player player = stats.search("Tuntematon");
        assertNull(player);
    }

    @Test
    public void palauttaaOikeanPelaajan() {
        Player player = stats.search("Semenko");
        assertEquals("Semenko", player.getName());
        assertEquals("EDM", player.getTeam());
        assertEquals(4, player.getGoals());
        assertEquals(12, player.getAssists());
    }

    @Test
    public void palauttaaOikeanJoukkuenKoon() {
        List<Player> team = stats.team("EDM");
        assertEquals(3, team.size());
    }

    @Test
    public void palauttaaOikeanTopMaaran() {
        List<Player> top = stats.topScorers(3);
        assertEquals(3, top.size());
    }

    @Test
    public void palauttaaOikeanPistejohtajan() {
        List<Player> top = stats.topScorers(1);
        assertTrue(top.contains(stats.search("Gretzky")));
    }

}