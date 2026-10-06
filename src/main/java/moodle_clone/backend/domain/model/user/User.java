package moodle_clone.backend.domain.model.user;

import moodle_clone.backend.domain.base.BaseEntity;
import moodle_clone.backend.domain.exception.user.InvalidUserException;
import moodle_clone.backend.domain.model.enums.user.Nationality;
import moodle_clone.backend.domain.model.enums.user.Roles;

import java.time.Instant;
import java.time.LocalDate;
import java.util.EnumSet;
import java.util.Set;
import java.util.UUID;

public class User extends BaseEntity {

    private String name;
    private Email email;
    private final Set<Roles> roles;
    private Phone phone;
    private LocalDate dateBirth;
    private Nationality nationality;
    private Address address;
    private boolean isActive;

    public User(UUID id, String name, Email email, Roles role) {
        super(id);
        this.name = name;
        this.email = email;
        this.roles = EnumSet.of(role);
        this.isActive = true;
    }

    private User(UUID id, Instant createdAt, Instant updatedAt, String name, Email email,
                 Set<Roles> roles, boolean isActive, LocalDate dateBirth,
                 Nationality nationality, Phone phone, Address address) {
        super(id, createdAt, updatedAt);
        this.name = name;
        this.email = email;
        this.roles = roles;
        this.isActive = isActive;
        this.dateBirth = dateBirth;
        this.nationality = nationality;
        this.phone = phone;
        this.address = address;
    }

    public static User reconstruct(UUID id, Instant createdAt, Instant updatedAt, String name, Email email,
                                   Set<Roles> roles, boolean isActive, LocalDate dateBirth,
                                   Nationality nationality, Phone phone, Address address) {
        return new User(id, createdAt, updatedAt, name, email, roles, isActive,
                dateBirth, nationality, phone, address);
    }

    public void addRole(Roles role) {
        this.roles.add(role);
        markUpdated();
    }

    public boolean hasRole(Roles role) {
        return this.roles.contains(role);
    }

    public void removeRole(Roles role) {
        if (this.roles.size() == 1 && this.roles.contains(role)) {
            throw new IllegalStateException("Usuário precisa ter ao menos um papel");
        }

        this.roles.remove(role);
        markUpdated();
    }

    public void updateName(String newName) {
        if (newName == null || newName.isBlank()) {
            throw new InvalidUserException("Nome não pode ser vazio");
        }

        this.name = newName;
        markUpdated();
    }

    public void updateEmail(Email newEmail) {
        this.email = newEmail;
        markUpdated();
    }

    public void updatePhone(Phone newPhone) {
        this.phone = newPhone;
        markUpdated();
    }

    public void updateDateBirth(LocalDate newDateBirth) {
        if (newDateBirth != null && newDateBirth.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data de nascimento inválida");
        }

        this.dateBirth = newDateBirth;
        markUpdated();
    }

    public void updateNationality(Nationality newNationality) {
        this.nationality = newNationality;
        markUpdated();
    }

    public void updateAddress(Address newAddress) {
        this.address = newAddress;
        markUpdated();
    }

    public void deactivate() {
        if (!this.isActive) {
            throw new IllegalStateException("Usuário já está inativo");
        }

        this.isActive = false;
        markUpdated();
    }

    public void activate() {
        if (this.isActive) {
            throw new IllegalStateException("Usuário já está ativo");
        }

        this.isActive = true;
        markUpdated();
    }

    public String getName() {
        return name;
    }
    public Email getEmail() {
        return email;
    }
    public boolean isActive() {
        return isActive;
    }
    public Phone getPhone() {
        return phone;
    }
    public LocalDate getDateBirth() {
        return dateBirth;
    }
    public Nationality getNationality() {
        return nationality;
    }
    public Address getAddress() {
        return address;
    }
    public Set<Roles> getRoles() {
        return Set.copyOf(roles);
    }
}