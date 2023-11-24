package com.mo.kmp_hello_world

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform