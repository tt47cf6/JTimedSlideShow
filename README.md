# JTimedSlideShow
A Java-based slideshow application that runs for a given amount of time using pictures from a specified directory, with a settable delay between each slide.

This application is perfect for needing to run a slideshow automatically from the command line, say from cron. It will use picutres from a given directory and finish after a given number of minutes. 

Slides will be shown in alphabetical order, and at the end of each loop, new pictures are looked for and displayed in the next round.


Usage
-----
**timedSlideShow.jar /../path/../ display_mins delay_secs**
Example: timedSlideShow.jar c:\\pictures 60 5 

**/../path/../**
the directory to display pictures from. Only GIF, JPG, and PNG files will be displayed.                 

**display_mins**
the number of minutes to run the slideshow for, in whole numbers, 1 or bigger.                       

**delay_secs**
the number of seconds to show each slide for, whole number 1 or bigger

 
