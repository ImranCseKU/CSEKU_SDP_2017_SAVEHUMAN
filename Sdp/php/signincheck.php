<?php
if(isset($_POST['id']) and isset($_POST['pass']))
{
	$id = $_POST['id'];
	$pass = $_POST['pass'];
	$sql = "SELECT id,password FROM donor WHERE id="."'".$id."'"." AND password="."'".$pass."'".";";
	$con = mysqli_connect('localhost','root','','bloodbank') or die(mysqli_error());
	$res = mysqli_query($con,$sql);
	if($con)
	{
		$cnt = 0;
		while($row = mysqli_fetch_assoc($res))
		{
			if($row['id']==$id and $row['password'] == $pass)
			{
				$cnt = 1;
				echo "true";
				break;
			}
		}
		if($cnt == 0)
		{
			echo "false";
		}
	}
	else
	{
		echo "Connection Error con!";
	}
}

?>