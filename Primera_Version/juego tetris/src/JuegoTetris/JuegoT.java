/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package JuegoTetris;

import java.awt.Color;

/**
 *
 * @author MANUEL
 */
//Para crear las piezas del pablero

public class JuegoT {
    byte[][] fdata ;
        byte[][] pI = {{1, 1, 1, 1}, {1, 0, 0, 1}, {1, 0, 0,1}, {1, 0, 0, 1}};
        byte[][] pS = {{0, 1, 1, 1}, {0, 0,0, 1}, {0, 0, 0, 1}, {0, 1, 1, 1}};
        byte[][] pZ = {{0, 0, 0, 0},  {1, 1, 0, 0}, {0, 1,1, 0},{0, 0, 0, 0}};
        byte[][] pP = {{0, 0, 0, 0},  {0, 1, 0, 0}, {1, 1,1, 0},{0, 0, 0, 0}};
        byte[][] pL = {{0, 0, 0, 0},  {0, 0, 1, 0}, {1, 1,1, 0},{0, 0, 0, 0}};
        byte[][] pJ = {{0, 0, 0, 0},  {1, 1, 1, 0}, {0, 0,1, 0},{0, 0, 0, 0}};
        byte[][] pC = {{0, 0, 0, 0},  {0, 1, 1, 0}, {0, 1,1, 0},{0, 0, 0, 0}};
        
        public Color color;
        
        public void GiraPieza() {
            byte[][] ftmpdata = new byte[4][4];
            for (int i =0 ; i< 4 ; i++)
                for (int j =0 ;j< 4 ; j++)
                    ftmpdata[i][j]=fdata[i][j];
            
            for (int i =0 ; i< 4 ; i++)
                for (int j =0 ;j< 4 ; j++)
                    fdata[3-j][i] = ftmpdata[i][j];
        }
        public JuegoT(int piece) {
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
