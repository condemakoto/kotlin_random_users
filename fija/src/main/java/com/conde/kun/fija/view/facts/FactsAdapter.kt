package com.conde.kun.fija.view.facts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.conde.kun.fija.R
import com.conde.kun.fija.domain.model.Fact
import kotlinx.android.synthetic.main.item_fact.view.*


class FactsAdapter(val facts: List<Fact>, val factListener: FactListener) : RecyclerView.Adapter<FactsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_fact, parent, false), factListener)
    }

    override fun getItemCount(): Int {
        return facts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(facts[position])
    }

    class ViewHolder(view: View, val factListener: FactListener) : RecyclerView.ViewHolder(view) {
        init {
            view.favoriteButton.setOnCheckedChangeListener { compoundButton, b ->
                itemView.tag?.let{
                    val fact = it as Fact
                    fact.favorited = b
                    factListener.onFavoriteFactClicked(fact)
                }
            }
        }

        fun bind(fact: Fact) {
            itemView.upvotesTV.text = fact.upvotes.toString()
            itemView.titleTV.text = fact.text
            itemView.typeTV.text = "Type " + fact.type
            itemView.favoriteButton.isChecked = fact.favorited
            itemView.tag = fact
        }
    }

    interface FactListener {
        fun onFavoriteFactClicked(fact: Fact)
    }
}