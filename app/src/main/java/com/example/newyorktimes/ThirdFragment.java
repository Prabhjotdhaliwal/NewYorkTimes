package com.example.newyorktimes;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ThirdFragment extends Fragment {
    private NavController navController;
    List<Result> resultarray=new ArrayList<>();
    TextView title3,abstact3,caption3,copyright3,byline3,createddate3,updateddate3;
    ImageView image3;
    public ThirdFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        title3=view.findViewById(R.id.f_title3);
        abstact3=view.findViewById(R.id.f_abstact3);
        caption3=view.findViewById(R.id.f_caption3);
        copyright3=view.findViewById(R.id.f_copyright3);
        byline3=view.findViewById(R.id.f_byline3);
        createddate3=view.findViewById(R.id.f_createdDate3);
        updateddate3=view.findViewById(R.id.f_updatedDate3);
        image3=view.findViewById(R.id.img3);


        DataServices services = RetrofitClientInstance.getRetrofitInstance().create(DataServices.class);
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        Call<NewYorkTimes> call = services.getResults();
        call.enqueue(new Callback<NewYorkTimes>()
        {


            @Override
            public void onResponse(Call<NewYorkTimes> call, Response<NewYorkTimes> response)
            {

                NewYorkTimes newYorkTimes=response.body();
                System.out.println("123456");

                //  Weather weather=response.body();
                System.out.println(newYorkTimes);
                try {

                    String status=newYorkTimes.getStatus();
                    System.out.println(status);
                    String copyright=newYorkTimes.getCopyright();
                    System.out.println(copyright);
                    String section=newYorkTimes.getSection();
                    System.out.println(section);
                    String lastUpdated=newYorkTimes.getLastUpdated();
                    System.out.println(lastUpdated);
                    int numResults=newYorkTimes.getNumResults();
                    System.out.println(numResults);

                    resultarray=(newYorkTimes.getResults());
                    System.out.println(resultarray);
                    System.out.println("jot456");

                    String section1=resultarray.get(0).getSection();
                    //System.out.println(section1);
                    String subSection1=resultarray.get(3).getSubsection();
                    System.out.println(subSection1);
                    String abstract1 =resultarray.get(3).getAbstract();
                    System.out.println(abstract1);
                    String byLine=resultarray.get(3).getByline();
                    System.out.println(byLine);
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
        return inflater.inflate(R.layout.fragment_third, container, false);
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
