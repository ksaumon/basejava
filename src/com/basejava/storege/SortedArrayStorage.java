package com.basejava.storege;

import com.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
   /* private static class ResumeComparator implements Comparator<Resume>{

        @Override
        public int compare(Resume o1, Resume o2) {
            return o1.getUuid().compareTo(o2.getUuid());
        }
    }*/

//    private static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getUuid);
//    Alt + Enter выбор упрощение либо усложнение чтение записи.

    @Override
    protected void insertResume(Resume r, int index) {
        int insertIndex = - index - 1;
        System.arraycopy(storage, insertIndex, storage, insertIndex + 1, size - insertIndex);
        storage[insertIndex] = r;
    }

    @Override
    protected void deleteResume(int index) {
        int numberIndexMove = size - index - 1;
        if (numberIndexMove > 0) {
            System.arraycopy(storage, index + 1, storage, index, numberIndexMove);
        }
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid,"dummy" );
        return Arrays.binarySearch(storage, 0, size, searchKey,RESUME_COMPARATOR);
    }


}
