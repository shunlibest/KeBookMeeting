package com.shunli.bookingmeeting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.haibin.calendarview.Calendar
import com.haibin.calendarview.CalendarView
import com.shunli.bookingmeeting.utils.StringUtil
import com.shunli.bookingmeeting.utils.ToastUtil
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import com.shunli.bookingmeeting.databinding.ActivityMainBinding
import androidx.databinding.ObservableField
import com.shunli.bookingmeeting.models.DisplayModel


class MainActivity : AppCompatActivity() {
    private val displayInfo = DisplayModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val binding =
            setContentView<ActivityMainBinding>(this@MainActivity, R.layout.activity_main)
        binding.display = displayInfo

        initCalendarView()

    }

    private fun initCalendarView() {
        findViewById<CalendarView>(R.id.calendarView)
            .setOnCalendarSelectListener(object : CalendarView.OnCalendarSelectListener {
                override fun onCalendarOutOfRange(calendar: Calendar?) {

                }

                override fun onCalendarSelect(calendar: Calendar?, isClick: Boolean) {
                    if (calendar == null) {
                        return
                    }
                    displayInfo.selectDate = "${calendar.year}-${calendar.month}-${calendar.day}"
                    ToastUtil.showShortToast(displayInfo.selectDate)
                }
            })
    }

    val httpLoginHelper: HttpLoginHelper = HttpLoginHelper()

    fun bookingMeeting(view: View) {
        Thread() {
            val cookies = httpLoginHelper.loginCookies

            if (StringUtil.isBlanks(cookies) || StringUtil.isBlanks(displayInfo.selectDate)) {
                ToastUtil.showLongToast("为空")
                return@Thread
            }
            BookMeetingHelper.bookingMeeting(cookies, displayInfo.selectDate)

        }.start()
    }
}