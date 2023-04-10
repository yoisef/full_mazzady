package com.mazaady.task.presentation.fragments.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.mazaady.task.R
import com.mazaady.task.adapters.*
import com.mazaady.task.databinding.FragmentMainBinding
import com.mazaady.task.domain.ICategory
import com.mazaady.task.domain.Property
import com.mazaady.task.domain.models.Category
import com.mazaady.task.domain.models.Option
import com.mazaady.task.domain.models.PropData
import com.mazaady.task.utils.Status
import com.mazaady.task.utils.afterTextChangedFlow
import com.mazaady.task.utils.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@AndroidEntryPoint
class MainFragment : Fragment(), Property ,ICategory{
    private lateinit var binding: FragmentMainBinding
    private lateinit var bottomSheetDialog: BottomSheetDialog
    private val viewModel: MainViewModel by viewModels()
    private lateinit var mAdapter : Property1Adapter
    private lateinit var valuesAdapter : Property2Adapter

    private lateinit var optionAdapter : OptionAdapter
    private lateinit var categoriesAdapter : CategoriesAdapter
    private  var properties:MutableList<PropData> = ArrayList<PropData>()

    private var selectOption: Option?=null
    private var subPosition: Int?=null

    private lateinit var listCategories : List<Category>
    private  var listSubcategories : List<Category>? = null

    private lateinit var closeBtn : ImageView
    private lateinit var searchField : EditText
    private lateinit var recyclerPro : RecyclerView
    private lateinit var propertyName : TextView



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        initialization()
        onClickListener()
        observers()

        // get categories
        viewModel.getMainCategories()






        return binding.root
    }

    private fun  observers()
    {
        // observerrecieve categories
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.getMainCategories.collect{
                    it ->
                when(it.status)
                {
                    Status.SUCCESS ->{
                        binding.progressBar.visibility=View.GONE

                        //it.data?.let { it1 -> setCategories(it1.data.categories) }
                        listCategories=it.data.let { data -> data!!.data.categories }
                        binding.inputEditTextSubCategory.text?.clear()
                    }
                    Status.ERROR ->{
                        binding.progressBar.visibility=View.GONE

                        //   handleErrorStatus(it)
                    }
                    Status.LOADING ->{
                        binding.progressBar.visibility=View.VISIBLE


                    }
                }

            }


        }

        //observer properties
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.getProperties.collect{
                    it ->
                when(it.status)
                {
                    Status.SUCCESS ->{
                        binding.progressBar.visibility=View.GONE
                        binding.recyclerProperties.visibility=View.VISIBLE
                        properties.clear()

                        properties.addAll(it.data!!.data as MutableList<PropData>)



                        mAdapter.updateDayListItems(properties)

                        binding.recyclerProperties.adapter

                    }
                    Status.ERROR ->{
                        binding.progressBar.visibility=View.GONE

                        //  handleErrorStatus(it)
                    }
                    Status.LOADING ->{
                        binding.progressBar.visibility=View.VISIBLE


                    }
                }

            }


        }

        //obsever options
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.getOptionsChild.collect{
                    newProperty ->
                when(newProperty.status)
                {
                    Status.SUCCESS ->{
                        binding.progressBar.visibility=View.GONE
                        properties.filter { prop -> prop.slug==newProperty.data!!.data[0].slug }
                            .let {
                                if (it.isNotEmpty())
                                {
                                    properties.remove(it[0])
                                }

                            }
                        properties.add(subPosition!! +1,newProperty.data!!.data[0])
                        mAdapter.updateDayListItems(properties)


                    }
                    Status.ERROR ->{
                        binding.progressBar.visibility=View.GONE

                        //  handleErrorStatus(it)
                    }
                    Status.LOADING ->{
                        binding.progressBar.visibility=View.VISIBLE


                    }
                }

            }

        }
    }
    private fun initialization()
    {
      //  binding.inputEditTextMainCategory.requestFocus()
        bottomSheetDialog =  BottomSheetDialog(requireContext(),R.style.BottomSheetDialog)
        bottomSheetDialog.setContentView(com.mazaady.task.R.layout.bottom_search_sheet)
        //initialize recycler_properties
        binding.recyclerProperties.layoutManager = LinearLayoutManager(context)
        mAdapter = context?.let { Property1Adapter(arrayListOf(), it,this) }!!
        binding.recyclerProperties.adapter =mAdapter


        //initialize bottom sheet
        closeBtn = bottomSheetDialog.findViewById<ImageView>(R.id.close_btn)!!
        searchField = bottomSheetDialog.findViewById<EditText>(R.id.search_edit)!!
        recyclerPro = bottomSheetDialog.findViewById<RecyclerView>(R.id.recycler_properties)!!
        propertyName = bottomSheetDialog.findViewById<TextView>(R.id.property_name)!!
    }
    private fun onClickListener()
    {
        // on Click for Main Category
        binding.inputEditTextMainCategory.setOnClickListener {


            showBottomSheetCategories(listCategories,resources.getString(R.string.main_category))

        }

        // on Click On SubCategory
        binding.inputEditTextSubCategory.setOnClickListener {

            if (listSubcategories!=null)
            {
                showBottomSheetCategories(listSubcategories!!,resources.getString(R.string.sub_category))

            }
        }

        binding.submitBtn.setOnClickListener {

            this.hideKeyboard()
            binding.nestedScrollView.postDelayed(
                Runnable { binding.nestedScrollView.fullScroll(View.FOCUS_DOWN) },
                200
            )


            binding.maincategoryValue.text=  binding.inputEditTextMainCategory.text.toString()
          binding.subcategoryValue.text=    binding.inputEditTextSubCategory.text.toString()
          binding.valuesLayout.visibility=View.VISIBLE
          binding.recyclerValues.layoutManager = GridLayoutManager(context,2)
           // valuesAdapter.updateDayListItems(properties)
          valuesAdapter = context?.let { Property2Adapter(properties as ArrayList<PropData>, it) }!!
          binding.recyclerValues.adapter =valuesAdapter

        }

        binding.navigateBtn.setOnClickListener {

             findNavController().navigate(R.id.action_main_to_auction_details)


        }

    }
    private fun showBottomSheetCategories(categories : List<Category>,title : String) {

        propertyName!!.text =title





        recyclerPro!!.layoutManager= LinearLayoutManager(context)
        categoriesAdapter= context?.let { CategoriesAdapter(arrayListOf(), it,this) }!!
        recyclerPro.adapter= categoriesAdapter


        categoriesAdapter.updateOptionListItems(categories)
        closeBtn!!.setOnClickListener {

            bottomSheetDialog.dismiss()

        }



        lifecycleScope.launch {
            searchField?.afterTextChangedFlow()?.debounce(500)?.collect() {
                if (it != null) {

                    if (it.isNotEmpty()) {

                        categories.filter { option -> option.name .contains(it.toString()) || option.slug.contains(it.toString()) }.let { list ->
                            categoriesAdapter.updateOptionListItems(list)
                        }
                    } else if (it.isEmpty()) {

                        categoriesAdapter.updateOptionListItems(categories)

                    }

                }
            }
        }

        bottomSheetDialog!!.show()

        if (searchField.text.isNotEmpty())
        {
            searchField.text.clear()

        }

    }
    private fun showBottomSheetProperty(property : PropData) {


        propertyName.text = property.slug



        recyclerPro.layoutManager = LinearLayoutManager(context)
        optionAdapter = context?.let { OptionAdapter(arrayListOf(), it, this) }!!
        recyclerPro.adapter = optionAdapter


        closeBtn.setOnClickListener {

            bottomSheetDialog.dismiss()

        }

        property.options.filter { option -> option.name.equals("other") }.apply {
            if (this.isEmpty()) {
                property.options.add(0, Option(false, 0, "other", property.id, "other"))

            }

        lifecycleScope.launch {
            searchField?.afterTextChangedFlow()?.debounce(500)?.collect() {
                if (it != null) {

                    if (it.isNotEmpty()) {

                        property.options.filter { option ->
                            option.name.contains(it.toString()) || option.slug.contains(
                                it.toString()
                            )
                        }.let { list ->
                            optionAdapter.updateOptionListItems(list)
                        }
                    } else if (it.isEmpty()) {
                        property.options.filter { option -> option.name.equals("other") }.apply {
                            if (this.isEmpty())
                            {
                                property.options.add(0, Option(false,0,"other",property.id,"other"))

                            }
                        }
                        optionAdapter.updateOptionListItems(property.options)

                    }

                }
            }
        }

            optionAdapter.updateOptionListItems(property.options)

            bottomSheetDialog.show()
            if (searchField.text.isNotEmpty()) {
                searchField.text.clear()

            }
        }
    }
    override fun onPropertyClick(propDetails: PropData) {

      //  searchField.text.clear()
        showBottomSheetProperty(propDetails)
    }

    override fun onOptionClick(option: Option, position: Int) {

        Log.e("current_option","=="+option.child)
        selectOption=option
        bottomSheetDialog.dismiss()
        mAdapter.updateProperty(option,0)


    }

    override fun getOptionChild(option: Option, position: Int) {

        subPosition=position

        viewModel.getOptionChild(option)


    }

    override fun onCategorySubClick(category: Category?, type: Int) {

        binding.valuesLayout.visibility=View.GONE
        bottomSheetDialog.dismiss()
        if (type==1)
        {
            //on category click
            binding.recyclerProperties.clearFocus()
            properties.clear()
            listSubcategories= category?.children!!
            binding.inputEditTextMainCategory.setText(category.slug)
            binding.inputEditTextSubCategory.text?.clear()
            binding.recyclerProperties.visibility=View.GONE

         //   binding.inputEditTextSubCategory.requestFocus()


        }else{
            //on sub category click
            binding.recyclerProperties.clearFocus()
            binding.inputEditTextSubCategory.setText(category?.slug)
            if(category!=null)
            {
                viewModel.getProperties(category.id.toString())

            }

           // mAdapter.updateDayListItems(category.)

        }

    }
}