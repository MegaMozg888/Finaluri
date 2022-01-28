package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.renderscript.Sampler
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_first.*
import org.w3c.dom.Text

class FirstFragment : Fragment(R.layout.fragment_first){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userImage = view.findViewById<ImageView>(R.id.userImage)
        val userName = view.findViewById<TextView>(R.id.userName)
        val addUserPhoto = view.findViewById<EditText>(R.id.addUserPhoto)
        val addUserName = view.findViewById<EditText>(R.id.addUserName)
        val saveBtn = view.findViewById<Button>(R.id.saveBtn)
        val toDoListBtn = view.findViewById<Button>(R.id.toDoListBtn)
        val db = FirebaseDatabase.getInstance().getReference("UserInfo")
        val auth = FirebaseAuth.getInstance()

        toDoListBtn.setOnClickListener {
            startActivity(Intent(requireContext(), ToDoListActivity::class.java))
            requireActivity().finish()

        }


        db.child(auth.currentUser?.uid!!).addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val userInfo: UserInfo = snapshot.getValue(UserInfo::class.java) ?:return
                userName.text = userInfo.name
                Glide.with(this@FirstFragment)
                    .load(userInfo.imageUrl).placeholder(R.drawable.ic_launcher_foreground).into(userImage)

            }

            override fun onCancelled(error: DatabaseError) {
            }

        })

        saveBtn.setOnClickListener {
            val name = addUserName.text.toString()
            val imageUrl = addUserPhoto.text.toString()

            if (name.isEmpty()|| imageUrl.isEmpty()){
                Toast.makeText(this.context, "Empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val userInfo = UserInfo(name,imageUrl)
            db.child(auth.currentUser?.uid!!)
                .setValue(userInfo)

        }

    }

}