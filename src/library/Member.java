
package library;


public class Member {
    
    private String id;
    private String first;
    private String last;
    private String address;
    private String date;
    private int rentals;
    private String level;
    private Double dues;
    
    public Member(String id, String first, String last, String address, String date, int rentals, String level, Double dues){
        
        this.id = id;
        this.first = first;
        this.last = last;
        this.address = address;
        this.date = date;
        this.rentals = rentals;
        this.level = level;
        this.dues = dues;
       
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Double getDues() {
        return dues;
    }

    public void setDues(Double dues) {
        this.dues = dues;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getRentals() {
        return rentals;
    }

    public void setRentals(int rentals) {
        this.rentals = rentals;
    }
    
}
