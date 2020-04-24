package com.galaktionov.firstandroidapp

import android.os.Bundle
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
        val post = Post(1L, "Netology", "My first post!", 1587699392000, true, 100, 0, 1823)
        date.text = lastSeen(post.created)
        mainText.text = post.content
        company.text = post.author
        setValues(post)

        manageLikeButton(post)

    }

    private fun lastSeen(created: Long): String {
        val instance = Calendar.getInstance()
        val currentDate =
            Date(instance.timeInMillis).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
        val lastSeenDate =
            Date(created).toInstant().atZone(ZoneId.systemDefault())
                .toLocalDateTime()
        val yearsBetween = ChronoUnit.YEARS.between(Year.from(lastSeenDate), Year.from(currentDate))
        val monthsBetween =
            ChronoUnit.MONTHS.between(YearMonth.from(lastSeenDate), YearMonth.from(currentDate))
        val daysBetween = ChronoUnit.DAYS.between(lastSeenDate, currentDate)
        val hoursBetween = ChronoUnit.HOURS.between(lastSeenDate, currentDate)
        val minutesBetween = ChronoUnit.MINUTES.between(lastSeenDate, currentDate)

        return when {
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
        if (post.likes > 0) {
            likeText.text = post.likes.toString()
        } else {
            likeText.text = ""
        }

        if (post.shares > 0) {

            sharedText.text = post.shares.toString()

        } else {
            sharedText.text = ""
        }

        if (post.comments > 0) {

            сommentText.text = post.comments.toString()

        } else {
            сommentText.text = ""
        }
    }
}
