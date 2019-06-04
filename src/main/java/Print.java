import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.io.File;
import java.io.IOException;

public class Print implements Printable, ActionListener {
String path;
BufferedImage img = null;

    public int print(Graphics g, PageFormat pf, int page) {
        if (page > 0) {
            return NO_SUCH_PAGE;
        }
        Graphics2D g2d = (Graphics2D)g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());
        if (img == null) {
            try {
                File file = new File(path);
                img = ImageIO.read(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        g.drawImage(img, 0, 0, null);
        return PAGE_EXISTS;
    }
    public void actionPerformed(ActionEvent e) { }
}
