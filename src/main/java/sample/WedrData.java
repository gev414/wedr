package sample;

import com.google.gson.*;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

import java.util.Scanner;

public class WedrData {


    //  to be sent in the request as parameter
    private String input;

    HttpResponse<JsonNode> response;

    //  Received data
    private String name;
    private String region;
    private String country;
    private double latitude;
    private double longitude;
    private String time;
    private double tempC;
    private double tempF;
    private String condition;
    private String rawInfo;

    Key key = new Key();
    Scanner scan = new Scanner(System.in);
    JsonParser jp = new JsonParser();

    //httpresponse to JsonElement
    private JsonElement request() {
        HttpResponse<JsonNode> response = Unirest.get("https://weatherapi-com.p.rapidapi.com/current.json?q=" + input)
                .header("X-RapidAPI-Key", key.getKey())
                .header("X-RapidAPI-Host", "weatherapi-com.p.rapidapi.com")
                .asJson();
        this.response = response;
        JsonElement je = jp.parse(response.getBody().toString());
        return je;
    }

    public WedrData() {
    }


    public void wedrInit () {
            try {
                // this.input = scan.nextLine();
                request();
                setName();
                setRegion();
                setCountry();
                setLatitude();
                setLongitude();
                setTempC();
                setTempF();
                setCondition();
                setTime();
            } catch (Exception e) {
                System.out.println("It went to cinema: " + e);
            }
    }


    public String getInfo(){
        return getName() + "\n " +
                getRegion() + "\n " +
                getCountry() + "\n " +
                getLatitude() + "\n " +
                getLongitude() + "\n " +
                getTime() + "\n " +
                getTempC() + "\n " +
                getTempF() + "\n " +
                getCondition();

    }

    public String compact(){
        return getTempC()+"° as of "+getTime()+"\nin "+getName()+", "+getCountry();
    }


    public void setInput(String inputText) {
        this.input = inputText;
    }

    public void setName() {
        JsonElement str = request().getAsJsonObject().get("location").getAsJsonObject().get("name");
        this.name = str.toString();
    }

    public String getName() {
        return name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion() {
        JsonElement str = request().getAsJsonObject().get("location").getAsJsonObject().get("region");
        this.region = str.toString();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry() {
        JsonElement str = request().getAsJsonObject().get("location").getAsJsonObject().get("country");
        this.country = str.toString();
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude() {
        JsonElement str = request().getAsJsonObject().get("location").getAsJsonObject().get("lat");
        this.latitude = Double.parseDouble(str.toString());
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude() {
        JsonElement str = request().getAsJsonObject().get("location").getAsJsonObject().get("lon");
        this.longitude = Double.parseDouble(str.toString());
    }

    public String getTime() {
        return time;
    }

    public void setTime() {
        JsonElement str = request().getAsJsonObject().get("location").getAsJsonObject().get("localtime");
        this.time = str.toString();
    }

    public double getTempC() {
        return tempC;
    }

    public void setTempC() {
        JsonElement str = request().getAsJsonObject().get("current").getAsJsonObject().get("temp_c");
        this.tempC = Double.parseDouble(str.toString());
    }

    public double getTempF() {
        return tempF;
    }

    public void setTempF() {
        JsonElement str = request().getAsJsonObject().get("current").getAsJsonObject().get("temp_f");
        this.tempF = Double.parseDouble(str.toString());
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition() {
        JsonElement str = request().getAsJsonObject().get("current").getAsJsonObject().get("condition").getAsJsonObject().get("text");
        this.condition = str.toString();

    }


    //unprocessed info
    public String getRawInfo() {
        return rawInfo;
    }

    public void setRawInfo() {
        JsonElement str = request().getAsJsonObject();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        this.rawInfo = gson.toJson(jp.parse(response.getBody().toString()));
    }

    //test
  //  public void print() {
  //      System.out.println(response.getStatus());
  //      System.out.println(response.getHeaders().get("Content-Type"));
  //      System.out.println(
  //              getName() + "\n " +
  //                      getRegion() + "\n " +
  //                      getCountry() + "\n " +
  //                      getLatitude() + "\n " +
  //                      getLongitude() + "\n " +
  //                      getTime() + "\n " +
  //                      getTempC() + "\n " +
  //                      getTempF() + "\n " +
  //                      getCondition());
  //  }

}
