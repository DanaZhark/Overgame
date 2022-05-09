package com.zhandabo.overgame.util;

import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

@UtilityClass
public class SpecificationUtils {

    public <T> Specification<T> search(@Nullable String query, @Nullable String... fields) {
        if (fields == null || fields.length < 1) {
            throw new IllegalArgumentException("'fields' parameter can not be null or empty");
        }
        SpecificationBuilder<T> output = new SpecificationBuilder<>();
        if (query != null) {
            for (String str : fields) {
                output.or((root, qb, builder) -> builder.like(builder.lower(root.get(str).as(String.class)), "%" + query.toLowerCase() + "%"));
            }
        }
        return output;
    }
}
