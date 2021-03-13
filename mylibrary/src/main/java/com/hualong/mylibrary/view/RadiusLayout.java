package com.hualong.mylibrary.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ConvertUtils;
import com.hualong.mylibrary.R;


//自定义圆角Linealayout
public class RadiusLayout extends LinearLayout {
    private Context mContext;
    private GradientDrawable gd;

    public RadiusLayout(Context context) {
        this(context, null);

    }

    public RadiusLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public RadiusLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;



//        View view = ((LayoutInflater) mContext
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(
//                R.layout.view_radius_layout, null);
//
//        view.setLayoutParams(new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.MATCH_PARENT));
//        addView(view);
//
//        LinearLayout ll = (LinearLayout) view.findViewById(R.id.layout_radius);

        TypedArray typeArray = context.obtainStyledAttributes(attrs,
                R.styleable.RadiusLayout);

        int roundRadius = typeArray.getInt(R.styleable.RadiusLayout_radius, 0);
        int TopLeftRadius =  typeArray.getInt(R.styleable.RadiusLayout_radius_top_left, 0);
        int TopRightRadius = typeArray.getInt(R.styleable.RadiusLayout_radius_top_right, 0);
        int BottomLeftRadius = typeArray.getInt(R.styleable.RadiusLayout_radius_bottom_left, 0);
        int BottomRightRadius = typeArray.getInt(R.styleable.RadiusLayout_radius_bottom_right, 0);
        int fillColor;
        try {
            fillColor = Color.parseColor(typeArray.getString(R.styleable.RadiusLayout_backcolor));//内部填充颜色
        }catch (Exception e){
            fillColor = mContext.getResources().getColor(R.color.white);//内部填充颜色
        }

        float strokeWidth = typeArray.getFloat(R.styleable.RadiusLayout_stroke_width, 0);


        gd = new GradientDrawable();//创建drawable


        if(strokeWidth > 0){
            gd.setStroke(ConvertUtils.dp2px(strokeWidth), Color.parseColor(typeArray.getString(R.styleable.RadiusLayout_stroke_color)));
        }


        gd.setColor(fillColor);
        if (roundRadius != 0) {
            gd.setCornerRadius(ConvertUtils.dp2px(roundRadius));
        } else if(TopLeftRadius != 0 ||TopRightRadius != 0 ||BottomLeftRadius != 0 ||BottomRightRadius != 0){
            float topLeft  = ConvertUtils.dp2px(TopLeftRadius);
            float topRight  = ConvertUtils.dp2px(TopRightRadius);
            float bottomLeft  = ConvertUtils.dp2px(BottomLeftRadius);
            float bottomRight  =ConvertUtils.dp2px(BottomRightRadius);
            float[] radii = new float[]{
                    topLeft, topLeft,
                    topRight, topRight,
                    bottomRight, bottomRight,
                    bottomLeft, bottomLeft
            };
            gd.setCornerRadii(radii);
        }



        this.setBackground(gd);
    }

    public void setBackcolor(String color){
        gd.setColor(Color.parseColor(color));
        this.setBackground(gd);
    }

    public GradientDrawable getGradientDrawable(){
        return gd;
    }
}
