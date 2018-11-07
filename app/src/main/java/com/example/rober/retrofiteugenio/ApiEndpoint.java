package com.example.rober.retrofiteugenio;

public interface ApiEndpoint {
    @GET("posts/{id}")
    Call<Post> obterPost(@Path("id") int userID);

    Call<Post> obterPost(@Path("id") String userID);
}