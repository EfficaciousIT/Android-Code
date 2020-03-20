package info.efficacious.centralmodelschool.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import info.efficacious.centralmodelschool.Interface.DataService;
import info.efficacious.centralmodelschool.R;
import info.efficacious.centralmodelschool.Tab.TimetableActivity_teacher;
import info.efficacious.centralmodelschool.adapters.StudentTimetableAdapter;
import info.efficacious.centralmodelschool.entity.TimeTableDetail;
import info.efficacious.centralmodelschool.entity.TimeTableDetailPojo;
import info.efficacious.centralmodelschool.webApi.RetrofitInstance;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by EFF-4 on 3/22/2018.
 */

public class Monday_teacher_Fragment extends Fragment {
    String techer_id,academic_id,school_id,role_id,div_id;
    private static final String PREFRENCES_NAME = "myprefrences";
    SharedPreferences settings;
    RecyclerView mrecyclerView;
    RecyclerView.Adapter madapter;
    private CompositeDisposable mCompositeDisposable;
    private ProgressDialog progress;
    public Monday_teacher_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mrecyclerView = (RecyclerView) getActivity().findViewById(R.id.teacherattb_list);
        settings = getActivity().getSharedPreferences(PREFRENCES_NAME, Context.MODE_PRIVATE);
        role_id = settings.getString("TAG_USERTYPEID", "");
        div_id=settings.getString("TAG_DIVISIONID","");

//        role_id = settings.getString("TAG_USERTYPEID", "");
        try
        {
            if(role_id.contentEquals("6")||role_id.contentEquals("7"))
            {
                school_id= TimetableActivity_teacher.intSchool_id;

            }else
            {
                school_id= settings.getString("TAG_SCHOOL_ID", "");
            }

            academic_id = settings.getString("TAG_ACADEMIC_ID", "");
            techer_id= TimetableActivity_teacher.teacher_id;
        }catch (Exception ex)
        {

        }


        try
        {
            StudtimeAsync ();
        }catch (Exception ex)
        {

        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.monday_teacher, container, false);
    }

    public void  StudtimeAsync (){
        try {
            DataService service = RetrofitInstance.getRetrofitInstance().create(DataService.class);
            Log.d("TAG","StudtimeAsync"+school_id+"__"+"monday"+academic_id+"__"+techer_id+"__"+div_id);
            mCompositeDisposable.add(service.getTimeTableDetails("select",school_id,"monday",academic_id,"",techer_id,"")
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribeWith(getObserver()));
        } catch (Exception ex) {
        }
    }
    public DisposableObserver<TimeTableDetailPojo> getObserver(){
        return new DisposableObserver<TimeTableDetailPojo>() {

            @Override
            public void onNext(@NonNull TimeTableDetailPojo timeTableDetailPojo) {
                try {
                    Log.d("TAG","timeTableDetailPojo"+timeTableDetailPojo.getTimeTableDetail().toString());
                    generateTimetableList((ArrayList<TimeTableDetail>) timeTableDetailPojo.getTimeTableDetail());
                } catch (Exception ex) {
                    Toast.makeText(getActivity(), "Response Taking Time,Seems Network issue! in  ", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Toast.makeText(getActivity(), "Response Taking Time,Seems Network issue! in  ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {
            }
        };
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }
    }


    public void generateTimetableList(ArrayList<TimeTableDetail> taskListDataList) {
        try {
            if ((taskListDataList != null && !taskListDataList.isEmpty())) {
                madapter = new StudentTimetableAdapter(getActivity(),taskListDataList,"Teacher");

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());

                mrecyclerView.setLayoutManager(layoutManager);

                mrecyclerView.setAdapter(madapter);
            } else {


            }

        } catch (Exception ex) {
            Toast.makeText(getActivity(), "Response Taking Time,Seems Network issue!", Toast.LENGTH_SHORT).show();
        }
    }


}

