package TetrisAvanzado;

public class PiezaBloque extends Bloque{
    private int x;
    private int y;

    public void setCoords(int x, int y){
        this.x = x;
        this.y = y;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
}
