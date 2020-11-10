package com.nooralhealth.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.nooralhealth.R;
import com.nooralhealth.model.PostRetro;
import com.nooralhealth.retro.JsonPlaceHolderApi;

public class ListDemo extends AppCompatActivity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_demo);

        textView = findViewById(R.id.text_view_result);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<PostRetro>> listCall = jsonPlaceHolderApi.getPosts();

        listCall.enqueue(new Callback<List<PostRetro>>() {
            @Override
            public void onResponse(Call<List<PostRetro>> call, Response<List<PostRetro>> response) {
                if (!response.isSuccessful()) {
                    textView.setText("Code " + response.code());
                    return;
                }

                List<PostRetro> postRetros = response.body();

                for (PostRetro postRetro : postRetros) {
                    String content = "";
                    content += "ID: " + postRetro.getId() + "\n";
                    content += "User ID: " + postRetro.getUserID() + "\n";
                    content += "Title: " + postRetro.getTitle() + "\n";
                    content += "Body: " + postRetro.getText() + "\n\n";

                    textView.append(content);

                }

            }

            @Override
            public void onFailure(Call<List<PostRetro>> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });

    }
}
