package persistance;

import model.item.*;
import model.list.Section;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

// Represent a reader that reads sections from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: Construct a reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: Read section from file, and returns it;
    // throws IOException if there's an error reading the file
    public List<Section> read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseSections(jsonObject);
    }

    // EFFECTS: read source file as String and returns it
    public String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: Parse Section from JSONObject and returns it
    private List<Section> parseSections(JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Sections");
        List<Section> sections = new ArrayList<>();
        for (Object json: jsonArray) {
            JSONObject nextSection = (JSONObject) json;
            sections.add(parseSection(nextSection));


        }
        return sections;
    }

    // EFFECTS: parse individual section from jsonObject to section
    private Section parseSection(JSONObject jsonObject) {
        String type = jsonObject.getString("Type");
        Section section = new Section(type);
        addStoreItems(section,jsonObject);
        return section;
    }

    // MODIFIES: section
    // EFFECTS: parse StoreItems from JSONObject and add them to section
    private void addStoreItems(Section section, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("items");
        for (Object json : jsonArray) {
            JSONObject nextItem = (JSONObject) json;
            addItem(section, nextItem);
        }
    }

    // MODIFIES: section
    // EFFECTS: parses item from JSON Object and add it into section
    private  void addItem(Section section, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String brand = jsonObject.getString("brand");
        double price = jsonObject.getDouble("price");
        int amount = jsonObject.getInt("amount");
        int initialAmount = jsonObject.getInt("initialAmount");
        double boughtPrice = jsonObject.getDouble("boughtPrice");
        String unit = jsonObject.getString("unit");
        int year = jsonObject.getInt("year");
        int month = jsonObject.getInt("month");
        int date = jsonObject.getInt("date");

        String type = section.getType();
        StoreItem item = createStoreItem(name, brand,amount,boughtPrice,unit,type,initialAmount);
        item.setExpiryDate(year,month,date);
        section.addItem(item);
    }

    // EFFECTS: Create a new StoreItem
    private StoreItem createStoreItem(String name, String brand, int amount,
                                      double boughtPrice, String unit, String type, int initialAmount) {
        switch (type) {
            case "Produce":
                StoreItem produce = new Produce();
                setField(produce, name, brand, amount, boughtPrice, unit, initialAmount);
                return produce;
            case "Grocery":
                StoreItem grocery = new Grocery();
                setField(grocery, name, brand, amount, boughtPrice, unit, initialAmount);
                return grocery;
            case "Meat":
                StoreItem meat = new Meat();
                setField(meat, name, brand, amount, boughtPrice, unit, initialAmount);
                return meat;
            case "Dairy":
                StoreItem dairy = new Dairy();
                setField(dairy, name, brand, amount, boughtPrice, unit, initialAmount);
                return dairy;
            default:
                return null;
        }
    }


    // EFFECTS: Set StoreItem field
    private void setField(StoreItem item, String name, String brand, int amount,
                          double boughtPrice, String unit, int initialAmount) {
        item.setName(name);
        item.setBrand(brand);
        item.setAmount(amount);
        item.setInitialAmount(initialAmount);
        item.setUnit(unit);
        item.setBoughtPrice(boughtPrice);
        item.setPrice();
    }



}
