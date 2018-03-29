package adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.deepak.lk.Question;
import com.deepak.lk.R;
import com.libRG.CustomTextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by dsk on 15-Mar-18.
 */

public class QuestionMenuAdapter extends RecyclerView.Adapter<QuestionMenuAdapter.PageViewHolder>{



    Context context;
    boolean addOrRemoveItemClicked=false;
    private LayoutInflater inflater;
    ClickListener clickListener;
    List<Question> data= Collections.emptyList();

    public QuestionMenuAdapter(Context context, List<Question> data)
    {
        inflater = LayoutInflater.from(context);

        this.context=context;
        this.data=data;


    }

    public void setOnClickListener(ClickListener clickListener)
    {
        this.clickListener=clickListener;
    }

    @Override
    public QuestionMenuAdapter.PageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.question_menu_items,parent,false);
        PageViewHolder myViewHolder=new PageViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(QuestionMenuAdapter.PageViewHolder holder, int position) {

        holder.customTextView.setText(""+(position+1));


        // here i m getting NPE
        holder.questionText.setText(data.get(position).getQuestion());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class PageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CustomTextView customTextView;
        TextView questionText;

        public PageViewHolder(View itemView) {
            super(itemView);
            customTextView=itemView.findViewById(R.id.question_number_view_id);
            questionText=itemView.findViewById(R.id.question_data_recycler);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            if(clickListener!=null)
            {
                clickListener.menuItemClick(view,getAdapterPosition());
            }

        }
    }

    public interface ClickListener{
        void menuItemClick(View view,int position);
    }


}
