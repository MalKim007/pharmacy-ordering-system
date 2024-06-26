<?php
header('Content-Type: application/json');
require 'db_config.php'; 

$category = $_GET['category'];

try {
    $sql = "SELECT productName, price FROM product_list WHERE productCategory = :category";
    $stmt = $dbPDO->prepare($sql);
    $stmt->bindParam(':category', $category, PDO::PARAM_STR);
    $stmt->execute();

    $products = [];
    while ($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
        $products[] = $row['productName'] . ' - ' . $row['price'];
    }

    echo json_encode($products);
} catch (PDOException $e) {
    echo json_encode(['error' => $e->getMessage()]);
}
?>