package cn.cqupt.taobao.view.fragment.order.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import cn.cqupt.taobao.R
import cn.cqupt.taobao.bean.Good
import com.bumptech.glide.Glide

class DataListAdapter(private val list:ArrayList<Good>) : RecyclerView.Adapter<DataListAdapter.VH>() {

    override fun getItemCount(): Int {
        return 10
    }

    class VH(view: View) : RecyclerView.ViewHolder(view) {
        val desc:TextView = view.findViewById(R.id.description)
        val img: ImageView = view.findViewById(R.id.img)
        val money: TextView = view.findViewById(R.id.moneyValue)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
//        Glide.with(holder.itemView)
//            .load(list[position].imgPath)
//            .into(holder.img)
//        holder.desc.text = list[position].desc
//        holder.money.text = list[position].money
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
//        val view = View.inflate(parent.context,R.layout.good_item_layout,parent)
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.good_item_layout, parent, false)
        return VH(view)
    }
}