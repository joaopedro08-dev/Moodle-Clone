package moodle_clone.backend.domain.model.user;

import moodle_clone.backend.domain.exception.user.InvalidUserException;

public final class Phone {

    private final String number;

    public Phone(String number) {
        if (number == null || !number.matches("\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4}")) {
            throw new InvalidUserException("Telefone inválido: " + number);
        }
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Phone that)) return false;
        return number.equals(that.number);
    }

    @Override
    public int hashCode() { return number.hashCode(); }
}
