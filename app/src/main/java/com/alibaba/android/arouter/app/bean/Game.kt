package com.alibaba.android.arouter.app.bean

@Suppress("unused")
interface ScheduleModule {

    data class Schedule(
        val currentDate: String,
        val day: String,
        val games: List<Game>,
        val nextDate: String,
        val paging: Paging,
        val previousDate: String
    )

    data class Game(
        val awayTeam: Team,
        val blackoutStations: String,
        val date: String,
        val dateTimeGMT: String,
        val description: String,
        val extId: String,
        val gameState: Int,
        val homeTeam: Team,
        val id: String,
        val image: String,
        val isGame: String,
        val leagueId: String,
        val location: String,
        val logo: String,
        val masterImage: String,
        val name: String,
        val noAccess: Boolean,
        val season: String,
        val seoName: String,
        val sportId: String,
        val sportName: String,
        val type: String,
        val week: String
    )

    data class Paging(
        val count: Int,
        val navEnd: Int,
        val navStart: Int,
        val pageNumber: Int,
        val totalPages: Int
    )

    data class Team(
        val code: String,
        val id: String,
        val name: String,
        val score: String
    )
}