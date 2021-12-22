package levels.Level_3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CupGame extends JPanel implements MouseListener{
    private JLabel imageLabel;
    private ImageIcon cup;
    private int x = 100;
    private int y;
    private int determine = 1;
    private ImageIcon cupStop;
    private ImageIcon cupFirst, cupMiddle,cupLast;
    private Window win;

    public CupGame() {
        imageLabel = new JLabel("WAIT FOR SHUFFLE");
        y = 100;
        cup = new ImageIcon("CupPictures/ezgif-7-057bb9928e.gif");
        cupStop = new ImageIcon("CupPictures/cupsStill.png");
        cupFirst = new ImageIcon("CupPictures/firstUp.gif");
        cupMiddle = new ImageIcon("CupPictures/middleUp.gif");
        cupLast = new ImageIcon("CupPictures/lastUp.gif");
        if (determine == 1) {
            imageLabel.setText("WAIT FOR SHUFFLE");
        }
        else if (determine == 2) {
            imageLabel.setText("CHOOSE ONE");
        }
        else if(determine == 3){
            imageLabel.setText("YOU WON");
        }
        else if(determine == 4){
            imageLabel.setText("YOU WON");
        }
        else if(determine == 5){
            imageLabel.setText("YOU WON");
        }

        imageLabel.setIconTextGap(17);
        imageLabel.setFont(new Font("Courier new", Font.BOLD, 36));
        add(imageLabel).setBounds(250,0,700,100);
        setLayout(null);
        setVisible(true);
        this.setPreferredSize(new Dimension(800, 480));
        this.setBackground(Color.white);
        this.addMouseListener(this);
        new java.util.Timer().
                schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run () {
                                determine=2;
                                repaint();
                            }
                        },
                        5000
                );
    }

    public void paintComponent(Graphics page) {
        super.paintComponent(page);
        if (determine == 1) {
            imageLabel.setText("WAIT FOR SHUFFLE");
            cup.paintIcon(this, page, x, 100);
        } else if (determine == 2) {
            imageLabel.setText("CHOOSE ONE");
            cupStop.paintIcon(this, page, 100, 100);
        }
        else if(determine == 3){
            imageLabel.setText("YOU WON");
            cupFirst.paintIcon(this, page, 70,60);
        }
        else if(determine == 4){
            imageLabel.setText("YOU WON");
            cupMiddle.paintIcon(this, page, 90,40);
        }
        else if(determine == 5){
            imageLabel.setText("YOU WON");
            cupLast.paintIcon(this, page, 70,60);
        }
    }

    public void timerSet(int time){
        new java.util.Timer().
                schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run () {
                                win.dispose();
                            }
                        },
                        time
                );
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JComponent comp = (JComponent) e.getSource();
        win = SwingUtilities.getWindowAncestor(comp);
        if(e.getX()>150&&e.getX()<270){
            determine=3;
            repaint();
            timerSet(6000);
        }
        else if(e.getX()>330&&e.getX()<500){
            determine=4;
            repaint();
            timerSet(6000);
        }
        else if(e.getX()>500&&e.getX()<700){
            determine=5;
            repaint();
            timerSet(6000);
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
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.getContentPane().add(new CupGame());
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}

