package TetrisAvanzado;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class VentanaDeJuego extends JFrame{
  public static final long serialVersionUID = 0L;  
  public VentanaDeJuego(){
    super("Tetris - Nivel Avanzada");
    setDefaultCloseOperation(3);
    setResizable(false);
    setLayout(new BorderLayout());
  }
}
