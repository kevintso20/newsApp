package com.app.myapplication.ui.home;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.app.myapplication.R;
import com.app.myapplication.RssFeed.RssItem;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.Date;
import java.util.List;

public class CustomListAdapter extends ArrayAdapter<RssItem> {

    private int resourceLayout;
    private Context mContext;

    public CustomListAdapter(Context context, int resource, List<RssItem> items) {
        super(context, resource, items);
        this.resourceLayout = resource;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            view = vi.inflate(resourceLayout, null);
        }
        RssItem p = getItem(position);

        TextView title = (TextView) view.findViewById(R.id.title);
        ImageView image = (ImageView) view.findViewById(R.id.image);
        TextView date = (TextView) view.findViewById(R.id.date);
        TextView time = (TextView) view.findViewById(R.id.time);
        TextView description = (TextView) view.findViewById(R.id.description);

        Date str = p.getPubDate();
        String[] arrayDate = str.toString().split(" ");
        String[] arrayTime = arrayDate[3].split(":");


        date.setText(arrayDate[0] + "\n " + arrayDate[2] + " " + arrayDate[1]);
        time.setText(arrayTime[0]+":"+arrayTime[1]);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            title.setText(Html.fromHtml(p.getTitle(), Html.FROM_HTML_MODE_COMPACT));
            description.setText(Html.fromHtml(p.getDescription(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            title.setText(Html.fromHtml(p.getTitle()));
            description.setText(Html.fromHtml(p.getDescription()));
        }

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(p.getImage(), image);

        return view;
    }



}