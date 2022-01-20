package com.gangaown.shopcar.feature.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gangaown.shopcar.R

import com.gangaown.shopcar.core.util.SharedInfoUtil
import com.gangaown.shopcar.feature.domain.model.VehicleBasicInfo
import com.gangaown.shopcar.ui.VehicleDetailsFragment

class VehicleListViewAdapter(var vehicleList: List<VehicleBasicInfo>): RecyclerView.Adapter<VehicleListViewAdapter.VehicleViewHolder>() {

    inner class VehicleViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val tvMake : TextView
        val tvDisplayVarient : TextView
        val tvMilage : TextView
        val tvRegYear : TextView
        val tvPrice : TextView
        val tvPcp : TextView
        val ivUrl : ImageView
        val cvVehicle : CardView

        init {
            tvMake =  itemView.findViewById(R.id.tv_make)
            tvDisplayVarient = itemView.findViewById(R.id.tv_display_varient)
            tvMilage = itemView.findViewById(R.id.tv_miles)
            tvRegYear = itemView.findViewById(R.id.tv_reg)
            tvPrice = itemView.findViewById(R.id.tv_price)
            tvPcp = itemView.findViewById(R.id.tv_pcp)
            ivUrl  = itemView.findViewById(R.id.iv_vehicle)
            cvVehicle = itemView.findViewById(R.id.cv_vehicle)
        }

        fun setData(curVehicle: VehicleBasicInfo){
            val context = itemView.context as AppCompatActivity
            val makeModel = curVehicle.make +" "+ curVehicle.model
            tvMake.text = makeModel
            tvDisplayVarient.text = curVehicle.displayVariant
            (curVehicle.mileage.toString()+context.getString(R.string.miles)).also { tvMilage.text = it }
            (curVehicle.registrationYear.toString()+context.getString(R.string.reg)).also { tvRegYear.text = it }


            var pricing = curVehicle.pricing!!.fullPrice?.value
            if (pricing == null){
                pricing = curVehicle.pricing.subscriptionPrice?.value
            }
            (context.getString(R.string.currency_unit)+ pricing.toString()).also { tvPrice.text = it }

            val pcp = curVehicle.pricing.pcmPrice?.pcp?.value
            val url = curVehicle.images!!.main!!.url
            if(pcp!=null){
                ("$pcp/month PCP").also { tvPcp.text = it }
            }

            Glide.with(itemView.context)
                .load(url)
                .into(ivUrl)

            cvVehicle.setOnClickListener{
                /*val intent = Intent(context, VehicleDetailsActivity::class.java)
                SharedInfoUtil.PRODUCT_ID = curVehicle.id!!
                context.startActivity(intent)*/

                SharedInfoUtil.PRODUCT_ID = curVehicle.id!!
                val appCompatActivity = it.context as AppCompatActivity
                appCompatActivity.supportFragmentManager.
                beginTransaction()
                    .replace(R.id.navHostFragment, VehicleDetailsFragment())
                    .addToBackStack(null)
                    .commit()
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.vehicle_list_item, parent, false)
        return VehicleViewHolder(view)
    }

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        val curVehicle = vehicleList[position]
        holder.setData(curVehicle)

    }

    override fun getItemCount(): Int {
        return vehicleList.count()
    }
}