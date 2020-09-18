package com.app.myapplication.RssFeed;

/*
 * Copyright (C) 2011 Mats Hofman <http://matshofman.nl/contact/>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.Serializable;
import java.util.ArrayList;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public class RssFeed implements Parcelable, Serializable {

    private String title;
    private String link;
    private String description;
    private String language;
    private String image;
    private String guid;
    private String  comments;
    private ArrayList<RssItem> rssItems;

    public RssFeed() {
        rssItems = new ArrayList<RssItem>();
    }

    public RssFeed(Parcel source) {
        Bundle data = source.readBundle();
        title = data.getString("title");
        link = data.getString("link");
        description = data.getString("description");
        image = data.getString("image");
        guid = data.getString("guid");
        comments = data.getString("comments");
        language = data.getString("language");
        rssItems = data.getParcelableArrayList("rssItems");
    }

    public RssFeed(String title, String link, String description, String language, String image, String guid, String comments) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.language = language;
        this.image = image;
        this.guid = guid;
        this.comments = comments;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        Bundle data = new Bundle();
        data.putString("title", title);
        data.putString("link", link);
        data.putString("image",image);
        data.putString("guid",guid);
        data.putString("description", description);
        data.putString("comments",comments);
        data.putString("language", language);
        data.putParcelableArrayList("rssItems", rssItems);
        dest.writeBundle(data);
    }

    public static final Parcelable.Creator<RssFeed> CREATOR = new Parcelable.Creator<RssFeed>() {
        public RssFeed createFromParcel(Parcel data) {
            return new RssFeed(data);
        }
        public RssFeed[] newArray(int size) {
            return new RssFeed[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    void addRssItem(RssItem rssItem) {
        rssItems.add(rssItem);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage(){ return image; }

    public void setImage(String image){ this.image = image; }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public ArrayList<RssItem> getRssItems() {
        return rssItems;
    }

    public void setRssItems(ArrayList<RssItem> rssItems) {
        this.rssItems = rssItems;
    }

    @Override
    public String toString() {
        return "RssFeed{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", description='" + description + '\'' +
                ", language='" + language + '\'' +
                ", image='" + image + '\'' +
                ", guid='" + guid + '\'' +
                ", comments='" + comments + '\'' +
                ", rssItems=" + rssItems +
                '}';
    }

}
