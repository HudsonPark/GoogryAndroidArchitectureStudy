package com.example.seonoh.seonohapp


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.seonoh.seonohapp.model.CurrentPriceInfoModel
import com.example.seonoh.seonohapp.network.CurrentPriceInfoRequest
import kotlinx.android.synthetic.main.krw_fragment.view.*


class KrwFrgment : BaseFragment(),CurrentPriceInfoRequest.ResultListener{

    lateinit var mView : View
    lateinit var mData : ArrayList<CurrentPriceInfoModel>
    lateinit var mLinearLayoutManager: LinearLayoutManager
    lateinit var mAdapter : CoinAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.krw_fragment, container, false)
        Log.e("onCreateView","onCreateView")
        showLoading()
        requestData()

        return mView
    }

    fun initView( data: ArrayList<CurrentPriceInfoModel>){
        mAdapter = CoinAdapter(data,KRW_TYPE)
        mLinearLayoutManager = LinearLayoutManager(activity,RecyclerView.VERTICAL,false)
        mView.krwRecyclerView.apply {
            layoutManager = mLinearLayoutManager
            adapter = mAdapter
        }
    }

    fun requestData(){
        CurrentPriceInfoRequest().send(this, MainActivity.krwMarketData)
    }

    override fun getCurrentInfoSuccess(result: ArrayList<CurrentPriceInfoModel>) {
        hideLoading()
        mData = result
        initView(mData)
    }

    override fun getCurrentInfoFailed(code: String) {
        hideLoading()
    }


    companion object{
        fun newInstance(): KrwFrgment {
            val fragment = KrwFrgment()
            return fragment
        }
    }
}
