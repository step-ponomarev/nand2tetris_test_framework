package nand2tetris.test.lib;

import java.nio.file.Files;
import java.nio.file.Path;

import Hack.CPUEmulator.CPUEmulator;

public final class ASMTester {
    private static final String TEST_SCRIPT_FILE_EXTENSION = ".tst";
    private static final String CMP_FILE_EXTENSION = ".cmp";
    private static final String ASM_FILE_EXTENSION = ".asm";

    public static void executeTestScript(Path testScriptPath) throws ASMTranslationException {
        if (Files.notExists(testScriptPath)) {
            throw new IllegalArgumentException("Script file does not exist: " + testScriptPath);
        }

        final String path = testScriptPath.toAbsolutePath().toString();
        if (!path.endsWith(TEST_SCRIPT_FILE_EXTENSION)) {
            throw new IllegalArgumentException("Invalid script file extension " + path);
        }

        String testName = testScriptPath.getFileName().toString();
        testName = testName.substring(0, testName.length() - 4);

        final Path testDir = testScriptPath.getParent();
        final Path cmpFile = testDir.resolve(testName + CMP_FILE_EXTENSION);
        if (Files.notExists(cmpFile)) {
            throw new IllegalStateException("Compare file does not exist: " + cmpFile);
        }

        final Path asmFile = testDir.resolve(testName + ASM_FILE_EXTENSION);
        if (Files.notExists(asmFile)) {
            throw new IllegalStateException("Asm source file does not exist: " + asmFile);
        }

        try {
            new HackErrorThrowController(new CPUEmulator(), path);
        } catch (Exception e) {
            throw new ASMTranslationException(e.getMessage());
        }
    }
}