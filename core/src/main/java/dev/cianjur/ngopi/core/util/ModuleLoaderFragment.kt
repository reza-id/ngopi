package dev.cianjur.ngopi.core.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.dynamicfeatures.fragment.ui.AbstractProgressFragment
import dev.cianjur.ngopi.core.R
import dev.cianjur.ngopi.core.databinding.FragmentModuleLoaderBinding

class ModuleLoaderFragment : AbstractProgressFragment() {

    private var _binding: FragmentModuleLoaderBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentModuleLoaderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onProgress(status: Int, bytesDownloaded: Long, bytesTotal: Long) {
        binding.message.text = "Installing... [$status]"
        binding.progressBar.progress = (bytesDownloaded.toDouble() * 100 / bytesTotal).toInt()
    }

    override fun onCancelled() {
        binding.message.text = getString(R.string.install_cancelled)
    }

    override fun onFailed(errorCode: Int) {
        binding.message.text = getString(R.string.install_failed, errorCode)
    }

}
