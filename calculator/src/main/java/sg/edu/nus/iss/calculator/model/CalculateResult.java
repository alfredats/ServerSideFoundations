package sg.edu.nus.iss.calculator.model;

import java.io.StringReader;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import jakarta.json.Json;
import jakarta.json.JsonException;
import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;

public class CalculateResult {
    private double result;
    private LocalDateTime timestamp;
    private String userAgent;

    @Override
    public String toString() {
        String datetimeStr = (this.getTimestamp() == null) ? null : this.getTimestamp().toString();
        JsonObject obj = Json.createObjectBuilder()
            .add("result", this.getResult())
            .add("timestamp", this.getTimestamp().toString())
            .add("userAgent", this.getUserAgent())
            .build();
        return obj.toString();
    }

    public static final CalculateResult create(String agent, String reqStr) throws JsonException, Exception{
        CalculateResult cr = new CalculateResult();
        JsonObject req = Json.createReader(new StringReader(reqStr)).readObject();
        JsonNumber o1Raw = req.getJsonNumber("oper1");
        JsonNumber o2Raw = req.getJsonNumber("oper2");
        String ops = req.getString("ops");

        double d1 = o1Raw.doubleValue();
        double d2 = o2Raw.doubleValue();

        switch (ops) {
            case "plus": 
                cr.setResult(d1 + d2);
                break;
            case "minus":
                cr.setResult(d1 - d2);
                break;
            case "divide":
                cr.setResult(d1 / d2);
                break;
            case "multiply":
                cr.setResult(d1 * d2);
                break;
            default: 
                throw new Exception("Error: Operand not supported");
        }
        Instant instant = Instant.ofEpochMilli(System.currentTimeMillis());
        LocalDateTime date = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        cr.setTimestamp(date);
        cr.setUserAgent(agent);

        return cr;
    }
    

    /**
     * @return double return the result
     */
    public double getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(double result) {
        this.result = result;
    }

    /**
     * @return String return the userAgent
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * @param userAgent the userAgent to set
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }


    /**
     * @return LocalDateTime return the timestamp
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }


    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

}
