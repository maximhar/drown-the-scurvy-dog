package uk.ac.aber.dcs.msh4.cs124.entry;

import uk.ac.aber.dcs.msh4.cs124.GameView;
import uk.ac.aber.dcs.msh4.cs124.cli.ConsoleView;
import uk.ac.aber.dcs.msh4.cs124.gui.GraphicalView;

public class Main {
    public static void main(String[] args){
        GameView view = new ConsoleView();
        if(args.length == 1){
            if(args[0] == "-g"){
                view = new GraphicalView();
            }
        }
        view.run();
    }
}
