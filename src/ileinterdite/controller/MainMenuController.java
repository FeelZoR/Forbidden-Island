package ileinterdite.controller;

import ileinterdite.util.IObservable;
import ileinterdite.util.IObserver;

import java.util.ArrayList;

public class MainMenuController implements IObserver<ArrayList<String>> {

    private ArrayList<String> playerName = new ArrayList<>();

    public ArrayList<String> getPlayersName() {
        return playerName;
    }

    @Override
    public void update(IObservable<ArrayList<String>> o, ArrayList<String> message) {
        playerName = message;

        GameController c = new GameController(this);
    }
}