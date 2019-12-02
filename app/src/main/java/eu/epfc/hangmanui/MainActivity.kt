package eu.epfc.hangmanui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import eu.epfc.hangmanui.model.GameManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val gameManager = GameManager()
    private lateinit var rectangleViews : MutableList<View>
    private var tryCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rectangleViews = mutableListOf()
        rectangleViews.add(findViewById(R.id.rectangle1))
        rectangleViews.add(findViewById(R.id.rectangle2))
        rectangleViews.add(findViewById(R.id.rectangle3))
        rectangleViews.add(findViewById(R.id.rectangle4))
        rectangleViews.add(findViewById(R.id.rectangle5))
        rectangleViews.add(findViewById(R.id.rectangle6))

        for (indicatorView in rectangleViews){
            indicatorView.alpha = 0.2f
        }

        gameManager.startNewGame(this)
        val maskedWordText: TextView = findViewById(R.id.maskedWord)
        maskedWordText.text = gameManager.maskedWord

        // Update the views representing tryCount
        updateTryCountViews(gameManager.tryCount)
    }

    fun onClickOkButton(view: View) {
        val editText: EditText = findViewById(R.id.editText)
        val stringEditText = editText.text

        // If the user has entered a letter
        if (stringEditText.isNotEmpty()) {
            gameManager.playLetter(stringEditText[0])
            updateTryCountViews(gameManager.tryCount)
        }
    }

    private fun updateTryCountViews(tryCount: Int){

        // first rectangles (until tryCount) are opaque
        for (i in 0 until tryCount){
            rectangleViews[i].alpha = 1f
        }
        // the rest of the rectangles are semi-transparent
        for (i in tryCount until rectangleViews.size){
            rectangleViews[i].alpha = 0.2f
        }
    }
}
