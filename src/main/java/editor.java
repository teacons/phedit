import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
/*
 * Created by JFormDesigner on Sun Jun 02 18:27:01 MSK 2019
 */



/**
 * @author unknown
 */
class editor extends JFrame {
    private BufferedImage imag;
    private int rezhim=0;
    private int xPad;
    private int yPad;
    private boolean pressed=false;
    private Color maincolor;
    private JColorChooser tcc;

    editor(String path) {
        initComponents();
        maincolor = Color.black;
        ColorButton.setBackground(maincolor);
        setContentPane(MainPanel);
        try {
            File iF= new File(path);
            imag = scale(ImageIO.read(iF), MainFrame.getWidth() - 100, MainFrame.getHeight() - 100);
            panEditor.repaint();
        }
        catch (FileNotFoundException ex) { JOptionPane.showMessageDialog(MainFrame, "Такого файла не существует"); }
        catch (IOException ex) { JOptionPane.showMessageDialog(MainFrame, "Исключение ввода-вывода"); }
        catch (Exception ignored) { }
        tcc = new JColorChooser(maincolor);
        tcc.getSelectionModel().addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                maincolor = tcc.getColor();
                ColorButton.setBackground(maincolor);
            }
        });
    }

    private void PencilActionPerformed(ActionEvent e) {
        rezhim = 0;
    }

    private void BrushActionPerformed(ActionEvent e) {
        rezhim = 1;
    }

    private void EraserActionPerformed(ActionEvent e) {
        rezhim = 2;
    }

    private void BlackActionPerformed(ActionEvent e) {
        maincolor = Color.black;
        ColorButton.setBackground(maincolor);
    }

    private void WhiteActionPerformed(ActionEvent e) {
        maincolor = Color.white;
        ColorButton.setBackground(maincolor);
    }

    private void BlueActionPerformed(ActionEvent e) {
        maincolor = Color.blue;
        ColorButton.setBackground(maincolor);
    }

    private void RedActionPerformed(ActionEvent e) {
        maincolor = Color.red;
        ColorButton.setBackground(maincolor);
    }

    private void OrangeActionPerformed(ActionEvent e) {
        maincolor = Color.orange;
        ColorButton.setBackground(maincolor);
    }

    private void YellowActionPerformed(ActionEvent e) {
        maincolor = Color.yellow;
        ColorButton.setBackground(maincolor);
    }

    private void GreenActionPerformed(ActionEvent e) {
        maincolor = Color.green;
        ColorButton.setBackground(maincolor);
    }

    private void PinkActionPerformed(ActionEvent e) {
        maincolor = Color.pink;
        ColorButton.setBackground(maincolor);
    }

    private void MagentaActionPerformed(ActionEvent e) {
        maincolor = Color.magenta;
        ColorButton.setBackground(maincolor);
    }

    private void CyanActionPerformed(ActionEvent e) {
        maincolor = Color.cyan;
        ColorButton.setBackground(maincolor);
    }

    private void ColorButtonActionPerformed(ActionEvent e) {
        ColorDialog coldi = new ColorDialog(MainFrame,"Выбор цвета");
        coldi.setVisible(true);
    }

    private void panEditorMouseDragged(MouseEvent e) {
        if (pressed) {
            Graphics g = imag.getGraphics();
            Graphics2D g2 = (Graphics2D)g;
            g2.setColor(maincolor);
            switch (rezhim) {
                case 0:
                    g2.drawLine(xPad, yPad, e.getX(), e.getY());
                    break;
                    case 1:
                        g2.setStroke(new BasicStroke(3.0f));
                        g2.drawLine(xPad, yPad, e.getX(), e.getY());
                        break;
                        case 2:
                            g2.setStroke(new BasicStroke(3.0f));
                            g2.setColor(Color.WHITE);
                            g2.drawLine(xPad, yPad, e.getX(), e.getY());
                            break;
            }
            xPad=e.getX();
            yPad=e.getY();
        }
        panEditor.repaint();
    }

    private void panEditorMouseClicked(MouseEvent e) {
        Graphics g = imag.getGraphics();
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(maincolor);
        switch (rezhim) {
            case 0:
                g2.drawLine(xPad, yPad, xPad+1, yPad+1);
                break;
                case 1:
                    g2.setStroke(new BasicStroke(3.0f));
                    g2.drawLine(xPad, yPad, xPad+1, yPad+1);
                    break;
                    case 2:
                        g2.setStroke(new BasicStroke(3.0f));
                        g2.setColor(Color.WHITE);
                        g2.drawLine(xPad, yPad, xPad+1, yPad+1);
                        break;
        }
        xPad=e.getX();
        yPad=e.getY();
        pressed=true;
        panEditor.repaint();
    }

    private void panEditorMousePressed(MouseEvent e) {
        xPad=e.getX();
        yPad=e.getY();
        pressed=true;
    }

    private void button1ActionPerformed(ActionEvent e) {
        PrinterJob job = PrinterJob.getPrinterJob();
        print test = new print();
        test.img = imag;
        job.setPrintable(test);
        boolean ok = job.printDialog();
        if (ok) {
            try {
                job.print();
            } catch (PrinterException ignored) {

            }
        }
    }
    private BufferedImage scale(BufferedImage src, int w, int h) {
        int type = BufferedImage.TYPE_INT_RGB;
        BufferedImage dst = new BufferedImage(w, h, type);
        Graphics2D g2 = dst.createGraphics();
        g2.setBackground(UIManager.getColor("panEditor.background"));
        g2.clearRect(0,0,w,h);
        double xScale = (double)w/src.getWidth();
        double yScale = (double)h/src.getHeight();
        double scale = Math.min(xScale, yScale);
        int width  = (int)(scale*src.getWidth());
        int height = (int)(scale*src.getHeight());
        int x = (w - width)/2;
        int y = (h - height)/2;
        g2.drawImage(src, x, y, width, height, null);
        g2.dispose();
        return dst;
    }






    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - tea
        MainFrame = new JFrame();
        MainPanel = new JPanel();
        Tools = new JToolBar();
        Pencil = new JButton();
        Brush = new JButton();
        Eraser = new JButton();
        ColorsBar = new JToolBar();
        Black = new JButton();
        White = new JButton();
        Blue = new JButton();
        Red = new JButton();
        Orange = new JButton();
        Yellow = new JButton();
        Green = new JButton();
        Pink = new JButton();
        Magenta = new JButton();
        Cyan = new JButton();
        ColorButton = new JButton();
        panEditor = new MyPanel();
        button1 = new JButton();

        //======== MainFrame ========
        {
            MainFrame.setBackground(Color.white);
            MainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            MainFrame.setTitle("\u0420\u0435\u0434\u0430\u043a\u0442\u0438\u0440\u043e\u0432\u0430\u043d\u0438\u0435");
            MainFrame.setMinimumSize(new Dimension(500, 500));
            Container MainFrameContentPane = MainFrame.getContentPane();
            MainFrameContentPane.setLayout(null);

            //======== MainPanel ========
            {

                // JFormDesigner evaluation mark

                MainPanel.setLayout(null);

                //======== Tools ========
                {
                    Tools.setFloatable(false);
                    Tools.setOrientation(SwingConstants.VERTICAL);
                    Tools.setOpaque(false);
                    Tools.setPreferredSize(new Dimension(30, 120));
                    Tools.setMaximumSize(new Dimension(30, 120));
                    Tools.setMinimumSize(new Dimension(30, 120));

                    //---- Pencil ----
                    Pencil.setMaximumSize(new Dimension(30, 30));
                    Pencil.setMinimumSize(new Dimension(30, 30));
                    Pencil.setPreferredSize(new Dimension(30, 30));
                    Pencil.setIcon(new ImageIcon("C:\\Users\\ivkin\\Downloads\\icon\\pencil30px.png"));
                    Pencil.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            PencilActionPerformed(e);
                        }
                    });
                    Tools.add(Pencil);

                    //---- Brush ----
                    Brush.setMaximumSize(new Dimension(30, 30));
                    Brush.setMinimumSize(new Dimension(30, 30));
                    Brush.setPreferredSize(new Dimension(30, 30));
                    Brush.setIcon(new ImageIcon("C:\\Users\\ivkin\\Downloads\\icon\\paint-brush30px.png"));
                    Brush.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            BrushActionPerformed(e);
                        }
                    });
                    Tools.add(Brush);

                    //---- Eraser ----
                    Eraser.setMaximumSize(new Dimension(30, 30));
                    Eraser.setMinimumSize(new Dimension(30, 30));
                    Eraser.setPreferredSize(new Dimension(30, 30));
                    Eraser.setIcon(new ImageIcon("C:\\Users\\ivkin\\Downloads\\icon\\eraser30px.png"));
                    Eraser.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            EraserActionPerformed(e);
                        }
                    });
                    Tools.add(Eraser);
                }
                MainPanel.add(Tools);
                Tools.setBounds(0, 0, 34, 125);

                //======== ColorsBar ========
                {
                    ColorsBar.setFloatable(false);
                    ColorsBar.setOpaque(false);
                    ColorsBar.setMaximumSize(new Dimension(300, 30));
                    ColorsBar.setMinimumSize(new Dimension(300, 30));
                    ColorsBar.setPreferredSize(new Dimension(300, 30));

                    //---- Black ----
                    Black.setMaximumSize(new Dimension(30, 30));
                    Black.setMinimumSize(new Dimension(30, 30));
                    Black.setPreferredSize(new Dimension(30, 30));
                    Black.setBackground(Color.black);
                    Black.setForeground(Color.black);
                    Black.setVerticalAlignment(SwingConstants.TOP);
                    Black.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            BlackActionPerformed(e);
                        }
                    });
                    ColorsBar.add(Black);

                    //---- White ----
                    White.setMaximumSize(new Dimension(30, 30));
                    White.setMinimumSize(new Dimension(30, 30));
                    White.setPreferredSize(new Dimension(30, 30));
                    White.setBackground(Color.white);
                    White.setForeground(Color.white);
                    White.setVerticalAlignment(SwingConstants.TOP);
                    White.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            WhiteActionPerformed(e);
                        }
                    });
                    ColorsBar.add(White);

                    //---- Blue ----
                    Blue.setMaximumSize(new Dimension(30, 30));
                    Blue.setMinimumSize(new Dimension(30, 30));
                    Blue.setPreferredSize(new Dimension(30, 30));
                    Blue.setBackground(Color.blue);
                    Blue.setForeground(Color.blue);
                    Blue.setVerticalAlignment(SwingConstants.TOP);
                    Blue.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            BlueActionPerformed(e);
                        }
                    });
                    ColorsBar.add(Blue);

                    //---- Red ----
                    Red.setMaximumSize(new Dimension(30, 30));
                    Red.setMinimumSize(new Dimension(30, 30));
                    Red.setPreferredSize(new Dimension(30, 30));
                    Red.setBackground(Color.red);
                    Red.setForeground(Color.red);
                    Red.setVerticalAlignment(SwingConstants.TOP);
                    Red.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            RedActionPerformed(e);
                        }
                    });
                    ColorsBar.add(Red);

                    //---- Orange ----
                    Orange.setMaximumSize(new Dimension(30, 30));
                    Orange.setMinimumSize(new Dimension(30, 30));
                    Orange.setPreferredSize(new Dimension(30, 30));
                    Orange.setBackground(Color.orange);
                    Orange.setForeground(Color.orange);
                    Orange.setVerticalAlignment(SwingConstants.TOP);
                    Orange.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            OrangeActionPerformed(e);
                        }
                    });
                    ColorsBar.add(Orange);

                    //---- Yellow ----
                    Yellow.setMaximumSize(new Dimension(30, 30));
                    Yellow.setMinimumSize(new Dimension(30, 30));
                    Yellow.setPreferredSize(new Dimension(30, 30));
                    Yellow.setBackground(Color.yellow);
                    Yellow.setForeground(Color.yellow);
                    Yellow.setVerticalAlignment(SwingConstants.TOP);
                    Yellow.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            YellowActionPerformed(e);
                        }
                    });
                    ColorsBar.add(Yellow);

                    //---- Green ----
                    Green.setMaximumSize(new Dimension(30, 30));
                    Green.setMinimumSize(new Dimension(30, 30));
                    Green.setPreferredSize(new Dimension(30, 30));
                    Green.setBackground(Color.green);
                    Green.setForeground(Color.green);
                    Green.setVerticalAlignment(SwingConstants.TOP);
                    Green.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            GreenActionPerformed(e);
                        }
                    });
                    ColorsBar.add(Green);

                    //---- Pink ----
                    Pink.setMaximumSize(new Dimension(30, 30));
                    Pink.setMinimumSize(new Dimension(30, 30));
                    Pink.setPreferredSize(new Dimension(30, 30));
                    Pink.setBackground(Color.pink);
                    Pink.setForeground(Color.pink);
                    Pink.setVerticalAlignment(SwingConstants.TOP);
                    Pink.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            PinkActionPerformed(e);
                        }
                    });
                    ColorsBar.add(Pink);

                    //---- Magenta ----
                    Magenta.setMaximumSize(new Dimension(30, 30));
                    Magenta.setMinimumSize(new Dimension(30, 30));
                    Magenta.setPreferredSize(new Dimension(30, 30));
                    Magenta.setBackground(Color.magenta);
                    Magenta.setForeground(Color.magenta);
                    Magenta.setVerticalAlignment(SwingConstants.TOP);
                    Magenta.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            MagentaActionPerformed(e);
                        }
                    });
                    ColorsBar.add(Magenta);

                    //---- Cyan ----
                    Cyan.setMaximumSize(new Dimension(30, 30));
                    Cyan.setMinimumSize(new Dimension(30, 30));
                    Cyan.setPreferredSize(new Dimension(30, 30));
                    Cyan.setBackground(Color.cyan);
                    Cyan.setForeground(Color.cyan);
                    Cyan.setVerticalAlignment(SwingConstants.TOP);
                    Cyan.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            CyanActionPerformed(e);
                        }
                    });
                    ColorsBar.add(Cyan);

                    //---- ColorButton ----
                    ColorButton.setPreferredSize(new Dimension(40, 40));
                    ColorButton.setMaximumSize(new Dimension(40, 40));
                    ColorButton.setMinimumSize(new Dimension(40, 40));
                    ColorButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            ColorButtonActionPerformed(e);
                        }
                    });
                    ColorsBar.add(ColorButton);
                }
                MainPanel.add(ColorsBar);
                ColorsBar.setBounds(65, 0, 345, 45);

                //======== panEditor ========
                {
                    panEditor.addMouseMotionListener(new MouseMotionAdapter() {
                        @Override
                        public void mouseDragged(MouseEvent e) {
                            panEditorMouseDragged(e);
                        }
                    });
                    panEditor.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            panEditorMouseClicked(e);
                        }
                        @Override
                        public void mousePressed(MouseEvent e) {
                            panEditorMousePressed(e);
                        }
                    });
                    panEditor.setLayout(null);
                }
                MainPanel.add(panEditor);
                panEditor.setBounds(40, 50, 855, 545);

                //---- button1 ----
                button1.setText("\u041f\u0435\u0447\u0430\u0442\u044c");
                button1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        button1ActionPerformed(e);
                    }
                });
                MainPanel.add(button1);
                button1.setBounds(705, 5, 155, 33);

                { // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < MainPanel.getComponentCount(); i++) {
                        Rectangle bounds = MainPanel.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = MainPanel.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    MainPanel.setMinimumSize(preferredSize);
                    MainPanel.setPreferredSize(preferredSize);
                }
            }
            MainFrameContentPane.add(MainPanel);
            MainPanel.setBounds(5, 5, 930, 615);

            { // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < MainFrameContentPane.getComponentCount(); i++) {
                    Rectangle bounds = MainFrameContentPane.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = MainFrameContentPane.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                MainFrameContentPane.setMinimumSize(preferredSize);
                MainFrameContentPane.setPreferredSize(preferredSize);
            }
            MainFrame.pack();
            MainFrame.setLocationRelativeTo(MainFrame.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - tea
    private JFrame MainFrame;
    private JPanel MainPanel;
    private JToolBar Tools;
    private JButton Pencil;
    private JButton Brush;
    private JButton Eraser;
    private JToolBar ColorsBar;
    private JButton Black;
    private JButton White;
    private JButton Blue;
    private JButton Red;
    private JButton Orange;
    private JButton Yellow;
    private JButton Green;
    private JButton Pink;
    private JButton Magenta;
    private JButton Cyan;
    private JButton ColorButton;
    private MyPanel panEditor;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    class ColorDialog extends JDialog {
        ColorDialog(JFrame owner, String title) {
            super(owner, title, true);
            add(tcc);
            setSize(600, 600);
        }
    }
    class MyPanel extends JPanel {
        MyPanel()
        { }
        public void paintComponent (Graphics g) {
        super.paintComponent(g);
        g.drawImage(imag, 0, 0,this);
    }
    }

}
