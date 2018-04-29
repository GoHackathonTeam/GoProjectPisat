package com.dekinci.lksbstu.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dekinci.lksbstu.model.PolyManager;
import com.dekinci.lksbstu.fragment.messenger.ConversationFragment;
import com.example.hackaton.goprojectpisat.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class TetATetFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_tet_a_tet, container, false);
        ListView listView = view.findViewById(R.id.list_dialogs);
        Log.i("Tet A Tet", "Fragment attached");
        PolyManager.get().getApi().getDialogs(dialogs -> {
            Objects.requireNonNull(getActivity()).runOnUiThread(() ->
                listView.setAdapter(new DialogsAdapter(dialogs, getContext())));
        });



        return view;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }


    private class DialogsAdapter extends BaseAdapter {

        private List<String> dialogs;
        private Context context;
        private List<String> name;
        private String dialogsID;

        public DialogsAdapter(List<String> dialogs, Context context) {
            this.dialogs = dialogs;
            this.context = context;

            name = new ArrayList<>();

            PolyManager.get().getApi().getUserInfo(null, user -> {
                name.add(user.getGroupName());
                dialogsID = user.getGroupId();
            });
            for(int i = 0; i < dialogs.size(); i++) {
                PolyManager.get().getApi().getUserInfo(dialogs.get(i), user -> {
                    name.add(user.getName() + " " + user.getSurname());
                });
            }
        }

        @Override
        public int getCount() {
            return dialogs.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            SetData setData = new SetData();

            assert inflater != null;
            @SuppressLint({"ViewHolder", "InflateParams"})
            View view = inflater.inflate(R.layout.item, null);


            setData.name = view.findViewById(R.id.user);
            setData.image = view.findViewById(R.id.headerProfileView);

            setData.name.setText(name.get(position + 1));
            //  setData.image.setImageBitmap(images.get(position));

            view.setOnClickListener(v -> {
                String id;
                if (position == 0){
                    id = dialogsID;
                }else {
                    id = name.get(position + 1);
                }
                Fragment fragment = new ConversationFragment(id);
                Activity activity = getActivity();
                if (activity != null) {
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.add(R.id.tet_a_tet, fragment);
                    fragmentTransaction.commit();
                }
                else
                    Log.e("ScheduleFragment", "activity is null!!!");
            });

            return view;
        }

        private class SetData{
            TextView name;
            ImageView image;
        }
    }
}
