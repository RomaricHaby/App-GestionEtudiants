package packControleur;

import packModele.Promotion;
import java.util.ArrayList;

public class ControleurSuppForm extends AbstractControleur {

    public ControleurSuppForm(Promotion promotion) {
        super(promotion);
    }

    @Override
    public void control(ArrayList<String> array) {}

    @Override
    public void contolSupForm(String index) {
        promotion.suppEtu(index);
    }
}



