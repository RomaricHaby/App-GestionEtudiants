package mvc;

import packModele.Promotion;
import packVue.MainWindow;

/**
 * @authors brice.effantin & Eric Duchene
 * @modifed by L Buathier & A. Peytavie
 */

public class Mvc {
    public static void main(String[] args) {
        Promotion p = new Promotion();
        MainWindow fen=new MainWindow(p);
        fen.setVisible(true);
    }
}
