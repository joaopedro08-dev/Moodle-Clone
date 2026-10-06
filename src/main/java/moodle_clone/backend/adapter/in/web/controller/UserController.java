package moodle_clone.backend.adapter.in.web.controller;

import jakarta.validation.Valid;
import moodle_clone.backend.adapter.in.web.dto.user.*;
import moodle_clone.backend.application.port.in.user.*;
import moodle_clone.backend.application.readmodel.user.UserSummaryReadModel;
import moodle_clone.backend.application.usecase.user.*;
import moodle_clone.backend.domain.model.enums.user.Roles;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final ActivateUserUseCase activateUserUseCase;
    private final DeactivateUserUseCase deactivateUserUseCase;
    private final FindUserByIdUseCase findUserByIdUseCase;
    private final ListUsersUseCase listUsersUseCase;
    private final UpdateUserAddressUseCase updateUserAddressUseCase;
    private final UpdateUserPhoneUseCase updateUserPhoneUseCase;
    private final UpdateUserNationalityUseCase updateUserNationalityUseCase;
    private final UpdateUserDateBirthUseCase updateUserDateBirthUseCase;
    private final AddRoleToUserUseCase addRoleToUserUseCase;
    private final RemoveRoleFromUserUseCase removeRoleFromUserUseCase;
    private final UpdateUserNameUseCase updateUserNameUseCase;
    private final UpdateUserEmailUseCase updateUserEmailUseCase;

    public UserController(CreateUserUseCase createUserUseCase,
                          ActivateUserUseCase activateUserUseCase,
                          DeactivateUserUseCase deactivateUserUseCase,
                          FindUserByIdUseCase findUserByIdUseCase,
                          ListUsersUseCase listUsersUseCase, UpdateUserAddressUseCase updateUserAddressUseCase, UpdateUserPhoneUseCase updateUserPhoneUseCase, UpdateUserNationalityUseCase updateUserNationalityUseCase, UpdateUserDateBirthUseCase updateUserDateBirthUseCase, AddRoleToUserUseCase addRoleToUserUseCase, RemoveRoleFromUserUseCase removeRoleFromUserUseCase, UpdateUserNameUseCase updateUserNameUseCase, UpdateUserEmailUseCase updateUserEmailUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.activateUserUseCase = activateUserUseCase;
        this.deactivateUserUseCase = deactivateUserUseCase;
        this.findUserByIdUseCase = findUserByIdUseCase;
        this.listUsersUseCase = listUsersUseCase;
        this.updateUserAddressUseCase = updateUserAddressUseCase;
        this.updateUserPhoneUseCase = updateUserPhoneUseCase;
        this.updateUserNationalityUseCase = updateUserNationalityUseCase;
        this.updateUserDateBirthUseCase = updateUserDateBirthUseCase;
        this.addRoleToUserUseCase = addRoleToUserUseCase;
        this.removeRoleFromUserUseCase = removeRoleFromUserUseCase;
        this.updateUserNameUseCase = updateUserNameUseCase;
        this.updateUserEmailUseCase = updateUserEmailUseCase;
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody CreateUserRequest request) {
        var command = new CreateUserCommand(request.name(), request.email(), request.role());
        var user = createUserUseCase.execute(command);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new UserResponse(user.getId(), user.getName(), user.getEmail().getValue(),
                        user.getRoles().stream().map(Enum::name).collect(Collectors.toSet()),
                        user.isActive(), user.getDateBirth()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable UUID id) {
        var readModel = findUserByIdUseCase.execute(id);
        return ResponseEntity.ok(UserResponse.fromReadModel(readModel));
    }

    @GetMapping
    public ResponseEntity<List<UserSummaryResponse>> list() {
        List<UserSummaryReadModel> summaries = listUsersUseCase.execute();
        return ResponseEntity.ok(summaries.stream().map(UserSummaryResponse::fromReadModel)
                .toList());
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<Void> activate(@PathVariable UUID id) {
        activateUserUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable UUID id) {
        deactivateUserUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/name")
    public ResponseEntity<Void> updateName(@PathVariable UUID id, @Valid @RequestBody UpdateNameRequest request) {
        var command = new UpdateNameCommand(id, request.name());
        updateUserNameUseCase.execute(command);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/email")
    public ResponseEntity<Void> updateEmail(@PathVariable UUID id, @Valid @RequestBody UpdateEmailRequest request) {
        var command = new UpdateEmailCommand(id, request.email());
        updateUserEmailUseCase.execute(command);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/address")
    public ResponseEntity<Void> updateAddress(@PathVariable UUID id, @Valid @RequestBody UpdateAddressRequest request) {
        var command = new UpdateAddressCommand(id, request.street(), request.number(),
                request.city(), request.state(), request.zipCode());
        updateUserAddressUseCase.execute(command);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/phone")
    public ResponseEntity<Void> updatePhone(@PathVariable UUID id, @Valid @RequestBody UpdatePhoneRequest request) {
        var command = new UpdatePhoneCommand(id, request.phoneNumber());
        updateUserPhoneUseCase.execute(command);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/nationality")
    public ResponseEntity<Void> updateNationality(@PathVariable UUID id, @Valid @RequestBody UpdateNationalityRequest request) {
        var command = new UpdateNationalityCommand(id, request.nationality());
        updateUserNationalityUseCase.execute(command);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/date-birth")
    public ResponseEntity<Void> updateDateBirth(@PathVariable UUID id, @Valid @RequestBody UpdateDateBirthRequest request) {
        var command = new UpdateDateBirthCommand(id, request.dateBirth());
        updateUserDateBirthUseCase.execute(command);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/roles")
    public ResponseEntity<Void> addRole(@PathVariable UUID id, @Valid @RequestBody RoleRequest request) {
        var command = new AddRoleCommand(id, request.role());
        addRoleToUserUseCase.execute(command);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/roles/{role}")
    public ResponseEntity<Void> removeRole(@PathVariable UUID id, @PathVariable Roles role) {
        var command = new AddRoleCommand(id, role);
        removeRoleFromUserUseCase.execute(command);
        return ResponseEntity.noContent().build();
    }
}