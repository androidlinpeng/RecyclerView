package com.msgcopy.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.textView:
                startActivity(new Intent(this,SwipeRecyclerActivity.class));
                break;
            case R.id.textView3:
                startActivity(new Intent(this,RecyclerGalleryActivity.class));
                break;
        }
    }
}
