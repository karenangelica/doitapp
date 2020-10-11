//package com.example.doitapp.repository
//
//
//import com.example.doitapp.Task
//import com.example.doitapp.TaskDate
//import com.example.doitapp.TaskList
//
//
//class FakeTaskRepositoryImpl  {
//
//     fun getListOfDates(): List<TaskDate> {
//        val fakeTask = TaskList("Make Task", false )
//        val listOfTaskCard = Task("Tuesday, September 22, 2020",false, List(20) {fakeTask})
//        return List(5) {listOfTaskCard}
//    }
//
//     fun getListOfTask(): List<TaskList> {
//        val listOfTask = TaskList("Make Task", false)
//        return List(5) {listOfTask}
//    }
//
//     fun insertDate(date: String) {
//            Task(date)
//    }
//
//
//
//}