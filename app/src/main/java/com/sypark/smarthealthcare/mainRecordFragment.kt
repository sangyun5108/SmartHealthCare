package com.sypark.smarthealthcare

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class mainRecordFragment : Fragment() {

    private val dataList = mutableListOf<recordDataModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main_record, container, false)

        dataList.add(recordDataModel("2021","11","03","Pull-Down"))
        dataList.add(recordDataModel("2021","11","04","Bench-Press"))
        dataList.add(recordDataModel("2021","11","03","Shoulder-Press"))
        dataList.add(recordDataModel("2021","11","03","Arm-Curl"))
        dataList.add(recordDataModel("2021","11","03","Pull-Down"))
        dataList.add(recordDataModel("2021","11","04","Bench-Press"))
        dataList.add(recordDataModel("2021","11","03","Shoulder-Press"))
        dataList.add(recordDataModel("2021","11","03","Arm-Curl"))
        dataList.add(recordDataModel("2021","11","03","Pull-Down"))
        dataList.add(recordDataModel("2021","11","04","Bench-Press"))
        dataList.add(recordDataModel("2021","11","03","Shoulder-Press"))
        dataList.add(recordDataModel("2021","11","03","Arm-Curl"))
        dataList.add(recordDataModel("2021","11","03","Pull-Down"))
        dataList.add(recordDataModel("2021","11","04","Bench-Press"))
        dataList.add(recordDataModel("2021","11","03","Shoulder-Press"))
        dataList.add(recordDataModel("2021","11","03","Arm-Curl"))

        Log.d("list",dataList.toString())

        val rvView = view.findViewById<RecyclerView>(R.id.record_recyclerView)

        val rvAdapter = recordRvAdapter(dataList)

        rvView.adapter = rvAdapter
        rvView.layoutManager = LinearLayoutManager(this.context)

        return view
    }

}