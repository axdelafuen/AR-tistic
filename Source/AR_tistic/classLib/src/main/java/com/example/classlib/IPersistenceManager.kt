package com.example.classlib

import java.io.Serializable

interface IPersistenceManager : Serializable {
    // USER METHODS
    fun getUserById(idUser:Int):User?
    fun getuserByEmail(content:String):User?
    fun createUser(usr:User)
    fun updateUser(id:Int,usr:User)
    fun deleteUser(id:Int)
    fun findUserByLogPswd(log:String, psswrd:String):User?
    fun getLikes(id:Int):Int
    fun getFollowers(id:Int): HashMap<Int, User>
    fun patternRecognitionUsers(pattern: String): ArrayList<User>
    fun getNbFollowers(id:Int):Int
    // DRAWS METHODS
    fun getDrawById(idDraw: Int): Draw?
    fun deleteDraw(id:Int)
    fun updateDraw(d: Draw)
    fun createDraw(draw: Draw)
    fun getDrawFromUser(userId:Int):HashMap<Int,Draw>?
    fun getCollaborated(idDraw: Int): HashMap<Int,com.example.classlib.User>
    fun getDrawsInInterestPoint(idInterestPoint: Int):ArrayList<Draw>
    // INTERESTPOINTS METHODS
    fun getInterestPointById(idIP: Int): InterestPoint
    fun getInterestPointsByRange(rayon: Double, latitude: Double, longitude: Double): HashMap<Int, InterestPoint>
    fun userFollows(idUser: Int, idUserCible: Int)
    fun userLikesDrawing(idUser: Int, idDraw: Int)
    fun createInterestPoint(ip: InterestPoint)
    fun getDrawsByInterestPoint(idIP: Int): HashMap<Int, Draw>
    fun getAllUsers(): ArrayList<User>
}