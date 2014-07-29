package TetrisAvanzado;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GridJuego
  extends JPanel
{
  public static final long serialVersionUID = 1L;
  public static final int width = 10;
  public static final int height = 24;
  private GridBloqueJuego[][] gridValues;
  private Pieza piece;
  private State state;
  private Puntuacion score;
  
  public static enum State{ REG,  CLEAR,  PAUSE,  LOSE; }
  
  public GridJuego(Puntuacion sc){
    setBackground(new Color(200, 210, 255));    
    setPreferredSize(new Dimension(160,384));
    this.gridValues = new GridBloqueJuego[10][24];
    for (int countX = 0; countX < this.gridValues.length; countX++) {
      for (int countY = 0; countY < this.gridValues[0].length; countY++) {
        this.gridValues[countX][countY] = new GridBloqueJuego();
      }
    }
    this.score = sc;
    this.state = State.REG;
  }
  public void setPiece(Pieza p) {this.piece = p;}
  public void setState(State x) {this.state = x;}
  public State getState()       {return this.state;}
    @Override
  public synchronized void paint(Graphics g){
    super.paintComponent(g);
    if ((this.state == State.REG) || (this.state == State.CLEAR)){
      for (int countX = 0; countX < this.gridValues.length; countX++) {
        for (int countY = 0; countY < this.gridValues[0].length; countY++) {
          if (this.gridValues[countX][countY].exists()) {
            this.gridValues[countX][countY].getImage().paintIcon(this, g, countX * 16, countY * 16);
          }
        }
      }
      if ((this.piece != null) && (this.state == State.REG)) {
        this.piece.paintPiece(this, g);
      }
    }
    else if (this.state == State.LOSE){
      g.setColor(Color.WHITE);
      g.setFont(new Font("SansSerif", 1, 20));
      g.drawString("Juego Terminado", 20, 160);
    }
    else if (this.state == State.PAUSE){
      g.setColor(Color.WHITE);
      g.setFont(new Font("SansSerif", 1, 18));
      g.drawString("PAUSE", 40, 160);
    }
  }  
  public void setBlock(int x, int y, boolean e, int c){
    this.gridValues[x][y].setExists(e);
    this.gridValues[x][y].setColor(c);
  }
  
  public synchronized void setGridValues(GridJuego pg){
    this.gridValues = pg.getGridValues();
  }
  
  public void clearRows(){
    int lines = 0;
    int s = 0;    
    int[] rows = new int[4];
    for (int c = 0; c < rows.length; c++) {
      rows[c] = -1;
    }
    for (int cY = 1; cY < 24; cY++){
      boolean filled = true;
      for (int cX = 0; cX < 10; cX++) {
        if (!this.gridValues[cX][cY].exists()){
          filled = false; break;
        }
      }
      if (filled){
        setState(State.CLEAR);
        for (int x = 0; x < rows.length; x++) {
          if (rows[x] == -1){
            rows[x] = cY;lines++; break;
          }
        }
        for (int cX = (int)Math.floor(5.0D); cX >= 0; cX--){
          this.gridValues[cX][cY].setExists(false);
          this.gridValues[(9 - cX)][cY].setExists(false);
          repaint();
          try{
            Thread.sleep(30L);
          }catch (InterruptedException e){
            return;
          }
        }
      }
    }
    if (lines > 0){
      s = (int)Math.pow(lines, lines) * 100;
      this.score.addScore(s);
      this.score.addLines(lines);
    }
    else{
      s = 10;
      this.score.addScore(s);
      repaint();
      return;
    }
    for (int c = 0; c < lines; c++){
      int start = rows[c];
      for (int countY = start; countY > 0; countY--) {
        for (int countX = 0; countX < 10; countX++)
        {
          this.gridValues[countX][countY].setExists(this.gridValues[countX][(countY - 1)].exists());
          this.gridValues[countX][countY].setColor(this.gridValues[countX][(countY - 1)].getColor());
        }
      }
    }
  }
  public GridBloqueJuego[][] getGridValues(){
    return this.gridValues;
  }
}
