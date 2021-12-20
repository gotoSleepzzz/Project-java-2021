/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.LinkedHashMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author TRUNG
 */
public class ChartConsume extends JFrame{
    public ChartConsume(LinkedHashMap<String,Integer> data){
        super("Thống kê");
        setContentPane(createContent(data));
    }
    private DefaultCategoryDataset createDataset(LinkedHashMap<String,Integer> data) {
      DefaultCategoryDataset dataset = new DefaultCategoryDataset();
      data.keySet().forEach(i -> {
          dataset.addValue(data.get(i),"Số lượng tiêu thụ",i);
      });
      return dataset;         
   }
    private JFreeChart createChart(DefaultCategoryDataset dataset){
    JFreeChart chart = ChartFactory.createBarChart(      
         "Thống kê tiêu thụ",   // chart title
         "Gói nhu yếu phẩm","Số lượng",
         dataset,          // data
         PlotOrientation.VERTICAL,
         true,             // include legend   
         true, 
         false);

      return chart;
    }
    private JPanel createContent(LinkedHashMap<String,Integer> data){
      JFreeChart chart = createChart(createDataset(data) );  
      return new ChartPanel( chart ); 
    }
}
