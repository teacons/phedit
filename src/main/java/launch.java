public class launch {
    public static void main(String[] args) {
        openDir dialog = new openDir();
        dialog.pack();
        dialog.setVisible(true);
        fileChoose a = new fileChoose(dialog.dir);
        a.pack();
        a.setVisible(true);
    }
}
