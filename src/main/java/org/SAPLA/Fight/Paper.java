package org.SAPLA.Fight;

import org.SAPLA.Enum.FightElements;

public class Paper extends FightElement {

    public Paper() {
        this._me = FightElements.Paper;
        this._winAgainst = FightElements.Rock;
        this._loseAgainst =  FightElements.Scissors;
    }
}
