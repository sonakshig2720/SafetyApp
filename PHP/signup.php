<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['name']) && isset($_POST['phonenumber']) && isset($_POST['emailid']) && isset($_POST['password'])) {
    if ($db->dbConnect()) {
        if ($db->signUp("userdetails", $_POST['name'], $_POST['phonenumber'], $_POST['emailid'], $_POST['password'])) {
            echo "Sign Up Success";
        } else echo "Sign up Failed";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>
