<?php
require "DataBase.php";
$db = new DataBase();

    if ($db->dbConnect()) 
	{
        if ($db->insert_incidents("incidents", $_POST['emailid'], $_POST['incidents'])) 
		{
            echo "Inserted into incidents";
        } 
		else 
			echo "Failed incident";
    } 
	else 
		echo "Error: Database connection";

?>