package com.example.hospitalapplication

interface Callback {
    fun onResult(data: String)
}

//class Utils {
//    fun getImageBitmap(url: String, callback: (Any) -> Unit): Any? =
//        CoroutineScope(Dispatchers.IO).launch {
//            val aURL = URL(url)
//            val conn =
//                aURL.openConnection() as HttpURLConnection // Explicitly cast as HttpURLConnection for proper methods
//            conn.connect()
//
//            if (conn.responseCode == HttpURLConnection.HTTP_OK) { // Check for successful response
//                val inputStream = conn.inputStream
//                val bufferedInputStream = BufferedInputStream(inputStream)
//                val bitmap = BitmapFactory.decodeStream(bufferedInputStream)
//                bufferedInputStream.close()
//                inputStream.close()
//                withContext(Dispatchers.Main) {
//                    callback(bitmap) // Call the callback with the bitmap
//                }
//
//                return@launch
//            } else {
//                Log.e(
//                    "TAG",
//                    "Error getting bitmap: HTTP response code ${conn.responseCode}"
//                ) // Use descriptive error message
//
//                return@launch
//            }
//
//        }
//}