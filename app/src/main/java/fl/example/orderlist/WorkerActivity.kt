package fl.example.orderlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fl.example.orderlist.databinding.ActivityUserBinding

class WorkerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loOrder.text = intent.getStringExtra("order") ?: ""
        binding.loSalary.text = intent.getStringExtra("salary") ?: ""
        binding.loAddress.text = intent.getStringExtra("score") ?: ""
    }
}