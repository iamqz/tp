package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.Model;
import seedu.address.model.resident.Resident;

/**
 * Copies all currently displayed residents to the clipboard.
 */
public class CopyCommand extends Command {

    public static final String COMMAND_WORD = "copy";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Copies all currently displayed residents to the clipboard.\n"
            + "Use 'find' command first to filter specific residents before copying.\n"
            + "Parameters: None\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_COPY_SUCCESS = "Copied %1$d resident(s) to clipboard";
    public static final String MESSAGE_COPY_EMPTY = "No residents to copy";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        List<Resident> residentsToDisplay = model.getFilteredResidentList();

        if (residentsToDisplay.isEmpty()) {
            return new CommandResult(MESSAGE_COPY_EMPTY);
        }

        String copyContent = formatResidentsForCopy(residentsToDisplay);

        return new CommandResult(
                String.format(MESSAGE_COPY_SUCCESS, residentsToDisplay.size()),
                false,
                false,
                copyContent
        );
    }

    /**
     * Formats the residents information for copying to clipboard.
     */
    private String formatResidentsForCopy(List<Resident> residents) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < residents.size(); i++) {
            Resident resident = residents.get(i);
            sb.append(String.format("Name: %s%n", resident.getName()));
            sb.append(String.format("Phone: %s%n", resident.getPhone()));
            sb.append(String.format("Unit Number: %s%n", resident.getUnitNumber()));
            sb.append(String.format("Role: %s", resident.getRole()));

            if (i < residents.size() - 1) {
                sb.append("\n\n");
            }
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object other) {
        // All CopyCommand instances are equal since they have no parameters
        return other instanceof CopyCommand;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .toString();
    }
}
