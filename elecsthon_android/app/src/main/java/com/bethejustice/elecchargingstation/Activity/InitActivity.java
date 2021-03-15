package com.bethejustice.elecchargingstation.Activity;

import android.content.Context;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.bethejustice.elecchargingstation.R;
import com.bethejustice.elecchargingstation.Dialog.InitDialog;
import com.pm10.library.CircleIndicator;

public class InitActivity extends AppCompatActivity {

    private static final String TAG = "InitActivity";
    final int NUMBER_OF_INTRO_PAGE = 3;

    ViewPager pager;
    InitDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
        setViewPager();

        Button button = findViewById(R.id.btn_start);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSettingDialog();
            }
        });

    }

    private void setViewPager() {
        pager = findViewById(R.id.viewPager);
        pager.setOffscreenPageLimit(NUMBER_OF_INTRO_PAGE);
        ImageAdapter adapter = new ImageAdapter(getApplicationContext());

        pager.setAdapter(adapter);

        CircleIndicator circleIndicator = findViewById(R.id.circle_indicator);
        circleIndicator.setupWithViewPager(pager);
    }

    private class ImageAdapter extends PagerAdapter {

        int[] images = {R.drawable.tuto1, R.drawable.tuto2, R.drawable.tuto3};
        Context context;

        public ImageAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return images.length;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(images[position]);
            container.addView(imageView, 0);

            return imageView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == ((ImageView) o);
        }
    }

    public void openSettingDialog() {
        Log.d(TAG, "openSettingDialog: started");
        dialog = new InitDialog(InitActivity.this);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.setCancelable(false);
        dialog.show();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        Window window = dialog.getWindow();

        int x = (int) (size.x * 0.9f);
        int y = (int) (size.y * 0.7f);
        window.setLayout(x, y);
    }
}
