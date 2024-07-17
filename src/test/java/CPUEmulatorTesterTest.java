import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import nand2tetris.test.lib.CPUEmulatorTester;
import nand2tetris.test.lib.ControllerException;

public final class CPUEmulatorTesterTest {
    public static final Path RESOURCES_DIR = Paths.get("src", "test", "resources");

    @Test
    public void testScriptLoad() throws ControllerException {
        CPUEmulatorTester.fireTestScript(
                RESOURCES_DIR.resolve("FibonacciElement.tst")
        );
    }
}
