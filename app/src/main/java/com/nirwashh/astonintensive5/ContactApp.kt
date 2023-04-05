package com.nirwashh.astonintensive5

import android.app.Application
import com.github.javafaker.Faker
import com.nirwashh.astonintensive5.model.Contact

class ContactApp : Application() {
    companion object {
        var contacts = mutableListOf<Contact>()
    }

    override fun onCreate() {
        super.onCreate()
        val faker = Faker.instance()
        contacts = (1..110).map {
            Contact(
                name = faker.name().firstName(),
                lastName = faker.name().lastName(),
                telNumber = faker.phoneNumber().cellPhone(),
                imageUrl = "https://picsum.photos/200/200?people$it",
                id = it.toLong()
            )
        }.toMutableList()

    }


}