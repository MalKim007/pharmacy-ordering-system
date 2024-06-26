<?php
header('Content-Type: application/json');
include 'db_config.php';

$response = array();

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $username = $_POST['username'];
    $password = $_POST['password'];

    $stmt = $dbPDO->prepare("SELECT userID, passwd, role FROM users WHERE username = ?");
    $stmt->execute([$username]);
    $user = $stmt->fetch(PDO::FETCH_ASSOC);

    if ($user) {
        if ($password == $user['passwd']) {
            $response['status'] = 'success';
            $response['role'] = $user['role'] == 0 ? 'staff' : 'customer';
        } else {
            $response['status'] = 'error';
            $response['message'] = 'Incorrect password';
        }
    } else {
        $response['status'] = 'error';
        $response['message'] = 'Username not found';
    }
} else {
    $response['status'] = 'error';
    $response['message'] = 'Invalid request method';
}

echo json_encode($response);
?>