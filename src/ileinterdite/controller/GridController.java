package ileinterdite.controller;

import ileinterdite.factory.BoardFactory;
import ileinterdite.model.Card;
import ileinterdite.model.Grid;
import ileinterdite.model.Treasure;
import ileinterdite.model.adventurers.Adventurer;
import ileinterdite.util.Parameters;
import ileinterdite.util.Tuple;
import ileinterdite.util.Utils;
import ileinterdite.util.helper.GridControllerHelper;
import ileinterdite.view.GridView;

public class GridController {

    private GameController controller; //< A reference to the main controller

    // Necessary elements to make the controller work
    private GridView gridView; //< The visual representation of the grid
    private Grid grid; //< The object representing the grid

    public GridController(GameController c) {
        this.controller = c;
        this.gridView = new GridView();
        this.grid = new Grid(BoardFactory.getCells(), BoardFactory.getTreasures());

        GridControllerHelper.spawnAdventurers(c.getAdventurers(), grid);
        GridControllerHelper.initView(gridView, grid, controller.getAdventurers(), controller.getActionController());

        controller.getWindow().setGridView(gridView);
    }

    public void finishGridInit() {
        if (!Parameters.DEMOMAP) {
            controller.getDeckController().drawFloodCards(6);
        }
    }

    /* ************* *
     * TURN HANDLING *
     * ************* */

    public void newTurn() {
        gridView.newTurn();
    }

    /* *************** *
     * ACTIONS ON GRID *
     * *************** */

    public void dry(Tuple<Integer, Integer> pos){
        grid.dry(pos.x, pos.y);
        gridView.updateCell(pos.x, pos.y, Utils.State.NORMAL);
    }

    /**
     *  Vérifie que l'aventurier peut récupérer un trésor, puis si c'est le cas, retire le trésor de la liste des trésors
     *  non récupérés, puis défausse les cartes utilisées par l'aventurier pour récupérer le trésor dans la défausse des
     *  cartes trésors
     * @param adventurer
     */
    public void collectTreasure(Adventurer adventurer) {
        Treasure collectibleTreasure = adventurer.isAbleToCollectTreasure();
        if (collectibleTreasure != null) {
            String collectibleTreasureName = collectibleTreasure.getName();
            this.grid.getTreasures().remove(collectibleTreasure);
            int discardedCards = 0;
            for (Card card : adventurer.getCards()) {
                if (card.getCardName().equals(collectibleTreasureName) && discardedCards <4 ) {
                    controller.getDeckController().getDiscardPile(Utils.CardType.Treasure).addCard(card);
                    adventurer.getCards().remove(card);
                    discardedCards++;
                }
            }
            controller.getActionController().reduceNbActions();
            // TODO : methode pour montrer à l'utilisateur que le trésor a bien été récupéré / update de sa main
        }
    }

    /* ***************** *
     * GETTERS & SETTERS *
     * ***************** */

    public Grid getGrid() {
        return grid;
    }

    public GridView getGridView() {
        return gridView;
    }
}
