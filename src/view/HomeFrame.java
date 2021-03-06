/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

import languagesAndMessages.Message;
import settings.ProjectSettings;
import simulator.objects.AmbientObject;
import simulator.objects.Obstacle;
import simulator.objects.SimulationStatus;
import simulator.objects.SoundObject;
import simulator.objects.SoundSourceObject;
import simulator.objects.UIController;

/**
 *
 * @author Gabriel
 */
public class HomeFrame extends javax.swing.JFrame {
	
    private static HomeFrame homeFrame= null;
    private static final long serialVersionUID = 1L;
    
    private GraphicGenerator graphicGenerator = GraphicGenerator.getInstance();
    private SimulationStatus simulation = SimulationStatus.getInstance();
    
    private final AmbientSettingsFrame ambienteSettingsFrame = new AmbientSettingsFrame();
    private final SoundSourceSettingsFrame soundSourceSettingsFrame = new SoundSourceSettingsFrame();
    private final SimulationSettingsFrame simulationSettingsFrame = new SimulationSettingsFrame();
    private final ObstaclesSettingsFrame obstacleSettingsFrame = new ObstaclesSettingsFrame();
    
    private static DefaultTableModel obstacleModel = new DefaultTableModel(null, new String [] {"Nº", "ID", "Initial Point", "End Point", "Absorption Rate"});
    private static DefaultTableModel soundSourceModel = new DefaultTableModel(null, new String [] {"Nº", "ID", "Nº of Sounds", "Opening", "Location", "Direction"});
    private static int obstacleCount = 0;
    private static int soundSourceCount = 0;
    
    private HomeFrame() {
        initComponents();

        /*
        //Put console in application
        PrintStream printStream = new PrintStream(new CustomOutputStream(jTextAreaLog));
         
        // re-assigns standard output stream and error output stream
        System.setOut(printStream);
        System.setErr(printStream);  
        */
    }
    
    public static HomeFrame getHomeFrame(){
    	if(homeFrame == null){
    		homeFrame = new HomeFrame();
    	}
    	return homeFrame;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar = new javax.swing.JToolBar();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jButtonRun = new javax.swing.JButton();
        jButtonStop = new javax.swing.JButton();
        jButtonResume = new javax.swing.JButton();
        jButtonPause = new javax.swing.JButton();
        jButtonConfig = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jPanelHome = new javax.swing.JPanel();
        jPanelBody = new javax.swing.JPanel();
        jSplitPaneBody = new javax.swing.JSplitPane();
        jSplitPaneMenu = new javax.swing.JSplitPane();
        jPanelObstacles = new javax.swing.JPanel();
        jLabelObstacles = new javax.swing.JLabel();
        jScrollPaneTableObstacles = new javax.swing.JScrollPane();
        jTableObstacles = new javax.swing.JTable();
        jButtonAddObstacle = new javax.swing.JButton();
        jButtonRemoveObstacle = new javax.swing.JButton();
        jPanelSoundSources = new javax.swing.JPanel();
        jLabelSoundSources = new javax.swing.JLabel();
        jScrollPaneTableSoundSources = new javax.swing.JScrollPane();
        jTableSoundSources = new javax.swing.JTable();
        jButtonAddSoundSource = new javax.swing.JButton();
        jButtonRemoveSoundSource = new javax.swing.JButton();
        jPanelRight = new javax.swing.JPanel();
        jPanelMonitor = graphicGenerator.createPanel();
        jLabelReverberacao = new javax.swing.JLabel();
        jLabelNivel = new javax.swing.JLabel();
        jMenuBar = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuItemRun = new javax.swing.JMenuItem();
        jMenuItemStop = new javax.swing.JMenuItem();
        jMenuItemPause = new javax.swing.JMenuItem();
        jMenuItemResume = new javax.swing.JMenuItem();
        jMenuItemExit = new javax.swing.JMenuItem();
        jMenuSoundEdit = new javax.swing.JMenu();
        jMenuItemAmbient = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItemSoundSource = new javax.swing.JMenuItem();
        jMenuItemSimulationSetting = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(ProjectSettings.getProjectSettings().PROJECT_NAME);
        setExtendedState(MAXIMIZED_BOTH);

        jToolBar.setFloatable(false);
        jToolBar.setRollover(true);
        jToolBar.add(jSeparator1);

        jButtonRun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/runIcon.png"))); // NOI18N
        jButtonRun.setToolTipText("Run simulation");
        jButtonRun.setEnabled(false);
        jButtonRun.setFocusable(false);
        jButtonRun.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonRun.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRunActionPerformed(evt);
            }
        });
        jToolBar.add(jButtonRun);

        jButtonStop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/stopIcon.png"))); // NOI18N
        jButtonStop.setToolTipText("Stop");
        jButtonStop.setEnabled(false);
        jButtonStop.setFocusable(false);
        jButtonStop.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonStop.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStopActionPerformed(evt);
            }
        });
        jToolBar.add(jButtonStop);

        jButtonResume.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/resumeIcon.png"))); // NOI18N
        jButtonResume.setToolTipText("Resume");
        jButtonResume.setEnabled(false);
        jButtonResume.setFocusable(false);
        jButtonResume.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonResume.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonResume.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResumeActionPerformed(evt);
            }
        });
        jToolBar.add(jButtonResume);

        jButtonPause.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/pauseIcon.png"))); // NOI18N
        jButtonPause.setToolTipText("Pause");
        jButtonPause.setEnabled(false);
        jButtonPause.setFocusable(false);
        jButtonPause.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonPause.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPauseActionPerformed(evt);
            }
        });
        jToolBar.add(jButtonPause);

        jButtonConfig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/settingsIcon.png"))); // NOI18N
        jButtonConfig.setFocusable(false);
        jButtonConfig.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonConfig.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConfigActionPerformed(evt);
            }
        });
        jToolBar.add(jButtonConfig);
        jToolBar.add(jSeparator2);

        jSplitPaneBody.setDividerLocation(500);

        jSplitPaneMenu.setDividerLocation(350);
        jSplitPaneMenu.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jLabelObstacles.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabelObstacles.setText("OBSTACLES:");

        jTableObstacles.setModel(obstacleModel);
        jScrollPaneTableObstacles.setViewportView(jTableObstacles);

        jButtonAddObstacle.setText("ADD");
        jButtonAddObstacle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddObstacleActionPerformed(evt);
            }
        });

        jButtonRemoveObstacle.setText("Remove");
        jButtonRemoveObstacle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoveObstacleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelObstaclesLayout = new javax.swing.GroupLayout(jPanelObstacles);
        jPanelObstacles.setLayout(jPanelObstaclesLayout);
        jPanelObstaclesLayout.setHorizontalGroup(
            jPanelObstaclesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelObstaclesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelObstaclesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneTableObstacles, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                    .addGroup(jPanelObstaclesLayout.createSequentialGroup()
                        .addGroup(jPanelObstaclesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelObstacles)
                            .addGroup(jPanelObstaclesLayout.createSequentialGroup()
                                .addComponent(jButtonAddObstacle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonRemoveObstacle)))
                        .addGap(0, 343, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelObstaclesLayout.setVerticalGroup(
            jPanelObstaclesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelObstaclesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelObstacles)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneTableObstacles, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelObstaclesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAddObstacle)
                    .addComponent(jButtonRemoveObstacle))
                .addContainerGap())
        );

        jSplitPaneMenu.setTopComponent(jPanelObstacles);

        jLabelSoundSources.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabelSoundSources.setText("SOUND SOURCES:");

        jTableSoundSources.setModel(soundSourceModel);
        jScrollPaneTableSoundSources.setViewportView(jTableSoundSources);

        jButtonAddSoundSource.setText("ADD");
        jButtonAddSoundSource.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddSoundSourceActionPerformed(evt);
            }
        });

        jButtonRemoveSoundSource.setText("Remove");
        jButtonRemoveSoundSource.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoveSoundSourceActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelSoundSourcesLayout = new javax.swing.GroupLayout(jPanelSoundSources);
        jPanelSoundSources.setLayout(jPanelSoundSourcesLayout);
        jPanelSoundSourcesLayout.setHorizontalGroup(
            jPanelSoundSourcesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSoundSourcesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSoundSourcesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneTableSoundSources, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanelSoundSourcesLayout.createSequentialGroup()
                        .addGroup(jPanelSoundSourcesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelSoundSources)
                            .addGroup(jPanelSoundSourcesLayout.createSequentialGroup()
                                .addComponent(jButtonAddSoundSource)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonRemoveSoundSource)))
                        .addGap(0, 343, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelSoundSourcesLayout.setVerticalGroup(
            jPanelSoundSourcesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSoundSourcesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelSoundSources)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneTableSoundSources, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelSoundSourcesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAddSoundSource)
                    .addComponent(jButtonRemoveSoundSource))
                .addContainerGap())
        );

        jSplitPaneMenu.setRightComponent(jPanelSoundSources);

        jSplitPaneBody.setLeftComponent(jSplitPaneMenu);

        javax.swing.GroupLayout jPanelMonitorLayout = new javax.swing.GroupLayout(jPanelMonitor);
        jPanelMonitor.setLayout(jPanelMonitorLayout);
        jPanelMonitorLayout.setHorizontalGroup(
            jPanelMonitorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanelMonitorLayout.setVerticalGroup(
            jPanelMonitorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 409, Short.MAX_VALUE)
        );

        jLabelReverberacao.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabelReverberacao.setText("Reverberation time: --");

        jLabelNivel.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabelNivel.setText("Sound intensity level:");

        javax.swing.GroupLayout jPanelRightLayout = new javax.swing.GroupLayout(jPanelRight);
        jPanelRight.setLayout(jPanelRightLayout);
        jPanelRightLayout.setHorizontalGroup(
            jPanelRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelMonitor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanelRightLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelNivel, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                    .addComponent(jLabelReverberacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelRightLayout.setVerticalGroup(
            jPanelRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRightLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelReverberacao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelMonitor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelNivel)
                .addContainerGap())
        );

        jSplitPaneBody.setRightComponent(jPanelRight);

        javax.swing.GroupLayout jPanelBodyLayout = new javax.swing.GroupLayout(jPanelBody);
        jPanelBody.setLayout(jPanelBodyLayout);
        jPanelBodyLayout.setHorizontalGroup(
            jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPaneBody, javax.swing.GroupLayout.DEFAULT_SIZE, 792, Short.MAX_VALUE)
        );
        jPanelBodyLayout.setVerticalGroup(
            jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBodyLayout.createSequentialGroup()
                .addComponent(jSplitPaneBody)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanelHomeLayout = new javax.swing.GroupLayout(jPanelHome);
        jPanelHome.setLayout(jPanelHomeLayout);
        jPanelHomeLayout.setHorizontalGroup(
            jPanelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelBody, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelHomeLayout.setVerticalGroup(
            jPanelHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelBody, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jMenuFile.setText("Action");

        jMenuItemRun.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemRun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/runIcon.png"))); // NOI18N
        jMenuItemRun.setText("Run simulation");
        jMenuItemRun.setEnabled(false);
        jMenuItemRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRunActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemRun);

        jMenuItemStop.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemStop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/stopIcon.png"))); // NOI18N
        jMenuItemStop.setText("Stop simulation");
        jMenuItemStop.setEnabled(false);
        jMenuItemStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemStopActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemStop);

        jMenuItemPause.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/pauseIcon.png"))); // NOI18N
        jMenuItemPause.setText("Pause simulation");
        jMenuItemPause.setEnabled(false);
        jMenuItemPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPauseActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemPause);

        jMenuItemResume.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/resumeIcon.png"))); // NOI18N
        jMenuItemResume.setText("Resume simulation");
        jMenuItemResume.setEnabled(false);
        jMenuItemResume.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemResumeActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemResume);

        jMenuItemExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        jMenuItemExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/exitIcon.png"))); // NOI18N
        jMenuItemExit.setText("Exit");
        jMenuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemExitActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemExit);

        jMenuBar.add(jMenuFile);

        jMenuSoundEdit.setText("Edit");

        jMenuItemAmbient.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemAmbient.setText("Define ambient");
        jMenuItemAmbient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAmbientActionPerformed(evt);
            }
        });
        jMenuSoundEdit.add(jMenuItemAmbient);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Add Obstacles");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenuSoundEdit.add(jMenuItem1);

        jMenuItemSoundSource.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemSoundSource.setText("Add Sound Source");
        jMenuItemSoundSource.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSoundSourceActionPerformed(evt);
            }
        });
        jMenuSoundEdit.add(jMenuItemSoundSource);

        jMenuItemSimulationSetting.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemSimulationSetting.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/img/settingsIcon.png"))); // NOI18N
        jMenuItemSimulationSetting.setText("Simulation settings");
        jMenuItemSimulationSetting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSimulationSettingActionPerformed(evt);
            }
        });
        jMenuSoundEdit.add(jMenuItemSimulationSetting);

        jMenuBar.add(jMenuSoundEdit);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jToolBar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRunActionPerformed
        this.runSimulation();
    }//GEN-LAST:event_jButtonRunActionPerformed

    private void jMenuItemRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRunActionPerformed
        this.runSimulation();
    }//GEN-LAST:event_jMenuItemRunActionPerformed

    private void jMenuItemSoundSourceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSoundSourceActionPerformed
        this.soundSourceSettingsFrame.setVisible(true);
    }//GEN-LAST:event_jMenuItemSoundSourceActionPerformed

    private void jMenuItemAmbientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAmbientActionPerformed
        this.ambienteSettingsFrame.setVisible(true);
    }//GEN-LAST:event_jMenuItemAmbientActionPerformed

    private void jMenuItemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItemExitActionPerformed

    private void jMenuItemSimulationSettingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSimulationSettingActionPerformed
        simulationSettingsFrame.setVisible(true);
    }//GEN-LAST:event_jMenuItemSimulationSettingActionPerformed

    private void jButtonConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConfigActionPerformed
        simulationSettingsFrame.setVisible(true);
    }//GEN-LAST:event_jButtonConfigActionPerformed

    private void jButtonStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStopActionPerformed
        this.stopSimulation();
    }//GEN-LAST:event_jButtonStopActionPerformed

    private void jMenuItemStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemStopActionPerformed
        this.stopSimulation();
    }//GEN-LAST:event_jMenuItemStopActionPerformed

    private void jButtonResumeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResumeActionPerformed
        this.resumeSimulation();
    }//GEN-LAST:event_jButtonResumeActionPerformed

    private void jMenuItemPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPauseActionPerformed
        this.pauseSimulation();
    }//GEN-LAST:event_jMenuItemPauseActionPerformed

    private void jButtonPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPauseActionPerformed
        this.pauseSimulation();
    }//GEN-LAST:event_jButtonPauseActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        this.obstacleSettingsFrame.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButtonAddObstacleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddObstacleActionPerformed
        this.obstacleSettingsFrame.setVisible(true);
    }//GEN-LAST:event_jButtonAddObstacleActionPerformed

    private void jButtonAddSoundSourceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddSoundSourceActionPerformed
        this.soundSourceSettingsFrame.setVisible(true);
    }//GEN-LAST:event_jButtonAddSoundSourceActionPerformed

    private void jButtonRemoveObstacleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveObstacleActionPerformed
    	String id;
        id = (String)jTableObstacles.getModel().getValueAt(jTableObstacles.getSelectedRow(), 1);
    	System.out.println(id);
        
    	Obstacle.getObstacles().remove(id);
        
        ((DefaultTableModel) jTableObstacles.getModel()).removeRow(jTableObstacles.getSelectedRow());
        for(int i = 0; i < jTableObstacles.getModel().getRowCount(); i++){
            jTableObstacles.getModel().setValueAt(i+1, i, 0);
        }
        if(Obstacle.getObstacles().isEmpty()){
            jButtonRun.setEnabled(false);
        }
    }//GEN-LAST:event_jButtonRemoveObstacleActionPerformed

    private void jButtonRemoveSoundSourceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveSoundSourceActionPerformed
        String id;
        AmbientObject ambient = AmbientObject.getInstance();
        id = (String)jTableSoundSources.getModel().getValueAt(jTableSoundSources.getSelectedRow(), 1);
    	System.out.println(id);
        
        ambient.killSoundSource(id);
    	ambient.getSoundSources().remove(id);
        SoundSourceObject.getSoundSources().remove(id);
        graphicGenerator.updateSoundSources();
        
        ((DefaultTableModel) jTableSoundSources.getModel()).removeRow(jTableSoundSources.getSelectedRow());
        for(int i = 0; i < jTableSoundSources.getModel().getRowCount(); i++){
            jTableSoundSources.getModel().setValueAt(i+1, i, 0);
        }
        if(ambient.getSoundSources().isEmpty()){
            jButtonRun.setEnabled(false);
        }
    }//GEN-LAST:event_jButtonRemoveSoundSourceActionPerformed

    private void jMenuItemResumeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemResumeActionPerformed
        this.resumeSimulation();
    }//GEN-LAST:event_jMenuItemResumeActionPerformed

    private void runSimulation(){
        graphicGenerator.clearGraphic();
        graphicGenerator.updateSoundSources();
        
        simulation.setDecibel(0);
        simulation.setReverberationTime(0);
        
        this.jButtonStop.setEnabled(true);
        this.jMenuItemStop.setEnabled(true);
        HomeFrame.jButtonRun.setEnabled(false);
        HomeFrame.jMenuItemRun.setEnabled(false);
        this.jButtonPause.setEnabled(true);
        this.jMenuItemPause.setEnabled(true);
        this.jButtonResume.setEnabled(false);
        this.jMenuItemResume.setEnabled(false);
        
        UIController.getInstance().setRunning(true);
        
        UIController.getInstance().addNewEvent(Message.RUN);
    }
    
    public void stopSimulation(){
        if(this.jButtonResume.isEnabled())
            UIController.getInstance().addNewEvent(Message.STOP_PAUSED);
        else
            UIController.getInstance().addNewEvent(Message.STOP_RESUMED);
        
        UIController.getInstance().setRunning(false);
        
        this.jButtonStop.setEnabled(false);
        this.jMenuItemStop.setEnabled(false);
        this.jButtonPause.setEnabled(false);
        this.jMenuItemPause.setEnabled(false);
        this.jButtonResume.setEnabled(false);
        this.jMenuItemResume.setEnabled(false);
        HomeFrame.jButtonRun.setEnabled(true);
        HomeFrame.jMenuItemRun.setEnabled(true);
        
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        SoundObject.getSounds().clear();
        graphicGenerator.updateSounds();
        graphicGenerator.updateSoundSources();
    }
    
    private void pauseSimulation(){
        this.jButtonPause.setEnabled(false);
        this.jMenuItemPause.setEnabled(false);
        this.jButtonResume.setEnabled(true);
        this.jMenuItemResume.setEnabled(true);
        this.jButtonStop.setEnabled(true);
        
        UIController.getInstance().addNewEvent(Message.PAUSE);
    }
    
    private void resumeSimulation(){
        this.jButtonPause.setEnabled(true);
        this.jMenuItemPause.setEnabled(true);
        this.jButtonResume.setEnabled(false);
        this.jMenuItemResume.setEnabled(false);
        this.jButtonStop.setEnabled(true);
        
        UIController.getInstance().addNewEvent(Message.RESUME);
    }

    public static DefaultTableModel getObstacleModel() {
        return obstacleModel;
    }

    public static void setObstacleModel(DefaultTableModel obstacleModel) {
        HomeFrame.obstacleModel = obstacleModel;
    }

    public static DefaultTableModel getSoundSourceModel() {
        return soundSourceModel;
    }

    public static void setSoundSourceModel(DefaultTableModel soundSourceModel) {
        HomeFrame.soundSourceModel = soundSourceModel;
    }
    
    public static int getObstacleCount() {
		obstacleCount++;
    	return obstacleCount;
	}

	public static int getSoundSourceCount() {
		soundSourceCount++;
		return soundSourceCount;
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAddObstacle;
    private javax.swing.JButton jButtonAddSoundSource;
    private javax.swing.JButton jButtonConfig;
    private javax.swing.JButton jButtonPause;
    private javax.swing.JButton jButtonRemoveObstacle;
    private javax.swing.JButton jButtonRemoveSoundSource;
    private javax.swing.JButton jButtonResume;
    public static javax.swing.JButton jButtonRun;
    private javax.swing.JButton jButtonStop;
    public static javax.swing.JLabel jLabelNivel;
    private javax.swing.JLabel jLabelObstacles;
    public static javax.swing.JLabel jLabelReverberacao;
    private javax.swing.JLabel jLabelSoundSources;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItemAmbient;
    private javax.swing.JMenuItem jMenuItemExit;
    private javax.swing.JMenuItem jMenuItemPause;
    private javax.swing.JMenuItem jMenuItemResume;
    public static javax.swing.JMenuItem jMenuItemRun;
    private javax.swing.JMenuItem jMenuItemSimulationSetting;
    public static javax.swing.JMenuItem jMenuItemSoundSource;
    private javax.swing.JMenuItem jMenuItemStop;
    private javax.swing.JMenu jMenuSoundEdit;
    private javax.swing.JPanel jPanelBody;
    private javax.swing.JPanel jPanelHome;
    private javax.swing.JPanel jPanelMonitor;
    private javax.swing.JPanel jPanelObstacles;
    private javax.swing.JPanel jPanelRight;
    private javax.swing.JPanel jPanelSoundSources;
    private javax.swing.JScrollPane jScrollPaneTableObstacles;
    private javax.swing.JScrollPane jScrollPaneTableSoundSources;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JSplitPane jSplitPaneBody;
    private javax.swing.JSplitPane jSplitPaneMenu;
    public static javax.swing.JTable jTableObstacles;
    public static javax.swing.JTable jTableSoundSources;
    private javax.swing.JToolBar jToolBar;
    // End of variables declaration//GEN-END:variables
}
