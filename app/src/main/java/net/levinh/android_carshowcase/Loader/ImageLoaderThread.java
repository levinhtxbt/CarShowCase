package net.levinh.android_carshowcase.Loader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import net.levinh.android_carshowcase.Utils.BitmapCache;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;

/**
 * Created by levinhtxbt@gmail.com on 08/08/2016.
 */
public class ImageLoaderThread extends AsyncTask<String, Void, Bitmap> {

    Context mContext;
    WeakReference<ImageView> weakReference;
    int position;

    public ImageLoaderThread(Context context, ImageView imageView, int position) {
        this.mContext = context;
        this.weakReference = new WeakReference<>(imageView);
        this.position = position;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        String key = strings[0];
        if (BitmapCache.getBitmapCache().getBitmap(key) == null) {
            // decode
            Bitmap bitmap = decodeAndResize(key);
            BitmapCache.getBitmapCache().addBitmapToMemoryCache(key, bitmap);
            return bitmap;
        } else
            return BitmapCache.getBitmapCache().getBitmap(key);
    }

    private Bitmap decodeAndResize(String imagePath) {

        int dstWidth = 400;
        int dstHeight = 400;

        try {
            int inWidth = 0;
            int inHeight = 0;

            Uri uri = Uri.parse(imagePath);
            //Get InputStream by Uri like: android.resource://net.levinh.android_carshowcase/raw/photo
            InputStream in = mContext.getContentResolver().openInputStream(uri);

            //get InputStream by file name
//            in = mContext.getResources().openRawResource(
//                    mContext.getResources().getIdentifier(imagePath, "raw", mContext.getPackageName()));
            // decode image size (decode metadata only, not the whole image)
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(in, null, options);
            in.close();
            in = null;

            // save width and height
            inWidth = options.outWidth;
            inHeight = options.outHeight;

            // decode full image pre-resized

            in = mContext.getContentResolver().openInputStream(uri);
            options = new BitmapFactory.Options();
            // calc rought re-size (this is no exact resize)
            options.inSampleSize = Math.max(inWidth / dstWidth, inHeight / dstHeight);
            // decode full image
            Bitmap roughBitmap = BitmapFactory.decodeStream(in, null, options);

            // calc exact destination size
            Matrix m = new Matrix();
            RectF inRect = new RectF(0, 0, roughBitmap.getWidth(), roughBitmap.getHeight());
            RectF outRect = new RectF(0, 0, dstWidth, dstHeight);
            m.setRectToRect(inRect, outRect, Matrix.ScaleToFit.CENTER);
            float[] values = new float[9];
            m.getValues(values);
            // resize bitmap
            Bitmap resizedBitmap = Bitmap.createScaledBitmap(roughBitmap, (int) (roughBitmap.getWidth() * values[0]), (int) (roughBitmap.getHeight() * values[4]), true);
            return resizedBitmap;
        } catch (IOException e) {
            Log.d("Image", e.getMessage(), e);
            return null;
        }
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        ImageView imageView = weakReference.get();
        if (imageView != null) {
            if (imageView.getTag().equals(position)) {
                imageView.setImageBitmap(bitmap);
            }
        }
    }
}
