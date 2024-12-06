package org.SAPLA.Fight;

import org.SAPLA.LivingBeing.LivingBeing;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Fight {
    private LivingBeing _fighter1;
    private LivingBeing _fighter2;

    private int _scoreFighter1;
    private int _scoreFighter2;

    private FightElement _elementChoosedByFighter1;
    private FightElement _elementChoosedByFighter2;

    private int _nbRound;

    public Fight(LivingBeing fighter1, LivingBeing fighter2) {
        this._fighter1 = fighter1;
        this._fighter2 = fighter2;
        this._scoreFighter1 = 0;
        this._scoreFighter2 = 0;
        this._nbRound = 1;
    }

    //return le gagnant et le perdant du combat
    public LivingBeing[] startFight() {

        while(this._scoreFighter1 < 3 && this._scoreFighter2 < 3) {
            this.nextRound();
            this._nbRound++;
        }
        if(this._scoreFighter1 >= 3) {
            return new LivingBeing[] {this._fighter1, this._fighter2};
        } else {
            return new LivingBeing[] {this._fighter2, this._fighter1};
        }
    }

    private void nextRound() {
        this._elementChoosedByFighter1 = this.chooseFightElement();
        this._elementChoosedByFighter2 = this.chooseFightElement();

        if(this._elementChoosedByFighter1.doesItWinAgainst(this._elementChoosedByFighter2.getMe())) {
            this._scoreFighter1++;
        } else if (this._elementChoosedByFighter2.doesItWinAgainst(this._elementChoosedByFighter1.getMe())) {
            this._scoreFighter2++;
        } else {
        }
    }

    private FightElement chooseFightElement() {
        int random = ThreadLocalRandom.current().nextInt(1, 4);
        if(random == 1) {
            return new Paper();
        } else if(random == 2) {
            return new Rock();
        } else {
            return new Scissors();
        }
    }
}
