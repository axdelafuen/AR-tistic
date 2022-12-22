package com.example.clientapi

import com.example.classlib.*
import com.example.classlib.Collection
import com.fasterxml.jackson.databind.JsonDeserializer
import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import kotlin.reflect.cast

@Suppress("NewApi")
class ClientAPI:IPersistenceManager,java.io.Serializable{

    val url = "https://codefirst.iut.uca.fr/containers/api-artistic-axelde_la_fuente/" // for release
    //val url = "http://localhost:1705/" //for local

    fun loadData(): Collection? {
        try{
            return Gson().fromJson(get(URL(url+"loadData")),Collection::class.java)
        }catch(e:Exception){
            return null
        }
    }

    // USER

    override fun getUserById(idUser: Int): User? {
        try{
            return Gson().fromJson(get(URL(url+"users/"+idUser.toString())),User::class.java)
        }catch(e: Exception){
            return null
        }
    }

    override fun getuserByEmail(content: String): User? {
        try{
            return Gson().fromJson(get(URL(url+"users/email/"+content)),User::class.java)
        }catch(e:Exception){
            return null
        }
    }

    override fun createUser(usr: User) {
        post(URL(url+"users"),Gson().toJson(usr))
    }

    override fun updateUser(id: Int, usr: User) {
        put(URL(url+"users"+id as String),Gson().toJson(usr))
    }

    override fun deleteUser(id: Int) {
        delete(URL(url+"users"+id as String))
    }

    override fun findUserByLogPswd(log: String, psswrd: String): User? {
        try{
            return Gson().fromJson(get(URL(url+"user/pwd/"+log+"/"+psswrd+"/")),User::class.java)
        }catch(e:Exception){
            return null
        }
    }

    override fun getLikes(id: Int): Int {
        return 1
    }

    override fun getFollowers(id: Int): HashMap<Int, User> {
        TODO("Not yet implemented")
    }

    override fun patternRecognitionUsers(pattern: String): Array<User> {
        try{
            var users = Gson().fromJson(get(URL(url+"users/findByPattern/"+pattern)),java.util.HashMap::class.java)
            for(user in users){
                //println(Gson().fromJson(user.value.toString(),User::class.java).name)
            }
            return arrayOf()
        }catch(e:Exception){
            println(e)
            return arrayOf()
        }
    }

    // DRAWS

    override fun getDrawById(idDraw: Int): Draw? {
        try{
            return Gson().fromJson(get(URL(url+"draws/"+idDraw.toString())),Draw::class.java)
        }catch(e: Exception) {
            return null
        }
    }

    override fun deleteDraw(id: Int) {
        delete(URL(url+"draws"+id as String))
    }

    override fun updateDraw(d: Draw) {
        put(URL(url+"draws"),Gson().toJson(d))
    }

    override fun createDraw(draw: Draw) {
        post(URL(url+"draws"),Gson().toJson(draw))
    }

    override fun getDrawFromUser(userId: Int): HashMap<Int, Draw>? {
        try{
            return Gson().fromJson(get(URL(url+"draws/fromUser/"+userId)),HashMap<Int,Draw>()::class.java)
        }catch(e: Exception) {
            return null
        }


    }

    override fun getCollaborated(idDraw: Int): HashMap<Int, User> {
        TODO("Not yet implemented")
    }

    override fun getInterestPointById(idIP: Int): InterestPoint {
        TODO("Not yet implemented")
    }

    override fun getInterestPointsByRange(rayon: Double, latitude: Double, longitude: Double): HashMap<Int, InterestPoint> {
        TODO("Not yet implemented")
    }
    /// http requests :

    @Suppress("NewApi")
    private fun get(url: URL):String{
        lateinit var jsonStr: String
        with(url.openConnection() as HttpURLConnection){
            requestMethod = "GET"
            inputStream.bufferedReader().use {
                it.lines().forEach { line -> jsonStr = line }
            }
        }
        return jsonStr
    }
    private fun delete(url: URL){
        with(url.openConnection() as HttpURLConnection){
            requestMethod  = "DELETE"
            this.setRequestProperty("Content-Type", "text/plain")
            doInput = true
            doOutput = false

            println("Code de retour du delete (204 = succes) : "+this.responseCode)
        }
    }
    private fun post(url: URL, data:String){
        with(url.openConnection() as HttpURLConnection){
            requestMethod = "POST"
            doOutput = true
            this.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
            this.setRequestProperty("Content-Length", data.length.toString())

            val wr = OutputStreamWriter(this.outputStream)
            wr.write(data)
            wr.flush()

            BufferedReader(InputStreamReader(inputStream)).use {
                val response = StringBuffer()

                var inputLine = it.readLine()
                while (inputLine != null) {
                    response.append(inputLine)
                    inputLine = it.readLine()
                }
                //println("Response : $response")
            }

        }
    }
    private fun put(url: URL, data:String) {
        with(url.openConnection() as HttpURLConnection) {
            requestMethod = "PUT"
            doOutput = true

            this.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
            this.setRequestProperty("Content-Length", data.length.toString())

            val wr = OutputStreamWriter(outputStream)
            wr.write(data)
            wr.flush()

            BufferedReader(InputStreamReader(inputStream)).use {
                val response = StringBuffer()

                var inputLine = it.readLine()
                while (inputLine != null) {
                    response.append(inputLine)
                    inputLine = it.readLine()
                }
                println("Response : $response")
            }
        }
    }

}