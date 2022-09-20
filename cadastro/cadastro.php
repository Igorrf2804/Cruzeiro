<?php

$servername = "";
$username = "cruzeiro_db";
$password = "";
$dbname = "cruzeiro_db";

// Pegando o input dos dados
$name = $_POST['name'];
//$data_nasc = $_POST['date'];
$data_nasc = $_POST['idade'];
$cpf = $_POST['cpf'];
$email = $_POST['email'];
$telefone = $_POST['telefone'];
$cpf_resp = $_POST['cpfResp'];
$tel_resp = $_POST['numberResp'];

// Criando conexão com o banco
	$con = mysqli_connect($servername,$username,$password,$dbname);

	if (mysqli_connect_errno()) {
	echo "Falha de conexão com o banco de dados: " . mysqli_connect_error();
	exit();

	} else {
		$sql1 = "SELECT `cpf` FROM `Pessoas` WHERE `cpf` = '$cpf'";
		$select = mysqli_query($con,$sql1);
		$row = mysqli_num_rows($select);
		if ($row > 0) {
			echo '<script type="text/JavaScript"> 
			alert("O CPF já está cadastrado!");
			javascript:history.go(-1)
			</script>';
		} else {
			$sql2 = "INSERT INTO `Pessoas` (`nome`, idade, `cpf`, `email`, `telefone`) 
				VALUES ('$name', $data_nasc, '$cpf', '$email', '$telefone')";
			$insert_pessoas = mysqli_query($con,$sql2);
				if ($insert_pessoas && $data_nasc >= 18){
					$sql3 = "INSERT INTO `Clientes` (`senha`, `fk_Pessoas_id`) 
						VALUES ('user@$cpf', LAST_INSERT_ID())";
					$insert_clientes = mysqli_query($con,$sql3);
					echo '<script type="text/JavaScript"> 
					alert("Cadastrado com sucesso!");
					javascript:history.go(-1)
					</script>';
				} else if ($insert_pessoas && $data_nasc < 18) {
							$sql5 = "INSERT INTO `ClienteMenor` (`fk_Pessoas_id`) 
								VALUES (LAST_INSERT_ID())";
							$insert_clientemenor = mysqli_query($con,$sql5);							
							$sql6 = "SELECT Clientes.id FROM Clientes INNER JOIN Pessoas ON Clientes.fk_Pessoas_id = Pessoas.id WHERE Pessoas.cpf = '$cpf_resp'";
							$verifica_resp = mysqli_query($con,$sql6);	
							$arrayresp = mysqli_fetch_array($verifica_resp);
							$id_maior = $arrayresp['id'];
							$sql7 = "INSERT INTO `responsavel` (`fk_ClienteMenor_id`,`fk_Clientes_id`)
								VALUES (LAST_INSERT_ID(), $id_maior)";
							$insert_resp = mysqli_query($con,$sql7);
							echo '<script type="text/JavaScript"> 
							alert("Cadastrado com sucesso AQUI!");
							javascript:history.go(-1)
							</script>';
						
							}	
			}
	}	
?>
