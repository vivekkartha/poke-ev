package com.nightshade.pokeev.ui.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.nightshade.pokeev.R
import com.nightshade.pokeev.data.models.PokemonEffortVO
import com.nightshade.pokeev.data.models.Stat
import com.nightshade.pokeev.data.providers.DataProvider
import com.nightshade.pokeev.databinding.FragmentHomeBinding
import com.nightshade.pokeev.hide
import com.nightshade.pokeev.show

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels {
        HomeViewModelFactory(
            DataProvider(requireContext().resources)
        )
    }
    private var _binding: FragmentHomeBinding? = null
    private val evAdapter by lazy { EvRecyclerAdapter(requireContext(), mutableListOf()) }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        observeOnHomeViewModel()
        homeViewModel.getEvData()
        return root
    }

    private fun observeOnHomeViewModel() {
        binding.tvLoading.show()
        homeViewModel.effortValues.observe(viewLifecycleOwner, { effortVOList ->
            setEvList(effortVOList)
            binding.tvLoading.hide()
        })
    }

    private fun setEvList(effortVOList: List<PokemonEffortVO>) {
        with(binding.rvStats) {
            layoutManager = LinearLayoutManager(context)
            adapter = evAdapter
            evAdapter.pokemonEffortVOList.addAll(effortVOList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val stat = when (item.itemId) {
            R.id.hp -> Stat.HP
            R.id.speed -> Stat.SPEED
            R.id.atk -> Stat.ATTACK
            R.id.def -> Stat.DEFENSE
            R.id.spAtk -> Stat.SPATTACK
            R.id.spDef -> Stat.SPDEF
            else -> Stat.ALL
        }
        evAdapter.filter(stat)
        return true
    }
}