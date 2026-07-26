package com.example.data.initial

import com.example.data.model.LessonEntity

object LessonBuilder {

    data class McqItem(
        val question: String,
        val optionA: String,
        val optionB: String,
        val optionC: String,
        val optionD: String,
        val correctOption: String,
        val explanation: String
    )

    fun buildLesson(
        id: Int,
        category: String,
        title: String,
        learningObjectives: String,
        definition: String,
        explanation: String,
        rulesAndExceptions: String,
        examples: List<String>,
        realLifeAndErrors: String,
        mnemonics: String,
        summary: String,
        mcqs: List<McqItem>,
        exercises: List<String>,
        nextChapterId: Int = id + 1
    ): LessonEntity {
        val formattedDefinition = "🎯 **শেখার লক্ষ্য (Learning Objectives):**\n$learningObjectives\n\n📚 **সংজ্ঞা (Definition):**\n$definition"

        val formattedExplanation = "📖 **বিস্তারিত ব্যাখ্যা (Detailed Explanation):**\n$explanation\n\n📝 **নিয়ম ও ব্যতিক্রম (Rules & Exceptions):**\n$rulesAndExceptions\n\n⚠️ **বাস্তব জীবনের ব্যবহার ও সাধারণ ভুল (Real Life Usage & Common Errors):**\n$realLifeAndErrors\n\n📋 **সারাংশ (Summary):**\n$summary"

        val formattedExamples = "💡 **১০-২০টি উদাহরণ (Examples):**\n" + examples.mapIndexed { idx, ex -> "${idx + 1}. $ex" }.joinToString("\n")

        val formattedMnemonics = "🧠 **মনে রাখার কৌশল (Mnemonics):**\n$mnemonics"

        val mcqSection = "❓ **১০-২০টি MCQ কুইজ, উত্তর ও ব্যাখ্যা (MCQs & Explanations):**\n" + mcqs.mapIndexed { idx, item ->
            "${idx + 1}. ${item.question}\n   ক) ${item.optionA}   খ) ${item.optionB}\n   গ) ${item.optionC}   ঘ) ${item.optionD}\n   ✅ উত্তর: ${item.correctOption} (${item.explanation})"
        }.joinToString("\n\n")

        val exerciseSection = "✍️ **৫-১০টি অনুশীলনী (Practice Exercises):**\n" + exercises.mapIndexed { idx, ex -> "${idx + 1}. $ex" }.joinToString("\n")

        val navigationSection = "🔗 **পরবর্তী অধ্যায় (Next Chapter):**\nঅধ্যায় $nextChapterId তে নেভিগেট করতে পরবর্তী বোতামটি চাপুন।"

        val tableChartHtml = "$mcqSection\n\n$exerciseSection\n\n$navigationSection"

        return LessonEntity(
            id = id,
            category = category,
            subtopic = "অধ্যায় $id: $title",
            title = title,
            definition = formattedDefinition,
            explanation = formattedExplanation,
            examples = formattedExamples,
            mnemonics = formattedMnemonics,
            tableChartHtml = tableChartHtml,
            isBookmarked = (id % 13 == 0)
        )
    }
}
