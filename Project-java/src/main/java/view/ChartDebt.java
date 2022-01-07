/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 *
 * @author TRUNG
 */
public class ChartDebt extends JFrame{
    public ChartDebt(LinkedHashMap<String,Double> data){
        super("Thống kê");
        setContentPane(createContent(data));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    private static PieDataset createDataset(LinkedHashMap<String,Double> data) {
      DefaultPieDataset dataset = new DefaultPieDataset( );
      data.keySet().forEach(i -> {
          dataset.setValue(i,data.get(i));
      });  
      return dataset;         
   }
    private JFreeChart createChart(PieDataset dataset){
    JFreeChart chart = ChartFactory.createPieChart(      
         "Thống kê dư nợ",   // chart title 
         dataset,          // data    
         true,             // include legend   
         true, 
         false);

      return chart;
    }
    private JPanel createContent(LinkedHashMap<String,Double> data){
      JFreeChart chart = createChart(createDataset(data));  
      PiePlot plot = (PiePlot) chart.getPlot();
      PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
            "{0}: {1} người ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
      plot.setLabelGenerator(gen);
      return new ChartPanel( chart ); 
    }
}
