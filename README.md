#  Team 18 Weather App

Team 18 Weather App displays current and upcoming weather information for a user selected location.

It connects with [OpenWeatherMap's API](https://openweathermap.org) to retrieve current, short-term (3h intervals), and 7 day weather forecasts. The response from the API (in JSON) is parsed using [JSON.simple](https://code.google.com/p/json-simple/).
Likewise, it connects with [Mars Atmospheric Aggregation System API](http://marsweather.ingenology.com) to retrieve the most recent weather update from the [Curiosity Rover](http://mars.nasa.gov/msl/) on Mars. [Apache Log4j 2](https://logging.apache.org/log4j/2.x/) was used for logging.

## Installation

[Download our application JAR file](https://github.com/UWO-2212-W2015/team18/blob/master/team18-WeatherApp-1.0-SNAPSHOT-jar-with-dependencies.jar?raw=true). Then, from the same directory as the JAR file, use the following line to run the app:

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

*You will require [Apache Maven](https://maven.apache.org/download.cgi)

## Usage

[Watch our demonstration video!](https://www.youtube.com/watch?v=JdziXdv03YQ)

## Documentation

See our [Javadocs](https://github.com/UWO-2212-W2015/team18/tree/master/doc).
