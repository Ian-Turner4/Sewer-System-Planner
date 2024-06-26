import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class SewerSystemPlanner {

    private static int mouseX = 0;
    private static int mouseY = 0;
    private static int toolPosX = 0;
    private static int toolPosY = 0;

    private static int tempPipeX, tempPipeY;

    private static String toolSym = "L";

    static boolean pointsFull[][] = new boolean[89][79];

    static ArrayList<Loc> Locs = new ArrayList<Loc>();
    static ArrayList<Pipe> Pipes = new ArrayList<Pipe>();
    static ArrayList<Pipe> Lines = new ArrayList<Pipe>();

    static Button[] Buttons = new Button[]{
        new Button(850, 100, 30, "L"),
        new Button(850, 200, 30, "P"),
        new Button(850, 300, 30, "R")
    };

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("Sewer System Planner");

            frame.setSize(900, 800);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);

            JPanel panel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.setColor(Color.BLACK);
                    g.drawOval(toolPosX, toolPosY, 10, 10);
                    for (int i = 1; i < (frame.getWidth() / 10); i++) {
                        g.drawLine(i * 10, 0, i * 10, frame.getHeight());
                    }
                    for (int i = 1; i < (frame.getHeight() / 10); i++) {
                        g.drawLine(0, i * 10, frame.getWidth(), i * 10);
                    }
                    for (Loc i : Locs) {
                        g.drawOval(i.x, i.y, 10, 10);
                    }
                    for (Pipe i : Pipes) {
                        g.setColor(Color.BLUE);
                        g.drawLine(i.x1, i.y1, i.x2, i.y2);
                        g.setColor(Color.BLACK);
                        g.drawString(String.valueOf(Math.round(i.length * 1000.0) / 1000.0), (i.x1 + i.x2) / 2, (i.y1 + i.y2) / 2);
                    }
                    g.setColor(Color.RED);
                    for (Pipe i : Lines) {
                        g.drawLine(i.x1, i.y1, i.x2, i.y2);
                    }
                    g.setColor(Color.RED);
                    for (Button i : Buttons) {
                        g.fillOval(i.x, i.y, i.size, i.size);
                    }
                    g.setColor(Color.BLACK);
                    for (Button i : Buttons) {
                        g.drawString(i.sym, i.x + i.size / 2, i.y + i.size / 2);
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
                    toolPosX = (int) (Math.round(mouseX / 10.0) * 10) - 5;
                    toolPosY = (int) (Math.round(mouseY / 10.0) * 10) - 5;
                    panel.repaint();
                }
            });

            panel.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    toolSym = Buttons[0].mouseOver(mouseX, mouseY) ? "L" :
                              Buttons[1].mouseOver(mouseX, mouseY) ? "P" :
                              Buttons[2].mouseOver(mouseX, mouseY) ? "R" : toolSym;
                    
                    boolean overButton = false;
                    
                    for(Button i:Buttons) {
                    	if(i.mouseOver(mouseX, mouseY)) {
                    		overButton = true;
                    	}
                    }

                    boolean pointAvailable = (!pointsFull[toolPosX / 10][toolPosY / 10]) && !overButton;

                    if (toolSym.equals("L") && pointAvailable) {
                        Locs.add(new Loc(toolPosX, toolPosY));
                        pointsFull[toolPosX / 10][toolPosY / 10] = true;
                        panel.repaint();
                    } else if (toolSym.equals("P") && pointAvailable) {
                        Locs.add(new Loc(toolPosX, toolPosY));
                        pointsFull[toolPosX / 10][toolPosY / 10] = true;
                        tempPipeX = toolPosX;
                        tempPipeY = toolPosY;
                        toolSym = "P2";
                    } else if (toolSym.equals("P2") && pointAvailable) {
                        Locs.add(new Loc(toolPosX, toolPosY));
                        pointsFull[toolPosX / 10][toolPosY / 10] = true;
                        Pipes.add(new Pipe(tempPipeX, toolPosX, tempPipeY, toolPosY));
                        toolSym = "P";
                        panel.repaint();
                    } else if (toolSym.equals("R") && !overButton) {
                        Locs.add(new Loc(toolPosX, toolPosY));
                        tempPipeX = toolPosX;
                        tempPipeY = toolPosY;
                        toolSym = "R2";
                    } else if (toolSym.equals("R2")) {
                        Locs.add(new Loc(toolPosX, toolPosY));
                        Lines.add(new Pipe(tempPipeX, toolPosX, tempPipeY, toolPosY));
                        toolSym = "R";
                        panel.repaint();
                    }
                }
            });

            frame.add(panel);

            frame.setVisible(true);
        });
    }
}
