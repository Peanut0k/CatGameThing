package mapMaker;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        MapPanel mapPanel = new MapPanel();

        // --- SIDEBAR ---
        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(150, 0));
        sidebar.setBackground(Color.GRAY);
        sidebar.setLayout(new FlowLayout());

        // A button to select the "Block" tile
        JButton blockBtn = new JButton("Wall Block");
        blockBtn.addActionListener(e -> mapPanel.selectedTileType = 1);

        // A button to select "Eraser"
        JButton eraserBtn = new JButton("Eraser");
        eraserBtn.addActionListener(e -> mapPanel.selectedTileType = 0);

        sidebar.add(new JLabel("Tile Palette"));
        sidebar.add(blockBtn);
        sidebar.add(eraserBtn);

        // --- ADD TO FRAME ---
        add(sidebar, BorderLayout.WEST); // Blocks on the left!

        // Wrap MapPanel in a ScrollPane in case it's bigger than the screen
        JScrollPane scrollPane = new JScrollPane(mapPanel);
        add(scrollPane, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}