package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Lists all residents in the address book to the user.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_SUCCESS = "Sorted all residents";
    public static final String MESSAGE_EMPTY = "No residents found; to add, use the ‘add’ command";
    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "Sort command not implemented yet";

    private final SortField sortField;

    public SortCommand(SortField sortField) {
        this.sortField = requireNonNull(sortField);

    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(MESSAGE_NOT_IMPLEMENTED_YET);
    }

    public enum SortField {
        NAME,
        PHONE,
        BLOCK
    }
}
