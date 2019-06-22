package ileinterdite.factory;

import ileinterdite.model.Treasure;

import java.util.ArrayList;

public class TreasureFactory {
    public static ArrayList<Treasure> treasureFactory() {
        ArrayList<Treasure> treasures = new ArrayList<>();
        treasures.add(new Treasure("La Pierre sacrée"));
        treasures.add(new Treasure("La Statue du zéphyr"));
        treasures.add(new Treasure("Le Cristal ardent"));
        treasures.add(new Treasure("Le Calice de l'onde"));
        return treasures;
    }
}
