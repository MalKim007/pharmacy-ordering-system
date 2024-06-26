<?php
// updateOrderStatus.php

require 'db_config.php';

header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

$data = json_decode(file_get_contents("php://input"));

$orderID = $data->orderID;
$status = $data->status;

if(isset($orderID) && isset($status)) {
    $sql = "UPDATE order_list SET status = :status WHERE orderID = :orderID";
    $stmt = $dbPDO->prepare($sql);
    $stmt->bindParam(':orderID', $orderID);
    $stmt->bindParam(':status', $status);

    if($stmt->execute()) {
        http_response_code(200);
        echo json_encode(array("message" => "Order status updated successfully."));
    } else {
        http_response_code(500);
        echo json_encode(array("message" => "Failed to update order status."));
    }
} else {
    http_response_code(400);
    echo json_encode(array("message" => "Invalid input."));
}
?>