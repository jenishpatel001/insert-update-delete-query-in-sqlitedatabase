package com.example.sqlitedatabase;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
    Cursor cursor;

    public MyAdapter(Cursor cursor) {
        this.cursor = cursor;
    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.mydesign,null,false );
        MyHolder h = new MyHolder( view );
        return h;
    }

    @Override
    public void onBindViewHolder( MyAdapter.MyHolder holder, int position) {
        cursor.moveToPosition( position );
        holder.t1.setText( cursor.getString( 0 ) );
        holder.t2.setText( cursor.getString( 1 ) );
        holder.t3.setText( cursor.getString( 2 ) );
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }


    class MyHolder extends RecyclerView.ViewHolder {
        TextView t1, t2, t3;

        public MyHolder(View itemView) {
            super( itemView );
            t1 = itemView.findViewById( R.id.tx1 );
            t2 = itemView.findViewById( R.id.tx2 );
            t3 = itemView.findViewById( R.id.tx3 );
        }
    }
}


