package tools;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jgap.IChromosome;
import org.jgap.impl.CompositeGene;

public class ChartPitchs {

	private static DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	//private static XYDataset dataset = new XYDataset();
//	private DateAxis domain = new DateAxis("Evoluzioni");
//	private NumberAxis range = new NumberAxis("Valore fitness");
//	private XYItemRenderer renderer =null;
//	private XYPlot plot =null;
	//JFreeChart chart=null;
	
	
	public static JPanel getChartPanel(double lower, int numottave){
		JPanel panel=new JPanel();
		panel.add(getChart(lower,lower+numottave*12));
		return panel;
	}
	
	public static ChartPanel getChart(double lower, double upper){
		JFreeChart chart = ChartFactory.createLineChart(
		//JFreeChart chart = ChartFactory.createXYLineChart(
				"andamento delle note", // chart title
				"tempo", // domain axis label
				"nota", // range axis label
				dataset, // data
				PlotOrientation.VERTICAL, // orientation
				false, // include legend
				true, // tooltips
				true // urls
				);
		
		

		((chart.getCategoryPlot()).getDomainAxis()).setTickLabelsVisible(false);
		//((chart.getCategoryPlot()).getRangeAxis()).setRange(lower,upper );
		((chart.getCategoryPlot()).getRangeAxis()).setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		//((chart.getCategoryPlot()).
		LineAndShapeRenderer render=(LineAndShapeRenderer)(chart.getCategoryPlot()).getRenderer();
		//render.setBaseLinesVisible(false);
		render.setShapesVisible(true);
		render.setShapesFilled(true);
		//render.setDrawOutlines(false);
		//render.setUseFillPaint(true);
		//render.setShape(new Ellipse2D.Double(-3.0, -3.0, 6.0, 6.0));
		//render.setPaint(Color.RED);
		
		NumberAxis rangeAxis=(NumberAxis)(chart.getCategoryPlot()).getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		
		ChartPanel chartPanel = new ChartPanel(chart);
		//chartPanel.setSize(100, 1000);
		chartPanel.setBorder(new EmptyBorder(0,0,0,0));
		return chartPanel;
		
	}
	
	public static void updateChart(ArrayList<Integer> pitchs){
		//dataset=new DefaultCategoryDataset();
		//dataset.clear();
		int count=0;
		for(int pitch: pitchs){
			//System.out.println(pitch);
			dataset.addValue(pitch, "brano", String.valueOf(count++));
			count++;
		}
		//panel.add(getChart());
	}
	
	public static void updateChart(IChromosome crhomosome){
		//dataset=new DefaultCategoryDataset();
		//dataset.
		int count=0;
		for(int x=0; x<crhomosome.getGenes().length; x++){
			
			//System.out.println(((CompositeGene) cromosome.getGene(x)).geneAt(2).getAllele().toString());
//			d((CompositeGene) cromosome.getGene(x)).geneAt(2).getAllele();
			dataset.addValue(Integer.parseInt(((CompositeGene) crhomosome.getGene(x)).geneAt(2).getAllele().toString()), "brano", String.valueOf(count++));
			//dataset.addValue(count/10, "brano", String.valueOf(count++));
			//System.out.println("X:"+x+" count: "+count);
			//count++;
			
		}
	}

}
