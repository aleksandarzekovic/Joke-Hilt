package me.aleksandarzekovic.joke_hilt.utils.recyclerview

import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import me.aleksandarzekovic.joke_hilt.R

@BindingAdapter(value = ["tools:data", "tools:itemList", "tools:itemListener"], requireAll = true)
fun <T> setAdapter(
    recyclerView: RecyclerView,
    data: MutableLiveData<ArrayList<T>>,
    @LayoutRes listItem: Int = R.layout.item_category,
    itemListener: Any
) {
    if (recyclerView.adapter == null) {
        recyclerView.adapter =
            RecyclerViewAdapter(
                listItem,
                data.value ?: ArrayList(),
                itemListener
            )
    } else {
        if (recyclerView.adapter is RecyclerViewAdapter<*>) {
            val items = data.value ?: ArrayList()
            (recyclerView.adapter as RecyclerViewAdapter<T>).updateData(items)
        }
    }
}