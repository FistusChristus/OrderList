package fl.example.orderlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class WorkersRecyclerAdapter(
    private val inflater: LayoutInflater,
    private val onClick: (MainActivity.Worker) -> Unit
) :
    ListAdapter<MainActivity.Worker, WorkersRecyclerAdapter.ViewHolder>(WorkerDiffCallback) {

    class ViewHolder(
        view: View,
        val onClick: (MainActivity.Worker) -> Unit
    ) : RecyclerView.ViewHolder(view) {
        private val order = view.findViewById<TextView>(R.id.lo_order)
        private val salary = view.findViewById<TextView>(R.id.lo_salary)
        private val address = view.findViewById<TextView>(R.id.lo_address)
        private var worker: MainActivity.Worker? = null

        init {
            view.setOnClickListener {
                worker?. let {
                    onClick(it)
                }
            }
        }

        fun bind(Worker: MainActivity.Worker) {
            this.worker = Worker
            order.text = Worker.order
            salary.text = Worker.salary.toString()
            address.text = Worker.Address
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.list_item, parent, false)
        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val worker = getItem(position)
        holder.bind(worker)
    }

    object WorkerDiffCallback : DiffUtil.ItemCallback<MainActivity.Worker>() {
        override fun areItemsTheSame(
            oldItem: MainActivity.Worker,
            newItem: MainActivity.Worker
        ): Boolean {

            val res = oldItem == newItem

            return res
        }

        override fun areContentsTheSame(
            oldItem: MainActivity.Worker,
            newItem: MainActivity.Worker
        ): Boolean {

            return oldItem.order == newItem.order && oldItem.salary == newItem.salary && oldItem.Address == newItem.Address
        }
    }
}