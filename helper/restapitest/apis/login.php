<?php
include_once('../common/include.php');
include_once('../common/encipher.php');
$user = json_decode(file_get_contents("php://input"));
if(!$user->email){
    sendResponse(400, [] , 'Email Required !');  
}else if(!$user->password){
    sendResponse(400, [] , 'Password Required !');        
}else{
    $conn=getConnection();
    if($conn==null){
        sendResponse(500,$conn,'Server Connection Error !');
    }else{
        $password=doEncrypt($user->password);
        $sql = "SELECT * FROM user WHERE email = '$user->email' AND password = '$password' ";
        $result = $conn->query($sql);
        if ($result->num_rows > 0) {
            $users = array();
            while($row = $result->fetch_assoc()) {
                $users[] = $row;
            }
            sendResponse(200,$users,'User Details');
        } else {
            sendResponse(404,[],'User not available');
        }
        $conn->close();
    }
}
