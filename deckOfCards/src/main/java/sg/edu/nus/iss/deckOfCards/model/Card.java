package sg.edu.nus.iss.deckOfCards.model;

import java.util.Map;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

public class Card {
    private String id;
    private String value;
    private String suit;
    private String img;
    private static final String IMG_URL = "https://deckofcardsapi.com/static/img/";

    public static final Card create(Map<String, String> respMap) {
        Card crd = new Card();

        crd.setId(respMap.get("code"));
        crd.setValue(respMap.get("value"));
        crd.setSuit(respMap.get("suit"));
        crd.setImg(IMG_URL + crd.getId() + ".png");

        return crd;
    }

    /**
     * @return String return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return String return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return String return the suit
     */
    public String getSuit() {
        return suit;
    }

    /**
     * @param suit the suit to set
     */
    public void setSuit(String suit) {
        this.suit = suit;
    }

    /**
     * @return String return the img
     */
    public String getImg() {
        return img;
    }

    /**
     * @param img the img to set
     */
    public void setImg(String img) {
        this.img = img;
    }

}
