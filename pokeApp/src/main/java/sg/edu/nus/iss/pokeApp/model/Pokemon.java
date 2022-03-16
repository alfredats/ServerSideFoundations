package sg.edu.nus.iss.pokeApp.model;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Pokemon {
    private String name;
    private Map<String, String> spriteURLs;
    // private List<String> spriteURLs;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    // public List<String> getSpriteURLs() {
    //     return spriteURLs;
    // }
    // public void setSpriteURLs(List<String> spriteURLs) {
    //     this.spriteURLs = spriteURLs;
    // }
    public Map<String, String> getSpriteURLs() {
        return spriteURLs;
    }
    public void setSpriteURLs(Map<String, String> spriteURLs) {
        this.spriteURLs = spriteURLs;
    }

    public static Pokemon createPokemon(String apiResp) {
        JsonObject respObj = Json.createReader(new StringReader(apiResp)).readObject();
        Pokemon pkm = new Pokemon();
        JsonObject gen1RedBlueSprites = respObj
            .getJsonObject("sprites")
            .getJsonObject("versions")
            .getJsonObject("generation-i")
            .getJsonObject("red-blue");
        
        HashMap<String,String> spriteURLMap = new HashMap<>();
        // ArrayList<String> sprLst = new ArrayList<>();
        for (String spriteStr : gen1RedBlueSprites.keySet()) {
            spriteURLMap.put(spriteStr, gen1RedBlueSprites.getString(spriteStr));
            // sprLst.add(gen1RedBlueSprites.getString(spriteStr));
        }

        pkm.setName(respObj.getString("name"));
        pkm.setSpriteURLs(spriteURLMap);
        // pkm.setSpriteURLs(sprLst);

        return pkm;
    }


}
