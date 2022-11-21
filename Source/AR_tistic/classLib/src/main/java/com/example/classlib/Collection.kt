package com.example.classlib

class Collection (
    var users:HashMap<Int,User>,
    var interestPoints:HashMap<Int,InterestPoint>,
    var draws:HashMap<Int,Draw>
    ):java.io.Serializable{
        init{
            this.users=users
            this.interestPoints = interestPoints
            this.draws = draws
        }
    }