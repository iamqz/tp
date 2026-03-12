package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UNITNUMBER;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.model.resident.Resident;

/**
 * A utility class for Person.
 */
public class ResidentUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Resident resident) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(resident);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Resident resident) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + resident.getName().fullName + " ");
        sb.append(PREFIX_PHONE + resident.getPhone().value + " ");
        sb.append(PREFIX_UNITNUMBER + resident.getUnitNumber().value + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditCommand.EditResidentDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_UNITNUMBER).append(address.value).append(" "));
        return sb.toString();
    }
}
