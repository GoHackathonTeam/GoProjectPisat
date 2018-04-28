package com.dekinci.lksbstu.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.util.function.Consumer;

public class ImageLoader extends AsyncTask<String, Void, Bitmap> {
    private Consumer<Bitmap> consumer;

    public ImageLoader(Consumer<Bitmap> consumer) {
        this.consumer = consumer;
    }

    protected Bitmap doInBackground(String... urls) {
        String urlDisplay = urls[0];
        Bitmap img = null;
        try {
            InputStream in = new java.net.URL(urlDisplay).openStream();
            img = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error getting image", e.getClass().getName() + " " + e.getMessage());
        }
        return img;
    }


    protected void onPostExecute(Bitmap result) {
        consumer.accept(result);
    }

}
