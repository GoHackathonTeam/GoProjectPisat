package com.dekinci.lksbstu.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dekinci.lksbstu.model.CurrentUser;
import com.dekinci.lksbstu.model.PolyManager;
import com.dekinci.lksbstu.communication.structure.pojos.User;
import com.dekinci.lksbstu.utils.ImageLoader;
import com.example.hackaton.goprojectpisat.R;

public class ProfileFragment extends Fragment {
    private LogOutListener logOutListener;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        View resultView = inflater.inflate(R.layout.fragment_profile, container, false);
        resultView.findViewById(R.id.button_logout).setOnClickListener(v -> logOutListener.logout());
        inflateUserData(resultView);
        return resultView;
    }

    private void inflateUserData(View v) {
        CurrentUser user = PolyManager.get().getUser();

        ImageView profilePhoto = v.findViewById(R.id.profile_photo);
        user.getAvatar(b -> getActivity().runOnUiThread(() -> profilePhoto.setImageBitmap(b)));

        user.getUser(u -> getActivity().runOnUiThread(()-> {
            TextView surname = v.findViewById(R.id.profile_surname);
            surname.setText(u.getSurname());

            TextView name = v.findViewById(R.id.profile_name);
            name.setText(u.getName());

            TextView patronymic = v.findViewById(R.id.profile_patronymic);
            patronymic.setText(u.getPatronymic());

            TextView group = v.findViewById(R.id.profile_group_value);
            group.setText(u.getGroupId());

            TextView institute = v.findViewById(R.id.profile_institute_value);
            institute.setText(u.getInstitute());

            TextView recordBook = v.findViewById(R.id.profile_rec_book_value);
            recordBook.setText("ERROR!"); //TODO!!!

            TextView speciality = v.findViewById(R.id.profile_speciality_value);
            speciality.setText("ERROR!");//TODO!!!
        }));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof LogOutListener) {
            logOutListener = (LogOutListener) context;
        } else {
            throw new RuntimeException(context.toString() +
                    " must implement LogOutListener!");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        logOutListener = null;
    }

    public interface LogOutListener {
        void logout();
    }
}
