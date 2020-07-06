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
    private Float price;

    @SerializedName("accessibility")
    private Float accessibility;



    public BoredAction(String activity, String type, String key, Integer participants, Double price, String link, Float accessibility, Double minAccessibility, Double maxAccessibility, Double minPrice, Double maxPrice) {
        this.activity = activity;
        this.type = type;
        this.key = key;
        this.participants = participants;
        this.accessibility = accessibility;

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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getAccessibility() {
        return accessibility;
    }

    public void setAccessibility(Float accessibility) {
        this.accessibility = accessibility;
    }

    @Override
    public  String toString(){
        return  "BoredAction{" + "key=" +key + ",activity=" + activity +",type=" + ",participants=" +participants+
                ",price=" +price + ",link="  +",accessibility="+accessibility  +"}";

    }
}