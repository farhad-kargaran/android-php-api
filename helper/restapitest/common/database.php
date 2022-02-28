<?php
    function getConnection()
    {
        
 
        $user = "farhadka_root";
        $pass = "F@rhad39096048";
        $host= "localhost";
        $dbname="farhadka_restapitest";

    
        $conn= new mysqli($host, $user, $pass, $dbname);
        if ($conn->connect_error) {
            $conn= null;
        }
        $conn->set_charset("utf8");
        return $conn;
    }
