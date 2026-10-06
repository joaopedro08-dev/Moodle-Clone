package moodle_clone.backend.domain.user;

import moodle_clone.backend.domain.exception.user.InvalidUserException;
import moodle_clone.backend.domain.model.user.Address;
import moodle_clone.backend.domain.model.user.Email;
import moodle_clone.backend.domain.model.user.Phone;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ValueObjectTest {

    @Test
    void shouldCreateValidEmailAndPhone() {
        Email email = new Email("user.name@example.com");
        Phone phone = new Phone("(11) 99999-9999");

        assertEquals("user.name@example.com", email.getValue());
        assertEquals("(11) 99999-9999", phone.getNumber());
    }

    @Test
    void shouldRejectInvalidEmailAndPhone() {
        assertThrows(InvalidUserException.class, () -> new Email("invalid"));
        assertThrows(InvalidUserException.class, () -> new Phone("123"));
    }

    @Test
    void shouldCompareEqualValueObjectsByValue() {
        assertEquals(new Email("user@example.com"), new Email("user@example.com"));
        assertEquals(new Phone("11999999999"), new Phone("11999999999"));
        assertEquals(
                new Address("Rua A", "10", "São Paulo", "SP", "01000-000"),
                new Address("Rua A", "10", "São Paulo", "SP", "01000-000")
        );
    }

    @Test
    void shouldRejectInvalidAddress() {
        assertThrows(InvalidUserException.class,
                () -> new Address("Rua A", "", "São Paulo", "SP", "01000-000"));
        assertThrows(InvalidUserException.class,
                () -> new Address("Rua A", "10", "", "SP", "01000-000"));
        assertThrows(InvalidUserException.class,
                () -> new Address("Rua A", "10", "São Paulo", "S", "01000-000"));
        assertThrows(InvalidUserException.class,
                () -> new Address("Rua A", "10", "São Paulo", "SP", "000"));
    }
}
