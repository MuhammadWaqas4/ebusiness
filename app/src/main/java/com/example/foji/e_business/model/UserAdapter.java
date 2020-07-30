package com.example.foji.e_business.model;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.foji.e_business.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {
    private Context mcontext;
    private List<user> mlist;
    String no;

    public UserAdapter(Context mcontext, List<user> mlist) {
        this.mcontext = mcontext;
        this.mlist = mlist;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootview = LayoutInflater.from(mcontext).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(rootview);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        user u = mlist.get(position);
        holder.textview.setText(u.getName());
        holder.cell.setText(u.getCell());
        holder.city.setText(u.getCity());
       // no = holder.cell.getText().toString();
    }

    @Override
    public int getItemCount() {
        return mlist.size() ;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textview, cell, city;

        public MyViewHolder(View itemView) {
            super(itemView);
            textview = itemView.findViewById(R.id.nameview);
            cell= itemView.findViewById(R.id.cellview);
            city = itemView.findViewById(R.id.cityview);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    no = cell.getText().toString();
                    Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                    smsIntent.setType("vnd.android-dir/mms-sms");
                    smsIntent.putExtra("address", no);
                    smsIntent.putExtra("sms_body","");
                    mcontext.startActivity(smsIntent);
                   /* Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                    sendIntent.setData(Uri.parse("sms:"));
                    mcontext.startActivity(sendIntent);*/
                }
            });
        }
    }
}
