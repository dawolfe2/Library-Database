
package library;


public class Member {
    
    private String id;
    private String first;
    private String last;
    private String address;
    private String phoneOne;
    private String emailOne;
    private String date;
    private int rentals;
    
    public Member(String id, String first, String last, String address, String phoneOne, String emailOne, String date, int rentals){
        
        this.id = id;
        this.first = first;
        this.last = last;
        this.address = address;
        this.phoneOne = phoneOne;
        this.emailOne = emailOne;
        this.date = date;
        this.rentals = rentals;
        
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

    public String getPhoneOne() {
        return phoneOne;
    }

    public void setPhoneOne(String phoneOne) {
        this.phoneOne = phoneOne;
    }

    public String getEmailOne() {
        return emailOne;
    }

    public void setEmailOne(String emailOne) {
        this.emailOne = emailOne;
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
