package newpackage;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.JFrame;

public class Handler implements KeyListener, MouseListener, MouseMotionListener{
    
    private JFrame jFrame;
    private Canvas canvas;

    //------------
    private int lastOffsetX;
    private int lastOffsetY;

    //SCALE
    final private double MIN_SCALE = 0.1;
    final private double MAX_SCALE = 20;

    //STATE Click 
    private int stateClick = 0;//0=idle; 1 = set start; 2 = set finish

    public Handler(JFrame jFrame, Canvas canvas) {
        this.jFrame = jFrame;
        this.canvas = canvas;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        double x1 = e.getX() - canvas.getTranslateX();
        double y1 = e.getY() - canvas.getTranslateY();

        double scale = canvas.getScale();
        double cellSizeScale = canvas.getCellSize() * scale;
        double x = Math.floor(x1 / cellSizeScale);
        double y = Math.floor(y1 / cellSizeScale);

        int i = (int) y;
        int j = (int) x;

        if (stateClick == 0) {
            if ((new Titik(i, j)).compare(canvas.getStart())) {
                stateClick = 1;
            } else if ((new Titik(i, j)).compare(canvas.getFinish())) {
                stateClick = 2;
            } else {
                if (canvas.getLabyrinth() != null && i >= 0 && i < canvas.getLabyrinth().length && j >= 0 && j < canvas.getLabyrinth()[0].length) {
                    if (canvas.getLabyrinth()[i][j] == 0) {
                        canvas.getLabyrinth()[i][j] = -1;
                    } else {
                        canvas.getLabyrinth()[i][j] = 0;
                    }
                    canvas.solve();
                }
            }
        } else if (stateClick == 1) {
            canvas.setStart(i, j);
            stateClick = 0;
            canvas.solve();
        } else if (stateClick == 2) {
            canvas.setFinish(i, j);
            stateClick = 0;
            canvas.solve();
        }
        canvas.repaint();
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        // capture titik start x dan y
        lastOffsetX = e.getX();
        lastOffsetY = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        // defenisikan posisi x dan y yang baru
        // hitung translasi x dan y
        int newX = e.getX() - lastOffsetX;
        int newY = e.getY() - lastOffsetY;

        // increment last offset oleh even drag mouse
        lastOffsetX += newX;
        lastOffsetY += newY;

        // update posisi canvas
        canvas.setTranslateX(canvas.getTranslateX() + newX);
        canvas.setTranslateY(canvas.getTranslateY() + newY);

        // schedule a repaint.
        canvas.repaint();
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        //
    }

}
