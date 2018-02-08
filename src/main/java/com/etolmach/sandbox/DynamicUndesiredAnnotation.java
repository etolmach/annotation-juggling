package com.etolmach.sandbox;

import java.lang.annotation.Annotation;

/**
 * @author etolmach
 */
public class DynamicUndesiredAnnotation implements UndesiredAnnotation {

    private final String value;

    public DynamicUndesiredAnnotation(String value) {
        this.value = value;
    }

    @Override
    public String value() {
        return value;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return DynamicUndesiredAnnotation.class;
    }
}
