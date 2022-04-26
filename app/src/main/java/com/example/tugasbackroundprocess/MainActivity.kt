package com.example.tugasbackroundprocess

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.tugasbackroundprocess.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.net.URL

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var handler: Handler




    // handler
    private var i = 1
    private var d = 5
    private val runnable : Runnable = object : Runnable{
        override fun run() {
            if(i <= 5){
                binding.tvHandler.text = " download in $i s"
                i++
                handler.postDelayed(this, (i*1000).toLong())
            }
            else{
                binding.tvHandler.text = "download finished"
            }

        }

    }


    //Asyntask
//    private class DownloadFilesTask: AsyncTask<URL, Int, Long>() {
//        override fun doInBackground(vararg urls:URL):Long {
//            val count = urls.size
//            val totalSize:Long = 0
//            for (i in 0 until count)
//            {
//                totalSize += Downloader.downloadFile(urls[i])
//                publishProgress(((i / count.toFloat()) * 100).toInt())
//                // Escape early if cancel() is called
//                if (isCancelled()) break
//            }
//            return totalSize
//        }
//        override fun onProgressUpdate(vararg progress:Int) {
//            super.onProgressUpdate(progress[0])
//        }
//        override fun onPostExecute(result:Long) {
//            super.onPostExecute(result)
//        }
//    }



    //Coroutine
    private fun launchCoroutine(time: Int){
    lifecycleScope.launch{
        for (i in 1..time){
            delay((i * 1000).toLong())
            binding.tvCoroutines.text = "download in $i s"
        }
        Toast.makeText(this@MainActivity, "dowload finished", Toast.LENGTH_SHORT).show()
    }
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        handler = Handler(mainLooper)
        binding.btnHandler.setOnClickListener {
            handler.post(runnable)
        }


        binding.btnCoroutines.setOnClickListener {
            launchCoroutine(5)
        }

        binding.btnAsyntask.setOnClickListener {
//        val download = DownloadFilesTask()
//            download.execute()
        }

    }

}