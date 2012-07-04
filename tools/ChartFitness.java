package tools;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class ChartFitness {
	
	private static int count=0;
	private static int numChart=0;
	private static DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//	private DateAxis domain = new DateAxis("Evoluzioni");
//	private NumberAxis range = new NumberAxis("Valore fitness");
//	private XYItemRenderer renderer =null;
//	private XYPlot plot =null;
	//JFreeChart chart=null;
	
	
	public static JPanel getChartPanel(){
		JPanel panel=new JPanel();
		panel.add(getChart());
		return panel;
	}
	
	public static ChartPanel getChart(){
//		dataset.addValue(212, "Classes", "JDK 1.0");
//		dataset.addValue(504, "Classes", "JDK 1.1");
//		dataset.addValue(1520, "Classes", "SDK 1.2");
//		dataset.addValue(1842, "Classes", "SDK 1.3");
//		dataset.addValue(2991, "Classes", "SDK 1.4");
		
//		DateAxis domain = new DateAxis("Evoluzioni");
//		NumberAxis range = new NumberAxis("Valore fitness");
//		domain.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));
//		range.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));
//		domain.setLabelFont(new Font("SansSerif", Font.PLAIN, 14));
//		range.setLabelFont(new Font("SansSerif", Font.PLAIN, 14));
//		XYItemRenderer renderer = new XYLineAndShapeRenderer(true, false);
//		renderer.setSeriesPaint(0, Color.red);
//		
//		XYPlot plot = new XYPlot((XYDataset) dataset, domain, range, renderer);
//		plot.setBackgroundPaint(Color.lightGray);
//		plot.setDomainGridlinePaint(Color.white);
//		plot.setRangeGridlinePaint(Color.white);
//		//plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
//		domain.setAutoRange(true);
//		domain.setLowerMargin(0.0);
//		domain.setUpperMargin(1.0);
//		domain.setTickLabelsVisible(true);
//		
//		range.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
//		
//		JFreeChart chart = new JFreeChart(
//				"JVM Memory Usage",
//				new Font("SansSerif", Font.BOLD, 24),
//				plot,
//				true
//				);
		
		JFreeChart chart = ChartFactory.createLineChart(
				"Java Standard Class Library", // chart title
				"Evoluzioni", // domain axis label
				"Fitness value", // range axis label
				dataset, // data
				PlotOrientation.VERTICAL, // orientation
				true, // include legend
				false, // tooltips
				false // urls
				);
		((chart.getCategoryPlot()).getDomainAxis()).setTickLabelsVisible(false);
		//((chart.getCategoryPlot()).getRangeAxis()).setTickMarksVisible(false);
		//chart.setBackgroundPaint(Color.white);
		ChartPanel chartPanel = new ChartPanel(chart);
		return chartPanel;
		
	}
	
	public static void newChart(){
		numChart++;
		count=0;
	}
	
	public  static void addNewFitness(double y) {
		dataset.addValue(y, "Fitness"+numChart, String.valueOf(count++));
		}
	
	public static void resetChart(){
		dataset=new DefaultCategoryDataset();
		count=0;
		numChart=0;
	}
	
	

}
