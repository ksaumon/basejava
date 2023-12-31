package com.basejava.storege;

import com.basejava.exception.StorageException;
import com.basejava.model.Resume;
import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {
    protected AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test
    public void saveOverflow() {
        try {
            storage.clear();
            for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("Name" + i));
            }
        } catch(StorageException e) {
            Assert.fail();
        }
        storage.save(new Resume("Overflow"));
    }
}