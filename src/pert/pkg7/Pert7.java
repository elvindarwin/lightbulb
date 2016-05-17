/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pert.pkg7;


import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class Pert7 extends JFrame implements ActionListener, MouseMotionListener{

    JButton start = new JButton("Start");
    JPanel splash = new JPanel();
    JPanel header = new JPanel();
    JPanel group = new JPanel();
    
    JTextPane editPane = new JTextPane();
    JComboBox fontStyle = new JComboBox();
    JComboBox fontSize = new JComboBox();
    
    private Image source;
    private Image Image1;
    private Image Image2;
    
    private boolean hover = false, started = false;
    
    public Pert7() {
        setTitle("nyala");
        setSize(300,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        start.addActionListener(this);
        splash.add(start);
        splash.setLayout(new GridLayout(1,1));
        
        fontStyle.addItem("Verdana");
        fontStyle.addItem("Arial");
        fontStyle.addActionListener(this);
        
        fontSize.addItem("12");
        fontSize.addItem("20");
        fontSize.addActionListener(this);
        
        header.setLayout(new FlowLayout(5,5,5));
        header.add(fontStyle);
        header.add(fontSize);
        
        group.setLayout(new GridLayout(2,1));
        group.add(header);
        group.add(editPane);
        
        group.setVisible(false);
        
        add(group,"North");
        add(splash,"South");
        
        source = new ImageIcon("lightbulb.png").getImage();
        Image1= createImage(new FilteredImageSource(source.getSource(), new CropImageFilter(1*300/2, 0, 150, 150)));
        Image2= createImage(new FilteredImageSource(source.getSource(), new CropImageFilter(0*300/2, 0, 150, 150)));
        addMouseMotionListener(this);
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
       java.awt.EventQueue.invokeLater(new Runnable() {

           @Override
           public void run() {
               new Pert7().setVisible(true);
           }
       });
    }

    public void ChangeFont(String font){
        int size;
        try{
             size = Integer.parseInt(fontSize.getSelectedItem().toString());
        }catch(Exception e){
            size =24;
        }
         Font fnt = new Font(font, Font.BOLD, size);
         editPane.setFont(fnt);
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g); 
        if(started == false){
            Graphics2D g2d = (Graphics2D)g;
        if(hover == false){
            g2d.drawImage(Image1,getWidth()/2-80, getHeight()/2-80, this);
        }
        else if(hover == true){
            g2d.drawImage(Image2,getWidth()/2-80, getHeight()/2-80, this);
        }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(hover == false && e.getY() >= splash.getY() && e.getY() < getHeight()){
            hover=true;
            repaint();
    }
    else if(hover == true && e.getY() <= splash.getY()){
            hover=false;
            repaint();
    }
}

    @Override
    public void actionPerformed(ActionEvent e) {
        ActionEvent a = e;
        if(e.getSource() == start){
            started = true;
            splash.setVisible(false);
            group.setVisible(true);
            setTitle("Font Styling");
            setSize(500,700);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
        }
        else if((JComboBox)a.getSource()== (JComboBox)fontStyle || (JComboBox)a.getSource()== fontSize){
            ChangeFont(fontStyle.getSelectedItem().toString());
        }
    }
}
