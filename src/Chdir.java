package io.github.alexarchambault.exec;

public final class Chdir {

  public static boolean available() {
    return false;
  }

  public static void chdir(String path) throws ErrnoException {
    // Not supported on the JVM, returning immediately
  }

}