package com.aust.rakib.mywallet.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aust.rakib.mywallet.Model.BackUpInformationModel;
import com.aust.rakib.mywallet.R;

import java.util.ArrayList;

/**
 * Created by Personal on 7/5/2017.
 */

public class BackUpListviewAdatper extends BaseAdapter {
    Context context;
    ArrayList<BackUpInformationModel> data=new ArrayList<>();

    public BackUpListviewAdatper(Context context,ArrayList<BackUpInformationModel> data)
    {
        this.context = context;
        this.data=data;
    }

    @Override
    public int getCount() {
      return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(R.layout.backuprecord_custom_row,parent,false);

        TextView fromdate= (TextView) convertView.findViewById(R.id.fromdateTV);
        TextView todate= (TextView) convertView.findViewById(R.id.todateTV);
        TextView total_income= (TextView) convertView.findViewById(R.id.totalincomebackupTV);
        TextView total_expense= (TextView) convertView.findViewById(R.id.totalexpensebackupTV);
        TextView total= (TextView) convertView.findViewById(R.id.totalBackUpTV);
        ProgressBar progressBar= (ProgressBar) convertView.findViewById(R.id.progressbar);

        fromdate.setText(data.get(position).getFromDate());
        todate.setText(data.get(position).getToDate());
        total_income.setText(data.get(position).getTotal_income()+"");
        total_expense.setText(data.get(position).getTotal_expense()+"");
        total.setText(data.get(position).getTotal()+"");
        double d=(double) (data.get(position).getTotal_income());
        double i=((data.get(position).getTotal_expense())/d)*100;
        int j=(int) i;
        progressBar.setProgress(j);
        return convertView;
    }
}
