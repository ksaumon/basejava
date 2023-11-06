package util;

import com.basejava.model.Resume;
import com.basejava.model.Section;
import com.basejava.model.TextSection;
import com.basejava.util.JsonParser;
import org.junit.Assert;
import org.junit.Test;

import static com.basejava.storege.AbstractStorageTest.RESUME_1;

public class JsonParserTest {
    @Test
    public void testResume() throws Exception {
        String json = JsonParser.write(RESUME_1);
        System.out.println(json);
        Resume resume = JsonParser.read(json, Resume.class);
        Assert.assertEquals(RESUME_1, resume);
    }

    @Test
    public void write() throws Exception {
        Section section1 = new TextSection("Objective1");
        String json = JsonParser.write(section1, Section.class);
        System.out.println(json);
        Section section2 = JsonParser.read(json, Section.class);
        Assert.assertEquals(section1, section2);
    }
}
