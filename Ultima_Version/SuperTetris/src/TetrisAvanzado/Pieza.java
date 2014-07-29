package TetrisAvanzado;

import java.awt.Component;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Pieza
{
  private GridJuego grid;
  private PiezaBloque[] next_piece = new PiezaBloque[4];
  private int next_type;
  private PiezaBloque[] piece = new PiezaBloque[4];
  private int type;
  private PieceSound pieceSound;
  private int xPos;
  private int yPos;
  private static final int L1_SHAPE = 0;
  private static final int L2_SHAPE = 1;
  private static final int T_SHAPE = 2;
  private static final int Z1_SHAPE = 3;
  private static final int Z2_SHAPE = 4;
  private static final int ROD = 5;
  private static final int SQUARE = 6;
  private static final int pieceAmount = 7;
  
  public Pieza(GridJuego g)
  {
    this.grid = g;
    for (int temp = 0; temp < this.piece.length; temp++)
    {
      this.next_piece[temp] = new PiezaBloque();
      this.piece[temp] = new PiezaBloque();
    }
    Random p = new Random();
    genPiece(p.nextInt());
    nextPiece();
    
    this.pieceSound = new PieceSound();
  }
  
  public void nextPiece()
  {
    this.grid.setState(GridJuego.State.REG);
    
    this.xPos = 3;
    this.yPos = 0;
    for (int count = 0; count < this.piece.length; count++)
    {
      this.piece[count].setCoords(this.next_piece[count].getX(), this.next_piece[count].getY());
      this.piece[count].setColor(this.next_piece[0].getColor());
    }
    this.type = this.next_type;
    Random p = new Random();
    genPiece(p.nextInt());
  }
  
  public GridJuego getPlayingGrid()
  {
    return this.grid;
  }
  
  public boolean pieceDown()
  {
    for (int temp = 0; temp < 4; temp++) {
      if ((this.piece[temp].getY() + this.yPos + 1 == 24) || 
        (this.grid.getGridValues()[(this.piece[temp].getX() + this.xPos)][(this.piece[temp].getY() + this.yPos + 1)].exists())) {
        return false;
      }
    }
    this.yPos += 1;
    return true;
  }
  
  public void storePiece()
  {
    for (int count = 0; count < 4; count++)
    {
      int x = this.piece[count].getX() + this.xPos;
      int y = this.piece[count].getY() + this.yPos;
      this.grid.setBlock(x, y, true, this.piece[count].getColor());
    }
    if (this.yPos == 0)
    {
      Juego.inGame = false;
      return;
    }
    this.pieceSound.playThud();
    this.grid.clearRows();
  }
  
  private void genPiece(int p)
  {
    p = Math.abs(p % 7);
    this.next_type = p;
    switch (p)
    {
    case 0: 
      this.next_piece[0].setCoords(0, 1);
      this.next_piece[1].setCoords(0, 2);
      this.next_piece[2].setCoords(1, 2);
      this.next_piece[3].setCoords(2, 2);
      break;
    case 1: 
      this.next_piece[0].setCoords(1, 0);
      this.next_piece[1].setCoords(1, 1);
      this.next_piece[2].setCoords(1, 2);
      this.next_piece[3].setCoords(2, 2);
      break;
    case 2: 
      this.next_piece[0].setCoords(1, 1);
      this.next_piece[1].setCoords(0, 2);
      this.next_piece[2].setCoords(1, 2);
      this.next_piece[3].setCoords(2, 2);
      break;
    case 3: 
      this.next_piece[0].setCoords(0, 1);
      this.next_piece[1].setCoords(1, 1);
      this.next_piece[2].setCoords(1, 2);
      this.next_piece[3].setCoords(2, 2);
      break;
    case 4: 
      this.next_piece[0].setCoords(0, 0);
      this.next_piece[1].setCoords(0, 1);
      this.next_piece[2].setCoords(1, 1);
      this.next_piece[3].setCoords(1, 2);
      break;
    case 5: 
      this.next_piece[0].setCoords(0, 1);
      this.next_piece[1].setCoords(1, 1);
      this.next_piece[2].setCoords(2, 1);
      this.next_piece[3].setCoords(3, 1);
      break;
    default: 
      this.next_piece[0].setCoords(0, 0);
      this.next_piece[1].setCoords(0, 1);
      this.next_piece[2].setCoords(1, 0);
      this.next_piece[3].setCoords(1, 1);
    }
    Random color = new Random();
    this.next_piece[0].setColor(color.nextInt());
    for (int count = 1; count < 4; count++) {
      this.next_piece[count].setColor(this.next_piece[(count - 1)].getColor());
    }
  }
  
  public boolean moveLeft()
  {
    for (int count = 0; count < this.piece.length; count++)
    {
      int x = this.piece[count].getX() + this.xPos - 1;
      int y = this.piece[count].getY() + this.yPos;
      if ((x < 0) || (x >= 10)) {
        return false;
      }
      if (this.grid.getGridValues()[x][y].exists()) {
        return false;
      }
    }
    this.xPos -= 1;
    return true;
  }
  
  public boolean moveRight()
  {
    for (int count = 0; count < 4; count++)
    {
      int x = this.piece[count].getX() + this.xPos + 1;
      int y = this.piece[count].getY() + this.yPos;
      if ((x < 0) || (x >= 10)) {
        return false;
      }
      if (this.grid.getGridValues()[x][y].exists()) {
        return false;
      }
    }
    this.xPos += 1;
    return true;
  }
  
  public boolean rotatePiece()
  {
    PiezaBloque[] tempPiece = new PiezaBloque[this.piece.length];
    for (int count = 0; count < tempPiece.length; count++)
    {
      tempPiece[count] = new PiezaBloque();
      if (this.type < 5) {
        tempPiece[count].setCoords(2 - this.piece[count].getY(), this.piece[count].getX());
      } else if (this.type == 5) {
        tempPiece[count].setCoords(this.piece[count].getY(), this.piece[count].getX());
      } else {
        tempPiece[count].setCoords(this.piece[count].getX(), this.piece[count].getY());
      }
    }
    for (int count = 0; count < tempPiece.length; count++)
    {
      int x = tempPiece[count].getX() + this.xPos;
      int y = tempPiece[count].getY() + this.yPos;
      if ((x < 0) || (x >= 10)) {
        return false;
      }
      if ((y < 0) || (y >= 24)) {
        return false;
      }
      if (this.grid.getGridValues()[x][y].exists()) {
        return false;
      }
    }
    for (int count = 0; count < tempPiece.length; count++) {
      this.piece[count].setCoords(tempPiece[count].getX(), tempPiece[count].getY());
    }
    this.pieceSound.playRot();
    return true;
  }
  
  public PiezaBloque[] getNextPiece(){
    return this.next_piece;
  }
  
  public void paintPiece(Component c, Graphics g)
  {
    for (int count = 0; count < 4; count++) {
      this.piece[count].getImage().paintIcon(c, g, (this.xPos + this.piece[count].getX()) * 16, (this.yPos + this.piece[count].getY()) * 16);
    }
  }
  
  private class PieceSound
  {
    AudioInputStream stream;
    AudioInputStream stream2;
    Clip clip;
    Clip clip2;
    
    public PieceSound()
    {
      try
      {
        File f = new File("src/musica/rotar.wav");
        File f2 = new File("src/musica/ruido.wav");
        this.stream = AudioSystem.getAudioInputStream(f);
        this.stream2 = AudioSystem.getAudioInputStream(f2);
        this.clip = AudioSystem.getClip();
        this.clip.open(this.stream);
        this.clip2 = AudioSystem.getClip();
        this.clip2.open(this.stream2);
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
    
    public void playRot()
    {
      this.clip.setFramePosition(0);
      this.clip.start();
    }
    
    public void playThud()
    {
      this.clip2.setFramePosition(0);
      this.clip2.start();
    }
  }
}
