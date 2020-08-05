package com.example.healthysalad


class FragmentScheduler (private val currentState: String){
    private val fragmentFlow : List<String> = listOf("base","protein","crunchy","dressing","soft")

    fun getNextState() : String {
        val pos = fragmentFlow.indexOf(currentState)
        var next : String = fragmentFlow.last()
        if (pos < fragmentFlow.size - 1){ // position is 0-indexed while size is count
            next = fragmentFlow[pos+1]
        }
        return next
    }

    fun getPrevState ()  : String{
        val pos = fragmentFlow.indexOf(currentState)
        var prev : String = fragmentFlow.first()
        if(pos > 0){
            prev = fragmentFlow[pos-1]
        }
        return prev
    }

    fun getLastState() : String {
        return fragmentFlow.last()
    }

    fun getFirstState() : String {
        return fragmentFlow.first()
    }

}
