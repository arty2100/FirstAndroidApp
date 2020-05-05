package com.galaktionov.firstandroidapp.adapters

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.galaktionov.firstandroidapp.R
import com.galaktionov.firstandroidapp.dto.Post
import com.galaktionov.firstandroidapp.extensions.inflate
import kotlinx.android.synthetic.main.post_detail.view.*
import kotlinx.android.synthetic.main.post_detail.view.commentText
import kotlinx.android.synthetic.main.post_detail.view.company
import kotlinx.android.synthetic.main.post_detail.view.likeIcon
import kotlinx.android.synthetic.main.post_detail.view.likeText
import kotlinx.android.synthetic.main.post_detail.view.logoIcon
import kotlinx.android.synthetic.main.post_detail.view.mainText
import kotlinx.android.synthetic.main.post_detail.view.notInterested
import kotlinx.android.synthetic.main.post_detail.view.sharedText
import kotlinx.android.synthetic.main.post_detail_adv.view.*
import java.time.Year
import java.time.YearMonth
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import java.util.*

class PostAdapter(
    val items: MutableList<Post>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_ADV = 1
    private val TYPE_OTHER = 0

    open class GenericAdapter(val view: View) : RecyclerView.ViewHolder(view) {
        val likeIcon = view.likeIcon
        val likeText = view.likeText
        val commentText = view.commentText
        val sharedText = view.sharedText
        val mainText = view.mainText
        val company = view.company
        val companyLogo = view.logoIcon
        val notInterested = view.notInterested
        val webView = view.webView
        val addressView = view.addressView
        val locationIcon = view.locationIcon
        val locationLayout = view.addressView
        val date = view.date
        val seeMoreBotton = view.seeMoreButton

        fun genericBind(
            post: Post,
            position: Int,
            postAdapter: PostAdapter
        ) {
            with(post) {
                mainText.text = content
                company.text = author
                sharedText.text = if (shares > 0) shares.toString() else ""
                commentText.text = if (comments > 0) comments.toString() else ""
                manageLikeButton(this)
                notInterested.setOnClickListener {
                    postAdapter.removeFromList(position)
                }
                likeIcon.setOnClickListener {
                    likedByMe = if (likedByMe) {
                        likes--
                        false
                    } else {
                        likes++
                        true
                    }
                    manageLikeButton(this)
                }
                Glide.with(view.context).load(companyImg ?: R.drawable.ic_android_48dp)
                    .transform(FitCenter(), RoundedCorners(10))
                    .into(companyLogo)
            }
        }

        fun lastSeen(created: Long): String {

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
        fun lastSeenApi26(created: Long): String {

            val currentDate =
                Date(System.currentTimeMillis()).toInstant().atZone(ZoneId.systemDefault())
                    .toLocalDateTime()
            val lastSeenDate =
                Date(created).toInstant().atZone(ZoneId.systemDefault())
                    .toLocalDateTime()
            val yearsBetween =
                ChronoUnit.YEARS.between(Year.from(lastSeenDate), Year.from(currentDate))
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

        fun lastSeenResult(
            yearsBetween: Long,
            monthsBetween: Long,
            daysBetween: Long,
            hoursBetween: Long,
            minutesBetween: Long
        ): String = when {
            yearsBetween > 0 -> view.context.resources.getQuantityString(
                R.plurals.plurals_years,
                yearsBetween.toInt(),
                yearsBetween
            )
            monthsBetween > 0 -> view.context.resources.getQuantityString(
                R.plurals.plurals_months,
                monthsBetween.toInt(),
                monthsBetween
            )
            daysBetween > 0 -> view.context.resources.getQuantityString(
                R.plurals.plurals_days,
                daysBetween.toInt(),
                daysBetween
            )
            hoursBetween > 0 -> view.context.resources.getQuantityString(
                R.plurals.plurals_hours,
                hoursBetween.toInt(),
                hoursBetween
            )
            minutesBetween > 0 -> view.context.resources.getQuantityString(
                R.plurals.plurals_minutes,
                minutesBetween.toInt(),
                minutesBetween
            )
            else -> view.context.getString(R.string.less_than_a_minute)
        }

        fun manageVideo(post: Post) {

            if (Post.POST_TYPE.VIDEO == post.postTpe && post.videoUrl != null) {
                webView.webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                        return false
                    }
                }
                val webSettings: WebSettings = webView.settings
                webSettings.javaScriptEnabled = true
                webSettings.loadWithOverviewMode = true
                webSettings.useWideViewPort = true

                webView.loadUrl(post.videoUrl)
            } else {
                webView.visibility = View.GONE
            }

        }

        fun manageLikeButton(post: Post) {

            if (post.likedByMe) {
                likeIcon.setImageDrawable(view.context.getDrawable(R.drawable.ic_favorite_red_24dp))
                likeText.setTextColor(view.context.resources.getColor(R.color.colorAccent))
            } else {
                likeIcon.setImageDrawable(view.context.getDrawable(R.drawable.ic_favorite_black_24dp))
                likeText.setTextColor(view.context.resources.getColor(android.R.color.black))
            }

            val anim = AnimationUtils.loadAnimation(view.context, R.anim.scale)
            likeIcon.startAnimation(anim);

            likeText.text = if (post.likes > 0) post.likes.toString() else ""
        }

        fun manageLocation(post: Post) {

            if (Post.POST_TYPE.EVENT == post.postTpe && post.location != null && post.address != null) {
                addressView.text = post.address
                locationLayout.setOnClickListener {
                    view.context.startActivity(Intent().apply {
                        action = Intent.ACTION_VIEW
                        data = Uri.parse("geo:${post.location.lat},${post.location.long}")
                    })
                }

            } else {
                locationIcon.visibility = View.GONE
                addressView.visibility = View.GONE
            }
        }
    }

    class AdvHolder(view: View) : GenericAdapter(view) {

        fun bind(
            post: Post,
            position: Int,
            postAdapter: PostAdapter
        ) {
            super.genericBind(post, position, postAdapter)
            seeMoreBotton.setOnClickListener {
                view.context.startActivity(Intent().apply {
                    action = Intent.ACTION_VIEW
                    data = post.advLink
                })
            }
        }
    }


    class Holder(view: View) : GenericAdapter(view) {
        fun bind(
            post: Post,
            position: Int,
            postAdapter: PostAdapter
        ) {
            super.genericBind(post, position, postAdapter)
            with(post) {
                date.text =
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) lastSeenApi26(created) else lastSeen(
                        created
                    )
                manageLocation(this)
                manageVideo(this)

            }
        }


    }

    private fun removeFromList(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, items.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if (TYPE_ADV != viewType) Holder(parent.inflate(R.layout.post_detail, false))
        else AdvHolder(parent.inflate(R.layout.post_detail_adv, false))


    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (TYPE_ADV != getItemViewType(position)) (holder as Holder).bind(
            items[position],
            position,
            this
        )
        else (holder as AdvHolder).bind(items[position], position, this)
    }

    override fun getItemViewType(position: Int): Int {
        return if (Post.POST_TYPE.ADV == items[position].postTpe) TYPE_ADV else TYPE_OTHER

    }
}




