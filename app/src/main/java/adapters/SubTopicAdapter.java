package adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.deepak.lk.AnimationUtil;
import com.deepak.lk.Information;
import com.deepak.lk.R;

import java.util.Collections;
import java.util.List;

import datastore.SubTopicDetail;

/**
 * Created by dsk on 18-Mar-18.
 */

public class SubTopicAdapter extends RecyclerView.Adapter<SubTopicAdapter.PageViewHolder> {

    int previousPosition=0;
    public ClickListener clickListener;
    List<SubTopicDetail> data= Collections.emptyList();

    LayoutInflater inflater;
    Context context;

    public SubTopicAdapter(Context context,List<SubTopicDetail> data)
    {
        inflater = LayoutInflater.from(context);
        this.data=data;
        this.context=context;
    }


    public void setOnClickListener(ClickListener clickListener)
    {
        this.clickListener=clickListener;
    }
    @Override
    public SubTopicAdapter.PageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=inflater.inflate(R.layout.sub_topic_frame,parent,false);
        PageViewHolder myViewHolder=new PageViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(SubTopicAdapter.PageViewHolder holder, int position) {


        holder.subSectionNumber.setText(data.get(position).getChapterNumber()+"");
        holder.subTopic.setText(data.get(position).getChapterTopicTitle());
        holder.pageNumbers.setText(data.get(position).getPageNumbers()+"");
        holder.timeInMin.setText(data.get(position).getTimeInMin()+"");

            if (position > previousPosition) {
                AnimationUtil.animation(holder, true);
            } else {
                AnimationUtil.animation(holder, false);

            }
            previousPosition = position;


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class PageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView subSectionNumber,subTopic,pageNumbers,timeInMin;
        public PageViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            subSectionNumber=itemView.findViewById(R.id.sub_topic_section);
            subTopic=itemView.findViewById(R.id.sub_topic_name);
            pageNumbers=itemView.findViewById(R.id.sub_topic_page_numbers);
            timeInMin=itemView.findViewById(R.id.sub_topic_time);


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
