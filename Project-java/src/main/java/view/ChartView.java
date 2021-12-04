/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.HashMap;
import java.util.LinkedHashMap;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author TRUNG
 */
class ThongKeDuNo extends ApplicationFrame{
    public ThongKeDuNo(LinkedHashMap<String,Double> data){
        super("Thống kê");
        setContentPane(createContent(data));
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
      JFreeChart chart = createChart(createDataset(data) );  
      return new ChartPanel( chart ); 
    }
}

class ThongKeTrangThai extends ApplicationFrame{
    public ThongKeTrangThai(HashMap<String,LinkedHashMap<String,Integer>> data){
        super("Thống kê");
        setContentPane(createContent(data));
    }
    private DefaultCategoryDataset createDataset(HashMap<String,LinkedHashMap<String,Integer>> data) {
      DefaultCategoryDataset dataset = new DefaultCategoryDataset();
      HashMap<String,Integer> arr;
      for (String i: data.keySet())
      {
          arr = data.get(i);
          for(String j: arr.keySet())
              dataset.addValue(arr.get(j), i, j);
      }
      return dataset;         
   }
    private JFreeChart createChart(DefaultCategoryDataset dataset){
    JFreeChart chart = ChartFactory.createLineChart(      
         "Thống kê trạng thái",// chart title
         "Thời gian", "Số lượng",
         dataset,          // data
         PlotOrientation.VERTICAL,
         true,             // include legend   
         true, 
         false);

      return chart;
    }
    private JPanel createContent(HashMap<String,LinkedHashMap<String,Integer>> data){
      JFreeChart chart = createChart(createDataset(data) );  
      return new ChartPanel( chart ); 
    }
}

class ThongKeTieuThu extends ApplicationFrame{
    public ThongKeTieuThu(LinkedHashMap<String,Integer> data){
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
class ThongKeChuyenTT extends ApplicationFrame{
    public ThongKeChuyenTT(LinkedHashMap<String,Integer> data){
        super("Thống kê");
        setContentPane(createContent(data));
    }
    private DefaultCategoryDataset createDataset(LinkedHashMap<String,Integer> data) {
      DefaultCategoryDataset dataset = new DefaultCategoryDataset();
      data.keySet().forEach(i -> {
          dataset.addValue(data.get(i),"Số lượng người",i);
      });
      return dataset;         
   }
    private JFreeChart createChart(DefaultCategoryDataset dataset){
    JFreeChart chart = ChartFactory.createBarChart(      
         "Thống kê chuyển trạng thái",   // chart title
         "Trạng thái","Số lượng",
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

public class ChartView{
   private static void ThongKeDuNoView(LinkedHashMap<String, Double> data){
      ThongKeDuNo chart = new ThongKeDuNo(data);
      chart.pack();
      RefineryUtilities.centerFrameOnScreen( chart );
      chart.setVisible( true );
   }
   private static void ThongKeTrangThaiView(HashMap<String,LinkedHashMap<String,Integer>> data){
      ThongKeTrangThai chart = new ThongKeTrangThai(data);
      chart.pack();
      RefineryUtilities.centerFrameOnScreen( chart );
      chart.setVisible( true );
   }
   private static void ThongKeTieuThuView(LinkedHashMap<String,Integer> data){
      ThongKeTieuThu chart = new ThongKeTieuThu(data);
      chart.pack();
      RefineryUtilities.centerFrameOnScreen( chart );
      chart.setVisible( true );
   }
   private static void ThongKeChuyenTTView(LinkedHashMap<String,Integer> data){
      ThongKeChuyenTT chart = new ThongKeChuyenTT(data);
      chart.pack();
      RefineryUtilities.centerFrameOnScreen( chart );
      chart.setVisible( true );
   }
   public static void main( String[ ] args ) {
      /*LinkedHashMap<String, Double> demo = new LinkedHashMap();
      demo.put("<500", 60d);
      demo.put("500-1000", 40d);
      ThongKeDuNoView(demo);*/
      
      /*LinkedHashMap<String, Integer> F1 = new LinkedHashMap();
      F1.put("1970", 15);
      F1.put("1980", 30);
      F1.put("1990", 60);
      F1.put("2000", 120);
      F1.put("2010", 240);
      F1.put("2014", 300);
      LinkedHashMap<String, Integer> F2 = new LinkedHashMap();
      F2.put("1970", 30);
      F2.put("1980", 45);
      F2.put("1990", 50);
      F2.put("2000", 100);
      F2.put("2010", 60);
      F2.put("2014", 150);
      HashMap<String,LinkedHashMap<String, Integer>> status = new HashMap();
      status.put("F1",F1);
      status.put("F2",F2);
      ThongKeTrangThaiView(status);*/
      
      /*LinkedHashMap<String, Integer> demo = new LinkedHashMap();
      demo.put("Food 1", 100);
      demo.put("Food 2", 300);
      demo.put("Food 3", 900);
      ThongKeTieuThuView(demo);*/
      
      /*LinkedHashMap<String, Integer> demo = new LinkedHashMap();
      demo.put("Chuyển trạng thái", 100);
      demo.put("Khỏi bệnh", 300);
      ThongKeChuyenTTView(demo);*/
   }
}