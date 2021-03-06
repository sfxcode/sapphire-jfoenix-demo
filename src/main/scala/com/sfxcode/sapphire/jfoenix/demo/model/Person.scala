package com.sfxcode.sapphire.jfoenix.demo.model

import org.bson.types.ObjectId

import java.util.Date

case class Person(
    id: Long,
    guid: String,
    isActive: Boolean,
    balance: Double,
    picture: String,
    age: Int,
    name: String,
    gender: String,
    email: String,
    phone: String,
    address: String,
    about: String,
    registered: Date,
    tags: List[String],
    friends: List[Friend],
    greeting: String,
    favoriteFruit: String,
    _id: ObjectId = new ObjectId()
)
