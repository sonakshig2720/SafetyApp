<?php
	
	define('DB_HOST','localhost');
	define('DB_USER','root');
	define('DB_PASS','');
	define('DB_NAME','safetyapp');
	
	$conn=new mysqli(DB_HOST,DB_USER,DB_PASS,DB_NAME);
	
	if(mysqli_connect_errno())
	{
		die("Not connected".mysqli_connect_error());
	}
	
	$stmt=$conn->prepare("select i.incidents,r.country,r.state,r.gender,r.age,r.incident_time from reports r, incidents i where r.report_time=i.report_time");
	$stmt->execute();
	$stmt->bind_result( $incidents ,$country, $state, $gender, $age ,$incident_time);
	$product=array();
	
	while($stmt->fetch())
	{
		if($gender=="m")
		{
			$gender="Male";
		}
		else if($gender=="f")
		{
			$gender="Female";
		}
		else if($gender=="o")
		{
			$gender="Other";
		}
		$temp=array();
		$temp['incidents']=$incidents;
		$temp['country']=$country;
		$temp['state']=$state;
		$temp['gender']=$gender;
		$temp['age']=$age;
		$temp['incident_time']=$incident_time;
		
		array_push($product,$temp);
		
	}
	echo json_encode($product);
	
	
?>