package com.basejava.storege;

import com.basejava.Config;
import com.basejava.exception.ExistStorageException;
import com.basejava.exception.NotExistStorageException;
import com.basejava.model.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = Config.get().getStorageDir();
    protected final Storage storage;
    private static final String UUID_NOT_EXIST = "dummy";
    private static final String UUID_1 = UUID.randomUUID().toString();
    private static final String UUID_2 = UUID.randomUUID().toString();
    private static final String UUID_3 = UUID.randomUUID().toString();
    private static final String UUID_4 = UUID.randomUUID().toString();

    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
    private static final Resume RESUME_4;

    static {
        RESUME_1 = new Resume(UUID_1, "Name1");
        RESUME_2 = new Resume(UUID_2, "Name2");
        RESUME_3 = new Resume(UUID_3, "Name3");
        RESUME_4 = new Resume(UUID_4, "Name4");
        //  createAndFillResume("uuid1", "Name1");
    }

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    protected static void createAndFillResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
        List <Organization> organizations = new ArrayList <>();
        resume.addContact(ContactType.PHONE, "+7(921) 855-0482");
        resume.addContact(ContactType.HOME_PHONE, "+7(345) 855-0482");
        resume.addContact(ContactType.MOBILE, "+7(921) 855-0482");
        resume.addContact(ContactType.SKYPE, "john.doe@example.com");
        resume.addContact(ContactType.MAIL, "john.doe@example.com");
        resume.addContact(ContactType.STATCKOVERFLOW, "john.doe@example.com");
        resume.addContact(ContactType.HOME_PAGE, "john.doe@example.com");
        resume.addContact(ContactType.LINKEDIN, "linkedin.com/in/johndoe");
        resume.addContact(ContactType.GITHUB, "github.com/johndoe");
        resume.addSection(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения"));
        resume.addSection(SectionType.PERSONAL, new TextSection("Аналитический склад ума"));
        resume.addSection(SectionType.ACHIEVEMENT, new TextSection("Организация команды"));
        resume.addSection(SectionType.QUALIFICATIONS, new TextSection("JEE AS: GlassFish"));
        organizations.add(new Organization("Wrike", "https://www.wrike.com/",
                List.of(new Period(2014, Month.OCTOBER, 2016, Month.JANUARY, "Старший разработчик" +
                        "(backend)", "Проектирование и разработка онлайн платформы управления проектами"))));
        resume.addSection(SectionType.EXPERIENCE, new OrganizationSection(organizations));
        organizations.add(new Organization("Coursera", "https://www.coursera.org/course/progfun",
                List.of(new Period(2013, Month.MARCH, 2013, Month.MAY, "'Functional Programming" +
                        "Principles in Scala' by Martin Odersky", ""))));
        resume.addSection(SectionType.EDUCATION, new OrganizationSection(organizations));
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void update() {
        Resume newResume = new Resume(UUID_1, "New Name");
        storage.update(newResume);
        assertEquals(newResume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.get(UUID_NOT_EXIST);
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        assertSize(2);
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID_NOT_EXIST);
    }

    @Test
    public void getAllSorted() {
        List <Resume> list = storage.getAllSorted();
        assertEquals(3, list.size());
        assertEquals(list, Arrays.asList(RESUME_1, RESUME_2, RESUME_3));
    }

    @Test
    public void get() {
        for (Resume resume : Arrays.asList(RESUME_1, RESUME_2, RESUME_3)) {
            assertGet(resume);
        }
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUID_NOT_EXIST);
    }

    private void assertGet(Resume r) {
        assertEquals(r, storage.get(r.getUuid()));
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }
}