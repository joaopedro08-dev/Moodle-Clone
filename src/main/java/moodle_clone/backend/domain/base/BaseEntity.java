package moodle_clone.backend.domain.base;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public abstract class BaseEntity {

    private final UUID id;
    private final Instant createdAt;
    private Instant updatedAt;

    protected BaseEntity(UUID id) {
        this.id = Objects.requireNonNull(id, "id não pode ser nulo");
        this.createdAt = Instant.now();
        this.updatedAt = this.createdAt;
    }

    protected BaseEntity(UUID id, Instant createdAt, Instant updatedAt) {
        this.id = Objects.requireNonNull(id);
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    protected void markUpdated() {
        this.updatedAt = Instant.now();
    }

    public UUID getId() { return id; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseEntity that)) return false;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}