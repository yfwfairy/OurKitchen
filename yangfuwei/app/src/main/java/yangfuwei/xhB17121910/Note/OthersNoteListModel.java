package yangfuwei.xhB17121910.Note;

public class OthersNoteListModel {
    private long time;
    private String title;
    private String auther;
    private String imageUrl;

    public OthersNoteListModel(long time, String title, String auther, String imageUrl) {
        this.time = time;
        this.title = title;
        this.auther = auther;
        this.imageUrl = imageUrl;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


}
