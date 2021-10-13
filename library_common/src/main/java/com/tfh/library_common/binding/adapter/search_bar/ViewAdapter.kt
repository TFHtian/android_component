package com.tfh.library_common.binding.adapter.search_bar

import androidx.databinding.BindingAdapter
import com.mancj.materialsearchbar.MaterialSearchBar
import com.mancj.materialsearchbar.adapter.SuggestionsAdapter
import com.tfh.lib_base.binding.command.BindingCommand

object ViewAdapter {

    @JvmStatic
    @BindingAdapter(
        value = ["onNavigationCommand", "onSearchConfirmCommand", "onSearchStateCommand", "onSearchIconCommand"],
        requireAll = false
    )
    fun onSearchActionCommand(
        searchBar: MaterialSearchBar,
        command: BindingCommand<Void?>?,
        bindingCommand: BindingCommand<String?>?,
        booleanCommand: BindingCommand<Boolean?>?,
        iconCommand: BindingCommand<Void?>?
    ) {
        searchBar.setOnSearchActionListener(object : MaterialSearchBar.OnSearchActionListener {
            override fun onSearchStateChanged(enabled: Boolean) {
                booleanCommand?.execute(enabled)
            }

            override fun onSearchConfirmed(text: CharSequence) {
                bindingCommand?.execute(text.toString().trim { it <= ' ' })
            }

            override fun onButtonClicked(buttonCode: Int) {
                if (MaterialSearchBar.BUTTON_NAVIGATION == buttonCode && command != null) {
                    command.execute()
                }
                if (MaterialSearchBar.BUTTON_SPEECH == buttonCode && iconCommand != null) {
                    iconCommand.execute()
                }
            }
        })
    }

    @JvmStatic
    @BindingAdapter("onSearchItemClick")
    fun onSearchItemClick(
        searchBar: MaterialSearchBar,
        listener: SuggestionsAdapter.OnItemViewClickListener?
    ) {
        searchBar.setSuggestionsClickListener(listener)
    }

    @JvmStatic
    @BindingAdapter("onSearchTextChangeCommand")
    fun onSearchTextChangeCommand(searchBar: MaterialSearchBar, command: BindingCommand<String?>?) {
        command?.execute(searchBar.searchEditText.text.toString())
    }

    @JvmStatic
    @BindingAdapter("searchPlaceHolder")
    fun setSearchPlaceHolder(bar: MaterialSearchBar, text: String?) {
        bar.setPlaceHolder(text)
    }

}