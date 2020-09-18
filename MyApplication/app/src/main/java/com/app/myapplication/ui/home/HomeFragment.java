package com.app.myapplication.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.app.myapplication.Article;
import com.app.myapplication.R;
import com.app.myapplication.RssFeed.RssFeed;
import com.app.myapplication.RssFeed.RssItem;
import com.app.myapplication.RssFeed.RssReader;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.parceler.Parcels;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class HomeFragment extends Fragment implements Serializable {

    private HomeViewModel homeViewModel;

    public ArrayList<RssItem> rssItems;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_home, container, false);
        final ListView listView = root.findViewById(R.id.listview);
        final String xml_url = "https://www.look4website.com/onairdev/rss/rss.php?feed=88&language=en-gb&content=all";

        new Thread(new Runnable() {
            @Override
            public void run() {
                URL url = null;
                try {
                    url = new URL(xml_url);
                    RssFeed feed = RssReader.read(url);
                    rssItems = feed.getRssItems();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                           ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(getActivity()));
                           CustomListAdapter customAdapter = new CustomListAdapter(getActivity() , R.layout.home_fragment_row, rssItems);
                           listView.setAdapter(customAdapter);
                           listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                               @Override
                               public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                   Intent intent = new Intent(getActivity(), Article.class);
                                   intent.putExtra("title", rssItems.get(i).getTitle());
                                   intent.putExtra("link", rssItems.get(i).getLink());
                                   intent.putExtra("description", rssItems.get(i).getDescription());
                                   intent.putExtra("image", rssItems.get(i).getImage());
                                   intent.putExtra("guid", rssItems.get(i).getGuid());
                                   intent.putExtra("comments", rssItems.get(i).getComments());
                                   intent.putExtra("pubDate", rssItems.get(i).getPubDate().toString());
                                   startActivity(intent);
                               }
                           });
                        }
                    });
                }
            }
        }).start();

        return root;
    }



 }

