package uk.ac.aber.dcs.msh4.cs124.model;

import java.util.HashSet;

public class HangmanGame implements GameModelInterface {
    private final char SPACE = ' ';
    private final char HIDDEN = '*';
    private int guessesLeft;
    private char[] word;
    private HashSet<Character> charactersTried = new HashSet<>();
    private HashSet<Character> charactersExtant = new HashSet<>();

    public HangmanGame(String word, int guesses){
        this.word = word.toUpperCase().toCharArray();
        this.guessesLeft = guesses;
        this.charactersExtant = getCharacterSet(this.word);
    }

    private HashSet<Character> getCharacterSet(char[] source){
        HashSet<Character> set = new HashSet<>();
        for(char c : source){
            if(c != SPACE) set.add(c);
        }
        return set;
    }

    @Override
    public String getVisible() {
        StringBuilder visibleBuilder = new StringBuilder();
        for(char c : this.word){
            if(charactersTried.contains(Character.valueOf(c))){
                visibleBuilder.append(c);
            } else if (c == SPACE) {
                visibleBuilder.append(c);
            } else {
                visibleBuilder.append(HIDDEN);
            }
        }
        return visibleBuilder.toString();
    }

    @Override
    public String getHidden() {
        return String.valueOf(this.word);
    }

    @Override
    public int guessLeft() {
        return this.guessesLeft;
    }

    @Override
    public String getLetters() {
        //the set of letters tried is the set of all letters in the word intersected with the set of all letters tried
        // = charactersTried ∩ charactersExtant
        StringBuilder intersectionBuilder = new StringBuilder();
        HashSet<Character> intersection = new HashSet<>(this.charactersTried);
        intersection.retainAll(this.charactersExtant);
        for(Character c : intersection){
            intersectionBuilder.append(c);
        }
        return intersectionBuilder.toString();
    }

    @Override
    public boolean tryThis(char letter) {
        Character normalised = Character.toUpperCase(letter);
        //if the character does not exist in the word, take away one guess
        if(!this.charactersExtant.contains(normalised)) {
            this.guessesLeft--;
        }
        //if the charactersTried set is a superset of the charactersExtant set, we have a win
        //therefore charactersExtant \ charactersTried should be zero
        this.charactersTried.add(normalised);
        HashSet<Character> complement = new HashSet<>(this.charactersExtant);
        complement.removeAll(this.charactersTried);
        return complement.isEmpty();
    }

    @Override
    public boolean tryWord(String guess) {
        String original = new String(this.word);
        if(original.equalsIgnoreCase(guess)) return true;
        else this.guessesLeft-=5;
        return false;
    }
}
