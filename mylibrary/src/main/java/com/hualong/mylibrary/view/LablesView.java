package com.hualong.mylibrary.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.flexbox.JustifyContent;
import com.hualong.mylibrary.R;
import com.hualong.mylibrary.callback.LablesCallback;
import com.hualong.mylibrary.databinding.ViewLabelsBinding;
import com.hualong.mylibrary.util.Console;

import java.util.ArrayList;
import java.util.List;

public class LablesView<T extends Object> extends LinearLayout {
    private ViewLabelsBinding mBinding;
    private FlexboxLayout flexboxLayout;
    private Context mContext;
    private FlexboxLayout.LayoutParams lp;
    public String lablesname;
    /**
     * 控件属性
     */
    private GradientDrawable gd;
    private GradientDrawable sgd;
    //宽度平分多少份
    private float flex;
    private float itemWidth;
    private float itemHeight;
    private float[] itemMargin;
    private Object textColor;
    private Object selectTextColor;
    private Drawable selectBackground;
    private int strokeWidth;
    private boolean isSelect;
    private int layoutId;
    /**
     * 传参
     */
    private int itemCount;
    private List<T> list;
    private List<Integer> selectIndex;
    /**
     * 回调
     */
    private LablesCallback lablesCallback;
    /**
     * 模式选择
     */
    private String selectType;
    public static final String SINGLE = "single";
    public static final String MULTIPLE = "multiple";
    public static final String NONE = "none";


    public void onLablesCallback(LablesCallback lablesCallback) {
        this.lablesCallback = lablesCallback;
    }

    public LablesView(Context context) {
        this(context, null);
    }

    public LablesView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LablesView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        lp = new FlexboxLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ;
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.view_labels, this, false);
        this.addView(mBinding.getRoot());
        flexboxLayout = mBinding.layoutFlex;
        list = new ArrayList<>();
        selectIndex = new ArrayList<>();
        selectType = NONE;

        flexboxLayout.setFlexDirection(FlexDirection.ROW);
        flexboxLayout.setFlexWrap(FlexWrap.WRAP);


        gd = new GradientDrawable();//创建drawable
        sgd = new GradientDrawable();

        try {
            lablesCallback = (LablesCallback) mContext;
        } catch (Exception e) {
        }


        //边框线
        // gd.setStroke(ConvertUtils.dp2px(1), Color.parseColor("#D65454"));
        //填充颜色
        // gd.setColor(Color.parseColor("#000000"));
        //圆角
        // gd.setCornerRadius(ConvertUtils.dp2px(10));
    }



    /**
     * 设置对齐方式
     */
    public void setJustifyContent(JustifyContent justifyContent) {
        flexboxLayout.setJustifyContent(JustifyContent.SPACE_BETWEEN);
    }

    /**
     * 获取FlexboxLayout
     */
    public FlexboxLayout getFlexboxLayout() {
        return flexboxLayout;
    }

    /**
     * 设置itemCount，用于最后一行靠左对齐
     */
    public void setItemCount(int itemCount) {
        if (itemCount > 1)
            this.itemCount = itemCount;
    }

    /** 设置资源文件 */
    public void initLayout(int layoutId,List<T> datas){
        this.layoutId = layoutId;
        setData(datas);
        initData();
    }

    /**
     * 设置选中位置
     */
    public void setSelectIndex(Integer... selectIndex) {
        for (int index : selectIndex) {
            this.selectIndex.add(index);
        }
    }

    /**
     * 设置数据
     */
    public void setData(List<T> list) {
        this.list = list;
    }

    /**
     * 添加数据
     */
    public void addData(List list) {
        this.list.addAll(list);
        flexboxLayout.removeAllViews();
        initData();
    }


    /**
     * 设置数据
     */
    public void initData() {
        flexboxLayout.removeAllViews();

        int resId = this.layoutId;
        Console.logd(lablesname,resId,list,itemCount);
        for (int i = 0; i < list.size(); i++) {
            T t = list.get(i);

            if (this.layoutId != 0) {
                final ViewDataBinding viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), this.layoutId, this, false);
                final View rootView = viewDataBinding.getRoot();
                flexboxLayout.addView(rootView);
                if (lablesCallback != null)
                    lablesCallback.customView(viewDataBinding, t, i);
            } else {
                final TextView tv = new TextView(mContext);

                if (t instanceof String)
                    tv.setText(String.valueOf(t));
                else if (lablesCallback != null)
                    lablesCallback.initView(tv, t, i);

                // tv.setPadding(30, 10, 30, 10);
                if (!ObjectUtils.isEmpty(textColor)) {
                    tv.setTextColor(str2Int(textColor));
                } else {
                    tv.setTextColor(mContext.getResources().getColor(R.color.color_888));
                }
                if (!ObjectUtils.isEmpty(selectIndex) && selectIndex.contains(i) && (!ObjectUtils.isEmpty(selectBackground) || isSelect)) {
                    tv.setBackground(selectBackground == null ? sgd : selectBackground);
                    if (!ObjectUtils.isEmpty(this.selectTextColor))
                        tv.setTextColor(str2Int(this.selectTextColor));
                } else
                    tv.setBackground(gd);


                if (!ObjectUtils.isEmpty(itemMargin)) {
                    lp.topMargin = dp2px(itemMargin[0]);
                    lp.leftMargin = dp2px(itemMargin[1]);
                    lp.bottomMargin = dp2px(itemMargin[2]);
                    lp.rightMargin = dp2px(itemMargin[3]);
                }
                if (itemHeight != 0)
                    lp.height = dp2px(itemHeight);
                if (itemWidth != 0)
                    lp.width = dp2px(itemWidth);
                else if (flex != 0)
                    lp.width = (int) this.flex;
                tv.setGravity(Gravity.CENTER);
                tv.setLayoutParams(lp);

                tv.setTag(i);
                final int position = i;
                if (!selectType.equals(NONE))
                    tv.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (selectIndex != null) {
                                if (selectIndex.contains(position)) {
                                    selectIndex.remove((Integer) position);
                                } else {
                                    if (LablesView.this.selectType.equals(LablesView.this.SINGLE))
                                        selectIndex.clear();
                                    selectIndex.add(position);
                                }
                                initData();
                                if (lablesCallback != null)
                                    lablesCallback.callback(selectIndex);
                            }
                        }
                    });

                flexboxLayout.addView(tv);
            }


        }

        if (flexboxLayout.getJustifyContent() == JustifyContent.SPACE_BETWEEN && itemCount > 0) {

            final int residue = list.size() % itemCount;
            if (residue > 1) {


                if (layoutId == 0) {
                    for (int x = 0; x < itemCount - residue; x++) {
                        View view = new View(mContext);
                        view.setLayoutParams(lp);
                        flexboxLayout.addView(view);
                    }
                    return;
                }

                flexboxLayout.post(new Runnable() {
                    @Override
                    public void run() {
                        View child = flexboxLayout.getChildAt(0);
                        View empty = new View(mContext);
                        lp.width = child.getWidth() * (itemCount - residue);
                        empty.setLayoutParams(lp);
                        flexboxLayout.addView(empty);
                    }
                });

            }
        }
    }

    /**
     * 颜色字符串转int
     */
    private int str2Int(Object obj) {
        if (obj instanceof String) {
            return Color.parseColor(String.valueOf(obj));
        }
        return (int) obj;
    }

    /**
     * 选择点击模式
     */
    public void setSelectType(String selectType) {
        this.selectType = selectType;
    }

    /**
     * 圆角
     */
    public void setCornerRadius(float raidus) {
        gd.setCornerRadius(dp2px(raidus));
        sgd.setCornerRadius(dp2px(raidus));
    }

    /**
     * 重新设置数据
     */
    public void setAttribute(float width, float height, float[] margin, Object textColor,
                             Drawable selectBackground, Object selectTextColor, Object selectBackgroundColor,
                             Object selectStrokeColor, float flex, int layoutId) {
        this.itemWidth = width;
        this.itemHeight = height;
        this.itemMargin = margin;
        this.textColor = textColor;
        this.selectBackground = selectBackground;
        this.selectTextColor = selectTextColor;
        if(layoutId != 0)
            this.layoutId = layoutId;
        if (flex > 0) {
            this.flex = (ScreenUtils.getScreenWidth() - this.getPaddingLeft() - this.getPaddingRight()) / flex;
            if (!ObjectUtils.isEmpty(margin))
                this.flex = this.flex - dp2px(margin[1]) - dp2px(margin[3]);
        }
        if (selectBackgroundColor != null) {
            sgd.setColor(str2Int(selectBackgroundColor));
            sgd.setStroke(dp2px(this.strokeWidth), str2Int(selectStrokeColor));
            this.isSelect = true;
        }
        initData();
    }

    /**
     * 填充颜色
     */
    public void setItemBackgroundColor(Object color) {
        gd.setColor(str2Int(color));
        sgd.setColor(str2Int(color));
    }

    /**
     * 填充被选中时的颜色颜色
     */
    public void setItemSelectColor(Object itemSelectBackground, Object itemSelectStokeCoor, Object selectTextColor) {
        sgd.setColor(str2Int(itemSelectBackground));
        sgd.setStroke(dp2px(this.strokeWidth), str2Int(itemSelectStokeCoor));
        this.selectTextColor = selectTextColor;
    }

    /**
     * dp转px
     */
    public int dp2px(float width) {
        return ConvertUtils.dp2px(width);
    }

    /**
     * 设置边框线
     */
    public void setStroke(int width, Object color) {
        gd.setStroke(dp2px(width), str2Int(color));
        sgd.setStroke(dp2px(width), str2Int(color));
        this.strokeWidth = width;
    }
}
