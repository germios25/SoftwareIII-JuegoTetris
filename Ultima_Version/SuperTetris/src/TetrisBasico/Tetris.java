package TetrisBasico;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Tetris {
    public class TSprite {
        byte[][] fdata ;
        byte[][] pI = {{0, 1, 0, 0}, {0, 1, 0, 0}, {0, 1, 0, 0}, {0, 1, 0, 0}};
        byte[][] pS = {{0, 0, 0, 0}, {0, 1,1, 0}, {1, 1, 0, 0}, {0, 0, 0, 0}};
        byte[][] pZ = {{0, 0, 0, 0},  {1, 1, 0, 0}, {0, 1,1, 0},{0, 0, 0, 0}};
        byte[][] pP = {{0, 0, 0, 0},  {0, 1, 0, 0}, {1, 1,1, 0},{0, 0, 0, 0}};
        byte[][] pL = {{0, 0, 0, 0},  {0, 0, 1, 0}, {1, 1,1, 0},{0, 0, 0, 0}};
        byte[][] pJ = {{0, 0, 0, 0},  {1, 1, 1, 0}, {0, 0,1, 0},{0, 0, 0, 0}};
        byte[][] pC = {{0, 0, 0, 0},  {0, 1, 1, 0}, {0, 1,1, 0},{0, 0, 0, 0}};
        public Color color;
        public void rotate() {
            byte[][] ftmpdata = new byte[4][4];
            for (int i =0 ; i< 4 ; i++) {
                for (int j =0 ;j< 4 ; j++) {
                    ftmpdata[i][j]=fdata[i][j];
                }
            }
            
            for (int i =0 ; i< 4 ; i++) {
                for (int j =0 ;j< 4 ; j++) {
                    fdata[3-j][i] = ftmpdata[i][j];
                }
            }
        }
        public TSprite(int piece) {
            int i =piece % 7 ;
            switch (i) {
                case 0 :               
                   fdata=pI;
                    color = Color.RED;
                    break;
                case 1 :
                    fdata=pS;
                    color = Color.BLUE;
                    break;
                case 2 :
                    fdata=pZ;
                    color = Color.GREEN.brighter();
                    break;
                case 3 :
                    fdata=pP;
                    color = Color.PINK;
                    break;
                case 4 :
                    fdata=pL;
                    color = Color.YELLOW;
                    break;
                case 5 :
                    fdata=pJ;
                    color= Color.ORANGE;
                    break;
                case 6 :
                    fdata=pC;
                    color = Color.MAGENTA;
            }
        }
    }
    public void clearBoard() {
        fline = 0;
        for (int i = 0 ; i < 10 ; i++) {
            for (int j = 0 ; j < 20 ; j++) {
                gameBoard[i][j] = 0;
            }
        }
        
    }
    public boolean canSpriteBeHere(TSprite sprite , Point pos) {
        for (int i = 0 ; i <= 3 ; i++) {
            for(int j = 0 ; j <= 3 ; j++) {
            if (sprite.fdata[i][j]!=0) {
                int ii = i + pos.x;
                int jj = j + pos.y;
                if (ii<0) {
                    return false;
                }
                if (ii>= gameBoard.length) {
                    return false;
                }
                if (jj >= gameBoard[0].length) {
                    return false;
                }
                if (gameBoard[ii][jj] != 0) {
                    return false;
                }
            }
            
            }
        }
        return true;
    }
    
    public int[][] gameBoard = new int[10][20];
    public TSprite fSprite;
    public TSprite nextSprite = null;
    private boolean isSprite = false;
    public Point spritePos = new Point(3,0);
    
    public TSprite getCurrentSprite() {
        if (isSprite) {
            return fSprite;
        }
        isSprite = true;
        if (nextSprite == null ) {
            fSprite = new TSprite((new Random()).nextInt(100)+1);
        } else {
            fSprite = nextSprite;
        }
        nextSprite = new TSprite((new Random()).nextInt(100)+1);
        
        spritePos.x = 3;
        spritePos.y = 0 ;
        
        
        if (!canSpriteBeHere(fSprite,spritePos)) {
            clearBoard();
        }
        return fSprite;
    }
    
    public int fline = 0 ;
    
    private void fixCurrentSprite() {
        if (isSprite) {
            
            for (int i = 0 ; i <= 3 ; i++) {
                for (int j= 0 ; j <= 3 ; j++) {
                    if (fSprite.fdata[i][j] != 0 ) {
                        gameBoard[i + spritePos.x][j+spritePos.y] = fSprite.color.getRGB();
                    }
                }
            }
            isSprite = false;
        }
        
        int k=0;
        for (int j = 0 ; j < gameBoard[0].length; j++) {
            k=0;
            for (int i = 0 ; i < gameBoard.length; i++) {
                if (gameBoard[i][j] != 0) {
                k++;
                }           
            }
            if (k == gameBoard.length) {
                fline= fline +1;
                for (int jj= j ; jj > 0 ; jj--) {
                    for (int ii = 0 ; ii < gameBoard.length; ii++ ) {
                        gameBoard[ii][jj] = gameBoard[ii][jj-1];
                    }
                }
                for (int ii = 0 ; ii < gameBoard.length; ii++) {
                    gameBoard[ii][0] = 0 ;
                }
                
                
            }
        }
        
    }
    public void stepGame(){
        Point p = new Point(spritePos.x,spritePos.y+1);
        if (canSpriteBeHere(getCurrentSprite(),p)) {
            spritePos = p;
        } else {
            fixCurrentSprite();
        }
    }
    public Tetris() {
        isSprite = false;
        
    }
    public void right() {
        if (canSpriteBeHere(fSprite, new Point(spritePos.x + 1 , spritePos.y))) {
            spritePos.x = spritePos.x + 1 ;
        }
        
    }
    public void left() {
        if (canSpriteBeHere(fSprite, new Point(spritePos.x - 1 , spritePos.y))) {
            spritePos.x = spritePos.x - 1 ;
        }
        
    }
    
    public int[][] getBoardWithSprite(){
        int[][] result = new int[10][20];
        for (int i = 0 ; i < gameBoard.length ; i++) {
            for (int j = 0 ; j < gameBoard[i].length ; j++) {
                result[i][j] = gameBoard[i][j];
            }
        }
        byte[][] spr = getCurrentSprite().fdata;
        for (int i = 0 ; i < spr.length ; i++) {
            for (int j = 0 ; j < spr[i].length ; j++) {
                if (spr[i][j] != 0 ) {
                    result[i+spritePos.x][j+spritePos.y] = getCurrentSprite().color.getRGB();
                }
            }
        }       
        return result;
        
    }
    
    public void rotate() {
        fSprite.rotate();
        if (!canSpriteBeHere(fSprite,spritePos)) {
            fSprite.rotate();
            fSprite.rotate();
            fSprite.rotate();
        }
        
    }
    
    public Image getGameImage(int isz) {
        BufferedImage bi = new BufferedImage(2+10 *isz,2+20 * isz,BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.getGraphics();
        g.setColor(Color.BLUE);
        g.fillRect(0,0,bi.getWidth(),bi.getHeight());
        int[][] brd = getBoardWithSprite();
        for (int i = 0 ; i < brd.length;i++) {
            for (int j = 0 ; j < brd[i].length;j++)  {
            Color c = new Color(brd[i][j]);
            g.setColor(c);
            g.fill3DRect(1+i*isz,1+j*isz,isz,isz ,true);
            }
        }
        for (int i = 0 ; i < nextSprite.fdata.length;i++) {
            for (int j = 0 ; j < nextSprite.fdata[i].length;j++)  {
            if (nextSprite.fdata[i][j]!=0) {
                Color c = nextSprite.color;
                g.setColor(c.darker());
                g.fillRect(5+i*4,5+j*4,4,4);
            }
            }
        }
        
        
        
        return bi;
        
    }
    
    public void dropSprite() {
        TSprite s = getCurrentSprite();
        while (s == getCurrentSprite()) {
            stepGame();
        }
        
    }
    
  
}