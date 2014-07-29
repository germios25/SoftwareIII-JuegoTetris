package TetrisAvanzado;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Juego
  implements Runnable{
  static final int loopSleep = 15;
  static final int dropSleep = 200;
  static final int clearSleep = 30;
  private VentanaDeJuego window;
  private GridJuego pGrid;
  private MenuBar mBar;
  private ManipuladorDeEntrada handler;
  private AudioPlayer player;
  private Thread thread;
  private Pieza piece;
  private Puntuacion score;
  private playLose lose;
  private int delayTime;
  private static final int[] delayTimes = { 36, 33, 30, 27, 24, 21, 18, 15, 12, 9 };
  private static final int inputDelay = 3;
  public static boolean inGame;
  
  public Juego(){
    this.window = new VentanaDeJuego();
    this.player = new AudioPlayer();
    this.player.start();    
    this.score = new Puntuacion();    
    this.pGrid = new GridJuego(this.score);
    this.window.add(this.pGrid);    
    this.piece = new Pieza(this.pGrid);
    this.pGrid.setPiece(this.piece);
    
    this.mBar = new MenuBar(this.pGrid.getPreferredSize(), this.score, this.player, this.piece);
    this.window.add("East", this.mBar);
    
    this.window.pack();
    this.window.setLocationRelativeTo(null);
    this.window.setVisible(true);
    
    inGame = true;
    
    this.thread = new Thread(this);
    this.thread.start();
    
    this.handler = new ManipuladorDeEntrada();
    this.window.addKeyListener(this.handler);
    
    this.lose = new playLose();
    
    this.delayTime = 1;
  }
  
    @Override
  public void run(){
    this.pGrid.repaint();
    this.mBar.repaint();
    while (inGame) {
      if (this.pGrid.getState() == GridJuego.State.REG){
        try{
          Thread.sleep(15);
        }catch (InterruptedException e){
          return;
        }
        this.delayTime += 1;
        if (this.handler.getDropKey()){
          while (this.piece.pieceDown()) {}
          this.handler.reset();
          this.piece.storePiece();
          try{
            if (this.pGrid.getState() == GridJuego.State.REG) {
              Thread.sleep(200L);
            }
          }
          catch (InterruptedException e){
            return;
          }
          this.piece.nextPiece();
          this.pGrid.repaint();
          this.mBar.repaint();
        }
        else{
          if ((this.delayTime % 3 == 0) && (!this.handler.getDKey())){
            if ((this.handler.getLKey()) && (!this.handler.getRKey())){
              if (!this.piece.moveLeft()) {
                this.handler.reset();
              }
              this.handler.Adjust();
            }
            if ((this.handler.getRKey()) && (!this.handler.getLKey())){
              if (!this.piece.moveRight()) {
                this.handler.reset();
              }
              this.handler.Adjust();
            }
          }
          if (this.handler.getRotVal()) {
            this.piece.rotatePiece();
          }
          if ((this.delayTime % delayTimes[this.score.getLevelSpeed()] == 0) || ((this.handler.getDKey()) && (this.delayTime % 3 < 2))) {
            if (!this.piece.pieceDown()){
              this.piece.storePiece();
              this.mBar.repaint();
              try{
                if (this.pGrid.getState() == GridJuego.State.CLEAR) {
                  Thread.sleep(30L);
                } else {
                  Thread.sleep(200L);
                }
              }
              catch (InterruptedException e){
                return;
              }
              this.piece.nextPiece();
            }
          }
          this.pGrid.repaint();
          this.mBar.repaint();
        }
      }
      else{
        if (this.handler.getPauseVal()) {
          this.pGrid.setState(GridJuego.State.PAUSE);
        }
        if (this.pGrid.getState() == GridJuego.State.PAUSE){
          this.player.stop();
          this.pGrid.repaint();
          while (this.pGrid.getState() == GridJuego.State.PAUSE) {
            if (!this.handler.getPauseVal()) {
              this.pGrid.setState(GridJuego.State.REG);
            }
          }
          this.pGrid.repaint();
          this.player.start();
        }
      }
    }
    this.lose.playSound();
    this.mBar.repaint();
    try{
      Thread.sleep(1500L);
    }
    catch (Exception localException) {}
    this.pGrid.setState(GridJuego.State.LOSE);
    this.pGrid.repaint();
    try{
      Thread.sleep(3000L);
    }
    catch (Exception localException1) {}
    this.window.dispose();
    this.player.stop();
    System.exit(0);
  }
  
  private class playLose{
    AudioInputStream stream;
    Clip clip;
    
    public playLose(){
      try{
        File f = new File("src/musica/perdido.wav");
        this.stream = AudioSystem.getAudioInputStream(f);
        this.clip = AudioSystem.getClip();
        this.clip.open(this.stream);
      }
      catch (IOException e)
      {
        File f;
        e.printStackTrace();
      }
      catch (UnsupportedAudioFileException e)
      {
        e.printStackTrace();
      }
      catch (LineUnavailableException e)
      {
        e.printStackTrace();
      }
    }
    
    public void playSound(){
      Juego.this.player.stop();
      this.clip.setFramePosition(0);
      this.clip.start();
    }
  }
}
