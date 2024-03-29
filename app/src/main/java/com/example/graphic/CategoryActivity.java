package com.example.graphic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CategoryActivity extends AppCompatActivity implements View.OnClickListener {
    TextView categoryTv1, tv1, tv2, tv3, tv4;
    ImageView iv1, iv2, iv3, iv4;
    LinearLayout LProduct1, LProduct2, LProduct3, LProduct4;
    Bundle bundle;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        LProduct1 = findViewById(R.id.LProduct1);
        LProduct1.setOnClickListener(this);
        LProduct2 = findViewById(R.id.LProduct2);
        LProduct2.setOnClickListener(this);
        LProduct3 = findViewById(R.id.LProduct3);
        LProduct3.setOnClickListener(this);
        LProduct4 = findViewById(R.id.LProduct4);
        LProduct4.setOnClickListener(this);

        tv1 = findViewById(R.id.categoryTv1);
        tv2 = findViewById(R.id.categoryTv2);
        tv3 = findViewById(R.id.categoryTv3);
        tv4 = findViewById(R.id.categoryTv4);


        iv1 = findViewById(R.id.categoryIv1);
        iv2 = findViewById(R.id.categoryIv2);
        iv3 = findViewById(R.id.categoryIv3);
        iv4 = findViewById(R.id.categoryIv4);


        categoryTv1 = findViewById(R.id.tv1);

        bundle = getIntent().getExtras();
        if (bundle != null) {
            name = bundle.getString("name");
            if (name != null) {
                categoryTv1.setText(name);
                if (name.equals("מגבות")) {
                    Towels();
                } else if (name.equals("כריות")) {
                    Pillowes();
                }
                else if (name.equals("מחזיקי מפתחות")) {
                    KeyChain();
                }
                else if (name.equals("לוחות שנה")) {
                    Calender();
                }
                else if (name.equals("מארזים")) {
                    Maarazim();
                }
                else if (name.equals("תינוקות")) {
                    Babies();
                }
                else if (name.equals("עוד")) {
                    Others();
                }
            }
        }
    }

    public void Towels(){
        iv1.setImageResource(R.drawable.micro_body_towel_1);
        tv1.setText("מגבת גוף מיקרופייבר");
        iv2.setImageResource(R.drawable.bear_towel);
        tv2.setText("מגבת תינוק קטנה");
        iv3.setImageResource(R.drawable.hand_towel_1);
        tv3.setText("מגבת ידיים");
        iv4.setImageResource(R.drawable.body_towel);
        tv4.setText("מגבת גוף");
    }
    public void Pillowes(){
        iv1.setImageResource(R.drawable.patterned_pillow);
        tv1.setText("כרית עם הדפס");
        iv2.setImageResource(R.drawable.pillow_cover_1);
        tv2.setText("ציפה לכרית שינה");
        iv3.setImageResource(R.drawable.pillow_with_hands);
        tv3.setText("כרית לב");
        iv4.setImageResource(R.drawable.birth_pillow);
        tv4.setText("כרית תאריך לידה");
    }
    public void KeyChain(){
        iv1.setImageResource(R.drawable.bear_key_chain);
        tv1.setText("מחזיק מפתחות דובי");
        iv2.setImageResource(R.drawable.house_key_chain);
        tv2.setText("מחזיק מפתחות מעץ בית");
        iv3.setImageResource(R.drawable.square_key_chain);
        tv3.setText("מחזיק מפתחות מעץ ריבוע");
    }
    public void Calender(){
        iv1.setImageResource(R.drawable.block_calendar);
        tv1.setText("לוח שנה על בלוק");
        iv2.setImageResource(R.drawable.mini_calendar);
        tv2.setText("לוח שנה קטן מגנט");
        iv3.setImageResource(R.drawable.calendar_picture);
        tv3.setText("לוח שנה לועזי כולל מגנט עליון");
    }
    public void Maarazim(){
        iv1.setImageResource(R.drawable.pesach);
        tv1.setText("מארז לפסח");
        iv2.setImageResource(R.drawable.mangal);
        tv2.setText("מארז למנגליסט");
        iv3.setImageResource(R.drawable.maaraz1);
        tv3.setText("מארז לתינוק");
        iv4.setImageResource(R.drawable.maaraz2);
        tv4.setText("מארז לאמא/אבא");
    }
    public void Babies(){
        iv1.setImageResource(R.drawable.baby_apron);
        tv1.setText("סינר תינוק");
        iv2.setImageResource(R.drawable.baby_bodysuit);
        tv2.setText("בגד גוף תינוק ללא רגלית");
        iv3.setImageResource(R.drawable.cloth_diaperes);
        tv3.setText("חיתולי בד עם הטבעת שם");
    }
    public void Others(){
        iv1.setImageResource(R.drawable.shirt);
        tv1.setText("חולצת דרייפיט עם הדפס");
        iv2.setImageResource(R.drawable.cup);
        tv2.setText("ספל במיתוג אישי");
        iv3.setImageResource(R.drawable.block);
        tv3.setText("בלוק עץ אורן עם תמונה");
        iv4.setImageResource(R.drawable.cutting_board);
        tv4.setText("קרש חיתוך צריבה מעוצבת");
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(CategoryActivity.this, ItemSelectedActivity.class);
        if(view==LProduct1){
            intent.putExtra("categoryName", name);
            intent.putExtra("productName", tv1.getText().toString());
            startActivity(intent);
        }
        if(view==LProduct2){
            intent.putExtra("categoryName", name);
            intent.putExtra("productName", tv2.getText().toString());
            startActivity(intent);
        }
        if(view==LProduct3){
            intent.putExtra("categoryName", name);
            intent.putExtra("productName", tv3.getText().toString());
            startActivity(intent);
        }
        if(view==LProduct4){
            intent.putExtra("categoryName", name);
            intent.putExtra("productName", tv4.getText().toString());
            startActivity(intent);
        }
    }
    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        startActivity(new Intent(CategoryActivity.this, MainActivity.class));
        return true;
    }
}