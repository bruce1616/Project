

public class laundryshops{
    private String name;
    private String address;
    private String latitude;
    private String longitude;
    private String ratings;

    public laundryshops(String name, String address, String latitude, String longitude, String ratings){
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.ratings = ratings;
    }

    public laundryshops(){

    }

    public void setName(String name){
        this.name = name;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setLatitude(String latitude){
        this.latitude = latitude;
    }

    public void setLongitude(String longitude){
        this.longitude = longitude;
    }

    public void setRatings(String ratings){
        this.ratings = ratings;
    }

    public String getName(){
        return name;
    }

    public String getAddress(){
        return address;
    }

    public String getLatitude(){
        return latitude;
    }

    public String getLongitude(){
        return longitude;
    }
    public String getRatings(){
        return ratings;
    }
}