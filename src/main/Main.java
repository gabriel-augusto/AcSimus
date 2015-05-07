package main;

import jade.wrapper.StaleProxyException;
import utils.Util;
import view.HomeFrame;

public class Main {	
	
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                HomeFrame.getHomeFrame().setVisible(true);
                Util.initiateJadeRma();
                try {
                	Util.initiateAmbient();
                } catch (StaleProxyException e) {
                	e.printStackTrace();
                }
            }
        });
    }    
}
