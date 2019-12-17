package com.example.time1.ui.zhutise_3;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.time1.LeftmenuNewMainActivity;
import com.example.time1.R;

public class SlideshowFragment extends Fragment {
    private RelativeLayout relativeLayout;
    private SeekBar color_R,color_G,color_B;
    public  int r = 0,g = 0,b = 0;
    private TextView int_r,int_g,int_b;

    private SlideshowViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_5_zhutise, container, false);
        //final TextView textView = root.findViewById(R.id.nav_view);

        relativeLayout = root. findViewById(R.id.RelativeLayout);

        color_R = root.findViewById(R.id.R);
        color_G = root. findViewById(R.id.G);
        color_B = root. findViewById(R.id.B);
        int_r = root. findViewById(R.id.int_R);
        int_g = root. findViewById(R.id.int_G);
        int_b = root. findViewById(R.id.int_B);

        color_R.setMax(255);
        color_G.setMax(255);
        color_B.setMax(255);

        color_B.setProgress(0);
        color_G.setProgress(0);
        color_B.setProgress(0);

        color_R.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                r = seekBar.getProgress();
                String int_color_r = "R:" + String.valueOf(r);
                int_r.setText(int_color_r);
                relativeLayout.setBackgroundColor(Color.rgb(r, g, b));
                ((LeftmenuNewMainActivity)getActivity()).fun(Color.rgb(r, g, b));
            }
        });

        color_G.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                g = seekBar.getProgress();
                String int_color_g = "G:" + String.valueOf(g);
                int_g.setText(int_color_g);
                relativeLayout.setBackgroundColor(Color.rgb(r, g, b));
                ((LeftmenuNewMainActivity)getActivity()).fun(Color.rgb(r, g, b));
            }
        });

        color_B.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                b = seekBar.getProgress();
                String int_color_b = "B:" + String.valueOf(b);
                int_b.setText(int_color_b);
                relativeLayout.setBackgroundColor(Color.rgb(r, g, b));

                ((LeftmenuNewMainActivity)getActivity()).fun(Color.rgb(r, g, b));
            }
        });

        return root;
    }
}