<?php
include_once('../common/include.php');
include_once('../common/encipher.php');

$user = json_decode(file_get_contents("php://input"));
if(!$user->name)
{
    sendResponse(400, [] , 'Name Required !');  
    
}
else if(!$user->email)
{
    sendResponse(400, [] , 'Email Required !');  
}
else if(!$user->id)
{
    sendResponse(400, [] , 'Id Required !');  
}

$conn=getConnection();
if($conn==null){
    sendResponse(500,$conn,'Server Connection Error !');
    exit;
}
$sql = "UPDATE user 
            SET 
                name = '$user->name',
                email = '$user->email'
        WHERE id = '$user->id'
        ";
        
        
$result = $conn->query($sql);
if ($conn->affected_rows > 0) {
    sendResponse(200, [], 'User Updated .');
} else {
    sendResponse(404, [], 'Error on update !');
}
$conn->close();
