package fl.example.orderlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.javafaker.Faker
import fl.example.orderlist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val list: MutableList<Worker> = mutableListOf()
    private val faker: Faker = Faker()
    private lateinit var adapter: WorkersRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = WorkersRecyclerAdapter(layoutInflater) {
            val intent = Intent(this, WorkerActivity::class.java)
            intent.putExtra("order", it.order)
            intent.putExtra("salary", it.salary.toString())
            intent.putExtra("Address", it.Address)
            startActivity(intent)
        }

        binding.list.adapter = adapter
        binding.list.layoutManager = LinearLayoutManager(this)
        adapter.submitList(null)
        binding.btnAddOrder.setOnClickListener {
            val worker = Worker(faker.name().title(), faker.number().randomDigit()*100000, faker.address().toString())
            list.add(worker)

            adapter.submitList(list.toList())
        }

        binding.btnRemoveOrder.setOnClickListener {
            if (list.isNotEmpty())
                list.removeLast()

            adapter.submitList(list.toList())
        }

        binding.btnClearOrders.setOnClickListener {
            list.clear()
            adapter.submitList(list.toList())
        }
    }

    override fun onStart() {
        super.onStart()

        adapter.submitList(list.toList())
    }

    data class Worker(val order: String, val salary: Int, val Address: String)
}