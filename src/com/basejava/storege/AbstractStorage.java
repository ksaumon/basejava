package com.basejava.storege;

import com.basejava.exception.ExistStorageException;
import com.basejava.exception.NotExistStorageException;
import com.basejava.model.Resume;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    protected static final Comparator <Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getFullName)
            .thenComparing(Resume::getUuid);

    public void update(Resume r) {
        LOG.info("update" + r);
        SK searchKey = getExistedKey(r.getUuid());
        doUpdate(r, searchKey);
    }

    public void save(Resume r) {
        LOG.info("save" + r);
        SK searchKey = getNotExistedKey(r.getUuid());
        doSave(r, searchKey);
    }

    public void delete(String uuid) {
        LOG.info("delete" + uuid);
        SK searchKey = getExistedKey(uuid);
        doDelete(searchKey);
    }

    public Resume get(String uuid) {
        LOG.info("get" + uuid);
        SK searchKey = getExistedKey(uuid);
        return doGet(searchKey);
    }

    private SK getExistedKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (! isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getNotExistedKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " already exist");
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    @Override
    public List <Resume> getAllSorted() {
        LOG.info("getAllSorted");
        List <Resume> list = doCopyAll();
        list.sort(RESUME_COMPARATOR);
        return list;
    }

    protected abstract List <Resume> doCopyAll();

    protected abstract SK getSearchKey(String uuid);

    protected abstract void doUpdate(Resume r, SK searchKey);

    protected abstract boolean isExist(SK searchKey);

    protected abstract void doSave(Resume r, SK searchKey);

    protected abstract Resume doGet(SK searchKey);

    protected abstract void doDelete(SK searchKey);
}
