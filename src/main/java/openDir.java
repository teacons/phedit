import javax.swing.*;
import java.awt.event.*;
import java.io.File;

public class openDir extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton chooseDir;
    private JFileChooser fileChooser;
    static File dir;

    public openDir() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        UIManager.put("FileChooser.openButtonText", "Открыть");
        UIManager.put("FileChooser.cancelButtonText", "Отмена");
        UIManager.put("FileChooser.filesOfTypeLabelText", "Типы файлов");
        UIManager.put("FileChooser.lookInLabelText", "Директория");
        UIManager.put("FileChooser.folderNameLabelText", "Путь директории");
        fileChooser = new JFileChooser();
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        chooseDir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileChooser.setDialogTitle("Выбор директории");
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = fileChooser.showOpenDialog(openDir.this);
                if (result == JFileChooser.APPROVE_OPTION ) {
                    JOptionPane.showMessageDialog(openDir.this, fileChooser.getSelectedFile());
                    dir = fileChooser.getSelectedFile();
                }
            }
        });
    }

    private void onOK() {
        if (fileChooser.getSelectedFile() == null) JOptionPane.showMessageDialog(openDir.this, "Папка не выбрана");
        else dispose();
    }

    private void onCancel() {
        dispose();
        System.exit(0);
    }
}
