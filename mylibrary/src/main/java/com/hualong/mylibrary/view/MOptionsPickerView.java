package com.hualong.mylibrary.view;

import android.app.Dialog;

import com.bigkoo.pickerview.configure.PickerOptions;
import com.bigkoo.pickerview.view.OptionsPickerView;

public class MOptionsPickerView extends OptionsPickerView {
    public Dialog dialog;

    public MOptionsPickerView(PickerOptions pickerOptions) {
        super(pickerOptions);
    }

    @Override
    public void dismiss() {
        if(dialog != null){
            if(dialog.isShowing())
                dialog.dismiss();
            dialog = null;
        }
        super.dismiss();
    }
}
