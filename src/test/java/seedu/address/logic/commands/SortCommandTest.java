//@@author luca590

package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Test;

import javafx.collections.ObservableList;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;

public class SortCommandTest {

    //featureUnderTest_testScenario_expectedBehavior()

    /**
     * helper function to print people given model parameter
     * useful for debugging
     */
    public void printPeople(Model printModel) throws Exception {
        ReadOnlyAddressBook myBook = printModel.getAddressBook();
        ObservableList<Person> myPersonList = myBook.getPersonList();
        Iterator personIterator = myPersonList.iterator();

        Person p;
        while (personIterator.hasNext()) {
            p = (Person) personIterator.next();
            System.out.println(p.getName() + ", " + p.getEmail() + ", "
                    + p.getAddress() + ", " + p.getPhone());
        }
    }

    /**
     * helper function to check if PersonList in model is sorted
     * this function is contingent on importing "data/Test_contacts_unsorted.csv"
     * and not another file
     */
    public boolean checkSorted(Model myModel) {
        boolean isSorted = true;
        requireNonNull(myModel);
        Object[] parray = myModel.getFilteredPersonList().toArray();

        if (!parray[0].toString().substring(0, 1).equals("A")) {
            isSorted = false;
        }
        if (!parray[1].toString().substring(0, 1).equals("B")) {
            isSorted = false;
        }
        if (!parray[2].toString().substring(0, 1).equals("C")) {
            isSorted = false;
        }
        if (!parray[3].toString().substring(0, 1).equals("D")) {
            isSorted = false;
        }
        if (!parray[4].toString().substring(0, 1).equals("E")) {
            isSorted = false;
        }
        if (!parray[5].toString().substring(0, 1).equals("G")) {
            isSorted = false;
        }
        if (!parray[6].toString().substring(0, 1).equals("H")) {
            isSorted = false;
        }

        return isSorted;
    }

    /**
     *  helper function to set up model and import contacts to sort

    public void setupModelWithImportedContacts() throws Exception {
        try {
        } catch (CommandException cr) {
            System.out.println("Failed in setupModelWithImportedContacts");
        }
        testModel = icc.model;
    }
    */

    @Test
    public void sortAddressBookCallingModel_functionCallWithImportedData_personsAreInCorrectOrder()
            throws Exception {
        ImportContactsCommand icc = new ImportContactsCommand("data/Test_contacts_unsorted.csv");
        icc.model = new ModelManager();
        icc.executeUndoableCommand();

        assertFalse(checkSorted(icc.model));
        icc.model.sortAddressBookAlphabeticallyByName();
        assertTrue(checkSorted(icc.model));
    }

    @Test
    public void executeUndoableCommand_sortImportedPersons_personsCorrectlySorted() throws Exception {
        ImportContactsCommand icc = new ImportContactsCommand("data/Test_contacts_unsorted.csv");
        icc.model = new ModelManager();

        try {
            icc.executeUndoableCommand();
        } catch (CommandException cr) {
            System.out.println("Failed in setupModelWithImportedContacts\n" + cr.getStackTrace());
        }

        SortCommand sc = new SortCommand();
        sc.model = icc.model;

        assertFalse(checkSorted(sc.model));
        CommandResult cr = sc.executeUndoableCommand();
        assertTrue(checkSorted(sc.model));
        assertEquals(cr.feedbackToUser, "Contacts successfully sorted alphabetically by name.");
    }

    @Test
    public void callSortParser_createAddressBookParser_returnSortCommand() throws ParseException {
        AddressBookParser abp = new AddressBookParser();
        Command sc1 = abp.parseCommand("sort");
        Command sc2 = abp.parseCommand("sort_by_name");

        requireNonNull(sc1);
        requireNonNull(sc2);
    }

}
//@@author
