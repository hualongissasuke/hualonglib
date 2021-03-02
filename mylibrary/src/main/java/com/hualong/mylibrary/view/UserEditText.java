package com.hualong.mylibrary.view;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;


public class UserEditText extends AppCompatEditText {
    public UserEditText(Context context) {
        super(context);
        init();
    }

    public UserEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public UserEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        int inputType = getInputType();

        if (inputType == InputType.TYPE_CLASS_TEXT || inputType == 129) {
            this.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (s.length() > 0) {
                        for (int i = 0; i < s.length(); i++) {
                            char c = s.charAt(i);
                            if (c >= 0x4e00 && c <= 0X9fff) { // 根据字节码判断(中文字节码范围)
                                // 如果是中文，则清除输入的字符，否则保留
                                s.delete(i, i + 1);
                            }
                        }
                    }
                }
            });
        }

    }


}
