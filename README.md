# USSD APP
A simple USSD application that can create account, deposit, withdraw and check balace.

# TOOLS AND SOFTWARE USED
Intellij idea, MongoDB, Maven, Postman, Restful tool, Infobip SMS API

# TO RUN PROJECT
$ mvn spring-boot:run

# CONFIGURATIONS
* Open the application.yml file and set your own configurations for the mongoDB database connection.

# HOW TO TEST ON POSTMAN
* Input *000# in the command field to access the USSD options
![img.png](https://res.cloudinary.com/dsvmzaooo/image/upload/v1677850494/img_rzbhd8.png)

* Input your phone number and pin and then "1" in the command field to create account
![img1.png](https://res.cloudinary.com/dsvmzaooo/image/upload/v1677850494/img1_slckgd.png)
* With your phone number and pin in place, Select "2" to deposit and choose how much you wish to deposit
![img2.png](https://res.cloudinary.com/dsvmzaooo/image/upload/v1677850495/img2_cpzywx.png)
* Select "4" to check account balance
![img3.png](https://res.cloudinary.com/dsvmzaooo/image/upload/v1677850495/img3_shvvxk.png)
