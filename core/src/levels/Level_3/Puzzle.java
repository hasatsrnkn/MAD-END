package levels.Level_3;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class Puzzle extends JFrame implements MouseListener {
    JButton b0,b1,b2,b3,b4,b5,b6,b7,b8,empty;
    ImageIcon ic0 =new ImageIcon("PuzzlePictures/black.jpg");
    Icon emptyIcon = new ImageIcon("PuzzlePictures/black.jpg");
    Icon ic1 =new ImageIcon("PuzzlePictures/Resim1.png");
    Icon ic2 =new ImageIcon("PuzzlePictures/Resim2.png");
    Icon ic3 =new ImageIcon("PuzzlePictures/Resim3.png");
    Icon ic4=new ImageIcon("PuzzlePictures/Resim4.png");
    Icon ic5=new ImageIcon("PuzzlePictures/Resim5.png");
    Icon ic6=new ImageIcon("PuzzlePictures/Resim6.png");
    Icon ic7=new ImageIcon("PuzzlePictures/Resim7.png");
    Icon ic8=new ImageIcon("PuzzlePictures/Resim8.png");
    Icon condition = new ImageIcon("PuzzlePictures/Ekran görüntüsü 2021-12-21 124938.png");
    Icon fullPicture = new ImageIcon("PuzzlePictures/org.png");

    public Puzzle(){
        super("Puzzle Game");
        JButton text = new JButton(condition);
        text.setBounds(370,10,260,145);
        JButton originalPic = new JButton(fullPicture);
        originalPic.setBounds(413,160,170,187);
        empty = new JButton(emptyIcon);
        b1=new JButton(ic1);
        b1.setBounds(123,123,113,113);
        b2=new JButton(ic2);
        b2.setBounds(10,236,113,113);
        b3=new JButton(ic3);
        b3.setBounds(236,123,113,113);
        b4=new JButton(ic4);
        b4.setBounds(10,123,113,113);
        b5=new JButton(ic5);
        b5.setBounds(10,10,113,113);
        b6=new JButton(ic6);
        b6.setBounds(236,10,113,113);
        b7=new JButton(ic7);
        b7.setBounds(123,10,113,113);
        b8=new JButton(ic8);
        b8.setBounds(123,236,113,113);
        b0=new JButton(ic0);
        b0.setBounds(236,236,113,113);
        empty = b0;
        try {
            setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("PuzzlePictures/black.jpg")))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        add(b2);
        add(b8);
        add(b5);
        add(b3);
        add(b6);
        add(b4);
        add(b1);
        add(b7);
        add(b0);
        b0.addMouseListener(this);
        b1.addMouseListener(this);
        b2.addMouseListener(this);
        b3.addMouseListener(this);
        b4.addMouseListener(this);
        b5.addMouseListener(this);
        b6.addMouseListener(this);
        b7.addMouseListener(this);
        b8.addMouseListener(this);
        setSize(660,400);
        add(text);
        add(originalPic);
        setLayout(null);
        setLocationRelativeTo(null);

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void switchIcons(JButton b,JButton black){
        Icon ic = b.getIcon();
        b.setIcon(black.getIcon());
        black.setIcon(ic);
    }

    public JButton getButtonByXAndY(int x,int y) {
        return (JButton) this.getContentPane().getComponentAt(x,y);
    }
    public boolean gameOver(){
        if(getButtonByXAndY(10,10).getIcon().toString().equals("PuzzlePictures/Resim1.png")&&
                getButtonByXAndY(123,10).getIcon().toString().equals("PuzzlePictures/Resim2.png")&&
                getButtonByXAndY(236,10).getIcon().toString().equals("PuzzlePictures/Resim3.png")&&
                getButtonByXAndY(10,123).getIcon().toString().equals("PuzzlePictures/Resim4.png")&&
                getButtonByXAndY(123,123).getIcon().toString().equals("PuzzlePictures/Resim5.png")&&
                getButtonByXAndY(236,123).getIcon().toString().equals("PuzzlePictures/Resim6.png")&&
                getButtonByXAndY(10,236).getIcon().toString().equals("PuzzlePictures/Resim7.png")&&
                getButtonByXAndY(123,236).getIcon().toString().equals("PuzzlePictures/Resim8.png")){
            return true;
        }
        return false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==b0){
            switchIcons(b0,empty);
            empty = b0;
        }
        if(e.getSource()==b1){
            switchIcons(b1,empty);
            empty = b1;
        }
        if(e.getSource()==b2){
            switchIcons(b2,empty);
            empty = b2;
        }
        if(e.getSource()==b3){
            switchIcons(b3,empty);
            empty = b3;
        }
        if(e.getSource()==b4){
            switchIcons(b4,empty);
            empty = b4;
        }
        if(e.getSource()==b5){
            switchIcons(b5,empty);
            empty = b5;
        }
        if(e.getSource()==b6){
            switchIcons(b6,empty);
            empty = b6;
        }
        if(e.getSource()==b7){
            switchIcons(b7,empty);
            empty = b7;
        }
        if(e.getSource()==b8){
            switchIcons(b8,empty);
            empty = b8;
        }
        if(gameOver()){
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {

    }
    @Override
    public void mouseReleased(MouseEvent e) {

    }
    @Override
    public void mouseEntered(MouseEvent e) {

    }
    @Override
    public void mouseExited(MouseEvent e) {

    }
    public static void main (String[]args) {
        Puzzle newGame = new Puzzle();
    }//end of main
}//end of class

