package kg.geektech.todo.model;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.annotations.SerializedName;

public class BoredAction {

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

    @SerializedName("link")
    private String link;

    @SerializedName("accessibility")
    private String accessibility;

    @SerializedName("minprice")
    private Double minPrice;

    @SerializedName("maxprice")
    private Double maxPrice;



    public BoredAction(String activity, String type, String key, Integer participants, Double price, String link, String accessibility, Double minPrice, Double maxPrice) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.activity = activity;
        this.type = type;
        this.key = key;
        this.participants = participants;
        this.price = price;
        this.link = link;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAccessibility() {
        return accessibility;
    }

    public void setAccessibility(String accessibility) {
        this.accessibility = accessibility;
    }

    @Override
    public  String toString(){
        return  "BoredAction{" + "key=" +key + ",activity=" + activity +",type=" + ",participants=" +participants+
                ",price=" +price + ",link=" +link +",accessibility="+accessibility + "}";

    }
}