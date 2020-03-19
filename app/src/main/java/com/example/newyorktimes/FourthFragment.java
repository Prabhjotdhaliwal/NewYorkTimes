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


public class FourthFragment extends Fragment {
    private NavController navController;
    List<Result> resultarray=new ArrayList<>();
    List<Multimedium> mutimediaArray=new ArrayList<>();
    TextView title4,abstact4,caption4,copyright4,byline4,createddate4,updateddate4,ccpy4;
    ImageView image4;
    public FourthFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        title4=view.findViewById(R.id.f_title4);
        abstact4=view.findViewById(R.id.f_abstact4);
        caption4=view.findViewById(R.id.f_caption4);
        copyright4=view.findViewById(R.id.f_copyright4);
        byline4=view.findViewById(R.id.f_byline4);
        createddate4=view.findViewById(R.id.f_createdDate4);
        updateddate4=view.findViewById(R.id.f_updatedDate4);
        ccpy4=view.findViewById(R.id.f_ccpy4);

        image4=view.findViewById(R.id.img4);


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
                    String subSection1=resultarray.get(4).getSubsection();
                    System.out.println(subSection1);

                    //get Values from API
                    String titleStr=resultarray.get(4).getTitle();
                    String abstactStr=resultarray.get(4).getAbstract();
                    String byLineStr=resultarray.get(4).getByline();
                    String createdDateStr=resultarray.get(4).getCreatedDate();
                    String updatedDateStr=resultarray.get(4).getUpdatedDate();
                    mutimediaArray=resultarray.get(4).getMultimedia();
                    String url1=mutimediaArray.get(4).getUrl();
                    String captionStr=mutimediaArray.get(4).getCaption();
                    String copyrightStr=mutimediaArray.get(4).getCopyright();

                    //Set values to the UI elements
                    title4.setText(titleStr);
                    abstact4.setText(abstactStr);
                    caption4.setText(captionStr);
                    copyright4.setText(copyrightStr);
                    byline4.setText(byLineStr);
                    createddate4.setText(createdDateStr);
                    updateddate4.setText(updatedDateStr);
                    ccpy4.setText(copyright);
                    Picasso.get().load(url1).into(image4);
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
        return inflater.inflate(R.layout.fragment_fourth, container, false);
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
