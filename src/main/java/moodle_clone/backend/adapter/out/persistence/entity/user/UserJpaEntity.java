package moodle_clone.backend.adapter.out.persistence.entity.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import moodle_clone.backend.adapter.out.persistence.entity.base.BaseJpaEntity;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "tb_users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserJpaEntity extends BaseJpaEntity {

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "email", length = 120, unique = true, nullable = false)
    private String email;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "tb_user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role", nullable = false)
    private Set<String> roles;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Column(name = "date_birth")
    private LocalDate dateBirth;

    @Column(name = "nationality", length = 40)
    private String nationality;

    @Column(name = "street", length = 150)
    private String street;

    @Column(name = "number", length = 20)
    private String number;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "state", length = 2)
    private String state;

    @Column(name = "zip_code", length = 9)
    private String zipCode;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;
}