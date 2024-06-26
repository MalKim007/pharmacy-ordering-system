<?php

$hostAddr = "localhost";
$dbName = "projectdad";
$dbUser = "root";
$dbPwd = "";
$dbPort = 3306;

try {
    $dbPDO = new PDO("mysql:host=$hostAddr;dbname=$dbName", $dbUser, $dbPwd);
    $dbPDO->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} catch (PDOException $e) {
    die("Connection failed: " . $e->getMessage());
}
?>