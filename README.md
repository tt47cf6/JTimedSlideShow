# JTimedSlideShow
A Java-based slideshow application that runs for a given amount of time using pictures from a specified directory, with a settable delay between each slide.

This application is perfect for needing to run a slideshow automatically from the command line, say from cron. It will use pictures from a given directory and finish after a given number of minutes. 

Slides will be shown in alphabetical order, and looks for new or missing files with each slide advance.


Usage
-----

<code>
timedSlideShow.jar /../path/../ display-mins delay-secs </br>
</code> 


/../path/../ </br>
*the directory to display pictures from. Only GIF, JPG, and PNG files will be displayed.*  
               

display_mins </br>
*the number of minutes to run the slideshow for, in whole numbers, 1 or bigger.*
                       

delay_secs </br>
*the number of seconds to show each slide for, whole number 1 or bigger*

**Example:** </br>
<code>
timedSlideShow.jar c:\\pictures 60 5
</code>

 
