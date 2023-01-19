package com.example.stub

import com.example.classlib.*
import com.example.classlib.Collection
import java.util.concurrent.atomic.AtomicInteger
import kotlin.collections.HashMap

class  Stub:IPersistenceManager{
    var usersArray = loadUsersArray()

    fun loadUsersArray():ArrayList<User>{
        val res:ArrayList<User> = ArrayList()

        res.add(User(id = 0, name = "Alice", profilePicture = "./img/pp/Alice.jpg", email = "alice@alice.kt", password ="1234", birthDate = Date(1999,2,2) , subscribes = hashMapOf(), nbReport = 0 ))
        res.add( User(id = 1, name = "Fredo", profilePicture = "./img/pp/Fredo.jpg", email = "fred@fred.kt", password ="aaaa123", birthDate = Date(2003,1,1), subscribes = hashMapOf(), nbReport = 0 ))
        res.add(User(id = 5, name = "1", profilePicture = "./img/pp/Fredo.jpg", email = "2", password ="1", birthDate = Date(2003,1,1), subscribes = hashMapOf(), nbReport = 0 ))

        return res
    }

    var userHashMap=loadData().users
        get() {
            return field
        }
        set(value) {field=value}

    var intPtsHashMap=loadData().interestPoints
        get() {return  field}
        set(value) {field=value}

    var drawsHashMap=loadData().draws
        get() {
            return field
        }
        set(value) {field=value}

    var lastId:AtomicInteger= AtomicInteger(userHashMap.size-1)

    fun loadData():(com.example.classlib.Collection){
        var collec:com.example.classlib.Collection = Collection(loadUsers(),loadInterestPoints(),loadDraws())
        return collec
    }
    fun loadUsers():HashMap<Int,User>{

        return hashMapOf(
            0 to User(id = 0, name = "Alice", profilePicture = "./img/pp/Alice.jpg", email = "alice@alice.kt", password ="1234", birthDate = Date(1999,2,2) , subscribes = hashMapOf(), nbReport = 0 ),
            1 to User(id = 1, name = "Fredo", profilePicture = "./img/pp/Fredo.jpg", email = "fred@fred.kt", password ="aaaa123", birthDate = Date(2003,1,1), subscribes = hashMapOf(), nbReport = 0 ),
            2 to User(id = 2, name = "Patrick", profilePicture = "./img/pp/Fredo.jpg", email = "patrick@gmail.kt", password ="aaaa123", birthDate = Date(2003,1,1), subscribes = hashMapOf(), nbReport = 0 ),
            3 to User(id = 3, name = "Nina", profilePicture = "./img/pp/Fredo.jpg", email = "fred@fred.kt", password ="aaaa123", birthDate = Date(2003,1,1), subscribes = hashMapOf(), nbReport = 0 ),
            4 to User(id = 4, name = "Louis", profilePicture = "./img/pp/Fredo.jpg", email = "fred@fred.kt", password ="aaaa123", birthDate = Date(2003,1,1), subscribes = hashMapOf(), nbReport = 0 ),
            5 to User(id = 5, name = "1", profilePicture = "./img/pp/Fredo.jpg", email = "2", password ="1", birthDate = Date(2003,1,1), subscribes = hashMapOf(), nbReport = 0 ),
        )
    }
    fun loadInterestPoints():HashMap<Int,InterestPoint>{
        return  hashMapOf(
            0 to InterestPoint(id=0,name="IUT", desc="IUT de Clermont", latitude=1.0000, longitude=2.00000, picture="./img/intPoint/IUT.png"),
            1 to InterestPoint(id=1,name="MAISON", desc="IUT de Clermont", latitude=1.0000, longitude=2.00000, picture="./img/intPoint/IUT.png"),
            2 to InterestPoint(id=2,name="RUE", desc="IUT de Clermont", latitude=1.0000, longitude=2.00000, picture="./img/intPoint/IUT.png"),
        )
    }
    fun loadDraws():HashMap<Int,Draw>{
        return hashMapOf(
            0 to Draw(
                id =0,
                name ="Peinture bleu sur fond bleu", image = "./img/draw/0.jpg", interestPoint = hashMapOf(), creationDate = Date(1999,2,2), lifeTime = Time(24,10,10), authors = hashMapOf(), nbView = 60, nbReport = 0 ),
            1 to Draw(
                id =1,
                name ="bleu", image = "./img/draw/0.jpg", interestPoint = hashMapOf(), creationDate = Date(1999,2,2), lifeTime = Time(24,10,10), authors = hashMapOf(), nbView = 60, nbReport = 0 ),
            2 to Draw(
                id =2,
                name ="Peinture", image = "./img/draw/0.jpg", interestPoint = hashMapOf(), creationDate = Date(1999,2,2), lifeTime = Time(24,10,10), authors = hashMapOf(), nbView = 60, nbReport = 0 )
        )
    }
    fun loadDataIdx(idxUser:Int, nbUser:Int,idxPt:Int, nbPt:Int,idxDraw: Int, nbDraw:Int):(com.example.classlib.Collection){
        var collec:com.example.classlib.Collection = Collection(loadUsersIndex(idxUser,nbUser),loadInterestPointsIndex(idxPt,nbPt),loadDrawsIndex(idxDraw,nbDraw))
        return collec
    }
    fun loadUsersIndex(idx:Int, nb:Int):HashMap<Int,User>{// Return an hashmap with user in index position
        if(idx+nb>userHashMap.size)return hashMapOf()//test si idx > nb user
        val ret = HashMap<Int, User>()// hashmap retourné avec la tranche de valeur demandé
        var cpt=0
        while(cpt<nb){
            ret[cpt]=userHashMap.values.elementAt(cpt+idx)
            cpt++
        }
        return  ret
    }
    fun loadInterestPointsIndex(idx:Int, nb:Int):HashMap<Int,InterestPoint>{// Return of an hashmap with point of interest in index position
        if(idx+nb>intPtsHashMap.size)return hashMapOf()//test si idx > nb user
        val ret = HashMap<Int, InterestPoint>()// hashmap retourné avec la tranche de valeur demandé
        var cpt=0
        while(cpt<nb){
            ret[cpt]=intPtsHashMap.values.elementAt(cpt+idx)
            cpt++
        }
        return  ret
    }
    fun loadDrawsIndex(idx:Int, nb:Int):HashMap<Int,Draw>{// Return of an hashmap with draws in index position
        if(idx+nb>drawsHashMap.size)return hashMapOf()//test si idx > nb user
        val ret = HashMap<Int, Draw>()// hashmap retourné avec la tranche de valeur demandé
        var cpt=0
        while(cpt<nb){
            ret[cpt]=drawsHashMap.values.elementAt(cpt+idx)
            cpt++
        }
        return  ret
    }



    //USERS FUNCTIONS


    override fun getUserById(idUser:Int):User?{
        return userHashMap[idUser]
    }

    override fun getuserByEmail(content: String): User? {
        for(user in userHashMap.values){
            if(content.equals(user.email)||content.equals(user.name)){
                return user
            }
        }
        return null
    }
    override fun createUser(usr:User){// créé un nouveau
        val id=lastId.incrementAndGet()
        userHashMap.put(id,User(id,usr.name, usr.profilePicture, usr.email, usr.password, usr.birthDate, usr.subscribes, usr.nbReport))
    }
    override fun updateUser(id:Int, usr:User){// modify
        userHashMap.put(id,User(id,usr.name, usr.profilePicture, usr.email, usr.password, usr.birthDate, usr.subscribes, usr.nbReport))
    }
    override fun deleteUser(id:Int){
        userHashMap.remove(id)
    }

    override fun findUserByLogPswd(log: String, psswrd: String): User? {
        TODO("Not yet implemented")
    }

    override fun getLikes(id: Int): Int {
        TODO("Not yet implemented")
    }

    override fun getFollowers(id: Int): HashMap<Int, User> {
        TODO("Not yet implemented")
    }


    override fun patternRecognitionUsers(pattern: String): ArrayList<User> {
        TODO("Not yet implemented")
    }

    override fun getNbFollowers(id: Int): Int {
        return 232
    }

    //DRAWS FUNCTIONS

    override fun getInterestPointsByRange(
        rayon: Double,
        latitude: Double,
        longitude: Double
    ): HashMap<Int, InterestPoint> {
        TODO("Not yet implemented")
    }

    override fun userFollows(idUser: Int, idUserCible: Int) {
        TODO("Not yet implemented")
    }

    override fun userLikesDrawing(idUser: Int, idDraw: Int) {
        TODO("Not yet implemented")
    }

    override fun createInterestPoint(intPt:InterestPoint){// créé un nouveau
        val id=lastId.incrementAndGet()
        intPtsHashMap.put(id, InterestPoint(id,intPt.name,intPt.desc,intPt.latitude,intPt.longitude,intPt.picture))
    }

    override fun getDrawsByInterestPoint(idIP: Int): HashMap<Int, Draw> {
        TODO("Not yet implemented")
    }

    override fun getAllUsers(): ArrayList<User> {
        TODO("Not yet implemented")
    }

    fun updateInterestPoint(id:Int,intPt:InterestPoint){// modify
        intPtsHashMap.put(id, InterestPoint(id,intPt.name,intPt.desc,intPt.latitude,intPt.longitude,intPt.picture))
    }
    fun deleteInterestPoint(id:Int){
        intPtsHashMap.remove(id)
    }

    //DRAWS FUNCTIONS
    override fun getDrawById(idDraw:Int):Draw?{
        return drawsHashMap[idDraw]
    }

    override fun deleteDraw(id: Int) {
        TODO("Not yet implemented")
    }

    override fun updateDraw(d: Draw) {
        TODO("Not yet implemented")
    }

    override fun createDraw(draw: Draw) {
    }

    override fun getDrawFromUser(userId: Int): HashMap<Int, Draw>? {
        TODO("Not yet implemented")
    }

    override fun getCollaborated(idDraw: Int): HashMap<Int, User> {
        TODO("Not yet implemented")
    }

    override fun getDrawsInInterestPoint(idInterestPoint: Int): ArrayList<Draw> {
        var drawssss:ArrayList<Draw>  = ArrayList()
        drawssss.add(Draw(0,"Peinture bleu sur fond bleu", "./img/draw/0.jpg",  hashMapOf(),  Date(1999,2,2), Time(24,10,10), hashMapOf(), 60, 0 ))
        return drawssss
    }

    override fun getInterestPointById(idIP: Int): InterestPoint {
        return InterestPoint(id=0,name="IUT", desc="IUT de Clermont", latitude=1.0000, longitude=2.00000, picture="./img/intPoint/IUT.png")
    }


}
