package info.efficacious.centralmodelschool.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import info.efficacious.centralmodelschool.R;

public class AddressForm extends AppCompatActivity {

    TextView tv_link;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_form);
        tv_link = (TextView) findViewById(R.id.tv_link);
        tv_link.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
