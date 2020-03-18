package com.example.newyorktimes;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {
    private static Retrofit retrofit;
    private static final String BASE_URL="https://api.nytimes.com/svc/topstories/v2/";
    //private static final String BASE_URL="    https://api.nytimes.com/svc/topstories/v2/arts.json?api-key=/";


    //https://api.nytimes.com/svc/topstories/v2/arts.json?api-key=72ObTj81i40BVdTZpLtXX8quTlqBxEwO
public  static Retrofit getRetrofitInstance()
{
    if (retrofit == null) {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    return retrofit;
}

}
