import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.ComponentLookupScope;
import org.assertj.swing.core.Robot;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.DialogFixture;
import org.assertj.swing.fixture.JFileChooserFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.assertj.swing.finder.JFileChooserFinder.findFileChooser;

class GuiTestOpenDir {
    private DialogFixture window;
    private Robot robot = BasicRobot.robotWithNewAwtHierarchy();

    @BeforeEach
    void setUp() {
        robot.settings().componentLookupScope(ComponentLookupScope.ALL);
        OpenDir frame = GuiActionRunner.execute(OpenDir::new);
        window = new DialogFixture(robot, frame);
        window.show(); // shows the frame to test
    }
    @Test
    void testFunFileChoose() {
        File file = new File("src" + File.separator + "test" + File.separator + "java");
        File fileDir = new File(".");
        window.button("buttonCancel").requireVisible();
        window.button("buttonOK").requireVisible();
        window.button("buttonOK").click();
        window.dialog("dialog1").requireVisible();
        window.dialog("dialog1").button("OptionPane.button").click();
        window.button("chooseDir").requireVisible();
        window.button("chooseDir").click();
        JFileChooserFixture fc = findFileChooser("fileChooser").using(robot);
        fc.requireVisible();
        fc.setCurrentDirectory(fileDir);
        fc.selectFile(file);
        fc.approve();
        window.dialog("dialog3").button("OptionPane.button");
        window.button("buttonOK").click();
    }
    @AfterEach
    void tearDown() {
        window.cleanUp();
    }
}
