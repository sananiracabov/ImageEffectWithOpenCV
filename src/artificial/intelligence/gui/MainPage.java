package artificial.intelligence.gui;

import artificial.intelligence.algorithm.Processing;
import artificial.intelligence.constants.CONSTANTS;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.objdetect.CascadeClassifier;

public class MainPage extends javax.swing.JFrame {

    private ImageIcon imageIcon;
    private File file;

    
    public MainPage() {
        initComponents();
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        CascadeClassifier cascadeClassifier = new CascadeClassifier(CONSTANTS.CASCADE_CLASSFIER);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser();
        lblImageOne = new javax.swing.JLabel();
        lblImageTwo = new javax.swing.JLabel();
        btnOpen = new javax.swing.JButton();
        btnProcess = new javax.swing.JButton();
        cmbTypes = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        btnOpen.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnOpen.setText("Open");
        btnOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenActionPerformed(evt);
            }
        });

        btnProcess.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnProcess.setText("Proccess");
        btnProcess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcessActionPerformed(evt);
            }
        });

        cmbTypes.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbTypes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Information", "Grayscale", "Brightness", "Sharpness", "GaussianFilter" }));
        cmbTypes.setName("Choose"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnOpen, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnProcess, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblImageOne, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbTypes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblImageTwo, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblImageOne, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
                    .addComponent(lblImageTwo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnOpen, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbTypes, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnProcess, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenActionPerformed
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            imageIcon = new ImageIcon(new ImageIcon(file.getAbsolutePath()).
                    getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT));
            lblImageOne.setIcon(imageIcon);
        }
    }//GEN-LAST:event_btnOpenActionPerformed

    private void btnProcessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcessActionPerformed
        try {
            Processing proc = new Processing();

            if (file == null) {
                return;
            }

            String item = cmbTypes.getSelectedItem().toString();

            switch (item) {
                case "Information":
                    proc.imageInfo(file);
                    break;
                case "Grayscale":
                    lblImageTwo.setIcon(getImageIcon(proc.applyGrayscale(file)));
                    break;
                case "Brightness":
                    proc.applyBrightness(file);
                    getImageIcon();
                    lblImageTwo.setIcon(getImageIcon());
                    break;
                case "Sharpness":
                    proc.applySharpness(file);
                    getImageIcon();
                    lblImageTwo.setIcon(getImageIcon());
                    break;
                case "GaussianFilter":
                    proc.applyGaussianFilter(file);
                    lblImageTwo.setIcon(getImageIcon());
                    break;
                default:
                    break;
            }
            
        } catch (Exception ex) {
            System.err.print(ex.getMessage());
        }
    }//GEN-LAST:event_btnProcessActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
    }//GEN-LAST:event_formWindowActivated

    private ImageIcon getImageIcon() throws Exception {
        BufferedImage bf = ImageIO.read(new File("image.jpg"));
        return new ImageIcon(bf.getScaledInstance(400, 400,
                Image.SCALE_SMOOTH));
    }

    private ImageIcon getImageIcon(Image image) {
        return new ImageIcon(image.getScaledInstance(400, 400, Image.SCALE_DEFAULT));
    }

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOpen;
    private javax.swing.JButton btnProcess;
    private javax.swing.JComboBox cmbTypes;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JLabel lblImageOne;
    private javax.swing.JLabel lblImageTwo;
    // End of variables declaration//GEN-END:variables
}
