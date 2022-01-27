package com.gangaown.shopcar.ui

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.gangaown.shopcar.R
import com.gangaown.shopcar.core.util.UIEvent
import com.gangaown.shopcar.feature.presentation.adapters.SliderViewPagerAdapter
import com.gangaown.shopcar.feature.domain.model.VehicleSpecs
import com.gangaown.shopcar.databinding.FragmentVehicleDetailsBinding
import com.gangaown.shopcar.feature.domain.model.*
import com.gangaown.shopcar.feature.presentation.VehicleDetailsVMFactory
import com.gangaown.shopcar.feature.presentation.VehicleDetailsViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class VehicleDetailsFragment: Fragment(R.layout.fragment_vehicle_details), KodeinAware {

    private val TAG = "VehicleDetailsFragment"

    private var _binding: FragmentVehicleDetailsBinding? = null
    private val binding get() = _binding!!


    private lateinit var viewPagerAdapter: SliderViewPagerAdapter

    private lateinit var vehicleDetailsViewModel: VehicleDetailsViewModel

    override val kodein: Kodein by kodein()
    private val vehicleDetailsVMFactory: VehicleDetailsVMFactory by instance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //container?.removeAllViews()
       _binding =  FragmentVehicleDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vehicleDetailsViewModel = ViewModelProvider(this, vehicleDetailsVMFactory).get(
            VehicleDetailsViewModel::class.java)



        binding.ivRefreshDetails.setOnClickListener {
            vehicleDetailsViewModel.fetchVehicleDetailsInfo()
            binding.pbDetails.visibility = View.VISIBLE
            binding.ivRefreshDetails.visibility = View.INVISIBLE
        }
        initViews(view)
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun initViews(view: View) {

        vehicleDetailsViewModel.fetchVehicleDetailsInfo()
        lifecycleScope.launch {
            vehicleDetailsViewModel.eventFlow.collectLatest {  event ->
                when(event){
                    is UIEvent.ShowSnackBar -> {
                        Snackbar.make(view, event.message?:"Unknown error!",Snackbar.LENGTH_SHORT).show()
                        binding.ivRefreshDetails.visibility = View.VISIBLE
                        binding.pbDetails.visibility = View.INVISIBLE
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                vehicleDetailsViewModel.vehicleDetailsDataState.collect {

                    val vehicleDetails = it.vehicleDetails

                    if(vehicleDetails != null) {
                        binding.ivRefreshDetails.visibility = View.INVISIBLE
                        binding.pbDetails.visibility = View.INVISIBLE

                        showBasicDetails(vehicleDetails)

                        if (vehicleDetails.imageGallery!!.isNotEmpty()) {
                            showImageSlider(vehicleDetails.imageGallery)
                        }

                        if (vehicleDetails.keyFeatures!!.isNotEmpty()) {
                            val keyFeatures = fetchKeyFeatures(vehicleDetails)
                            showKeyFeatures(keyFeatures)
                        }

                        if (vehicleDetails.summaryAttributes!!.isNotEmpty()) {
                            showCarSummary(vehicleDetails.summaryAttributes)
                        }

                        if (vehicleDetails.verifiedFeatures!!.isNotEmpty()) {
                            showOtherFeatures(vehicleDetails.verifiedFeatures)
                        }

                        if (vehicleDetails.manufacturerSpecs!!.isNotEmpty()) {
                            showManufactureSpecs(vehicleDetails.manufacturerSpecs)
                        }

                        if (vehicleDetails.serviceHistory != null && vehicleDetails.serviceHistory.isNotEmpty()) {
                            showServiceHistory(vehicleDetails.serviceHistory)
                        }

                        if (vehicleDetails.consumption != null && vehicleDetails.consumption.isNotEmpty()) {
                            showConsumption(vehicleDetails.consumption)
                        }
                    }
                    /*else{
                        binding.ivRefreshDetails.visibility = View.VISIBLE
                        binding.pbDetails.visibility = View.INVISIBLE
                        //Toast.makeText(context, it.message.toString(), Toast.LENGTH_SHORT).show()
                    }*/
                }

            }
        }
    }




    private fun showBasicDetails(vehicleDetails: VehicleDetails?) {
        // Car basic details
        // Make and Model
        val llContainer = binding.llContainer
        llContainer.visibility = View.VISIBLE
        val makeModel = vehicleDetails!!.make+ " "+vehicleDetails.model
        binding.tvMakeName.text = makeModel
        binding.tvDisplayVarient.text = vehicleDetails.displayVariant

        // Price details
        var price = vehicleDetails.pricing!!.fullPrice?.value
        if (price == null) vehicleDetails.pricing.subscriptionPrice?.value.also { price = it }
        val priceView = this.getString(R.string.currency_unit)+price
        binding.tvPrice.text = priceView
        binding.llPrice.visibility = View.VISIBLE
        val pcp = vehicleDetails.pricing.pcmPrice?.pcp?.value
        if(pcp != null){
            val pcpView = this.getString(R.string.currency_unit)+pcp.toString()+" month/PCP"
            binding.tvPCP.text = pcpView
        }
    }

    private fun showImageSlider(imageList: List<ImageGallery>?) {

        val mViewPager = binding.carImageSlider
        SliderViewPagerAdapter(imageList!!).also { viewPagerAdapter = it }

        mViewPager.apply {
            adapter = viewPagerAdapter
        }

        lifecycleScope.launch{
            while(true){
                for (i in 0..imageList.size){
                    delay(15000)
                    if(i==0){
                        mViewPager.setCurrentItem(i, false)
                    }else{
                        mViewPager.setCurrentItem(i, true)
                    }
                }
            }
        }

    }

    private fun showKeyFeatures(vehicleSpecs: List<VehicleSpecs>) {
        // General specifications of the car
        val llKeyFeatures = binding.llKeyFeatures
        llKeyFeatures.visibility = View.VISIBLE
        for(vehicleSpec in vehicleSpecs){

            val inflater =
                activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val rowView: View = inflater.inflate(R.layout.details_item, LinearLayout(context),false)

            val tvTitle = rowView.findViewById<TextView>(R.id.tvTitle)
            val tvDesc = rowView.findViewById<TextView>(R.id.tvDescription)

            tvTitle.text = vehicleSpec.title
            tvDesc.text = vehicleSpec.desc
            llKeyFeatures.addView(rowView)
        }

    }

    private fun showCarSummary(summaryAttributes: List<SummaryAttribute>) {
        // Summary attributes of car
        val llCarSummary = binding.llCarSummary
        llCarSummary.visibility = View.VISIBLE
        for(summaryAttribute in summaryAttributes){

            val inflater =
                activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val rowView: View = inflater.inflate(R.layout.details_item, LinearLayout(context), false)

            val tvTitle = rowView.findViewById<TextView>(R.id.tvTitle)
            val tvDesc = rowView.findViewById<TextView>(R.id.tvDescription)

            tvTitle.text = summaryAttribute.displayLabel
            tvDesc.text = summaryAttribute.displayValue
            llCarSummary.addView(rowView)
        }
    }

    private fun showOtherFeatures(verifiedFeatures: List<VerifiedFeature>) {

        //  Features of car
        val llVerifiedFeatures= binding.llVerifiedFeature
        llVerifiedFeatures.visibility = View.VISIBLE
        for(verifiedFeature in verifiedFeatures){

            val inflater =
                activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val rowView: View = inflater.inflate(R.layout.details_item, LinearLayout(context), false)

            val tvTitle = rowView.findViewById<TextView>(R.id.tvTitle)
            //val tvDesc = rowView.findViewById<TextView>(R.id.tvDescription)

            tvTitle.text = verifiedFeature.displayLabel
            //tvDesc.text = verifiedFeature.displayValue.toString()
            llVerifiedFeatures.addView(rowView)
        }

    }

    private fun showManufactureSpecs(manufacturerSpecs: List<ManufacturerSpec>) {

        // Car specifications
        val llCarSpecs = binding.llCarSpecs
        llCarSpecs.visibility = View.VISIBLE
        for(manufacturerSpec in manufacturerSpecs){
            val carSpecSubList = manufacturerSpec.displayValue
            if (carSpecSubList != null){
                for(carSpecSub  in carSpecSubList){
                    val inflater =
                        activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                    val rowView: View = inflater.inflate(R.layout.details_item, LinearLayout(context), false)

                    val tvTitle = rowView.findViewById<TextView>(R.id.tvTitle)
                    val tvDesc = rowView.findViewById<TextView>(R.id.tvDescription)

                    tvTitle.text = carSpecSub.displayLabel
                    tvDesc.text = carSpecSub.displayValue
                    llCarSpecs.addView(rowView)
                }
            }
        }
    }


    private fun showRunningCost(runningCosts: List<VehicleSpecs>) {

        // Running cost
        val llRunningCost = binding.llRunningCost
        llRunningCost.visibility = View.VISIBLE
        for(runningCost in runningCosts){
            val inflater =
                activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val rowView: View = inflater.inflate(R.layout.details_item, LinearLayout(context), false)
            val tvTitle = rowView.findViewById<TextView>(R.id.tvTitle)
            val tvDesc = rowView.findViewById<TextView>(R.id.tvDescription)

            tvTitle.text = runningCost.title
            tvDesc.text = runningCost.desc
            llRunningCost.addView(rowView)
        }

    }

    private fun showServiceHistory(serviceHistories: List<ServiceHistory>) {

        //  Service History
        val llServiceHistory= binding.llServiceHistory
        llServiceHistory.visibility = View.VISIBLE
        for(serviceHistory in serviceHistories){

            val inflater =
                activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val rowView: View = inflater.inflate(R.layout.details_item, LinearLayout(context), false)

            val tvTitle = rowView.findViewById<TextView>(R.id.tvTitle)
            val tvDesc = rowView.findViewById<TextView>(R.id.tvDescription)

            tvTitle.text = getString(R.string.date)
            tvDesc.text = serviceHistory.date
            llServiceHistory.addView(rowView)
        }

    }

    private fun showConsumption(consumptions: List<Consumption>) {

        // Consumption of car
        val llConsumptions = binding.llConsumption
        llConsumptions.visibility = View.VISIBLE
        for(consumption in consumptions){

            val inflater =
                activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val rowView: View = inflater.inflate(R.layout.details_item, LinearLayout(context), false)

            val tvTitle = rowView.findViewById<TextView>(R.id.tvTitle)
            val tvDesc = rowView.findViewById<TextView>(R.id.tvDescription)

            tvTitle.text = consumption.displayLabel
            tvDesc.text = consumption.displayValue
            llConsumptions.addView(rowView)
        }
    }

    private fun fetchKeyFeatures(vehicleDetails: VehicleDetails?):List<VehicleSpecs>{

        val keyFeatures = vehicleDetails!!.keyFeatures
        val vehicleSpecsList = mutableListOf<VehicleSpecs>()

        var vehicleSpec: VehicleSpecs
        if (keyFeatures != null) {
            for (keyFeature in keyFeatures){
                val titleString = keyFeature.identifier
                if(titleString != null){
                    try{
                        val title = this.getString(
                            this.resources.getIdentifier(
                                titleString,
                                "string",
                                activity?.packageName
                            )
                        )
                        val desc = keyFeature.displayValue.toString()
                        vehicleSpec = VehicleSpecs(title, desc)
                        vehicleSpecsList.add(vehicleSpec)
                    }catch (e: Resources.NotFoundException){
                        Log.e(TAG,e.toString())
                    }
                }
            }
        }
        return vehicleSpecsList

    }
}