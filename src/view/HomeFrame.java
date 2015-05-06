/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import java.io.PrintStream;
import javax.swing.table.DefaultTableModel;

import languagesAndMessages.Message;
import settings.ProjectSettings;
import simulator.agents.Ambient;

/**
 *
 * @author Gabriel
 */
public class HomeFrame extends javax.swing.JFrame {
	
    private static HomeFrame homeFrame= null;
    private static final long serialVersionUID = 1L;
	
    private final AmbientSettingsFrame ambienteSettingsFrame = new AmbientSettingsFrame();
    private final SoundSourceSettingsFrame soundSourceSettingsFrame = new SoundSourceSettingsFrame();
    private final SimulationSettingsFrame simulationSettingsFrame = new SimulationSettingsFrame();
    private final ObstaclesSettingsFrame obstacleSettingsFrame = new ObstaclesSettingsFrame();
    
    private static DefaultTableModel obstacleModel = new DefaultTableModel(null, new String [] {"Nº", "ID", "Initial Point", "End Point", "Absorption Rate"});
    private static DefaultTableModel soundSourceModel = new DefaultTableModel(null, new String [] {"Nº", "ID", "Power", "Opening", "Location", "Direction"});
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
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaLog = new javax.swing.JTextArea();
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
        setTitle(ProjectSettings.getProjectName());
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

        jSplitPaneBody.setDividerLocation(350);

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
                    .addComponent(jScrollPaneTableObstacles, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanelObstaclesLayout.createSequentialGroup()
                        .addGroup(jPanelObstaclesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelObstacles)
                            .addGroup(jPanelObstaclesLayout.createSequentialGroup()
                                .addComponent(jButtonAddObstacle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonRemoveObstacle)))
                        .addGap(0, 193, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelObstaclesLayout.setVerticalGroup(
            jPanelObstaclesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelObstaclesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelObstacles)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneTableObstacles, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
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
                        .addGap(0, 193, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelSoundSourcesLayout.setVerticalGroup(
            jPanelSoundSourcesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSoundSourcesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelSoundSources)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneTableSoundSources, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelSoundSourcesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAddSoundSource)
                    .addComponent(jButtonRemoveSoundSource))
                .addContainerGap())
        );

        jSplitPaneMenu.setRightComponent(jPanelSoundSources);

        jSplitPaneBody.setLeftComponent(jSplitPaneMenu);

        jTextAreaLog.setEditable(false);
        jTextAreaLog.setColumns(20);
        jTextAreaLog.setRows(5);
        jScrollPane1.setViewportView(jTextAreaLog);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 292, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jSplitPaneBody.setRightComponent(jPanel1);

        javax.swing.GroupLayout jPanelBodyLayout = new javax.swing.GroupLayout(jPanelBody);
        jPanelBody.setLayout(jPanelBodyLayout);
        jPanelBodyLayout.setHorizontalGroup(
            jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPaneBody)
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

        jMenuFile.setText("File");

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
        jMenuFile.add(jMenuItemResume);

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

        jMenuItemAmbient.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemAmbient.setText("Define ambient");
        jMenuItemAmbient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAmbientActionPerformed(evt);
            }
        });
        jMenuSoundEdit.add(jMenuItemAmbient);

        jMenuItem1.setText("Add Obstacles");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenuSoundEdit.add(jMenuItem1);

        jMenuItemSoundSource.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemSoundSource.setText("Configure sound source");
        jMenuItemSoundSource.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSoundSourceActionPerformed(evt);
            }
        });
        jMenuSoundEdit.add(jMenuItemSoundSource);

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
        
    	Ambient.getObstacles().remove(id);
        
        ((DefaultTableModel) jTableObstacles.getModel()).removeRow(jTableObstacles.getSelectedRow());
        for(int i = 0; i < jTableObstacles.getModel().getRowCount(); i++){
            jTableObstacles.getModel().setValueAt(i+1, i, 0);
        }
        if(Ambient.getObstacles().isEmpty()){
            jButtonRun.setEnabled(false);
        }
    }//GEN-LAST:event_jButtonRemoveObstacleActionPerformed

    private void jButtonRemoveSoundSourceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveSoundSourceActionPerformed
        String id;
        id = (String)jTableSoundSources.getModel().getValueAt(jTableSoundSources.getSelectedRow(), 1);
    	System.out.println(id);
        
        Ambient.killSoundSource(id);
    	Ambient.getSoundSources().remove(id);
        
        ((DefaultTableModel) jTableSoundSources.getModel()).removeRow(jTableSoundSources.getSelectedRow());
        for(int i = 0; i < jTableSoundSources.getModel().getRowCount(); i++){
            jTableSoundSources.getModel().setValueAt(i+1, i, 0);
        }
        if(Ambient.getSoundSources().isEmpty()){
            jButtonRun.setEnabled(false);
        }
    }//GEN-LAST:event_jButtonRemoveSoundSourceActionPerformed

    private void runSimulation(){
        this.jButtonStop.setEnabled(true);
        this.jMenuItemStop.setEnabled(true);
        HomeFrame.jButtonRun.setEnabled(false);
        HomeFrame.jMenuItemRun.setEnabled(false);
        this.jButtonPause.setEnabled(true);
        this.jMenuItemPause.setEnabled(true);
        this.jButtonResume.setEnabled(false);
        this.jMenuItemResume.setEnabled(false);
        
        UIController.getInstance().addNewEvent(Message.RUN);
    }
    
    public void stopSimulation(){
        if(this.jButtonResume.isEnabled())
            UIController.getInstance().addNewEvent(Message.STOP_PAUSED);
        else
            UIController.getInstance().addNewEvent(Message.STOP_RESUMED);
        
        this.jButtonStop.setEnabled(false);
        this.jMenuItemStop.setEnabled(false);
        this.jButtonPause.setEnabled(false);
        this.jMenuItemPause.setEnabled(false);
        this.jButtonResume.setEnabled(false);
        this.jMenuItemResume.setEnabled(false);
        HomeFrame.jButtonRun.setEnabled(true);
        HomeFrame.jMenuItemRun.setEnabled(true);
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
    private javax.swing.JLabel jLabelObstacles;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelBody;
    private javax.swing.JPanel jPanelHome;
    private javax.swing.JPanel jPanelObstacles;
    private javax.swing.JPanel jPanelSoundSources;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPaneTableObstacles;
    private javax.swing.JScrollPane jScrollPaneTableSoundSources;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JSplitPane jSplitPaneBody;
    private javax.swing.JSplitPane jSplitPaneMenu;
    public static javax.swing.JTable jTableObstacles;
    public static javax.swing.JTable jTableSoundSources;
    private javax.swing.JTextArea jTextAreaLog;
    private javax.swing.JToolBar jToolBar;
    // End of variables declaration//GEN-END:variables
}
