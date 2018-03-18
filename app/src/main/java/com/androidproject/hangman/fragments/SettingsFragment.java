package com.androidproject.hangman.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidproject.hangman.R;
import com.androidproject.hangman.handler.NavigationHandler;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SettingsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button btnChangeEmail, btnChangePasswort, btnDeleteUser;
    private EditText etChangeEmail, etChangePasswort;
    private View v;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */

    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
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
        v = inflater.inflate(R.layout.fragment_settings, container, false);

        btnChangeEmail = v.findViewById(R.id.btnChangeEmail);
        btnChangePasswort = v.findViewById(R.id.btnChangePasswort);
        btnDeleteUser = v.findViewById(R.id.btnDeleteUser);
        etChangeEmail = v.findViewById(R.id.etChangeEmail);
        etChangePasswort = v.findViewById(R.id.etChangePasswort);

        View.OnClickListener changePW = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user != null && !etChangePasswort.getText().toString().trim().equals("")) {
                    if (etChangePasswort.getText().toString().trim().length() < 6) {
                        etChangePasswort.setError("Passwot min. 6 Zeichen");
                    } else {
                        user.updatePassword(etChangePasswort.getText().toString().trim())
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(getActivity(), "Passwort wurde geändert, bitte neu einloggen", Toast.LENGTH_SHORT).show();
                                            FirebaseAuth.getInstance().signOut();
                                            NavigationHandler.changeToLoginActivity(getActivity());
                                        } else {
                                            Toast.makeText(getActivity(), "Passwort konnte nicht geändert werden", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                } else if (etChangePasswort.getText().toString().trim().equals("")) {
                    etChangePasswort.setError("Bitte geben sie ein Passwort ein");
                }
            }
        };

        View.OnClickListener changeMail = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user != null && !etChangeEmail.getText().toString().trim().equals("")) {
                    user.updateEmail(etChangeEmail.getText().toString().trim())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getActivity(), "E-mailadresse wurde geändert, bitte neu einloggen", Toast.LENGTH_LONG).show();
                                        FirebaseAuth.getInstance().signOut();
                                        NavigationHandler.changeToLoginActivity(getActivity());
                                    } else {
                                        Toast.makeText(getActivity(), "E-Mailadresse konnte nicht geändert werden", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                } else if (etChangeEmail.getText().toString().trim().equals("")) {
                    etChangeEmail.setError("Bitte geben sie eine E-Mailadresse ein");
                }
            }
        };

        View.OnClickListener deleteUserListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user != null) {
                    user.delete()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getActivity(), "Ihr Account/Benutzer wurde gelöscht", Toast.LENGTH_SHORT).show();
                                        NavigationHandler.changeToLoginActivity(getActivity());
                                    } else {
                                        Toast.makeText(getActivity(), "Löschen des Accounts/Benutzers fehlgeschlagen", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        };

        btnChangePasswort.setOnClickListener(changePW);
        btnChangeEmail.setOnClickListener(changeMail);
        btnDeleteUser.setOnClickListener(deleteUserListener);

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
