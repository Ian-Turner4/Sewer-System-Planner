import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SewerSystemPlanner {
	
	private static int mouseX = 0;
	private static int mouseY = 0;
	private static int toolPosX = 0;
	private static int toolPosY = 0;
	
	static JLabel l;
	

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("Sewer System Planner");
            
            l = new JLabel();
            l.setText("press mouse to enter application");

            frame.setSize(900, 800);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            
            JPanel start = new JPanel();
            start.add(l);
            
            JPanel panel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.setColor(Color.RED);
                    g.fillRect(100, 100, 200, 100);
                    g.setColor(Color.BLACK);
                    g.drawOval(toolPosX, toolPosY, 10, 10);
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
            
            start.addMouseListener(new MouseAdapter(){
            	public void mouseClicked(MouseEvent e) {
            		panel.setVisible(true);
            		start.setVisible(false);
            	}
            });
            
            panel.addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    mouseX = e.getX();
                    mouseY = e.getY();
                    toolPosX = (int) (Math.round(mouseX/10.0)*10) - 5;
                    toolPosY = (int) (Math.round(mouseY/10.0)*10) - 5;
                    panel.repaint();
                }
            });
            
            frame.setLayout(new FlowLayout());
            
            frame.add(panel);
            frame.add(start);
            
            frame.setVisible(true);
        });
	}

}
