package com.arhamtechnolabs.karohamapp.Hepers;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.arhamtechnolabs.karohamapp.models.CartModel;

import java.util.ArrayList;
import java.util.List;

public class LocalWishListHelper {
    Context context;
    SQLiteDatabase database;
    String tableName = "wishList";
    //String wishListTable = "wishList";

    public LocalWishListHelper(Context c) {
        context = c;
        database = context.openOrCreateDatabase("Billing.db", Context.MODE_PRIVATE, null);
        Log.i("Database","Created");


        /*childId SubId CatId childName description imageLink qty amount */
        //database.execSQL("Create table if NOT EXISTS "+tableName+" (courseId varchar, courseTitle varchar, coursePrice varchar , discountFlag varchar, discountedPrice varchar, userId varchar, isFreeCourse varchar, thumbnail varchar)");
        database.execSQL("Create table if NOT EXISTS "+tableName+" (courseId varchar, courseTitle varchar, coursePrice varchar , discountFlag varchar, discountedPrice varchar, userId varchar, isFreeCourse varchar, thumbnail varchar, ratings varchar)");

        Log.i("Database", "Table created");
    }

    public void insertData(CartModel data) {
        try{
            String insertQuery = "Insert into " + tableName + " Values ('" + data.getCourseId() + "', '"+data.getCourseTitle()+"', '"+data.getCoursePrice() +"', '"+data.getCourseDiscountFlag()+"', '"+
                    data.getCourseDiscountPrice() + "', '" +
                    data.getUserId() +"', '"+ data.getIsFreeCourse()+"', '"+data.getThumbnail() + "', '" +data.getRatings() + "' " + ")";
            String description = "";
            Log.i("InsertQuery", insertQuery);
            database.execSQL(insertQuery);
            Log.i("Database", "Data inserted");

        }catch (Exception e) {
            System.out.println("SqlException: "+e.getMessage());
        }
    }
    public boolean isCourseAddedToWishList(CartModel data) {
        try{

            Cursor c = database.rawQuery("Select * from "+tableName+" where courseId = '"+data.getCourseId()+"'", null);
            Log.i("Database", "Ceking course added to cart or not");

            return c.moveToFirst();

        }catch (Exception e) {
            System.out.println("SqlException: "+e.getMessage());
        }
        return false;
    }
    public void flushWishList() {
        String insertQuery = "delete from " +tableName;
        database.execSQL(insertQuery);
        Log.i("DeleteQuery", insertQuery);
    }

    public void deleteItem(CartModel data) {
        String insertQuery = "delete from " +tableName + " where courseId = '" + data.getCourseId() + "'";;
        database.execSQL(insertQuery);
        Log.i("InsertQuery", insertQuery);
    }
    public void deleteItem(String id) {
        String insertQuery = "delete from " +tableName + " where courseId = '" + id+"'";;
        database.execSQL(insertQuery);
        Log.i("InsertQuery", insertQuery);
    }

    public List<CartModel> fetchWishListData() {

        List<CartModel> result = new ArrayList<>();
        try{
            String query = "Select * from " + tableName + " order by courseId";
            Cursor cursor = database.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                do {
                    /*childId SubId CatId childName description imageLink qty amount */
                    CartModel model = new CartModel(
                            cursor.getString(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4),
                            cursor.getString(5),
                            cursor.getString(6),
                            cursor.getString(7),
                            cursor.getString(8));
                    System.out.println("WishList " +model.toString());
                    result.add(model);

                } while (cursor.moveToNext());
            }


        }catch (SQLException e) {
            Log.i("Exception", e.getMessage());

        }
        return result;

    }
    public void deleteCartTable() {
        try{
            String query = "Delete from " + tableName;
            database.execSQL(query);
            Log.i("Database", "TableDeleted");
        }catch (SQLException e) {
            System.out.println("SqlException: " +e.getMessage());
        }
    }

    public int getTotalItemsInCart() {
        List<CartModel> result = fetchWishListData();
        return result.size();
    }

    public int getGrandTotal() {
        List<CartModel> result = fetchWishListData();
        int grandTotal = 0;
        for (CartModel i: result) {
            if (i.getCourseDiscountFlag().equals("1")) {
                grandTotal+=ParseHelper.parseInt(i.getCourseDiscountPrice());
            }else {
                grandTotal+=ParseHelper.parseInt(i.getCoursePrice());
            }
        }
        return grandTotal;
    }
}
