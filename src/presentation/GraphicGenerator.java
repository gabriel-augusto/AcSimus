package presentation;

import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;

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

import simulator.objects.SimulationStatus;
import simulator.objects.SoundObject;
import simulator.objects.SoundSourceObject;

public class GraphicGenerator{
	
	private JFreeChart graphic = null;
	private XYItemRenderer renderer = null;
	private XYSeries sounds = new XYSeries("Sound");
    private XYSeries soundSources = new XYSeries("SoundSource");
    private XYPlot xyPlot = null;
    private static GraphicGenerator graphicGenerator = null;
    ChartPanel panel = new ChartPanel(graphic);
    private int red;
    private int blue;
    private SimulationStatus simulation = SimulationStatus.getInstance();
	
	private GraphicGenerator() {
		
	}
	
	public static GraphicGenerator getInstance(){
		if(graphicGenerator == null){
			graphicGenerator = new GraphicGenerator();
		}
		return graphicGenerator;
	}
	
	public ChartPanel createPanel() {        
        return panel;
    }
	
	public void setBounds(int width, int length){
		graphic = ChartFactory.createScatterPlot("Ambiente", "Largura", "Comprimento", createSampleData(), PlotOrientation.VERTICAL, true, true, false);
		panel.setChart(graphic);
		
		xyPlot = (XYPlot) graphic.getPlot();
        xyPlot.setDomainCrosshairVisible(true);
        xyPlot.setRangeCrosshairVisible(true);
        xyPlot.setBackgroundPaint(Color.white);
        
        renderer = xyPlot.getRenderer();
        renderer.setSeriesPaint(0, new Color(0,150,0));
		
		NumberAxis domain = (NumberAxis) xyPlot.getDomainAxis();
        adjustAxis((NumberAxis) xyPlot.getDomainAxis(), width, true);
        adjustAxis((NumberAxis) xyPlot.getRangeAxis(), length, false);
        
        domain.setVerticalTickLabels(true);
	}
	
	private XYDataset createSampleData() {
        XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
        xySeriesCollection.addSeries(soundSources);
        xySeriesCollection.addSeries(sounds);
        return xySeriesCollection;
    }
	
	public void updateSounds(){
		sounds.clear();	
		renderer.setSeriesPaint(1, new Color(red,0,blue));
		
		red = (int) (simulation.getDecibel()*255)/120;
		blue = (int)(255 - (simulation.getDecibel()*255)/120);
		
		if(red > 255)
			red=255;
		if(red < 0)
			red = 0;
		if(blue > 255)
			blue = 255;
		if(blue < 0)
			blue = 0;	
		
		for(SoundObject sound : SoundObject.getSounds().values()){
            sounds.add(sound.getActualLocation().getX(), sound.getActualLocation().getY());
        }
	}
	
	public void updateSoundSources(){
		soundSources.clear();
		for(SoundSourceObject soundSource : SoundSourceObject.getSoundSources().values()){
			soundSources.add(soundSource.getLocation().getX(), soundSource.getLocation().getY());
		}
	}
	
	public void clearGraphic(){
		sounds.clear();
		soundSources.clear();
	}
	
	private void adjustAxis(NumberAxis axis, int value, boolean vertical) {
        axis.setRange(0, value);
        axis.setTickUnit(new NumberTickUnit(1));
        axis.setVerticalTickLabels(vertical);
    }
	 
	public void save(OutputStream out) throws IOException {
		ChartUtilities.writeChartAsPNG(out, graphic, 500, 350);
	}
}
