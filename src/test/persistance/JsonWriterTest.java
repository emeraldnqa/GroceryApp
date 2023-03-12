package persistance;

import model.list.Section;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest{

    private Section produces;
    private Section meats;
    private Section dairies;
    private Section groceries;

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (FileNotFoundException e) {
            // Success
        }
    }

    @Test
    void testWriterSectionsEmpty() {
        try {
            List<Section> sectionList = new ArrayList<>();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptySection.json");
            writer.open();
            writer.write(sectionList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderEmptySections.json");
            List<Section> sections = reader.read();
            assertEquals(0,sections.size());
        } catch (FileNotFoundException e) {
            fail("Did not expect exception");
        } catch (IOException e) {
            fail("Did not expect Exception");
        }
    }

    @Test
    void testWriterSectionGeneral() {
        try{
            produces = setProduce();
            meats = setMeats();
            groceries = setGrocery();
            dairies = setDairy();
            List<Section> sectionList = new ArrayList<>();
            sectionList.add(produces);
            sectionList.add(meats);
            sectionList.add(groceries);
            sectionList.add(dairies);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralSection.json");
            writer.open();
            writer.write(sectionList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralSection.json");
            sectionList = reader.read();
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
        } catch (FileNotFoundException e) {
            fail("Exception not expected");
        } catch (IOException e) {
            fail("Exception not expected");
        }
    }
}

