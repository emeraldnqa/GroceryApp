package persistance;

import model.item.StoreItem;
import model.list.Section;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest{

    private Section produces;
    private Section meats;
    private Section dairies;
    private Section groceries;

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    void testReaderEmptySection() {
        JsonReader reader = new JsonReader("./data/testReaderEmptySections.json");
        try {
            List<Section> sectionList = reader.read();
            assertEquals(0,sectionList.size());
        } catch (IOException e) {
            fail("Exception not expected");
        }
    }

    @Test
    void testReaderGeneralSection() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralSection.json");
        try {
            List<Section> sectionList = reader.read();
            produces = sectionList.get(0);
            meats = sectionList.get(1);
            groceries = sectionList.get(2);
            dairies = sectionList.get(3);

            assertEquals(1,produces.getNumOfItem());
            assertEquals(1,meats.getNumOfItem());
            assertEquals(1,groceries.getNumOfItem());
            assertEquals(1,dairies.getNumOfItem());

            checkSections(setProduceItem(),produces);
            checkSections(setMeatItem(),meats);
            checkSections(setGroceryItem(),groceries);
            checkSections(setDairyItem(),dairies);
        } catch (IOException e) {
            fail("Exception not expected");
        }
    }



}
