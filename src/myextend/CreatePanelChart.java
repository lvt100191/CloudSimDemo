/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myextend;

import java.awt.Color;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author NhuocQuy
 */
public class CreatePanelChart {
    public static JPanel getJPanelChart(String tittle,List<Map<Integer, MyLogCreateVM>> listmap){
        JFreeChart lineChart = ChartFactory.createLineChart(tittle,
				"SỐ lượng máy ảo VM", "% success", createDataset(listmap),
				PlotOrientation.VERTICAL, true, true, false);
//                CategoryPlot xyPlot = lineChart.getCategoryPlot();
//                xyPlot.setBackgroundPaint(Color.WHITE);
//                xyPlot.setBackgroundAlpha(0.0f);    
                CategoryPlot plot = (CategoryPlot) lineChart.getPlot();
                plot.getRenderer().setSeriesPaint(0, Color.RED);
                plot.getRenderer().setSeriesPaint(1, Color.BLUE);
                plot.getRenderer().setSeriesPaint(2, Color.BLACK);
        	ChartPanel chartPanel = new ChartPanel(lineChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
                return chartPanel;
    }
    public  static DefaultCategoryDataset createDataset(
			List<Map<Integer, MyLogCreateVM>> listmap) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
                int base = 0;
		for (Map<Integer, MyLogCreateVM> map : listmap) {
//                    base = map.size()/ 20;
                    base = 50;
			for (Integer i : map.keySet()) {
				if (i % base == 0 ) {
					dataset.addValue(map.get(i).getNumVMCreateSuccess() * 100
							/ (i-1), map.get(i).getNumDC() + "DC", i + "");
				}
			}
		}
		return dataset;
	}
    public static JPanel getJPanelChartCompare3DC(String tittle,
			List<Map<Integer, MyLogCreateVM>> listMapChuaCT,
			List<Map<Integer, MyLogCreateVM>> listMapDaCT,int numDC){
        JFreeChart lineChart = ChartFactory.createLineChart(tittle + " - " + numDC + "DC",
				"SỐ lượng máy ảo VM", "% success", createDataset3DC(listMapChuaCT, listMapDaCT,numDC),
				PlotOrientation.VERTICAL, true, true, false);
//                CategoryPlot xyPlot = lineChart.getCategoryPlot();
//                xyPlot.setBackgroundPaint(Color.WHITE);
//                xyPlot.setBackgroundAlpha(1f);       
                 CategoryPlot plot = (CategoryPlot) lineChart.getPlot();
                plot.getRenderer().setSeriesPaint(0, Color.RED);
                plot.getRenderer().setSeriesPaint(1, Color.BLUE);
                plot.getRenderer().setSeriesPaint(2, Color.BLACK);
        	ChartPanel chartPanel = new ChartPanel(lineChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
                return chartPanel;
    }
    public  static DefaultCategoryDataset createDataset3DC(
			List<Map<Integer, MyLogCreateVM>> listMapChuaCT,
			List<Map<Integer, MyLogCreateVM>> listMapDaCT, int numDC) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
                int base = 0;
		for (Map<Integer, MyLogCreateVM> map : listMapChuaCT) {
//                    base = map.size()/ 20;
                    base = 50;
			for (Integer i : map.keySet()) {
				if (i % base == 0 && map.get(i).getNumDC() == numDC) {
					dataset.addValue(map.get(i).getNumVMCreateSuccess() * 100
							/ (i-1), "Chưa cải tiến", i + "");
				}
			}
		}
                for (Map<Integer, MyLogCreateVM> map : listMapDaCT) {
//                    base = map.size()/ 20;
                    base = 50;
			for (Integer i : map.keySet()) {
				if (i % base == 0 && map.get(i).getNumDC() == numDC) {
					dataset.addValue(map.get(i).getNumVMCreateSuccess() * 100
							/ (i-1), "Đã cải tiến", i + "");
				}
			}
		}
		return dataset;
	}
}
