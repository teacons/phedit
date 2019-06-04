import javax.swing.*;
import java.awt.event.*;
import java.io.File;

public class OpenDir extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton chooseDir;
    private JFileChooser fileChooser;
    static File dir;

    OpenDir() {
        buttonOK.setName("buttonOK");
        chooseDir.setName("chooseDir");
        buttonCancel.setName("buttonCancel");
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        UIManager.put("FileChooser.openButtonText", "Открыть");
        UIManager.put("FileChooser.cancelButtonText", "Отмена");
        UIManager.put("FileChooser.filesOfTypeLabelText", "Типы файлов");
        UIManager.put("FileChooser.lookInLabelText", "Директория");
        UIManager.put("FileChooser.folderNameLabelText", "Путь директории");
        fileChooser = new JFileChooser();
        buttonOK.addActionListener(e -> onOK());
        buttonCancel.addActionListener(e -> onCancel());
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        chooseDir.addActionListener(e -> {
            fileChooser.setName("fileChooser");
            fileChooser.setDialogTitle("Выбор директории");
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int result = fileChooser.showOpenDialog(OpenDir.this);
            if (result == JFileChooser.APPROVE_OPTION ) {
                JOptionPane.showMessageDialog(OpenDir.this, fileChooser.getSelectedFile());
                dir = fileChooser.getSelectedFile();
            }
        });
    }

    private void onOK() {
        if (fileChooser.getSelectedFile() == null) JOptionPane.showMessageDialog(OpenDir.this, "Папка не выбрана");
        else dispose();
    }

    private void onCancel() {
        dispose();
        System.exit(0);
    }
}
