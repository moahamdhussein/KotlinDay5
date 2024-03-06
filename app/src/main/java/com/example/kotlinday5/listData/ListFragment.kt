package com.example.kotlinday5.listData

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.kotlinday5.R
import com.example.kotlinday5.database.ProductDatabase
import com.example.kotlinday5.main.Communicator
import com.example.kotlinday5.model.Products

import com.example.kotlinday5.network.API
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ListFragment() : Fragment() {
    lateinit var rvItems: RecyclerView
    lateinit var manger: LayoutManager
    lateinit var adapter: Adapter
    lateinit var communicator: Communicator




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        communicator = activity as Communicator
        rvItems = view.findViewById(R.id.rv_languages)
        adapter = Adapter(communicator)
        manger = LinearLayoutManager(context)
        rvItems.adapter = adapter
        rvItems.layoutManager = manger

        val myProductDao = ProductDatabase.getInstance(requireContext()).getProductDao()

        val connectivityManager = requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (connectivityManager?.activeNetworkInfo?.isConnected == true) {
            Snackbar.make(requireView(),"data from Internet",Snackbar.LENGTH_SHORT).show()
            lifecycleScope.launch(Dispatchers.IO) {
                val response = API.retrofitService.getProducts()
                if (response.isSuccessful) {
                    val productList: List<Products> = response.body()?.products ?: listOf()
                    productList.forEach { myProductDao.insertProduct(it) }
                    withContext(Dispatchers.Main) {
                        adapter.submitList(productList)
                    }
                }
            }
        } else {
            Snackbar.make(requireView(),"data from database",Snackbar.LENGTH_SHORT).show()

            lifecycleScope.launch(Dispatchers.IO) {
                val response = myProductDao.getAllProduct()
                withContext(Dispatchers.Main) {
                    adapter.submitList(response)
                }
            }
        }

    }


}