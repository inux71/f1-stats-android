package com.grabieckacper.f1stats.network

sealed class Url(val path: String) {
    class Circuit(id: String) : Url(path = "circuits/$id")
    class Driver(id: String) : Url(path = "drivers/$id")
    class Team(id: String) : Url(path = "teams/$id")

    object ConstructorStandings : Url(path = "current/constructors-championship")
    object DriverStandings : Url(path = "current/drivers-championship")
    object Races : Url(path = "current")

    companion object {
        const val BASE = "https://f1api.dev/api/"
    }
}
