package packVue;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import packModele.Promotion;

public class VueHistogrammeChart extends AbstractVue implements Observer {

    private Histogramme histo;

    public VueHistogrammeChart(Promotion p, MainWindow mainWindow) {
        super(p,mainWindow);
        histo = new Histogramme();
        this.setContentPane(histo);
        this.pack();
    }

    public class Histogramme extends ChartPanel {
        public Histogramme() {
            super(null);
            this.setPreferredSize(new Dimension(285, 350));
            CategoryDataset dataset = createDataset();
            final JFreeChart chart = createChart(dataset);
            final ChartPanel chartPanel = new ChartPanel(chart);
            this.setChart(chart);
        }

        private CategoryDataset createDataset() {
            final double[][] data = promotion.getBac();
            String[] prefix = {"S", "STI", "ES", "STG", "Autre", "Etranger"};
            return DatasetUtilities.createCategoryDataset(prefix, new String[]{""}, data);
        }

        private JFreeChart createChart(final CategoryDataset dataset) {
            final JFreeChart chart = ChartFactory.createBarChart3D(
                    "Séries", // chart title
                    "Bacs", // domain axis label
                    "Nombre", // range axis label
                    dataset, // data
                    PlotOrientation.VERTICAL, // orientation
                    true, // include legend
                    true, // tooltips
                    false // urls
            );

            final CategoryPlot plot = chart.getCategoryPlot();
            final CategoryAxis axis = plot.getDomainAxis();
            axis.setCategoryLabelPositions(
                    CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 8.0)
            );
            final BarRenderer3D renderer = (BarRenderer3D) plot.getRenderer();
            renderer.setDrawBarOutline(false);

            return chart;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        histo = new Histogramme();
        this.setContentPane(histo);
        this.pack();
    }
}
