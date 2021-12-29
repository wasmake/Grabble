package me.makecode.grabble.cache.data;

@FunctionalInterface
public interface DataLoadStrategy {
    Object execute(Object object);
}