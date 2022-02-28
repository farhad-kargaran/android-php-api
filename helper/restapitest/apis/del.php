<?php
include_once('../common/include.php');
include_once('../common/encipher.php');

$user = json_decode(file_get_contents("php://input"));

$conn=getConnection();
if($conn==null){
    sendResponse(500,$conn,'Server Connection Error !');
    exit;
}

if($user->email)
{
    $sql = "DELETE FROM user WHERE email = '$user->email'    ";
}
else
{
$sql = "DELETE FROM user WHERE id    =       '$user->id'    ";
}

//echo $sql;

$result = $conn->query($sql);
if ($conn->affected_rows > 0) {
    sendResponse(200, [], 'User deleted .');
} else {
    sendResponse(404, [], 'Error on delete !');
}
$conn->close();
