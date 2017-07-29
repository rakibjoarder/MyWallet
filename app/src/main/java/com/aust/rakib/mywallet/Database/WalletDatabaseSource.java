package com.aust.rakib.mywallet.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.aust.rakib.mywallet.Model.BackUpInformationModel;
import com.aust.rakib.mywallet.Model.WalletModel;
import com.aust.rakib.mywallet.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Personal on 6/18/2017.
 */

public class WalletDatabaseSource {
    private  WalletHelper walletHelper;
    private WalletModel walletModel;
    private SQLiteDatabase sqLiteDatabase;

    public WalletDatabaseSource(Context context) {
      walletHelper =new WalletHelper(context);
    }
    public void open()
    {
        sqLiteDatabase=walletHelper.getWritableDatabase();
    }
    public void close()
    {
        sqLiteDatabase.close();
    }

    public boolean insertData(WalletModel walletModel)
    {   this.open();
        ContentValues values=new ContentValues();
        values.put(walletHelper.IMAGE,walletModel.getImage());
        values.put(walletHelper.DATE,walletModel.getDate());
        values.put(walletHelper.AMOUNT,walletModel.getAmount());
        values.put(walletHelper.DESCRIPTION,walletModel.getDescription());
        Long ID= sqLiteDatabase.insert(walletHelper.TABLE_NAME,null,values);
        this.close();
        if(ID>0)
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    public ArrayList<WalletModel> getAlldata()
    {   this.open();
        ArrayList<WalletModel> walletData=new ArrayList<>();
        Cursor cursor=sqLiteDatabase.query(walletHelper.TABLE_NAME,null,null,null,null,null,null,null);
        cursor.moveToFirst();

       if (cursor != null)
       {
           for(int i=0 ;i<cursor.getCount();i++)
           {
               int id=cursor.getInt(cursor.getColumnIndex(walletHelper.WALLET_ID));
               int image=cursor.getInt(cursor.getColumnIndex(walletHelper.IMAGE));
               int amount=cursor.getInt(cursor.getColumnIndex(walletHelper.AMOUNT));
               String description=cursor.getString(cursor.getColumnIndex(walletHelper.DESCRIPTION));
               String date=cursor.getString(cursor.getColumnIndex(walletHelper.DATE));
               walletData.add(new WalletModel(id,image,date,amount,description));
               cursor.moveToNext();
           }
           cursor.close();
           this.close();


        }
        return walletData;

    }

    public  boolean deleteSingleHistory(int walletID)
    {
        this.open();

        long deletdId = sqLiteDatabase.delete(walletHelper.TABLE_NAME,walletHelper.WALLET_ID+ " = "+walletID,null);
        this.close();
        if(deletdId>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean UpdateSingleHistory(WalletModel updateObj) {
        this.open();
        ContentValues values=new ContentValues();
        values.put(walletHelper.AMOUNT,updateObj.getAmount());
        values.put(walletHelper.DESCRIPTION,updateObj.getDescription());


        long updatedId = sqLiteDatabase.update(walletHelper.TABLE_NAME,values,walletHelper.WALLET_ID+" = "+updateObj.getId(),null);
        this.close();
        if(updatedId>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    public ArrayList<Integer> usageList() {
        this.open();
        ArrayList<Integer>usageList=new ArrayList<>();
        int total_income=0;
        int total_expense=0;
        int total_balance=0;
        String[] columns={walletHelper.AMOUNT,walletHelper.IMAGE};
        Cursor cursor=sqLiteDatabase.query(walletHelper.TABLE_NAME,columns,null,null,null,null,null,null);
        cursor.moveToFirst();
        if (cursor != null)
        {
            for(int i=0 ;i<cursor.getCount();i++)
            {   int amount=cursor.getInt(cursor.getColumnIndex(walletHelper.AMOUNT));
                int imageID=cursor.getInt(cursor.getColumnIndex(walletHelper.IMAGE));
                 if(imageID==R.drawable.ic_income)
                 {
                     total_income=total_income+amount;
                 }
                 else if(imageID==R.drawable.ic_expense)
                 {
                     total_expense=total_expense+amount;
                 }
                 total_balance=total_income-total_expense;
                cursor.moveToNext();
            }
            cursor.close();
            this.close();

        }
        usageList.add(total_income);
        usageList.add(total_expense);
        usageList.add(total_balance);
        return usageList;
    }

    public boolean SaveHistory(WalletModel saveObj)//for sAVED fRAGMENT
    {
        this.open();
        ContentValues values=new ContentValues();
        values.put(walletHelper.STATUS,saveObj.getStatus());


        long savedId = sqLiteDatabase.update(walletHelper.TABLE_NAME,values,walletHelper.WALLET_ID+" = "+saveObj.getId(),null);
        this.close();
        if(savedId>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    public ArrayList<WalletModel> getSaveddata()  //for sAVED fRAGMENT
    {   this.open();
        ArrayList<WalletModel> walletData=new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + walletHelper.TABLE_NAME + " WHERE "
                + walletHelper.STATUS + " = " + " 1 ";
        //  String selectQuery = "SELECT * FROM wallet_table WHERE date = '04-07-2017'";
        Cursor cursor=sqLiteDatabase.rawQuery(selectQuery,null);
        cursor.moveToFirst();
        Log.e("raaa",cursor.getCount()+"");
        if (cursor != null)
        {
            for(int i=0 ;i<cursor.getCount();i++)
            {
                int id=cursor.getInt(cursor.getColumnIndex(walletHelper.WALLET_ID));
                int image=cursor.getInt(cursor.getColumnIndex(walletHelper.IMAGE));
                int amount=cursor.getInt(cursor.getColumnIndex(walletHelper.AMOUNT));
                String description=cursor.getString(cursor.getColumnIndex(walletHelper.DESCRIPTION));
                String date=cursor.getString(cursor.getColumnIndex(walletHelper.DATE));
                walletData.add(new WalletModel(id,image,date,amount,description));
                cursor.moveToNext();
            }
            cursor.close();
            this.close();


        }
        return walletData;

    }
////////////////////////// kaj chole
    public void saveRecords(String fromDate,String toDate )
    {
        this.open();

        String sql1 = "INSERT INTO " + WalletHelper.BACKUP_TABLE_NAME +"(image,amount,description,date,status) SELECT image,amount,description,date,status  FROM " +WalletHelper.TABLE_NAME+ " WHERE " +WalletHelper.DATE+ " BETWEEN "+ fromDate + " AND " + toDate ;


        sqLiteDatabase.execSQL(sql1);

        Random rand = new Random();

        int  n = rand.nextInt(1000000000) + 1;
        String statuskey=Integer.toString(n);
        UpdatebackupHistory( statuskey);

        int total_income=BackUpusageList(statuskey,fromDate,toDate).get(0);
        int total_expense=BackUpusageList(statuskey,fromDate,toDate).get(1);
        int total=BackUpusageList(statuskey,fromDate,toDate).get(2);
        BackUpInformationModel backUpObj= new BackUpInformationModel(fromDate,toDate, statuskey,total_income,total_expense,total);
        Log.e("raaa", statuskey);
        insertBackUpInFormationData(backUpObj);
 this.close();

    }

    public void UpdatebackupHistory(String status) {
        this.open();
        ContentValues values=new ContentValues();
        values.put(walletHelper.STATUS,status);
        sqLiteDatabase.update(walletHelper.BACKUP_TABLE_NAME,values,walletHelper.STATUS+" = 0 ",null);
        this.close();

    }

    public ArrayList<WalletModel> getBackedUpdata(String status,String fromDate,String toDate)  //for sAVED fRAGMENT
    {   this.open();
        ArrayList<WalletModel> walletData=new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + walletHelper.BACKUP_TABLE_NAME + " WHERE "
                + walletHelper.STATUS + " = " +status+ " AND " +walletHelper.DATE+ " BETWEEN " +fromDate+ " AND " +toDate;
        //  String selectQuery = "SELECT * FROM wallet_table WHERE date = '04-07-2017'";
        Cursor cursor=sqLiteDatabase.rawQuery(selectQuery,null);
        cursor.moveToFirst();

        if (cursor != null)
        {
            for(int i=0 ;i<cursor.getCount();i++)
            {
                int id=cursor.getInt(cursor.getColumnIndex(walletHelper.WALLET_ID));
                int image=cursor.getInt(cursor.getColumnIndex(walletHelper.IMAGE));
                int amount=cursor.getInt(cursor.getColumnIndex(walletHelper.AMOUNT));
                String description=cursor.getString(cursor.getColumnIndex(walletHelper.DESCRIPTION));
                String date=cursor.getString(cursor.getColumnIndex(walletHelper.DATE));
                walletData.add(new WalletModel(id,image,date,amount,description));
                cursor.moveToNext();
            }
            cursor.close();
            this.close();


        }
        return walletData;

    }





    public void insertBackUpInFormationData(BackUpInformationModel obj)
    {   this.open();
        ContentValues values=new ContentValues();
        values.put(walletHelper.BACKUP_INFORMATION_FROM_DATE,obj.getFromDate());
        values.put(walletHelper.BACKUP_INFORMATION_TO_DATE,obj.getToDate());
        values.put(walletHelper.BACKUP_INFORMATION_KEY,obj.getKey());
        values.put(walletHelper.BACKUP_INFORMATION_TOTAL_INCOME,obj.getTotal_income());
        values.put(walletHelper.BACKUP_INFORMATION_TOTAL_EXPENSE,obj.getTotal_expense());
        values.put(walletHelper.BACKUP_INFORMATION_TOTAL,obj.getTotal());

        sqLiteDatabase.insert(walletHelper.BACKUP_INFORMATION_TABLE_NAME,null,values);

    }

    public ArrayList<BackUpInformationModel> getBackUpInformation()
    {   this.open();
        ArrayList<BackUpInformationModel>backUpData=new ArrayList<>();
        Cursor cursor=sqLiteDatabase.query(walletHelper.BACKUP_INFORMATION_TABLE_NAME,null,null,null,null,null,null,null);
        cursor.moveToFirst();

        if (cursor != null)
        {
            for(int i=0 ;i<cursor.getCount();i++)
            {
                int id=cursor.getInt(cursor.getColumnIndex(walletHelper.BACKUP_INFORMATION_ID));
                String fromdate=cursor.getString(cursor.getColumnIndex(walletHelper.BACKUP_INFORMATION_FROM_DATE));
                String todate=cursor.getString(cursor.getColumnIndex(walletHelper.BACKUP_INFORMATION_TO_DATE));
                String key=cursor.getString(cursor.getColumnIndex(walletHelper.BACKUP_INFORMATION_KEY));
                int total_income=cursor.getInt(cursor.getColumnIndex(walletHelper.BACKUP_INFORMATION_TOTAL_INCOME));
                int total_expense=cursor.getInt(cursor.getColumnIndex(walletHelper.BACKUP_INFORMATION_TOTAL_EXPENSE));
                int total=cursor.getInt(cursor.getColumnIndex(walletHelper.BACKUP_INFORMATION_TOTAL));
                backUpData.add(new BackUpInformationModel(fromdate,todate,key,total_income,total_expense,total));
                cursor.moveToNext();
            }
            cursor.close();
            this.close();


        }
        return backUpData;

    }

    public ArrayList<Integer> BackUpusageList(String status,String fromDate,String toDate) {
        this.open();
        ArrayList<Integer>usageList=new ArrayList<>();
        int total_income=0;
        int total_expense=0;
        int total_balance=0;
        String selectQuery = "SELECT  * FROM " + walletHelper.BACKUP_TABLE_NAME + " WHERE "
                + walletHelper.STATUS + " = " +status+ " AND " +walletHelper.DATE+ " BETWEEN " +fromDate+ " AND " +toDate;
        //  String selectQuery = "SELECT * FROM wallet_table WHERE date = '04-07-2017'";
        Cursor cursor=sqLiteDatabase.rawQuery(selectQuery,null);


        cursor.moveToFirst();
        if (cursor != null)
        {
            for(int i=0 ;i<cursor.getCount();i++)
            {   int amount=cursor.getInt(cursor.getColumnIndex(walletHelper.AMOUNT));
                int imageID=cursor.getInt(cursor.getColumnIndex(walletHelper.IMAGE));
                if(imageID==R.drawable.ic_income)
                {
                    total_income=total_income+amount;
                }
                else if(imageID==R.drawable.ic_expense)
                {
                    total_expense=total_expense+amount;
                }
                total_balance=total_income-total_expense;
                cursor.moveToNext();
            }
            cursor.close();
            this.close();

        }
        usageList.add(total_income);
        usageList.add(total_expense);
        usageList.add(total_balance);
        return usageList;
    }




///////////////////////////////


}
