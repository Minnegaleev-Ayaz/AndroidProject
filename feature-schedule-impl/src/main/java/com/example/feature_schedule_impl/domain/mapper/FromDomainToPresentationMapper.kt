package com.example.feature_schedule_impl.domain.mapper


import android.util.Log
import com.example.feature_schedule_impl.domain.model.MatchesDomainModel
import com.example.feature_schedule_impl.domain.model.StreamDomainModel
import com.example.feature_schedule_impl.presentation.model.past.PastMatchUiModel
import com.example.feature_schedule_impl.presentation.model.running.RunningMatchUiModel
import com.example.feature_schedule_impl.presentation.model.running.RunningStreamUiModel
import com.example.feature_schedule_impl.presentation.model.upcoming.UpcomingMatchUiModel
import com.nefrit.common.utils.DateFormatter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

class FromDomainToPresentationMapper @Inject constructor(
    private val dateFormatter: DateFormatter
) {

    fun mapDomainToPresentationUpcoming(domainData: MatchesDomainModel): UpcomingMatchUiModel {
        return UpcomingMatchUiModel(
            id = domainData.id,
            firstTeamImage = domainData.firstTeam?.imageUrl,
            firstTeamName = domainData.firstTeam?.name,
            secondTeamImage = domainData.secondTeam?.imageUrl,
            secondTeamName = domainData.secondTeam?.name,
            matchType = domainData.matchType,
            matchTime = dateFormatter.formatDate(domainData.beginAt!!),
            leagueName = domainData.serieName?:"",
            firstTeamId = domainData.firstTeam?.id,
            secondTeamId = domainData.secondTeam?.id,

        )
    }
    fun mapDomainToPresentationRunning(domainData: MatchesDomainModel): RunningMatchUiModel {
        return RunningMatchUiModel(
            id = domainData.id,
            firstTeamImage = domainData.firstTeam?.imageUrl,
            firstTeamName = domainData.firstTeam?.name,
            secondTeamImage = domainData.secondTeam?.imageUrl,
            secondTeamName = domainData.secondTeam?.name,
            matchType = domainData.matchType,
            matchTime = dateFormatter.formatDate(domainData.beginAt!!),
            leagueName = domainData.serieName?:"",
            streams = domainData.streams.map { it-> mapStreamDomainToUi(it)}
        )
    }
    fun mapDomainToPresentationPast(domainData: MatchesDomainModel): PastMatchUiModel {

        val past =  PastMatchUiModel(
            id = domainData.id,
            firstTeamImage = domainData.firstTeam?.imageUrl,
            firstTeamName = domainData.firstTeam?.name,
            secondTeamImage = domainData.secondTeam?.imageUrl,
            secondTeamName = domainData.secondTeam?.name,
            matchType = domainData.matchType,
            matchTime = dateFormatter.formatDate(domainData.beginAt!!),
            leagueName = domainData.serieName?:"",
            firstTeamId = domainData.firstTeam?.id,
            secondTeamId = domainData.secondTeam?.id,
            firstTeamScore = domainData.firstTeamResult,
            secondTeamScore = domainData.secondTeamResult,
        )
        Log.e("Ayaz", past.matchTime.toString()+"- past")
        return past
    }
    fun mapStreamDomainToUi(runningStreamDomainModel: StreamDomainModel): RunningStreamUiModel{
        return RunningStreamUiModel(
            runningStreamDomainModel.language,
            runningStreamDomainModel.rawUrl
        )
    }
}