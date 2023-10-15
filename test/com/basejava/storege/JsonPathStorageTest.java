package com.basejava.storege;

import com.basejava.storege.serializer.JsonStreamSerializer;

public class JsonPathStorageTest extends AbstractStorageTest {

 public JsonPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new JsonStreamSerializer()));
    }
}
