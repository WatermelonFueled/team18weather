#  Team 18 Weather App

Team 18 Weather App displays current and upcoming weather information for a user selected location.

It connects with [OpenWeatherMap's API](https://openweathermap.org) to retrieve current, short-term (3h), and 7 day weather forecasts. The response from the API (in JSON) is parsed using [JSON.simple](https://code.google.com/p/json-simple/)
Likewise, it connects with [Mars Atmospheric Aggregation System API](http://marsweather.ingenology.com) to retrieve the most recent weather update from the [Curiosity Rover](http://mars.nasa.gov/msl/) on Mars.

## Installation

Download our application JAR file. Then use the following line to run the app:

```
$ java -jar team18-WeatherApp-1.0-SNAPSHOT-jar-with-dependencies.jar
```

## Build

To build:

```
$ git clone git@github.com:UWO-2212-W2015/team18.git
$ cd team18
$ mvn compile
$ mvn package
```

## Usage

[Watch our demonstration video!](https://www.youtube.com/watch?v=JdziXdv03YQ)

## Documentation