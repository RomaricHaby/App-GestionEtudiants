package packVue;

import packModele.Promotion;
import javax.swing.JInternalFrame;

public abstract class AbstractVue extends JInternalFrame{
    protected Promotion promotion;
    protected MainWindow mainWindow;

   public AbstractVue(Promotion p, MainWindow m){
        this.promotion = p;
        this.mainWindow = m;
   }
}