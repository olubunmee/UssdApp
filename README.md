# UssdApp
A simple USSD application that can create account, deposit, withdraw and check balace.

# TOOLS AND SOFTWARE USED
Intellij idea, MongoDB, Maven, Postman, Restful tool, Infobip SMS api

# TO RUN PROJECT
$ mvn spring-boot:run

# CONFIGURATIONS
* Open the application.yml file and set your own configurations for the mongoDB database connection.

# HOW TO TEST ON POSTMAN
* Input *000# in the command field to access the USSD options
![img.png](/home/olubunmi/Pictures/Screenshots/img.png)
* Input your phone number and pin and then "1" in the command field to create account
![img1.png](/home/olubunmi/Pictures/Screenshots/img1.png)
* With your phone number and pin in place, Select "2" to deposit and the choose how much you wish to deposit
![img2.png](/home/olubunmi/Pictures/Screenshots/img2.png)
* Select "4" to check account balance
![img3.png](/home/olubunmi/Pictures/Screenshots/img3.png)

