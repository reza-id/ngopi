package dev.cianjur.ngopi.presentation.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dev.cianjur.ngopi.R
import dev.cianjur.ngopi.core.domain.model.Nonton
import dev.cianjur.ngopi.databinding.ActivityDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val detailItem = intent.getParcelableExtra<Nonton>(EXTRA_DATA)
        showDetailItem(detailItem)
    }

    private fun showDetailItem(item: Nonton?) {
        item?.let {
            title = it.title
            binding.tvTitle.text = it.title
            binding.tvYear.text = it.releaseDate.substring(0, 4)
            binding.ratingBar.rating = (it.voteAverage / 2).toFloat()
            binding.tvRating.text = it.voteAverage.toString()
            binding.tvPopularity.text = getString(R.string.popularity, it.popularity)
            binding.tvOverview.text = it.overview
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500${it.backdropPath}")
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(binding.imgBackdrop)
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w185${it.posterPath}")
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(binding.imgPoster)

            var statusFavorite = it.isFavorite
            setStatusFavorite(statusFavorite)
            binding.btnFavorite.setOnClickListener { _ ->
                statusFavorite = !statusFavorite
                viewModel.setFavorite(it, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.btnFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_star_filled))
        } else {
            binding.btnFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_star_outline))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
