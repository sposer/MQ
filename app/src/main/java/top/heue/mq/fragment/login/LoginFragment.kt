package top.heue.mq.fragment.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.android.ext.android.inject
import top.heue.mq.BR
import top.heue.mq.base.BaseActivity
import top.heue.mq.base.BaseFragment
import top.heue.mq.bean.LoginBean
import top.heue.mq.databinding.FragmentLoginBinding

class LoginFragment : BaseFragment() {
    private val loginModel: LoginModel by inject()
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val bean: LoginBean by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.setVariable(BR.bean, bean)
        bean.activity = requireActivity() as BaseActivity?

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}