package com.deepak.lk;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

/**
 * Created by dsk on 28-Feb-18.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    boolean addOrRemoveItemClicked=false;
    public ClickListener clickListener;
    List<Information> data= Collections.emptyList();

    private LayoutInflater inflater;

    private int previousPosition=0;
    public MyAdapter(Context context)
    {
      inflater = LayoutInflater.from(context);
    /*  this.data=data;*/
      this.context=context;
    }


   public void setOnClickListener(ClickListener clickListener)
   {
       this.clickListener=clickListener;
   }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=inflater.inflate(R.layout.custom_row_test,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
      /*  Information currentObject=data.get(position);
        holder.textView.setText(currentObject.title);*/
      holder.textView.setText("deepak");
if(!addOrRemoveItemClicked) {
    if (position > previousPosition) {
        AnimationUtil.animation(holder, true);
    } else {
        AnimationUtil.animation(holder, false);

    }
    previousPosition = position;

}
else {
    addOrRemoveItemClicked=false;
    previousPosition = position;

}

    }

    @Override
    public int getItemCount() {
        return 15;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView textView;

        Button testStartButton;

        public MyViewHolder(final View itemView) {
            super(itemView);
            testStartButton=itemView.findViewById(R.id.test_start_button);
            textView=itemView.findViewById(R.id.paper_name);
           testStartButton.setOnClickListener(this);
           itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            if(clickListener!=null)
            {
                switch (view.getId())
                {
                    case R.id.test_start_button: clickListener.testStartButtonClick(view,getAdapterPosition());
                                                 break;
                    default:clickListener.ItemClick(view,getAdapterPosition());
                        break;
                }
            }
        }
    }
    public interface ClickListener{
        public void ItemClick(View view,int position);
        public void testStartButtonClick(View view,int position);

    }

    public void removeItem(int position)
    {
        if(data.size()!=0) {
            addOrRemoveItemClicked=true;
            data.remove(position);
            notifyItemRemoved(position);
        }
        else {
            Toast.makeText(context,"No item to remove",Toast.LENGTH_SHORT).show();
        }
    }
    public void addItem(String title)
    {
        addOrRemoveItemClicked=true;
        Information item=new Information();
        item.title=title;
        data.add(item);
        notifyItemInserted(data.size());
    }
    public int getPreviousPositon()
    {
        return previousPosition;
    }
}
