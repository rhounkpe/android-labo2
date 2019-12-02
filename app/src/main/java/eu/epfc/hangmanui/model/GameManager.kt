package eu.epfc.hangmanui.model

import android.content.Context
import android.content.res.AssetManager
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import kotlin.random.Random

class GameManager {
    var word2Guess = ""
    var maskedWord = ""
    var playedLetters = mutableListOf<Char>()

    fun startNewGame(context: Context) {
        word2Guess = generateWord2Guess(context)
        playedLetters.clear()
        maskedWord = getMaskedWordToGuess(word2Guess, playedLetters)
    }

    private fun generateWord2Guess(context: Context): String {
        val assetManager: AssetManager = context.assets
        val inputStream: InputStream = assetManager.open("dictionary.txt")
        val reader = BufferedReader(InputStreamReader(inputStream))

        val wordList: MutableList<String> = mutableListOf()

        while (reader.ready()) {
            val line = reader.readLine()
            wordList.add(line)
        }

        val randomIndex = Random.nextInt(wordList.count() -1)

        return wordList[randomIndex].toLowerCase()
    }

    private fun getMaskedWordToGuess(wordToGuess : String, playedLetters : List<Char>) : String{

        var maskedWord = ""
        for (letter in wordToGuess){

            if (playedLetters.contains(letter)){
                maskedWord += letter
            }
            else{
                maskedWord += '*'
            }
        }
        return maskedWord
    }
}