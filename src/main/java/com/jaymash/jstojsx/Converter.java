package com.jaymash.jstojsx;

import java.io.File;

public class Converter {

    public void start(String rootPath, String deleteAfterRename) {
        File directory = new File(rootPath);

        if (directory.isDirectory()) {
            getFiles(directory, deleteAfterRename);
        }
    }

    private void getFiles(File directory, String deleteAfterRename) {
        File[] files = directory.listFiles();

        if (files == null) return;

        for (File file : files) {
            if (file.isDirectory()) {
                getFiles(file, deleteAfterRename);
            } else {
                String filename = file.getName();
                String[] parts = filename.split("\\.");
                String extension = parts.length > 1 ? parts[parts.length - 1] : "";

                // Rename only if it's likely to be a component with uppercase first character

                if (Character.isUpperCase(filename.charAt(0)) && extension.equalsIgnoreCase("js")) {
                    String path = file.getPath().replace(filename, parts[0] + ".jsx");
                    File destination = new File(path);

                    if (!destination.exists()) {
                        if (file.renameTo(destination)) {
                            System.out.println("Renamed file " + file.getPath() + " to " + destination.getPath());

                            if (deleteAfterRename.equals("1") || deleteAfterRename.equals("true")) {
                                file.delete();
                            }
                        }
                    }
                }
            }
        }
    }

}
