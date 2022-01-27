package dev.cianjur.ngopi.core.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.navigation.dynamicfeatures.fragment.ui.AbstractProgressFragment
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
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
        binding.message.setText(convertProgressStatus(status))
        binding.progressBar.progress = (bytesDownloaded.toDouble() * 100 / bytesTotal).toInt()
    }

    @StringRes
    private fun convertProgressStatus(status: Int): Int {
        return when (status) {
            SplitInstallSessionStatus.PENDING -> R.string.install_pending
            SplitInstallSessionStatus.DOWNLOADED -> R.string.install_downloaded
            SplitInstallSessionStatus.INSTALLING -> R.string.install_installing
            else -> R.string.install_downloading
        }
    }

    override fun onCancelled() {
        binding.message.text = getString(R.string.install_cancelled)
    }

    override fun onFailed(errorCode: Int) {
        binding.message.text = getString(R.string.install_failed, errorCode)
    }

}
