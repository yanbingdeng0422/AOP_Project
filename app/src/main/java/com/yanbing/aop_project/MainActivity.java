package com.yanbing.aop_project;


import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.LayoutInflaterCompat;

public class MainActivity extends Activity {
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            LayoutInflaterCompat.setFactory2(LayoutInflater.from(this), new LayoutInflater.Factory2() {
                @Nullable
                @Override
                public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
                    long start = System.currentTimeMillis();
                    AppCompatDelegate delegate = getDelegate();
                    View view = delegate.createView(parent, name, context, attrs);
                    Log.d(MainActivity.this.getPackageName(),"main activity "+name+" 绘制耗时>>>> "+(System.currentTimeMillis() - start));
                    return view;
                }

                @Nullable
                @Override
                public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
                    return null;
                }
            });
        }

        MYTest();
        button = findViewById(R.id.bottom);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(MainActivity.class.getSimpleName(),"button click");
            }
        });
    }

    @NonNull
    public AppCompatDelegate getDelegate() {
        return AppCompatDelegate.create(this,this,null);
    }

    private void MYTest() {
        setContentView(R.layout.activity_main);
        Log.d(MainActivity.class.getSimpleName(),"MYTest");
    }
}
