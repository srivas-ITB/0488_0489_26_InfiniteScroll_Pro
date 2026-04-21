package com.example.app26_InfiniteScroll_Pro.data.entity

class MyData {

    companion object {
        var counter : Int = 0
    }

    var id : Int
    var description: String

    constructor(description:String) {
       this.id = counter++
       this.description = description
    }
}