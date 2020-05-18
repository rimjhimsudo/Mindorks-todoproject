package com.example.todoapp.onboarding

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.todoapp.R


class OnBoardingOneFragment : Fragment() {
    lateinit var tv_next : TextView
    lateinit var  onnextClick: onNextClick

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onnextClick=context as onNextClick

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_on_boarding_one, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindviews(view)
    }

    private fun bindviews(view: View) {
        tv_next=view.findViewById(R.id.tv_next)
        clicklisteners()
    }

    private fun clicklisteners() {
        tv_next.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                onnextClick.onClick()
            }

        })
    }
    interface onNextClick {
        fun onClick()
    }
}
