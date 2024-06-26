<?php
header('Content-Type: application/json');
require 'db_config.php';

$productName = $_GET['productName'];

try {
    $sql = "SELECT quantity FROM product_list WHERE productName = :productName";
    $stmt = $dbPDO->prepare($sql);
    $stmt->bindParam(':productName', $productName, PDO::PARAM_STR);
    $stmt->execute();
    
    $row = $stmt->fetch(PDO::FETCH_ASSOC);
    if ($row) {
        echo json_encode(['quantity' => $row['quantity']]);
    } else {
        echo json_encode(['error' => 'Product not found']);
    }
} catch (PDOException $e) {
    echo json_encode(['error' => $e->getMessage()]);
}
?>