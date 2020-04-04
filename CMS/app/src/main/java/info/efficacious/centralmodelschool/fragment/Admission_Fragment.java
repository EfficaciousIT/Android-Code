package info.efficacious.centralmodelschool.fragment;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import info.efficacious.centralmodelschool.R;


public class Admission_Fragment extends Fragment {
    View myview;
    TextView tv_link;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myview = inflater.inflate(R.layout.activity_address_form, null);
        tv_link = (TextView) myview.findViewById(R.id.tv_link);
        tv_link.setMovementMethod(LinkMovementMethod.getInstance());
        return myview;

    }
}

