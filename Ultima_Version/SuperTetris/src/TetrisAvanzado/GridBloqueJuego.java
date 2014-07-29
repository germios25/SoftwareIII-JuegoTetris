package TetrisAvanzado;

public class GridBloqueJuego extends Bloque{
  private boolean exists;
  
  public GridBloqueJuego(){
    this.exists = false;
  }
  
  public void setExists(boolean b){
    this.exists = b;
  }
  
  public boolean exists(){
    return this.exists;
  }
}
