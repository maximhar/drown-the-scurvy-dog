package uk.ac.aber.dcs.msh4.cs124.cli;

import java.util.Scanner;
import uk.ac.aber.dcs.msh4.cs124.GameView;
import uk.ac.aber.dcs.msh4.cs124.model.PirateHangman;

import java.io.IOException;

public class ConsoleView implements GameView {
    PirateHangman model;
    Scanner sc;
    @Override
    public void run() {
        sc = new Scanner(System.in);
        try {
            System.out.println("Welcome to Drown the Scurvy Dog!");
            model = new PirateHangman(10);
            startGame();
        }
        catch (IOException e) {
            System.err.println("Unable to read pirate dictionary... goodbye");
        }
        sc.close();
    }

    private void startGame() {
        boolean won = false;
        while(model.guessLeft() > 0){
            System.out.println("You have " + model.guessLeft() + " guesses left.");
            System.out.println("So far you can see: " + model.getVisible());
            System.out.println("Letters you already tried: " + model.getLetters());
            System.out.println("Press W to guess the whole word or C to guess a letter: ");
            char choice = sc.nextLine().toUpperCase().charAt(0);
            boolean isWin = false;
            if(choice == 'C'){
                System.out.println("Enter a letter: ");
                char letter = sc.nextLine().charAt(0);
                isWin = model.tryThis(letter);
            } else if (choice == 'W') {
                System.out.println("Enter a word: ");
                String word = sc.nextLine();
                isWin = model.tryWord(word);
            }
            if(isWin) {
                won = true;
                printWin();
                break;
            }
        }
        if(!won)
            System.out.println("You ran out of guesses, sorry. The word was " + model.getHidden());
    }

    private void printWin() {
        System.out.println("You won, congratulations. The word is " + model.getHidden());
    }
}
