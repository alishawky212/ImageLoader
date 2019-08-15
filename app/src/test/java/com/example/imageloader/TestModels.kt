package com.example.imageloader

import com.example.imageloader.models.*


fun createItem(): Item = Item(id = "1", created_at = "createdAt", color = "Black", height = 100, liked_by_user = true, likes = 20, urls = createUrl(), user = createUser(),width = 200)
fun createUser(): User = User(id = "2", name = "testuser",username = "test",links = createLinks() ,profile_image = createProfileImages())
fun createUrl(): Urls = Urls(raw = "raw", full = "full", regular = "regular", small = "small", thumb = "thumb")
fun createLinks(): Links = Links(self = "self", likes = "likes", html = "html",photos = "photos")

fun createProfileImages(): ProfileImages = ProfileImages(small = "small", large = "large", medium = "medium")