package be.LaPireTeam.wishlist.Objects;

public class List {
    public final int ID;
    private String name;

    public List(int id) {
        this.ID = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
