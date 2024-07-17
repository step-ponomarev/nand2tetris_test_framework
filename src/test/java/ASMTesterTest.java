import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import nand2tetris.test.lib.ASMTester;
import nand2tetris.test.lib.ASMTranslationException;

public final class ASMTesterTest {
    public static final Path RESOURCES_DIR = Paths.get("src", "test", "resources");

    @Test
    public void testAsmFile() throws ASMTranslationException {
        ASMTester.fireTestScript(
                RESOURCES_DIR.resolve("FibonacciElement/FibonacciElement.tst")
        );
    }

    @Test
    public void testAsmNoCmpFile() {
        Assertions.assertThrows(IllegalStateException.class, () -> ASMTester.fireTestScript(
                RESOURCES_DIR.resolve("FibonacciElement_no_cmp/FibonacciElement.tst")
        ));
    }

    @Test
    public void testAsmNoAsmFile() {
        Assertions.assertThrows(IllegalStateException.class, () -> ASMTester.fireTestScript(
                RESOURCES_DIR.resolve("FibonacciElement_no_asm/FibonacciElement.tst")
        ));
    }
}
