<?php
// report.php
require 'db_config.php';

header("Content-Type: application/json; charset=UTF-8");

$action = isset($_GET['action']) ? $_GET['action'] : '';

try {
    $response = array();
    switch ($action) {
        case 'totalItems':
            $query = "SELECT itemOrdered FROM order_list";
            $stmt = $dbPDO->query($query);
            $totalItems = 0;

            while ($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
                $items = explode(", ", $row['itemOrdered']);
                foreach ($items as $item) {
                    $parts = explode(" - ", $item);
                    if (count($parts) == 2) {
                        $totalItems += intval($parts[1]);
                    }
                }
            }

            $response = ['totalItems' => $totalItems];
            break;

        case 'totalDelivered':
            $query = "SELECT COUNT(*) as totalDelivered FROM order_list WHERE status = 'DELIVERED'";
            $stmt = $dbPDO->query($query);
            $result = $stmt->fetch(PDO::FETCH_ASSOC);
            $response = $result ? $result : ['totalDelivered' => 0];
            break;

        case 'totalInProcess':
            $query = "SELECT COUNT(*) as totalInProcess FROM order_list WHERE status != 'DELIVERED'";
            $stmt = $dbPDO->query($query);
            $result = $stmt->fetch(PDO::FETCH_ASSOC);
            $response = $result ? $result : ['totalInProcess' => 0];
            break;

        case 'totalUsers':
            $query = "SELECT COUNT(*) as totalUsers FROM users WHERE role = 1";
            $stmt = $dbPDO->query($query);
            $result = $stmt->fetch(PDO::FETCH_ASSOC);
            $response = $result ? $result : ['totalUsers' => 0];
            break;

        case 'totalSales':
            $query = "SELECT SUM(totalPrice) as totalSales FROM order_list";
            $stmt = $dbPDO->query($query);
            $result = $stmt->fetch(PDO::FETCH_ASSOC);
            $response = $result ? $result : ['totalSales' => 0];
            break;

        default:
            $response = ['error' => 'Invalid action'];
            break;
    }
    echo json_encode($response);
} catch (Exception $e) {
    echo json_encode(['error' => $e->getMessage()]);
}
?>