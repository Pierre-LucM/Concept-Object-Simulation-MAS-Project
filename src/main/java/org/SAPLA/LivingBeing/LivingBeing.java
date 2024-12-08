package org.SAPLA.LivingBeing;

import org.SAPLA.Enum.Direction;
import org.SAPLA.Fight.Fight;
import org.SAPLA.Map.SafeZone;
import org.SAPLA.Map.Tile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public abstract class LivingBeing {
    private static int _instancesCount = 0;
    private List<String> _message = new ArrayList<>();
    private Tile _currentTile;
    private Direction _lastDirectionTaken;
    private int _energyPoint;
    private SafeZone _safeZone;
    private int _maxEnergy;

    public LivingBeing() {
        _instancesCount++;
    }

    public abstract void move();

    public void interact(LivingBeing other) {
        if(this.getClass().equals(other.getClass())) {
            this.exchangeAllMessages(other);
        }
        else if(this.getClass().getSuperclass().equals(other.getClass().getSuperclass())) {
            this.exchangeSomeMessages(other);
        }
        else {
            this.fight(other);
        }
    }

    private void exchangeAllMessages(LivingBeing other) {
        List<String> newMessagesForThis = other.getMessage().stream().filter(message -> !this.getMessage().contains(message)).toList();
        List<String> newMessagesForOther = this.getMessage().stream().filter(message -> !other.getMessage().contains(message)).toList();
        this.addMessage(newMessagesForThis);
        other.addMessage(newMessagesForOther);
    }

    private void exchangeSomeMessages(LivingBeing other) {

        List<String> newMessagesForThis = new java.util.ArrayList<>(other.getMessage().stream().filter(message -> !this.getMessage().contains(message)).toList());
        if(!newMessagesForThis.isEmpty()) {
            int randomNumber = new Random().nextInt(1, newMessagesForThis.size() + 1);
            Collections.shuffle(newMessagesForThis);
            newMessagesForThis = newMessagesForThis.subList(0, randomNumber);
            this.addMessage(newMessagesForThis);
        }

        List<String> newMessagesForOther = new java.util.ArrayList<>(this.getMessage().stream().filter(message -> !other.getMessage().contains(message)).toList());
        if(!newMessagesForOther.isEmpty()) {
            int randomNumber = new Random().nextInt(1, newMessagesForOther.size() + 1);
            Collections.shuffle(newMessagesForOther);
            newMessagesForOther = newMessagesForOther.subList(0, randomNumber);
            other.addMessage(newMessagesForOther);
        }

    }

    private void fight(LivingBeing other) {
        Fight fight = new Fight(this, other);
        LivingBeing[] fightResults = fight.startFight();
        LivingBeing winner = fightResults[0];
        LivingBeing loser = fightResults[1];

        List<String> messagesVoles = new java.util.ArrayList<>(loser.getMessage().stream().filter(message -> !winner.getMessage().contains(message)).toList());
        if(!messagesVoles.isEmpty()) {
            Collections.shuffle(messagesVoles);
            int randomNumber = new Random().nextInt(1, messagesVoles.size() + 1);
            messagesVoles = messagesVoles.subList(0, randomNumber);
            loser.deleteMessage(messagesVoles);
            winner.addMessage(messagesVoles);
        }

    }

    //Getter
    protected List<String> getMessage(){
        return this._message;
    }

    public Tile getCurrentTile(){
        return this._currentTile;
    }

    protected Direction getLastDirectionTaken(){
        return this._lastDirectionTaken;
    }

    protected int getEnergyPoint(){
        return this._energyPoint;
    }

    public SafeZone getSafeZone(){
        return this._safeZone;
    }

    protected int getMaxEnergy(){
        return this._maxEnergy;
    }

    //Setter
    protected void addMessage(List<String> messages){
        if(messages != null) {
            this._message.addAll(messages);
        }
    }

    protected void deleteMessage(List<String> messages) {
        this._message.removeAll(messages);
    }

    protected void setCurrentTile(Tile currentTile){
        this._currentTile = currentTile;
    }

    protected void setLastDirectionTaken(Direction lastDirectionTaken){
        this._lastDirectionTaken = lastDirectionTaken;
    }

    protected void setEnergyPoint(int energyPoint){
        this._energyPoint = energyPoint;
    }

    protected void setSafeZone(SafeZone safeZone){
        this._safeZone = safeZone;
    }

    protected void setMaxEnergy(int maxEnergy){
        this._maxEnergy = maxEnergy;
    }

    public static int getInstancesCount() {
        return _instancesCount;
    }
}
