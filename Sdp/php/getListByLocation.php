<?php
if(isset($_POST['GROUP']) and isset($_POST['LOCATION']))
{
	$bg = $_POST['GROUP'];
	$location=$_POST['LOCATION'];
	//echo $bg;
	$sql = "SELECT id,name,discipline,mobile,email,district,data FROM donor WHERE bloodgroup="."'".$bg."'"." AND district="."'".$location."'".";";
	//echo $sql;
	$con = mysqli_connect("localhost", "root", "","bloodbank") or die(mysql_error());
	$res = mysqli_query($con,$sql);
	if($con)
	{
		$cnt = 0;
		while($row = mysqli_fetch_assoc($res))
		{
			$cnt += 1;
			echo $row['id']."\n";
			echo $row['name']."\n";
			echo $row['discipline']."\n";
			echo $row['mobile']."\n";
			echo $row['email']."\n";
			echo $row['district']."\n";
			echo $row['data']."\n";	
		}
		if($cnt == 0)
		{
			echo "false";
		}
	}
	else
	{
		echo "Connection Error!";
	}
}

?>