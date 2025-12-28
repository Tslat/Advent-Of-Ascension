package net.tslat.aoa3.library.object.interfaces;

@FunctionalInterface
public interface ToFloatFunction<T> {
    float apply(T input);
}
