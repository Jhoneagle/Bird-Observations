# Bird Observations Android app

## Description

Android application for collecting observations with really simple interface. It uses phones location data and media if permission is granted but is not mandatory. Also for data persistent it creates own SQL database on the local device using androids integrated SQLite.

Program contains two views. Where main one is list of stored observations and second is form to add new ones. IN the list each item has following things depending on permissions and user types: species name, rarity, time of observations creation, location were observation was made, picture of observation and notes about the observation. Which all can be added in the Form. However form doesn't validate names or notes except by makeing sure there is no SQL injections being done. As image is stored mainly as reference and rarity is sellected from predefined values and after user clicks create observation location and timestamp are generated automatically. Also canceling creation removes all content in the forms 'notes', 'name' and 'image' fields.

## Set up

All commands are recomended to be executed in the projects app folder if done with terminal/command line. In example android studio this can be done from root savely.

### Building

To build the project you just need to run 'gradle build' or in example android studio run 'make/build project'.

### Running

First of all you need to have Android emulator open or physical device that is connected with USB and have 'USB-debugging allowed'. And then you can run the project with 'gradle run' or in example android studio by choosing 'run' with configuration that calls 'gradle run'.

### Release

To only make APK-file then you need to type either 'gradle assemble' or 'gradle assembleDebug' or 'gradle assembleAndroidTest'. Or in android studio example use 'make APK' or 'make unsigned APK'.

## This was done as a assignment

### My expreience about the project

This was intresting project to make espesically as I haven't done much coding in mobiles yet. All of all that meant i had to find out and learn some things. Like how to access androids SQLite integration, how to handle image upload from gallery and fetch users current location. Where other parts like Dao, SQL-querys, Activitys were quite fimilar already.

So ofcourse it was challeging to make the application but also enjoyable in the same time. New things got learned and in my opinion good solution was made after all. 

If i would have to say cons in the application then lack of styling or oversimplified interface maybe. Like I know I could have focused more in the UX designing to make it more visually satisfying. But yeah, didn't do it this time. Also tried to make the application in away that it's easily expandable but you can't really tell if it is that easily expandable before you actually do it.
