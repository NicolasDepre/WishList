package be.LaPireTeam.wishlist.Objects;

public class Session {

    private static Session instance;
    private User u;

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }

    public static Session getInstance(){
        if(instance == null){
            instance = new Session();
        }
        return instance;
    }


}
