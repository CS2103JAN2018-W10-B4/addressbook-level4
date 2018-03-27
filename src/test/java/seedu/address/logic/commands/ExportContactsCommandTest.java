package seedu.address.logic.commands;

import static org.junit.Assert.*;

import org.junit.*;

import java.io.IOException;

import org.apache.commons.csv.CSVPrinter;

import org.junit.rules.ExpectedException;
import seedu.address.logic.commands.exceptions.CommandException;

public class ExportContactsCommandTest {

    public static final String VALID_NEW_FILE_PATH = "~/Desktop/writeToThisFile.csv";
    public static final String VALID_EXISTING_FILE_PATH = "~/Desktop/testContacts.csv";

    //featureUnderTest_testScenario_expectedBehavior()

    ExportContactsCommand exportDefaultPath = new ExportContactsCommand();
    ExportContactsCommand exportExistingPath = new ExportContactsCommand(VALID_EXISTING_FILE_PATH);
    ExportContactsCommand exportNewPath = new ExportContactsCommand(VALID_NEW_FILE_PATH);

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void getCSVToWriteTo_validFilePath_noExceptionThrown() throws IOException {
        CSVPrinter csvp = null;

        try {
            csvp = exportDefaultPath.getCSVToWriteTo();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertNotNull(csvp);
    }

    @Test
    public void getDefaultPath_noPathGiven_throwsNoExceptionsAndReturnsPath() {
        String path = null;
        path = exportDefaultPath.getDefaultPath();
        assertNotNull(path);
    }

    @Test
    public void executeUndoableCommand_validNewFilePath_createsNewFileAndWritesToIt() throws CommandException {
        CommandResult cr = null;
        try {
            cr = exportNewPath.executeUndoableCommand();
        } catch (CommandException e) {
            e.printStackTrace();
        }

        assertEquals(cr.toString(), "Contacts successfully exported.\n");
    }


}
