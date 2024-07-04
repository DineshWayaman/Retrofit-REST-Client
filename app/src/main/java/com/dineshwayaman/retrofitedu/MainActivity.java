package com.dineshwayaman.retrofitedu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.dineshwayaman.retrofitedu.Adapters.UserRecyclerviewAdapter;
import com.dineshwayaman.retrofitedu.Models.Users;
import com.dineshwayaman.retrofitedu.Services.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

//    Declare Recyclerview and adapter
    private RecyclerView recyclerUser;
    private UserRecyclerviewAdapter userRecyclerviewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        init recyclerview and set it's layout
        recyclerUser = findViewById(R.id.recyclerUser);
        recyclerUser.setLayoutManager(new LinearLayoutManager(this));



//        call the fetch data mmethode
        fetchData();


    }

//    methode to handle fetch data
    private void fetchData() {
//        create retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

//        create instance of the API service
        ApiService apiService = retrofit.create(ApiService.class);

//        make an asyn call to fetch the users
        apiService.getUsers().enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
//                check if the response is success and the body is not null
                if(response.isSuccessful() && response.body() != null){
//                    init adapter with the list of users and set it to the recyclerview
                    userRecyclerviewAdapter = new UserRecyclerviewAdapter(response.body());
                    recyclerUser.setAdapter(userRecyclerviewAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
// if failed to fetch data
                Toast.makeText(MainActivity.this, "Failed to fetch data: " + t, Toast.LENGTH_SHORT).show();
            }
        });

    }
}