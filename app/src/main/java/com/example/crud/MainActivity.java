package com.example.crud;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.crud.model.User;
import com.example.crud.model.UserResponse;
import com.example.crud.network.ApiClient;
import com.example.crud.network.ApiUsers;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

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
        gonStart();
    }


    protected void gonStart() {
        Call<UserResponse> call = ApiClient.getClient().create(ApiUsers.class).getUsuarios();
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (!response.isSuccessful()) {
                    System.out.println("codigo error: "+ response.code());
                    return;
                }
                System.out.println("[+] todo correcto datos rewcividos");

                UserResponse userResponse = response.body();
                if (userResponse != null) {
                    List<User> users = userResponse.getData();
                    for (User user : users) {
                        System.out.println("Usuario: "+ user.getEmail() +", Nombres: "+ user.getFirst_name() + " "+ user.getLast_name());
                    }
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error de conecion", Toast.LENGTH_SHORT).show();
            }
        });
    }
}