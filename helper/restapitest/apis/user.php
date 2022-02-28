<?php
include_once('../common/include.php');
$userParam = json_decode(file_get_contents("php://input"));
$conn=getConnection();

if($conn==null){
    sendResponse(500,$conn,'Server Connection Error');
}else{
    $sql = "SELECT * FROM `user` ";
    
    if (mb_strlen($userParam->name, 'utf8') > 2 ) {
        $sql .= " WHERE name LIKE '%{$userParam->name}%' ";
    }

    $sql .= ' ORDER BY id ASC LIMIT 20';

    //file_put_contents(__DIR__ . '/test.log', $sql, FILE_APPEND);

    $result = $conn->query($sql);
    
    if ($result->num_rows > 0) {
        $users=array();
        while($row = $result->fetch_assoc()) {
            $users[] = $row;
        }
        sendResponse(200, $users, 'User List');
    } else {
        sendResponse(404,[],'User not available');
    }
    $conn->close();
}
?>
