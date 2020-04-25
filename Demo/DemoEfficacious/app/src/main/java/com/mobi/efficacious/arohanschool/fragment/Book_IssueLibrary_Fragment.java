package com.mobi.efficacious.arohanschool.fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.mobi.efficacious.arohanschool.Interface.DataService;
import com.mobi.efficacious.arohanschool.R;
import com.mobi.efficacious.arohanschool.activity.MainActivity;
import com.mobi.efficacious.arohanschool.adapters.BookIssueLiabraryAdapter;
import com.mobi.efficacious.arohanschool.common.ConnectionDetector;
import com.mobi.efficacious.arohanschool.entity.LibraryDetail;
import com.mobi.efficacious.arohanschool.entity.LibraryDetailPojo;
import com.mobi.efficacious.arohanschool.webApi.RetrofitInstance;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by EFF on 2/23/2017.
 */

public class Book_IssueLibrary_Fragment  extends Fragment {
    View myview;
    private static final String PREFRENCES_NAME = "myprefrences";
    SharedPreferences settings;
    SearchView searchView;
    String Schooli_id,student_id,standard_id,role_id;
    ConnectionDetector cd;
    RecyclerView recyclerView;
    private ProgressDialog progress;
    BookIssueLiabraryAdapter madapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myview=inflater.inflate(R.layout.activity_allstudent,null);
        ((MainActivity) getActivity()).setActionBarTitle("Book Issue");
        cd = new ConnectionDetector(getActivity());
        searchView = (SearchView)myview.findViewById(R.id.search_view_student);
        settings = getActivity().getSharedPreferences(PREFRENCES_NAME, Context.MODE_PRIVATE);
        Schooli_id= settings.getString("TAG_SCHOOL_ID", "");
        student_id = settings.getString("TAG_USERID", "");
        standard_id = settings.getString("TAG_STANDERDID", "");
        role_id = settings.getString("TAG_USERTYPEID", "");
        recyclerView  = (RecyclerView) myview.findViewById(R.id.allstudent_list);
        progress = new ProgressDialog(getActivity());
        progress.setCancelable(false);
        progress.setCanceledOnTouchOutside(false);
        progress.setMessage("loading...");
        myview.setFocusableInTouchMode(true);
        myview.requestFocus();
        myview.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        try {
                            if (role_id.contentEquals("1")||role_id.contentEquals("2")) {
                                try {
                                    StandardWise_Book standardWise_book = new StandardWise_Book();
                                    MainActivity.fragmentManager.beginTransaction().replace(R.id.content_main, standardWise_book).commitAllowingStateLoss();
                                } catch (Exception ex) {

                                }


                            }
                        } catch (Exception ex) {

                        }


                        return true;
                    }
                }
                return false;
            }
        });
        if (!cd.isConnectingToInternet())
        {

            AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
            alert.setMessage("No InternetConnection");
            alert.setPositiveButton("OK",null);
            alert.show();

        }

        else {

            StudentAsync ();
        }
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                madapter.getFilter().filter(newText);
                return true;
            }
        });
        return myview;
    }
    private void setupSearchView()
    {
        searchView.setIconifiedByDefault(false);
        searchView.setSubmitButtonEnabled(true);
        searchView.setQueryHint("Search Book Name Here");
    }
    public void  StudentAsync (){
        try {
            DataService service = RetrofitInstance.getRetrofitInstance().create(DataService.class);
            Observable<LibraryDetailPojo> call = service.getLibraryDetails("selectAssignedBook",Schooli_id,standard_id,student_id);
            call.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<LibraryDetailPojo>() {
                @Override
                public void onSubscribe(Disposable disposable) {
                    progress.show();
                }

                @Override
                public void onNext(LibraryDetailPojo body) {
                    try {
                        generateBookList((ArrayList<LibraryDetail>) body.getLibraryDetail());
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

    public void generateBookList(ArrayList<LibraryDetail> taskListDataList) {
        try {
            if ((taskListDataList != null && !taskListDataList.isEmpty())) {
                madapter = new BookIssueLiabraryAdapter(taskListDataList,getActivity());

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());

                recyclerView.setLayoutManager(layoutManager);

                recyclerView.setAdapter(madapter);
                setupSearchView();
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
            Toast.makeText(getActivity(), "Response taking time seems Network issue!", Toast.LENGTH_SHORT).show();
        }
    }
}

