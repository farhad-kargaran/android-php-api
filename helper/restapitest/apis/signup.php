<?php
include_once('../common/include.php');
include_once('../common/encipher.php');

$user = json_decode(file_get_contents("php://input"));
if(!$user->name){
    sendResponse(400, [] , 'Name Required !');  
}else if(!$user->email){
    sendResponse(400, [] , 'Email Required !');  
}else if(!$user->password){
    sendResponse(400, [] , 'Password Required !');        
}
else{
    $password = doEncrypt($user->password);
    $conn=getConnection();
    if($conn==null){
        sendResponse(500, $conn, 'Server Connection Error !');
    }else{
        $sql = "INSERT INTO user 
            SET 
                name = '$user->name',
                email = '$user->email',
                password = '$password',
                contact = '$user->contact'
        ";

        $result = $conn->query($sql);
        //file_put_contents(__DIR__ . '/test.log', $conn->error, FILE_APPEND);
        if ($result) {
            sendResponse(200, $result , 'User Registration Successful.');
        } else {
            sendResponse(404, [] ,'User not Registered');
        }
        $conn->close();
    }
}
