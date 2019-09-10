package com.dvm.appd.bosm.dbg.profile.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.dvm.appd.bosm.dbg.R
import com.dvm.appd.bosm.dbg.wallet.data.room.dataclasses.ModifiedTicketsData
import com.dvm.appd.bosm.dbg.wallet.data.room.dataclasses.TicketsCart
import com.dvm.appd.bosm.dbg.wallet.data.room.dataclasses.TicketsData
import kotlinx.android.synthetic.main.adapter_tickets_child.view.*

class TicketsChildAdapter(private val listener: TicketCartActions): RecyclerView.Adapter<TicketsChildAdapter.TicketsChildViewHolder>(){

    var tickets: List<ModifiedTicketsData> = emptyList()

    interface TicketCartActions{
        fun insertTicketCart(ticket: TicketsCart)
        fun updateTicketCart(qunatity: Int, id: Int)
        fun deleteTicketCart(id: Int)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TicketsChildViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.adapter_tickets_child, p0, false)
        return TicketsChildViewHolder(view)
    }

    override fun getItemCount(): Int = tickets.size

    override fun onBindViewHolder(p0: TicketsChildViewHolder, p1: Int) {

        p0.name.text = tickets[p1].name
        p0.price.text = tickets[p1].price.toString()
        p0.shows.text = if (tickets[p1].shows != null) tickets[p1].shows else ""

        if (tickets[p1].quantity > 0){
            p0.add.isVisible = false
            p0.minus.isVisible = true
            p0.plus.isVisible = true
            p0.quantity.isVisible = true

            p0.quantity.text = tickets[p1].quantity.toString()

            p0.plus.setOnClickListener {
                listener.updateTicketCart(tickets[p1].quantity + 1, tickets[p1].cartId)
            }

            p0.minus.setOnClickListener {

                if (tickets[p1].quantity > 1){
                    listener.updateTicketCart(tickets[p1].quantity - 1, tickets[p1].cartId)
                }
                else{
                    listener.deleteTicketCart(tickets[p1].cartId)
                }
            }
        }
        else{
            p0.add.isVisible = true
            p0.minus.isVisible = false
            p0.plus.isVisible = false
            p0.quantity.isVisible = false

            p0.add.setOnClickListener {
                listener.insertTicketCart(TicketsCart(tickets[p1].ticketId, 1, tickets[p1].type, 0))
            }
        }

    }

    inner class TicketsChildViewHolder(view: View): RecyclerView.ViewHolder(view){

        val name: TextView = view.name
        val shows: TextView = view.shows
        val price: TextView = view.price
        val add: Button = view.addBtn
        val quantity: TextView = view.quantity
        val plus: Button = view.plus
        val minus: Button = view.minus
    }
}