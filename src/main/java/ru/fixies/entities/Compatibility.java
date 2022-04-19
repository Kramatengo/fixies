package ru.fixies.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "compatibility")
@Getter
@Setter
@NoArgsConstructor
@IdClass(CompatibilityId.class)
public class Compatibility {
    @Id
    private Long modelId;

    @Id
    private Long spareId;

    @Column(name = "used_by_default")
    private boolean usedByDefault;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Compatibility that)) return false;
        return usedByDefault == that.usedByDefault && modelId.equals(that.modelId) && spareId.equals(that.spareId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modelId, spareId, usedByDefault);
    }
}
