package com.basejava.storege;

import com.basejava.exception.NotExistStorageException;
import com.basejava.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {
    private Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() {
    }

    @Test
    public void save() {
        storage.save(new Resume(UUID_4));
        Assert.assertEquals(4, storage.size());
    }

    @Test
    public void delete() {
        storage.delete(new String(UUID_2));
        Assert.assertEquals(2, storage.size());

    }

    @Test
    public void getAll() {
        Resume[] arr = storage.getAll();
        Assert.assertEquals(new Resume(UUID_1), arr[0]);
        Assert.assertEquals(new Resume(UUID_2), arr[1]);
        Assert.assertEquals(new Resume(UUID_3), arr[2]);
    }

    @Test
    public void get() {
        ToHelpGet(new Resume(UUID_1));
        ToHelpGet(new Resume(UUID_2));
        ToHelpGet(new Resume(UUID_3));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    public void ToHelpGet(Resume r) {
        Assert.assertEquals(r, storage.get(r.getUuid()));
    }
}