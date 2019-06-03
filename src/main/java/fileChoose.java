import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;

public class fileChoose extends JFrame {
    private JList filesList;
    private JPanel explorerPanel;
    private JScrollPane scrollPane;
    private JButton editButton;
    private JButton printButton;
    private JPanel buttonPanel;
    private JPanel listPanel;

    public fileChoose(final File file) {
        super("Выбор файла");
        setSize(640, 480);
        setExtendedState(MAXIMIZED_BOTH);
        setContentPane(explorerPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        DefaultListModel model = new DefaultListModel();
        String[] rootStr = file.list();
        for (String str : rootStr) {
            File obj = new File (file, str);
            if (obj.isFile()) model.addElement(str);
        }
        filesList.setModel(model);
        filesList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        printButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PrinterJob job = PrinterJob.getPrinterJob();
                print test = new print();
                test.path = file.getPath() + File.separator + filesList.getSelectedValue().toString();
                job.setPrintable(test);
                boolean ok = job.printDialog();
                if (ok) {
                    try {
                        job.print();
                    } catch (PrinterException ex) {

                    }
                }
            }
        });
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onEdit(file);
            }
        });
        filesList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
    }

    public void onEdit(File file) {
        editor a = new editor(file.getPath() + File.separator + filesList.getSelectedValue().toString());
        a.pack();
        a.setVisible(true);
    }
}
