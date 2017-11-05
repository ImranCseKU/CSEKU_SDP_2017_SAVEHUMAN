<?php

		$fcm_token=$_POST["fcm_token"];
		//echo $fcm_token;
		$con = mysqli_connect('localhost','root','','bloodbank');
		$sql = "INSERT INTO `fcm`(`token`) VALUES ('".$fcm_token."'); " ;
		$res = mysqli_query($con,$sql);
		mysqli_close($con);
	

?>