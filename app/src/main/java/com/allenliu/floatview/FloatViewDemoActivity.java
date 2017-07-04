package com.allenliu.floatview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.allenliu.floatview.library.FloatView;

public class FloatViewDemoActivity extends AppCompatActivity {
    FloatView floatView;
    EditText et1;
    EditText et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_view_demo);
        init();


    }

    private void init() {
        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);

        floatView = new FloatView(this, 0, 0, R.layout.floatview_layotu);
        floatView.setFloatViewClickListener(new FloatView.IFloatViewClick() {
            @Override
            public void onFloatViewClick() {
                Toast.makeText(FloatViewDemoActivity.this, "floatview is clicked", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                floatView.removeFromWindow();
                Log.v("SS", "REMOVE");
                break;
            case R.id.btn2:
                floatView.addToWindow();
                break;
            case R.id.btn_update:
                int x = Integer.parseInt(et1.getText().toString());
                int y = Integer.parseInt(et2.getText().toString());
                floatView.updateFloatViewPosition(x, y);
                break;
            case R.id.btn3:
                Intent intent = new Intent();
                intent.setAction("com.allenliu.floatview.REGISTER_CALLSHOW");
                sendBroadcast(intent);
                break;
        }

    }

    @Override
    protected void onStart() {
        floatView.addToWindow();
        super.onStart();
    }

    @Override
    protected void onStop() {
        floatView.removeFromWindow();
        super.onStop();
    }
}
