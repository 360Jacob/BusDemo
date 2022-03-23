package com.jacob.lib_base.utils

import java.lang.AssertionError
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object TimeUtils {


    val DEFAULT_DATE_FORMAT = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val DATE_FORMAT_DATE = SimpleDateFormat("yyyy-MM-dd")


    private fun TimeUtils() {
        throw AssertionError()
    }

    /**
     * long time to string
     *
     * @param timeInMillis
     * @param dateFormat
     * @return
     */
    fun getTime(timeInMillis: Long, dateFormat: SimpleDateFormat): String {
        return dateFormat.format(Date(timeInMillis))
    }

    /**
     * long time to string, format is [.DEFAULT_DATE_FORMAT]
     *
     * @param timeInMillis
     * @return
     */
    fun getTime(timeInMillis: Long): String? {
        return getTime(timeInMillis, DEFAULT_DATE_FORMAT)
    }

    /**
     * get current time in milliseconds
     *
     * @return
     */
    fun getCurrentTimeInLong(): Long {
        return System.currentTimeMillis()
    }

    /**
     * get current time in milliseconds, format is [.DEFAULT_DATE_FORMAT]
     *
     * @return
     */
    fun getCurrentTimeInString(): String? {
        return getTime(getCurrentTimeInLong())
    }

    /**
     * get current time in milliseconds
     *
     * @return
     */
    fun getCurrentTimeInString(dateFormat: SimpleDateFormat): String? {
        return getTime(getCurrentTimeInLong(), dateFormat)
    }


    /**
     * 获取两个日期之间的间隔天数
     * 4.1到4.2,结果应为2
     *
     * @return
     */
    fun getDayInterval(startDate: Date?, endDate: Date?): Int {
        val fromCalendar = Calendar.getInstance()
        fromCalendar.time = startDate
        fromCalendar[Calendar.HOUR_OF_DAY] = 0
        fromCalendar[Calendar.MINUTE] = 0
        fromCalendar[Calendar.SECOND] = 0
        fromCalendar[Calendar.MILLISECOND] = 0
        val toCalendar = Calendar.getInstance()
        toCalendar.time = endDate
        toCalendar[Calendar.HOUR_OF_DAY] = 0
        toCalendar[Calendar.MINUTE] = 0
        toCalendar[Calendar.SECOND] = 0
        toCalendar[Calendar.MILLISECOND] = 0
        return ((toCalendar.time.time - fromCalendar.time.time) / (1000 * 60 * 60 * 24)).toInt() + 1
    }

    /**
     * 获取时间间隔
     *
     * @param endDate   结束时间
     * @param startDate 开始时间
     * @return X天X小时X分钟
     */
    fun getTimeInterval(endDate: Date, startDate: Date): String? {
        return getTimeInterval(endDate.time, startDate.time)
    }


    /**
     * 获取时间间隔
     *
     * @param endTime   结束时间
     * @param startTime 开始时间
     * @return X天X小时X分钟
     */
    fun getTimeInterval(endTime: Long, startTime: Long): String? {
        val nd = (1000 * 24 * 60 * 60).toLong()
        val nh = (1000 * 60 * 60).toLong()
        val nm = (1000 * 60).toLong()
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        val diff = endTime - startTime
        // 计算差多少天
        val day = diff / nd
        // 计算差多少小时
        val hour = diff % nd / nh
        // 计算差多少分钟
        val min = diff % nd % nh / nm
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day.toString() + "天" + hour + "小时" + min + "分钟"
    }

    /**
     * 获取时间间隔(分钟)
     *
     * @param endTime   结束时间
     * @param startTime 开始时间
     * @return 分钟数
     */
    fun getTimeIntervalInMinute(endTime: Long, startTime: Long): Long {
        val nm = (1000 * 60).toLong()
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        val diff = endTime - startTime
        // 计算差多少分钟
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return diff / nm
    }

    /**
     * 获取当前日期0点0分
     *
     * @return 毫秒数
     */
    fun getCurrentDayZeroHourInLong(): Long {
        val current = System.currentTimeMillis() //当前时间毫秒数
        return current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().rawOffset //今天零点零分零秒的毫秒数
    }

    /**
     * 获取某个Date的00:00:00
     *
     * @param date
     * @return
     */
    fun getZeroHourInLong(date: Date?): Long {
        if (date == null) {
            return 0
        }
        val cal = Calendar.getInstance()
        cal.time = date
        val hour = cal[Calendar.HOUR_OF_DAY]
        val minute = cal[Calendar.MINUTE]
        val second = cal[Calendar.SECOND]
        //时分秒（毫秒数）
        val millisecond = (hour * 60 * 60 * 1000 + minute * 60 * 1000 + second * 1000).toLong()
        //凌晨00:00:00
        cal.timeInMillis = cal.timeInMillis - millisecond
        return cal.time.time
    }

    /**
     * 获取某个Date的23:59:59
     *
     * @param date
     * @return
     */
    fun getEndHourInLong(date: Date?): Long {
        if (date == null) {
            return 0
        }
        val cal = Calendar.getInstance()
        cal.time = date
        val hour = cal[Calendar.HOUR_OF_DAY]
        val minute = cal[Calendar.MINUTE]
        val second = cal[Calendar.SECOND]
        //时分秒（毫秒数）
        val millisecond = (hour * 60 * 60 * 1000 + minute * 60 * 1000 + second * 1000).toLong()
        //凌晨00:00:00
        cal.timeInMillis = cal.timeInMillis - millisecond

        //凌晨23:59:59
        cal.timeInMillis = cal.timeInMillis + 23 * 60 * 60 * 1000 + 59 * 60 * 1000 + 59 * 1000
        return cal.time.time
    }

    /**
     * 把分钟转为hh:mm
     *
     * @param minute
     * @return
     */
    fun getTimeHourMinuteFromMinute(minute: Int): String? {
        val h = minute / 60
        val m = minute % 60
        var result = "$h:"
        if (m < 10) {
            result += "0$m"
        } else {
            result += m
        }
        return result
    }

    /**
     * 把分钟转为hh:mm
     *
     * @param minute
     * @return
     */
    fun getTimeHourMinuteTextFromMinute(minute: Int): String? {
        val h = minute / 60
        val m = minute % 60
        var result = h.toString() + "小时"
        if (m < 10) {
            result += "0$m"
        } else {
            result += m
        }
        result += "分钟"
        return result
    }

    /**
     * 判断是否是当天
     * @param time
     * @return
     */
    fun isToday(time: Long): Boolean {
        val inputJudgeDate = Date(time)
        var flag = false
        //获取当前系统时间
        val longDate = System.currentTimeMillis()
        val nowDate = Date(longDate)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val format = dateFormat.format(nowDate)
        val subDate = format.substring(0, 10)
        //定义每天的24h时间范围
        val beginTime = "$subDate 00:00:00"
        val endTime = "$subDate 23:59:59"
        var paseBeginTime: Date? = null
        var paseEndTime: Date? = null
        try {
            paseBeginTime = dateFormat.parse(beginTime)
            paseEndTime = dateFormat.parse(endTime)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        if (inputJudgeDate.after(paseBeginTime) && inputJudgeDate.before(paseEndTime)) {
            flag = true
        }
        return flag
    }

    fun getTodayTime(time: Long, replaceStr: String?): String? {
        val temp = getTime(time, SimpleDateFormat("MM月dd日 HH:mm"))
        return if (isToday(time)) {
            val tempStr = temp.substring(0, 7)
            temp.replace(tempStr, replaceStr!!)
        } else {
            temp
        }
    }

    /**
     * 耗时转化成xx小时xx分钟
     * @param time 秒
     * @return
     */
    fun timeToTimeString(time: Long): String? {
        val tempH = time / 3600
        val tempM = (time - tempH * 3600) / 60
        var strTemp = ""
        strTemp = if (tempH == 0L) {
            tempM.toString() + "分钟"
        } else if (tempH > 0 && tempM == 0L) {
            tempH.toString() + "小时"
        } else {
            tempH.toString() + "小时" + tempM + "分钟"
        }
        return strTemp
    }

    fun dateToTimestamp(time: String?, dateFormat: SimpleDateFormat): Long {
        var date: Date? = null
        try {
            date = dateFormat.parse(time)
            return date.time
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return 0
    }

    /**
     * 返回当前数据的年月数据时间戳返回
     * @param time
     * @return
     */
    fun getDaysOfMonth(time: Long): Long {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = time
        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH]
        val temp = Calendar.getInstance()
        temp[year, month, 1, 0, 0] = 0
        return temp.timeInMillis / 1000 * 1000
    }
}