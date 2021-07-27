
package library;


public class Reservation {
    
    private String memberID;
    private String name;
    private String isbn;
    private String title;
    private String endDate;
    
     public Reservation(String memberID, String name, String isbn, String title, String endDate){
        this.memberID = memberID;
        this.name = name;
        this.isbn = isbn;
        this.title = title;
        this.endDate = endDate;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
     
     
}
