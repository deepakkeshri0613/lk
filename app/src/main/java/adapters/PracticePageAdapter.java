package adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
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
 * Created by dsk on 17-Mar-18.
 */

public class PracticePageAdapter extends RecyclerView.Adapter<PracticePageAdapter.PageViewHolder> {

    Context context;
    boolean addOrRemoveItemClicked=false;
    public ClickListener clickListener;
    List<ModuleDetail> data= Collections.emptyList();

    TypedArray typedArray;
    int[] colors;
    private LayoutInflater inflater;

    private int previousPosition=0;
    public PracticePageAdapter(Context context,List<ModuleDetail> data)
    {
        inflater = LayoutInflater.from(context);
        this.data=data;
        this.context=context;
        typedArray=context.getResources().obtainTypedArray(R.array.colours);
        colors=new int[typedArray.length()];
        for(int i=0;i<typedArray.length();++i)
            colors[i]=typedArray.getColor(i,0);

    }


    public void setOnClickListener(ClickListener clickListener)
    {
        this.clickListener=clickListener;
    }


    @Override
    public PageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.module_card_view,parent,false);
        PageViewHolder myViewHolder=new PageViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(PageViewHolder holder, int position) {

        View view=holder.itemView.findViewById(R.id.title_holder);
        view.setBackgroundColor(colors[position%colors.length]);
        holder.moduleNumber.setText("Module "+(position+1));
        holder.moduleTitle.setText(data.get(position).getModuleName());
        holder.moduleOverView.setText(data.get(position).getOverView());
        holder.dueDate.setText(data.get(position).getDueDate());
        holder.progressBar.setProgress(data.get(position).getProgress());
        holder.progressBar.getProgressDrawable().setColorFilter(
                colors[position%colors.length], PorterDuff.Mode.SRC_IN
        );
        String progressComplete=data.get(position).getProgress()+"%"+" Complete";
        holder.progressComplete.setText(progressComplete);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class PageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView moduleTitle;
        TextView moduleOverView;
        TextView dueDate;
        ProgressBar progressBar;
        TextView progressComplete;
        TextView moduleNumber;
        public PageViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            moduleTitle=itemView.findViewById(R.id.module_title);
            moduleOverView=itemView.findViewById(R.id.module_OverView);
            dueDate=itemView.findViewById(R.id.module_dueDate);
            progressBar=itemView.findViewById(R.id.module_progress);
            progressComplete=itemView.findViewById(R.id.module_progress_complete);
            moduleNumber=itemView.findViewById(R.id.module_number);
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
