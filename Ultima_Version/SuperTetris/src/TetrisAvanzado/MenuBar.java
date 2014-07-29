package TetrisAvanzado;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuBar
  extends JPanel
{
  public static final long serialVersionUID = 2L;
  private int width;
  private int height;
  private ImageIcon gridBlock = new ImageIcon("src/resources/gridblock.png");
  private static final int gridBlockSize = 16;
  private Puntuacion score;
  private Pieza piece;
  private AudioPlayer audio;
  
  public MenuBar(Dimension d, Puntuacion sc, AudioPlayer au, Pieza p)
  {
    setBackground(Color.WHITE);
    setPreferredSize(d);
    
    this.width = d.getSize().width;
    this.height = d.getSize().height;
    
    setLayout(new BorderLayout());
    this.score = sc;
    this.audio = au;
    this.piece = p;
  }
  
  public synchronized void paint(Graphics g)
  {
    super.paintComponent(g);
    for (int countX = 0; countX < this.width / 16; countX++) {
      for (int countY = 0; countY < this.height / 16; countY++) {
        this.gridBlock.paintIcon(this, g, countX * 16, countY * 16);
      }
    }
    g.setColor(Color.WHITE);
    g.fill3DRect(16, 48, 128, 96, false);
    g.fill3DRect(32, 240, 96, 80, false);
    
    g.setFont(new Font("SansSerif", 1, 16));
    g.drawString("Puntos: ", 18, 70);g.drawString(String.format("%d", new Object[] { Long.valueOf(this.score.getScore()) }), 84, 70);
    g.drawString("Lineas : ", 18, 92);g.drawString(String.format("%d", new Object[] { Integer.valueOf(this.score.getLines()) }), 84, 92);
    g.drawString("Nivel: ", 18, 130);g.drawString(String.format("%d", new Object[] { Integer.valueOf(this.score.getLevel()) }), 64, 130);
    

    
    for (int count = 0; count < this.piece.getNextPiece().length; count++) {
      this.piece.getNextPiece()[count].getImage().paintIcon(this, g, this.piece.getNextPiece()[count].getX() * 16 + 48, 
        this.piece.getNextPiece()[count].getY() * 16 + 256);
    }
  }
}
