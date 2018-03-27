package seedu.address.logic.commands;

import com.google.api.services.gmail.Gmail;

import javafx.collections.ObservableList;

import seedu.address.commons.util.GmailUtil;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.email.Template;

//@@author ng95junwei

/**
 * Finds and emails all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class EmailCommand extends Command {

    public static final String COMMAND_WORD = "email";
    public static final String COMMAND_ALIAS = "em";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Emails all persons whose names matches any of "
            + "the specified keywords (case-insensitive) "
            + "and displays them as a list with index numbers.\n"
            + "Parameters: NAME TEMPLATE\n"
            + "Example: " + COMMAND_WORD + " alice coldemail";

    private final NameContainsKeywordsPredicate predicate;
    private final String search;

    public EmailCommand(NameContainsKeywordsPredicate predicate, String search) {

        this.predicate = predicate;
        this.search = search;
    }

    @Override
    public CommandResult execute() {
        // Build a new authorized API client service.

        model.updateFilteredPersonList(predicate);
        ObservableList<Person> emailList = model.getFilteredPersonList();
        Template template = model.selectTemplate(this.search);
        for (Person p : emailList) {
            try {
                GmailUtil handler = new GmailUtil();
                Gmail service = handler.getService();
                handler.send(service, p.getEmail().toString(), "",
                        service.users().getProfile("me").getUserId(), template);
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("Some IOException occurred");
            }
        }
        return new CommandResult(getMessageForPersonListShownSummary(model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EmailCommand // instanceof handles nulls
                && this.predicate.equals(((EmailCommand) other).predicate)); // state check
    }
}



