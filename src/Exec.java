//> using lib "org.graalvm.nativeimage:svm:22.0.0.2"

package io.github.alexarchambault.exec;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public final class Exec {

  public static boolean available() {
    return false;
  }

  public static String[] currentEnv() {
    Map<String, String> env = System.getenv();
    ArrayList<String> b = new ArrayList<>(env.size());
    for (Map.Entry<String, String> e: env.entrySet()) {
      String value = e.getKey() + "=" + e.getValue();
      b.add(value);
    }
    Collections.sort(b);
    return b.toArray(new String[0]);
  }

  public static String defaultPath(String[] command) {
    if (command.length == 0)
      throw new IllegalArgumentException("command cannot be empty");
    String[] components = command[0].split("/");
    return components[components.length - 1];
  }

  public static void execve(
    String[] command
  ) throws ErrnoException {
    callExecve(defaultPath(command), command, currentEnv());
  }

  public static void execve(
    String[] command,
    String[] env
  ) throws ErrnoException {
    callExecve(defaultPath(command), command, env);
  }

  public static void execve(
    String[] command,
    String[] env,
    File cwd,
    String path
  ) throws ErrnoException {
    if (cwd != null) {
      Chdir.chdir(cwd.toString());
    }
    String path0 = path;
    if (path0 == null)
      path0 = defaultPath(command);
    callExecve(path, command, env);
  }

  static void callExecve(String path, String[] command, String[] env) throws ErrnoException {
    // Not supported on the JVM, returning immediately
  }

}
