package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_RESIDENTS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.resident.Remark;
import seedu.address.model.resident.Resident;


/**
 * Changes the remark of an existing Resident in the address book.
 */
public class RemarkCommand extends Command {

    public static final String COMMAND_WORD = "remark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the remark of the Resident identified "
            + "by the index number used in the last Resident listing. "
            + "Existing remark will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "r/ [REMARK]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "r/ Likes to swim.";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Remark: %2$s";
    public static final String MESSAGE_ADD_REMARK_SUCCESS = "Added remark to Resident: %1$s";
    public static final String MESSAGE_DELETE_REMARK_SUCCESS = "Removed remark from Resident: %1$s";

    private final Index index;
    private final Remark remark;

    /**
     * @param index of the Resident in the filtered Resident list to edit the remark
     * @param remark of the Resident to be updated to
     */
    public RemarkCommand(Index index, Remark remark) {
        requireAllNonNull(index, remark);

        this.index = index;
        this.remark = remark;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Resident> lastShownList = model.getFilteredResidentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_RESIDENT_DISPLAYED_INDEX);
        }

        Resident ResidentToEdit = lastShownList.get(index.getZeroBased());
        Resident editedResident = new Resident(
                ResidentToEdit.getName(), ResidentToEdit.getPhone(), ResidentToEdit.getEmail(),
                ResidentToEdit.getAddress(), ResidentToEdit.getTags(), remark);

        model.setResident(ResidentToEdit, editedResident);
        model.updateFilteredResidentList(PREDICATE_SHOW_ALL_RESIDENTS);

        return new CommandResult(generateSuccessMessage(editedResident));
    }

    /**
     * Generates a command execution success message based on whether
     * the remark is added to or removed from
     * {@code residentToEdit}.
     */
    private String generateSuccessMessage(Resident residentToEdit) {
        String message = !remark.value.isEmpty() ? MESSAGE_ADD_REMARK_SUCCESS : MESSAGE_DELETE_REMARK_SUCCESS;
        return String.format(message, Messages.format(residentToEdit));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemarkCommand)) {
            return false;
        }

        RemarkCommand e = (RemarkCommand) other;
        return index.equals(e.index)
                && remark.equals(e.remark);
    }
}
