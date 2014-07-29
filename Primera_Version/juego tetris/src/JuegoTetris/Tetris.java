package JuegoTetris;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Manuel 
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Tetris {
    
    public int[][] JuegoTablero = new int[10][20];
    public JuegoT fSprite;
    public JuegoT SeguienteSprite = null;
    private boolean isSprite = false;
    public Point spritePos = new Point(3,0);
    public int flinea = 0 ;
    
// La posicion para Limpiar
    public void Limpiar() {
        flinea = 0;
        for (int i = 0 ; i < 10 ; i++)
            for (int j = 0 ; j < 20 ; j++)
                JuegoTablero[i][j] = 0;
        
    }
    
    //  La posición que si dirige a la derecha 
    
    public void Derecha() {
        if (canSpriteBeHere(fSprite, new Point(spritePos.x + 1 , spritePos.y)))
            spritePos.x = spritePos.x + 1 ;
        
    }

    public boolean canSpriteBeHere(JuegoT sprite , Point pos) {
        for (int i = 0 ; i <= 3 ; i++)
            for(int j = 0 ; j <= 3 ; j++) {
            if (sprite.fdata[i][j]!=0) {
                int ii = i + pos.x;
                int jj = j + pos.y;
                if (ii<0) return false;
                if (ii>= JuegoTablero.length) return false;
                if (jj >= JuegoTablero[0].length) return false;
                if (JuegoTablero[ii][jj] != 0) return false;
            }
            
            }
        return true;
    }
    
    public JuegoT getCurrentSprite() {
        if (isSprite) return fSprite;
        isSprite = true;
        if (SeguienteSprite == null )
            fSprite = new JuegoT((new Random()).nextInt(100)+1); else
                fSprite = SeguienteSprite;
        SeguienteSprite = new JuegoT((new Random()).nextInt(100)+1);
        
        spritePos.x = 3;
        spritePos.y = 0 ;
        
        
        if (!canSpriteBeHere(fSprite,spritePos)) Limpiar();
        return fSprite;
    }
    
   
    
    private void fixCurrentSprite() {
        if (isSprite) {
            
            for (int i = 0 ; i <= 3 ; i++)
                for (int j= 0 ; j <= 3 ; j++)
                    if (fSprite.fdata[i][j] != 0 )
                        JuegoTablero[i + spritePos.x][j+spritePos.y] = fSprite.color.getRGB();
            isSprite = false;
        }
        
        int k = 0;
        for (int j = 0 ; j < JuegoTablero[0].length; j++) {
            k=0;
            for (int i = 0 ; i < JuegoTablero.length; i++) if (JuegoTablero[i][j] != 0) k++;
            if (k == JuegoTablero.length) {
                flinea= flinea +1;
                for (int jj= j ; jj > 0 ; jj--)
                    for (int ii = 0 ; ii < JuegoTablero.length; ii++ )
                        
                        JuegoTablero[ii][jj] = JuegoTablero[ii][jj-1];
                for (int ii = 0 ; ii < JuegoTablero.length; ii++) JuegoTablero[ii][0] = 0 ;
                
                
            }
        }
        
    }
    public void stepGame(){
        Point p = new Point(spritePos.x,spritePos.y+1);
        if (canSpriteBeHere(getCurrentSprite(),p)) spritePos = p; else
            fixCurrentSprite();
    }
    public Tetris() {
        isSprite = false;
        
    }

 //  La posición que si dirige a la Izquierda
    
    public void Izquierda() {
        if (canSpriteBeHere(fSprite, new Point(spritePos.x - 1 , spritePos.y)))
            spritePos.x = spritePos.x - 1 ;
        
    }
// La Posicion del tablero
    
    public int[][] getTablero(){
        int[][] result = new int[10][20];
        for (int i = 0 ; i < JuegoTablero.length ; i++)
            for (int j = 0 ; j < JuegoTablero[i].length ; j++)
                result[i][j] = JuegoTablero[i][j];
        byte[][] spr = getCurrentSprite().fdata;
        for (int i = 0 ; i < spr.length ; i++)
            for (int j = 0 ; j < spr[i].length ; j++)
                if (spr[i][j] != 0 )
                    result[i+spritePos.x][j+spritePos.y] = getCurrentSprite().color.getRGB();
        
        
        
        return result;
        
    }
    
    public void rotate() {
        fSprite.GiraPieza();
        if (!canSpriteBeHere(fSprite,spritePos)) {
            fSprite.GiraPieza();
            fSprite.GiraPieza();
            fSprite.GiraPieza();
        }
        
    }
    
    public Image getGameImage(int isz) {
        BufferedImage bi = new BufferedImage(2+10 *isz,2+20 * isz,BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.getGraphics();
        g.setColor(Color.BLUE);
        g.fillRect(0,0,bi.getWidth(),bi.getHeight());
        int[][] brd = getTablero();
        for (int i = 0 ; i < brd.length;i++)
            for (int j = 0 ; j < brd[i].length;j++)  {
            Color c = new Color(brd[i][j]);
            g.setColor(c);
            g.fill3DRect(1+i*isz,1+j*isz,isz,isz ,true);
            }
        for (int i = 0 ; i < SeguienteSprite.fdata.length;i++)
            for (int j = 0 ; j < SeguienteSprite.fdata[i].length;j++)  {
            if (SeguienteSprite.fdata[i][j]!=0) {
                Color c = SeguienteSprite.color;
                g.setColor(c.darker());
                g.fillRect(5+i*4,5+j*4,4,4);
            }
            }
        
        
        
        return bi;
        
    }
    
    public void dropSprite() {
        JuegoT s = getCurrentSprite();
        while (s == getCurrentSprite()) stepGame();
        
    }
 }