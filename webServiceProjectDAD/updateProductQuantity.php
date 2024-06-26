<?php
header('Content-Type: application/json');
require 'db_config.php'; 

$productName = $_POST['productName'];
$quantity = $_POST['quantity'];

try {
    $sql = "UPDATE product_list SET quantity = quantity - :quantity WHERE productName = :productName";
    $stmt = $dbPDO->prepare($sql);
    $stmt->bindParam(':quantity', $quantity, PDO::PARAM_INT);
    $stmt->bindParam(':productName', $productName, PDO::PARAM_STR);
    $stmt->execute();

    if ($stmt->rowCount() > 0) {
        echo json_encode(['status' => 'success']);
    } else {
        echo json_encode(['status' => 'error', 'message' => 'Product not found or quantity not sufficient']);
    }
} catch (PDOException $e) {
    echo json_encode(['status' => 'error', 'message' => $e->getMessage()]);
}
?>