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
        val post = Post(1L, "Netology", "My first post!", 1585052512000, true, 100, 34, 1823)
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

//        when {
//            yearsBetween > 0 -> {
//
//                return getResponse(yearsBetween, TYPE.YEAR)
//            }
//            monthsBetween > 0 -> {
//                return getResponse(monthsBetween, TYPE.MONTH)
//            }
//            daysBetween > 0 -> {
//                return getResponse(daysBetween, TYPE.DAY)
//            }
//            hoursBetween > 0 -> {
//                return getResponse(hoursBetween, TYPE.HOUR)
//            }
//            minutesBetween > 0 -> {
//                return getResponse(minutesBetween, TYPE.MINUTE)
//            }
//            else -> {
//                return "меньше минуты назад"
//            }
//        }
        return ""
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
            if (post.likes > 999) {
                likeText.text = "999+"
            } else {
                likeText.text = post.likes.toString()
            }
        } else {
            likeText.text = ""
        }

        if (post.shares > 0) {
            if (post.shares > 999) {
                sharedText.text = "999+"
            } else {
                sharedText.text = post.shares.toString()
            }
        } else {
            sharedText.text = ""
        }

        if (post.comments > 0) {
            if (post.comments > 999) {
                сommentText.text = "999+"
            } else {
                сommentText.text = post.comments.toString()
            }
        } else {
            сommentText.text = ""
        }
    }
}
