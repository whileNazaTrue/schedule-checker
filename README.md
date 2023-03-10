# Schedule overlapping checker

This project is part of a challenge for a recruitment process.

## The exercise

 ```
The company ACME offers their employees the flexibility to work the hours they want. But due to some external circumstances they need to know what employees have been at the office within the same time frame

The goal of this exercise is to output a table containing pairs of employees and how often they have coincided in the office.

Input: the name of an employee and the schedule they worked, indicating the time and hours. This should be a .txt file with at least five sets of data. You can include the data from our examples below:

Example 1:

INPUT
RENE=MO10:00-12:00,TU10:00-12:00,TH01:00-03:00,SA14:00-18:00,SU20:00- 21:00
ASTRID=MO10:00-12:00,TH12:00-14:00,SU20:00-21:00
ANDRES=MO10:00-12:00,TH12:00-14:00,SU20:00-21:00


OUTPUT:
ASTRID-RENE: 2
ASTRID-ANDRES: 3
RENE-ANDRES: 2

Example 2:

INPUT:
RENE=MO10:15-12:00,TU10:00-12:00,TH13:00-13:15,SA14:00-18:00,SU20:00-21:00
ASTRID=MO10:00-12:00,TH12:00-14:00,SU20:00-21:00

OUTPUT:
RENE-ASTRID: 3
 ```
 
<br>
In simple terms, the program must check how many times do employees work at the same time, grouping them by two. The program will recieve a input.txt file with the format used above.

A sample input text is already in the data folder.

# Approach, methodology and solution


## Reading the employees from the file
<br>


Reading the problem and taking in account the possibilities that Java brings, being a OOP language, solving this exercise will require two classes:
  * An <b>Employee</b> class, which has a name, and a list of <b>Schedules</b>
  * A <b>Schedule</b> class, which has two LocalDateTime objects: One for when the employee begins working, and one for when it ends.

The program recieves a file with unprocessed data from employees, including their name, a day of the week, and the times when the employee begins and ends their workday.
<br>
<br>

 ><b><u>This data is added as a input.txt file, which must be created in a folder called data. It must follow the format specified in the exercise </b> </u>
<br>

Therefore, all the data must be decomposed, using certain characters included in the file. This is done through the "split" method, included in String. The first one is the breakspace, which separates different employees in itself. After this, all employees and their schedules become different elements of a String array.

After this, the name is separated from the schedules using the "=" symbol. This leaves us with a day of the week, and two dates.

Having the day sorted out, we are left with the times and the days of the week. For them, instead of splitting the method substring is used, which in this case leaves us for easier manipulation of the hours and correct selection of the days of the week. These last ones are converted using a util class which converts the input to a number, used by the LocalDateTime for creating the Date. 

This time, the split method is used once again for different schedules, since they are separated with ",".

Once the string is deconstructed, the employee list is loaded into memory from the file.

The approach for this problem implied modularization, reducing the functions to minimize their responsibility, which later on helps with maintainance in case something needs to be modified. 
<br>
## Checking for matching schedules
In this program we must save two values: The names of two employees in a EMPLOYEE-EMPLOYEE2 format and the ammount of times they have coincided in their schedules.

That's why, one of the approaches is using a HashMap. HashMaps contain a Key and a Value. The Key must be unique. It's important to take this in account, since if a key is pushed with another value, the last one gets overwritten, and there's no repeated data.

Since we have a list of employees, and a list of schedules, both must be iterated to check for matching schedules overlapping.

The comparison is done through a method on "Schedule", which works similar to a compareTo method, but using the "isBefore" method included in the class LocalDateTime, which checks if two schedules are included inbetween one or other.

When a schedule is matched, a key is generated with the names of both employees, which are generated by appending their names. When pushed to the HashMap, the key is set as said string, and the value goes up by one. There's a check for the value existing before.

As said before, the key is overwritten, but the value goes up to represent how many times the overlap has happened.

After the HashMap is loaded with all the combinations and the times they happen, it is written into a file named output.txt in the data folder.
<br>
## Architecture

The architecture used for this program is MVC. Though there's no real "view", since there was no UI required for this project, there are Models which interact with controllers, which do all the logic required for, in this case, loading the data into memory, filtering it and writing it again as an output with the information required (in this case, it was to count how much times two Employees meet in the office at the same time)

This pattern assures that the responsibility for manipulating the data isn't on the Model, but rather through invoking it when a request is done. Of course, this is what the controller does. The model is purely a representation of the information the system has. The controller would also be in charge of sending the information required to the view. Basically, the controller works as a intermediary between these two.


## How to run
  
  Below, there's a guide on how to run and compile the file for IntelliJ IDE


  1.1 Java must be installed on your computer.
```
https://www.java.com/en/download/ 
```


  1.2 Install also the Java JDK
```
https://www.oracle.com/java/technologies/downloads/#jdk19-windows
```

  2. Clone the GitHub project in your computer.
```
https://github.com/whileNazaTrue/schedule-checker.git
```
  3. Open the project file. (.iml extension)
  4. File -> Project Structure -> Artifacts. Click on the plus sign, JAR -> From modules and dependencies
  5. Set the main class to main, press OK, then Apply.
  6. Compile the project going to Build -> Build artifacts -> Build
  7. On the project tab, go to out/artifacts/schedule_checker_jar and move the .jar file wherever you wish. Open it and it should create a "data" folder.
  8. Add your input.txt on the data folder, run again. The program will have created an output with the results.
  
## Contact

You can find me on LinkedIn on https://www.linkedin.com/in/rodrigueznazareno/


