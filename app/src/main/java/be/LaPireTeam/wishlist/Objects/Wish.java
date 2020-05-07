package be.LaPireTeam.wishlist.Objects;

public class Wish {
    public final int ID;
    private String name;
    private int priority;
    private String commentary;
    private boolean bookingStatus;
    private String product;

    public Wish(int id, String name, int priority, String commentary, String product) {
        if (id == -1) {
            this.ID = -1; //TODO generate number
        } else {
            this.ID = id;
        }
        this.name = name;
        this.priority = priority;
        this.commentary = commentary;
        this.product = product;
        this.bookingStatus = false;
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

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public boolean getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(boolean bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
