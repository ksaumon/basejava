package com.basejava.storege;

import com.basejava.exception.ExistStorageException;
import com.basejava.exception.NotExistStorageException;
import com.basejava.exception.StorageException;
import com.basejava.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public abstract class AbstractArrayStorageTest {
    private final Storage STORAGE;
    private static final String UUID_NOT_EXIST = "dummy";
    private static final String UUID_1 = "uuid1";
    private static final Resume R_1 = new Resume(UUID_1);

    private static final String UUID_2 = "uuid2";
    private static final Resume R_2 = new Resume(UUID_2);

    private static final String UUID_3 = "uuid3";
    private static final Resume R_3 = new Resume(UUID_3);

    private static final String UUID_4 = "uuid4";
    private static final Resume R_4 = new Resume(UUID_4);


    protected AbstractArrayStorageTest(Storage storage) {
        this.STORAGE = storage;
    }

    @Before
    public void setUp() {
        STORAGE.clear();
        STORAGE.save(R_1);
        STORAGE.save(R_2);
        STORAGE.save(R_3);
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test
    public void clear() {
        STORAGE.clear();
        assertSize(0);
    }

    @Test
    public void update() {
        Resume newR = new Resume(UUID_1);
        STORAGE.update(newR);
        assertEquals(newR, STORAGE.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        STORAGE.get(UUID_NOT_EXIST);
    }

    @Test
    public void save() {
        STORAGE.save(R_4);
        assertSize(4);
        assertGet(R_4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        STORAGE.save(R_1);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        try {
            for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                STORAGE.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail();
        }
        STORAGE.save(new Resume());
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        STORAGE.delete(UUID_1);
        assertSize(2);
        STORAGE.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        STORAGE.delete(UUID_NOT_EXIST);
    }

    @Test
    public void getAll() {
        Resume[] actual = STORAGE.getAll();
        assertEquals(R_1, actual[0]);
        assertEquals(R_2, actual[1]);
        assertEquals(R_3, actual[2]);
    }

    @Test
    public void get() {
        assertGet(R_1);
        assertGet(R_2);
        assertGet(R_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        STORAGE.get(UUID_NOT_EXIST);
    }

    private void assertGet(Resume r) {
        assertEquals(r, STORAGE.get(r.getUuid()));
    }

    private void assertSize(int size) {
        assertEquals(size, STORAGE.size());
    }
}