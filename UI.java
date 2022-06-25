package newpackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class UI extends javax.swing.JFrame {
    
    //variable
    private JPanel jContentPane = null;
    private JPanel jPanelNorth = null;
    private Canvas canvas = null;
    private JButton jButtonStart = null;
    private JButton jButtonGenerateMap = null;
    private JButton jButtonReset = null;
    private JButton jButtonSolusiDFS = null;
//    private JButton jButtonSolusiBFS = null;
    
    public UI() {
        initialize();
    }

    private void initialize() {
        setTitle("UI Grid");
        setSize(600, 600);
        setContentPane(getJContentPane());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private JPanel getJContentPane() {
        if (jContentPane == null) {
            jContentPane = new JPanel();
            jContentPane.setLayout(new BorderLayout());
            jContentPane.add(getJPanelNorth(), BorderLayout.NORTH);
            jContentPane.add(getCanvas(), BorderLayout.CENTER);
        }
        return jContentPane;
    }
    
    private JButton getJButtonReset(){
        if (jButtonReset == null){
            jButtonReset = new JButton("Clear Blocks");
            jButtonReset.setPreferredSize(new Dimension(120, 30));
            jButtonReset.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int rows = 6;
                    int cols = 11;
                    canvas.resetGrid(rows, cols);
 
                }
            });
        }
        
        return jButtonReset;
    }
    
    private JButton getJButtonSolusiDFS() {
        if (jButtonSolusiDFS == null) {
            jButtonSolusiDFS = new JButton("Solusi DFS");
            jButtonSolusiDFS.setPreferredSize(new Dimension(120, 30));
            jButtonSolusiDFS.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    canvas.shortestPathDepthFirstSearch();
                }
            });
        }
        return jButtonSolusiDFS;
    }

//    private JButton getJButtonSolusiBFS() {
//        if (jButtonSolusiBFS == null) {
//            jButtonSolusiBFS = new JButton("Solusi BFS");
//            jButtonSolusiBFS.setPreferredSize(new Dimension(120, 30));
//            jButtonSolusiBFS.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent evt) {
//                    canvas.shortestPathBreadthFirstSearch();
//                }
//            });
//        }
//        return jButtonSolusiBFS;
//    }

    private JPanel getJPanelNorth() {
        if (jPanelNorth == null) {
            jPanelNorth = new JPanel();
            jPanelNorth.setBackground(Color.orange);
            jPanelNorth.setLayout(new FlowLayout());
            jPanelNorth.add(getJButtonReset());
            jPanelNorth.add(getJButtonSolusiDFS());
//            jPanelNorth.add(getJButtonSolusiBFS());
            
                    
        }
        return jPanelNorth;
    }

    private Canvas getCanvas() {
        if (canvas == null) {
            canvas = new Canvas();
            Handler handler = new Handler(this, canvas);
            this.addKeyListener(handler);
            canvas.addMouseListener(handler);
            canvas.addMouseMotionListener(handler);
        }
        return canvas;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                UI tampilan = new UI();
            }
        });
    }//end of main()
}
