
package library;


public class Book {
    
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private String date;
    private String genre;
    private int pages;
    private int quantity;
    private int available;
    
    
    
    public Book(String isbn, String title, String author, String publisher, String date, String genre, int pages, int quantity, int available){
        
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this. publisher = publisher;
        this.date = date;
        this.genre = genre;
        this.pages = pages;
        this.quantity = quantity;
        this.available = available;
        
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

}
