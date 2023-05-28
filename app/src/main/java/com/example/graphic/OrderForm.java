package com.example.graphic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class OrderForm extends AppCompatActivity implements View.OnClickListener {
    TextView tv1;
    EditText etAmount, etText, etClientName, etPhoneNumber;
    Button btnConfirmOrder;
    Bundle bundle;
    String productName;

    FirebaseAuth mAuth;

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

        mAuth = FirebaseAuth.getInstance();

        bundle = getIntent().getExtras();
        productName = bundle.getString("productName");
        tv1.setText(productName);

    }

    @Override
    public void onClick(View view) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(etClientName.getText().toString()+" "+mAuth.getCurrentUser().getUid());
        if(mAuth.getCurrentUser()!=null){
            if (TextUtils.isEmpty(etClientName.getText().toString())&&TextUtils.isEmpty(etPhoneNumber.getText().toString())){
                etClientName.setError("שדה חובה");
                etClientName.requestFocus();
            }
            else if (etPhoneNumber.getText().toString().length()!=10){
                etPhoneNumber.setError("מספר פלאפון חייב להיות מעל עשרה ספרות");
                etPhoneNumber.requestFocus();
            }

            else{
                Order order = new Order(tv1.getText().toString(), Integer.parseInt(etAmount.getText().toString()), etText.getText().toString()
                        , etClientName.getText().toString(), etPhoneNumber.getText().toString());

                myRef.setValue(order);
                startActivity(new Intent(OrderForm.this, MainActivity.class));
            }
        }
        else{
            Toast.makeText(this, "אפשרות זו תפתח אחרי הרשמה", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(OrderForm.this, LoginActivity.class));
        }

    }
}