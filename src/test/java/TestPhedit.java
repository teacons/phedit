import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestPhedit {
    private File fileTemp;
    private FileChoose fc;
    private String ex = "java.lang.IllegalArgumentException";

    @BeforeEach
    void create() throws IOException {
        new FileWriter("temp.txt", false);
        File file = new File(".");
        fileTemp = new File("temp.txt");
        fc = new FileChoose(file);
    }
    @Test
    void testOnEditFileChoose () {
        fc.onEdit(fileTemp.getAbsolutePath());
        assertEquals(ex, fc.ex);
    }
    @Test
    void testOnPrintFileChoose () {
        fc.onPrint(fileTemp.getAbsolutePath());
        assertEquals(ex, fc.ex);
    }
}
