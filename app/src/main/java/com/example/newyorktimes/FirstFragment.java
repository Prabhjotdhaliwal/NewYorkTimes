package com.example.newyorktimes;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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


public class FirstFragment extends Fragment {
    private NavController navController;
    List<Result> resultarray=new ArrayList<>();
    List<Multimedium> mutimediaArray=new ArrayList<>();
TextView title1,abstact1,caption1,copyright1,byline,createddate1,updateddate1;
ImageView image;
    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        title1=view.findViewById(R.id.f_title);
        abstact1=view.findViewById(R.id.f_abstact);
        caption1=view.findViewById(R.id.f_caption);
        copyright1=view.findViewById(R.id.f_copyright);
        byline=view.findViewById(R.id.f_byline);
        createddate1=view.findViewById(R.id.f_createdDate);
        updateddate1=view.findViewById(R.id.f_updatedDate);
        image=view.findViewById(R.id.img);


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
                    System.out.println(section1);
                    String subSection1=resultarray.get(0).getSubsection();
                    System.out.println(subSection1);
                    String abstract11 =resultarray.get(0).getAbstract();
                  //  System.out.println(abstract11);
                  //  String byLine=resultarray.get(0).getByline();
                  //  System.out.println(byLine);

                    //get Values from API
                    String titleStr=resultarray.get(0).getTitle();
                    String abstactStr=resultarray.get(0).getAbstract();
                    String byLineStr=resultarray.get(0).getByline();
                    String createdDateStr=resultarray.get(0).getCreatedDate();
                    String updatedDateStr=resultarray.get(0).getUpdatedDate();
              mutimediaArray=resultarray.get(0).getMultimedia();
                   // System.out.println(url1);
                    System.out.println("jot456789");
                    String url1=mutimediaArray.get(0).getUrl();
                    String captionStr=mutimediaArray.get(0).getCaption();
                    System.out.println(captionStr);
                    String copyrightStr=mutimediaArray.get(0).getCopyright();
                    System.out.println(copyrightStr);


                    //Set values to the UI elements
                    title1.setText(titleStr);
                    abstact1.setText(abstactStr);
                    caption1.setText(captionStr);
                    copyright1.setText(copyrightStr);
                    byline.setText(byLineStr);
                    createddate1.setText(createdDateStr);
                    updateddate1.setText(updatedDateStr);

                    Picasso.get().load(url1).into(image);

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
