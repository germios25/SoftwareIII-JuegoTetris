/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TetrisBasico;

import Tetris.Informacion;
import Tetris.MenuPrincipal;
import java.awt.BorderLayout;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequencer;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class APPTetris extends javax.swing.JFrame {
    private Timer tm = new Timer();
    private Sequencer sequencer;
    public Tetris tetris = new Tetris();
    public MenuPrincipal m=new MenuPrincipal();
       
    /**
     * Creates new form APPTetris
     */
    public APPTetris() {
        this.inicializar();
        initComponents(); 
        setLocationRelativeTo(null);  
        toFront();
        
            }
    public void inicializar(){
    TimerTask task = new TimerTask() {
                        public void run() {
                            int nbl = tetris.fline;
                            APPTetris.this.tetris.stepGame();
                            APPTetris.this.paintGame();
                            if (nbl<  tetris.fline)
                                playsound(APPTetris.SoundGame.SOUND_LINE);
                            if (nbl>tetris.fline)
                                playsound(APPTetris.SoundGame.SOUND_LOSE);
                        }
                    };
                    tm.scheduleAtFixedRate(task,1000,1000);
                    try {
                        sequencer = MidiSystem.getSequencer();                        
                        URL u = getClass().getResource("/musica/tetris.mid");
                        sequencer.setSequence(MidiSystem.getSequence(u));
                                                sequencer.open();
                        sequencer.setLoopCount(Integer.MAX_VALUE);
                        sequencer.start();
                        } catch (Exception e ) {e.printStackTrace();}
    
}
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        t2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        t1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        t2.setBackground(new java.awt.Color(153, 153, 255));
        t2.setForeground(new java.awt.Color(0, 51, 204));
        t2.setDisabledTextColor(new java.awt.Color(102, 0, 0));
        t2.setEnabled(false);

        jLabel2.setForeground(new java.awt.Color(204, 0, 51));
        jLabel2.setText("Puntaje: ");

        jLabel1.setForeground(new java.awt.Color(204, 0, 51));
        jLabel1.setText("lineas:");

        t1.setBackground(new java.awt.Color(102, 153, 255));
        t1.setForeground(new java.awt.Color(0, 51, 204));
        t1.setCaretColor(new java.awt.Color(153, 0, 0));
        t1.setDisabledTextColor(new java.awt.Color(102, 0, 0));
        t1.setEnabled(false);

        jButton1.setBackground(new java.awt.Color(102, 255, 102));
        jButton1.setText("Ayuda");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton1KeyPressed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 255, 255));
        jButton2.setForeground(new java.awt.Color(255, 0, 0));
        jButton2.setText("Salir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Limpiar el juego");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(260, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(t2)
                        .addComponent(t1)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(3, 3, 3)
                .addComponent(t1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(t2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jButton2)
                .addGap(45, 45, 45)
                .addComponent(jButton3)
                .addContainerGap(201, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
        // TODO add your handling code here:
        int nbl = tetris.fline;
        if (evt.getKeyCode() == 37){
            tetris.left();
        }
        if (evt.getKeyCode() == 38) {
            tetris.rotate();
            //        playsound(SonidoDibujo.SOUND_TURN);
        }
        if (evt.getKeyCode() == 39){
            tetris.right();
            //        playsound(SonidoDibujo.SOUND_MOVE);
        }
        if (evt.getKeyCode() == 40){
            tetris.stepGame();
            //        playsound(SonidoDibujo.SOUND_MOVE);
        }
        if (evt.getKeyCode() == 32) { tetris.dropSprite();
            //        playsound(SonidoDibujo.SOUND_DROP);
        }
        paintGame();

        //        if (nbl<  tetris.flinea)  playsound(SonidoDibujo.Sonido_Linea);
        //        if (nbl>tetris.flinea)  playsound(SonidoDibujo.SOUND_LOSE);
    }//GEN-LAST:event_jButton1KeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Informacion n=new Informacion(this, rootPaneCheckingEnabled);
        n.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        
        MenuPrincipal n=new MenuPrincipal();
        n.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            MenuPrincipal p=new MenuPrincipal();
            p.setVisible(true);
            this.dispose();
            sequencer.stop();
        } catch (Exception e) {
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed
    public  static int A,B;
    
        
    public enum SoundGame  {
        
    SOUND_DROP,
    SOUND_TURN,            
    SOUND_MOVE,            
    SOUND_LINE ,
    SOUND_LOSE 
    };
    private void paintGame() {
        int sz = getWidth() / 10;
        int sz2 = getHeight() / 20;
        if (sz > sz2) {
            sz = sz2;
        }
        getGraphics().drawImage(tetris.getGameImage(sz), 5, 30, 280, 490, null);
    }
    
     private void playsound(APPTetris.SoundGame isound) {
        try{
            
            AudioInputStream stream = null;
            switch  (isound) {
                case SOUND_DROP : stream = AudioSystem.getAudioInputStream(getClass().getResource("/musica/drop.wav"));
                break;
                case SOUND_TURN :stream = AudioSystem.getAudioInputStream(getClass().getResource("/musica/turn.wav"));
                break;
                case SOUND_MOVE :stream = AudioSystem.getAudioInputStream(getClass().getResource("/musica/move.wav"));
                break;
                case  SOUND_LINE : stream = AudioSystem.getAudioInputStream(getClass().getResource("/musica/line.wav"));
                break;
                case  SOUND_LOSE : stream = AudioSystem.getAudioInputStream(getClass().getResource("/musica/lose.wav"));
                
            }
            if (stream != null) {
                AudioFormat format = stream.getFormat();
                DataLine.Info info = new DataLine.Info(Clip.class, stream.getFormat());
                Clip clip = (Clip) AudioSystem.getLine(info);
                clip.open(stream);
                clip.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(APPTetris.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(APPTetris.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(APPTetris.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(APPTetris.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        int bst = -1 ;
        APPTetris f=new APPTetris();
        f.setLayout(new BorderLayout());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

        f.requestFocus();
        while (f.isVisible()) {
            try {
                Thread.sleep(400);
                
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            A= f.tetris.fline;
            
            t1.setText("  "+ A +" Lineas");
    
            if (bst < f.tetris.fline) {
                bst = f.tetris.fline;
                B= bst*10;
            t2.setText("  "+ B );
            }
        }
        
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new APPTetris().setVisible(true);                          
            }
        });
             
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private static javax.swing.JTextField t1;
    private static javax.swing.JTextField t2;
    // End of variables declaration//GEN-END:variables
}
