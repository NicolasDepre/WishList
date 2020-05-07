package be.LaPireTeam.wishlist.Objects;

public class Session {

    private static Session instance;
    private User u;
    private List lastClickedList;
    private Wish lastClickedWish;
    private User lastClickedFriend;


    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }


    public List getLastClickedList() {
        return lastClickedList;
    }

    public void setLastClickedList(List lastClickedList) {
        this.lastClickedList = lastClickedList;
    }

    public Wish getLastClickedWish() {
        return lastClickedWish;
    }

    public void setLastClickedWish(Wish lastClickedWish) {
        this.lastClickedWish = lastClickedWish;
    }

    public User getLastClickedFriend() {
        return lastClickedFriend;
    }

    public void setLastClickedFriend(User lastClickedFriend) {
        this.lastClickedFriend = lastClickedFriend;
    }
}
