package co.edu.unicauca.layersmvc.presentation;

import co.edu.unicauca.layersmvc.domain.Product;
import co.edu.unicauca.layersmvc.domain.service.ServiceModel;
import co.edu.unicauca.layersmvc.infra.Observer;
import java.awt.Dimension;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Santiago Acuña
 */
public class GUIGraficoBarras extends JFrame implements Observer {

    ServiceModel service;
    JFreeChart barras;
    double min1 = 0;
    double btw12 = 0;
    double max2 = 0;

    public GUIGraficoBarras(ServiceModel service, String nameWindow) {
        super(nameWindow);
        this.service = service;
        setSize(560, 367);
//        initComponents("Cantidad", "Productos");
        initComponents("Precios","Productos");

    }

    @Override
    public void update(Object o) {
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//        initComponents("Cantidad", "Productos");
        initComponents("Precios","Productos");
    }

    private void initComponents(String s1, String s2) {
        barras = ChartFactory.createBarChart(
                "Grafico de barras", s2, s1, crearDataset(),
                PlotOrientation.VERTICAL, true, true, false);
        ChartPanel chartPanel = new ChartPanel(barras);
        chartPanel.setPreferredSize(new Dimension(560, 367));
        setContentPane(chartPanel);
    }

//    private CategoryDataset crearDataset() {
//        min1 = 0;
//        btw12 = 0;
//        max2 = 0;
//        final String pregunta = "Productos";
//        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
//        for (Product p : service.listProducts()) {
//            if (p.getPrice() <= 1000000) {
//                min1++;
//            }
//            if (p.getPrice() >= 1000000 && p.getPrice() <= 2000000) {
//                btw12++;
//            }
//            if (p.getPrice() >= 2000000) {
//                max2++;
//            }
//        }
//        dataSet.addValue(min1, "Menor a 1.000.000", pregunta);
//        dataSet.addValue(btw12, "Entre 1.000.000 y 2.000.000", pregunta);
//        dataSet.addValue(max2, "Mayor a 2.000.000", pregunta);
//        return dataSet;
//    }

    private CategoryDataset crearDataset() {
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        for (Product p : service.listProducts()) {
            if (p.getPrice() < 1000000) {
                dataSet.addValue(p.getPrice(), "Menor a 1.000.000", p.getName());
            }
            if (p.getPrice() >= 1000000 && p.getPrice() <= 2000000) {
                dataSet.addValue(p.getPrice(), "Entre 1.000.000 y 2.000.000", p.getName());
            }
            if (p.getPrice() > 2000000) {
                dataSet.addValue(p.getPrice(), "Mayor a 2.000.000", p.getName());
            }
        }
        return dataSet;
    }

}
