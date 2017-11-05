<?php
	
	$con = mysqli_connect('localhost','root','','bloodbank') or die(mysqli_error());
	$message= $_POST['message'];
	$title = $_POST['title'];
	$path_to_fcm ='https://fcm.googleapis.com/fcm/send';
	$server_key= "AAAAeRGKCoE:APA91bFiEu3fI_jznyC4g-D0yhQOU8lDhrNLQfKoFePyOc6SP_9VL9fkzCrcIH1nzbxjCcBRvcqOPTm0QeOPZqgZPVYyA-WH8xTDtX7BVDpHYmGko8QMlD_wuGp-8IRM9OgzSut7F0pO" ;
	$sql = "SELECT  token FROM fcm; ";
	$result=mysqli_query($con,$sql);
	$row=mysqli_fetch_row($result);
	$key=$row[1];
	
	$headers= array(
	'Authorization:key=' .$server_key,
	'Content-type:application/json'
	);
	
	$fields= array( 'to'=>$key, 'notification'=>array('title'=>$title, 'body'=>$message));
	
	$payload=json_encode($fields);
	$curl_session =curl_init();
	
	curl_setopt( $curl_session,CURLOPT_URL, $path_to_fcm );
	curl_setopt( $curl_session,CURLOPT_POST, true );
	curl_setopt( $curl_session,CURLOPT_HTTPHEADER, $headers );
	curl_setopt( $curl_session,CURLOPT_RETURNTRANSFER, true );
	curl_setopt( $curl_session,CURLOPT_SSL_VERIFYPEER, false );
	curl_setopt( $curl_session,CURLOPT_IPRESOLVE, CURL_IPRESOLVE_V4 );
	curl_setopt( $curl_session,CURLOPT_POSTFIELDS,$payload );
	
	$result = curl_exec($curl_session );
	curl_close( $curl_session );
	mysqli_close($con);
?>