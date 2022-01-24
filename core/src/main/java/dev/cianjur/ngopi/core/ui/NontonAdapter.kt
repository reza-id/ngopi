package dev.cianjur.ngopi.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dev.cianjur.ngopi.core.R
import dev.cianjur.ngopi.core.databinding.ItemNontonBinding
import dev.cianjur.ngopi.core.domain.model.Nonton

class NontonAdapter(var onItemClick: (Nonton) -> Unit) : ListAdapter<Nonton, NontonAdapter.NontonViewHolder>(
    object : DiffUtil.ItemCallback<Nonton>() {
        override fun areItemsTheSame(oldItem: Nonton, newItem: Nonton) = oldItem.itemId == newItem.itemId
        override fun areContentsTheSame(oldItem: Nonton, newItem: Nonton) = oldItem == newItem
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NontonViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_nonton, parent, false))

    override fun onBindViewHolder(holder: NontonViewHolder, position: Int) = holder.bind(getItem(position))

    inner class NontonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemNontonBinding.bind(itemView)
        fun bind(data: Nonton) {
            with(binding) {
                tvYear.text = if (data.releaseDate.length > 4) data.releaseDate.substring(0, 4) else "-"
                tvTitle.text = data.title
                ratingBar.rating = (data.voteAverage / 2).toFloat()
                tvRating.text = data.voteAverage.toString()
                tvPopularity.text = itemView.context.getString(R.string.popularity, data.popularity)
                ivFavorite.visibility = if (data.isFavorite) View.VISIBLE else View.GONE
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w185${data.posterPath}")
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(imgPoster)
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick(getItem(adapterPosition))
            }
        }
    }
}
