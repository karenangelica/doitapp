package com.example.doitapp.presenter

interface BasePresenter<T: BaseView> {
    fun onViewReady(view: T)
    fun onViewDetach()
}