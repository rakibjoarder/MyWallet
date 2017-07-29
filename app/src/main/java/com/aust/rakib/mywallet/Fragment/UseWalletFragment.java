package com.aust.rakib.mywallet.Fragment;


import android.content.DialogInterface;
import android.content.Intent;

import java.text.SimpleDateFormat;

import java.text.DecimalFormat;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.aust.rakib.mywallet.Database.WalletDatabaseSource;
import com.aust.rakib.mywallet.R;
import com.aust.rakib.mywallet.Model.WalletModel;

import java.util.Date;
import java.util.Locale;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class UseWalletFragment extends Fragment implements View.OnClickListener {
    private EditText amountET;
    private EditText descriptionET;
    WalletDatabaseSource walletDatabaseSource;
    Button savebutton;
    Button showbutton;

    ImageButton calculatorButton;

    int amount_;
    String description_;
    int image=R.drawable.ic_income;


    //................calculator
    TextView calculated_Value;
    String displayData ="";
    String backgroundata="";
    String flag="noAction";
    String data2;
    String data3;
    String[] data;
    boolean dotflag=true;
    DecimalFormat df = new DecimalFormat("#.####");
    Button oneBT;
    Button twoBT;
    Button threeBT;
    Button fourBT;
    Button fiveBT;
    Button sixBT;
    Button sevenBT;
    Button eightBT;
    Button nineBT;
    Button zeroBT;
    Button equalBT;
    Button dotBT;
    Button plusBT;
    Button minusBT;
    Button multiBT;
    Button divideBT;
    Button deleteBT;

    //.....................

    public UseWalletFragment() {
        // Required empty public constructor
    }
    private String getDateTime() {

        SimpleDateFormat dateFormat =new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        Date date =new Date();
        return dateFormat.format(date);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_use_wallet, container, false);
        amountET= (EditText) view.findViewById(R.id.amountET);
        descriptionET= (EditText) view.findViewById(R.id.descriptionET);
        savebutton= (Button) view.findViewById(R.id.savebutton);

        calculatorButton= (ImageButton) view.findViewById(R.id.calculatorButton);

        RadioButton rbIncomeButton= (RadioButton) view.findViewById(R.id.radio_income);
        RadioButton rbExpenseButton= (RadioButton) view.findViewById(R.id.radio_expense);
        rbIncomeButton.setOnClickListener(this);
        rbExpenseButton.setOnClickListener(this);
        walletDatabaseSource=new WalletDatabaseSource(getActivity());
        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(amountET.getText().toString().equals(""))
                {
                    amountET.setError("Insert Amount");
                }
                else if(descriptionET.getText().toString().equals(""))
                {
                    descriptionET.setError("Insert Description");
                }
                else if(!amountET.getText().toString().equals("") && !descriptionET.getText().toString().equals("") ){
                        amount_=Integer.parseInt(amountET.getText().toString());
                        description_=descriptionET.getText().toString();

                String date_=getDateTime();
                WalletModel walletModel =new WalletModel(image,date_,amount_,description_);
                boolean status=walletDatabaseSource.insertData(walletModel);
                if(status==true)
                {
                    Toast.makeText(getActivity(), "INSERTED", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(getActivity(), "INSERTION FAILED", Toast.LENGTH_SHORT).show();
                }
                }

            }

        });




       calculatorButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               AlertDialog.Builder calculatorBuilder = new AlertDialog.Builder(getActivity());
               LayoutInflater inflater = getActivity().getLayoutInflater();
               View updateview = inflater.inflate(R.layout.calculator, null);
               calculatorBuilder.setView(updateview);
               calculated_Value= (TextView) updateview.findViewById(R.id.calculatedvalue);



               oneBT= (Button) updateview.findViewById(R.id.oneBT);
               twoBT= (Button) updateview.findViewById(R.id.twoBT);
               threeBT= (Button) updateview.findViewById(R.id.threeBT);
               fourBT= (Button) updateview.findViewById(R.id.fourBT);
               fiveBT= (Button) updateview.findViewById(R.id.fiveBT);
               sixBT= (Button) updateview.findViewById(R.id.sixBT);
               sevenBT= (Button) updateview.findViewById(R.id.sevenBT);
               eightBT= (Button) updateview.findViewById(R.id.eightBT);
               nineBT= (Button) updateview.findViewById(R.id.nineBT);
               zeroBT= (Button) updateview.findViewById(R.id.zeroBT);
               equalBT= (Button) updateview.findViewById(R.id.equalBT);
               dotBT= (Button) updateview.findViewById(R.id.dotBT);
               plusBT= (Button) updateview.findViewById(R.id.plusBT);
               minusBT= (Button) updateview.findViewById(R.id.minusBT);
               multiBT= (Button) updateview.findViewById(R.id.multiBT);
               divideBT= (Button) updateview.findViewById(R.id.divideBT);
               deleteBT= (Button) updateview.findViewById(R.id.deleteBT);


               oneBT.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       displayData +="1";
                       backgroundata +="1";

                       calculated_Value.setText(displayData);
                   }
               });
               twoBT.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       displayData +="2";
                       backgroundata +="2";

                       calculated_Value.setText(displayData);
                   }
               });
               threeBT.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       displayData +="3";
                       backgroundata +="3";

                       calculated_Value.setText(displayData);
                   }
               });
               fourBT.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       displayData +="4";
                       backgroundata +="4";

                       calculated_Value.setText(displayData);
                   }
               });
               fiveBT.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       displayData +="5";
                       backgroundata +="5";

                       calculated_Value.setText(displayData);
                   }
               });
              sixBT.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       displayData +="6";
                       backgroundata +="6";

                       calculated_Value.setText(displayData);
                   }
               });
               sevenBT.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       displayData +="7";
                       backgroundata +="7";

                       calculated_Value.setText(displayData);
                   }
               });
              eightBT.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       displayData +="8";
                       backgroundata +="8";

                       calculated_Value.setText(displayData);
                   }
               });
              nineBT.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       displayData +="9";
                       backgroundata +="9";

                       calculated_Value.setText(displayData);
                   }
               });
              zeroBT.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {

                       displayData +="0";
                       backgroundata +="0";
                       calculated_Value.setText(displayData);
                   }
               });
               equalBT.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {

                       try{
                           if(flag.equals("plus") && !(backgroundata.substring(backgroundata.length()-1,backgroundata.length()).trim()).equals("p"))
                           {
                               data= backgroundata.split("p");
                               data2=data[0];
                               data3=data[1];
                               displayData=df.format( Double.parseDouble(data2.trim()) +   Double.parseDouble(data3.trim()));
                               backgroundata=displayData;

                               flag="noAction";
                           }

                           else if(flag.equals("minus")  && !(backgroundata.substring(backgroundata.length()-1,backgroundata.length()).trim()).equals("-"))
                           {
                               data= backgroundata.split("-");
                               data2=data[0];
                               data3=data[1];

                               if(Double.parseDouble(data2.trim()) -   Double.parseDouble(data3.trim()) <= 0)
                               {
                                   displayData="0";

                               }
                               else
                               {
                                   displayData=df.format( Double.parseDouble(data2.trim()) -   Double.parseDouble(data3.trim()));
                               }
                               backgroundata=displayData;
                               flag="noAction";
                           }
                           else if(flag.equals("multi")  && !(backgroundata.substring(backgroundata.length()-1,backgroundata.length()).trim()).equals("m"))
                           {
                               data= backgroundata.split("m");
                               data2=data[0];
                               data3=data[1];
                               displayData=df.format( Double.parseDouble(data2.trim()) *   Double.parseDouble(data3.trim()));
                               backgroundata=displayData;
                               flag="noAction";
                           }
                           else if(flag.equals("divide")  && !(backgroundata.substring(backgroundata.length()-1,backgroundata.length()).trim()).equals("/"))
                           {
                               data= backgroundata.split("/");
                               data2=data[0];
                               data3=data[1];
                               displayData=df.format( Double.parseDouble(data2.trim()) /   Double.parseDouble(data3.trim()));
                               backgroundata=displayData;
                               flag="noAction";
                           }
                           else
                           {
                               String lol="";
                           }


                       }catch (Exception e)
                       {

                       }
                       calculated_Value.setText(displayData);
                   }
               });
               plusBT.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       try{
                           if( backgroundata.length()==0 ||(backgroundata.substring(backgroundata.length()-1,backgroundata.length()).trim()).equals("p") || backgroundata.contains("-") ||backgroundata.contains("m") ||backgroundata.contains("/"))
                           {

                           }
                           else if(backgroundata.split("p").length==1  )
                           {
                               displayData +="+";
                               backgroundata +="p";
                               flag="plus";

                           }
                           else if(backgroundata.split("p").length==2)
                           {
                               data= backgroundata.split("p");
                               data2=data[0];
                               data3=data[1];
                               displayData=df.format( Double.parseDouble(data2.trim()) +   Double.parseDouble(data3.trim()));
                               backgroundata=displayData;
                               flag="noAction";

                           }
                       }catch (Exception e)
                       {

                       }



                       calculated_Value.setText(displayData);
                   }
               });
               minusBT.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       try {
                           if(backgroundata.length()==0 ||(backgroundata.substring(backgroundata.length()-1,backgroundata.length()).trim()).equals("-") ||  backgroundata.contains("p") ||backgroundata.contains("m") ||backgroundata.contains("/"))
                           {

                           }
                           else if(backgroundata.split("-").length==1  )
                           {
                               displayData +="-";
                               backgroundata +="-";
                               flag="minus";

                           }
                           else if(backgroundata.split("-").length==2)
                           {
                               data= backgroundata.split("-");
                               data2=data[0];
                               data3=data[1];
                               displayData=df.format( Double.parseDouble(data2.trim()) -  Double.parseDouble(data3.trim()));
                               backgroundata=displayData;
                               flag="noAction";

                           }
                       }catch (Exception e)
                       {

                       }


                       calculated_Value.setText(displayData);
                   }
               });
               multiBT.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       try{
                           if(backgroundata.length()==0 ||(backgroundata.substring(backgroundata.length()-1,backgroundata.length()).trim()).equals("m") ||   backgroundata.contains("p") ||backgroundata.contains("-") ||backgroundata.contains("/"))
                           {

                           }
                           else if(backgroundata.split("m").length==1  )
                           {
                               displayData +="*";
                               backgroundata +="m";
                               flag="multi";

                           }
                           else if(backgroundata.split("m").length==2)
                           {
                               data= backgroundata.split("m");
                               data2=data[0];
                               data3=data[1];
                               displayData=df.format( Double.parseDouble(data2.trim()) *   Double.parseDouble(data3.trim()));
                               backgroundata=displayData;
                               flag="noAction";

                           }

                       }catch (Exception e)
                       {

                       }


                       calculated_Value.setText(displayData);
                   }
               });
               divideBT.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {

                       try {
                           if(backgroundata.length()==0 ||(backgroundata.substring(backgroundata.length()-1,backgroundata.length()).trim()).equals("/") ||  backgroundata.contains("p") ||backgroundata.contains("-") ||backgroundata.contains("m"))
                           {

                           }
                           else if(backgroundata.split("/").length==1  )
                           {
                               displayData +="/";
                               backgroundata +="/";
                               flag="divide";

                           }
                           else if(backgroundata.split("/").length==2)
                           {
                               data= backgroundata.split("/");
                               data2=data[0];
                               data3=data[1];
                               displayData=df.format( Double.parseDouble(data2.trim()) /   Double.parseDouble(data3.trim()));
                               backgroundata=displayData;
                               flag="noAction";

                           }
                       }catch (Exception e)
                       {

                       }
                       calculated_Value.setText(displayData);
                   }
               });
               dotBT.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       try{
                           String[] p=backgroundata.split("p");
                           String[] m=backgroundata.split("-");
                           String[] M=backgroundata.split("m");
                           String[] d=backgroundata.split("/");
                           if(!p[1].contains(".")||!m[1].contains(".")||!M[1].contains(".")||!d[1].contains("."))
                           {
                               dotflag=true;
                           }

                       }catch (Exception e)
                       {

                       }
                       try {
                           if(backgroundata.substring(backgroundata.length()-1,backgroundata.length()).equals("p") ||
                                   backgroundata.substring(backgroundata.length()-1,backgroundata.length()).equals("-") ||
                                   backgroundata.substring(backgroundata.length()-1,backgroundata.length()).equals("m") ||
                                   backgroundata.substring(backgroundata.length()-1,backgroundata.length()).equals("/"))
                           {
                               dotflag=true;
                           }
                       }catch (Exception e)
                       {

                       }
                       if(dotflag)
                       {displayData +=".";
                           backgroundata +=".";
                           dotflag=false;
                       }
                       calculated_Value.setText(displayData);
                   }
               });
               deleteBT.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       try
                       {
                           if(!backgroundata.contains("."))
                           {
                               dotflag=true;
                           }
                           if( !backgroundata.contains(".") && (backgroundata.split("p").length==2 ||backgroundata.split("-").length==2 ||backgroundata.split("m").length==2||backgroundata.split("/").length==2))
                           {
                               dotflag=true;
                           }


                           displayData = displayData.substring(0, displayData.length() - 1);
                           backgroundata=backgroundata.substring(0, displayData.length() - 1);
                       }catch (Exception e)
                       {

                       }
                       flag="noAction";
                       calculated_Value.setText(displayData);
                   }
               });


               calculatorBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       amountET.setText(displayData);
                       displayData="";
                       backgroundata="";
                   }
               });

               calculatorBuilder.show();

           }
       });




        return view;
    }

    @Override
    public void onClick(View v) {

        boolean checked = ((RadioButton) v).isChecked();

        switch (v.getId()) {
            case R.id.radio_income: {

                if(checked)
                {
                   image =R.drawable.ic_income;
                }
                break;
            }
            case R.id.radio_expense: {

                if(checked)
                {
                    image =R.drawable.ic_expense;
                }
                break;
            }

        }
    }
}
