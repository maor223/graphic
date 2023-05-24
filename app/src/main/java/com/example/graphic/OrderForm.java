package com.example.graphic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class OrderForm extends AppCompatActivity implements View.OnClickListener {
    TextView tv1;
    EditText etAmount, etText, etClientName, etPhoneNumber;
    Button btnConfirmOrder;
    Bundle bundle;
    String productName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_form);
        tv1 = findViewById(R.id.tv1);
        etAmount = findViewById(R.id.etAmount);
        etText = findViewById(R.id.etText);
        etClientName = findViewById(R.id.etClientName);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        btnConfirmOrder = findViewById(R.id.btnConfirmOrder);
        btnConfirmOrder.setOnClickListener(this);

        bundle = getIntent().getExtras();
        productName = bundle.getString("productName");
        tv1.setText(productName);

    }

    @Override
    public void onClick(View view) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(etClientName.getText().toString());

        if (TextUtils.isEmpty(etClientName.getText().toString())&&TextUtils.isEmpty(etPhoneNumber.getText().toString())){
            etClientName.setError("שדה חובה");
            etClientName.requestFocus();
            etPhoneNumber.setError("שדה חובה");
            etPhoneNumber.requestFocus();
        }

        else{
            Order order = new Order(tv1.getText().toString(), Integer.parseInt(etAmount.getText().toString()), etText.getText().toString()
                    , etClientName.getText().toString(), etPhoneNumber.getText().toString());

            myRef.setValue(order);
        }
    }
}