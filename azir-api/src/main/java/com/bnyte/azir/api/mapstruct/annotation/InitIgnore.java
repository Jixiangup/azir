package com.bnyte.azir.api.mapstruct.annotation;

import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author bnyte
 * @since 1.0.0
 */
@Retention(RetentionPolicy.CLASS)
@Mappings({
        @Mapping(target = "gmtCreate", ignore = true),
        @Mapping(target = "gmtModified", ignore = true),
        @Mapping(target = "deleted", ignore = true)
})
public @interface InitIgnore {
}
