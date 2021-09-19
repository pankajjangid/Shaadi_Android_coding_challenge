package com.example.demoproject.ui.invite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.recyclerview.widget.RecyclerView
import com.example.demoproject.R
import com.example.demoproject.databinding.RowItemInvitationBinding
import com.example.demoproject.model.network.InviteResponse
import kotlinx.android.synthetic.main.row_item_invitation.view.*

class InviteAdapter :RecyclerView.Adapter<InviteAdapter.InviteViewHolder>() {
    var itemList = listOf<InviteResponse.Result>()
    var onInvitationUpdateListener: OnAdapterClickListener? = null
    companion object {
        const val INVITE_ACCEPTED = 1
        const val INVITE_DECLINED = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InviteViewHolder {

        val binding =  DataBindingUtil.inflate<RowItemInvitationBinding>(
            LayoutInflater.from(parent.context),
            R.layout.row_item_invitation,
            parent,
            false
        )

      return  InviteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InviteViewHolder, position: Int) {
        holder.bind(itemList[position])    }

    override fun getItemCount(): Int {
        return  itemList.size
    }

    inner   class InviteViewHolder(var binding: RowItemInvitationBinding):RecyclerView.ViewHolder(binding.root) {

        fun bind(data: InviteResponse.Result) {

            binding.apply {

                item =data


                executePendingBindings()
                root.btInvitationDecline.setOnClickListener {
                    data.status = INVITE_DECLINED

                    if (onInvitationUpdateListener != null) {
                        onInvitationUpdateListener!!.onClick(data)
                    }
                    notifyItemChanged(adapterPosition)

                }
                root.btInvitationAccept.setOnClickListener {
                    data.status = INVITE_ACCEPTED
                    if (onInvitationUpdateListener != null) {
                        onInvitationUpdateListener!!.onClick(data)

                    }

                    notifyItemChanged(adapterPosition)
                }


            }

        }


    }


    interface OnAdapterClickListener {
        fun onClick(invite: InviteResponse.Result)
    }

}