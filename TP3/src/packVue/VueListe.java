package packVue;

import packControleur.ControleurSupp;
import packModele.Etudiant;
import packModele.Promotion;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

import static javax.swing.JOptionPane.showMessageDialog;


public class VueListe extends AbstractVue implements Observer{

    private final JList liste;
    private final JButton btSuppr = new JButton("Supprimer");

    private ControleurSupp supp;

    public VueListe(Promotion p, MainWindow mainWindow) {
        super(p,mainWindow);

        promotion.addObserver(this); // pour dire de look sa
        supp = new ControleurSupp(p);

        liste = new JList();
        liste.setLayoutOrientation(JList.VERTICAL);

        JScrollPane scrollPane = new JScrollPane(liste);
        liste.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.BOTH;
        gc.gridx = 0;
        gc.gridy = 0;
        this.add(scrollPane, gc);
        gc.gridx = 0;
        gc.gridy = 1;
        this.add(btSuppr, gc);
        btSuppr.addActionListener(new EcouteurSuppr2());

        liste.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int index = liste.locationToIndex(evt.getPoint()); // on récupère l'index dans la Jliste de l'étudiant qui a été sélectionner

                    Etudiant etu = promotion.getPromo().get(index);

                    mainWindow.getForm().getTxtNumeroAjout().setText(etu.getNumEtu());
                    mainWindow.getForm().getTxtPrenom().setText(etu.getPrenom());
                    mainWindow.getForm().getTxtNom().setText(etu.getNom());

                    mainWindow.getForm().getBoxBac().setSelectedItem(etu.getBac());


                    if(etu.getDepartment() == "1"){
                        mainWindow.getForm().getBoxDpt().setSelectedItem("0"+etu.getDepartment());
                    }
                    else{
                        mainWindow.getForm().getBoxDpt().setSelectedItem(etu.getDepartment());
                    }

                    mainWindow.getForm().getBtAjout().setVisible(false);
                    mainWindow.getForm().getBtModify().setVisible(true);

                    mainWindow.getForm().getTxtNumeroAjout().setEditable(false);
                }
            }
        });

        remplissageListe();
        this.pack();
        liste.setVisibleRowCount(this.getHeight()/8);
        this.pack();
    }

    private void remplissageListe() {
        liste.removeAll();
        liste.setListData(promotion.setListeEtu());
    }

    public class EcouteurSuppr2 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (liste.getSelectedValue() != null) {
                ArrayList<String> etus = (ArrayList) liste.getSelectedValuesList();
                supp.control(etus);
            }
            else {
                showMessageDialog(null, "Aucun étudiant n'est sélectionné !", "Attention", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof Promotion){
            remplissageListe();
        }
    }
}
