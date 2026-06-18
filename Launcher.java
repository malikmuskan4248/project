package com.schemefinder;

/**
 * Launcher class needed when creating a fat JAR with JavaFX.
 * This avoids the "JavaFX runtime components are missing" error.
 */
public class Launcher {
    public static void main(String[] args) {
        MainApp.main(args);
    }
}
