package com.blackjin.behaviorsample.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.blackjin.behaviorsample.R;
import com.google.android.material.appbar.AppBarLayout;

public class BehaviorBlackJin extends CoordinatorLayout.Behavior<TextView> {

    private final Context mContext;

    private final float marginTop;
    private final float marginTopAfter;

    private final float getYMax;

    public BehaviorBlackJin(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

        getYMax = context.getResources().getDimension(R.dimen.appbar_height) - context.getResources().getDimension(R.dimen.toolbar_height);

        marginTop = dpToPx(getYMax / 4);
        marginTopAfter = dpToPx(getYMax / 8);

    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent,
                                   @NonNull TextView child,
                                   @NonNull View dependency) {
        return dependency instanceof AppBarLayout;
    }

    /**
     * dependency 의 View 의 변화가 있을때 이벤트가 들어옵니다.
     */
    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent,
                                          @NonNull TextView child,
                                          @NonNull View dependency) {

        child.setAlpha(getRatioValue(1f, 0f,
                Math.abs(dependency.getY()), getYMax));
        child.setAlpha(getRatioValue(1f, 0f,
                Math.abs(dependency.getY()), getYMax));

        child.setX(dependency.getWidth() / 2 - (child.getWidth() / 2));

        child.setY(getRatioValue(marginTop, marginTopAfter,
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
