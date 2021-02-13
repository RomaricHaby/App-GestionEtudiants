package packControleur;

import packModele.Promotion;
import java.util.ArrayList;

public class ControleurSupp extends AbstractControleur {

    public ControleurSupp(Promotion promotion) {
        super(promotion);
    }

    @Override
    public void control(ArrayList<String> array) {
        int nbDelete = array.size();
        for (int i = 0; i < nbDelete; i++){
            String line = array.get(i);
            String[] tmp = line.split(" ");
            String index = tmp[0];

            promotion.suppEtu(index);
        }
    }

    @Override
    public void contolSupForm(String index) {}
}



