<?php
header('Content-Type: application/json');
require 'db_config.php'; 

if (!isset($_GET['username'])) {
    http_response_code(400);
    echo json_encode(['error' => 'Missing username parameter.']);
    exit;
}

$username = $_GET['username'];
$status = isset($_GET['status']) ? urldecode($_GET['status']) : '';

try {
    if ($status && $status !== 'All') {
        $sql = "SELECT * FROM order_list WHERE customerName = :username AND status = :status";
        $stmt = $dbPDO->prepare($sql);
        $stmt->bindParam(':username', $username, PDO::PARAM_STR);
        $stmt->bindParam(':status', $status, PDO::PARAM_STR);
    } else {
        $sql = "SELECT * FROM order_list WHERE customerName = :username";
        $stmt = $dbPDO->prepare($sql);
        $stmt->bindParam(':username', $username, PDO::PARAM_STR);
    }

    $stmt->execute();
    $orders = $stmt->fetchAll(PDO::FETCH_ASSOC);

    if (empty($orders)) {
        http_response_code(404);
        echo json_encode(['error' => 'No orders found with the specified status.']);
    } else {
        echo json_encode(['orders' => $orders]);
    }
} catch (PDOException $e) {
    http_response_code(500);
    echo json_encode(['error' => $e->getMessage()]);
}
?>