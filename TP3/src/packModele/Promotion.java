package packModele;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Promotion extends Observable {
    private ArrayList<Etudiant> promo = new ArrayList();

    public Promotion() {
        setPromo();
    }

    //Get and set
    private void setPromo() {
        try {
            File file = new File("data\\promoDUT.csv");
            Scanner sc = new Scanner(file);
            String line;
            String[] tmp;

            while (sc.hasNextLine()){
                line = sc.nextLine();
                tmp = line.split(";");
                promo.add(new Etudiant(tmp[0],tmp[1],tmp[2],tmp[3],tmp[4]));
            }
        }
        catch (NullPointerException | FileNotFoundException e){
            System.err.println(e.toString());
        }
        triPromo();
    }
    public ArrayList<Etudiant> getPromo() {
        return promo;
    }

    public String [] setListeEtu(){
        String [] tab  = new String[promo.size()];

        for (int i = 0; i < promo.size(); i++){
            tab[i] = promo.get(i).getNumEtu() +" -  "+ promo.get(i).getNom() + " " + promo.get(i).getPrenom() + " (" + promo.get(i).getDepartment() + ")" ;
        }
        return tab;
    }

    //Fonction pour les Camenbert
    public double[][] getBac(){ //map
        double[][] tab = new double[6][1];
        for (int i = 0; i< promo.size(); i++){
            switch (promo.get(i).getBac()){
                case "S":
                    tab[0][0]++;
                    break;
                case "STI":
                    tab[1][0]++;
                    break;
                case "ES":
                    tab[2][0]++;
                    break;
                case "STG":
                    tab[3][0]++;
                    break;
                case "Autre":
                    tab[4][0]++;
                    break;
                case "Etr":
                    tab[5][0]++;
                    break;
                default:
                    break;
            }
        }
        return tab;
    }
    public HashMap<String,Integer> getDepart(){ //TODO a refaire
        HashMap<String,Integer> dep = new HashMap<>();

        for (Etudiant etu : promo){
            if(dep.get(etu.getDepartment())!= null){
                dep.put(etu.getDepartment(), dep.get(etu.getDepartment()) + 1);
            }
            else{
                dep.put(etu.getDepartment(),1);
            }
        }
        return dep;
    }

    public void triPromo(){
        Etudiant tmp;
        for(int i = promo.size()-1 ; i>=1 ; i--) {
            for(int j = 0 ; j < i ; j++){
                if(Integer.parseInt(promo.get(j).getNumEtu()) > Integer.parseInt(promo.get(j+1).getNumEtu()))
                {
                    tmp = promo.get(j+1);
                    promo.set(j+1, promo.get(j));
                    promo.set(j,tmp);
                }
            }
        }
    }

    public  void suppEtu(String index){
        Etudiant tmp = null;
        for(Etudiant etu : promo){
            if(index.equals(etu.getNumEtu())){
                tmp = etu;
            }
        }
        promo.remove(tmp);
        triPromo();

        setChanged();
        notifyObservers();
    }
    public void addEtu(Etudiant etu){
        promo.add(etu);
        triPromo();
        setChanged();
        notifyObservers();
    }

    //Fonction de check
    public boolean checkNumEtu(String num){
        boolean flag = false;
        for (int i = 0; i < promo.size(); i++){
            if (promo.get(i).getNumEtu().equals(num)){
                flag = true;
            }
        }
        return flag;
    }
    public boolean checkNOm(String etudiant){
        boolean flag = false;
        for (Etudiant etu : promo){
            if (etu.getNom().toUpperCase().equals(etudiant)){
                flag = true;
            }
        }
        return flag;
    }
    public boolean checkPrenom(String etudiant){
        boolean flag = false;
        for (Etudiant etu : promo){
            if (etu.getPrenom().toUpperCase().equals(etudiant)){
                flag = true;
            }
        }
        return flag;
    }
}
