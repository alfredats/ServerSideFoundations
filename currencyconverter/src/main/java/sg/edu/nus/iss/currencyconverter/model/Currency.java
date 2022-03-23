package sg.edu.nus.iss.currencyconverter.model;

import jakarta.json.JsonObject;

public class Currency {
    private String id;
    private String symbol;
    private String name;

    public static Currency create(JsonObject resp) {
        Currency c = new Currency();
        c.setId(resp.getString("currencyId"));
        c.setName(resp.getString("currencyName"));
        c.setSymbol(resp.getString("currencySymbol"));
        return c;
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
     * @return String return the symbol
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * @param symbol the symbol to set
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }


}
