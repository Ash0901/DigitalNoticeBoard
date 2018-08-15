package com.example.shivamagarwal.noticeboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shivamagarwal.noticeboard.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Main2Activity extends AppCompatActivity {

    EditText t1,t2;
    Button b1,b2;
    DatabaseReference noticesRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        noticesRef=FirebaseDatabase.getInstance().getReference("simple_notice_board_project/notices");

        t1=findViewById(R.id.a2_t1);
        t2=findViewById(R.id.a2_t2);
        b1=findViewById(R.id.a2_b1);
        b2=findViewById(R.id.a2_b2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference notice=noticesRef.push();
                SharedPreferences sp=getSharedPreferences("mysp",Context.MODE_PRIVATE);
                notice.child("postedby").setValue(sp.getString("postedby",null));
                notice.child("postedon").setValue(Calendar.getInstance().getTime().toString());
                notice.child("heading").setValue(t1.getText().toString());
                notice.child("message").setValue(t2.getText().toString());
                Toast.makeText(Main2Activity.this, "Notice Posted Successfully by "+sp.getString("postedby","Shivam Agarwal<shivamag0901@gmail.com>"+" at "+Calendar.getInstance().getTime().toString()), Toast.LENGTH_LONG).show();
            }
        });
    }
}
