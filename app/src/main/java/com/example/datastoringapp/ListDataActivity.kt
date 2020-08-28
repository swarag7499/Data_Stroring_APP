package com.example.datastoringapp

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
//import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import java.util.*


/**
 * Created by User on 2/28/2017.
 */
class ListDataActivity : AppCompatActivity() {
    var mDatabaseHelper: DatabaseHelper? = null
    private var mListView: ListView? = null
    protected override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_layout)
        mListView = findViewById(R.id.listView) as ListView?
        mDatabaseHelper = DatabaseHelper(this)
        populateListView()
    }

    private fun populateListView() {
        Log.d(
            TAG,
            "populateListView: Displaying data in the ListView."
        )

        //get the data and append to a list
        val data: Cursor = mDatabaseHelper.getData()
        val listData = ArrayList<String?>()
        while (data.moveToNext()) {
            //get the value from the database in column 1
            //then add it to the ArrayList
            listData.add(data.getString(1))
        }
        //create the list adapter and set the adapter
        val adapter: ListAdapter =
            ArrayAdapter<Any?>(this, android.R.layout.simple_list_item_1, listData as List<Any?>)
        mListView!!.adapter = adapter

        //set an onItemClickListener to the ListView
        mListView!!.onItemClickListener =
            OnItemClickListener { adapterView, view, i, l ->
                val name = adapterView.getItemAtPosition(i).toString()
                Log.d(
                    TAG,
                    "onItemClick: You Clicked on $name"
                )
                val data =
                    mDatabaseHelper!!.getItemID(name) //get the id associated with that name
                var itemID = -1
                while (data.moveToNext()) {
                    itemID = data.getInt(0)
                }
                if (itemID > -1) {
                    Log.d(
                        TAG,
                        "onItemClick: The ID is: $itemID"
                    )
                    val editScreenIntent =
                        Intent(this@ListDataActivity, EditDataActivity::class.java)
                    editScreenIntent.putExtra("id", itemID)
                    editScreenIntent.putExtra("name", name)
                    startActivity(editScreenIntent)
                } else {
                    toastMessage("No ID associated with that name")
                }
            }
    }

    /**
     * customizable toast
     * @param message
     */
    private fun toastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val TAG = "ListDataActivity"
    }
}
