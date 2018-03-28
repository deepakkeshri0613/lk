package adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.deepak.lk.R;

import java.util.Collections;
import java.util.List;

import datastore.ModuleDetail;

/**
 * Created by dsk on 22-Mar-18.
 */

public class ChatPageAdapter extends RecyclerView.Adapter<ChatPageAdapter.PageViewHolder> {

    Context context;
    boolean addOrRemoveItemClicked=false;
    private LayoutInflater inflater;
    ClickListener clickListener;
    List<ModuleDetail> data= Collections.emptyList();



    public ChatPageAdapter(Context context)
    {
        inflater = LayoutInflater.from(context);
       // this.data=data;
        this.context=context;
    }

    public void setOnClickListener(ClickListener clickListener)
    {
        this.clickListener=clickListener;
    }

    @Override
    public ChatPageAdapter.PageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=inflater.inflate(R.layout.chat_card_view,parent,false);
        PageViewHolder myViewHolder=new PageViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(ChatPageAdapter.PageViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }


    class PageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public PageViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            if(clickListener!=null)
            {
                clickListener.itemClick(view,getAdapterPosition());
            }

        }
    }

    public interface ClickListener{
        void itemClick(View view,int position);
    }


}
