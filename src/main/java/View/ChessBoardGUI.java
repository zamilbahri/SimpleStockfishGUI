/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel; 
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.SwingWorker;
import Model.Position;

/**
 *
 * @author zamil
 */
public class ChessBoardGUI extends JFrame {

    char[] pieceMap;
    final Image[] imgs;
    final int DIM = 512; // Dmensiton of the JFrame Window
    
    /**
     * Constructor
     * @param pieceMap an array of characters containing the pieces in the proper indices 
     */
    public ChessBoardGUI(char[] pieceMap) {
        this.pieceMap = pieceMap;
        imgs = getPieceSprites();
        if (imgs == null) {}
        initComponents();
    }
    
    /**
     * Sets up the board and pieces in their starting positions
     */
    private void initComponents() {
        JFrame frame = new JFrame ("Chess");
        frame.setSize(DIM+16, DIM+39);
        System.out.println(String.format("%s, %s, %s, %s", frame.getInsets().bottom, frame.getInsets().left, frame.getInsets().top, frame.getInsets().right));
        //frame.setUndecorated(true);
        
        JPanel pn;
        pn = new JPanel(){
            @Override
            public void paint (Graphics g){
                boolean white = true;
                for(int y = 0;y < 8;y++){
                    for(int x = 0;x < 8;x++){
                        if(white){
                            g.setColor(new Color(235,235, 208));
                        }else{
                            g.setColor(new Color(119, 148, 85));
                        }
                        g.fillRect(x*64, y*64, 64, 64);
                        white=!white;
                    }
                    white=!white;
                }
                for (char i = 0; i < 64; ++i) {
                    char p = pieceMap[i];
                    int ind = -1;
                    
                    switch(p) {
                        case 'K', 'k' -> ind = 0;
                        case 'Q', 'q' -> ind = 1;           
                        case 'B', 'b' -> ind = 2;
                        case 'N', 'n' -> ind = 3;
                        case 'R', 'r' -> ind = 4;
                        case 'P', 'p' -> ind = 5;
                        default -> {
                        }
                    }
                    
                    if(Character.isLowerCase(p)){
                        ind += 6;
                    }
                    if (ind > -1) {
                        Position pos = new Position(i);
                        g.drawImage(imgs[ind], pos.getCol()*64, pos.getRow()*64, this);
                    }
                } 
            }
        };
        frame.add(pn);
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);
    }
   
    public void test() throws InterruptedException {
        SwingWorker<Void, Void> Worker;
        Worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                Thread.sleep(3000);
                System.out.println("Hello");
                return null;
            }
        };
        
        Worker.execute();
    }
    
    // texture atlas
    private Image[] getPieceSprites() {
        BufferedImage all;
        try {
            all = ImageIO.read(new File("src\\main\\java\\Sprites\\Pieces\\Pieces.png"));
            Image imgs[] = new Image[12];
            int ind = 0;
            for (int y = 0; y < 400; y+=200) {
                for (int x = 0; x < 1200; x+=200) {
                    imgs[ind] = all.getSubimage(x, y, 200, 200).getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH);
                    ind++;
                }
            }
            return imgs;
        } catch (IOException ex) {
            Logger.getLogger(ChessBoardGUI.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
            return null;
        }
    }
}
