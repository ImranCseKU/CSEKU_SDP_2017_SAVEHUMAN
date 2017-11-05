<?php
if(isset($_POST['bg']))
{
	$bg = $_POST['bg'];
	//echo $bg;
	//$sql = "SELECT id,name,discipline,mobile,email,district,data FROM donor WHERE bloodgroup="."'".$bg."'".";";
	$sql=" SELECT id,name,discipline,mobile,email,district,data FROM `donor` WHERE `bloodgroup`="."'".$bg."'"." AND (SELECT ADDDATE(`data`,INTERVAL 90 DAY)) <(SELECT CURDATE())";
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
			//echo $row['IsDonate']."\n";	
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