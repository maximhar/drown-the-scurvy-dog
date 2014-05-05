package uk.ac.aber.dcs.msh4.cs124.model;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;
import java.util.Random;

public class PirateHangman extends Hangman {

    public PirateHangman(String word, int guesses)  {
        this.setWord(word);
        this.guessesLeft = guesses;
    }

    public PirateHangman(int guesses) throws IOException {
        this.setWord(this.loadRandomWord());
        this.guessesLeft = guesses;
    }

    private String loadRandomWord() throws IOException {
        File pirateWords = new File("piratewords.txt");
        List<String> words = Files.readAllLines(pirateWords.toPath(), Charset.defaultCharset());
        Random rand = new Random();
        int index = rand.nextInt(words.size() - 2) + 1;
        return words.get(index);
    }
}
