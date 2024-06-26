<?php
header('Content-Type: application/json');
require 'db_config.php'; 

$status = isset($_GET['status']) ? urldecode($_GET['status']) : '';

try {
    if ($status && $status !== 'All') {
        $sql = "SELECT * FROM order_list WHERE status = :status";
        $stmt = $dbPDO->prepare($sql);
        $stmt->bindParam(':status', $status, PDO::PARAM_STR);
    } else {
        $sql = "SELECT * FROM order_list";
        $stmt = $dbPDO->prepare($sql);
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