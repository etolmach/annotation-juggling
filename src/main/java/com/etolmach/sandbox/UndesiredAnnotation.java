package com.etolmach.sandbox;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author etolmach
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface UndesiredAnnotation {
    String value();
}
