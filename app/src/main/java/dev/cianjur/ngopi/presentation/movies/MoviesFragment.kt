package dev.cianjur.ngopi.presentation.movies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import dev.cianjur.ngopi.R
import dev.cianjur.ngopi.core.data.Resource
import dev.cianjur.ngopi.core.ui.NontonAdapter
import dev.cianjur.ngopi.databinding.FragmentListBinding
import dev.cianjur.ngopi.presentation.detail.DetailActivity
import dev.cianjur.ngopi.presentation.detail.DetailActivity.Companion.EXTRA_DATA
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MoviesViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val moviesAdapter = NontonAdapter {
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(EXTRA_DATA, it)
                startActivity(intent)
            }

            viewModel.movies.observe(viewLifecycleOwner, { movies ->
                if (movies != null) {
                    when (movies) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            moviesAdapter.submitList(movies.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text = movies.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            })

            with(binding.rvList) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = moviesAdapter
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
