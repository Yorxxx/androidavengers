# Sample application featuring RxJava, Dagger and Mosby (MVP)

This application retrieves data from Marvel API, displaying comics featured by one of their
characters.

Feel free to notify, modify or warn for any wrong or better implementation :)

### Purpose

The main purpose of this development is to try to integrate Clean code arquitecture within an Android app.

This means the following:

* Dependency injection
* Model-View-Presenter arquitecture
* Unit testing
* Integration tests

### Libraries

Some of the libraries used are:

* [Dagger 2](http://google.github.io/dagger/)
* [RxJava](https://github.com/ReactiveX/RxJava)
* [Mosby](https://github.com/sockeqwe/mosby)
* [Butterknife](https://github.com/JakeWharton/butterknife)
* [Picasso](https://github.com/square/picasso)
* [Retrofit](https://github.com/square/retrofit)

### Source

All data is retrieved from the open [Marvel API](http://developer.marvel.com/docs)

For creating the POJO objects, I was a bit lazy, so I used [jsonschema2pojo](http://www.jsonschema2pojo.org/), but feel free to modify it.
