package info.efficacious.centralmodelschool.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import java.util.ArrayList;

import info.efficacious.centralmodelschool.Interface.DataService;
import info.efficacious.centralmodelschool.R;
import info.efficacious.centralmodelschool.activity.MainActivity;
import info.efficacious.centralmodelschool.adapters.NoticeBoardAdapter;
import info.efficacious.centralmodelschool.common.ConnectionDetector;
import info.efficacious.centralmodelschool.database.Databasehelper;
import info.efficacious.centralmodelschool.entity.DashboardDetail;
import info.efficacious.centralmodelschool.entity.DashboardDetailsPojo;
import info.efficacious.centralmodelschool.webApi.RetrofitInstance;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class Noticeboard extends Fragment {
    private static final String PREFRENCES_NAME = "myprefrences";
    SharedPreferences settings;
    Databasehelper mydb;
    RecyclerView mrecyclerView;
    RecyclerView.Adapter madapter;
    String Schooli_id, role_id, intStandard_id, academic_id;
    View myview;
    ConnectionDetector cd;
    private ProgressDialog progress;
    FloatingActionButton addButton;
    ArrayList<DashboardDetail> noticeboard = new ArrayList<DashboardDetail>();

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myview = inflater.inflate(R.layout.noticeboard, null);
        settings = getActivity().getSharedPreferences(PREFRENCES_NAME, Context.MODE_PRIVATE);
        Schooli_id = settings.getString("TAG_SCHOOL_ID", "");
        role_id = settings.getString("TAG_USERTYPEID", "");
        academic_id = settings.getString("TAG_ACADEMIC_ID", "");
        addButton = (FloatingActionButton) myview.findViewById(R.id.addButton);
        addButton.setVisibility(View.GONE);
        if (role_id.contentEquals("5") || role_id.contentEquals("6") || role_id.contentEquals("7")) {
            addButton.setVisibility(View.VISIBLE);
        } else {
            addButton.setVisibility(View.GONE);
        }

        try {
            /*if (role_id.contentEquals("1") || role_id.contentEquals("2")) {
                intStandard_id = settings.getString("TAG_STANDERDID", "");
            }*/
            intStandard_id = settings.getString("TAG_STANDERDID", "");
        } catch (Exception ex) {

        }
        progress = new ProgressDialog(getActivity());
        progress.setCancelable(false);
        progress.setCanceledOnTouchOutside(false);
        progress.setMessage("loading...");
        progress.show();
        mrecyclerView = (RecyclerView) myview.findViewById(R.id.chat_recyclerview);
        mydb = new Databasehelper(getActivity(), "Notifications", null, 1);
        cd = new ConnectionDetector(getActivity().getApplicationContext());
        if (!cd.isConnectingToInternet()) {

            AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
            alert.setMessage("No Internet Connection");
            alert.setPositiveButton("OK", null);
            alert.show();

        } else {
            try {
                NoticeboardAsync();
            } catch (Exception ex) {

            }

        }
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),ApplyNotice.class);
                startActivity(intent);
//                NoticeBoard_application noticeBoard_application = new NoticeBoard_application();
//                MainActivity.fragmentManager.beginTransaction().replace(R.id.content_main, noticeBoard_application).commitAllowingStateLoss();
            }
        });
        ((MainActivity) getActivity()).setActionBarTitle("Noticeboard");
        return myview;
    }


    public void NoticeboardAsync() {
        try {
            Observable<DashboardDetailsPojo> call;

            DataService service = RetrofitInstance.getRetrofitInstance().create(DataService.class);
            if (role_id.contentEquals("6") || role_id.contentEquals("7")) {
                call = service.getDashboardDetails("NoticeBoardPrincipal", academic_id, Schooli_id);
            } else if (role_id.contentEquals("1") || role_id.contentEquals("2")) {
                Log.d("TAG", "NoticeboardAsync__" + academic_id +"__"+ Schooli_id+"__" + intStandard_id);
                call = service.getDashboardDetail("NoticeBoardStudent", Schooli_id, intStandard_id);
            } else if (role_id.contentEquals("3")) {
                call = service.getDashboardDetails("NoticeBoard", academic_id, Schooli_id);
            } else {
                call = service.getDashboardDetails("NoticeBoard", "",Schooli_id);
                Log.d("TAG", "NoticeboardAsync" + academic_id + Schooli_id + intStandard_id);
            }
            call.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<DashboardDetailsPojo>() {
                @Override
                public void onSubscribe(Disposable disposable) {
                    progress.show();
                }

                @Override
                public void onNext(DashboardDetailsPojo body) {
                    try {
                        Log.d("TAG", "onNext" + body.getDashboardDetails());
                        generateNoticeList((ArrayList<DashboardDetail>) body.getDashboardDetails());
                    } catch (Exception ex) {
                        progress.dismiss();
                        Toast.makeText(getActivity(), " Response Taking Time,Seems Network issue!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onError(Throwable t) {
                    progress.dismiss();
                    Log.e("TAG", "onError" + t.toString());
                    Toast.makeText(getActivity(), " onError Response Taking Time,Seems Network issue!", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onComplete() {
                    progress.dismiss();
                }
            });
        } catch (Exception ex) {
            progress.dismiss();
        }
    }

    public void generateNoticeList(ArrayList<DashboardDetail> taskListDataList) {
        Log.d("TAG", "generateNoticeList" + taskListDataList.toString());
        try {
            if ((taskListDataList != null && !taskListDataList.isEmpty())) {
                mrecyclerView.setHasFixedSize(false);
                mrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                madapter = new NoticeBoardAdapter(taskListDataList,getContext());
                mrecyclerView.setAdapter(madapter);
            } else {
                Toast toast = Toast.makeText(getActivity(),
                        "No Data Available",
                        Toast.LENGTH_SHORT);
                View toastView = toast.getView();
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toastView.setBackgroundResource(R.drawable.no_data_available);
                toast.show();
            }

        } catch (Exception ex) {
            progress.dismiss();
            Toast.makeText(getActivity(), " Response Taking Time,Seems Network issue!", Toast.LENGTH_SHORT).show();
        }
    }
}