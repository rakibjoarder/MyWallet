package com.aust.rakib.mywallet.Fragment;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.aust.rakib.mywallet.Database.WalletDatabaseSource;
import com.aust.rakib.mywallet.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class BackUpRecordsFragment extends Fragment {


    int month,year,day;
    String fromDate;
    String toDate;
    Calendar calendar;
    Button fromButton;
    Button toButton;
    Button saveButton;
    WalletDatabaseSource databaseSource;
    public BackUpRecordsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      final View view=inflater.inflate(R.layout.fragment_backup_records, container, false);

        fromButton= (Button) view.findViewById(R.id.fromButton);
        toButton= (Button) view.findViewById(R.id.toButton);
        saveButton= (Button) view.findViewById(R.id.saveButton);
        databaseSource=new WalletDatabaseSource(getActivity());
        calendar=Calendar.getInstance(Locale.getDefault());
        year=calendar.get(Calendar.YEAR);
        month=calendar.get(Calendar.MONTH);
        day=calendar.get(Calendar.DAY_OF_MONTH);


        fromButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    DatePickerDialog dpd =new DatePickerDialog(getActivity(),fromdatelistener,year,month,day);
                    dpd.show();

            }
        });
        toButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpd =new DatePickerDialog(getActivity(),todatelistener,year,month,day);
                dpd.show();

            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             databaseSource.saveRecords(fromDate,toDate);
                Toast.makeText(getActivity(), "Data Saved", Toast.LENGTH_SHORT).show();
            }
        });
        return  view;
    }
    private DatePickerDialog.OnDateSetListener fromdatelistener= new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            fromButton.setVisibility(View.GONE);
            toButton.setVisibility(View.VISIBLE);
            Date date = new Date(year-1900,month,dayOfMonth);
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            String cdate = formatter.format(date);
            fromDate= "'"+cdate+"'";
        }
    };
    private DatePickerDialog.OnDateSetListener todatelistener= new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            toButton.setVisibility(View.GONE);
            saveButton.setVisibility(View.VISIBLE);
            Date date = new Date(year-1900,month,dayOfMonth);
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            String cdate = formatter.format(date);
            toDate= "'"+cdate+"'";
        }
    };

}
