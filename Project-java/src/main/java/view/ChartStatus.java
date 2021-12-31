/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BasicStroke;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;

/**
 * @author TRUNG
 */
public class ChartStatus extends JFrame {
    public ChartStatus(HashMap<String, LinkedHashMap<String, Integer>> data) {
        super("Thống kê");
        setContentPane(createContent(data));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private DefaultCategoryDataset createDataset(HashMap<String, LinkedHashMap<String, Integer>> data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        HashMap<String, Integer> arr;
        for (String i : data.keySet()) {
            arr = data.get(i);
            for (String j : arr.keySet())
                dataset.addValue(arr.get(j), i, j);
        }
        return dataset;
    }

    private JFreeChart createChart(DefaultCategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createLineChart(
                "Thống kê trạng thái",// chart title
                "Thời gian", "Số lượng",
                dataset,          // data
                PlotOrientation.VERTICAL,
                true,             // include legend
                true,
                false);
        LineAndShapeRenderer renderer = (LineAndShapeRenderer)((CategoryPlot)chart.getPlot()).getRenderer();
        renderer.setDefaultShapesVisible(true);
        return chart;
    }

    private JPanel createContent(HashMap<String, LinkedHashMap<String, Integer>> data) {
        JFreeChart chart = createChart(createDataset(data));
        return new ChartPanel(chart);
    }
}
