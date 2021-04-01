package com.blackjin.behaviorsample.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.blackjin.behaviorsample.R;
import com.google.android.material.appbar.AppBarLayout;

public class BehaviorTopBall extends CoordinatorLayout.Behavior<ImageView> {

    private final Context mContext;

    private final float iconMarginLeft;
    private final float iconMarginTop;
    private final float iconMarginTopAfter;

    private final float getYMax;

    public BehaviorTopBall(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

        getYMax = context.getResources().getDimension(R.dimen.appbar_height) - context.getResources().getDimension(R.dimen.toolbar_height);

        iconMarginLeft = dpToPx(30);
        iconMarginTop = dpToPx(72);
        iconMarginTopAfter = dpToPx(20);

    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent,
                                   @NonNull ImageView child,
                                   @NonNull View dependency) {
        return dependency instanceof AppBarLayout;
    }

    /**
     * dependency 의 View 의 변화가 있을때 이벤트가 들어옵니다.
     */
    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent,
                                          @NonNull ImageView child,
                                          @NonNull View dependency) {

        // child icon scale [1 , 0.8]
        child.setScaleX(getRatioValue(1f, 0.8f,
                Math.abs(dependency.getY()), getYMax));
        child.setScaleY(getRatioValue(1f, 0.8f,
                Math.abs(dependency.getY()), getYMax));

        // child icon set x [iconMarginLeft , (dependency.getWidth() - child.getWidth())/2]
        child.setX(getRatioValue(iconMarginLeft, (dependency.getWidth() - child.getWidth()) / 2,
                Math.abs(dependency.getY()), getYMax));

        // child icon set y [iconMarginTop, iconMarginTopAfter]
        child.setY(getRatioValue(iconMarginTop, iconMarginTopAfter,
                Math.abs(dependency.getY()), getYMax));

        return false;
    }

    private float dpToPx(float dp) {
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, dm);
    }

    private float getRatioValue(float firstValue, float lastValue, float getY, float getYMax) {
        float temp = -(firstValue - lastValue) * getY / getYMax;
        return firstValue + temp;
    }

}
