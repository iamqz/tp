package seedu.address.model.resident;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_BOB;
import static seedu.address.testutil.TypicalResidents.ALICE;
import static seedu.address.testutil.TypicalResidents.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ResidentBuilder;

public class ResidentTest {

    @Test
    public void isSameResident() {
        // same object -> returns true
        assertTrue(ALICE.isSameResident(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameResident(null));

        // same name, all other attributes different -> returns false
        // Possible to have 2 resident with the same name
        Resident editedAlice = new ResidentBuilder(ALICE).withPhone(VALID_PHONE_BOB)
                .withUnitNumber(VALID_ADDRESS_BOB).withRole(VALID_ROLE_BOB).build();
        assertFalse(ALICE.isSameResident(editedAlice));

        // different name, all other attributes same -> returns false
        // False; since it is impossible to have 2 different resident (same name) with the same phone number.
        editedAlice = new ResidentBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameResident(editedAlice));

        // name differs in case, all other attributes same -> returns true
        // ResidentBuilder will ensure no trailing space and capitalised first letter of every word in name.
        Resident editedBob = new ResidentBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertTrue(BOB.isSameResident(editedBob));

        // name has trailing spaces, all other attributes same -> returns true
        // ResidentBuilder will ensure no trailing space and capitalised first letter of every word in name.
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new ResidentBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertTrue(BOB.isSameResident(editedBob));

        // different phone, all other attributes same -> returns false
        // False; possible to have different phone with same name
        // While same unitNumber (possible for double room; Not the main concern since testing for same resident)
        editedAlice = new ResidentBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.isSameResident(editedAlice));

        // phone has trailing spaces, all other attributes same -> returns true
        // ResidentBuilder will ensure no trailing space in phone.
        String phoneWithTrailingSpaces = VALID_PHONE_BOB + " ";
        editedBob = new ResidentBuilder(BOB).withPhone(phoneWithTrailingSpaces).build();
        assertTrue(BOB.isSameResident(editedBob));

        // unitNumber has trailing spaces, all other attributes same -> returns true
        // ResidentBuilder will ensure no trailing space in unitNumber.
        String unitNumberWithTrailingSpaces = VALID_ADDRESS_BOB + " ";
        editedBob = new ResidentBuilder(BOB).withUnitNumber(unitNumberWithTrailingSpaces).build();
        assertTrue(BOB.isSameResident(editedBob));

        // same ALICE, but different role -> should returns true (same resident)
        Resident editedAlice1 = new ResidentBuilder(ALICE).withRole(VALID_ROLE_BOB).build();
        assertTrue(ALICE.isSameResident(editedAlice1));

        // Same name, same phone, different attributes (Unit/Role) -> returns true
        Resident editedAlice2 = new ResidentBuilder(ALICE)
                .withUnitNumber(VALID_ADDRESS_BOB).withRole(VALID_ROLE_BOB).build();
        assertTrue(ALICE.isSameResident(editedAlice2));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Resident aliceCopy = new ResidentBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different person -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Resident editedAlice = new ResidentBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new ResidentBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new ResidentBuilder(ALICE).withUnitNumber(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different role -> returns false
        editedAlice = new ResidentBuilder(ALICE).withRole(VALID_ROLE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

    }

    @Test
    public void toStringMethod() {
        String expected = Resident.class.getCanonicalName() + "{name=" + ALICE.getName() + ", phone=" + ALICE.getPhone()
                + ", unitNumber=" + ALICE.getUnitNumber() + ", role=" + ALICE.getRole() + "}";
        assertEquals(expected, ALICE.toString());
    }
}
