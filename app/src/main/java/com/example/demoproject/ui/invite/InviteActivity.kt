package com.example.demoproject.ui.invite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.demoproject.R
import com.example.demoproject.databinding.ActivityInviteBinding
import com.example.demoproject.model.network.InviteResponse
import com.example.demoproject.networking.NetworkUtils
import com.example.demoproject.utils.App
import com.example.demoproject.utils.Extensions.toast
import com.example.demoproject.utils.ProgressDialogUtil
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


class InviteActivity : AppCompatActivity(), KodeinAware, InviteListener,
    InviteAdapter.OnAdapterClickListener {

    lateinit var mBinding: ActivityInviteBinding
    lateinit var mViewModel: InviteViewModel
    val factory: InviteViewModelFactory by instance()
    private val adapter = InviteAdapter()

    lateinit var mActivity: InviteActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_invite)
        mBinding.lifecycleOwner = this
        mViewModel = ViewModelProvider(this, factory).get(InviteViewModel::class.java)
        mBinding.mViewModel = mViewModel
        mViewModel.listener = this
        mActivity = this

        adapter.onInvitationUpdateListener = this

        mBinding.adapter = adapter


        if (NetworkUtils.isNetworkConnected(this))
            mViewModel.requestInvites("10")
        else
            mViewModel.requestInvitesLocal()



        mViewModel.inviteList.observe(this, Observer {


            adapter.itemList = it
            adapter.notifyDataSetChanged()
        })


    }


    override val kodein: Kodein by kodein()


    override fun onStarted() {
        ProgressDialogUtil.showProgress(this)
    }

    override fun onSuccess(responseData: Any?) {
        ProgressDialogUtil.hideProgress()


        val inviteResponse = responseData as InviteResponse

        mViewModel.inviteList.value = inviteResponse.results


    }

    override fun onFailure(message: String?) {
        ProgressDialogUtil.hideProgress()
        message?.let {
            toast(message)
        }
    }

    override fun onClick(invite: InviteResponse.Result) {
        mViewModel.updateInvite(invite)


    }

}



