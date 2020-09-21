package com.example.madlevel2task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel2task2.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private val questions = arrayListOf<Question>()
    private val questionAdapter = QuestionAdapter(questions)
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initviews()
    }

    fun initviews(){
        binding.rvQuestions.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.rvQuestions.adapter = questionAdapter

        for(i in Question.QUESTIONS.indices) {
            questions.add(Question(Question.QUESTIONS[i], Question.ANSWERS[i]))
        }
        itemTouchHelper().attachToRecyclerView(binding.rvQuestions)
    }

    private fun itemTouchHelper(): ItemTouchHelper {
        val itemTouchHelperCallback =
            object :
                ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition
                    val selectQuestion: Question = questions[position]

                    if (direction == ItemTouchHelper.RIGHT && selectQuestion.answer ||
                        direction == ItemTouchHelper.LEFT && !selectQuestion.answer) {
                        questions.removeAt(position)
                    } else {
                        Snackbar.make(binding.rvQuestions, getString(R.string.wrong_answer), Snackbar.LENGTH_SHORT).show()
                    }
                    questionAdapter.notifyDataSetChanged()
                }

            }

        return ItemTouchHelper(itemTouchHelperCallback)
    }
}