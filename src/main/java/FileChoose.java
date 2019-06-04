import org.apache.commons.io.FilenameUtils;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;

public class FileChoose extends JFrame {
    private JList<String> filesList;
    private JPanel explorerPanel;
    private JScrollPane scrollPane;
    private JButton editButton;
    private JButton printButton;
    private JPanel buttonPanel;
    private JPanel listPanel;
    String ex;

    FileChoose(final File file) {
        super("Выбор файла");
        filesList.setName("filesList");
        editButton.setName("editButton");
        printButton.setName("printButton");
        setSize(640, 480);
        setExtendedState(MAXIMIZED_BOTH);
        setContentPane(explorerPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        DefaultListModel<String> model = new DefaultListModel<>();
        String[] rootStr = file.list();
        assert rootStr != null;
        for (String str : rootStr) {
            File obj = new File (file, str);
            if (obj.isFile()) model.addElement(str);
        }
        filesList.setModel(model);
        filesList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        printButton.addActionListener(e -> onPrint(file.getPath() + File.separator + filesList.getSelectedValue()));
        editButton.addActionListener(e -> onEdit(file.getPath() + File.separator + filesList.getSelectedValue()));
        filesList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
    }

    void onEdit(String path) {
        try {
            if (!FilenameUtils.getExtension(path).equals("jpg")) throw new IllegalArgumentException();
            else {
                Editor a = new Editor(path);
                a.pack();
                a.setVisible(true);
            }
        } catch (IllegalArgumentException e){
            ex = e.toString();
            JOptionPane.showMessageDialog(FileChoose.this, "Только jpg файлы");
        }
    }
    void onPrint(String path) {
        try {
            if (!FilenameUtils.getExtension(path).equals("jpg")) throw new IllegalArgumentException();
            else {
                PrinterJob job = PrinterJob.getPrinterJob();
                Print test = new Print();
                test.path = path;
                job.setPrintable(test);
                boolean ok = job.printDialog();
                if (ok) {
                    try {
                        job.print();
                    } catch (PrinterException ex) {
                        JOptionPane.showMessageDialog(FileChoose.this, "Исключение" + ex.toString());
                    }
                }
            }
        } catch (IllegalArgumentException e){
            ex = e.toString();
            JOptionPane.showMessageDialog(FileChoose.this, "Только jpg файлы");
        }

    }
}
