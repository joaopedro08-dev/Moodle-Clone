package moodle_clone.backend.domain.user;

import moodle_clone.backend.domain.exception.user.InvalidUserException;
import moodle_clone.backend.domain.model.enums.user.Nationality;
import moodle_clone.backend.domain.model.enums.user.Roles;
import moodle_clone.backend.domain.model.user.Address;
import moodle_clone.backend.domain.model.user.Email;
import moodle_clone.backend.domain.model.user.Phone;
import moodle_clone.backend.domain.model.user.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserTest {

    @Test
    void shouldCreateUserWithInitialRoleAndActiveStatus() {
        User user = user();

        assertEquals("Maria", user.getName());
        assertEquals(new Email("maria@example.com"), user.getEmail());
        assertTrue(user.hasRole(Roles.STUDENT));
        assertTrue(user.isActive());
    }

    @Test
    void shouldManageRolesWithoutRemovingLastRole() {
        User user = user();
        user.addRole(Roles.TEACHER);

        user.removeRole(Roles.STUDENT);

        assertFalse(user.hasRole(Roles.STUDENT));
        assertTrue(user.hasRole(Roles.TEACHER));
        assertThrows(IllegalStateException.class, () -> user.removeRole(Roles.TEACHER));
    }

    @Test
    void shouldNotExposeMutableRoles() {
        User user = user();

        assertThrows(UnsupportedOperationException.class,
                () -> user.getRoles().remove(Roles.STUDENT));
    }

    @Test
    void shouldRejectBlankName() {
        User user = user();

        assertThrows(InvalidUserException.class, () -> user.updateName(" "));
    }

    @Test
    void shouldUpdatePersonalInformation() {
        User user = user();
        Email email = new Email("ana@example.com");
        Phone phone = new Phone("(11) 99999-9999");
        Address address = new Address("Rua A", "10", "São Paulo", "SP", "01000-000");
        LocalDate birthDate = LocalDate.of(1990, 1, 1);

        user.updateName("Ana");
        user.updateEmail(email);
        user.updatePhone(phone);
        user.updateDateBirth(birthDate);
        user.updateNationality(Nationality.BRAZILIAN);
        user.updateAddress(address);

        assertEquals("Ana", user.getName());
        assertEquals(email, user.getEmail());
        assertEquals(phone, user.getPhone());
        assertEquals(birthDate, user.getDateBirth());
        assertEquals(Nationality.BRAZILIAN, user.getNationality());
        assertEquals(address, user.getAddress());
    }

    @Test
    void shouldRejectFutureBirthDate() {
        User user = user();

        assertThrows(IllegalArgumentException.class,
                () -> user.updateDateBirth(LocalDate.now().plusDays(1)));
    }

    @Test
    void shouldActivateAndDeactivateUserOnlyOncePerState() {
        User user = user();

        user.deactivate();
        assertFalse(user.isActive());
        assertThrows(IllegalStateException.class, user::deactivate);

        user.activate();
        assertTrue(user.isActive());
        assertThrows(IllegalStateException.class, user::activate);
    }

    private User user() {
        return new User(UUID.randomUUID(), "Maria", new Email("maria@example.com"), Roles.STUDENT);
    }
}