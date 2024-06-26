<?php
header('Content-Type: application/json');
require 'db_config.php'; 

$customerName = $_POST['customerName'];
$phoneNum = $_POST['phoneNum'];
$address = $_POST['address'];
$itemOrdered = $_POST['itemOrdered'];
$totalPrice = $_POST['totalPrice'];
$status = "PENDING"; // Hardcoded status

try {
    $sql = "INSERT INTO order_list (customerName, phoneNum, address, itemOrdered, status, totalPrice) VALUES (:customerName, :phoneNum, :address, :itemOrdered, :status, :totalPrice)";
    $stmt = $dbPDO->prepare($sql);
    $stmt->bindParam(':customerName', $customerName, PDO::PARAM_STR);
    $stmt->bindParam(':phoneNum', $phoneNum, PDO::PARAM_STR);
    $stmt->bindParam(':address', $address, PDO::PARAM_STR);
    $stmt->bindParam(':itemOrdered', $itemOrdered, PDO::PARAM_STR);
    $stmt->bindParam(':status', $status, PDO::PARAM_STR);
    $stmt->bindParam(':totalPrice', $totalPrice, PDO::PARAM_STR);
    $stmt->execute();

    echo json_encode(['status' => 'success']);
} catch (PDOException $e) {
    echo json_encode(['status' => 'error', 'message' => $e->getMessage()]);
}
?>