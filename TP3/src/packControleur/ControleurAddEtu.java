package packControleur;

import packModele.Etudiant;
import packModele.Promotion;
import java.util.ArrayList;

public class ControleurAddEtu extends AbstractControleur {

    public ControleurAddEtu(Promotion promotion) {
        super(promotion);
    }

    @Override
    public void control(ArrayList<String> array) {}

    @Override
    public void contolSupForm(String index) {}

    public void controlAdd(String numEtu, String nom, String prenom, String department, String bac){
            Etudiant etu = new Etudiant(numEtu,nom,prenom,department,bac);
            promotion.addEtu(etu);
    }
}



