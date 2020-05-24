package yangfuwei.xhB17121910.Recommend;

public class RecommendListModel {
    private long time;
    private String title;
    private String auther;
    private String imageUrl;
    private String sharedByWho;

    public RecommendListModel(long time, String title, String auther, String imageUrl, String sharedByWho) {
        this.time = time;
        this.title = title;
        this.auther = auther;
        this.imageUrl = imageUrl;
        this.sharedByWho = sharedByWho;
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

    public String getSharedByWho() {
        return sharedByWho;
    }

    public void setSharedByWho(String sharedByWho) {
        this.sharedByWho = sharedByWho;
    }


}
