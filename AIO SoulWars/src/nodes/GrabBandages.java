package nodes;

import aids.Constants;
import aids.Helper_Functions;
import aids.Variables;
import xobot.bot.Context;
import xobot.script.methods.GameObjects;
import xobot.script.methods.Players;
import xobot.script.methods.input.KeyBoard;
import xobot.script.methods.tabs.Inventory;
import xobot.script.wrappers.interactive.GameObject;

/**
 * Created by Skattle on 1/23/2018.
 */
public class GrabBandages extends Node {

    private GameObject object;

    @Override
    public boolean validate() {
        System.out.println("Checking validation of GrabBandages");
        return Variables.getCurrentTeam() != null
                && !Inventory.Contains(Constants.BANDAGES)
                && !Players.getMyPlayer().isInCombat()
                && Constants.GAME_AREA.contains(Players.getMyPlayer().getLocation());
    }

    @Override
    public void execute() {

        object = GameObjects.getNearest(Constants.BANDAGE_STALL);

        if (Context.client.getInputState() == 0 && object != null) {
            object.interact("take-x"); // SECOND FUCKING OPTION
            Helper_Functions.conditionalSleep(() -> Context.client.getInputState() == 3, 4500); //it better be fucking state 3
        }

        if (Context.client.getInputState() == 3) {
            Context.client.setInputText("25");
            KeyBoard.pressEnter();
            Helper_Functions.conditionalSleep(() -> Inventory.Contains(Constants.BANDAGES), 1200);
        }


    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
