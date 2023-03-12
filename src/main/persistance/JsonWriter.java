package persistance;

import model.list.Section;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

// Represents a writer that write sections from JSON data stored in file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;
    private List<Section> sections;

    // EFFECTS: Constructs a writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
        this.sections = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: open writer, throw FileNotFoundException if file cannot be opened to write
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: Write JSON representation of sections to File
    public void write(List<Section> sections) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Sections",sectionsToJsonArray(sections));
        saveToFile(jsonObject.toString(TAB));

    }

    // MODIFIES: this
    // EFFECTS: Write each section to JSONObjet, and added into JSONArray
    private JSONArray sectionsToJsonArray(List<Section> sections) {
        JSONArray jsonArray = new JSONArray();
        for (Section s: sections) {
            jsonArray.put(s.toJson());
        }
        return jsonArray;
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    public void saveToFile(String json) {
        writer.print(json);
    }


}
