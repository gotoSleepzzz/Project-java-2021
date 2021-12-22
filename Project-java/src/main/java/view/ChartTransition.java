/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.LinkedHashMap;

/**
 * @author TRUNG
 */
public class ChartTransition extends JFrame {
    public ChartTransition(LinkedHashMap<String, Integer> data) {
        super("Thống kê");
        setContentPane(createContent(data));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private DefaultCategoryDataset createDataset(LinkedHashMap<String, Integer> data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        data.keySet().forEach(i -> {
            dataset.addValue(data.get(i), "Số lượng người", i);
        });
        return dataset;
    }

    private JFreeChart createChart(DefaultCategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                "Thống kê chuyển trạng thái",   // chart title
                "Trạng thái", "Số lượng",
                dataset,          // data
                PlotOrientation.VERTICAL,
                true,             // include legend
                true,
                false);

        return chart;
    }

    private JPanel createContent(LinkedHashMap<String, Integer> data) {
        JFreeChart chart = createChart(createDataset(data));
        return new ChartPanel(chart);
    }
}
