package yangfuwei.xhB17121910.Note.Model;

public class NoteModel {
    private long time;
    private String title;
    private String auther;
    private String imageUrl;
    private String content;
    private int importance;
    private int type;

    public NoteModel(long time, String title, String auther, String imageUrl, String content) {
        this.time = time;
        this.title = title;
        this.auther = auther;
        this.imageUrl = imageUrl;
        this.content = content;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
