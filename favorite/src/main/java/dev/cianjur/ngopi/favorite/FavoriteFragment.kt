package dev.cianjur.ngopi.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dev.cianjur.ngopi.R
import dev.cianjur.ngopi.favorite.databinding.FragmentFavoriteBinding
import dev.cianjur.ngopi.favorite.di.favoriteModule
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        loadKoinModules(favoriteModule)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.favoritePager.adapter = FavoritePagerAdapter(this)

        TabLayoutMediator(binding.favoriteTab, binding.favoritePager) { tab, position ->
            tab.text = getText(if (position == 0) R.string.title_movies else R.string.title_tv_shows)
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unloadKoinModules(favoriteModule)
        _binding = null
    }

    inner class FavoritePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
        override fun getItemCount() = 2
        override fun createFragment(position: Int) = if (position == 0) FavoriteMoviesFragment() else FavoriteTvShowsFragment()
    }
}
