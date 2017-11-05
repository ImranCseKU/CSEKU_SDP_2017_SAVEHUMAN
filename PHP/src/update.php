<?php
if( isset($_POST['dated']) OR isset($_POST['name']) OR isset($_POST['mobile']) OR isset($_POST['email']) OR isset($_POST['pass']) OR isset($_POST['LOCATION']) )
{
	$id = $_POST['id'];
	$dated = $_POST['dated'];
	$name = $_POST['name'];
	$mobile = $_POST['mobile'];
	$email = $_POST['email'];
	$pass = $_POST['pass'];
	$location=$_POST['LOCATION'];
	$sql = "UPDATE donor SET ";
	$go = 0;
	if(!empty($name))
	{
		$go = 1;
		$sql = $sql."name = "."'".$name."' ";
	}
	if(!empty($mobile))
	{
		
		if($go==1)
		{
			$sql = $sql.", mobile = "."'".$mobile."' ";
		}
		else
		{
			$sql = $sql."mobile = "."'".$mobile."' ";
		}
		$go = 1;
	}
	if(!empty($email))
	{
		if($go==1)
		{
			$sql = $sql.", email = "."'".$email."' ";
		}
		else
		{
			$sql = $sql."email = "."'".$email."' ";
		}
		$go = 1;
	}
	if(!empty($pass))
	{
		if($go==1)
		{
			$sql = $sql.", password = "."'".$pass."' ";
		}
		else
		{
			$sql = $sql."password = "."'".$pass."' ";
		}
		$go = 1;
	}
	
	if(!empty($location))
	{
		if($go==1)
		{
			$sql = $sql.", district = "."'".$location."' ";
		}
		else
		{
			$sql = $sql."district = "."'".$location."' ";
		}
		$go = 1;
	}

	if(!empty($dated))
	{
		if($go==1)
		{
			$sql = $sql.", data = "."'".$dated."'  ";
		}
		else
		{
			$sql = $sql."data = "."'".$dated."'  ";
		}
		$go = 1;
	}
	$con = mysqli_connect("localhost", "root", "","bloodbank") or die(mysql_error());
	if($go==1)
	{
		$sql = $sql."WHERE id=".$id." ;";
		//echo $sql;
		mysqli_query($con,$sql);
		if($con)
		{
			echo "true";
		}
		else
		{
			echo "Connection Error!";
		}
	}
	
}

?>  