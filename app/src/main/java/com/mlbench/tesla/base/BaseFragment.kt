package com.mlbench.tesla.base

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.mlbench.tesla.views.activities.MainActivity

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB: ViewBinding>() : Fragment() {

    abstract val inflate: Inflate<VB>
    private var _binding: VB? = null
    val binding get() = _binding!!
    private lateinit var mActivity: BaseActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Lingver.getInstance().setLocale(currentActivity(), getSelectedLanguage())
        _binding = inflate.invoke(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(savedInstanceState)
        observeLiveData()

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = when (context) {
            is MainActivity -> context
            else -> context as BaseActivity
        }
    }

    open fun hideLoadingBar() {
        mActivity.hideLoadingBar()
    }

    open fun showLoadingBar() {
        mActivity.showLoadingBar()
    }

    open fun showToast(message: String?) {
        currentActivity().showToast(message)
    }

    open fun currentActivity() = mActivity

    abstract fun observeLiveData()
    abstract fun init(savedInstanceState: Bundle?)


    fun getStringArgument(key: String) = arguments?.getString(key)
    fun getIntArgument(key: String) = arguments?.getInt(key, -1)
    fun getBooleanArgument(key: String) = arguments?.getBoolean(key)
    fun <T> getParcelableArgument(key: String) = arguments?.getParcelable<Parcelable>(key) as T

    override fun onDestroy() {
        super.onDestroy()
//        _binding = null
    }
}