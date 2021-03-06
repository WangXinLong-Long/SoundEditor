package com.lkdont.soundeditor.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lkdont.soundeditor.R
import com.lkdont.soundeditor.widget.OnItemClickListener
import kotlinx.android.synthetic.main.examples_list_frag.*
import org.greenrobot.eventbus.EventBus

/**
 * Examples List Page
 *
 * Created by kidonliang on 2018/3/4.
 */
class ExamplesListFrag : Fragment() {

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.examples_list_frag, container, false)
        return view
    }

    private val examples = arrayOf(
            "Convertor",
            "Decode",
            "Encode",
            "Player"
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ctx = context ?: return
        content_rv.layoutManager = LinearLayoutManager(ctx)
        val adapter = ExamplesAdapter(ctx, examples)
        content_rv.adapter = adapter
        adapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(v: View, position: Int) {
                when (examples[position]) {
                    "Convertor" -> EventBus.getDefault().post(MainAct.FragmentEvent(ConvertorFrag()))
                    "Decode" -> EventBus.getDefault().post(MainAct.FragmentEvent(DecodeFrag()))
                    "Encode" -> EventBus.getDefault().post(MainAct.FragmentEvent(EncoderFrag()))
                    "Player" -> EventBus.getDefault().post(MainAct.FragmentEvent(PlayerFrag()))
                }
            }
        })
    }

    private class ExampleItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTv: TextView = itemView.findViewById(R.id.title_tv)
    }

    private class ExamplesAdapter(context: Context, val examples: Array<String>)
        : RecyclerView.Adapter<ExampleItemViewHolder>() {

        private val mInflater = LayoutInflater.from(context)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleItemViewHolder {
            val itemView = mInflater.inflate(R.layout.example_item, parent, false)
            return ExampleItemViewHolder(itemView)
        }

        override fun getItemCount(): Int {
            return examples.size
        }

        override fun onBindViewHolder(holder: ExampleItemViewHolder, position: Int) {
            holder.titleTv.text = examples[position]
            holder.itemView?.setOnClickListener {
                mOnItemClickListener?.onItemClick(holder.itemView, position)
            }
        }

        private var mOnItemClickListener: OnItemClickListener? = null

        fun setOnItemClickListener(listener: OnItemClickListener?) {
            mOnItemClickListener = listener
        }

    }

}