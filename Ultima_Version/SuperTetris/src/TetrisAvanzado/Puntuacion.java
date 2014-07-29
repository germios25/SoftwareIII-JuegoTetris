package TetrisAvanzado;
public class Puntuacion{
  private int level,lines;
  private long score;  
  public Puntuacion(){
    this.level = 0;
    this.lines = 0;
    this.score = 0;
  }
  public int getLines() {return this.lines;}
  public long getScore(){return this.score;}
  public int getLevel() {return this.level;}
  public int getLevelSpeed(){
      if (this.level > 9){return 9;} 
      return this.level;
  }
  public void addScore(int x){ this.score += x;}
  public void addLines(int x){
    this.lines += x;
    this.level = ((int)Math.floor(this.lines / 10));
    if (this.level > 99){
        this.level = 99;
    }
  }
}
