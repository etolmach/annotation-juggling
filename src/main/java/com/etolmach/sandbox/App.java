package com.etolmach.sandbox;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

public class App {

    public static final String ANNOTATIONS = "annotations";
    public static final String ANNOTATION_DATA = "annotationData";

    public static void main(String[] args) throws ReflectiveOperationException {
        System.out.println("isAnnotationPresent() ? " + MyClass.class.isAnnotationPresent(UndesiredAnnotation.class));
        System.out.println("annotation: " + MyClass.class.getAnnotation(UndesiredAnnotation.class));
        System.out.println("value: " + MyClass.class.getAnnotation(UndesiredAnnotation.class).value() + "\n");

        updateAnnotation(MyClass.class, UndesiredAnnotation.class, new DynamicUndesiredAnnotation("anotherValue"){});

        System.out.println("isAnnotationPresent() ? " + MyClass.class.isAnnotationPresent(UndesiredAnnotation.class));
        System.out.println("annotation: " + MyClass.class.getAnnotation(UndesiredAnnotation.class));
        System.out.println("value: " + MyClass.class.getAnnotation(UndesiredAnnotation.class).value() + "\n");

        updateAnnotation(MyClass.class, UndesiredAnnotation.class, null);

        System.out.println("isAnnotationPresent() ? " + MyClass.class.isAnnotationPresent(UndesiredAnnotation.class));
        System.out.println("annotation: " + MyClass.class.getAnnotation(UndesiredAnnotation.class));
    }

    private static void updateAnnotation(Class<?> targetClass, Class<? extends Annotation> targetAnnotation, Annotation newAnnotation) throws ReflectiveOperationException {
        Method method = Class.class.getDeclaredMethod(ANNOTATION_DATA);
        method.setAccessible(true);

        Object annotationData = method.invoke(targetClass);

        Field annotations = annotationData.getClass().getDeclaredField(ANNOTATIONS);
        annotations.setAccessible(true);

        Map<Class<? extends Annotation>, Annotation> map = (Map<Class<? extends Annotation>, Annotation>) annotations.get(annotationData);
        map.put(targetAnnotation, newAnnotation);
    }

}
