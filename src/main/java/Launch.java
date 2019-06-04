public class Launch {
    public static void main(String[] args) {
        OpenDir dialog = new OpenDir();
        dialog.pack();
        dialog.setVisible(true);
        FileChoose a = new FileChoose(OpenDir.dir);
        a.pack();
        a.setVisible(true);
    }
}
