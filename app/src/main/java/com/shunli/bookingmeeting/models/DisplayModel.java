package com.shunli.bookingmeeting.models;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.shunli.bookingmeeting.BR;

public class DisplayModel extends BaseObservable {

    private String selectDate = "";


    @Bindable
    public String getSelectDate() {
        return selectDate;
    }

    public void setSelectDate(String selectDate) {
        this.selectDate = selectDate;
        notifyPropertyChanged(BR.selectDate);
    }
}
