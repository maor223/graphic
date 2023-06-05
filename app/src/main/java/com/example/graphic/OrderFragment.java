package com.example.graphic;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class OrderFragment extends Fragment implements View.OnClickListener {

    View view;
    Button btnConfirm, btnCall;
    ImageView ivWhatsapp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_order, container, false);
        btnConfirm = (Button)view.findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(this);
        btnCall = view.findViewById(R.id.btnCall);
        btnCall.setOnClickListener(this);
        ivWhatsapp = view.findViewById(R.id.ivwhatsapp);
        ivWhatsapp.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view==btnConfirm){
            getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        }
        else if (view==btnCall){
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_CALL);
            Uri data = Uri.parse("tel:" + "52-11101111");
            intent.setData(data);
            startActivity(intent);
        }
        else{
            openWhatsApp();
        }
    }
    public void openWhatsApp(){
        try {
            String text = "היי הייתי רוצה לקבל פרטים נוספים לגבי המוצר שהזמנתי";// the message.

            String toNumber = "972545561643"; // Replace with mobile phone number without +Sign or leading zeros, but with country code
            //Suppose your country is India and your phone number is “xxxxxxxxxx”, then you need to send “91xxxxxxxxxx”.


            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+toNumber +"&text="+text));
            startActivity(intent);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}