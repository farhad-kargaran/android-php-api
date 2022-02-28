This is a simple android project that store and fetch data to mysql using PHP API


1- Clone the project using: git clone https://github.com/farhad-kargaran/android-php-api .

2- Run project in Android Studio

3- Create a db in mysql, in our example named "farhadka_restapitest"

4- create a user, in our example named "farhadka_root"

5- set user password, in our exmple "F@rhad39096048"

6- assign user to db with all privileges

7- create user table with following command:

CREATE TABLE `farhadka_restapitest`.`user` ( `id` INT NOT NULL AUTO_INCREMENT , `name` VARCHAR(50) NOT NULL , `email` VARCHAR(50) NOT NULL , `password` TEXT NOT NULL , `contact` VARCHAR(50) NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB; 

8- change android variables in MainActivity.java based on your domain and directories name in our example it is:

    String url_signup = "http://farhadkargaran.ir/apps/restapitest/apis/signup.php";
    String url_login = "http://farhadkargaran.ir/apps/restapitest/apis/login.php";
    String url_del = "http://farhadkargaran.ir/apps/restapitest/apis/del.php";
    String url_update = "http://farhadkargaran.ir/apps/restapitest/apis/update.php";
    String url_user = "http://farhadkargaran.ir/apps/restapitest/apis/user.php";

9- upload restapi folder to your server, in our example the url is: "http://farhadkargaran.ir/apps/restapitest/"

10- open file named database.php in the path: restapitest/common/database.php and change the following 3 variables based on yours:
    $user, $pass, $dbname

11- Thats all !!
