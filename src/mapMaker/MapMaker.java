package mapMaker;

import main.GamePanel;
import manager.BlockManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MapMaker {
    // 0 = Sky, 1 = Rock, 2 = Grass (matching your BlockManager IDs)
    private static int selectedBlockID = 1;


    public static void main(String[] args) {
        JFrame window = new JFrame("Map Maker");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());

        MapPanel mapPanel = new MapPanel();

        // --- SIDEBAR ---
        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(150, mapPanel.screenHeight));
        sidebar.setBackground(Color.DARK_GRAY);

        // Buttons to switch the "Paint" color/texture
        sidebar.add(createTileButton("Sky", 0, mapPanel));
        sidebar.add(createTileButton("Rock", 1, mapPanel));
        sidebar.add(createTileButton("Grass", 2, mapPanel));

        // --- PAINTING LOGIC ---
        MouseAdapter mouseHandler = new MouseAdapter() {
            private void paint(MouseEvent e) {
                int col = e.getX() / mapPanel.tileSize;
                int row = e.getY() / mapPanel.tileSize;

                if (col >= 0 && col < mapPanel.maxScreenCol && row >= 0 && row < mapPanel.maxScreenRow) {
                    // Update the actual array in your Manager
                    mapPanel.blockManager.mapBlockNum[col][row] = selectedBlockID;
                    mapPanel.repaint();
                }
            }

            @Override public void mousePressed(MouseEvent e) { paint(e); }
            @Override public void mouseDragged(MouseEvent e) { paint(e); }
        };

        mapPanel.addMouseListener(mouseHandler);
        mapPanel.addMouseMotionListener(mouseHandler);

        window.add(sidebar, BorderLayout.WEST);
        window.add(mapPanel, BorderLayout.CENTER);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    private static JButton createTileButton(String name, int id, MapPanel mp) {
        JButton btn = new JButton(name);
        // This pulls the image YOU loaded in BlockManager to show on the button
        if (MapPanel.blockManager.block[id] != null && MapPanel.blockManager.block[id].image != null) {
            btn.setIcon(new ImageIcon(MapPanel.blockManager.block[id].image.getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
        }
        btn.addActionListener(e -> selectedBlockID = id);
        return btn;
    }
}