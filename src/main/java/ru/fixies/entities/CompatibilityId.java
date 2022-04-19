package ru.fixies.entities;

import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
public class CompatibilityId implements Serializable {
    private Long modelId;
    private Long spareId;

    public CompatibilityId(Long modelId, Long spareId) {
        this.modelId = modelId;
        this.spareId = spareId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompatibilityId that)) return false;
        return modelId.equals(that.modelId) && spareId.equals(that.spareId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modelId, spareId);
    }
}
