package dev.cianjur.ngopi.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import dev.cianjur.ngopi.core.ui.NontonAdapter
import dev.cianjur.ngopi.databinding.FragmentListBinding
import dev.cianjur.ngopi.presentation.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteTvShowsFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavoriteViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val tvShowsAdapter = NontonAdapter {
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, it)
                startActivity(intent)
            }

            viewModel.tvShows.observe(viewLifecycleOwner, { tvShows ->
                binding.progressBar.visibility = View.GONE
                if (tvShows?.isNullOrEmpty() == true) {
                    binding.rvList.visibility = View.GONE
                    binding.viewError.root.visibility = View.VISIBLE
                    binding.viewError.tvError.text = getString(R.string.empty_favorite)
                } else {
                    binding.rvList.visibility = View.VISIBLE
                    binding.viewError.root.visibility = View.GONE
                    tvShowsAdapter.submitList(tvShows)
                }
            })

            with(binding.rvList) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowsAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
