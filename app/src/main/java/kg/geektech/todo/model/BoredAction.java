package kg.geektech.todo.model;

import com.google.gson.annotations.SerializedName;
public  class BoredAction{

    @SerializedName("activity")
    private String activity;

    @SerializedName("type")
    private String type;

    @SerializedName("key")
    private String key;

    @SerializedName("participants")
    private Integer participants;

    @SerializedName("price")
    private Double price;

    @SerializedName("accessibility")
    private String accessibility;

    @SerializedName("minAccessibility")
    private Double minAccessibility;

    @SerializedName("maxAccessibility")
    private Double maxAccessibility;

    @SerializedName("minprice")
    private Double minPrice;
    @SerializedName("maxprice")
    private Double maxPrice;

    public BoredAction(String activity, String type, String key, Integer participants, Double price, String link, String accessibility, Double minAccessibility, Double maxAccessibility, Double minPrice, Double maxPrice) {
        this.activity = activity;
        this.type = type;
        this.key = key;
        this.participants = participants;
        this.price = price;
        this.accessibility = accessibility;
        this.minAccessibility = minAccessibility;
        this.maxAccessibility = maxAccessibility;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getParticipants() {
        return participants;
    }

    public void setParticipants(Integer participants) {
        this.participants = participants;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }



    public String getAccessibility() {
        return accessibility;
    }

    public void setAccessibility(String accessibility) {
        this.accessibility = accessibility;
    }

    public Double getMinAccessibility() {
        return minAccessibility;
    }

    public void setMinAccessibility(Double minAccessibility) {
        this.minAccessibility = minAccessibility;
    }

    public Double getMaxAccessibility() {
        return maxAccessibility;
    }

    public void setMaxAccessibility(Double maxAccessibility) {
        this.maxAccessibility = maxAccessibility;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    @Override
    public  String toString(){
        return  "BoredAction{" + "key=" +key + ",activity=" + activity +",type=" + ",participants=" +participants+
                ",price=" +price + ",link="  +",accessibility="+accessibility +  "minAccessibility=" + minAccessibility
                + "maxAccessibility=" + minAccessibility + "minprice=" +minPrice + "maxPrice" + maxPrice +"}";

    }
}