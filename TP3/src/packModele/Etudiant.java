package packModele;

public class Etudiant {
    private String numEtu;
    private String nom;
    private String prenom;
    private String department;
    private String bac;

    public Etudiant(String numEtu, String nom, String prenom, String department, String bac) {
        this.numEtu = numEtu;
        this.nom = nom;
        this.prenom = prenom;
        this.department = department;
        this.bac = bac;
    }
    //Get and Set
    public String getNumEtu() {
        return numEtu;
    }
    public String getNom() {
        return nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public String getDepartment() {
        return department;
    }
    public String getBac() {
        return bac;
    }
    public void setBac(String bac) {
        this.bac = bac;
    }

    @Override
    public String toString() {
        return ("Id : "+ getNumEtu() + " Nom : " + getNom() + " Prenom : " + getPrenom() + " Departement : " + getDepartment() + " Bac : " + getBac() );
    }
}
