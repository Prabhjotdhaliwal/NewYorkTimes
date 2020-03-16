package com.example.newyorktimes;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FirstFragment extends Fragment {
    private NavController navController;
    ArrayList<Result> resultarray;

    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DataServices services = RetrofitClientInstance.getRetrofitInstance().create(DataServices.class);
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        Call<NewYorkTimes> call = services.getResults();
        call.enqueue(new Callback<NewYorkTimes>()
        {


            @Override
            public void onResponse(Call<NewYorkTimes> call, Response<NewYorkTimes> response)
            {
             System.out.println("jot123");
                NewYorkTimes newYorkTimes=new NewYorkTimes();
                       newYorkTimes =response.body();
              //  Weather weather=response.body();

                try {

                  //  resultarray=new ArrayList<>(newYorkTimes.getResults());
                 //   int i=newYorkTimes.getNumResults();
                 //   System.out.println(i);

                    System.out.println("jot456");
             }

                catch (NullPointerException e)
            {
                e.printStackTrace();
            }
        }

            @Override
            public void onFailure(Call<NewYorkTimes> call, Throwable t) {

            }
        });
    }

        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
