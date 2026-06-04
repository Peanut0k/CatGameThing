package mapMaker;

import main.GamePanel;
import manager.BlockManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MapPanel extends JPanel {
    public final int originalTileSize = 32;
    final double tileScale = 1.25;
    final int screenScale = 5;
    final int[] resolution = {16, 9};

    private static GamePanel gp = new GamePanel();
    public static BlockManager blockManager = new BlockManager(gp);

    public final int tileSize = (int) (originalTileSize * tileScale);
    public final int screenHeight = originalTileSize * screenScale * resolution[1];
    public final int screenWidth = originalTileSize * screenScale * resolution[0];

    public final int maxScreenCol = screenWidth / tileSize;
    public final int maxScreenRow = screenHeight / tileSize;


    public int[][] mapData = new int[maxScreenCol][maxScreenRow];
    public int selectedTileType = 1;

    public MapPanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);

        MouseAdapter mouseHandler = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) { paintTile(e); }
            @Override
            public void mouseDragged(MouseEvent e) { paintTile(e); }
        };
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
    }

    private void paintTile(MouseEvent e) {
        int col = e.getX() / tileSize;
        int row = e.getY() / tileSize;

        if (col >= 0 && col < maxScreenCol && row >= 0 && row < maxScreenRow) {
            mapData[col][row] = selectedTileType;
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for (int col = 0; col < maxScreenCol; col++) {
            for (int row = 0; row < maxScreenRow; row++) {
                if (mapData[col][row] == 1) {
                    g2.setColor(Color.WHITE); // Your "Block" color
                    g2.fillRect(col * tileSize, row * tileSize, tileSize, tileSize);
                }

                g2.setColor(Color.DARK_GRAY);
                g2.drawRect(col * tileSize, row * tileSize, tileSize, tileSize);
            }
        }
        g2.dispose();
    }
}