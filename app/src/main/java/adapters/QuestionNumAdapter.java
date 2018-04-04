package adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.deepak.lk.Question;
import com.deepak.lk.QuestionFetch;
import com.deepak.lk.R;
import com.libRG.CustomTextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by dsk on 13-Mar-18.
 */

public class QuestionNumAdapter extends RecyclerView.Adapter<QuestionNumAdapter.QuestionNumViewHolder> {


    Context context;
    boolean addOrRemoveItemClicked=false;
    public ClickListener clickListener;
    int numberOfQuestion=0;

    private LayoutInflater inflater;
    public QuestionNumAdapter(Context context,int numberOfQuestion)
    {
        inflater = LayoutInflater.from(context);
        this.context=context;
        this.numberOfQuestion=numberOfQuestion;
    }

    public void setOnClickListener(ClickListener clickListener)
    {
        this.clickListener=clickListener;
    }

    @Override
    public QuestionNumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=inflater.inflate(R.layout.question_number_view,parent,false);
         QuestionNumViewHolder questionNumViewHolder=new QuestionNumViewHolder(view);
        return questionNumViewHolder;
    }

    @Override
    public void onBindViewHolder(QuestionNumViewHolder holder, int position) {
        holder.customTextView.setText(""+(position+1));

    }

    @Override
    public int getItemCount() {
        return numberOfQuestion;
    }

    class QuestionNumViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        CustomTextView customTextView;


        public QuestionNumViewHolder(View itemView) {
            super(itemView);
            customTextView=itemView.findViewById(R.id.question_number_view_id);
            customTextView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            if(clickListener!=null)
            {
                switch (view.getId())
                {
                    case R.id.question_number_view_id: clickListener.ItemClick(view,getAdapterPosition());
                        break;
                    default:
                        break;
                }
            }

        }
    }



    public interface ClickListener{
        public void ItemClick(View view,int position);

    }


    public void removeItem(int position)
    {
       /* if(data.size()!=0) {
            addOrRemoveItemClicked=true;
            data.remove(position);
            notifyItemRemoved(position);
        }
        else {
            Toast.makeText(context,"No item to remove",Toast.LENGTH_SHORT).show();
        }*/
    }
    public void addItem(String title)
    {
        addOrRemoveItemClicked=true;
        //Information item=new Information();
        //data.add(item);
       // notifyItemInserted(data.size());
    }
    public int getAdapterPosition()
    {
        return getAdapterPosition();
    }
}
