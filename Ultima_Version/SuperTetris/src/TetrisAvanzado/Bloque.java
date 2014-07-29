package TetrisAvanzado;

import javax.swing.ImageIcon;

public class Bloque{
  private ColorValues color;
  public static final int blockSize = 16;  
  private static enum ColorValues{
    RED(0),  GREEN(1),  BLUE(2),  YELLOW(3);
    private static ImageIcon[] blockImages = { new ImageIcon("src/resources/redblock.png"), new ImageIcon("src/resources/greenblock.png"), 
    new ImageIcon("src/resources/blueblock.png"), new ImageIcon("src/resources/yellowblock.png") };
    private int image;
    private ColorValues(int i){
        this.image = i;
    }    
    int getColor(){
        return this.image;
    }    
    ImageIcon getImage(){
        return blockImages[this.image];
    }
  }
  public Bloque(){
      this.color = ColorValues.RED;
  }
  public int getColor(){
      return this.color.getColor();
  }
  public void setColor(int i){
      i = Math.abs(i % ColorValues.values().length);
      this.color = ColorValues.values()[i];
  }
  public ImageIcon getImage(){
      return this.color.getImage();
  }
}
