package com.sunnyweather.android.ui.place

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sunnyweather.android.MainActivity
import com.sunnyweather.android.R
import com.sunnyweather.android.ui.weather.WeatherActivity

/**
 * @author 郭梦龙
 * @data 8/15/21-11:53 PM
 */
class PlaceFragment:Fragment() {

    val viewModel by lazy { ViewModelProvider(this).get(PlaceViewModel::class.java) }
    private lateinit var adapter: PlaceAdapter
    private var mRootView:View?=null
    private lateinit var recyclerView:RecyclerView
    private lateinit var searchPlaceEdit:EditText
    private lateinit var bgImageView: ImageView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mRootView=inflater.inflate(R.layout.fragment_place,container,false)
        return mRootView
    }
    private fun init(){
        recyclerView= mRootView!!.findViewById(R.id.recyclerView)
        searchPlaceEdit=mRootView!!.findViewById(R.id.searchPlaceEdit)
        bgImageView=mRootView!!.findViewById(R.id.bgImageView)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity is MainActivity && viewModel.isPlaceSaved()){
            val place=viewModel.getSavedPlace()
            val intent=Intent(context,WeatherActivity::class.java).apply {
                putExtra("location_lng",place.location.lng)
                putExtra("location_lat",place.location.lat)
                putExtra("place_name",place.name)
            }
            startActivity(intent)
            activity?.finish()
            return
        }
        init()
        val layoutManager=LinearLayoutManager(activity)
        recyclerView.layoutManager=layoutManager
        adapter= PlaceAdapter(this,viewModel.placeList)
        recyclerView.adapter=adapter
        searchPlaceEdit.addTextChangedListener{editable->
            val content=editable.toString()
            if (content.isNotEmpty()){
                viewModel.searchPlaces(content)
            }else{
                recyclerView.visibility=View.GONE
                bgImageView.visibility=View.VISIBLE
                viewModel.placeList.clear()
                adapter.notifyDataSetChanged()
            }

        }
        viewModel.placeLiveData.observe(viewLifecycleOwner,Observer { result ->
            val places = result.getOrNull()
            if (places != null) {
                recyclerView.visibility=View.VISIBLE
                bgImageView.visibility=View.GONE
                viewModel.placeList.clear()
                viewModel.placeList.addAll(places)
                adapter.notifyDataSetChanged()
            }else{
                Toast.makeText(activity,"未能查询到任何地点",Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })


        

    }

}