package com.example.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText ed1,ed2,ed3;
RecyclerView rv;
SQLiteDatabase sql;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        ed1 = findViewById( R.id.ed1 );
        ed2 = findViewById( R.id.ed2 );
        ed3 = findViewById( R.id.ed3 );
        rv = findViewById( R.id.rv );
        rv.setLayoutManager( new LinearLayoutManager( this ) );

        sql = openOrCreateDatabase("mydatabase" ,MODE_PRIVATE,null);
        String q = "create table if not exists student(name varchar(20),password varchar(20),contact interger(10))";
        sql.execSQL(q);
    }

    public void reset(View view) {
        ed1.setText( "" );
        ed2.setText( "" );
        ed3.setText( "" );
    }
    public void insert(View view) {
        ContentValues values = new ContentValues();
        values.put( "name",ed1.getText().toString() );
        values.put( "password",ed2.getText().toString() );
        values.put( "contact",ed3.getText().toString() );
        long n = sql.insert( "student",null,values );
        Toast.makeText( this,n+"records inserted.........",Toast.LENGTH_SHORT ).show();
    }
    public void update(View view) {
        String name = ed1.getText().toString();
        ContentValues values = new ContentValues();
        values.put( "password",ed2.getText().toString() );
        values.put( "contact",ed3.getText().toString() );
        //update student set password=?,contact=? where name=?
        int n = sql.update( "student",values,"name=?",new String[]{name} );
        Toast.makeText( this,n+ "rows removed", Toast.LENGTH_SHORT ).show();
    }
    public void delete(View view) {
        String name = ed1.getText().toString();
        int n = sql.delete( "student","name=?",new String[]{name} );
        Toast.makeText( this,n+ "rows removed", Toast.LENGTH_SHORT ).show();
    }
    public void search(View view) {
        String name = ed1.getText().toString();
        Cursor cursor = sql.query( "student",null,"name=?",new String[]{name},null,null ,null,null);
        MyAdapter adapter = new MyAdapter( cursor );
        rv.setAdapter( adapter );
    }
    public void displayall(View view) {
       Cursor cursor = sql.query( "student",null,null,null,null ,null,null);
        MyAdapter adapter = new MyAdapter( cursor );
        rv.setAdapter( adapter );
    }
}