package application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import database.CRUD;

public class ShowChart extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	HashMap<String, Integer> map;
	Iterator<String> it;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowChart frame = new ShowChart(args[0]);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ShowChart(String facultyName) {
		map = CRUD.getOverallRating(facultyName);
		it = map.keySet().iterator();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setTitle("Stats of "+facultyName);		
		PieDataset dataset = createDataset();
		JFreeChart chart = createChart(dataset,facultyName);
		ChartPanel chartPanel = new ChartPanel(chart);
		contentPane.add(chartPanel);
		chartPanel.setPreferredSize(new Dimension(700,600));
	}
	private PieDataset createDataset() {
		DefaultPieDataset result = new DefaultPieDataset();
		for(int i =0;i<map.size();i++) {
			String res = it.next();
			result.setValue(res, map.get(res));
		}
		return result;
	}
	private JFreeChart createChart(PieDataset dataset,String title) {
		JFreeChart chart = ChartFactory.createPieChart3D(title, dataset, true, true, false);
		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setSectionPaint("1", Color.RED);
		plot.setSectionPaint("2", Color.ORANGE);
		plot.setSectionPaint("3", Color.YELLOW);
		plot.setSectionPaint("4", Color.BLUE);
		plot.setSectionPaint("5", Color.GREEN);
		PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
	            "{0}: {1} ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
		plot.setLabelGenerator(gen);

		return chart;
	}
}
