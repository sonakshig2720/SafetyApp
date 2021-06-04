<?php
require "DataBaseConfig.php";

class DataBase
{
    public $connect;
    public $data;
    private $sql,$sql1,$sql2,$sql3,$sql4,$sql5,$sql6;
    protected $servername;
	protected $username;
    protected $password;
    protected $databasename;

    public function __construct()
    {
        $this->connect = null;
        $this->data = null;
		$this->sql = null;
        $this->sql1 = null;
		$this->sql2 = null;
		$this->sql3 = null;
		$this->sql4 = null;
		$this->sql5 = null;
		$this->sql6 = null;
        $dbc = new DataBaseConfig();
        $this->servername = $dbc->servername;
        $this->username = $dbc->username;
        $this->password = $dbc->password;
        $this->databasename = $dbc->databasename;
    }

    function dbConnect()
    {
        $this->connect = mysqli_connect($this->servername, $this->username, $this->password, $this->databasename);
        return $this->connect;
    }

    function prepareData($data)
    {
        return mysqli_real_escape_string($this->connect, stripslashes(htmlspecialchars($data)));
    }

    function logIn($table, $emailid, $password)
    {
        $emailid = $this->prepareData($emailid);
        $password = $this->prepareData($password);
        $this->sql = "select * from " . $table . " where emailid = '" . $emailid . "'";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
		//return $row;
		
        if (mysqli_num_rows($result) != 0) 
		{
            $dbemailid = $row['emailid'];
            $dbpassword = $row['password'];
			//$hashedpwd=password_hash($password,PASSWORD_BCRYPT);
		
            if ($dbemailid == $emailid && $password==$dbpassword) 
			{
                $login = true;
            } 
			else 
				$login = false;
        } 
		else 
			$login = false;

        return $login;
		
    }

    function signUp($table, $name, $phonenumber, $emailid, $password)
    {
        $name = $this->prepareData($name);
        $emailid = $this->prepareData($emailid);
        $password = $this->prepareData($password);
        $phonenumber = $this->prepareData($phonenumber);
        //$password = password_hash($password, PASSWORD_DEFAULT);
        $this->sql =
            "INSERT INTO " . $table . " (name, phonenumber, emailid, password) VALUES ('" . $name . "','" . $phonenumber . "','" . $emailid . "','" . $password . "')";
        if (mysqli_query($this->connect, $this->sql)) 
		{
            return true;
        } 
		else 
			return false;
    }
	
	function insert_reports($table_r ,$emailid ,$country,$state, $city, $area, $gender, $age, $incident_time)
	{
		$flag=0;
		
		$this->sql1="select id from userdetails where emailid= '" . $emailid . "'";
		$result = mysqli_query($this->connect, $this->sql1);
        $row = mysqli_fetch_assoc($result);
		$dbid=$row['id'];
		
		$this->sql2 =
            "INSERT INTO " . $table_r . " (id, country, state, city, area ,gender, age, incident_time) VALUES ('" . $dbid . "','" . $country . "','" . $state . "','" . $city . "','" . $area . "','" . $gender . "','" . $age . "','" . $incident_time . "')";
		
		
		if (mysqli_query($this->connect, $this->sql2)) 
		{
            return true;
        } 
		else 
			return false;	
		
	}
	
	function insert_incidents($table,$emailid, $incidents)
	{
		/*$this->sql2="select r_id from reports r where r.id=(select id from userdetails where emailid= '" . $emailid . "')";
		$result = mysqli_query($this->connect, $this->sql2);
        $row = mysqli_fetch_assoc($result);
		$dbid=$row['r_id'];*/
		
		$this->sql3 = "SELECT report_time FROM reports order by report_time desc";

			if ($result = mysqli_query($this->connect, $this->sql3)) {
			  while ($fieldinfo = $result -> fetch_field()) {
				$row = mysqli_fetch_assoc($result);
				$varr=$row['report_time'];
			  }
			}
		
		$this->sql =
            "INSERT INTO " . $table . " (report_time, incidents) VALUES ('" . $varr . "','" . $incidents . "')";
		if (mysqli_query($this->connect, $this->sql)) 
		{
            return true;
        } 
		else 
			return false;
	}
	
	
	function get_reports($table,$selected_col,$data)
	{
		if($selected_col=="Country")
		{
			$this->sql="select * from reports where country='".$data."'";
			$s=mysqli_query($this->connect,$this->sql);
			if($s)
			{
				if(mysqli_num_rows($s)>0)
				{
					while($row=mysqli_fetch_array($s))
					{
						echo "Country : {$row['country']} \n";
						$a[]=$row;
					}
					print(json_encode($a));
					return true;
				}
				else
				{
					return false;
				}
			}
			else
			{
				return false;
			}
			
		}
		else if($selected_col=="state")
		{
		}
		else if($selected_col=="city")
		{
			
		}
		else if($selected_col=="area")
		{
			
		}
	}
}
?>
