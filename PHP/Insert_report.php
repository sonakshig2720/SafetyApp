<?php
require "DataBase.php";
$db = new DataBase();

    if ($db->dbConnect()) 
	{
        if ($db->insert_reports("reports", $_POST['emailid'], $_POST['country'], $_POST['state'],$_POST['city'], $_POST['area'], $_POST['gender'], $_POST['age'], $_POST['incident_time'])) 
		{
            echo "Inserted into all tables";
        } 
		else 
			echo "Failed tables";
    } 
	else 
		echo "Error: Database connection";

?>