package nand2tetris.test.lib;

import Hack.Controller.HackController;
import Hack.Controller.HackSimulator;

public final class HackErrorThrowController  extends HackController {
    public HackErrorThrowController(HackSimulator simulator, String scriptFileName) {
        super(simulator, scriptFileName);
    }

    @Override
    protected void displayMessage(String message, boolean error) {
        if (!error) {
            super.displayMessage(message, false);
            return;
        }
        
        throw new RuntimeException(message);
    }
}
