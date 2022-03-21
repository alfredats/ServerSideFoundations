package sg.edu.nus.iss.deckOfCards.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.JsonPathException;

public class Deck {
    private String id;
    private Boolean shuffled;
    private Integer remaining;
    private List<Card> drawn;

    public static Deck create(String respBody) 
        throws IOException, JsonPathException, Exception {
            Deck nd = new Deck();
            DocumentContext context = JsonPath.parse(respBody);
            if (context.read("$.success",Boolean.class)) {
                String id = context.read("$.deck_id",String.class);
                nd.setId(id);
                nd.setRemaining(context.read("$.remaining",Integer.class));
                nd.setDrawn(new ArrayList<Card>());
            
                if (!context.read("$[?(@.shuffled)]", List.class).isEmpty()) {
                    nd.setShuffled(context.read("$.shuffled", Boolean.class));
                }

                if(!context.read("$[?(@.cards)]", List.class).isEmpty()) {
                    List<Map> respCards = context.read("$.cards[*]", List.class);
                    for (Map cardJSON : respCards) {
                        nd.addDrawn(Card.create(cardJSON));
                    }
                }

            } else {
                throw new Exception("API RESPONSE FAILURE");
            }
            return nd;
    }
    
    @Override
    public String toString() {
        return "Deck [id=" + id + ", remaining=" + remaining + ", shuffled=" + shuffled + "]";
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
     * @return Integer return the remaining
     */
    public Integer getRemaining() {
        return remaining;
    }

    /**
     * @param remaining the remaining to set
     */
    public void setRemaining(Integer remaining) {
        this.remaining = remaining;
    }

    /**
     * @return List<String> return all the drawn cards
     */
    public List<Card> getDrawn() {
        return drawn;
    }

    /**
     * @param drawn the list of drawn cards to set
     */
    public void setDrawn(List<Card> drawn) {
        this.drawn = drawn;
    }

    /**
     *  @param single the single drawn card to add
     */
    public void addDrawn(Card single) {
        this.drawn.add(single);
    } 


    /**
     * @return Boolean return the shuffled
     */
    public Boolean isShuffled() {
        return shuffled;
    }

    /**
     * @param shuffled the shuffled to set
     */
    public void setShuffled(Boolean shuffled) {
        this.shuffled = shuffled;
    }

}
