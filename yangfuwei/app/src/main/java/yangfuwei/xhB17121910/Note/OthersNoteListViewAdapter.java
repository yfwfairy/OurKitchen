package yangfuwei.xhB17121910.Note;

import android.content.Context;
import android.database.DataSetObserver;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import yangfuwei.xhB17121910.R;
import yangfuwei.xhB17121910.Note.OthersNoteListModel;

public class OthersNoteListViewAdapter implements ListAdapter {
    private Context mContext;
    private List<OthersNoteListModel> articleList;

    public OthersNoteListViewAdapter(Context _context, List<OthersNoteListModel> _listModel) {
        mContext = _context;
        if (_listModel == null) {
            articleList = new ArrayList<>();
        } else {
            articleList = _listModel;
        }
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int i) {
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount() {
        return articleList.size();
    }

    @Override
    public Object getItem(int i) {
        return articleList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view != null) {
            viewHolder = (ViewHolder) view.getTag();
        } else {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.others_note_list_item, null, true);
            viewHolder.titleTxv = view.findViewById(R.id.title);
            viewHolder.authorTxv = view.findViewById(R.id.author);
            viewHolder.mImageView = view.findViewById(R.id.image);
            view.setTag(viewHolder);
        }
        OthersNoteListModel model = articleList.get(i);
        if (model != null) {
            viewHolder.titleTxv.setText(model.getTitle());
            viewHolder.authorTxv.setText(model.getAuther());
            if (model.getImageUrl() != null && model.getImageUrl().length() != 0) {
                Glide.with(mContext).load(model.getImageUrl()).centerCrop().into(viewHolder.mImageView);
            }
        }
        return view;
    }

    @Override
    public int getItemViewType(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    private final class ViewHolder {
        public TextView titleTxv;
        public TextView authorTxv;
        public ImageView mImageView;
    }
}
