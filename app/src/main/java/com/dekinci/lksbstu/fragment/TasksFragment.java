package com.dekinci.lksbstu.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dekinci.lksbstu.model.PolyManager;
import com.dekinci.lksbstu.fragment.messenger.ConversationFragment;
import com.example.hackaton.goprojectpisat.R;

import java.util.ArrayList;
import java.util.List;

public class TasksFragment extends Fragment {

    public TasksFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("Tasks", "Fragment attached");
        return inflater.inflate(R.layout.fragment_tasks, container, false);
    }

    private class TaskAdapter extends BaseAdapter {

        private List<String> dialogs;
        private Context context;
        private List<String> name;
        private String dialogsID;

        public TaskAdapter(List<String> dialogs, Context context) {
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
            TaskAdapter.SetData setData = new TaskAdapter.SetData();

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
