package be.LaPireTeam.wishlist;

public class Wish {
    public final int ID;
    private String name;
    private int priority;
    private String[] commentary;
    private User bookingStatus;
    private Product product;

    public Wish(String name, int priority, String[] commentary, Product product) {
        this.ID = 0; //TODO generate number
        this.name = name;
        this.priority = priority;
        this.commentary = commentary;
        this.product = product;
        this.bookingStatus = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String[] getCommentary() {
        return commentary;
    }

    public void setCommentary(String[] commentary) {
        this.commentary = commentary;
    }

    public User getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(User bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
