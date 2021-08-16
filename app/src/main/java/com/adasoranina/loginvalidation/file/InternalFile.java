package com.adasoranina.loginvalidation.file;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

// location data/data/packageName/
public abstract class InternalFile {

    private final Context context;
    protected static final String FILENAME = "LOGIN";

    public InternalFile(Context context) {
        this.context = context;
    }

    protected void saveFile(
            String fileName,
            String contentFile,
            InternalFileCallback callback) {
        File file = new File(context.getFilesDir(), fileName);

        try {
            boolean createdFile = file.createNewFile();

            if (createdFile) {
                FileOutputStream outputStream = new FileOutputStream(file, false);
                outputStream.write(contentFile.getBytes());
                outputStream.flush();
                outputStream.close();
            }

            callback.state(StateFile.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            callback.state(StateFile.ERROR);
        }

    }

    protected void readFile(
            String fileName,
            ConsumeResultCallback<String> resultCallback) {
        File path = getContext().getFilesDir();
        File file = new File(path, fileName);

        if (!file.exists()) {
            resultCallback.result(null);
            return;
        }

        StringBuilder text = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                text.append(line);
            }

            resultCallback.result(text.toString());
        } catch (Exception e) {
            e.printStackTrace();
            resultCallback.result(null);
        }
    }

    protected Context getContext() {
        return this.context;
    }

    public static boolean getIsLogin(Context context) {
        File path = context.getFilesDir();
        File file = new File(path, FILENAME);
        return file.exists();
    }

    public static boolean deleteLoginFile(Context context) {
        File path = context.getFilesDir();
        File file = new File(path, InternalFile.FILENAME);

        if (file.exists()) {
            return file.delete();
        }

        return false;
    }

    public static boolean deleteLoginFile(String username, Context context) {
        File path = context.getFilesDir();
        File file = new File(path, username);

        if (file.exists()) {
            return file.delete();
        }

        return false;
    }

}
