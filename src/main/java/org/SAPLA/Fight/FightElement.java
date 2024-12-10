package org.SAPLA.Fight;

import org.SAPLA.Enum.FightElements;

public abstract class FightElement {

    protected FightElements _me;
    protected FightElements _winAgainst;
    protected FightElements _loseAgainst;

    public boolean doesItWinAgainst(FightElements otherFightElement) {
        return this._winAgainst == otherFightElement;
    }

    public FightElements getMe() {
        return this._me;
    }
}
