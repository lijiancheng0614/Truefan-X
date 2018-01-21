package io.github.lijiancheng0614.truefanx;

import android.content.ContentResolver;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class DemoActivity extends AppCompatActivity {

    public static final String sPath = "file:///mnt/sdcard/Download/vr/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        // full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final EditText et = (EditText) findViewById(R.id.edit_text_url);

        final SparseArray<String> data = new SparseArray<>();

        data.put(data.size(), "http://s3-eu-west-1.amazonaws.com/fcb-output-hls/Stands/index.m3u8");
        data.put(data.size(), sPath + "4k.mp4");
        data.put(data.size(), sPath + "1080.mp4");
        data.put(data.size(), "http://cache.utovr.com/201508270528174780.m3u8");
//        data.put(data.size(), "rtsp://218.204.223.237:554/live/1/66251FC11353191F/e7ooqwcfbqjoo80j.sdp");

        data.put(data.size(), getDrawableUri(R.drawable.bitmap360).toString());
        data.put(data.size(), getDrawableUri(R.drawable.texture).toString());
        data.put(data.size(), getDrawableUri(R.drawable.dome_pic).toString());
        data.put(data.size(), getDrawableUri(R.drawable.stereo).toString());
        data.put(data.size(), getDrawableUri(R.drawable.multifisheye).toString());
        data.put(data.size(), getDrawableUri(R.drawable.multifisheye2).toString());
        data.put(data.size(), getDrawableUri(R.drawable.fish2sphere180sx2).toString());
        data.put(data.size(), getDrawableUri(R.drawable.fish2sphere180s).toString());

        SpinnerHelper.with(this)
                .setData(data)
                .setClickHandler(new SpinnerHelper.ClickHandler() {
                    @Override
                    public void onSpinnerClicked(int index, int key, String value) {
                        et.setText(value);
                    }
                })
                .init(R.id.spinner_url);

        findViewById(R.id.video_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String url = et.getText().toString();
                String url = data.get(0).toString();
                if (!TextUtils.isEmpty(url)) {
                    MD360PlayerActivity.startVideo(DemoActivity.this, Uri.parse(url));
                } else {
                    Toast.makeText(DemoActivity.this, "empty url!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.bitmap_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = et.getText().toString();
                if (!TextUtils.isEmpty(url)) {
                    MD360PlayerActivity.startBitmap(DemoActivity.this, Uri.parse(url));
                } else {
                    Toast.makeText(DemoActivity.this, "empty url!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.ijk_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = et.getText().toString();
                if (!TextUtils.isEmpty(url)) {
                    IjkPlayerDemoActivity.start(DemoActivity.this, Uri.parse(url));
                } else {
                    Toast.makeText(DemoActivity.this, "empty url!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.cubemap_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = et.getText().toString();

                MD360PlayerActivity.startCubemap(DemoActivity.this, null);
            }
        });

        findViewById(R.id.recycler_view_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerViewActivity.start(DemoActivity.this);
            }
        });
    }

    private Uri getDrawableUri(@DrawableRes int resId) {
        Resources resources = getResources();
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + resources.getResourcePackageName(resId) + '/' + resources.getResourceTypeName(resId) + '/' + resources.getResourceEntryName(resId));
    }
}
