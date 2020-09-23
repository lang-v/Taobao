package cn.cqupt.taobao.view.fragment.shop.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.cqupt.taobao.R
import cn.cqupt.taobao.bean.Goods
import cn.cqupt.taobao.view.activity.GoodsInfoActivity
import cn.cqupt.taobao.view.toBitmap
import com.bumptech.glide.Glide

class GoodsListAdapter(private val list:ArrayList<Goods>) : RecyclerView.Adapter<GoodsListAdapter.VH>() {

    override fun getItemCount(): Int {
        return list.size
    }

    class VH(view: View) : RecyclerView.ViewHolder(view) {
        val desc:TextView = view.findViewById(R.id.description)
        val img: ImageView = view.findViewById(R.id.img)
        val money: TextView = view.findViewById(R.id.moneyValue)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        if (list[position].pic != "") {
            val bitmap = "".toBitmap(list[position].pic)
            Glide.with(holder.itemView)
                .load(bitmap)
                .into(holder.img)
        }
        holder.desc.text = list[position].name
        holder.money.text = list[position].price

        holder.itemView.setOnClickListener{
            GoodsInfoActivity.start(it.context,list[position].name,list[position].pic,list[position].price,list[position].count)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.good_item_layout, parent, false)
        return VH(view)
    }
}