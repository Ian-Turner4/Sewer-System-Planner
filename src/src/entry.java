import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class entry {
	
	private static int mouseX = 0;
	private static int mouseY = 0;

	public static void main(String[] args) {
		// TODO Fill out method, test
		SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("Graphics Window");

            frame.setSize(900, 800);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            
            JPanel panel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.setColor(Color.RED);
                    g.fillRect(100, 100, 200, 100);
                    g.setColor(Color.BLACK);
                    g.drawOval(mouseX, mouseY, 10, 10);
                    for (int i=1; i<(frame.getWidth()/10); i++) {
                    	g.drawLine(i*10, 0, i*10, frame.getHeight());
                    }
                    for (int i=1; i<(frame.getHeight()/10); i++) {
                    	g.drawLine(0, i*10, frame.getWidth(), i*10);
                    }
                }

                @Override
                public Dimension getPreferredSize() {
                    return new Dimension(400, 300);
                }
                
            };
            
            panel.addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    mouseX = e.getX();
                    mouseY = e.getY();
                    panel.repaint();
                }
            });
            
            
            frame.add(panel);
            
            frame.setVisible(true);
        });
	}

}
