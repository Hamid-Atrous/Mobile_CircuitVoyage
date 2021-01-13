<?php
define("SERVEUR","localhost");
define("USAGER","root");
define("PASS","");
define("BD","circuit_bd");

$connexion = new mysqli(SERVEUR,USAGER,PASS,BD);
if ($connexion->connect_errno) {
	echo "Probleme de connexion au serveur de bd";
	exit();
}
?>