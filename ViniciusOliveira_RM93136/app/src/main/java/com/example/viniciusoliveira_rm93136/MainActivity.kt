package com.example.viniciusoliveira_rm93136

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.viniciusoliveira_rm93136.adapter.BeachAdapter
import com.example.viniciusoliveira_rm93136.models.Beach
import com.example.viniciusoliveira_rm93136.ui.theme.ViniciusOliveira_RM93136Theme

class MainActivity : ComponentActivity(), BeachAdapter.OnBeachClickListener {

    private lateinit var adapter: BeachAdapter
    private lateinit var beachList: MutableList<Beach>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViniciusOliveira_RM93136Theme {
                AndroidView(
                    modifier = Modifier.fillMaxSize(),
                    factory = { context ->
                        val view = layoutInflater.inflate(R.layout.main_activity, null)
                        setupViews(view)
                        return@AndroidView view
                    }
                )
            }
        }
    }
    private fun addBeach(view: View) {
        val changeBeach = view.findViewById<EditText>(R.id.beachName)
        val changeCity = view.findViewById<EditText>(R.id.cityName)
        val changeEstate = view.findViewById<EditText>(R.id.estateName)

        val name = changeBeach.text.toString().trim()
        val city = changeCity.text.toString().trim()
        val estate = changeEstate.text.toString().trim()

        if (name.isEmpty() || city.isEmpty() || estate.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos os campos", Toast.LENGTH_SHORT).show()
            return
        }

        val beach = Beach(name, city, estate){
        }

        adapter.addBeach(beach)

        changeBeach.text.clear()
        changeCity.text.clear()
        changeEstate.text.clear()
    }

    override fun onExcludeClick(beach: Beach) {
        adapter.removeBeach(beach)
    }

    private fun setupViews(view: View) {
        val recyView = view.findViewById<RecyclerView>(R.id.recyView);
        beachList = mutableListOf()
        adapter = BeachAdapter(beachList)
        recyView.adapter = adapter
        recyView.layoutManager = LinearLayoutManager(this)
        adapter.setOnBeachClickListener(this)

        val includeBtn = view.findViewById<Button>(R.id.includeBtn)
        includeBtn.setOnClickListener {
            addBeach(view)
        }
    }
}
