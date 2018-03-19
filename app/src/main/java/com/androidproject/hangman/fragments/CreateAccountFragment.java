package com.androidproject.hangman.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidproject.hangman.R;
import com.androidproject.hangman.dataHandling.CurrentUser;
import com.androidproject.hangman.dataHandling.UserAccountViewModel;
import com.androidproject.hangman.handler.NavigationHandler;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import static android.text.TextUtils.isEmpty;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreateAccountFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CreateAccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateAccountFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FirebaseAuth auth;
    private EditText inputUsername, inputEmail, inputPassword, inputRepeatPassword;
    private FloatingActionButton fabAadPic;
    private Button btnSignUp, btnCancel;
    private ImageView ivProfilePic;
    private UserAccountViewModel model;


    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CreateAccountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateAccountFragment.
     */

    public static CreateAccountFragment newInstance(String param1, String param2) {
        CreateAccountFragment fragment = new CreateAccountFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_create_account, container, false);
        auth = FirebaseAuth.getInstance();

        inputUsername = (EditText) v.findViewById(R.id.etUsername);
        inputEmail = (EditText) v.findViewById(R.id.etEmail);
        inputPassword = (EditText) v.findViewById(R.id.etPassword);
        inputRepeatPassword = (EditText) v.findViewById(R.id.etRepeatPassword);
        btnSignUp = (Button) v.findViewById(R.id.btnCreateAccount);
        fabAadPic = (FloatingActionButton) v.findViewById(R.id.floatingActionButton2);
        ivProfilePic = (ImageView) v.findViewById(R.id.ivCreatedAccount);
        btnCancel = v.findViewById(R.id.btnCancel);

        View.OnClickListener singUpListener = new View.OnClickListener() {
            public void onClick(View v) {
                createAccount();
            }
        };

        View.OnClickListener chooseProfilePicListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseProfilePic();
            }
        };

        View.OnClickListener canecListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                try {
                    fragmentManager.beginTransaction().replace(R.id.userAccountFrameLayout, CreateAccountFragment.class.newInstance()).commit();
                } catch (java.lang.InstantiationException | java.lang.IllegalAccessException ex) {
                    ex.printStackTrace();
                }
            }
        };

        btnSignUp.setOnClickListener(singUpListener);
        fabAadPic.setOnClickListener(chooseProfilePicListener);

        if (model.getProfilePic() != null) {
            ivProfilePic.setImageDrawable(model.getProfilePic());

        }
        if (model.getEmailadress() != null) {
            inputEmail.setText(model.getEmailadress());
        }
        if (model.getPassword() != null) {
            inputPassword.setText(model.getPassword());
        }
        if (model.getRepeatPassword() != null) {
            inputRepeatPassword.setText(model.getRepeatPassword());
        }
        if (model.getUsername() != null) {
            inputUsername.setText(model.getUsername());
        }

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                try {
                    fragmentManager.beginTransaction().replace(R.id.userAccountFrameLayout, LoginFragment.class.newInstance()).commit();
                } catch (java.lang.InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });

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

    /**
     * Creates a new account with the given username, email and password
     */
    public void createAccount() {

        final String email = inputEmail.getText().toString().trim();
        final String password = inputPassword.getText().toString().trim();
        String repeatPassword = inputRepeatPassword.getText().toString().trim();

        if (isEmpty(email)) {
            Toast.makeText(getActivity(), "Bitte gibt eine E-Mailadresse ein!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isEmpty(password) && isEmpty(repeatPassword)) {
            Toast.makeText(getActivity(), "Gib ein Passwort ein", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(getActivity(), "Das Passwort muss mindestens 6 Zeichen lang sein", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isEmpty(repeatPassword)) {
            Toast.makeText(getActivity(), "Bitte wiederhole das Passwort", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!password.equals(repeatPassword)) {
            Toast.makeText(getActivity(), "Die Passwörter stimmen nicht überein" + password + repeatPassword, Toast.LENGTH_SHORT).show();
            return;
        }

        //create user
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Benutzer konnte nicht erstellt werden\n" + task.getException(),
                                    Toast.LENGTH_SHORT).show();

                        } else {
                            loginAfterSuccess(email, password);
                            Toast.makeText(getActivity(), "Benutzer erfolgreich erstellt", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    /**
     * Logs the user in with the newly created account and adds the username to it
     *
     * @param email    Email des neu angelegten Benutzers
     * @param password Password des neu angelegten Benutzers
     */
    public void loginAfterSuccess(String email, String password) {
        final String username = inputUsername.getText().toString().trim();
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getActivity(), "Authentifizierung erfolgreich", Toast.LENGTH_SHORT).show();

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    CurrentUser.getInstance().setProfilePic(model.getProfilePic());
                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName(username)
                            .setPhotoUri(Uri.parse(model.getProfilePicUrl()))
                            .build();
                    user.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getActivity(), "Update erfolgreich", Toast.LENGTH_SHORT).show();
                                NavigationHandler.changeToDrawerTest(getActivity());
                            }
                        }
                    });
                } else {
                    Toast.makeText(getActivity(), "Authentifizierung fehlgeschlagen", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void chooseProfilePic() {
        //Save data into View Model
        FragmentManager fragmentManager = getFragmentManager();
        try {
            model.setUsername(inputUsername.getText().toString());
            model.setEmailadress(inputEmail.getText().toString());
            model.setPassword(inputPassword.getText().toString());
            model.setRepeatPassword(inputRepeatPassword.getText().toString());
            fragmentManager.beginTransaction().replace(R.id.userAccountFrameLayout, ChooseProfilePicFragment.class.newInstance()).commit();
        } catch (java.lang.InstantiationException | java.lang.IllegalAccessException ex) {
            ex.printStackTrace();
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
