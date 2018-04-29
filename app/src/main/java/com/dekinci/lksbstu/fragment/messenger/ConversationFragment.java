package com.dekinci.lksbstu.fragment.messenger;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dekinci.lksbstu.communication.structure.pojos.User;
import com.dekinci.lksbstu.model.PolyManager;
import com.dekinci.lksbstu.communication.structure.Message;
import com.dekinci.lksbstu.utils.FactCallback;
import com.example.hackaton.goprojectpisat.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SuppressLint("ValidFragment")
public class ConversationFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener{

    String id;

    @SuppressLint("ValidFragment")
    public ConversationFragment(String id) {
        this.id = id;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_conversation, container, false);
        listView = view.findViewById(R.id.list_dialog);

        ImageButton send = view.findViewById(R.id.sendMess);
        EditText takeMess = view.findViewById(R.id.takeMess);

        PolyManager.get().getApi().getMessageList(id, messageList -> {
            Objects.requireNonNull(getActivity()).runOnUiThread(() ->{
                    listView.setAdapter(new MessageAdapter(messageList, getContext()));
            });
        },0, 10);

        send.setOnClickListener(v ->{
            PolyManager.get().getApi().sendMessage(id,
                    takeMess.getText().toString(), a ->{});
            takeMess.setText("");
            PolyManager.get().getApi().getMessageList(id, messageList -> {
                Objects.requireNonNull(getActivity()).runOnUiThread(() ->{
                    listView.setAdapter(new MessageAdapter(messageList, getContext()));
                });
            },0, 10);
        });
        return view;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    private class MessageAdapter extends BaseAdapter {

        private List<Message> messageList;
        private Context context;
        private List<String> name;
        private List<String> messageBody;

        public MessageAdapter(List<Message> messageList, Context context) {
            this.messageList = messageList;
            this.context = context;

            name = new ArrayList<>();
            messageBody = new ArrayList<>();

            for (int i = 0; i < messageList.size(); i++) {
                PolyManager.get().getApi().getUserInfo(messageList.get(i).getSenderId(), user -> {
                    name.add(user.getName() + " " + user.getSurname());
                });
                messageBody.add(messageList.get(i).getText());
            }
            System.out.println(messageList);
            System.out.println(name);
            System.out.println(messageBody);
        }

        @Override
        public int getCount() {
            return messageList.size();
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
            View view = inflater.inflate(R.layout.messages, null);

            setData.name = view.findViewById(R.id.user);
    //        setData.image = view.findViewById(R.id.headerProfileView);
            setData.msg = view.findViewById(R.id.msg);



            setData.name.setText(name.get(position));
            setData.msg.setText(messageBody.get(position));
            //  setData.image.setImageBitmap(images.get(position));

            return view;
        }

        private class SetData{
            TextView name;
            TextView msg;
            ImageView image;
        }
    }
}
