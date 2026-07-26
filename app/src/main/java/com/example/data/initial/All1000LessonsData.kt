package com.example.data.initial

import com.example.data.model.LessonEntity

object All1000LessonsData {

    fun getAllLessons(): List<LessonEntity> {
        val all = mutableListOf<LessonEntity>()
        all.addAll(Lessons1To250.getLessons1To250())
        all.addAll(Lessons251To500.getLessons251To500())
        all.addAll(Lessons501To750.getLessons501To750())
        all.addAll(Lessons751To1000.getLessons751To1000())
        return all
    }
}
