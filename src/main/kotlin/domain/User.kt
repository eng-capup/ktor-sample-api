package domain

import com.fasterxml.jackson.annotation.JsonProperty

data class User(val email: String, val password: String)

data class AuthResponse(
    @JsonProperty("access_token") val accessToken: String
)
