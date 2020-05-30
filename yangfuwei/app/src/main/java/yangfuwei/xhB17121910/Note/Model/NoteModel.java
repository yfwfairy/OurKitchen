package yangfuwei.xhB17121910.Note.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.Nullable;

public class NoteModel implements Parcelable {
    private int id = -1;
    private long time;
    private String title = "";
    private String auther;
    private String imageUrl;
    private String content = "";
    private int importance;
    private int type;
    public NoteModel() {
        time = System.currentTimeMillis();
    }


    @Override
    public boolean equals(@Nullable Object obj) {
        Log.d("yfw", "equals: " + toString());
        if (obj instanceof NoteModel) {
            NoteModel noteModel = (NoteModel)obj;
            if (noteModel.getTitle().equals(this.title) && noteModel.getContent().equals(content) && noteModel.getImportance() == importance && noteModel.getType() == type) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "NoteModel{" +
                "id=" + id +
                ", time=" + time +
                ", title='" + title + '\'' +
                ", auther='" + auther + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", content='" + content + '\'' +
                ", importance=" + importance +
                ", type=" + type +
                '}';
    }

    public NoteModel(long time, String title, String auther, String imageUrl, String content) {
        this.time = time;
        this.title = title;
        this.auther = auther;
        this.imageUrl = imageUrl;
        this.content = content;
    }

    protected NoteModel(Parcel in) {
        id = in.readInt();
        time = in.readLong();
        title = in.readString();
        auther = in.readString();
        imageUrl = in.readString();
        content = in.readString();
        importance = in.readInt();
        type = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeLong(time);
        dest.writeString(title);
        dest.writeString(auther);
        dest.writeString(imageUrl);
        dest.writeString(content);
        dest.writeInt(importance);
        dest.writeInt(type);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NoteModel> CREATOR = new Creator<NoteModel>() {
        @Override
        public NoteModel createFromParcel(Parcel in) {
            return new NoteModel(in);
        }

        @Override
        public NoteModel[] newArray(int size) {
            return new NoteModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
