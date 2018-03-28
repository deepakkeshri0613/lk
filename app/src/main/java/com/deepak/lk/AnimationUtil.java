package com.deepak.lk;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

/**
 * Created by dsk on 01-Mar-18.
 */

public class AnimationUtil {

    public static void animation(RecyclerView.ViewHolder holder,boolean goesDown)
    {

        YoYo.with(goesDown==true?Techniques.BounceInUp:Techniques.BounceInDown).duration(1600).playOn(holder.itemView);
       /* AnimatorSet animatorSet=new AnimatorSet();
        ObjectAnimator animatorTranslateY=ObjectAnimator.ofFloat(holder.itemView,"translationY",
                goesDown==true?100:-100,0);
        ObjectAnimator animatorTranslateX=ObjectAnimator.ofFloat(holder.itemView,"translationX",
                -25,25,-20,20,-15,15,-5,5,0);
        animatorTranslateX.setDuration(1000);
        animatorTranslateY.setDuration(1000);
        animatorSet.playTogether(animatorTranslateX,animatorTranslateY);
        animatorSet.start();*/
    }
}
