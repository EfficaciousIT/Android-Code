package com.mobi.efficacious.TraffordSchool.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobi.efficacious.TraffordSchool.Interface.DataService;
import com.mobi.efficacious.TraffordSchool.R;
import com.mobi.efficacious.TraffordSchool.adapters.ChatAllUser_Adapter;
import com.mobi.efficacious.TraffordSchool.common.ConnectionDetector;
import com.mobi.efficacious.TraffordSchool.entity.ChatDetail;
import com.mobi.efficacious.TraffordSchool.entity.ChatDetailsPojo;
import com.mobi.efficacious.TraffordSchool.webApi.RetrofitInstance;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


import java.util.ArrayList;

public class Teacher_Chat_Fragment extends Fragment {
    View myview;
    RecyclerView recyclerView;
    private static final String PREFRENCES_NAME = "myprefrences";
    SharedPreferences settings;
    String Academic_id, Schooli_id, role_id, Standard_id="", Division_id="", userid="";
    androidx.appcompat.widget.SearchView searchView;
    ChatAllUser_Adapter adapter;
    ConnectionDetector cd;
    private ProgressDialog progress;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myview = inflater.inflate(R.layout.chat_allusername_recylerview, null);
        recyclerView = (RecyclerView) myview.findViewById(R.id.chat_listview);
        cd = new ConnectionDetector(getActivity());
        settings = getActivity().getSharedPreferences(PREFRENCES_NAME, Context.MODE_PRIVATE);
        searchView = (androidx.appcompat.widget.SearchView) myview.findViewById(R.id.search_view_member);
        searchView.setVisibility(View.GONE);
        Academic_id = settings.getString("TAG_ACADEMIC_ID", "");
        Schooli_id = settings.getString("TAG_SCHOOL_ID", "");
        role_id = settings.getString("TAG_USERTYPEID", "");
        userid = settings.getString("TAG_USERID", "");
        progress = new ProgressDialog(getActivity());
        progress.setCancelable(false);
        progress.setCanceledOnTouchOutside(false);
        progress.setMessage("loading...");
        try {
            if (role_id.contentEquals("2") || role_id.contentEquals("1")) {
                Standard_id = settings.getString("TAG_STANDERDID", "");
                Division_id = settings.getString("TAG_DIVISIONID", "");
                if (!cd.isConnectingToInternet()) {

                    AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                    alert.setMessage("No InternetConnection");
                    alert.setPositiveButton("OK", null);
                    alert.show();

                } else {
                    ChatTeacherAsync ();
                }
            } else {
                if (!cd.isConnectingToInternet()) {

                    AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                    alert.setMessage("No InternetConnection");
                    alert.setPositiveButton("OK", null);
                    alert.show();

                } else {
                    ChatAllTeacherAsync ();
                }
            }
        } catch (Exception ex) {

        }
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });

        return myview;
    }

    private void setupSearchView() {
        searchView.setIconifiedByDefault(false);
        searchView.setSubmitButtonEnabled(true);
        searchView.setQueryHint("Search Teacher Name Here");
    }


    public void ChatTeacherAsync (){
        try {
            DataService service = RetrofitInstance.getRetrofitInstance().create(DataService.class);
            Observable<ChatDetailsPojo> call = service.getChatUserDetails("selectTeacherStandardWise",Schooli_id,"",Standard_id,Division_id,Academic_id);
            call.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ChatDetailsPojo>() {
                @Override
                public void onSubscribe(Disposable disposable) {
                    progress.show();
                }

                @Override
                public void onNext(ChatDetailsPojo body) {
                    try {
                        generateUserList((ArrayList<ChatDetail>) body.getChatDetails());
                    } catch (Exception ex) {
                        progress.dismiss();
                        Toast.makeText(getActivity(), "Response taking time seems Network issue!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onError(Throwable t) {
                    progress.dismiss();
                    Toast.makeText(getActivity(), "Response taking time seems Network issue!", Toast.LENGTH_SHORT).show();

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

    public void generateUserList(ArrayList<ChatDetail> taskListDataList) {
        try {
            if ((taskListDataList != null && !taskListDataList.isEmpty())) {
                adapter = new ChatAllUser_Adapter(taskListDataList,getActivity(),role_id);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
                setupSearchView();
            } else {

            }

        } catch (Exception ex) {
            progress.dismiss();
            Toast.makeText(getActivity(), "Response taking time seems Network issue!", Toast.LENGTH_SHORT).show();
        }
    }

    public void ChatAllTeacherAsync (){
        try {
            String command;
            if (role_id.contentEquals("3")) {
                command="selectTeacher";
            } else if (role_id.contentEquals("6") || role_id.contentEquals("7")) {
                command="selectPrincipal";
            } else {
                command="selectAdmin";
            }
            DataService service = RetrofitInstance.getRetrofitInstance().create(DataService.class);
            Observable<ChatDetailsPojo> call = service.getChatUserDetails(command,Schooli_id,"",Standard_id,Division_id,Academic_id);
            call.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ChatDetailsPojo>() {
                @Override
                public void onSubscribe(Disposable disposable) {
                    progress.show();
                }

                @Override
                public void onNext(ChatDetailsPojo body) {
                    try {
                        generateUserList((ArrayList<ChatDetail>) body.getChatDetails());
                    } catch (Exception ex) {
                        progress.dismiss();
                        Toast.makeText(getActivity(), "Response taking time seems Network issue!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onError(Throwable t) {
                    progress.dismiss();
                    Toast.makeText(getActivity(), "Response taking time seems Network issue!", Toast.LENGTH_SHORT).show();

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

}
