import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.ComponentLookupScope;
import org.assertj.swing.core.Robot;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.assertj.swing.finder.WindowFinder.findFrame;

class GuiTestFileChoose {
    private FrameFixture window;
    private Robot robot = BasicRobot.robotWithNewAwtHierarchy();

    @BeforeEach
    void setUp() {
        robot.settings().componentLookupScope(ComponentLookupScope.ALL);
        File file = new File("." + File.separator + "src" + File.separator + "test" + File.separator + "java");
        FileChoose frame = GuiActionRunner.execute(() -> new FileChoose(file));
        window = new FrameFixture(robot, frame);
        window.show(); // shows the frame to test
    }
    @Test
    void testFunFileChoose() {
        window.list("filesList").requireVisible();
        window.list("filesList").selectItem("test.jpg");
        window.button("editButton").requireVisible();
        window.button("editButton").click();
        FrameFixture editor = findFrame("frame1").using(robot);
        editor.requireVisible();
        editor.close();
        window.button("printButton").requireVisible();
        window.button("printButton").click();
        window.dialog("dialog0").requireVisible();
    }
    @AfterEach
    void tearDown() {
        window.cleanUp();
    }
}
