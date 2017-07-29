package com.aust.rakib.mywallet.Fragment;


import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.aust.rakib.mywallet.Adapter.WalletAdapter;
import com.aust.rakib.mywallet.Database.WalletDatabaseSource;
import com.aust.rakib.mywallet.Model.BackUpInformationModel;
import com.aust.rakib.mywallet.Model.WalletModel;
import com.aust.rakib.mywallet.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class BackUpHistoryFragment extends Fragment {

    ListView listView;
    WalletDatabaseSource databaseSource;
    WalletAdapter walletAdapter;
    AlertDialog choiceDialog;
    WalletModel updateObj;
    BackUpInformationModel backupObj;
    TextView totalIncomeTv;
    TextView totalExpenseTv;
    TextView totalTv;
    int total_income=0;
    int total_expense=0;
    int total_balance=0;
    BottomNavigationView bottomNavigationView;
  int listviewchildposition=0;
    LinearLayout linearLayout;
    int position;
    public BackUpHistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        position=getArguments().getInt("position");
        View view_=inflater.inflate(R.layout.fragment_backup_history, container, false);
        linearLayout= (LinearLayout) view_.findViewById(R.id.linear_layout_saved);
        listView= (ListView) view_.findViewById(R.id.listview);
        totalIncomeTv= (TextView) view_.findViewById(R.id.totalincomeTV);
        totalExpenseTv= (TextView) view_.findViewById(R.id.totalExpenseTV);
        totalTv= (TextView) view_.findViewById(R.id.totalTV);
        bottomNavigationView= (BottomNavigationView) view_.findViewById(R.id.bottom_navigation);
        Toast.makeText(getActivity(), position+"", Toast.LENGTH_SHORT).show();
        return view_;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        databaseSource= new WalletDatabaseSource(getActivity());


        initializeView();
        bottomNavigationView.setVisibility(bottomNavigationView.GONE);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,int position, long id) {
                for (int i = 0; i < listView.getChildCount(); i++) {
                    if(position == i ){
                        listView.getChildAt(i).setBackgroundColor(Color.RED);
                        listView.getChildAt(i).animate().translationX(2);
                        listView.getChildAt(i).animate().translationY(2);
                        listviewchildposition=i;
                    }else{
                        listView.getChildAt(i).setBackgroundColor(Color.LTGRAY);
                        listView.getChildAt(i).animate().translationX(0);
                        listView.getChildAt(i).animate().translationY(0);

                    }
                }
                final int ID= databaseSource.getSaveddata().get(position).getId();
                bottomNavigationView.setVisibility(bottomNavigationView.VISIBLE);
                bottomNavigationView.animate().translationY(0);
                bottomNavicationBar(ID);



                return true;
            }
        });
        if(listView.getChildCount()>0) {
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bottomNavigationView.animate().translationY(bottomNavigationView.getHeight());
                    listView.getChildAt(listviewchildposition).animate().translationX(0);
                    listView.getChildAt(listviewchildposition).animate().translationY(0);
                    listView.getChildAt(listviewchildposition).setBackgroundColor(Color.LTGRAY);
                }
            });
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bottomNavigationView.animate().translationY(bottomNavigationView.getHeight());

                for (int i = 0; i < listView.getChildCount(); i++) {
                    listView.getChildAt(i).animate().translationX(0);
                    listView.getChildAt(i).animate().translationY(0);
                    listView.getChildAt(i).setBackgroundColor(Color.LTGRAY);

                }

            }
        });

    }



    public void initializeView()
    {

        String key=databaseSource.getBackUpInformation().get(position).getKey();
        String fromDate=databaseSource.getBackUpInformation().get(position).getFromDate();
        String toDate=databaseSource.getBackUpInformation().get(position).getToDate();
        walletAdapter=new WalletAdapter(getActivity(),databaseSource.getBackedUpdata(key,fromDate,toDate));
        total_income =databaseSource.getBackUpInformation().get(position).getTotal_income();
        total_expense=databaseSource.getBackUpInformation().get(position).getTotal_expense();
        total_balance=databaseSource.getBackUpInformation().get(position).getTotal();
        listView.setAdapter(walletAdapter);
        totalIncomeTv.setText(total_income+"");
        totalExpenseTv.setText(total_expense+"");
        totalTv.setText(total_balance+"");


    }


    public void bottomNavicationBar(final int ID)
    {

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.updatebutton: {
                        bottomNavigationView.animate().translationY(bottomNavigationView.getHeight());

                        final AlertDialog.Builder updateBuilder = new AlertDialog.Builder(getActivity());
                        LayoutInflater inflater = getActivity().getLayoutInflater();

                        final View updateview = inflater.inflate(R.layout.custom_update_dialouge, null);
                        updateBuilder.setView(updateview);

                        updateBuilder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                EditText amount = (EditText) updateview.findViewById(R.id.alertamountET);
                                EditText description = (EditText) updateview.findViewById(R.id.alertdescriptionET);

                                int amount_ = Integer.parseInt(amount.getText().toString());
                                String description_ = description.getText().toString();

                                updateObj = new WalletModel(ID, amount_, description_);
                                boolean flag = databaseSource.UpdateSingleHistory(updateObj);

                                if (flag == true) {

                                    initializeView();
                                    walletAdapter.notifyDataSetChanged();
                                    Toast.makeText(getActivity(), "Updated successfully", Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(getActivity(), "Update Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        updateBuilder.show();
                    }
                        break;


                    case R.id.deletebutton:
                        {

                            WalletModel saveObj = new WalletModel(ID,0);
                            boolean flag = databaseSource.SaveHistory(saveObj);

                            if (flag == true) {

                                initializeView();
                                walletAdapter.notifyDataSetChanged();
                                Toast.makeText(getActivity(), "Deleted successfully", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(getActivity(), "Deletion Failed", Toast.LENGTH_SHORT).show();
                            }


                            bottomNavigationView.animate().translationY(bottomNavigationView.getHeight());


                            Toast.makeText(getActivity(), "Save", Toast.LENGTH_SHORT).show();
                        break;
                       }

                }
                return true;
            }

        });
    }

}

/*
  final AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                //    builder.setCancelable(false);



                choiceDialog= builder.create();
                choiceDialog.show();



     builder.setSingleChoiceItems(alertadapter,-1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {

                        switch(item) {
                            case 0:
                            {
                                final AlertDialog.Builder secondbuilder = new AlertDialog.Builder(getActivity());
                                secondbuilder.setTitle("Are You Sure ?");
                                secondbuilder.setCancelable(false);
                                secondbuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        boolean flag = databaseSource.deleteSingleHistory(ID);


                                        if (flag == true) {

                                            InitializeView();
                                            walletAdapter.notifyDataSetChanged();
                                            Toast.makeText(getActivity(), "Deleted successfully", Toast.LENGTH_SHORT).show();

                                        } else {
                                            Toast.makeText(getActivity(), "Deletion Failed", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                                secondbuilder.setNegativeButton("Cancel", null);
                                secondbuilder.show();
                                break;
                            }
                            case 1: {
                                final AlertDialog.Builder updateBuilder = new AlertDialog.Builder(getActivity());
                                LayoutInflater inflater = getActivity().getLayoutInflater();
                                final View updateview = inflater.inflate(R.layout.custom_update_dialouge, null);
                                updateBuilder.setView(updateview);
                                updateBuilder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        EditText amount = (EditText) updateview.findViewById(R.id.alertamountET);
                                        EditText description = (EditText) updateview.findViewById(R.id.alertdescriptionET);
                                        int amount_ = Integer.parseInt(amount.getText().toString());
                                        String description_ = description.getText().toString();

                                        updateObj=new WalletModel(ID,amount_,description_);
                                        boolean flag = databaseSource.UpdateSingleHistory(updateObj);

                                        if (flag == true) {

                                            InitializeView();
                                            walletAdapter.notifyDataSetChanged();
                                            Toast.makeText(getActivity(), "Updated successfully", Toast.LENGTH_SHORT).show();

                                        } else {
                                            Toast.makeText(getActivity(), "Update Failed", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                                updateBuilder.show();
                            }
                        }

                        choiceDialog.dismiss();
                    }
                });





 */