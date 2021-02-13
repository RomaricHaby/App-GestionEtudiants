package packControleur;

import packModele.Promotion;
import java.util.ArrayList;

public abstract class AbstractControleur {
    protected Promotion promotion;

    public AbstractControleur(Promotion promotion) {
        this.promotion = promotion;
    }

    public abstract void control(ArrayList<String> array);
    public abstract void contolSupForm(String index);
}

