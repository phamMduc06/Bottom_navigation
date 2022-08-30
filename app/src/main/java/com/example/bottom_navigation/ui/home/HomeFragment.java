package com.example.bottom_navigation.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.ViewModelProvider;

import com.example.bottom_navigation.R;
import com.example.bottom_navigation.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public static final String TAG = "HomeFragment";
    private ListView listView;
    View view;
    private UserAccount[] users;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_home, container,false);

         this.listView = (ListView) view.findViewById(R.id.listView);
         UserAccount tom = new UserAccount("Tom","admin",true);
         UserAccount jerry = new UserAccount("Jerry","user");
         UserAccount donald = new UserAccount("Donald","guest",false);
         this.users = new UserAccount[]{tom,jerry,donald};

        ArrayAdapter<UserAccount> arrayAdapter = new ArrayAdapter<UserAccount>(getActivity(),
                android.R.layout.simple_list_item_checked,
                this.users
                );
        this.listView.setAdapter(arrayAdapter);
        for (int i = 0; i< users.length; i++){
            this.listView.setItemChecked(i,users[i].isActive());
        }
        this.listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG,"onItemClick:"+ position);
                CheckedTextView v = (CheckedTextView) view;
                boolean currentCheck = v.isChecked();
                UserAccount user = (UserAccount) listView.getItemAtPosition(position);
                user.setActive(!currentCheck);
            }
        });
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}