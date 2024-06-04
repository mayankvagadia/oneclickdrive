package com.oneclickdrive.practicaldemo.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val _result = MutableLiveData<String>()
    val result: LiveData<String> get() = _result

    fun calculate(list1: String, list2: String, list3: String) {
        val set1 = list1.split(",").map { it.trim().toInt() }.toSet()
        val set2 = list2.split(",").map { it.trim().toInt() }.toSet()
        val set3 = list3.split(",").map { it.trim().toInt() }.toSet()

        val intersection = set1.intersect(set2).intersect(set3)
        val union = set1.union(set2).union(set3)
        val highestNumber = union.maxOrNull()

        _result.value = """
            Intersection: ${intersection.joinToString(", ")}
            Union: ${union.joinToString(", ")}
            Highest Number: $highestNumber
        """.trimIndent()
    }
}
