package com.example.data.initial

import com.example.data.model.*

object InitialData {

    fun getInitialLessons(): List<LessonEntity> = All1000LessonsData.getAllLessons()

    fun getInitialDictionary(): List<DictionaryEntity> = listOf(
        DictionaryEntity(
            id = 1,
            word = "অখণ্ড",
            pronunciation = "ওখন্ডো",
            meaningBangla = "সম্পূর্ণ, যা ভাগ করা হয়নি, নিরবচ্ছিন্ন",
            meaningEnglish = "Undivided, Intact, Whole",
            partOfSpeech = "বিশেষণ",
            synonyms = "সম্পূর্ণ, অবিকল, অখণ্ডিত",
            antonyms = "খণ্ডি়ত, বিভক্ত",
            exampleSentence = "বাংলাদেশ একটি অখণ্ড স্বাধীন রাষ্ট্র।"
        ),
        DictionaryEntity(
            id = 2,
            word = "অভিধান",
            pronunciation = "ওভিধান্",
            meaningBangla = "শব্দকোষ, যেখানে শব্দের অর্থ ও উৎপত্তি সংকলিত থাকে",
            meaningEnglish = "Dictionary, Lexicon",
            partOfSpeech = "বিশেষ্য",
            synonyms = "শব্দকোষ, শব্দাবলী",
            antonyms = "-",
            exampleSentence = "প্রতিদিন অভিধান থেকে নতুন নতুন শব্দ শেখা উচিত।"
        ),
        DictionaryEntity(
            id = 3,
            word = "ব্যাকরণ",
            pronunciation = "ব্যাঁকরন্",
            meaningBangla = "যে শাস্ত্র পাঠ করলে ভাষাকে শুদ্ধভাবে লিখতে, পড়তে ও বলতে পারা যায়",
            meaningEnglish = "Grammar",
            partOfSpeech = "বিশেষ্য",
            synonyms = "ভাষা শাস্ত্র",
            antonyms = "-",
            exampleSentence = "বাংলা ব্যাকরণের প্রধান আলোচ্য বিষয় ৪টি।"
        ),
        DictionaryEntity(
            id = 4,
            word = "জিজ্ঞাসা",
            pronunciation = "জিগ্গ্যাঁশা",
            meaningBangla = "জানার ইচ্ছা, প্রশ্ন করা",
            meaningEnglish = "Inquiry, Curiosity, Query",
            partOfSpeech = "বিশেষ্য",
            synonyms = "প্রশ্ন, অনুসন্ধিৎসা",
            antonyms = "উপেক্ষা",
            exampleSentence = "বিজ্ঞানের মূল চালিকাশক্তি হলো মানুষের জিজ্ঞাসা।"
        ),
        DictionaryEntity(
            id = 5,
            word = "জিগীষা",
            pronunciation = "জিগিশা",
            meaningBangla = "জয় করার ইচ্ছা",
            meaningEnglish = "Desire to conquer or win",
            partOfSpeech = "বিশেষ্য",
            synonyms = "জয়াশা",
            antonyms = "আত্মসমর্পণ",
            exampleSentence = "পরীক্ষায় প্রথম হওয়ার জিগীষা তাকে কঠোর পরিশ্রমী করেছে।"
        ),
        DictionaryEntity(
            id = 6,
            word = "জিঘাংসা",
            pronunciation = "জিঘাংশা",
            meaningBangla = "হত্যা করার ইচ্ছা",
            meaningEnglish = "Desire to kill or harm",
            partOfSpeech = "বিশেষ্য",
            synonyms = "হিংসা, শত্রুতা",
            antonyms = "ক্ষমা, দয়া",
            exampleSentence = "মানুষের মনের জিঘাংসা দূর করাই শিক্ষার মূল উদ্দেশ্য।"
        ),
        DictionaryEntity(
            id = 7,
            word = "উপসর্গ",
            pronunciation = "উপোশোর্গো",
            meaningBangla = "যেসব অব্যয়সূচক শব্দাংশ শব্দের পূর্বে বসে নতুন অর্থ সৃষ্টি করে",
            meaningEnglish = "Prefix",
            partOfSpeech = "বিশেষ্য",
            synonyms = "আদিপ্রত্যয়",
            antonyms = "অনুসর্গ",
            exampleSentence = "উপসর্গের অর্থবাচকতা নেই কিন্তু অর্থদ্যোতকতা আছে।"
        ),
        DictionaryEntity(
            id = 8,
            word = "সমাস",
            pronunciation = "শোমাশ্",
            meaningBangla = "সংক্ষেপণ, সংক্ষেপ বা একাধিক পদের মিলন",
            meaningEnglish = "Compound Word",
            partOfSpeech = "বিশেষ্য",
            synonyms = "সংক্ষেপণ, একপদীকরণ",
            antonyms = "ব্যাসবাক্য",
            exampleSentence = "সমাস শব্দের অর্থ সংক্ষেপণ।"
        )
    )

    fun getInitialQuizQuestions(): List<QuizQuestionEntity> = listOf(
        QuizQuestionEntity(
            id = 1,
            category = "BCS Exam",
            questionType = "MCQ",
            question = "বাংলা ভাষার প্রধান দুটি রূপ কী কী?",
            optionA = "আঞ্চলিক ও প্রমিত",
            optionB = "লৈখিক ও মৌখিক",
            optionC = "সাধু ও চলিত",
            optionD = "উচ্চ ও নিম্ন",
            correctAnswerIndex = 1,
            correctAnswerText = "লৈখিক ও মৌখিক",
            explanation = "বাংলা ভাষার প্রধান দুটি রূপ হলো লৈখিক (লেখা) এবং মৌখিক (বলা)। আবার লৈখিক রূপের দুটি রীতি হলো সাধু ও চলিত।"
        ),
        QuizQuestionEntity(
            id = 2,
            category = "BCS Exam",
            questionType = "MCQ",
            question = "বাংলা ব্যাকরণ প্রথম কে রচনা করেন?",
            optionA = "উইলিয়াম কেরি",
            optionB = "ম্যানুয়েল দা আস্সুম্পসাঁউ",
            optionC = "রামমোহন রায়",
            optionD = "ডা. মুহম্মদ শহীদুল্লাহ",
            correctAnswerIndex = 1,
            correctAnswerText = "ম্যানুয়েল দা আস্সুম্পসাঁউ",
            explanation = "১৭৪৩ সালে পর্তুগালের লিসবন থেকে প্রকাশিত 'Vocabolario em idioma Bengalla, e Portuguez' গ্রন্থটির রচিয়তা ম্যানুয়েল দা আস্সুম্পসাঁউ।"
        ),
        QuizQuestionEntity(
            id = 3,
            category = "SSC Exam",
            questionType = "MCQ",
            question = "'সমাস' শব্দের অর্থ কী?",
            optionA = "সংক্ষেপণ",
            optionB = "সম্প্রসারণ",
            optionC = "সংযোজন",
            optionD = "বিয়োজন",
            correctAnswerIndex = 0,
            correctAnswerText = "সংক্ষেপণ",
            explanation = "সমাস শব্দের অর্থ সংক্ষেপণ, মিলন বা একাধিক পদের একপদীকরণ।"
        ),
        QuizQuestionEntity(
            id = 4,
            category = "HSC Exam",
            questionType = "MCQ",
            question = "কোনটি খাঁটি বাংলা উপসর্গ?",
            optionA = "অঘ",
            optionB = "প্র",
            optionC = "পরা",
            optionD = "বে",
            correctAnswerIndex = 0,
            correctAnswerText = "অঘ",
            explanation = "খাঁটি বাংলা উপসর্গ ২১টি (যেমন: অ, অঘা, অজ, অনা, আ, কড়, কু, নি, পাতি, রাম...)। 'প্র' ও 'পরা' হলো তৎসম উপসর্গ।"
        ),
        QuizQuestionEntity(
            id = 5,
            category = "Daily Quiz",
            questionType = "MCQ",
            question = "'চৌরাস্তা' কোন সমাসের উদাহরণ?",
            optionA = "দ্বন্দ্ব সমাস",
            optionB = "দ্বিগু সমাস",
            optionC = "বহুব্রীহি সমাস",
            optionD = "অব্যয়ীভাব সমাস",
            correctAnswerIndex = 1,
            correctAnswerText = "দ্বিগু সমাস",
            explanation = "চৌ (চার) রাস্তার সমাহার = চৌরাস্তা। পূর্বপদে সংখ্যাবাচক শব্দ এবং পরপদে সমাহার বোঝালে দ্বিগু সমাস হয়।"
        ),
        QuizQuestionEntity(
            id = 6,
            category = "University Exam",
            questionType = "MCQ",
            question = "বাংলা বর্ণমালায় মাত্রাছাড়া বর্ণ কয়টি?",
            optionA = "৮টি",
            optionB = "১০টি",
            optionC = "৭টি",
            optionD = "৯টি",
            correctAnswerIndex = 1,
            correctAnswerText = "১০টি",
            explanation = "বাংলা বর্ণমালায় পূর্ণমাত্রা ৩৮টি, অর্ধমাত্রা ৮টি এবং মাত্রাছাড়া ১০টি (এ, ঐ, ও, ঔ, ঙ, ঞ, ণ, ং, ঃ, ঁ)।"
        ),
        QuizQuestionEntity(
            id = 7,
            category = "Bank Job",
            questionType = "MCQ",
            question = "চর্যাপদ কোন ছন্দে রচিত?",
            optionA = "অক্ষরবৃত্ত",
            optionB = "মাত্রাবৃত্ত",
            optionC = "স্বরবৃত্ত",
            optionD = "মুক্তক",
            correctAnswerIndex = 1,
            correctAnswerText = "মাত্রাবৃত্ত",
            explanation = "চর্যাপদের পদগুলো প্রাচীন মাত্রাবৃত্ত বা পাদাকুলক ছন্দে রচিত।"
        ),
        QuizQuestionEntity(
            id = 8,
            category = "Daily Quiz",
            questionType = "SPELLING_CORRECTION",
            question = "নিচের কোনটি শুদ্ধ বানান?",
            optionA = "সমীচিন",
            optionB = "সমীচীন",
            optionC = "সমিচীন",
            optionD = "সমিচিন",
            correctAnswerIndex = 1,
            correctAnswerText = "সমীচীন",
            explanation = "শুদ্ধ বানান হলো 'সমীচীন' (স + ম + ঈ-কার + চ + ঈ-কার + ন)। দুটিই দীর্ঘ ঈ-কার।"
        )
    )

    fun getInitialFlashcards(): List<FlashcardEntity> = listOf(
        FlashcardEntity(
            id = 1,
            category = "Grammar Rules",
            frontText = "উপসর্গের নিজস্ব কী নেই?",
            backText = "অর্থবাচকতা (উপসর্গের অর্থবাচকতা নেই, কিন্তু অর্থদ্যোতকতা বা নতুন অর্থ তৈরির ক্ষমতা আছে)।",
            hint = "অর্থ সম্পর্কিত"
        ),
        FlashcardEntity(
            id = 2,
            category = "Grammar Rules",
            frontText = "সমাস মূলত কয় প্রকার?",
            backText = "৬ প্রকার (দ্বন্দ্ব, তৎপুরুষ, কর্মধারয়, বহুব্রীহি, দ্বিগু, অব্যয়ীভাব)।",
            hint = "প্রধান প্রকারভেদ"
        ),
        FlashcardEntity(
            id = 3,
            category = "Vocabulary",
            frontText = "'জিগীষা' শব্দের অর্থ কী?",
            backText = "জয় করার ইচ্ছা।",
            hint = "এক কথায় প্রকাশ"
        ),
        FlashcardEntity(
            id = 4,
            category = "Idioms",
            frontText = "'আকাশ কুসুম' বাগধারার অর্থ কী?",
            backText = "অসম্ভব কল্পনা।",
            hint = "বাগধারা"
        ),
        FlashcardEntity(
            id = 5,
            category = "Vocabulary",
            frontText = "'সৌম্য' শব্দের বিপরীত শব্দ কী?",
            backText = "উগ্র।",
            hint = "বিপরীত শব্দ"
        )
    )

    fun getInitialAchievements(): List<AchievementEntity> = listOf(
        AchievementEntity(
            id = "first_lesson",
            title = "প্রথম পদক্ষেপ",
            description = "প্রথম ব্যাকরণ অধ্যায় সম্পন্ন করুন",
            badgeType = "BRONZE",
            isUnlocked = true,
            requiredCount = 1,
            currentCount = 1
        ),
        AchievementEntity(
            id = "quiz_master",
            title = "কুইজ মাস্টার",
            description = "১০টি কুইজে অংশগ্রহণ করুন",
            badgeType = "SILVER",
            isUnlocked = true,
            requiredCount = 10,
            currentCount = 12
        ),
        AchievementEntity(
            id = "streak_30",
            title = "ধারাবাহিক শিক্ষানবিস",
            description = "৩০ দিন নিয়মিত পাঠ গ্রহণ করুন",
            badgeType = "GOLD",
            isUnlocked = false,
            requiredCount = 30,
            currentCount = 5
        ),
        AchievementEntity(
            id = "lessons_100",
            title = "শততম অধ্যায় জয়ী",
            description = "১০০টি বাংলা ব্যাকরণ অধ্যায় সম্পন্ন করুন",
            badgeType = "DIAMOND",
            isUnlocked = false,
            requiredCount = 100,
            currentCount = 100
        )
    )

    fun getInitialExamples(): List<ExampleEntity> = listOf(
        ExampleEntity(
            id = 1,
            lessonId = 1,
            category = "বাংলা ভাষার পরিচয়",
            subcategory = "ভাষাতত্ত্ব ও ইতিহাস",
            topic = "বিশেষ্য পদ",
            example = "ঢাকা বাংলাদেশের রাজধানী।",
            type = "grammar_analysis",
            analysis = "'ঢাকা' = স্থানবাচক বিশেষ্য; 'বাংলাদেশ' = স্থানবাচক বিশেষ্য; 'রাজধানী' = গুণ/সংজ্ঞা পদ।",
            grammarPoint = "নামবাচক/সংজ্ঞাবাচক বিশেষ্য (Proper Noun)",
            grammarRule = "কোনো নির্দিষ্ট স্থান, নদী বা দেশের নাম বোঝালে সংজ্ঞাবাচক বিশেষ্য হয়।",
            explanation = "কোনো স্থান, দেশ বা নগরীর নির্দিষ্ট নাম বোঝালে তা সংজ্ঞাবাচক বিশেষ্য হয়।",
            difficulty = "EASY",
            examLevel = "SSC",
            tags = "বিশেষ্য,স্থানবাচক,ঢাকা",
            sourceType = "STANDARD",
            viewCount = 125,
            orderIndex = 1
        ),
        ExampleEntity(
            id = 2,
            lessonId = 1,
            category = "বর্ণতত্ত্ব",
            subcategory = "স্বরবর্ণ ও ব্যঞ্জনবর্ণ",
            topic = "অঘোষ ও ঘোষ ধ্বনি",
            example = "ক, খ (অঘোষ) ➔ গ, ঘ (ঘোষ)",
            type = "grammar_analysis",
            analysis = "ক, খ উচ্চারণে স্বরতন্ত্রী অনুরণিত হয় না (অঘোষ); গ, ঘ উচ্চারণে অনুরণিত হয় (ঘোষ)।",
            grammarPoint = "বর্গীয় ধ্বনির শ্রেণীবিন্যাস",
            grammarRule = "বর্গের ১ম ও ২য় ধ্বনি অঘোষ, ৩য় ও ৪র্থ ধ্বনি ঘোষ।",
            explanation = "ধ্বনি উচ্চারণের সময় স্বরতন্ত্রীর অনুরণন বা কম্পনের ওপর ভিত্তি করে অঘোষ ও ঘোষ ভাগ করা হয়।",
            difficulty = "EASY",
            examLevel = "SSC",
            tags = "বর্ণতত্ত্ব,ঘোষ,অঘোষ",
            sourceType = "STANDARD",
            viewCount = 98,
            orderIndex = 2
        ),
        ExampleEntity(
            id = 3,
            lessonId = 1,
            category = "ধ্বনিতত্ত্ব",
            subcategory = "ধ্বনি পরিবর্তন",
            topic = "সমিভবন ও সমীভবন",
            example = "পদ্ম ➔ পদ্দ (আত্তীকরণ)",
            type = "grammar_analysis",
            analysis = "'দ্ম' যুক্তব্যঞ্জনে 'ম' ধ্বনিটি 'দ' ধ্বনিতে রূপান্তরিত হয়ে সমতা লাভ করেছে।",
            grammarPoint = "সমীভবন (Assimilation)",
            grammarRule = "শব্দমধ্যস্থ দুটি ভিন্ন ব্যঞ্জনের একটি অপরটির প্রভাবে সমতা লাভ করলে তাকে সমীভবন বলে।",
            explanation = "সংস্কৃত 'পদ্ম' প্রাকৃত ও আধুনিক বাংলায় 'পদ্দ' বা 'পদ্মো' উচ্চারিত হয়।",
            difficulty = "MEDIUM",
            examLevel = "HSC",
            tags = "ধ্বনিতত্ত্ব,ধ্বনি পরিবর্তন,সমীভবন",
            sourceType = "STANDARD",
            viewCount = 142,
            orderIndex = 3
        ),
        ExampleEntity(
            id = 4,
            lessonId = 1,
            category = "বানান ও বানান শুদ্ধি",
            subcategory = "ণ-ত্ব ও ষ-ত্ব বিধান",
            topic = "শুদ্ধ বানান",
            example = "❌ সে স্কুল যাই। ➔ ✅ সে স্কুলে যায়।",
            type = "correct_incorrect",
            analysis = "অশুদ্ধ: 'যাই' (১ম পুরুষ); শুদ্ধ: 'যায়' (৩য় পুরুষ/নাম পুরুষ)।",
            grammarPoint = "পুরুষ ও ক্রিয়াপদের সঙ্গতি",
            grammarRule = "কর্তার পুরুষ অনুসারে ক্রিয়াপদের রূপ পরিবর্তন করতে হয়।",
            explanation = "কর্তা 'সে' (৩য় পুরুষ) হওয়ায় ক্রিয়াপদ 'যায়' হবে, 'যাই' নয়।",
            difficulty = "MEDIUM",
            examLevel = "BCS",
            tags = "বানান,পুরুষ,ক্রিয়াপদ",
            sourceType = "EXAM",
            viewCount = 230,
            orderIndex = 4
        ),
        ExampleEntity(
            id = 5,
            lessonId = 1,
            category = "কারক",
            subcategory = "কারক ও বিভক্তি প্রয়োগ",
            topic = "কর্তৃকারক",
            example = "পাগলে কি না বলে, ছাগলে কি না খায়।",
            type = "exam",
            analysis = "'পাগলে' = কর্তায় ৭মী বিভক্তি (পাগল + এ); 'ছাগলে' = কর্তায় ৭মী বিভক্তি।",
            grammarPoint = "কর্তৃকারকে ৭মী (এ) বিভক্তি",
            grammarRule = "যে বাক্যস্থিত ক্রিয়া সম্পাদন করে তাকে কর্তিকারক বলে।",
            explanation = "ক্রিয়ার সম্পাদনকারী পাগলে ও ছাগলে, তাই উভয়ই প্রথমা বা সপ্তমী বিভক্তিতেৃ কারক।",
            difficulty = "HARD",
            examLevel = "BCS",
            tags = "কারক,বিভক্তি,প্রবাদ",
            sourceType = "LITERATURE",
            viewCount = 310,
            orderIndex = 5
        ),
        ExampleEntity(
            id = 6,
            lessonId = 1,
            category = "সমাস",
            subcategory = "দ্বন্দ ও বহুব্রীহি সমাস",
            topic = "দ্বন্দ্ব সমাস",
            example = "মা-বাবা (মা ও বাবা)",
            type = "normal",
            analysis = "পূর্বপদ 'মা' এবং পরপদ 'বাবা' উভয় পদের অর্থই প্রধান।",
            grammarPoint = "মিলনার্থক দ্বন্দ্ব সমাস",
            grammarRule = "যে সমাসে প্রত্যেকটি সমস্যমান পদের অর্থের প্রাধান্য থাকে তাকে দ্বন্দ্ব সমাস বলে।",
            explanation = "উভয় পদের অর্থ প্রাধান্য পেয়ে দ্বন্দ্ব সমাস গঠিত হয়েছে।",
            difficulty = "EASY",
            examLevel = "ADMISSION",
            tags = "সমাস,দ্বন্দ্ব,মা-বাবা",
            sourceType = "STANDARD",
            viewCount = 180,
            orderIndex = 6
        ),
        ExampleEntity(
            id = 7,
            lessonId = 1,
            category = "উপসর্গ",
            subcategory = "বাংলা ও বিদেশী উপসর্গ",
            topic = "খাঁটি বাংলা উপসর্গ",
            example = "অঘারাম, অঝোর, অনাচার",
            type = "practice",
            analysis = "'অ' এবং 'অনা' খাঁটি বাংলা উপসর্গ হিসেবে শব্দের পূর্বে বসে ভিন্ন অর্থ প্রকাশ করেছে।",
            grammarPoint = "খাঁটি বাংলা ২১টি উপসর্গ",
            grammarRule = "উপসর্গের নিজস্ব অর্থবাচকতা নেই, কিন্তু অর্থদ্যোতকতা আছে।",
            explanation = "উপসর্গের নিজস্ব অর্থ নেই কিন্তু নতুন অর্থ সৃষ্টির ক্ষমতা আছে।",
            difficulty = "MEDIUM",
            examLevel = "JOB",
            tags = "উপসর্গ,অনাচার,অঝোর",
            sourceType = "STANDARD",
            viewCount = 95,
            orderIndex = 7
        ),
        ExampleEntity(
            id = 8,
            lessonId = 1,
            category = "সন্ধি",
            subcategory = "স্বরে স্বরসন্ধি",
            topic = "স্বরসন্ধি",
            example = "বিদ্যা + আলয় = বিদ্যালয়",
            type = "grammar_analysis",
            analysis = "বিদ্যা (আ) + আলয় (আ) = বিদ্যালয় (আ)",
            grammarPoint = "আ + আ = আ এর নিয়ম",
            grammarRule = "অ-কার কিংবা আ-কারের পর অ-কার কিংবা আ-কার থাকলে উভয়ে মিলে আ-কার হয়।",
            explanation = "দুটি স্বরধ্বনি পাশাপাশি এসে যুক্ত হয়ে স্বরসন্ধি গঠিত হয়েছে।",
            difficulty = "EASY",
            examLevel = "SSC",
            tags = "সন্ধি,স্বরসন্ধি,বিদ্যালয়",
            sourceType = "STANDARD",
            viewCount = 410,
            orderIndex = 8
        ),
        ExampleEntity(
            id = 9,
            lessonId = 1,
            category = "বাগধারা",
            subcategory = "বিশেষ অর্থবাচক বাক্যাংশ",
            topic = "মুখ্য অর্থ ও গৌণ অর্থ",
            example = "আকাশ কুসুম ➔ অসম্ভব কল্পনা",
            type = "normal",
            analysis = "'আকাশে কুসুম বা ফুল ফোটা অসম্ভব' — তাই এর অর্থ অবাস্তব বা অসম্ভব কল্পনা।",
            grammarPoint = "রূপক ও ভাবাত্মক বাগধারা",
            grammarRule = "শব্দের আক্ষরিক অর্থ অতিক্রম করে বিশেষ বিশিষ্ট অর্থ প্রকাশ করে।",
            explanation = "চাকরির পরীক্ষায় বহুল জিজ্ঞাসিত বিখ্যাত বাগধারা।",
            difficulty = "MEDIUM",
            examLevel = "BCS",
            tags = "বাগধারা,আকাশ কুসুম,কল্পনা",
            sourceType = "EXAM",
            viewCount = 285,
            orderIndex = 9
        ),
        ExampleEntity(
            id = 10,
            lessonId = 1,
            category = "এক কথায় প্রকাশ",
            subcategory = "সংকোচন",
            topic = "বাক্য সংকোচন",
            example = "যা পূর্বে দেখা যায়নি ➔ অদৃষ্টপূর্ব",
            type = "exam",
            analysis = "অ (না) + দৃষ্ট (দেখা) + পূর্ব (আগে) = অদৃষ্টপূর্ব।",
            grammarPoint = "উপপদ বা প্রত্যয়ঘটিত বাক্য সংকোচন",
            grammarRule = "একটি পূর্ণাঙ্গ বাক্যাংশকে একটি পদে পরিণত করাকে বাক্য সংকোচন বলে।",
            explanation = "বিসিএস ও ব্যাংক নিয়োগ পরীক্ষায় ঘনঘন আসে।",
            difficulty = "HARD",
            examLevel = "JOB",
            tags = "এক কথায় প্রকাশ,বাক্য সংকোচন,অদৃষ্টপূর্ব",
            sourceType = "EXAM",
            viewCount = 340,
            orderIndex = 10
        )
    )
}
