package com.androidproject.hangman.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.androidproject.hangman.R;
import com.androidproject.hangman.dataHandling.UserAccountViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ChooseProfilePicFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ChooseProfilePicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChooseProfilePicFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private UserAccountViewModel model;
    private ImageView profilPicIv;
    private Button btnAcceptPic, btnCancelPic;
    private ImageButton ibtnProfilePic, ibtnProfilePic2, ibtnProfilePic3, ibtnProfilePic4;
    private ImageButton ibtnProfilePic5, ibtnProfilePic6, ibtnProfilePic7, ibtnProfilePic8;
    private ImageButton ibtnProfilePic9, ibtnProfilePic10, ibtnProfilePic11, ibtnProfilePic12;
    private ImageButton ibtnProfilePic13, ibtnProfilePic14, ibtnProfilePic15;
    private View v;
    private FragmentManager fm;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ChooseProfilePicFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChooseProfilePicFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChooseProfilePicFragment newInstance(String param1, String param2) {
        ChooseProfilePicFragment fragment = new ChooseProfilePicFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        model = ViewModelProviders.of(getActivity()).get(UserAccountViewModel.class);
        fm = getFragmentManager();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_choose_profile_pic, container, false);

        btnAcceptPic = v.findViewById(R.id.btnAcceptPic);
        btnCancelPic = v.findViewById(R.id.btnCancelPic);
        ibtnProfilePic = v.findViewById(R.id.ibtnProfilePic);
        ibtnProfilePic2 = v.findViewById(R.id.ibtnProfilePic2);
        ibtnProfilePic3 = v.findViewById(R.id.ibtnProfilePic3);
        ibtnProfilePic4 = v.findViewById(R.id.ibtnProfilePic4);
        ibtnProfilePic5 = v.findViewById(R.id.ibtnProfilePic5);
        ibtnProfilePic6 = v.findViewById(R.id.ibtnProfilePic6);
        ibtnProfilePic7 = v.findViewById(R.id.ibtnProfilePic7);
        ibtnProfilePic8 = v.findViewById(R.id.ibtnProfilePic8);
        ibtnProfilePic9 = v.findViewById(R.id.ibtnProfilePic9);
        ibtnProfilePic10 = v.findViewById(R.id.ibtnProfilePic10);
        ibtnProfilePic11 = v.findViewById(R.id.ibtnProfilePic11);
        ibtnProfilePic12 = v.findViewById(R.id.ibtnProfilePic12);
        ibtnProfilePic13 = v.findViewById(R.id.ibtnProfilePic13);
        ibtnProfilePic14 = v.findViewById(R.id.ibtnProfilePic14);
        ibtnProfilePic15 = v.findViewById(R.id.ibtnProfilePic15);
        profilPicIv = v.findViewById(R.id.chooseProfilePicIv);

        View.OnClickListener ibtnListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable buttonImage = v.getBackground();
                imageSelected(buttonImage);
            }
        };

        View.OnClickListener acceptListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acceptProfilePic();
            }
        };

        View.OnClickListener cancelListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelProfilePic();
            }
        };


        btnAcceptPic.setOnClickListener(acceptListener);
        btnCancelPic.setOnClickListener(cancelListener);
        ibtnProfilePic.setOnClickListener(ibtnListener);
        ibtnProfilePic2.setOnClickListener(ibtnListener);
        ibtnProfilePic3.setOnClickListener(ibtnListener);
        ibtnProfilePic4.setOnClickListener(ibtnListener);
        ibtnProfilePic5.setOnClickListener(ibtnListener);
        ibtnProfilePic6.setOnClickListener(ibtnListener);
        ibtnProfilePic7.setOnClickListener(ibtnListener);
        ibtnProfilePic8.setOnClickListener(ibtnListener);
        ibtnProfilePic9.setOnClickListener(ibtnListener);
        ibtnProfilePic10.setOnClickListener(ibtnListener);
        ibtnProfilePic11.setOnClickListener(ibtnListener);
        ibtnProfilePic12.setOnClickListener(ibtnListener);
        ibtnProfilePic13.setOnClickListener(ibtnListener);
        ibtnProfilePic14.setOnClickListener(ibtnListener);
        ibtnProfilePic15.setOnClickListener(ibtnListener);

        if (model.getProfilePic() != null){
            profilPicIv.setImageDrawable(model.getProfilePic());
        }else{
            profilPicIv.setImageDrawable(ibtnProfilePic.getDrawable());
        }
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void imageSelected(Drawable buttonImage) {
        profilPicIv.setImageDrawable(buttonImage);
    }

    public void acceptProfilePic() {
        model.setProfilePic(profilPicIv.getDrawable());
        try {
            fm.beginTransaction().replace(R.id.userAccountFrameLayout, CreateAccountFragment.class.newInstance()).commit();
        } catch (java.lang.InstantiationException | java.lang.IllegalAccessException ex) {
            ex.printStackTrace();
        }

    }

    public void cancelProfilePic() {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
