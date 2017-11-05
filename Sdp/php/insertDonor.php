<?php
require 'db_connect.php';
//echo "helllow world!!!"."<br>";

if(isset($_POST['id']))
{
	$nam = $_POST['name'];
	$id = $_POST['id'];
	$email = $_POST['email'];
	$pass = $_POST['password'];
	$bg = $_POST['bg'];
	$disci = $_POST['disci'];
	$mobile = $_POST['mobile'];
	$dist = $_POST['dist'];
	$date = $_POST['date'];
	$sql = "INSERT INTO donor (id,name,discipline,bloodgroup,mobile,email,password,district,data) VALUES("."'".$id."'".",'".$nam."'".",'".$disci."',"."'".$bg."',"."'".$mobile."'".",'".$email."',"."'".$pass."',"."'".$dist."',"."'".$date."'".");";
	/*
	$sql = "INSERT INTO donor (id,name,discipline,bloodgroup,mobile,email,password,district,data) VALUES('$id','$nam','$disci','$bg','$mobile','$email','$pass','$dist','$date');";
	*/
	$con = mysqli_connect("localhost", "root", "","bloodbank") or die(mysql_error());
	echo $sql;
	if($con)
	{	
		echo "Successfull!!!";
	}
	else
	{
		echo "failed";
	}
	mysqli_query($con,$sql);
	
	if($con)
	{
		echo "Query Success!!"."<br>";
	}
	else
	{
		echo mysqli_error($con);
	}
}

?>