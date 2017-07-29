package com.aust.rakib.mywallet.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.aust.rakib.mywallet.Adapter.BackUpListviewAdatper;
import com.aust.rakib.mywallet.Database.WalletDatabaseSource;
import com.aust.rakib.mywallet.Model.BackUpInformationModel;
import com.aust.rakib.mywallet.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BackUpRecordListViewFragment extends Fragment {

  ListView listView;
    BackUpListviewAdatper adatper;
    WalletDatabaseSource databaseSource;
    Bundle b;
    public BackUpRecordListViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_back_up_record_list_view, container, false);
        listView= (ListView) view.findViewById(R.id.backuprecordlistview);
        b=new Bundle();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        databaseSource= new WalletDatabaseSource(getActivity());
        adatper=new BackUpListviewAdatper(getActivity(),databaseSource.getBackUpInformation());
        listView.setAdapter(adatper);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                b.putInt("position",position);
                Fragment fragment=new BackUpHistoryFragment();
                FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
                FragmentTransaction ft= fragmentManager.beginTransaction();
                ft.replace(R.id.fragmentContainer,fragment);
                fragment.setArguments(b);
                ft.commit();
            }
        });
    }
}
