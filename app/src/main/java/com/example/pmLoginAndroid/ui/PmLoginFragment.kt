package com.example.pmLoginAndroid.ui

import android.content.ActivityNotFoundException
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieDrawable
import com.example.pmLoginAndroid.R
import com.example.pmLoginAndroid.client.PmLogin
import com.example.pmLoginAndroid.client.model.LoginError
import com.example.pmLoginAndroid.data.response.LoginSocial
import com.example.pmLoginAndroid.databinding.PmLoginFragmentBinding
import com.example.pmLoginAndroid.ui.adapter.SocialsAdapter
import com.example.pmLoginAndroid.utils.injectViewModel
import com.example.pmLoginAndroid.utils.setVisible
import javax.inject.Inject

@Suppress("TooManyFunctions")
internal class PmLoginFragment : DialogFragment() {

    companion object {
        private const val KEY_REQUEST_PROFILE = "11111111111"

        fun newInstance(): PmLoginFragment = PmLoginFragment()
    }

    private var _binding: PmLoginFragmentBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: PmLoginViewModel

    private lateinit var socialsAdapter: SocialsAdapter
    private var shouldRequestProfile: Boolean = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        PmLogin.PmClient.component.inject(this)
        viewModel = injectViewModel(viewModelFactory)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        shouldRequestProfile = savedInstanceState?.getBoolean(KEY_REQUEST_PROFILE) ?: false
        _binding = PmLoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRv()
        setupObserver()
        if (savedInstanceState == null) viewModel.loadAvailableSocials()

        binding.btnCancel.setOnClickListener {
            dialog?.dismiss()
        }
    }

    override fun onResume() {
        super.onResume()
        if (shouldRequestProfile) {
            viewModel.requestProfile()
            shouldRequestProfile = false
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY_REQUEST_PROFILE, shouldRequestProfile)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onSocialSelect(socials: List<LoginSocial>) {
        binding.apply {
            rvSocialSelect.setVisible(true)
            tvStatus.setVisible(false)
            lawStatus.setVisible(false)
        }
        socialsAdapter.submitList(socials)
    }

    private fun onBrowserLogin(uri: Uri) {
        try {
            val customTabsIntent = CustomTabsIntent.Builder().build()
            customTabsIntent.launchUrl(requireContext(), uri)
            shouldRequestProfile = true
        } catch (exception: ActivityNotFoundException) {
            Log.d("wtf", exception.message.toString())
            viewModel.onBrowserLoginFailed()
        }
    }

    private fun onLoading() {
        binding.apply {
            rvSocialSelect.setVisible(false)
            tvStatus.setVisible(false)
            lawStatus.apply {
                setVisible(true)
                cancelAnimation()
                repeatCount = LottieDrawable.INFINITE
                setAnimation(R.raw.status_loading)
                playAnimation()
            }
        }
    }

    private fun onSuccess() {
        binding.apply {
            rvSocialSelect.setVisible(false)
            tvStatus.setVisible(true)
            tvStatus.text = getString(R.string.login_success)
            btnCancel.setVisible(false)
            lawStatus.apply {
                setVisible(true)
                removeAllUpdateListeners()
                cancelAnimation()
                setAnimation(R.raw.status_complete)
                repeatCount = 0
                playAnimation()
                addAnimatorUpdateListener {
                    if (it.animatedFraction >= 1) dialog?.dismiss()
                }
            }
        }
    }

    private fun onError(error: LoginError) {
        binding.apply {
            rvSocialSelect.setVisible(false)
            tvStatus.setVisible(true)
            tvStatus.text = getString(error.msgId)
            btnCancel.setVisible(false)
            lawStatus.apply {
                setVisible(true)
                cancelAnimation()
                removeAllUpdateListeners()
                setAnimation(R.raw.status_error)
                repeatCount = 0
                playAnimation()
                addAnimatorUpdateListener {
                    if (it.animatedFraction >= 1) dialog?.dismiss()
                }
            }
        }
    }

    private fun setupObserver() {
        viewModel.viewState.observe(viewLifecycleOwner) {
            when (it) {
                is ViewState.SocialSelect -> onSocialSelect(it.data)
                is ViewState.BrowserLogin -> onBrowserLogin(it.uri)
                is ViewState.Loading -> onLoading()
                is ViewState.Success -> onSuccess()
                is ViewState.Error -> onError(it.error)
            }
        }
    }

    private fun setupRv() {
        socialsAdapter = SocialsAdapter {
            viewModel.loadAuthUriData(it)
        }
        binding.rvSocialSelect.apply {
            adapter = socialsAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }
}


