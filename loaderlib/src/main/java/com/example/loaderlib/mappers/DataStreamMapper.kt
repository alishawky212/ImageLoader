package com.example.mindloader.core.mappers

interface DataStreamMapper <in I,out O> {
    fun mapData(input:I):O
}