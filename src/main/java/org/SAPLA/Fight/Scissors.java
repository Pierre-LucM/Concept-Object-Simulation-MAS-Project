package org.SAPLA.Fight;

import org.SAPLA.Enum.FightElements;

public class Scissors extends FightElement {
    public Scissors() {
        this._me = FightElements.Scissors;
        this._winAgainst = FightElements.Paper;
        this._loseAgainst =  FightElements.Rock;
    }
}
