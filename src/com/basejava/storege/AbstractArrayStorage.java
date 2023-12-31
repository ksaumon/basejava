package com.basejava.storege;

import com.basejava.exception.StorageException;
import com.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage <Integer> {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public final int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected void doUpdate(Resume r, Integer index) {
        storage[index] = r;
    }

    @Override
    protected void doSave(Resume r, Integer index) {
        if (size >= storage.length) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            insertResume(r, index);
            size++;
        }
    }

    @Override
    protected void doDelete(Integer index) {
        deleteResume(index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    public List <Resume> doCopyAll() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, size));
    }

    @Override
    protected Resume doGet(Integer index) {
        return storage[index];
    }

    @Override
    protected boolean isExist(Integer index) {
        return index >= 0;
    }

    protected abstract void insertResume(Resume r, int index);

    protected abstract void deleteResume(int index);

    protected abstract Integer getSearchKey(String uuid);
}
