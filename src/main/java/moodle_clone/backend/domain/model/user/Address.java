package moodle_clone.backend.domain.model.user;

import moodle_clone.backend.domain.exception.user.InvalidUserException;

public final class Address {

    private final String street;
    private final String number;
    private final String city;
    private final String state;
    private final String zipCode;

    public Address(String street, String number, String city, String state, String zipCode) {

        if (street == null || street.isBlank()) {
            throw new InvalidUserException("Rua não pode ser vazia");
        }

        if (number == null || number.isBlank()) {
            throw new InvalidUserException("Número não pode ser vazio");
        }

        if (city == null || city.isBlank()) {
            throw new InvalidUserException("Cidade não pode ser vazia");
        }

        if (state == null || !state.matches("[A-Z]{2}$")) {
            throw new InvalidUserException("Estado inválido: " + state);
        }

        if (zipCode == null || !zipCode.matches("\\d{5}-?\\d{3}")) {
            throw new InvalidUserException("CEP inválido: " + zipCode);
        }

        this.street = street;
        this.number = number;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }
    public String getNumber() { return number; }
    public String getCity() { return city; }
    public String getState() {
        return state;
    }
    public String getZipCode() {
        return zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address that)) return false;
        return street.equals(that.street) && city.equals(that.city)
                && zipCode.equals(that.zipCode) && java.util.Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(street, number, city, zipCode);
    }
}