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

import yangfuwei.xhB17121910.Note.Model.NoteModel;
import yangfuwei.xhB17121910.R;
import yangfuwei.xhB17121910.Utils.DataTimeUtils;

public class NoteListViewAdapter implements ListAdapter {
    private Context mContext;
    private List<NoteModel> articleList;

    public NoteListViewAdapter(Context _context, List<NoteModel> _listModel) {
        mContext = _context;
        if (_listModel == null) {
            articleList = new ArrayList<>();
        } else {
            articleList = _listModel;
        }
    }

    public void setNoteList(List<NoteModel> noteList) {
        articleList = noteList;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int i) {
        return true;
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
            view = LayoutInflater.from(mContext).inflate(R.layout.mynote_list_item, null, true);
            viewHolder.titleTxv = view.findViewById(R.id.title);
            viewHolder.timeTxv = view.findViewById(R.id.time);
            viewHolder.mImageView = view.findViewById(R.id.image);
            view.setTag(viewHolder);
        }
        NoteModel model = articleList.get(i);
        if (model != null) {
            viewHolder.titleTxv.setText(model.getTitle());
            viewHolder.timeTxv.setText(DataTimeUtils.stampToDate(String.valueOf(model.getTime())));
            if (model.getImageUrl() != null && model.getImageUrl().length() != 0) {
                Glide.with(mContext).load(model.getImageUrl()).into(viewHolder.mImageView);
            }
            NoteType noteType = NoteType.get(model.getType());
            if (noteType != null) {
                View contentView = view.findViewById(R.id.itemlayout);
                switch (noteType) {
                    case FRIED:
                        contentView.setBackgroundResource(R.drawable.layout_round_background_fried);
                        break;
                    case PRINCIPLE:
                        contentView.setBackgroundResource(R.drawable.layout_round_background_principle);
                        break;
                    case STREET:
                        contentView.setBackgroundResource(R.drawable.layout_round_background_street);
                        break;

                }
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
        public TextView timeTxv;
        public ImageView mImageView;
    }
}
