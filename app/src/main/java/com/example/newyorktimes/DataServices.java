package com.example.newyorktimes;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataServices {
    @GET("72ObTj81i40BVdTZpLtXX8quTlqBxEwO")
    Call<NewYorkTimes>getResults();
}
