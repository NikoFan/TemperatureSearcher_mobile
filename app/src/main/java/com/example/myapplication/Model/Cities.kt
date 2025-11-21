package com.example.myapplication.Model

data class Cities (
    val citiesList: Array<String> = arrayOf(
        // Столицы стран
        "London",           // Великобритания
        "Paris",            // Франция
        "Berlin",           // Германия
        "Rome",             // Италия
        "Madrid",           // Испания
        "Warsaw",           // Польша
        "Kyiv",             // Украина
        "Minsk",            // Беларусь
        "Washington",       // США
        "Ottawa",           // Канада
        "Tokyo",            // Япония
        "Beijing",          // Китай
        "New Delhi",        // Индия
        "Brasilia",         // Бразилия
        "Canberra",         // Австралия
        "Moscow",           // Москва
        "Saint Petersburg", // Санкт-Петербург
        "Novosibirsk",
        "Yekaterinburg",
        "Kazan",
        "Nizhny Novgorod",
        "Chelyabinsk",
        "Samara",
        "Omsk",
        "Rostov-on-Don",
        "Ufa",
        "Krasnoyarsk",
        "Voronezh",
        "Perm",
        "Volgograd"
    ),
    val citiesReNameMap: Map<String, String> = mapOf<String, String>(
        "London" to "Лондон",
        "Paris" to "Париж",
        "Berlin" to "Берлин",
        "Rome" to "Рим",
        "Madrid" to "Мадрид",
        "Warsaw" to "Варшава",
        "Kyiv" to "Киев",
        "Minsk" to "Минск",
        "Washington" to "Вашингтон",
        "Ottawa" to "Оттава",
        "Tokyo" to "Токио",
        "Beijing" to "Пекин",
        "New Delhi" to "Нью-Дели",
        "Brasília" to "Бразилиа",
        "Canberra" to "Канберра",
        "Moscow" to "Москва",
        "Saint Petersburg" to "Санкт-Петербург",
        "Novosibirsk" to "Новосибирск",
        "Ekaterinburg" to "Екатиринбург",
        "Kazan’" to "Казань",
        "Nizhny Novgorod" to "Нижний Новгород",
        "Chelyabinsk" to "Челябинск",
        "Samara Oblast" to "Самара",
        "Omsk" to "Омск",
        "Rostov-on-Don" to "Ростов-на-Дону",
        "Ufa" to "Уфа",
        "Krasnoyarsk" to "Красноярск",
        "Voronezh" to "Воронеж",
        "Perm" to "Пермь",
        "Volgograd" to "Волгоград"
    )

)