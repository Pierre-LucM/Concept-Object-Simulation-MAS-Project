package org.SAPLA.Fight;

import org.SAPLA.Enum.FightElements;

public class Rock extends FightElement {

    public Rock() {
        this._me = FightElements.Rock;
        this._winAgainst = FightElements.Scissors;
        this._loseAgainst =  FightElements.Paper;
    }
}
