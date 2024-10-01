package com.example.firebaserealtimedatabase.repository

import androidx.lifecycle.MutableLiveData
import com.example.firebaserealtimedatabase.model.Data
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Transaction.Handler
import com.google.firebase.database.ValueEventListener

class DataRepository {

    private val databaseReference:DatabaseReference = FirebaseDatabase.getInstance().getReference("data")

    fun fetchData(): MutableLiveData<List<Data>>{
        val dataList = MutableLiveData<List<Data>>()

        databaseReference.addValueEventListener(object : ValueEventListener{
   
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<Data>()
                for (dataSnapshot in snapshot.children){
                    val data = dataSnapshot.getValue(Data::class.java)
                    data?.let { list.add(it) }
                }
                dataList.value = list

            }

            override fun onCancelled(error: DatabaseError) {
               dataList.value = emptyList()

            }


        })

    }

}