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


public class SecondFragment extends Fragment {

    private NavController navController;
    List<Result> resultarray=new ArrayList<>();
    TextView title2,abstact2,caption2,copyright2,byline2,createddate2,updateddate2;
    ImageView image2;
    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        title2=view.findViewById(R.id.f_title2);
        abstact2=view.findViewById(R.id.f_abstact2);
        caption2=view.findViewById(R.id.f_caption2);
        copyright2=view.findViewById(R.id.f_copyright2);
        byline2=view.findViewById(R.id.f_byline2);
        createddate2=view.findViewById(R.id.f_createdDate2);
        updateddate2=view.findViewById(R.id.f_updatedDate2);
        image2=view.findViewById(R.id.img2);

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
                    String subSection1=resultarray.get(1).getSubsection();
                    System.out.println(subSection1);
                    String abstract1 =resultarray.get(1).getAbstract();
                    System.out.println(abstract1);
                    String byLine=resultarray.get(1).getByline();
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
        return inflater.inflate(R.layout.fragment_second, container, false);
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
