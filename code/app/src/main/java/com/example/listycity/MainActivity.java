package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ListView cityList;
    ArrayAdapter<String> cityAdapter;

    ArrayList<String> dataList;

    Button adding;
    Button delete;
    // indext to delete
    int to_delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cityList = findViewById(R.id.city_list);
        to_delete = -1;
        String []cities = {"Edmonton"}; // fill with current
        dataList = new ArrayList<>();
        // add cities to dataList
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this,R.layout.content,dataList); //

        cityList.setAdapter(cityAdapter); // adapter transfer to cityList ( a listview)

        adding = findViewById(R.id.Add);
        delete = findViewById(R.id.Delete);
        adding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCity(view);
            }
        } );

        setUpCityListListener();

        delete.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View view){
               delete_city(view, to_delete);
            }

        });

        }

    private void setUpCityListListener() {
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                to_delete = position;
                System.out.print("Rain");
            }
        });
    }

    private void delete_city(View view, int pos) {
        if(pos>0 && pos < dataList.size()){
            dataList.remove(pos);
            // return to unusable state
            to_delete = -1;
        }
    }


    private void addCity(View view) {
        EditText city = findViewById(R.id.editText);
        String text = city.getText().toString();
        cityAdapter.add(text);
    }

}


