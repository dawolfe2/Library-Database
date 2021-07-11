
package library;


public class Book {
    
    private int bookID;
    private String title;
    private String author;
    private String publisher;
    private String date;
    private String status;
    private String genre;
    private String edition;
    private int pages;
    
    
    public Book(int bookID, String title, String author, String publisher, String date, String status, String genre, String edition, int pages){
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this. publisher = publisher;
        this.date = date;
        this.status = status;
        this.genre = genre;
        this.edition = edition;
        this.pages = pages;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
    
    
}
