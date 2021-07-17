
package library;


public class Rental {
    
    private String memberID;
    private String name;
    private String isbn;
    private String title;
    private String dueDate;
    private String rentalDate;
    
    public Rental(String memberID, String name, String isbn, String title, String dueDate, String rentalDate){
        this.memberID = memberID;
        this.name = name;
        this.isbn = isbn;
        this.title = title;
        this.dueDate = dueDate;
        this.rentalDate = rentalDate;
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

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(String rentalDate) {
        this.rentalDate = rentalDate;
    }
    
    
}
