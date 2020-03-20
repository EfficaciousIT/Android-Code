package com.mobi.efficacious.demoproject;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class NoteViewModel extends AndroidViewModel {
    String TAG=this.getClass().getSimpleName();

    public NoteViewModel(@NonNull Application application) {
        super(application);

    }

}
