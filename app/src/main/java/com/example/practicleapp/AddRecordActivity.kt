package com.example.practicleapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_record.*

class AddRecordActivity : AppCompatActivity(), View.OnClickListener {

    private var dbManager: DBManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_record)

        dbManager = DBManager(this)
        dbManager!!.open()
        btn_add_record.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_add_record -> {
                if (isValidate()){
                    val name = et_name!!.text.toString()
                    val email = et_email!!.text.toString()
                    val address = et_Address!!.text.toString()
                    val phone = et_Phone!!.text.toString()
                    dbManager!!.insert(name, email, address, phone)
                    val intent = Intent(this@AddRecordActivity, MainUsersListActivity::class.java)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                }
            }
        }
    }

    private fun isValidate(): Boolean {
        if (et_name.text.toString().trim() == "") {
            et_name.error = resources.getText(R.string.should_not_blank)
            return false
        } else {
            et_name.error = null
            et_name.clearFocus()
        }

        if (et_email.text.toString().trim() == "") {
            et_email.error = resources.getText(R.string.should_not_blank)
            return false
        } else {
            et_email.error = null
            et_email.clearFocus()
        }

        if (et_Address.text.toString().trim() == "") {
            et_Address.error = resources.getText(R.string.should_not_blank)
            return false
        } else {
            et_Address.error = null
            et_Address.clearFocus()
        }

        if (et_Phone.text.toString().trim() == "") {
            et_Phone.error = resources.getText(R.string.should_not_blank)
            return false
        } else {
            et_Phone.error = null
            et_Phone.clearFocus()
        }


        return true
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
            menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.view_record) {
            val intent = Intent(this, MainUsersListActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}