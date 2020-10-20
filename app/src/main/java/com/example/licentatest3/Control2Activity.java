package com.example.licentatest3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.ArrayList;

public class Control2Activity extends AppCompatActivity {

    private ArrayList<SpinnerItem> mBusList;
    public int ok=0;
    private BusAdapter mAdapter;
    EditText descriere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initlist();

        final Spinner spinner=findViewById(R.id.spinner_bus);
        mAdapter=new BusAdapter(this,mBusList);
        spinner.setAdapter(mAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerItem clickedItem=(SpinnerItem) parent.getItemAtPosition(position);
                String clickedBusName=clickedItem.getBusName();

                if(!clickedBusName.equals("Nici un traseu selectat")) {
                    Toast.makeText(Control2Activity.this, clickedBusName + " selectat", Toast.LENGTH_SHORT).show();

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("Trasee");
                    myRef.setValue(clickedBusName);
                    ok=1;
                }
                else{
                    Toast.makeText(Control2Activity.this,"Selectati un traseu",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        descriere=(EditText) findViewById(R.id.descriere);
    }

    private void initlist(){
        mBusList=new ArrayList<>();
        mBusList.add(new SpinnerItem("Nici un traseu selectat",R.drawable.select));
        mBusList.add(new SpinnerItem("Traseul E1",R.drawable.e1));
        mBusList.add(new SpinnerItem("Traseul 2B",R.drawable.b2));
        mBusList.add(new SpinnerItem("Traseul 3B",R.drawable.b3));
        mBusList.add(new SpinnerItem("Traseul 5B",R.drawable.b5));
        mBusList.add(new SpinnerItem("Traseul 17B",R.drawable.b17));
        mBusList.add(new SpinnerItem("Traseul 23B",R.drawable.b23));
        mBusList.add(new SpinnerItem("Traseul 29B",R.drawable.b29));
        mBusList.add(new SpinnerItem("Traseul 1",R.drawable.x1));
        mBusList.add(new SpinnerItem("Traseul 4",R.drawable.x4));
        mBusList.add(new SpinnerItem("Traseul 9",R.drawable.x9));
        mBusList.add(new SpinnerItem("Traseul 10",R.drawable.x10));
        mBusList.add(new SpinnerItem("Traseul 11",R.drawable.x11));
        mBusList.add(new SpinnerItem("Traseul 13",R.drawable.x13));
        mBusList.add(new SpinnerItem("Traseul 20",R.drawable.x20));
        mBusList.add(new SpinnerItem("Traseul 24",R.drawable.x24));
        mBusList.add(new SpinnerItem("Traseul 25",R.drawable.x25));
        mBusList.add(new SpinnerItem("Tramvai",R.drawable.tramvai));
    }

    public void btnCurrentLocation(View view){
        if(ok==1) {
            startActivity(new Intent(this, MapsActivity.class));
        }
        else{
            Toast.makeText(Control2Activity.this,"Selectati un traseu",Toast.LENGTH_LONG).show();
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Date&Time");
        myRef.setValue(ServerValue.TIMESTAMP);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Detalii");
        Detali detali=new Detali(descriere.getText().toString());
        myRef.setValue(detali);

    }

}
