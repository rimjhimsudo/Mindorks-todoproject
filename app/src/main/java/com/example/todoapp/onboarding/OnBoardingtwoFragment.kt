package com.example.todoapp.onboarding

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.todoapp.R


//just like adapter were used for showing data on recyclerview(view)
//same way for viewpager =view and  data =fragments and now we want adapter to show data
class OnBoardingtwoFragment : Fragment() {
    lateinit var tv_next : TextView
    lateinit var tv_back : TextView
    lateinit var  onoptionClick : onOptionClick

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onoptionClick =context as onOptionClick
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_on_boardingtwo, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindviews(view) //whever u inflate something u need to use view
        clicklisteners()

    }

    private fun bindviews(view: View) {
        tv_next=view.findViewById(R.id.tv_next)
        tv_back=view.findViewById(R.id.tv_back)
    }

    private fun clicklisteners() {
        tv_next.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                onoptionClick.onOptionNext()
            }

        })
        tv_back.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                onoptionClick.onOptionBack()
            }

        })
    }
    interface onOptionClick {
        fun onOptionNext()
        fun onOptionBack()
    }
}
