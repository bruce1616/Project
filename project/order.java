public class order {
    private String customer;
    private String status;
    private String date_received;
    private String additional_ins;
    private String address;

    public order(){

    }
    public order(String customer, String status, String date_received, String additional_ins, String address){
        this.customer = customer;
        this.status = status;
        this.date_received = date_received;
        this.additional_ins = additional_ins;
        this.address = address;
    }

    public void setCustomer(String customer){
        this.customer = customer;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public void setDate(String date_received){
        this.date_received = date_received;
    }

    public void setAddition(String additional_ins){
        this.additional_ins = additional_ins;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getCustomer(){
        return customer;
    }
    public String getStatus(){
        return status;
    }
    public String getDate(){
        return date_received;
    }
    public String getAddition(){
        return additional_ins;
    }
    public String getAddress(){
        return address;
    }
}