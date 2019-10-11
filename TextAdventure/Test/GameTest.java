import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by rdw1995 on 9/22/16.
 */
public class GameTest {
    @Test
    public void testSaveAndLoad(){
        Player player = new Player();
        player.name = "test player";
        player.location = "tunnel";
        player.weapon = "sword";
        player.items.add("shield");
        Game.save(player, "testgame.json");
        Player player2 = Game.load("testgame.json");
        assertTrue(player.equals(player2));

    }

}