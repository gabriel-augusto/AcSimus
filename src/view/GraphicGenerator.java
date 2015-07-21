package view;

import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import simulator.objects.SoundObject;

public class GraphicGenerator {
	
	private JFreeChart graphic;
	private XYSeries series = null;
	
	public GraphicGenerator() {
		generateGraphic();
	}
	
	public void generateGraphic(){
		this.graphic = ChartFactory.createScatterPlot("Ambiente", "Largura", "Comprimento", createSampleData(), PlotOrientation.VERTICAL, true, true, false);	
		XYPlot xyPlot = (XYPlot) graphic.getPlot();
        xyPlot.setDomainCrosshairVisible(true);
        xyPlot.setRangeCrosshairVisible(true);
        XYItemRenderer renderer = xyPlot.getRenderer();
        renderer.setSeriesPaint(0, Color.red);
        adjustAxis((NumberAxis) xyPlot.getDomainAxis(), true);
        adjustAxis((NumberAxis) xyPlot.getRangeAxis(), false);
        xyPlot.setBackgroundPaint(Color.white);
	}
	
	private XYDataset createSampleData() {
        XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
        series = new XYSeries("Data");
        for(SoundObject sound : SoundObject.getSounds().values()){
            series.add(sound.getActualLocation().getX(), sound.getActualLocation().getY());
        }
        xySeriesCollection.addSeries(series);
        return xySeriesCollection;
    }
	
	private void adjustAxis(NumberAxis axis, boolean vertical) {
        axis.setRange(0, 10);
        axis.setTickUnit(new NumberTickUnit(1));
        axis.setVerticalTickLabels(vertical);
    }
	
	public JPanel getPanel() {
		generateGraphic();
		return new ChartPanel(graphic);
	}
	 
	public void save(OutputStream out) throws IOException {
		ChartUtilities.writeChartAsPNG(out, graphic, 500, 350);
	}
}
