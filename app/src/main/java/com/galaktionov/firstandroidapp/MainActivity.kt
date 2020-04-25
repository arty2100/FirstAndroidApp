package com.galaktionov.firstandroidapp

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.galaktionov.firstandroidapp.dto.Post
import kotlinx.android.synthetic.main.activity_main.*
import java.time.Year
import java.time.YearMonth
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val post = Post(1L, "Netology", "My first post!", 1587796778000, true, 101, 0, 1823)

        date.text =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) lastSeenApi26(post.created) else lastSeen(
                post.created
            )

        mainText.text = post.content
        company.text = post.author
        setValues(post)
        manageLikeButton(post)

    }

    private fun lastSeen(created: Long): String {

        val diff = System.currentTimeMillis() - created
        val minutesBetween = diff / 1000 / 60
        val hoursBetween = minutesBetween / 60
        val daysBetween = hoursBetween / 24
        val monthsBetween = daysBetween / 31
        val yearsBetween = monthsBetween / 12

        return lastSeenResult(
            yearsBetween,
            monthsBetween,
            daysBetween,
            hoursBetween,
            minutesBetween
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun lastSeenApi26(created: Long): String {

        val currentDate =
            Date(System.currentTimeMillis()).toInstant().atZone(ZoneId.systemDefault())
                .toLocalDateTime()
        val lastSeenDate =
            Date(created).toInstant().atZone(ZoneId.systemDefault())
                .toLocalDateTime()
        val yearsBetween = ChronoUnit.YEARS.between(Year.from(lastSeenDate), Year.from(currentDate))
        val monthsBetween =
            ChronoUnit.MONTHS.between(YearMonth.from(lastSeenDate), YearMonth.from(currentDate))
        val daysBetween = ChronoUnit.DAYS.between(lastSeenDate, currentDate)
        val hoursBetween = ChronoUnit.HOURS.between(lastSeenDate, currentDate)
        val minutesBetween = ChronoUnit.MINUTES.between(lastSeenDate, currentDate)

        return lastSeenResult(
            yearsBetween,
            monthsBetween,
            daysBetween,
            hoursBetween,
            minutesBetween
        )

    }

    private fun lastSeenResult(
        yearsBetween: Long,
        monthsBetween: Long,
        daysBetween: Long,
        hoursBetween: Long,
        minutesBetween: Long
    ): String = when {
        yearsBetween > 0 -> resources.getQuantityString(
            R.plurals.plurals_years,
            yearsBetween.toInt(),
            yearsBetween
        )
        monthsBetween > 0 -> resources.getQuantityString(
            R.plurals.plurals_months,
            monthsBetween.toInt(),
            monthsBetween
        )
        daysBetween > 0 -> resources.getQuantityString(
            R.plurals.plurals_days,
            daysBetween.toInt(),
            daysBetween
        )
        hoursBetween > 0 -> resources.getQuantityString(
            R.plurals.plurals_hours,
            hoursBetween.toInt(),
            hoursBetween
        )
        minutesBetween > 0 -> resources.getQuantityString(
            R.plurals.plurals_minutes,
            minutesBetween.toInt(),
            minutesBetween
        )
        else -> getString(R.string.less_than_a_minute)
    }


    private fun manageLikeButton(post: Post) {

        if (post.likedByMe) {
            likeIcon.setImageDrawable(getDrawable(R.drawable.ic_favorite_red_24dp))
            likeText.setTextColor(resources.getColor(R.color.colorAccent))
        } else {
            likeIcon.setImageDrawable(getDrawable(R.drawable.ic_favorite_black_24dp))
            likeText.setTextColor(resources.getColor(android.R.color.black))
        }
    }

    private fun setValues(post: Post) {

        likeText.text = if (post.likes > 0) post.likes.toString() else ""
        sharedText.text = if (post.shares > 0) post.shares.toString() else ""
        сommentText.text = if (post.comments > 0) post.comments.toString() else ""
    }
}
