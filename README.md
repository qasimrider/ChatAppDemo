# ChatAppDemo

###### This Application is build on a Multi modules using MVVM, Clean Architecture approache

The Approach to write this software is pretty much in an isolated way. Below are the brief description of the modules in the software

## Core Libs
1. Hilt for DI
2. Jetpack Architecture components
3. Compose UI

## App 
This is the Gateway to the whole application containing DI Initialization and in our case it contain MainActivity which host main Navhost of Compose UI

###### Purposes
1. UI belongs to this package
2. Hosting the Application Root 



## Domain
This module kept all the use cases belong to the application, on top of that this is UI agnostic module.Having all the business information of the app.
This is utilizing data module in order to get the business and populate their respective DTOS
###### Purposes
1. To keep the business in a separate module agnostic of UI


## Model
In this module Models objects are kept to provide other modules 


## Data
In this module the repositories of all the application is embedded also the Databse Integration is in this module
###### Purpose
1. populating business module
