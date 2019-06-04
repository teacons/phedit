import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.ComponentLookupScope;
import org.assertj.swing.core.Robot;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.File;

class GuiTestEditor {
    private FrameFixture window;
    private Robot robot = BasicRobot.robotWithNewAwtHierarchy();

    @BeforeEach
    void setUp() {
        robot.settings().componentLookupScope(ComponentLookupScope.ALL);
        String path = "." + File.separator + "src" + File.separator + "test" + File.separator + "java" + File.separator + "test.jpg";
        Editor frame = GuiActionRunner.execute(() -> new Editor(path));
        window = new FrameFixture(robot, frame);
        window.show(); // shows the frame to test
    }
    @Test
    void testFunFileChoose() {
        window.button("Pencil").requireVisible();
        window.button("Pencil").click();
        window.button("Brush").requireVisible();
        window.button("Brush").click();
        window.button("Eraser").requireVisible();
        window.button("Eraser").click();
        window.button("Black").requireVisible();
        window.button("Black").click();
        window.button("Black").background().requireEqualTo(Color.black);
        window.button("ColorButton").background().requireEqualTo(Color.black);
        window.button("White").requireVisible();
        window.button("White").click();
        window.button("White").background().requireEqualTo(Color.white);
        window.button("ColorButton").background().requireEqualTo(Color.white);
        window.button("Blue").requireVisible();
        window.button("Blue").click();
        window.button("Blue").background().requireEqualTo(Color.blue);
        window.button("ColorButton").background().requireEqualTo(Color.blue);
        window.button("Red").requireVisible();
        window.button("Red").click();
        window.button("Red").background().requireEqualTo(Color.red);
        window.button("ColorButton").background().requireEqualTo(Color.red);
        window.button("Orange").requireVisible();
        window.button("Orange").click();
        window.button("Orange").background().requireEqualTo(Color.orange);
        window.button("ColorButton").background().requireEqualTo(Color.orange);
        window.button("Yellow").requireVisible();
        window.button("Yellow").click();
        window.button("Yellow").background().requireEqualTo(Color.yellow);
        window.button("ColorButton").background().requireEqualTo(Color.yellow);
        window.button("Green").requireVisible();
        window.button("Green").click();
        window.button("Green").background().requireEqualTo(Color.green);
        window.button("ColorButton").background().requireEqualTo(Color.green);
        window.button("Pink").requireVisible();
        window.button("Pink").click();
        window.button("Pink").background().requireEqualTo(Color.pink);
        window.button("ColorButton").background().requireEqualTo(Color.pink);
        window.button("Magenta").requireVisible();
        window.button("Magenta").click();
        window.button("Magenta").background().requireEqualTo(Color.magenta);
        window.button("ColorButton").background().requireEqualTo(Color.magenta);
        window.button("Cyan").requireVisible();
        window.button("Cyan").click();
        window.button("Cyan").background().requireEqualTo(Color.cyan);
        window.button("ColorButton").background().requireEqualTo(Color.cyan);
        window.button("ColorButton").requireVisible();
        window.button("ColorButton").click();
        window.dialog("dialog0").close();
        window.button("printer").requireVisible();
        window.button("printer").click();
        window.dialog("dialog1").requireVisible();
            }
    @AfterEach
    void tearDown() {
        window.cleanUp();
    }
}
