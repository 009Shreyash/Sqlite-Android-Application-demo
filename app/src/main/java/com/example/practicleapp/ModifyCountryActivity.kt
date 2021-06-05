package com.example.practicleapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_add_record.*
import kotlinx.android.synthetic.main.activity_modify_record.*

class ModifyCountryActivity : Activity(), View.OnClickListener {

    private var _id: Long = 0
    private var dbManager: DBManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify_record)
        dbManager = DBManager(this)
        dbManager!!.open()

        val intent = intent
        val id = intent.getStringExtra("id")
        val name = intent.getStringExtra("title")
        val email = intent.getStringExtra("email")
        val address = intent.getStringExtra("address")
        val phone = intent.getStringExtra("phone")
        _id = id.toLong()
        et_update_name.setText(name)
        et_update_email.setText(email)
        et_update_address.setText(address)
        et_update_Phone.setText(phone)

        btn_update.setOnClickListener(this)
        btn_delete.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_update -> {
                if(isValidate()){
                    val title = et_update_name!!.text.toString()
                    val email = et_update_email!!.text.toString()
                    val address = et_update_address!!.text.toString()
                    val phone = et_update_Phone!!.text.toString()
                    dbManager!!.update(_id, title, email, address, phone)
                    backPressed()
                }
            }
            R.id.btn_delete -> {
                dbManager!!.delete(_id)
                backPressed()
            }
        }
    }


    private fun isValidate(): Boolean {
        if (et_update_name.text.toString().trim() == "") {
            et_update_name.error = resources.getText(R.string.should_not_blank)
            return false
        } else {
            et_update_name.error = null
            et_update_name.clearFocus()
        }

        if (et_update_email.text.toString().trim() == "") {
            et_update_email.error = resources.getText(R.string.should_not_blank)
            return false
        } else {
            et_update_email.error = null
            et_update_email.clearFocus()
        }

        if (et_update_address.text.toString().trim() == "") {
            et_update_address.error = resources.getText(R.string.should_not_blank)
            return false
        } else {
            et_update_address.error = null
            et_update_address.clearFocus()
        }

        if (et_update_Phone.text.toString().trim() == "") {
            et_update_Phone.error = resources.getText(R.string.should_not_blank)
            return false
        } else {
            et_update_Phone.error = null
            et_update_Phone.clearFocus()
        }


        return true
    }

    private fun backPressed() {
        val intent = Intent(applicationContext, MainUsersListActivity::class.java)
            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}