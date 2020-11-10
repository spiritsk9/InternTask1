package com.nooralhealth.retro;

import com.nooralhealth.model.PostRetro;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("posts")
    Call<List<PostRetro>> getPosts();

}
