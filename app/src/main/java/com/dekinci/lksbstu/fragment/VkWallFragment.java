package com.dekinci.lksbstu.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dekinci.lksbstu.communication.VKApi.VKposts;
import com.dekinci.lksbstu.utils.ImageLoader;
import com.example.hackaton.goprojectpisat.R;

import java.util.List;
import java.util.Objects;

public class VkWallFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener{


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_vk_wall, container, false);
        ListView listView = view.findViewById(R.id.list_wall);
        Log.i("VKWall", "Fragment attached");

//        PolyManager.get().getApi().(dialogs -> {
//            Objects.requireNonNull(getActivity()).runOnUiThread(() ->
//                    listView.setAdapter(new VkWallAdapter(vKposts ,getContext())));
//        });



        return view;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }


    private class VkWallAdapter extends BaseAdapter {

        private Context context;
        private List<VKposts> vKposts;
        private List<String> urls;

        public VkWallAdapter(List<VKposts> vKposts, Context context) {
            this.vKposts = vKposts;
            this.context = context;

            for (VKposts elem : vKposts)
                urls.add(elem.getImage());
        }

        @Override
        public int getCount() {
            return vKposts.size();
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
            View view = inflater.inflate(R.layout.wall, null);


            setData.text = view.findViewById(R.id.text);
            setData.image = view.findViewById(R.id.wallImage);

            setData.text.setText(vKposts.get(position).getText());
            new ImageLoader(image ->{
                Objects.requireNonNull(getActivity()).runOnUiThread(() ->
                        setData.image.setImageBitmap(image)
            );
            }).execute(vKposts.get(position).getImage());


            return view;
        }

        private class SetData{
            TextView text;
            ImageView image;
        }
    }
}
