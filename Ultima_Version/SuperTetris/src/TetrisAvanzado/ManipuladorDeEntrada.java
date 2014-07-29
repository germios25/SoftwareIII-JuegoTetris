package TetrisAvanzado;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ManipuladorDeEntrada
  implements KeyListener
{
  private boolean lKey;
  private boolean rKey;
  private boolean dropKey;
  private boolean pauseKey;
  private boolean dKey;
  private boolean uKey;
  private boolean sKey;
  private boolean lUp;
  private boolean rUp;
  private boolean rotVal;
  private boolean pauseVal;
  private boolean pause;
  
  public ManipuladorDeEntrada(){
    reset();
  }
  
  public void keyTyped(KeyEvent e) {}
  
  public synchronized void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == 37){
      this.lKey = true;
      this.rKey = false;
    }
    if (e.getKeyCode() == 39){
      this.rKey = true;
      this.lKey = false;
    }
    if (e.getKeyCode() == 40) {
      this.dKey = true;
    }
    if (e.getKeyCode() == 65) {
      this.dropKey = true;
    }
    if (e.getKeyCode() == 38) {
      this.uKey = true;
    }
    if (e.getKeyCode() == 16) {
      this.sKey = true;
    }
    if ((e.getKeyCode() == 80) && (!this.pauseKey))
    {
      this.pauseKey = true;
      this.pauseVal = (!this.pauseVal);
    }
  }
  
  public synchronized void keyReleased(KeyEvent e)
  {
    if (e.getKeyCode() == 37) {
      this.lUp = true;
    }
    if (e.getKeyCode() == 39) {
      this.rUp = true;
    }
    if (e.getKeyCode() == 40) {
      this.dKey = false;
    }
    if (e.getKeyCode() == 38) {
      this.uKey = false;
    }
    if (e.getKeyCode() == 16) {
      this.sKey = false;
    }
    if ((this.rotVal) && (!this.sKey) && (!this.uKey)) {
      this.rotVal = false;
    }
    if (e.getKeyCode() == 80) {
      this.pauseKey = false;
    }
  }
  
  public void Adjust()
  {
    if (this.lUp) {
      this.lKey = false;
    }
    if (this.rUp) {
      this.rKey = false;
    }
  }
  
  public void reset()
  {
    this.pauseKey = false;
    this.pauseVal = false;
    this.dropKey = false;
    this.rotVal = false;
    this.uKey = false;
    this.sKey = false;
    this.lKey = false;
    this.rKey = false;
    this.dKey = false;
    Adjust();
  }
  
  public boolean getLKey()
  {
    return this.lKey;
  }
  
  public boolean getRKey()
  {
    return this.rKey;
  }
  
  public boolean getDKey()
  {
    return this.dKey;
  }
  
  public boolean getDropKey()
  {
    return this.dropKey;
  }
  
  public boolean getRotVal()
  {
    if ((!this.rotVal) && ((this.sKey) || (this.uKey)))
    {
      this.rotVal = true;
      return true;
    }
    return false;
  }
  
  public boolean getPauseVal()
  {
    return this.pauseVal;
  }
}
