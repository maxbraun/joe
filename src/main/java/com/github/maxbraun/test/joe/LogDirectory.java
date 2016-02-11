package com.github.maxbraun.test.joe;

import net.oneandone.sushi.fs.MkdirException;
import net.oneandone.sushi.fs.Node;
import net.oneandone.sushi.fs.World;

import java.io.IOException;

/**
 * Handle the naming and saving of taken screenshots
 */
public class LogDirectory {
    private static final String SCREENSHOT_DIR = "test-screenshots";

    private final Class<?> testClass;
    private final Node baseDirectory;
    private int counter = 1;

    public LogDirectory(Class<?> testClass, World world) {
        this.testClass = testClass;
        try {
            baseDirectory = world.guessProjectHome(Class.forName(testClass.getName())) .join("target", SCREENSHOT_DIR).mkdirsOpt();
        } catch (ClassNotFoundException | MkdirException e) {
            throw new RuntimeException("ToDo: Handle this", e);
        }
    }

    public void save(byte[] screenshot, String action, String method) {
        try {
            Node file = methodDirectory(method).join(filename(action, ".png"));
            file.writeBytes(screenshot);
        } catch (IOException e) {
            throw new RuntimeException("ToDo: Handle this", e);
        }
    }

    public void save(String json, String action, String method) {
        try {
            Node file = methodDirectory(method).join(filename(action, ".log"));
            file.writeString(json);
        } catch (IOException e) {
            throw new RuntimeException("ToDo: Handle this", e);
        }
    }

    private String filename(String suffix, String type) {
        StringBuilder filename = new StringBuilder();
        filename.append(counter);
        counter++;
        if (suffix != null && !suffix.isEmpty()) {
            filename.append("_").append(suffix);
        }
        filename.append(type);
        return filename.toString();
    }

    protected Node methodDirectory(String method) throws IOException {
        if (method != null) {
            if (method.contains("[") && method.contains("]")) {
                return methodWithParameters(method);
            }
            return classDirectory().join(method).mkdirsOpt();
        }
        return classDirectory();
    }

    private Node methodWithParameters(String method) throws IOException {
        String methodName = method.substring(0, method.indexOf('['));
        Node screenshotDir = classDirectory().join(methodName);

        int offset = 1;
        String unsplittedParameters = method.substring(method.indexOf('[') + offset, method.indexOf(']'));
        String[] parameters = unsplittedParameters.split("\\|");
        for (String parameter : parameters) {
            screenshotDir = screenshotDir.join(parameter);
        }
        return screenshotDir.mkdirsOpt();

    }

    private Node classDirectory() throws IOException {
        return baseDirectory.join(testClass.getSimpleName()).mkdirsOpt();
    }
}
