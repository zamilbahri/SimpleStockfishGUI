/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Move;
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
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import javax.swing.JTextField;

/**
 *
 * @author zamil
 */
public class ChessBoardGUI extends JFrame {

    char[] pieceMap;
    final Image[] imgs;
    final int DIM = 512; // Dmensiton of the JFrame Window
    JTextField fenInput;
    
    /**
     * Constructor
     * @param pieceMap an array of characters containing the pieces in the proper indices 
     */
    public ChessBoardGUI(char[] pieceMap) {
        this.pieceMap = pieceMap;
        imgs = getPieceSprites();
        if (imgs == null) {}
 
        //initComponents();
        this.setSize(DIM+16, DIM+39);
        JPanel pn = createChessBoard();
        fenInput = new JTextField();
        
        this.add(pn);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
        
    
    /**
     * Creates the Chessboard
     * @return JPanel object that contains the chess board
     */
    private JPanel createChessBoard() {
        
        return new JPanel(){
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
                for (int i = 0; i < 64; ++i) {
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
                        //System.out.println(String.format("ind: %s, i: %s, (%s,%s)", ind, i, pos.getCol(), pos.getRow()));
                        g.drawImage(imgs[ind], pos.getCol()*64, (7-pos.getRow())*64, this);
                    }
                    
                } 
            }
        };
    }
    
    public void addFENListener(ActionListener listenForFEN) {
        fenInput.addActionListener(listenForFEN);
    }
    
    /**
     * Gets the FEN Input from JTextField fenInput. Called by the actionListener
     * @return String containing the FEN
     */
    public String getFEN() {
        return fenInput.getText();
    }
    
    /**
     * Gets the move when piece is dragged and dropped. This is an example function for now.
     * @return 
     */
    public Move getMove() {
        
        return new Move("e2", "e4");
    }
    
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
    
    public void update() {
        //updatePieceMap(pieceMap);
        this.repaint();
    }
    
    // this function currenty serves no purpose because there is no mechanism to pull data
    //  from the Model
    public void updatePieceMap(char[] pieceMap) {
        this.pieceMap = pieceMap;
    }
    
    // In reality getMove() will pull data from an action listener, which will be in ChessBoardController
    /**
     * Returns the move made by the user while dragging and dropping the mouse to a location.
     * @return Move object
     */
    /*
    public Move getMove() {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter move: ");
        
        String moveStr = s.nextLine();
        
        Move m = new Move(moveStr.substring(0, 2), moveStr.substring(2));
  
        return m;
        
    } */
    
    // texture atlas
    
}
