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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SecondFragment extends Fragment {

    private NavController navController;
    List<Result> resultarray=new ArrayList<>();
    List<Multimedium> mutimediaArray=new ArrayList<>();
    TextView title2,abstact2,caption2,copyright2,byline2,createddate2,updateddate2,ccpy2;
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
        ccpy2=view.findViewById(R.id.f_ccpy2);
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
                    String copyright=newYorkTimes.getCopyright();
                    String section=newYorkTimes.getSection();
                    String lastUpdated=newYorkTimes.getLastUpdated();
                    int numResults=newYorkTimes.getNumResults();
                    resultarray=(newYorkTimes.getResults());
                    String section1=resultarray.get(0).getSection();
                    System.out.println(section1);
                    String subSection1=resultarray.get(2).getSubsection();
                    System.out.println(subSection1);

                    //get Values from API
                    String titleStr=resultarray.get(2).getTitle();
                    String abstactStr=resultarray.get(2).getAbstract();
                    String byLineStr=resultarray.get(2).getByline();
                    String createdDateStr=resultarray.get(2).getCreatedDate();
                    String updatedDateStr=resultarray.get(2).getUpdatedDate();
                    mutimediaArray=resultarray.get(2).getMultimedia();
                    String url1=mutimediaArray.get(2).getUrl();
                    String captionStr=mutimediaArray.get(2).getCaption();
                    String copyrightStr=mutimediaArray.get(2).getCopyright();

                    //Set values to the UI elements
                    title2.setText(titleStr);
                    abstact2.setText(abstactStr);
                    caption2.setText(captionStr);
                    copyright2.setText(copyrightStr);
                    byline2.setText(byLineStr);
                    createddate2.setText(createdDateStr);
                    updateddate2.setText(updatedDateStr);
                    ccpy2.setText(copyright);
                    Picasso.get().load(url1).into(image2);

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
