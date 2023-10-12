package com.basejava.storege;

import com.basejava.exception.StorageException;
import com.basejava.model.Resume;
import com.basejava.storege.serializer.StreamSerializer;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FileStorage extends AbstractStorage <File> {
    private final File directory;
    private final StreamSerializer streamSerializer;

    protected FileStorage(File directory, StreamSerializer streamSerializer) {
        Objects.requireNonNull(directory, "directory must not be null");

        this.streamSerializer = streamSerializer;
        if (! directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + "is not directory");
        }
        if (! directory.canRead() || ! directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + "is not readable/writable");
        }
        this.directory = directory;
    }

    @Override
    protected List <Resume> doCopyAll() {
        List <File> files = listFilesInDirectory();
        List <Resume> list = new ArrayList <>(files.size());
        for (File file : files) {
            list.add(doGet(file));
        }
        return list;
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void doUpdate(Resume r, File file) {
        try {
            streamSerializer.doWrite(r, new BufferedOutputStream(new FileOutputStream(file)));
        } catch(IOException e) {
            throw new StorageException("File write error", r.getUuid(), e);
        }
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected void doSave(Resume r, File file) {
        try {
            file.createNewFile();
        } catch(IOException e) {
            throw new StorageException("Couldn't create file " + file.getAbsolutePath(), file.getName(), e);
        }
        doUpdate(r, file);
    }

    @Override
    protected Resume doGet(File file) {
        try {
            return streamSerializer.doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch(IOException e) {
            throw new StorageException("File read error", file.getName(), e);
        }
    }

    @Override
    protected void doDelete(File file) {
        if (! file.delete()) {
            throw new StorageException("File delete error", file.getName());
        }
    }

    @Override
    public void clear() {
        List <File> files = listFilesInDirectory();
        for (File file : files) {
            doDelete(file);
        }
    }

    @Override
    public int size() {
        List <File> files = listFilesInDirectory();
        return files.size();
    }

    private List <File> listFilesInDirectory() {
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("Directory read error");
        }
        return Arrays.asList(files);
    }
}
