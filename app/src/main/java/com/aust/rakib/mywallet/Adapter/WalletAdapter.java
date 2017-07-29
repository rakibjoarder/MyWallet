package com.aust.rakib.mywallet.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aust.rakib.mywallet.R;
import com.aust.rakib.mywallet.Model.WalletModel;

import java.util.ArrayList;

/**
 * Created by Personal on 6/18/2017.
 */

public class WalletAdapter extends BaseAdapter {
     Context context;
    ArrayList<WalletModel>walletRecords;

    public WalletAdapter(Context context, ArrayList<WalletModel> walletRecords) {
        this.context = context;
        this.walletRecords = walletRecords;
    }

    @Override
    public int getCount() {
        return walletRecords.size();
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
        convertView=inflater.inflate(R.layout.single_row,parent,false);
        TextView amountTv= (TextView) convertView.findViewById(R.id.amountTV);
        TextView descriptionTv= (TextView) convertView.findViewById(R.id.descriptionTV);
        TextView dateTv= (TextView) convertView.findViewById(R.id.dateTV);
        ImageView imageView= (ImageView) convertView.findViewById(R.id.imageView);
        amountTv.setText(walletRecords.get(position).getAmount()+"");
        descriptionTv.setText(walletRecords.get(position).getDescription());
        dateTv.setText(walletRecords.get(position).getDate());
        imageView.setImageResource(walletRecords.get(position).getImage());
        return convertView;
    }


}
