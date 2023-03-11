package persistance;

import org.json.JSONObject;

public interface Writable {

    // EFFECT: Return Json Object;
    JSONObject toJson();

}
