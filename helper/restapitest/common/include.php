<?php
include_once('../common/database.php');
//include_once('../common/header.php');
include_once('../common/response.php');

if (empty($_SERVER['HTTP_TOKEN']) || $_SERVER['HTTP_TOKEN'] != '7EE9DA7D594CFF9F134268A3AC98E10BEBA66194') {
    sendResponse(403, [], 'Access Denied !');
    exit;
}
?>