package com.basejava.storege;

import com.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void update(Resume r) {

    }

    @Override
    protected void insertIndexR(Resume r, int index) {
        int insertIndex = -index - 1;
        System.arraycopy(storage, index, storage, index + 1, insertIndex);
        storage[insertIndex] = r;
    }

    @Override
    protected void insertDeletedIndexR(int index) {
        int numberIndexMove = size - index - 1;
        if (numberIndexMove > 0) {
            System.arraycopy(storage, index + 1, storage, index, numberIndexMove);
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
