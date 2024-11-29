package org.SAPLA.MouvementType.CavalerMouv;

import org.SAPLA.Enum.Direction;
import org.SAPLA.Map.Map;
import org.SAPLA.Map.Tile;
import org.SAPLA.MouvementType.MouvementType;
import org.SAPLA.Result.Result;

import static org.SAPLA.Map.Map.*;

/**
 * La classe CavalerMouv représente un type de mouvement spécialisé pour des entités.
 * Elle gère les déplacements dans différentes directions, en tenant compte de l'énergie
 * et des contraintes de la carte.
 */

public class CavalerMouv extends MouvementType {

    /**
     * Déplace l'entité vers le nord.
     *
     * @param currentTile La tuile actuelle.
     * @param targetTile  La tuile cible.
     * @param energyPoint Points d'énergie disponibles.
     * @return Un objet Result représentant l'état après le mouvement.
     */

    @Override
    public Result moveNorth(Tile currentTile, Tile targetTile, int energyPoint) {
        return move(currentTile, targetTile, energyPoint, Direction.NORTH, Direction.EAST, Direction.WEST);
    }

    /**
     * Déplace l'entité vers l'est.
     *
     * @param currentTile La tuile actuelle.
     * @param targetTile  La tuile cible.
     * @param energyPoint Points d'énergie disponibles.
     * @return Un objet Result représentant l'état après le mouvement.
     */

    @Override
    public Result moveEast(Tile currentTile, Tile targetTile, int energyPoint) {
        return move(currentTile, targetTile, energyPoint, Direction.EAST, Direction.NORTH, Direction.SOUTH);
    }

    /**
     * Déplace l'entité vers le sud.
     *
     * @param currentTile La tuile actuelle.
     * @param targetTile  La tuile cible.
     * @param energyPoint Points d'énergie disponibles.
     * @return Un objet Result représentant l'état après le mouvement.
     */

    @Override
    public Result moveSouth(Tile currentTile, Tile targetTile, int energyPoint) {
        return move(currentTile, targetTile, energyPoint, Direction.SOUTH, Direction.EAST, Direction.WEST);
    }

    /**
     * Déplace l'entité vers l'ouest.
     *
     * @param currentTile La tuile actuelle.
     * @param targetTile  La tuile cible.
     * @param energyPoint Points d'énergie disponibles.
     * @return Un objet Result représentant l'état après le mouvement.
     */

    @Override
    public Result moveWest(Tile currentTile, Tile targetTile, int energyPoint) {
        return move(currentTile, targetTile, energyPoint, Direction.WEST, Direction.NORTH, Direction.SOUTH);
    }

    /**
     * Déplace l'entité vers une nouvelle tuile, en tenant compte d'un nombre de déplacements autorisés.
     *
     * @param currentTile       La tuile actuelle.
     * @param energyPoint       Points d'énergie disponibles.
     * @param currentDirection  Direction à suivre.
     * @param numbMove          Nombre de déplacements.
     * @return Un objet Result représentant l'état après le mouvement.
     */

    @Override
    public Result moveToTile(Tile currentTile, int energyPoint, Direction currentDirection, int numbMove) {
        Tile nextTile = currentTile;

        for (int i = 0; i < numbMove; i++) {
            Tile previousTile = nextTile;

            int deltaX =  0, deltaY = 0;
            switch(currentDirection) {
                case NORTH -> deltaY = 1;
                case EAST -> deltaX = 1;
                case SOUTH -> deltaY = -1;
                case WEST -> deltaX = -1;
            }

            nextTile = nextTileCalculate(nextTile, deltaX, deltaY);

            if (nextTile == previousTile) {
                energyPoint = energyPoint -3;
                break;
            }
            energyPoint --;
        }
        return new Result(nextTile, energyPoint);
    }

    /**
     * Gère le mouvement spécifique à l'entité en fonction de son énergie.
     *
     * @param currentTile La tuile actuelle.
     * @param energyPoint Points d'énergie disponibles.
     * @param maxEnergy   Énergie maximale de l'entité.
     * @return Un objet Result représentant l'état après le mouvement.
     */

    public Result cavalerMov(Tile currentTile, int energyPoint , int maxEnergy) {

        if (energyPoint > (maxEnergy * 0.2)) {
            Direction randomDirection = Direction.getRandomDirection();

            Result result = directionToGo(currentTile, energyPoint, maxEnergy, randomDirection);
            return result;
        }

        Direction directionToSafeZone = Map.directionToReachSafeZone(super._livingBeing);

        Result result = directionToGo(currentTile, energyPoint, maxEnergy, directionToSafeZone);
        return result;
    }

    /**
     * Détermine la direction à suivre et effectue le mouvement.
     *
     * @param currentTile La tuile actuelle.
     * @param energyPoint Points d'énergie disponibles.
     * @param maxEnergy   Énergie maximale.
     * @param direction   Direction à suivre.
     * @return Un objet Result représentant l'état après le mouvement.
     */

    public Result directionToGo(Tile currentTile, int energyPoint, int maxEnergy, Direction direction) {
        Tile targetTile = getTargetTile(currentTile, direction);
        return switch (direction) {
            case NORTH, NORTHWEST -> moveNorth(currentTile, targetTile, energyPoint);
            case EAST, NORTHEAST -> moveEast(currentTile, targetTile, energyPoint);
            case SOUTH, SOUTHEAST -> moveSouth(currentTile, targetTile, energyPoint);
            case WEST, SOUTHWEST -> moveWest(currentTile, targetTile, energyPoint);
        };
    }

    /**
     * Calcule la tuile cible en fonction de la direction.
     *
     * @param currentTile La tuile actuelle.
     * @param direction   Direction à suivre.
     * @return La tuile cible.
     */

    private Tile getTargetTile(Tile currentTile, Direction direction) {
        int[][] deltas = {
                {2, 3},  // NORTH
                {3, 2},  // NORTHEAST
                {3, -2}, // EAST
                {2, -3}, // SOUTHEAST
                {-2, -3},// SOUTH
                {-3, -2},// SOUTHWEST
                {-3, 2}, // WEST
                {-2, 3}  // NORTHWEST
        };
        int index = direction.ordinal();
        int deltaX = deltas[index][0];
        int deltaY = deltas[index][1];

        return getMapGrid()[currentTile.getPosition().getX() + deltaX][currentTile.getPosition().getY() + deltaY];
    }

    /**
     * Déplace l'entité vers la tuile cible en fonction de la direction.
     *
     * @param currentTile       La tuile actuelle.
     * @param targetTile        La tuile cible.
     * @param energyPoint       Points d'énergie disponibles.
     * @param primaryDirection  Direction principale.
     * @param secondaryDirection Direction secondaire.
     * @param fallbackDirection Direction de secours.
     * @return Un objet Result représentant l'état après le mouvement.
     */

    public Result move(Tile currentTile, Tile targetTile, int energyPoint, Direction primaryDirection, Direction secondaryDirection, Direction fallbackDirection) {

        targetTile = isOutOfBound(targetTile);

        if (primaryDirection == Direction.NORTH || primaryDirection == Direction.SOUTH) {
            if (currentTile.getPosition().getY() == targetTile.getPosition().getY()) {
                return moveToTile(currentTile, energyPoint, primaryDirection, 2);
            }
        } else {
            if (currentTile.getPosition().getX() == targetTile.getPosition().getX()) {
                return moveToTile(currentTile, energyPoint, primaryDirection, 2);
            }
        }

        if (currentTile.getPosition().getX() < targetTile.getPosition().getX()) {
            Result result = moveToTile(currentTile, energyPoint, secondaryDirection, 3);
            return move(result.getTile(), targetTile, result.getEnergyPoint(), secondaryDirection, primaryDirection, fallbackDirection);
        }

        if (currentTile.getPosition().getX() > targetTile.getPosition().getX()) {
            Result result = moveToTile(currentTile, energyPoint, fallbackDirection, 3);
            return move(result.getTile(), targetTile, result.getEnergyPoint(), fallbackDirection, primaryDirection, secondaryDirection);
        }

        return new Result(currentTile, energyPoint);
    }

    /**
     * Calcule la prochaine tuile en fonction du déplacement.
     *
     * @param currentTile La tuile actuelle.
     * @param deltaX      Déplacement sur l'axe X.
     * @param deltaY      Déplacement sur l'axe Y.
     * @return La prochaine tuile si elle est accessible, sinon la tuile actuelle.
     */

    public Tile nextTileCalculate(Tile currentTile, int deltaX, int deltaY) {
        Tile bufferNextTileLeft = getMapGrid()[currentTile.getPosition().getX() + deltaX][ currentTile.getPosition().getY() + deltaY];
        char bufferTileContentLeft = bufferNextTileLeft.getTileContent();

        if (bufferTileContentLeft == ' ') {
            return bufferNextTileLeft;
        }else{
            return currentTile;
        }
    }

    /**
     * Vérifie si une tuile cible est hors des limites de la carte
     * et ajuste sa position si nécessaire.
     *
     * @param targetTile La tuile cible.
     * @return La tuile ajustée si la cible est hors des limites.
     */

    public Tile isOutOfBound(Tile targetTile){
        if (targetTile.getPosition().getX() > getMapWidth()){
            targetTile = getMapGrid()[getMapWidth() -1][targetTile.getPosition().getY()];
        }
        if (targetTile.getPosition().getY() > getMapHeight()){
            targetTile = getMapGrid()[targetTile.getPosition().getX()][getMapHeight() -1];
        }

        return targetTile;
    }
}