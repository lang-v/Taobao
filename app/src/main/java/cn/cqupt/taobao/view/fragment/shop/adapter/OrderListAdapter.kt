package cn.cqupt.taobao.view.fragment.shop.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.cqupt.taobao.R
import cn.cqupt.taobao.bean.Goods
import cn.cqupt.taobao.bean.Order
import cn.cqupt.taobao.net.NetUtil
import cn.cqupt.taobao.view.activity.GoodsInfoActivity
import cn.cqupt.taobao.view.toBitmap
import com.bumptech.glide.Glide
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class OrderListAdapter(private val list:ArrayList<Order>) : RecyclerView.Adapter<OrderListAdapter.VH>() {

    override fun getItemCount(): Int {
        return list.size
    }

    class VH(view: View) : RecyclerView.ViewHolder(view) {
//        val delete = itemView.findViewById<Button>(R.id.deleteOrderItem)
        val name = itemView.findViewById<TextView>(R.id.goodsName)
        val buyPerson = itemView.findViewById<TextView>(R.id.buyPerson)
        val price = itemView.findViewById<TextView>(R.id.moneyChange)
        val time = itemView.findViewById<TextView>(R.id.buyTime)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = list[position]

        holder.name.text = item.name
        holder.buyPerson.text = item.buyPerson
        holder.time.text = item.time
        holder.price.text = item.moneyChange
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.order_item_layout, parent, false)
        return VH(view)
    }
}