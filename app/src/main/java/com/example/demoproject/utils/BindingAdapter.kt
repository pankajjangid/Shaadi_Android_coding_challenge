package com.example.demoproject.utils

import android.graphics.Color
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demoproject.R
import com.example.demoproject.model.network.InviteResponse


@BindingAdapter("bind:loadUrl")
fun bindUrlImage(view: ImageView, url: String?) {

    if (!url.isNullOrEmpty()){

        Glide.with(view)
            .load(url)
            .circleCrop()
            .into(view)

    }

}




@BindingAdapter("bind:toSmallDate")
fun bindToSmallDate(view: TextView, date: String) {
    try {
        view.text = Converters.toSmallDate(date)
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
}




@BindingAdapter("bind:invitationDescription")
fun bindToSmallDate(view: TextView, invitation: InviteResponse.Result) {
    view.text =
        "${invitation.dob.age}, ${invitation.location.city}, ${invitation.location.state}, ${invitation.location.country}"
}

@BindingAdapter("bind:show")
fun bindToSmallDate(view: View, show: Boolean) {
    view.visibility = if (show) View.VISIBLE else View.GONE
}




@RequiresApi(Build.VERSION_CODES.M)
@BindingAdapter("bind:statusMessage")
fun statusMessage(textView: TextView, status: Int) {



         when (status) {
            1 ->{
                textView.setTextColor(textView.context.getColor(R.color.green))
                textView.text =  "Member Accepted"
            }
            2 ->{
                textView.setTextColor(textView.context.getColor(R.color.purple_700))

                textView.text =  "Member Declined"
            }
            else ->textView.text =  ""
        }

}