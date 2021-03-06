package com.dvm.appd.oasis.dbg.wallet.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dvm.appd.oasis.dbg.OASISApp
import com.dvm.appd.oasis.dbg.di.wallet.WalletModule
import com.dvm.appd.oasis.dbg.wallet.data.repo.WalletRepository
import javax.inject.Inject

class OrdersViewModelFactory: ViewModelProvider.Factory {

    @Inject
    lateinit var walletRepository: WalletRepository

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        OASISApp.appComponent.newWalletComponent(WalletModule()).injectOrders(this)
        @Suppress("UNCHECKED_CAST")
        return OrdersViewModel(walletRepository) as T
    }
}