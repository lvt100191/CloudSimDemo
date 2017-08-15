package myextend;

import java.awt.Color;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class ChartPercentageCreateVM extends JFrame {

    private static final long serialVersionUID = -8432699262984239315L;

    public ChartPercentageCreateVM(String applicationTitle,
            List<Map<Integer, MyLogCreateVM>> listmap) {
        super(applicationTitle);

        //
//                JFreeChart lineChart = ChartFactory.createLineChart(applicationTitle,
//				"SỐ lượng máy ảo VM", "% success", createDataset(listmap),
//				PlotOrientation.VERTICAL, true, true, false);
//                CategoryPlot xyPlot = lineChart.getCategoryPlot();
//                xyPlot.setBackgroundPaint(Color.WHITE);
//                xyPlot.setBackgroundAlpha(0.0f);
//		ChartPanel chartPanel = new ChartPanel(lineChart);
//		chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        //
        setContentPane(CreatePanelChart.getJPanelChart(applicationTitle, listmap));
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        RefineryUtilities.centerFrameOnScreen(this);
        setVisible(true);
    }

    public ChartPercentageCreateVM(String tittle, JPanel chart) {
        super(tittle);
        setContentPane(chart);
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        RefineryUtilities.centerFrameOnScreen(this);
        setVisible(true);
    }

}
