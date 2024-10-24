package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import javax.swing.SwingWorker;

public class Uplode extends JFrame {
    public Uplode() {
        setTitle("Uplode prodect");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setVisible(true);
        setLocationRelativeTo(null);
        JPanel jPanel = new JPanel();
        setcontent(jPanel);

    }

    public void setcontent(JPanel jPanel) {
        jPanel.setPreferredSize(new Dimension(500, 500));
        jPanel.setLayout(null);
        add(jPanel);

        JPanel jPanel2 = new JPanel();
        jPanel2.setPreferredSize(new Dimension(500, 100));
        jPanel2.setBounds(0, 0, 500, 100);
        jPanel2.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 10, 10);
        gbc.fill = GridBagConstraints.VERTICAL;
        JLabel chooseFile = new JLabel("Choosefile:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        jPanel2.add(chooseFile, gbc);
        jPanel.add(jPanel2);
        JPanel jPanel3 = new JPanel();
        jPanel3.setPreferredSize(new Dimension(500, 200));
        jPanel3.setBounds(0, 100, 500, 100);
        //jPanel3.setBackground(Color.gray);
        JTextField chooseFileTextField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        // gbc.anchor=GridBagConstraints.BASELINE;
        jPanel3.add(chooseFileTextField, gbc);
        jPanel.add(jPanel3);
        JButton browseButton = new JButton("browseButton");
        gbc.gridx = 1;
        gbc.gridy = 0;
        jPanel3.add(browseButton, gbc);

        JPanel jPanel4 = new JPanel();
        jPanel4.setPreferredSize(new Dimension(500, 200));
        jPanel4.setBounds(0, 200, 500, 200);
        // ImageIcon imageIcon = new ImageIcon("C:/Users/HP/OneDrive/Desktop/java shop
        // market/Project/resourse/akImage.jpg");

       
        jPanel4.setBackground(Color.red);
        JButton UplodeButton = new JButton("uplode");
        gbc.gridx = 1;
        gbc.gridy = 0;
        jPanel4.add(UplodeButton, gbc);

        JProgressBar ProgressBar = new JProgressBar(0, 100);
        ProgressBar.setStringPainted(true);
        gbc.gridx = 0;
        gbc.gridy = 0;
        jPanel4.add(ProgressBar, gbc);
        jPanel.add(jPanel4);

        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectFile = fileChooser.getSelectedFile();
                    selectFile.getAbsolutePath();
                }
            }
        });

        UplodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        for (int i = 0; i <= 100; i++) {
                            Thread.sleep(50);
                            publish(i);
                        }
                        return null;
                    }

                    @Override
                    protected void process(List<Integer> chunks) {
                        int lastvalue = chunks.get(chunks.size() - 1);
                        ProgressBar.setValue(lastvalue);
                    }

                    @Override
                    protected void done() {
                        UplodeButton.setEnabled(true);
                    }
                };
                worker.execute();
            }
        });

    }

}
