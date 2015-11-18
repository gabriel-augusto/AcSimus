package presentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.*;

/**
 * @see http://stackoverflow.com/questions/7205742
 * @see http://stackoverflow.com/questions/7208657
 * @see http://stackoverflow.com/questions/7071057
 */
public class SimulationFrame extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SimulationFrame(String s) {
        super(s);
        final ChartPanel chartPanel = GraphicGenerator.getInstance().createPanel();
        JPanel panel = new JPanel();
        this.add(panel, BorderLayout.CENTER);
        panel.add(chartPanel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                SimulationFrame demo = new SimulationFrame("Simulation");
                demo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                demo.pack();
                demo.setLocationRelativeTo(null);
                demo.setVisible(true);
            }
        });
    }
}