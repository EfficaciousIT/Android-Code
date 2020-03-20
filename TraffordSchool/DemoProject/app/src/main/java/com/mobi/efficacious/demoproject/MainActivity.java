package com.mobi.efficacious.demoproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public String TAG=this.getClass().getSimpleName();
    TextView textView;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.fetch);
        NoteViewModel noteViewModel;

        textView=findViewById(R.id.numbertextv);
//        MyActivityGenerator myActivityGenerator=new MyActivityGenerator();
        final MyActivityGenerator model= ViewModelProviders.of(this).get(MyActivityGenerator.class);

        LiveData<String> randomnum=model.getNumber();
        randomnum.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

                textView.setText(s);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.createnumber();
            }
        });

        Log.e("TAG","MainActivity_ONCREATE");
        noteViewModel=ViewModelProviders.of(this).get(NoteViewModel.class);

        getLifecycle().addObserver(new MainActivityObserver());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG,"MainActivity_onStart");
        getLifecycle().addObserver(new MainActivityObserver());
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG,"MainActivity_onPause");
        getLifecycle().addObserver(new MainActivityObserver());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG,"MainActivity_onResume");
        getLifecycle().addObserver(new MainActivityObserver());
    }
}
