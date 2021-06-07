package com.example.kotlinexam

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinexam.databinding.ActivityMainBinding

@SuppressLint("StaticFieldLeak")
class MainActivity : AppCompatActivity() , OnDeleteListener {

    lateinit var db : TestDatabase
    var testList = listOf<TestEntity>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        db = TestDatabase.getInstance(this)!!
        binding.btnAdd.setOnClickListener {
            val entity = TestEntity(null, binding.etText.text.toString())
            insertData(entity)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        getAllData()
    }

    //1. Insert Data
    //2. Get Data
    //3. Delete Data
    //4. Set RecyclerView

    fun insertData(entity: TestEntity) {
        val insertTask = object : AsyncTask<Unit, Unit, Unit>() {
            override fun doInBackground(vararg params: Unit?) {
                db.testDAO().insert(entity)
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                getAllData()
            }
        }

        insertTask.execute()
    }

    fun getAllData() {
        val getTask = (object : AsyncTask<Unit, Unit, Unit>(){
            override fun doInBackground(vararg p0: Unit?) {
                testList = db.testDAO().getAll()
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                setRecyclerView(testList)

            }
        }).execute()
    }

    fun deleteData(entity: TestEntity) {
        val deleteTask = object : AsyncTask<Unit, Unit, Unit>() {
            override fun doInBackground(vararg p0: Unit?) {
                db.testDAO().delete(entity)
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                getAllData()
            }
        }
        deleteTask.execute()
    }

    fun setRecyclerView(testList: List<TestEntity>) {
        binding.recyclerView.adapter = MyAdapter(this, testList, this)
    }

    override fun onDeleteListener(entity: TestEntity) {
        deleteData(entity)
    }

}