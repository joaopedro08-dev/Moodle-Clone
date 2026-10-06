package moodle_clone.backend.domain.model.user;

import moodle_clone.backend.domain.exception.user.InvalidUserException;

public final class Email {
    private final String value;

    public Email(String value) {
        if (value == null || !value.matches("^[\\w.+-]+@[\\w-]+\\.[a-zA-Z]{2,}$")) {
            throw new InvalidUserException(value);
        }
        this.value = value;
    }

    public String getValue() { return value; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Email)) return false;
        return value.equals(((Email) o).value);
    }

    @Override
    public int hashCode() { return value.hashCode(); }
}