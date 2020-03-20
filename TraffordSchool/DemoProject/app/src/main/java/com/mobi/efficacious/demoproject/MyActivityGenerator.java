package com.mobi.efficacious.demoproject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Random;

public class MyActivityGenerator extends ViewModel {

    public String TAG=this.getClass().getSimpleName();
    public MutableLiveData<String> myRandomnumber;
//    private String myRandomnumber;

   public LiveData<String> getNumber(){
       if (myRandomnumber==null)
       {
           if (myRandomnumber==null)
           {
               myRandomnumber=new MutableLiveData<>();
               createnumber();
           }

       }
       return myRandomnumber;

    }

    public void createnumber() {
        Random random=new Random();

        myRandomnumber.setValue("Number"+(random.nextInt(10-1)+1));

    }
}
