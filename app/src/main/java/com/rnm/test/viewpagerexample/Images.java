package com.rnm.test.viewpagerexample;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Mahe on 11/15/2017.
 */

public class Images implements Parcelable {


    public final String format;
    public final int width;
    public final int height;
    public final String filename;
    public final long id;
    public final String author;
    public final String author_url;
    public final String post_url;

    public Images(String format,
                 int width,
                 int height,
                 String filename,
                 long id,
                 String author,
                 String author_url,
                 String post_url) {
        this.format = format;
        this.width = width;
        this.height = height;
        this.filename = filename;
        this.id = id;
        this.author = author;
        this.author_url = author_url;
        this.post_url = post_url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(format);
        dest.writeInt(width);
        dest.writeInt(height);
        dest.writeString(filename);
        dest.writeLong(id);
        dest.writeString(author);
        dest.writeString(author_url);
        dest.writeString(post_url);

    }

    protected Images (Parcel in) {
        this.format = in.readString();
        this.width = in.readInt();
        this.height = in.readInt();
        this.filename = in.readString();
        this.id = in.readLong();
        this.author = in.readString();
        this.author_url = in.readString();
        this.post_url = in.readString();

    }

    public static final Creator<Images> CREATOR = new Creator<Images>() {
        @Override
        public Images createFromParcel(Parcel in) {
            return new Images(in);
        }

        @Override
        public Images[] newArray(int size) {
            return new Images[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Images images = (Images) o;

        if (width != images.width) return false;
        if (height != images.height) return false;
        if (id != images.id) return false;
        if (format != null ? !format.equals(images.format) : images.format != null) return false;
        if (filename != null ? !filename.equals(images.filename) : images.filename != null)
            return false;
        if (author != null ? !author.equals(images.author) : images.author != null) return false;
        if (author_url != null ? !author_url.equals(images.author_url) : images.author_url != null)
            return false;
        return post_url != null ? post_url.equals(images.post_url) : images.post_url == null;

    }

    @Override
    public int hashCode() {
        int result = format != null ? format.hashCode() : 0;
        result = 31 * result + width;
        result = 31 * result + height;
        result = 31 * result + (filename != null ? filename.hashCode() : 0);
        result = 31 * result + (int) (id ^ (id >>> 32));
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (author_url != null ? author_url.hashCode() : 0);
        result = 31 * result + (post_url != null ? post_url.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Images{" +
                "format='" + format + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", filename='" + filename + '\'' +
                ", id=" + id +
                ", author='" + author + '\'' +
                ", author_url='" + author_url + '\'' +
                ", post_url='" + post_url + '\'' +
                '}';
    }
}
