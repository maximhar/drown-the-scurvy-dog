package uk.ac.aber.dcs.msh4.cs124.model.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uk.ac.aber.dcs.msh4.cs124.model.Hangman;
import uk.ac.aber.dcs.msh4.cs124.model.PirateHangman;


public class PirateHangmanTest {
    //tryThis is tested implicitly by a few of the other tests
    private final String TESTWORD = "SCURVY DOG";
    private PirateHangman game;
    @Before
    public void createGame() throws Exception {
        game = new PirateHangman(TESTWORD, 10);
    }

    @Test
    public void testRandomWordFromFile() throws Exception {
        PirateHangman random = new PirateHangman(10);
        Assert.assertTrue(random.getHidden().length() > 0);
    }

    @Test
    public void testGetVisible() throws Exception {
        Assert.assertEquals("****** ***", game.getVisible());
        game.tryThis('S');
        Assert.assertEquals("S***** ***", game.getVisible());
    }

    @Test
    public void testGetHidden() throws Exception {
        Assert.assertEquals("SCURVY DOG", game.getHidden());
    }

    @Test
    public void testGuessLeft() throws Exception {
        Assert.assertEquals(10, game.guessLeft());
        game.tryThis('A');
        Assert.assertEquals(9, game.guessLeft());
    }

    @Test
    public void testGetLetters() throws Exception {
        game.tryThis('O');
        game.tryThis('K');
        char[] letters = game.getLetters().toCharArray();
        for(char c : letters){
            //K should not be present
            Assert.assertFalse(c == 'K');
        }
    }

    @Test
    public void testTryWord() throws Exception {
        int guesses = game.guessLeft();
        Assert.assertFalse(game.tryWord("BAD CAT"));
        Assert.assertEquals(-5, game.guessLeft() - guesses);
        Assert.assertTrue(game.tryWord(TESTWORD));
    }
}
