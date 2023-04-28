package com.youth4work.HSSC_2023.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter_extensions.scroll.EndlessRecyclerOnScrollListener
import com.squareup.picasso.Picasso
import com.vlonjatg.progressactivity.ProgressRelativeLayout
import com.youth4work.HSSC_2023.R
import com.youth4work.HSSC_2023.ui.adapter.ChatUserListingAdapter
import com.youth4work.HSSC_2023.ui.base.BaseActivity
import com.youth4work.prepapp.network.model.response.SubCatDetailsItem
import com.youth4work.prepapp.network.model.response.Youth
import com.youth4work.prepapp.network.model.response.YouthListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChatActivity : BaseActivity() {
    //lateinit var layout:ActivityChatBinding
    lateinit var subCatDetailsItem: SubCatDetailsItem
    lateinit var youthList: ArrayList<Youth>
    lateinit var adapter:ChatUserListingAdapter
    lateinit var llm:LinearLayoutManager
    var page:Int=1

    lateinit var imgUser:ImageView
    lateinit var imgUser1:ImageView
    lateinit var imgUser2:ImageView
    lateinit var imgUser3:ImageView
    lateinit var imgUser4:ImageView
    lateinit var progressActiviity:ProgressRelativeLayout
    lateinit var recyclerChatUser:RecyclerView
    lateinit var txtSubCat:TextView
    lateinit var txtSubCatAttempt:TextView
    lateinit var txtPartentCat:TextView
    lateinit var imgSubCat:ImageView
    lateinit var txtChat:TextView
    lateinit var txtTitle:TextView
    lateinit var txtSubCatName:TextView
    lateinit var btnBack:ImageView
    lateinit var cardCat:CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        imgUser=findViewById(R.id.img_user)
        imgUser1=findViewById(R.id.img_user1)
        imgUser2=findViewById(R.id.img_user2)
        imgUser3=findViewById(R.id.img_user3)
        imgUser4=findViewById(R.id.img_user4)
        progressActiviity=findViewById(R.id.progress_activiity)
        recyclerChatUser=findViewById(R.id.recycler_chat_user)
        txtSubCat=findViewById(R.id.txt_sub_cat)
        txtSubCatAttempt=findViewById(R.id.txt_sub_cat_attempt)
        txtPartentCat=findViewById(R.id.txt_partent_cat)
        imgSubCat=findViewById(R.id.img_sub_cat)
        txtChat=findViewById(R.id.txt_chat)
        txtTitle=findViewById(R.id.txt_title)
        txtSubCatName=findViewById(R.id.txt_sub_cat_name)
        btnBack=findViewById(R.id.btn_back)
        cardCat =findViewById(R.id.card_cat)
        supportActionBar?.hide()
        llm= LinearLayoutManager(this)
        click_event()
        getUserList("nodefaultimage")
        getCategoryData()
    }

    private fun getUserList(flag:String) {
        val call=prepService.getYouthList(mUserManager.category.catid, mUserManager.user.userId,1,20,flag)
        call.enqueue(object :Callback<YouthListResponse>{
            override fun onResponse(
                call: Call<YouthListResponse>,
                response: Response<YouthListResponse>
            ) {
                if(response.isSuccessful&&response.code()==200){
                   val youthListResponse= response.body()!!
                    youthList= youthListResponse.youthList as ArrayList<Youth>
                    if(youthList.size>5) {
                        Picasso.with(this@ChatActivity)
                            .load(youthList.get(0).imageURL).into(imgUser)
                        Picasso.with(this@ChatActivity)
                            .load(youthList.get(1).imageURL).into(imgUser1)
                        Picasso.with(this@ChatActivity)
                            .load(youthList.get(2).imageURL).into(imgUser2)
                        Picasso.with(this@ChatActivity)
                            .load(youthList.get(3).imageURL).into(imgUser3)
                        Picasso.with(this@ChatActivity)
                            .load(youthList.get(4).imageURL).into(imgUser4)
                    }
                    progressActiviity.showContent()
                    initalizedAdapter(youthListResponse)
                }
                else{
                    progressActiviity.showContent()
                    Toast.makeText(self,"Something went wrong,please try again...",Toast.LENGTH_SHORT ).show()
                }
            }

            override fun onFailure(call: Call<YouthListResponse>, t: Throwable) {
                progressActiviity.showContent()
                Log.d("error", t.message.toString())
            }
        })
        recyclerChatUser.addOnScrollListener(object :EndlessRecyclerOnScrollListener(llm){
            override fun onLoadMore(currentPage: Int) {
               getUserList(page++)
            }
        })
    }

    fun getUserList(page:Int){
        val call=prepService.getYouthList(mUserManager.category.catid, mUserManager.user.userId,page,20,"")
        call.enqueue(object :Callback<YouthListResponse>{
            override fun onResponse(
                call: Call<YouthListResponse>,
                response: Response<YouthListResponse>
            ) {
                if(response.isSuccessful&&response.code()==200){
                   val yLT:YouthListResponse=response.body()!!
                    progressActiviity.showContent()
                    if(yLT!=null&&yLT.youthList.size>0) {
                        youthList.addAll(yLT.youthList)
                        adapter.notifyDataSetChanged()
                    }

                }
                else{
                    progressActiviity.showContent()
                    Toast.makeText(self,"Something went wrong,please try again...",Toast.LENGTH_SHORT ).show()
                }
            }

            override fun onFailure(call: Call<YouthListResponse>, t: Throwable) {
                progressActiviity.showContent()
                Log.d("error", t.message.toString())
            }
        })
    }
    private fun initalizedAdapter(youthListResponse: YouthListResponse) {
        recyclerChatUser.layoutManager=llm
        adapter= ChatUserListingAdapter(this, youthListResponse.youthList as ArrayList<Youth>)
        recyclerChatUser.adapter=adapter
        adapter.notifyDataSetChanged()
    }

    private fun getCategoryData() {
        progressActiviity.showLoading()
        if(mUserManager.category!=null){
          //  layout.progressActiviity.showContent()
            Picasso.with(this@ChatActivity).load(mUserManager.category.subCategoryImg)
                .into(imgSubCat)
            txtPartentCat.text = mUserManager.category.parentCategory
            txtSubCat.text = mUserManager.category.category
            txtSubCatAttempt.text =
                "+" + mUserManager.category.attempts + " Students Attempted"
        }
        else {
            val cal = prepService.getSubCatDetails(mUserManager.category.catid)
            cal.enqueue(object : Callback<SubCatDetailsItem> {
                override fun onResponse(
                    call: Call<SubCatDetailsItem>,
                    response: Response<SubCatDetailsItem>
                ) {
                    if (response.isSuccessful && response.code() == 200) {
                //        layout.progressActiviity.showContent()
                        subCatDetailsItem = response.body()!!
                        Picasso.with(this@ChatActivity).load(subCatDetailsItem?.subCategoryImg)
                            .into(imgSubCat)
                        txtPartentCat.text = subCatDetailsItem?.parentCategory
                        txtSubCat.text = subCatDetailsItem?.subCategory
                        txtSubCatAttempt.text =
                            "+" + subCatDetailsItem?.aspirants + " Students Attempted"
                    } else {
                     //   layout.progressActiviity.showContent()
                        Toast.makeText(
                            self,
                            "Something went wrong,please try again...",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<SubCatDetailsItem>, t: Throwable) {
                    //layout.progressActiviity.showContent()
                    Log.d("error", t.message.toString())
                }
            })
        }
    }

    private fun click_event() {
       txtChat.setOnClickListener {
           cardCat.visibility= View.GONE
           recyclerChatUser.visibility=View.VISIBLE
           progressActiviity.showLoading()
           txtTitle.text="Student attempted for"
           txtSubCatName.text=  mUserManager.category.category
           getUserList(1)
       }
        btnBack.setOnClickListener {
            finish()
        }
    }
}