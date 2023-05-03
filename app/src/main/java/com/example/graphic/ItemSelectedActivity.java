package com.example.graphic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemSelectedActivity extends AppCompatActivity {
    TextView tv1;
    ImageView iv1;
    Bundle bundle;
    String categoryName, productName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_selected);
        tv1 = findViewById(R.id.tv1);
        iv1 = findViewById(R.id.iv1);

        bundle = getIntent().getExtras();
        if (bundle != null) {
            categoryName = bundle.getString("categoryName");
            productName = bundle.getString("productName");
            if (categoryName != null && productName != null) {
                if (categoryName.equals("חולצות"))
                    ProductShirt(productName);
                else if (categoryName.equals("מגבות"))
                    ProductTowel(productName);
                else if (categoryName.equals("כריות"))
                    iv1.setImageResource(R.drawable.pillow);
            }
        }
    }
    public void ProductShirt(String productName){
        tv1.setText(productName);
        iv1.setImageResource(R.drawable.shirt);
    }
    public void ProductTowel(String productName){
        if (productName.equals("מגבת גוף מיקרופייבר")){
            tv1.setText(productName);
            iv1.setImageResource(R.drawable.micro_body_towel_1);
        }
        if (productName.equals("מגבת תינוק קטנה")){
            tv1.setText(productName);
            iv1.setImageResource(R.drawable.bear_towel);
        }
        if (productName.equals("מגבת ידיים")){
            tv1.setText(productName);
            iv1.setImageResource(R.drawable.hand_towel_1);
        }
        if (productName.equals("מגבת גוף")){
            tv1.setText(productName);
            iv1.setImageResource(R.drawable.body_towel);
        }
    }
    public void ProductPillow(String productName){
        if (productName.equals("כרית עם הדפס")){
            tv1.setText(productName);
            iv1.setImageResource(R.drawable.patterned_pillow);
        }
        if (productName.equals("ציפה לכרית שינה")){
            tv1.setText(productName);
            iv1.setImageResource(R.drawable.pillow_cover_1);
        }
        if (productName.equals("כרית לב")){
            tv1.setText(productName);
            iv1.setImageResource(R.drawable.pillow_with_hands);
        }
        if (productName.equals("כרית תאריך לידה")){
            tv1.setText(productName);
            iv1.setImageResource(R.drawable.birth_pillow);
        }
    }

}
