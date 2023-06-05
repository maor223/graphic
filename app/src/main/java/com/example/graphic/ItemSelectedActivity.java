package com.example.graphic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class ItemSelectedActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tv1;
    ImageView iv1;
    Button btnOrder;
    Bundle bundle;
    String categoryName, productName;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_selected);
        btnOrder = findViewById(R.id.btnOrder);
        btnOrder.setOnClickListener(this);
        tv1 = findViewById(R.id.tv1);
        iv1 = findViewById(R.id.iv1);

        bundle = getIntent().getExtras();
        if (bundle != null) {
            categoryName = bundle.getString("categoryName");
            productName = bundle.getString("productName");
            if (categoryName != null && productName != null) {
                if (categoryName.equals("מגבות"))
                    ProductTowel(productName);
                else if (categoryName.equals("כריות"))
                    ProductPillow(productName);
                else if (categoryName.equals("מחזיקי מפתחות"))
                    ProductKeyChain(productName);
                else if (categoryName.equals("לוחות שנה"))
                    ProductCalender(productName);
                else if (categoryName.equals("מארזים"))
                    ProductMaarazim(productName);
                else if (categoryName.equals("תינוקות"))
                    ProductBabies(productName);
                else if (categoryName.equals("עוד"))
                    ProductOthers(productName);
            }
        }

        mAuth = FirebaseAuth.getInstance();
    }
    //מגבות
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
    //כריות
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
    //מחזיק מפתחות
    public void ProductKeyChain(String productName){
        if (productName.equals("מחזיק מפתחות דובי")){
            tv1.setText(productName);
            iv1.setImageResource(R.drawable.bear_key_chain);
        }
        if (productName.equals("מחזיק מפתחות מעץ בית")){
            tv1.setText(productName);
            iv1.setImageResource(R.drawable.house_key_chain);
        }
        if (productName.equals("מחזיק מפתחות מעץ ריבוע")){
            tv1.setText(productName);
            iv1.setImageResource(R.drawable.square_key_chain);
        }
    }
    //לוח שנה
    public void ProductCalender(String productName){
        if (productName.equals("לוח שנה על בלוק")){
            tv1.setText(productName);
            iv1.setImageResource(R.drawable.block_calendar);
        }
        if (productName.equals("לוח שנה קטן מגנט")){
            tv1.setText(productName);
            iv1.setImageResource(R.drawable.mini_calendar);
        }
        if (productName.equals("לוח שנה לועזי כולל מגנט עליון")){
            tv1.setText(productName);
            iv1.setImageResource(R.drawable.calendar);
        }
    }
    //מארזים
    public void ProductMaarazim(String productName){
        if (productName.equals("מארז לפסח")){
            tv1.setText(productName);
            iv1.setImageResource(R.drawable.pesach);
        }
        if (productName.equals("מארז למנגליסט")){
            tv1.setText(productName);
            iv1.setImageResource(R.drawable.mangal);
        }
        if (productName.equals("מארז לתינוק")){
            tv1.setText(productName);
            iv1.setImageResource(R.drawable.maaraz1);
        }
        if (productName.equals("מארז לאמא/אבא")){
            tv1.setText(productName);
            iv1.setImageResource(R.drawable.maaraz2);
        }
    }
    //תינוקות
    public void ProductBabies(String productName){
        if (productName.equals("סינר תינוק")){
            tv1.setText(productName);
            iv1.setImageResource(R.drawable.baby_apron);
        }
        if (productName.equals("בגד גוף תינוק ללא רגלית")){
            tv1.setText(productName);
            iv1.setImageResource(R.drawable.baby_bodysuit);
        }
        if (productName.equals("חיתולי בד עם הטבעת שם")){
            tv1.setText(productName);
            iv1.setImageResource(R.drawable.cloth_diaperes);
        }
    }
    //אחרים
    public void ProductOthers(String productName){
        if (productName.equals("חולצת דרייפיט עם הדפס")){
            tv1.setText(productName);
            iv1.setImageResource(R.drawable.shirt);
        }
        if (productName.equals("ספל במיתוג אישי")){
            tv1.setText(productName);
            iv1.setImageResource(R.drawable.cup);
        }
        if (productName.equals("בלוק עץ אורן עם תמונה")){
            tv1.setText(productName);
            iv1.setImageResource(R.drawable.block);
        }
        if (productName.equals("קרש חיתוך צריבה מעוצבת")){
            tv1.setText(productName);
            iv1.setImageResource(R.drawable.cutting_board);
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
        startActivity(new Intent(ItemSelectedActivity.this, MainActivity.class));
        return true;
    }

    @Override
    public void onClick(View view) {
        if (mAuth.getCurrentUser()==null){
            Toast.makeText(this, "אפשרות זו תפתח אחרי הרשמה", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(ItemSelectedActivity.this, LoginActivity.class));
        }
        else{
            Intent intent = new Intent(ItemSelectedActivity.this, OrderForm.class);
            intent.putExtra("productName", tv1.getText().toString());
            startActivity(intent);
        }
    }
}
