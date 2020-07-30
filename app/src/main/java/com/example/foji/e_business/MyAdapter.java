package com.example.foji.e_business;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.drm.DrmStore;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    static RecyclerView rv;
    Context context;
    ArrayList<String> items = new ArrayList<>();
    ArrayList<String> urls = new ArrayList<>();

    public MyAdapter(RecyclerView rv, Context context, ArrayList<String> items, ArrayList<String> urls) {
        this.rv = rv;
        this.context = context;
        this.items = items;
        this.urls = urls;
    }

    //public  void update(String name, String url)
    public void update(String name, String url) {
        items.add(name);
        urls.add(url);
        notifyDataSetChanged();
    }
   /* public MyAdapter(RecyclerView rv, Context context, ArrayList<String> items, ArrayList<String> urls) {
        this.rv = rv;
        this.context = context;
        this.items = items;
        this.urls = urls;
    }*/

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemslay, parent, false);
        // view.setOnClickListener(monClickListener);
        return new ViewHolder(view);
    }

    /*private final View.OnClickListener monClickListener = new View.OnClickListener()
        {
        public void onClick(View view){
            int position = rv.getChildLayoutPosition(view);
            Toast.makeText(context, "URL: "+Uri.parse(urls.get(position)), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setType(Intent.ACTION_VIEW);
            //intent.setData(Uri.parse(urls.get(position)));
            intent.setData(Uri.parse("com.google.android.gms.tasks.zzu@41d35428"));
            context.startActivity(intent);
        }
    };*/
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.nameofile.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void update(String url) {
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameofile;
        //Button button;
        String chooser_title;
        public ViewHolder(View itemView) {
            super(itemView);
            nameofile = itemView.findViewById(R.id.epemail);
           /* button = itemView.findViewById(R.id.imgpayID);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context, Payment.class));
                }
            });*/

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = rv.getChildLayoutPosition(view);
                    //String urll = "https://firebasestorage.googleapis.com/v0/b/e-business-9d42e.appspot.com/o/upload%2F1562511616951.pdf?alt=media&token=d158f0ca-11e0-4079-8beb-bbb365123992";
                    Intent intent = new Intent();
                    intent.setType(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(urls.get(position)));
                    context.startActivity(intent);
                   /* String urll = "https://firebasestorage.googleapis.com/v0/b/e-business-9d42e.appspot.com/o/upload%2F1562511616951.pdf?alt=media&token=d158f0ca-11e0-4079-8beb-bbb365123992";
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.parse( "http://docs.google.com/viewer?url=" + urll), "text/html");
                    context.startActivity(intent);
*/
                    /*Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                    browserIntent.setDataAndType(Uri.parse(urls.get(position)),"application/pdf");

                    Intent chooser = Intent.createChooser(browserIntent, getString(R.string.chooser_title));
                    chooser.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // optional

                    context.startActivity(chooser);*/
                   /* try {*/
                        /*int position = rv.getChildLayoutPosition(view);
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urls.get(position)));
                        context.startActivity(browserIntent);*/
//                    Intent intent = new Intent();
//                    intent.setType(Intent.ACTION_VIEW);
//                    intent.setData(Uri.parse(urls.get(position)));
//                    context.startActivity(intent);
                    /*}catch (Exception e) {
                        Toast.makeText(context, "Downloading... ", Toast.LENGTH_SHORT).show();
                    }*/
                }
            });
        }
    }
}
////                    ((context).startActivity(intent);
////                        i.setData(Uri.parse(urls.get(position)));
////                    i.setData(Uri.parse(url));
////                   context.startActivity(i);
////                     Uri webpage = Uri.parse(String.valueOf(urls));
////                    Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
////                    if (intent.resolveActivity(PackageManager != null) ){
////                        startActivity(intent);
//                    }
//                }
//            });
//        }
//    }
//}