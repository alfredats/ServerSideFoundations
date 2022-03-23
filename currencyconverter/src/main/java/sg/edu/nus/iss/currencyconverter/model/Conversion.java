package sg.edu.nus.iss.currencyconverter.model;

public class Conversion {
    private String fromCurr;
    private String fromSym;
    private Double fromAmt;
    private String toCurr;
    private String toSym;
    private Double toAmt;
    private long time;

    public static Conversion create(Currency from, Currency to, Double fromAmt, Double rate) {
        Conversion c = new Conversion();
        c.setFromCurr(from.getName());
        c.setFromSym(from.getSymbol());
        c.setFromAmt(fromAmt);
        c.setToCurr(to.getName());
        c.setToSym(to.getSymbol());
        c.setToAmt(fromAmt * rate);
        c.setTime(System.currentTimeMillis());
        
        return c;
    }

    @Override
    public String toString() {
        return "Conversion [fromAmt=" + fromAmt + ", fromCurr=" + fromCurr + ", fromSym=" + fromSym + ", time=" + time
                + ", toAmt=" + toAmt + ", toCurr=" + toCurr + ", toSym=" + toSym + "]";
    }

    /**
     * @return String return the fromCurr
     */
    public String getFromCurr() {
        return fromCurr;
    }

    /**
     * @param fromCurr the fromCurr to set
     */
    public void setFromCurr(String fromCurr) {
        this.fromCurr = fromCurr;
    }

    /**
     * @return String return the fromSym
     */
    public String getFromSym() {
        return fromSym;
    }

    /**
     * @param fromSym the fromSym to set
     */
    public void setFromSym(String fromSym) {
        this.fromSym = fromSym;
    }

    /**
     * @return Double return the fromAmt
     */
    public Double getFromAmt() {
        return fromAmt;
    }

    /**
     * @param fromAmt the fromAmt to set
     */
    public void setFromAmt(Double fromAmt) {
        this.fromAmt = fromAmt;
    }

    /**
     * @return String return the toCurr
     */
    public String getToCurr() {
        return toCurr;
    }

    /**
     * @param toCurr the toCurr to set
     */
    public void setToCurr(String toCurr) {
        this.toCurr = toCurr;
    }

    /**
     * @return String return the toSym
     */
    public String getToSym() {
        return toSym;
    }

    /**
     * @param toSym the toSym to set
     */
    public void setToSym(String toSym) {
        this.toSym = toSym;
    }

    /**
     * @return Double return the toAmt
     */
    public Double getToAmt() {
        return toAmt;
    }

    /**
     * @param toAmt the toAmt to set
     */
    public void setToAmt(Double toAmt) {
        this.toAmt = toAmt;
    }

    /**
     * @return long return the time
     */
    public long getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(long time) {
        this.time = time;
    }

}
