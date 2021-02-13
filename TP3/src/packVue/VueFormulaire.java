package packVue;

import packControleur.ControleurAddEtu;
import packControleur.ControleurSupp;
import packControleur.ControleurSuppForm;
import packModele.Promotion;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import static javax.swing.JOptionPane.showMessageDialog;

public class VueFormulaire extends AbstractVue implements Observer {
    //TextField
    private final JTextField txtNumeroAjout = new JTextField(10);
    private final JTextField txtNumeroSuppr = new JTextField(10);
    private final JTextField txtNom = new JTextField(10);
    private final JTextField txtPrenom = new JTextField(10);

    //Combobox
    private final JComboBox boxBac = new JComboBox();
    private final JComboBox boxDpt = new JComboBox();

    //Label
    private final JLabel lblNumeroAjout = new JLabel("Numero:");
    private final JLabel lblNumeroSuppr = new JLabel("Numero:");
    private final JLabel lblNom = new JLabel("Nom:");
    private final JLabel lblPrenom = new JLabel("Prénom:");
    private final JLabel lblBac = new JLabel("Bac:");
    private final JLabel lblDpt = new JLabel("Dpt:");
    private final JLabel lblPartieAjout = new JLabel("Ajout d'un étudiant");
    private final JLabel lblPartieSuppr = new JLabel("Suppression d'un étudiant:");
    private final JLabel lblEspace = new JLabel("    ");

    //Button
    private final JButton btAjout = new JButton("Ajout");
    private final JButton btSuppr = new JButton("Supprimer");
    private final JButton btModify = new JButton("Modifier");

    //Controleur
    private ControleurSuppForm suppEtu;
    private ControleurAddEtu addEtu;

    //Regex
    private final String regex_num = "^[0-9]+$";
    private final Pattern pattern_num = Pattern.compile(regex_num);

    public VueFormulaire(Promotion p,MainWindow mainWindow) {
        super(p,mainWindow);
        initFrame();
        promotion.addObserver(this); // pour dire de look sa
        suppEtu = new ControleurSuppForm(p);
        addEtu = new ControleurAddEtu(p);

        this.pack();
    }

    private void initFrame() {
        //remplissage des box
        boxDpt.addItem("- - -");
        for (int i = 1; i < 96; i++) {
            if (i != 20) {
                if (i < 10) {
                    boxDpt.addItem("0" + i);
                } else {
                    boxDpt.addItem("" + i);
                }
            } else {
                boxDpt.addItem("2A");
                boxDpt.addItem("2B");
            }
        }
        for (int i = 971; i < 977; i++) {
            boxDpt.addItem("" + i);
        }
        boxDpt.addItem("Autre");
        //seconde box
        boxBac.addItem("- - -");
        boxBac.addItem("S");
        boxBac.addItem("STI");
        boxBac.addItem("ES");
        boxBac.addItem("STG");
        boxBac.addItem("Etranger");
        boxBac.addItem("Autre");

        //placement des objets
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.BOTH;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 2;
        this.add(lblPartieAjout, gc);
        gc.gridx = 0;
        gc.gridy = 1;
        gc.gridwidth = 1;
        this.add(lblNumeroAjout, gc);
        gc.gridx = 1;
        this.add(txtNumeroAjout, gc);
        gc.gridx = 2;
        this.add(lblPrenom, gc);
        gc.gridx = 3;
        this.add(txtPrenom, gc);
        gc.gridx = 4;
        this.add(lblNom, gc);
        gc.gridx = 5;
        this.add(txtNom, gc);
        gc.gridx = 6;
        this.add(lblBac, gc);
        gc.gridx = 7;
        this.add(boxBac, gc);
        gc.gridx = 8;
        this.add(lblDpt, gc);
        gc.gridx = 9;
        this.add(boxDpt, gc);
        gc.gridx = 10;
        this.add(lblEspace, gc);



        gc.gridx = 11;
        btAjout.addActionListener(new EcouteurForm());
        this.add(btAjout, gc);

        btModify.addActionListener(new EcouteurForm());
        btModify.setVisible(false);
        this.add(btModify, gc);

        gc.gridx = 0;
        gc.gridy = 2;
        gc.gridwidth = 11;
        //this.add(lblSeparation, gc);
        this.add(new JSeparator(SwingConstants.HORIZONTAL), gc);

        gc.gridx = 0;
        gc.gridy = 3;
        gc.gridwidth = 2;
        this.add(lblPartieSuppr, gc);
        gc.gridx = 0;
        gc.gridy = 4;
        gc.gridwidth = 1;
        this.add(lblNumeroSuppr, gc);
        gc.gridx = 1;
        this.add(txtNumeroSuppr, gc);
        gc.gridx = 11;
        btSuppr.addActionListener(new EcouteurSuppr());
        this.add(btSuppr, gc);
    }
    public void resetChamps(){
        txtNumeroAjout.setText("");
        txtNom.setText("");
        txtPrenom.setText("");
        boxBac.setSelectedIndex(0);
        boxDpt.setSelectedIndex(0);
    }

    //Get and set
    public JTextField getTxtNumeroAjout() {
        return txtNumeroAjout;
    }
    public JTextField getTxtNom() {
        return txtNom;
    }
    public JTextField getTxtPrenom() {
        return txtPrenom;
    }
    public JComboBox getBoxBac() {
        return boxBac;
    }
    public JComboBox getBoxDpt() {
        return boxDpt;
    }
    public JButton getBtModify() {
        return btModify;
    }
    public JButton getBtAjout() {
        return btAjout;
    }

    //Ecouteur
    public class EcouteurSuppr implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btSuppr) {
                if(!txtNumeroSuppr.getText().isEmpty()){
                    if (pattern_num.matcher(txtNumeroSuppr.getText()).matches()){
                        String index = txtNumeroSuppr.getText();
                        if(promotion.checkNumEtu(index)){
                            suppEtu.contolSupForm(index);
                            showMessageDialog(null, "Suppression réussite !", "Attention", JOptionPane.WARNING_MESSAGE);
                        }
                        else {
                            showMessageDialog(null, "L'étudiant n'existe pas", "Attention", JOptionPane.WARNING_MESSAGE);
                            txtNumeroSuppr.setText("");
                        }
                    }
                    else{
                        showMessageDialog(null, "Le numéro étudiant doit être un nombre !", "Attention", JOptionPane.WARNING_MESSAGE);
                        txtNumeroSuppr.setText("");
                    }
                }
                else {
                    showMessageDialog(null, "Aucun numéro d'étudiant n'as été rentrer !", "Attention", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }
    public class EcouteurForm implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btAjout) {
                btModify.setVisible(false);
                btAjout.setVisible(true);

                if(!txtNumeroAjout.getText().isEmpty()
                && !txtPrenom.getText().isEmpty()
                && !txtNom.getText().isEmpty()
                && boxBac.getSelectedItem().toString() != "- - -"
                && boxDpt.getSelectedItem().toString() != "- - -"){

                    //Si on rentre autre chose qu'un nombre pour num etu
                    if (pattern_num.matcher(txtNumeroAjout.getText()).matches()){
                        //Si le num etu, ou le nom et prenom sont déjà dans la Jlist on l'ajoute pas
                        if(promotion.checkNumEtu(txtNumeroAjout.getText()) || (promotion.checkNOm(txtNom.getText().toUpperCase()) && promotion.checkPrenom(txtPrenom.getText().toUpperCase()) )){
                            resetChamps();
                            showMessageDialog(null, "Le numéro étudiant ou le nom est déjà utilisé", "Attention", JOptionPane.WARNING_MESSAGE);
                        }
                        else {
                            addEtu.controlAdd(txtNumeroAjout.getText(), txtNom.getText().toUpperCase(), txtPrenom.getText().toUpperCase(), boxDpt.getSelectedItem().toString(), boxBac.getSelectedItem().toString());
                            resetChamps();
                        }
                    }
                    else{
                        showMessageDialog(null, "Le numéro étudiant doit être un nombre !", "Attention", JOptionPane.WARNING_MESSAGE);
                        resetChamps();
                    }
                }
                else {
                    showMessageDialog(null, "Veuillez remplir les champs !", "Attention", JOptionPane.WARNING_MESSAGE);
                    resetChamps();
                }
            }

            if (e.getSource() == btModify){
                suppEtu.contolSupForm(txtNumeroAjout.getText());

                addEtu.controlAdd(txtNumeroAjout.getText(), txtNom.getText().toUpperCase(), txtPrenom.getText().toUpperCase(), boxDpt.getSelectedItem().toString(), boxBac.getSelectedItem().toString());
                resetChamps();

                btModify.setVisible(false);
                btAjout.setVisible(true);
                txtNumeroAjout.setEditable(true);
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        txtNumeroSuppr.setText("");
    }
}
