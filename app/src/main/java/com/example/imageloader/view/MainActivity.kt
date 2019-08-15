package com.example.imageloader.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.imageloader.R
import com.example.imageloader.ViewModelFactory
import com.example.imageloader.models.Item
import com.example.imageloader.models.ItemState
import com.example.imageloader.startRefreshing
import com.example.imageloader.stopRefreshing
import com.example.imageloader.view.adpters.ItemsListAdapter
import com.example.imageloader.viewmodel.MainViewModel
import dagger.android.AndroidInjection
import jp.wasabeef.recyclerview.animators.FadeInLeftAnimator
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var itemsAdapter:ItemsListAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel:MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_main)
        initViewModel()
        initViews()
    }

    private inline fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

        viewModel.getItemsLiveData().observe(this, Observer {
            updateItemsList(it)
        })
        viewModel.getItemsList()
    }

    private fun updateItemsList(itemsState:ItemState<Item>?){
        itemsState?.let {
            when(it){
                ItemState.LoadingState -> pinterestSwipeRefresh.startRefreshing()
                is ItemState.DataState -> showItems(it.data)
                is ItemState.ErrorState -> showError(it.error)
            }
        }
    }

    private fun showError(error: String) {
        pinterestSwipeRefresh.stopRefreshing()
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    private fun showItems(items: List<Item>) {
        pinterestSwipeRefresh.stopRefreshing()
        itemsAdapter.setItemsList(items)
    }

    private fun initViews(){
        pinterestSwipeRefresh.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorAccent))
        pinterestSwipeRefresh.setOnRefreshListener { viewModel.getItemsList() }
        val fadeInLeftAnimator = SlideInUpAnimator(OvershootInterpolator(1f))
        fadeInLeftAnimator.addDuration = 500
        val viewManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        pinterestRecyclerView.layoutManager = viewManager
        pinterestRecyclerView.addItemDecoration(DividerItemDecoration(pinterestRecyclerView.context, DividerItemDecoration.VERTICAL))
        pinterestRecyclerView.itemAnimator = fadeInLeftAnimator
        pinterestRecyclerView.adapter = itemsAdapter
    }
}
