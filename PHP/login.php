<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['emailid']) && isset($_POST['password'])) 
{
    if ($db->dbConnect()) 
	{
        if ($db->logIn("userdetails", $_POST['emailid'], $_POST['password'])) 
		{
            echo "Login Success";
        } 
		else 
			echo "Email or Password wrong";
    } 
	else 
		echo "Error: Database connection";
} 
else 
	echo "All fields are required";
?>
