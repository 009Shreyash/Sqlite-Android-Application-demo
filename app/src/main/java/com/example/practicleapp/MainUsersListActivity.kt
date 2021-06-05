package com.example.practicleapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cursoradapter.widget.SimpleCursorAdapter

class MainUsersListActivity : AppCompatActivity() {
    val from = arrayOf(
        DatabaseHelper._ID,
        DatabaseHelper.SUBJECT, DatabaseHelper.EMAIL,DatabaseHelper.ADDRESS, DatabaseHelper.PHONE
    )
    val to = intArrayOf(R.id.id, R.id.title, R.id.email,R.id.address, R.id.phone)
    private var dbManager: DBManager? = null
    private var listView: ListView? = null
    private var adapter: SimpleCursorAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_emp_list)
        dbManager = DBManager(this)
        dbManager!!.open()
        val cursor = dbManager!!.fetch()
        listView = findViewById<View>(R.id.list_view) as ListView
        listView!!.emptyView = findViewById(R.id.tv_empty)
        adapter = SimpleCursorAdapter(this, R.layout.activity_view_record, cursor, from, to, 0)
        adapter!!.notifyDataSetChanged()
        listView!!.adapter = adapter

        // OnCLickListiner For List Items
        listView!!.onItemClickListener = OnItemClickListener { parent, view, position, viewId ->
            val idTextView = view.findViewById<View>(R.id.id) as TextView
            val titleTextView = view.findViewById<View>(R.id.title) as TextView
            val emailTextView = view.findViewById<View>(R.id.email) as TextView
            val addressTextView = view.findViewById<View>(R.id.address) as TextView
            val phoneTextView = view.findViewById<View>(R.id.phone) as TextView
            val id = idTextView.text.toString()
            val title = titleTextView.text.toString()
            val email = emailTextView.text.toString()
            val address = addressTextView.text.toString()
            val phone = phoneTextView.text.toString()
            val intent = Intent(applicationContext, ModifyCountryActivity::class.java)
            intent.putExtra("title", title)
            intent.putExtra("email", email)
            intent.putExtra("address", address)
            intent.putExtra("phone", phone)
            intent.putExtra("id", id)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.add_record) {
            val intent = Intent(this, AddRecordActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}