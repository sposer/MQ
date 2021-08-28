package top.heue.mq.fragment.expand

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.android.ext.android.inject
import top.heue.mq.base.BaseFragment
import top.heue.mq.databinding.FragmentExpandBinding

class ExpandFragment: BaseFragment() {
    private val expandModel: ExpandModel by inject()
    private var _binding: FragmentExpandBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExpandBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}