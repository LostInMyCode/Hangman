package com.androidproject.hangman.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidproject.hangman.R;
import com.androidproject.hangman.handler.ProfilePicOnSuccessListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoginFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FirebaseAuth auth;
    private EditText inputEmail, inputPassword;
    private Button btnSignUp, btnLogIn, btnReset;



    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */

    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_login, container, false);
        auth = FirebaseAuth.getInstance();
        inputEmail = (EditText) v.findViewById(R.id.etEmail);
        inputPassword = (EditText) v.findViewById(R.id.etPassword);
        btnSignUp = (Button) v.findViewById(R.id.btnCreateAccount);
        btnLogIn = (Button) v.findViewById(R.id.btnLogin);
        btnReset = v.findViewById(R.id.btnReset);


        View.OnClickListener singUpListener = new View.OnClickListener() {
            public void onClick(View v) {
                changeToCreateAccount();
            }
        };

        View.OnClickListener loginListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        };

        View.OnClickListener resetPassListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                try {
                    fragmentManager.beginTransaction().replace(R.id.userAccountFrameLayout, ResetPasswordFragment.class.newInstance()).commit();
                } catch (java.lang.InstantiationException | java.lang.IllegalAccessException ex) {
                    ex.printStackTrace();
                }
            }
        };

        btnLogIn.setOnClickListener(loginListener);
        btnSignUp.setOnClickListener(singUpListener);
        btnReset.setOnClickListener(resetPassListener);

        return v;
    }


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

    public void login() {
        String email = inputEmail.getText().toString();
        final String password = inputPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getActivity(), "E-Mailadresse eingeben", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getActivity(), "Passwort eingeben", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            if (password.length() < 6) {
                                Toast.makeText(getActivity(), "Das Passwort muss mindestens 6 Zeichen lang sein", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), "Authentifizierung fehlgeschlagen", Toast.LENGTH_SHORT).show();
                                task.getException();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Authentifizierung erfolgreich " + auth.getCurrentUser().getDisplayName(), Toast.LENGTH_LONG).show();
                            loadProfilePic(auth.getCurrentUser().getPhotoUrl().toString());
                            //NavigationHandler.changeToDrawerTest(getActivity());
                        }
                    }
                });
    }

    public void changeToCreateAccount() {
        FragmentManager fragmentManager = getFragmentManager();
        try {
            fragmentManager.beginTransaction().replace(R.id.userAccountFrameLayout, CreateAccountFragment.class.newInstance()).commit();
        } catch (java.lang.InstantiationException | java.lang.IllegalAccessException ex) {
            ex.printStackTrace();
        }
    }

    public void loadProfilePic(String profilePicURL){
        FirebaseStorage storageInstance = FirebaseStorage.getInstance();
        StorageReference image = storageInstance.getReferenceFromUrl(profilePicURL);
            try {
                File localFile;
                localFile = File.createTempFile("profilepic", ".png");
                image.getFile(localFile).addOnSuccessListener(new ProfilePicOnSuccessListener(localFile, getContext())).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        String re = "";
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
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

        void onFragmentInteraction(Uri uri);
    }
}
