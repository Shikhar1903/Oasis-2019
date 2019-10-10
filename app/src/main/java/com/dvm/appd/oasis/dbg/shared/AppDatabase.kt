package com.dvm.appd.oasis.dbg.shared

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dvm.appd.oasis.dbg.elas.model.retrofit.PlayerRankingResponse
import com.dvm.appd.oasis.dbg.elas.model.room.ElasDao
import com.dvm.appd.oasis.dbg.elas.model.room.OptionData
import com.dvm.appd.oasis.dbg.elas.model.room.QuestionData
import com.dvm.appd.oasis.dbg.events.data.room.EventsDao
import com.dvm.appd.oasis.dbg.events.data.room.dataclasses.*
import com.dvm.appd.oasis.dbg.notification.Notification
import com.dvm.appd.oasis.dbg.notification.NotificationDao
import com.dvm.appd.oasis.dbg.wallet.data.room.WalletDao
import com.dvm.appd.oasis.dbg.wallet.data.room.dataclasses.*

@Database(entities = [StallData::class,StallItemsData::class, MiscEventsData::class,
    OrderItemsData::class, OrderData::class, CartData::class, QuestionData::class,
    OptionData::class,  Notification::class, FavEvents::class, TicketsData::class,
    UserShows::class, TicketsCart::class, PlayerRankingResponse::class, EventData::class,
    CategoryData::class, VenueData::class],version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun walletDao(): WalletDao
    abstract fun eventsDao(): EventsDao
    abstract fun elasDao(): ElasDao
    abstract fun notificationDao(): NotificationDao
}