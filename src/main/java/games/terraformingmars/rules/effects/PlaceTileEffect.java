package games.terraformingmars.rules.effects;

import games.terraformingmars.TMGameState;
import games.terraformingmars.TMTypes;
import games.terraformingmars.actions.PlaceTile;
import games.terraformingmars.actions.TMAction;

import java.util.Arrays;
import java.util.HashSet;

public class PlaceTileEffect extends Effect {
    public TMTypes.Tile tile;
    public TMTypes.Resource[] resourceTypeGained;
    public boolean onMars; // tile must've been placed on mars

    public PlaceTileEffect(boolean mustBeCurrentPlayer, TMAction effectAction, boolean onMars, TMTypes.Tile tile, TMTypes.Resource[] resourceGained) {
        super(mustBeCurrentPlayer, effectAction);
        this.onMars = onMars;
        this.tile = tile;
        this.resourceTypeGained = resourceGained;
    }
    public PlaceTileEffect(boolean mustBeCurrentPlayer, String effectAction, boolean onMars, TMTypes.Tile tile, TMTypes.Resource[] resourceGained) {
        super(mustBeCurrentPlayer, effectAction);
        this.onMars = onMars;
        this.tile = tile;
        this.resourceTypeGained = resourceGained;
    }

    @Override
    public boolean canExecute(TMGameState gameState, TMAction actionTaken, int player) {
        if (!(actionTaken instanceof PlaceTile)) return false;

        PlaceTile action = (PlaceTile) actionTaken;
        boolean marsCondition = !onMars || action.onMars;
        boolean tileCondition = tile == null || action.tile == tile;

        HashSet<TMTypes.Resource> gained = new HashSet<>();
        if (action.x != -1) {
            gained.addAll(Arrays.asList(gameState.getBoard().getElement(action.x, action.y).getResources()));
        }
        boolean resourceTypeCondition = resourceTypeGained == null;
        if (resourceTypeGained != null) {
            for (TMTypes.Resource r: resourceTypeGained) {
                if (gained.contains(r))  {
                    resourceTypeCondition = true;
                    break;
                }
            }
        }
        return marsCondition && tileCondition && resourceTypeCondition && super.canExecute(gameState, actionTaken, player);
    }
}
